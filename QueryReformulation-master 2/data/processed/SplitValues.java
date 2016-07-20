/***/
package org.eclipse.ui.internal;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.commands.IParameterValues;

/**
* Display the values that can be used in the keybindings page and quick access.
*
* @since 3.106
*/
public class SplitValues implements IParameterValues {

    private HashMap<String, String> values = new HashMap();

    public  SplitValues() {
        //$NON-NLS-1$
        values.put(WorkbenchMessages.SplitValues_Horizontal, "true");
        //$NON-NLS-1$
        values.put(WorkbenchMessages.SplitValues_Vertical, "false");
    }

    @Override
    public Map getParameterValues() {
        return values;
    }
}
