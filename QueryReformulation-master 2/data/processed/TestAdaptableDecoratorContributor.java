/***/
package org.eclipse.ui.tests.decorators;

import junit.framework.Assert;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.ui.tests.menus.ObjectContributionClasses;

/**
* Decorator for testing adaptability. This class is used to test
* the adaptable contribution. It can also be subclassed
* and the expected class and suffix can be customized
*/
public class TestAdaptableDecoratorContributor extends TestLightweightDecoratorContributor {

    public static final String SUFFIX = "ICommon.1";

    public static final String ID = "org.eclipse.ui.tests.decorators.generalAdaptabilityOn";

    private Class clazz;

    private String suffix;

    public  TestAdaptableDecoratorContributor() {
        setExpectedElementType(ObjectContributionClasses.ICommon.class);
        setSuffix(SUFFIX);
    }

    protected void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    protected void setExpectedElementType(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public void decorate(Object element, IDecoration decoration) {
        Assert.assertTrue(clazz.isInstance(element));
        decoration.addSuffix(suffix);
    }
}
