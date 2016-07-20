/***/
package org.eclipse.ui.tests.decorators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.decorators.DecoratorDefinition;
import org.eclipse.ui.internal.decorators.DecoratorManager;

/**
* @version 	1.0
*/
public class ExceptionDecoratorTestCase extends DecoratorEnablementTestCase implements ILabelProviderListener {

    private Collection problemDecorators = new ArrayList();

    private DecoratorDefinition light;

    /**
* Constructor for DecoratorTestCase.
* @param testName
*/
    public  ExceptionDecoratorTestCase(String testName) {
        super(testName);
    }

    /**
* Sets up the hierarchy.
*/
    @Override
    protected void doSetUp() throws Exception {
        //reset the static fields so that the decorators will fail
        HeavyNullImageDecorator.fail = true;
        HeavyNullTextDecorator.fail = true;
        NullImageDecorator.fail = true;
        DecoratorDefinition[] definitions = WorkbenchPlugin.getDefault().getDecoratorManager().getAllDecoratorDefinitions();
        for (DecoratorDefinition definition2 : definitions) {
            String id = definition2.getId();
            if (id.equals("org.eclipse.ui.tests.heavyNullImageDecorator") || id.equals("org.eclipse.ui.tests.heavyNullTextDecorator")) {
                definition2.setEnabled(true);
                problemDecorators.add(definition2);
            }
            //still need to be worked out.
            if (id.equals("org.eclipse.ui.tests.lightNullImageDecorator")) {
                definition2.setEnabled(true);
                light = definition2;
            }
        }
        super.doSetUp();
    }

    @Override
    protected void doTearDown() throws Exception {
        super.doTearDown();
        //errors to occur
        try {
            Platform.getJobManager().join(DecoratorManager.FAMILY_DECORATE, null);
        } catch (OperationCanceledException e) {
        } catch (InterruptedException e) {
        }
        //Be sure that the decorators were all disabled on errors.
        Iterator problemIterator = problemDecorators.iterator();
        while (problemIterator.hasNext()) {
            DecoratorDefinition next = (DecoratorDefinition) problemIterator.next();
            assertFalse("Enabled " + next.getName(), next.isEnabled());
        }
        //Turnoff the lightweight one so as not to clutter the methods.
        light.setEnabled(false);
    }
}
