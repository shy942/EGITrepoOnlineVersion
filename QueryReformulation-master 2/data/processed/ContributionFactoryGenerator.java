/***/
package org.eclipse.ui.internal.menus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.internal.workbench.OpaqueElementUtil;
import org.eclipse.e4.ui.model.application.ui.MCoreExpression;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.impl.UiFactoryImpl;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MToolItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.services.ServiceLocator;
import org.eclipse.ui.menus.AbstractContributionFactory;
import org.eclipse.ui.menus.IMenuService;

public class ContributionFactoryGenerator extends ContextFunction {

    private AbstractContributionFactory factoryImpl;

    private IConfigurationElement configElement;

    private int type;

    public  ContributionFactoryGenerator(AbstractContributionFactory factory, int type) {
        this.factoryImpl = factory;
        this.type = type;
    }

    public  ContributionFactoryGenerator(IConfigurationElement element, int type) {
        configElement = element;
        this.type = type;
    }

    private AbstractContributionFactory getFactory() {
        if (factoryImpl == null && configElement != null) {
            try {
                factoryImpl = (AbstractContributionFactory) configElement.createExecutableExtension(//$NON-NLS-1$
                "class");
            } catch (CoreException e) {
                WorkbenchPlugin.log(e);
                return null;
            }
        }
        return factoryImpl;
    }

    @Override
    public Object compute(IEclipseContext context, String contextKey) {
        AbstractContributionFactory factory = getFactory();
        final IMenuService menuService = context.get(IMenuService.class);
        final ContributionRoot root = new ContributionRoot(menuService, new HashSet(), null, factory);
        ServiceLocator sl = new ServiceLocator();
        sl.setContext(context);
        factory.createContributionItems(sl, root);
        final List contributionItems = root.getItems();
        final Map<IContributionItem, Expression> itemsToExpression = root.getVisibleWhen();
        List<MUIElement> menuElements = new ArrayList();
        for (Object obj : contributionItems) {
            if (obj instanceof IContributionItem) {
                IContributionItem ici = (IContributionItem) obj;
                MUIElement opaqueItem = createUIElement(ici);
                if (opaqueItem != null) {
                    if (itemsToExpression.containsKey(ici)) {
                        final Expression ex = itemsToExpression.get(ici);
                        MCoreExpression exp = UiFactoryImpl.eINSTANCE.createCoreExpression();
                        //$NON-NLS-1$
                        exp.setCoreExpressionId("programmatic." + ici.getId());
                        exp.setCoreExpression(ex);
                        opaqueItem.setVisibleWhen(exp);
                    }
                    menuElements.add(opaqueItem);
                }
            }
        }
        context.set(List.class, menuElements);
        // return something disposable
        return new Runnable() {

            @Override
            public void run() {
                root.release();
            }
        };
    }

    private MUIElement createUIElement(IContributionItem ici) {
        switch(type) {
            case 0:
                return createMenuItem(ici);
            case 1:
                return createToolItem(ici);
        }
        return null;
    }

    private MUIElement createMenuItem(IContributionItem ici) {
        MMenuItem opaqueItem = OpaqueElementUtil.createOpaqueMenuItem();
        opaqueItem.setElementId(ici.getId());
        OpaqueElementUtil.setOpaqueItem(opaqueItem, ici);
        return opaqueItem;
    }

    private MUIElement createToolItem(IContributionItem ici) {
        MToolItem opaqueItem = OpaqueElementUtil.createOpaqueToolItem();
        opaqueItem.setElementId(ici.getId());
        OpaqueElementUtil.setOpaqueItem(opaqueItem, ici);
        return opaqueItem;
    }
}
