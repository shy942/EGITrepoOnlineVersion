/***/
package org.eclipse.ui.tests.api;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;

/**
* This view is used to test the creation and restoration of
* view state between sessions.
*/
public class SessionView extends MockViewPart {

    private IMemento memento;

    public static String VIEW_ID = "org.eclipse.ui.tests.api.SessionView";

    /**
* Constructor for SessionView
*/
    public  SessionView() {
        super();
    }

    /**
* @see IViewPart#init(IViewSite, IMemento)
*/
    @Override
    public void init(IViewSite site, IMemento memento) {
        super.init(site, memento);
        this.memento = memento;
    }

    /**
* Create an IMemento.
*/
    @Override
    public void saveState(IMemento memento) {
        createMementoState(memento);
    }

    /**
* Creates an IMemento.
*/
    private void createMementoState(IMemento memento) {
        // Create float, integer and string.
        memento.putFloat("float", 0.50f);
        memento.putInteger("integer", 50);
        memento.putString("string", "50");
        // Create a single child.
        IMemento child = memento.createChild("single");
        child.putInteger("id", 1);
        // Create multiple children.
        int count = 10;
        for (int nX = 0; nX < count; nX++) {
            child = memento.createChild("multiple");
            child.putInteger("id", nX);
        }
        memento.putInteger("multiple.count", count);
    }

    /**
* Restore an IMemento.
*/
    public void testMementoState(TestCase testCase) {
        // Verify that the memento was passed to us in
        // constructor.
        Assert.assertNotNull(memento);
        // Read float.
        Float bigFloat = memento.getFloat("float");
        Assert.assertNotNull(bigFloat);
        Assert.assertEquals(bigFloat.floatValue(), 0.50f, 0.0001);
        // Read int.
        Integer bigInt = memento.getInteger("integer");
        Assert.assertEquals(bigInt, Integer.valueOf(50));
        // Read string.
        String str = memento.getString("string");
        Assert.assertEquals(str, "50");
        // Read single child.
        IMemento child = memento.getChild("single");
        Assert.assertNotNull(child);
        bigInt = child.getInteger("id");
        Assert.assertEquals(bigInt, Integer.valueOf(1));
        // Read multiple children.
        bigInt = memento.getInteger("multiple.count");
        Assert.assertNotNull(bigInt);
        int count = bigInt.intValue();
        IMemento[] children = memento.getChildren("multiple");
        Assert.assertEquals(count, children.length);
        for (int nX = 0; nX < count; nX++) {
            child = children[nX];
            Assert.assertNotNull(child);
            bigInt = child.getInteger("id");
            Assert.assertEquals(bigInt, Integer.valueOf(nX));
        }
    }
}
