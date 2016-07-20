/***/
package org.eclipse.ui.tests.decorators;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.decorators.DecoratorDefinition;
import org.eclipse.ui.internal.decorators.DecoratorManager;
import org.eclipse.ui.tests.navigator.AbstractNavigatorTest;

/**
* @version 	1.0
*/
public abstract class DecoratorEnablementTestCase extends AbstractNavigatorTest implements ILabelProviderListener {

    protected DecoratorDefinition definition;

    protected boolean updated = false;

    /**
* Constructor for DecoratorTestCase.
* @param testName
*/
    public  DecoratorEnablementTestCase(String testName) {
        super(testName);
    }

    /**
* Sets up the hierarchy.
*/
    @Override
    protected void doSetUp() throws Exception {
        super.doSetUp();
        createTestFile();
        showNav();
        WorkbenchPlugin.getDefault().getDecoratorManager().addListener(this);
        DecoratorDefinition[] definitions = WorkbenchPlugin.getDefault().getDecoratorManager().getAllDecoratorDefinitions();
        for (DecoratorDefinition definition2 : definitions) {
            if (definition2.getId().equals("org.eclipse.ui.tests.decorators.lightweightdecorator")) {
                definition = definition2;
            }
        }
    }

    protected DecoratorManager getDecoratorManager() {
        return WorkbenchPlugin.getDefault().getDecoratorManager();
    }

    /**
* Remove the listener.
*/
    @Override
    protected void doTearDown() throws Exception {
        super.doTearDown();
        getDecoratorManager().removeListener(this);
    }

    /**
* Test enabling the contributor
*/
    public void testEnableDecorator() {
        getDecoratorManager().clearCaches();
        definition.setEnabled(true);
        getDecoratorManager().updateForEnablementChange();
    }

    /**
* Test disabling the contributor
*/
    public void testDisableDecorator() {
        getDecoratorManager().clearCaches();
        definition.setEnabled(false);
        getDecoratorManager().updateForEnablementChange();
    }

    /*
* @see ILabelProviderListener#labelProviderChanged(LabelProviderChangedEvent)
*/
    @Override
    public void labelProviderChanged(LabelProviderChangedEvent event) {
        updated = true;
    }
}
