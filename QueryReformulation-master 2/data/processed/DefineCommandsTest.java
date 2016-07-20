/***/
package org.eclipse.e4.core.commands.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.e4.core.commands.CommandServiceAddon;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DefineCommandsTest {

    private static final String TEST_ID2 = "test.id2";

    private static final String TEST_ID1 = "test.id1";

    private static final String TEST_CAT1 = "test.cat1";

    private IEclipseContext workbenchContext;

    @Before
    public void setUp() {
        IEclipseContext globalContext = TestActivator.getDefault().getGlobalContext();
        workbenchContext = globalContext.createChild("workbenchContext");
        ContextInjectionFactory.make(CommandServiceAddon.class, workbenchContext);
    }

    @After
    public void tearDown() {
        workbenchContext.dispose();
    }

    @Test
    public void testCreateCommands() {
        ECommandService cs = workbenchContext.get(ECommandService.class);
        assertNotNull(cs);
        assertNotNull(cs.defineCategory(TEST_CAT1, "CAT1", null));
        Category category = cs.getCategory(TEST_CAT1);
        assertNotNull("need category", category);
        assertNotNull("command1", cs.defineCommand(TEST_ID1, "ID1", null, category, null));
        assertNotNull("command2", cs.defineCommand(TEST_ID2, "ID2", null, category, null));
        Command cmd1 = cs.getCommand(TEST_ID1);
        assertNotNull("get command1", cmd1);
        try {
            assertEquals("ID1", cmd1.getName());
        } catch (NotDefinedException e) {
            fail(e.getMessage());
        }
        assertNotNull("get command2", cs.getCommand(TEST_ID2));
        assertNotNull("parameterized command", cs.createCommand(TEST_ID1, null));
    }

    @Test
    public void testCreateWithSecondContexts() {
        IEclipseContext localContext = workbenchContext.createChild();
        ECommandService cs = localContext.get(ECommandService.class);
        assertNotNull(cs);
        assertNotNull(cs.defineCategory(TEST_CAT1, "CAT1", null));
        Category category = cs.getCategory(TEST_CAT1);
        assertNotNull("need category", category);
        assertNotNull("command1", cs.defineCommand(TEST_ID1, "ID1", null, category, null));
        assertNotNull("command2", cs.defineCommand(TEST_ID2, "ID2", null, category, null));
        Command cmd1 = cs.getCommand(TEST_ID1);
        assertNotNull("get command1", cmd1);
        try {
            assertEquals("ID1", cmd1.getName());
        } catch (NotDefinedException e) {
            fail(e.getMessage());
        }
        assertNotNull("get command2", cs.getCommand(TEST_ID2));
    }

    @Test
    public void testCreateWithTwoContexts() {
        IEclipseContext localContext = workbenchContext.createChild("Level1");
        ECommandService cs = localContext.get(ECommandService.class);
        assertNotNull(cs);
        assertNotNull(cs.defineCategory(TEST_CAT1, "CAT1", null));
        Category category = cs.getCategory(TEST_CAT1);
        assertNotNull("need category", category);
        assertNotNull("command1", cs.defineCommand(TEST_ID1, "ID1", null, category, null));
        assertNotNull("command2", cs.defineCommand(TEST_ID2, "ID2", null, category, null));
        cs = (ECommandService) workbenchContext.get(ECommandService.class.getName());
        Command cmd1 = cs.getCommand(TEST_ID1);
        assertNotNull("get command1", cmd1);
        try {
            assertEquals("ID1", cmd1.getName());
        } catch (NotDefinedException e) {
            fail(e.getMessage());
        }
        assertNotNull("get command2", cs.getCommand(TEST_ID2));
    }
}
