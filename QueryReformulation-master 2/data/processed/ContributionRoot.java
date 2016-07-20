/***/
package org.eclipse.ui.internal.menus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.core.expressions.Expression;
import org.eclipse.jface.action.ContributionManager;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.internal.expressions.AlwaysEnabledExpression;
import org.eclipse.ui.menus.AbstractContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.menus.IMenuService;

/**
* Default implementation.
*
* @since 3.3
*/
final class ContributionRoot implements IContributionRoot {

    private List topLevelItems = new ArrayList();

    private Map<IContributionItem, Expression> itemsToExpressions = new HashMap();

    Set restriction;

    private ContributionManager mgr;

    private AbstractContributionFactory factory;

    public  ContributionRoot(IMenuService menuService, Set restriction, ContributionManager mgr, AbstractContributionFactory factory) {
        this.restriction = restriction;
        this.mgr = mgr;
        this.factory = factory;
    }

    @Override
    public void addContributionItem(IContributionItem item, Expression visibleWhen) {
        if (item == null)
            throw new IllegalArgumentException();
        topLevelItems.add(item);
        if (visibleWhen == null)
            visibleWhen = AlwaysEnabledExpression.INSTANCE;
        // menuService.registerVisibleWhen(item, visibleWhen, restriction,
        // createIdentifierId(item));
        itemsToExpressions.put(item, visibleWhen);
    }

    /**
* Create the activity identifier for this contribution item.
*
* @param item the item
* @return the identifier
*/
    String createIdentifierId(IContributionItem item) {
        String namespace = factory.getNamespace();
        String identifierID = namespace != null ? namespace + '/' + item.getId() : // create the activity identifier ID. If
        null;
        // it will be null.
        return identifierID;
    }

    public List getItems() {
        return topLevelItems;
    }

    public Map<IContributionItem, Expression> getVisibleWhen() {
        return itemsToExpressions;
    }

    /**
* Unregister all visible when expressions from the menu service.
*/
    public void release() {
        for (Iterator<IContributionItem> itemIter = itemsToExpressions.keySet().iterator(); itemIter.hasNext(); ) {
            IContributionItem item = itemIter.next();
            // menuService.unregisterVisibleWhen(item, restriction);
            item.dispose();
        }
        itemsToExpressions.clear();
        topLevelItems.clear();
    }

    @Override
    public void registerVisibilityForChild(IContributionItem item, Expression visibleWhen) {
        if (item == null)
            throw new IllegalArgumentException();
        if (visibleWhen == null)
            visibleWhen = AlwaysEnabledExpression.INSTANCE;
        // menuService.registerVisibleWhen(item, visibleWhen, restriction,
        // createIdentifierId(item));
        itemsToExpressions.put(item, visibleWhen);
    }

    /**
* @return Returns the mgr.
*/
    public ContributionManager getManager() {
        return mgr;
    }
}