/***/
package org.eclipse.ui.tests.api;

import junit.framework.TestCase;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.tests.harness.util.ArrayUtil;

public class IPerspectiveRegistryTest extends TestCase {

    private IPerspectiveRegistry fReg;

    public  IPerspectiveRegistryTest(String testName) {
        super(testName);
    }

    @Override
    public void setUp() {
        fReg = PlatformUI.getWorkbench().getPerspectiveRegistry();
    }

    public void testFindPerspectiveWithId() {
        IPerspectiveDescriptor pers = (IPerspectiveDescriptor) ArrayUtil.pickRandom(fReg.getPerspectives());
        IPerspectiveDescriptor suspect = fReg.findPerspectiveWithId(pers.getId());
        assertNotNull(suspect);
        assertEquals(pers, suspect);
        suspect = fReg.findPerspectiveWithId(IConstants.FakeID);
        assertNull(suspect);
    }

    /*
public void testFindPerspectiveWithLabel()
{
IPerspectiveDescriptor pers = ( IPerspectiveDescriptor )ArrayUtil.pickRandom( fReg.getPerspectives() );
IPerspectiveDescriptor suspect = fReg.findPerspectiveWithLabel( pers.getLabel() );
assertNotNull( suspect );
assertEquals( pers, suspect );
suspect = fReg.findPerspectiveWithLabel( IConstants.FakeLabel );
assertNull( suspect );
}
*/
    public void testGetDefaultPerspective() {
        String id = fReg.getDefaultPerspective();
        assertNotNull(id);
        IPerspectiveDescriptor suspect = fReg.findPerspectiveWithId(id);
        assertNotNull(suspect);
    }

    public void testSetDefaultPerspective() {
        IPerspectiveDescriptor pers = (IPerspectiveDescriptor) ArrayUtil.pickRandom(fReg.getPerspectives());
        fReg.setDefaultPerspective(pers.getId());
        assertEquals(pers.getId(), fReg.getDefaultPerspective());
    }

    public void testGetPerspectives() throws Throwable {
        IPerspectiveDescriptor[] pers = fReg.getPerspectives();
        assertNotNull(pers);
        for (IPerspectiveDescriptor per : pers) {
            assertNotNull(per);
        }
    }

    public void XXXtestDeleteClonedPerspective() {
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        String perspId = page.getPerspective().getId() + ".1";
        IPerspectiveDescriptor desc = fReg.clonePerspective(perspId, perspId, page.getPerspective());
        page.setPerspective(desc);
        assertNotNull(fReg.findPerspectiveWithId(perspId));
        page.closePerspective(desc, false, false);
        fReg.deletePerspective(desc);
        assertNull(fReg.findPerspectiveWithId(perspId));
    }
}
