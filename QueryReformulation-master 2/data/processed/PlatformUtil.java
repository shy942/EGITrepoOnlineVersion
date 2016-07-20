/***/
package org.eclipse.ui.tests.harness.util;

import org.eclipse.jface.util.Util;

/**
* The Platform Util class is used to test for which platform we are in
*/
public class PlatformUtil {

    /**
* Determine if we are running on the Mac platform.
*
* @return true if we are runnig on the Mac platform.
*/
    public static boolean onMac() {
        return Util.isMac();
    }
}
