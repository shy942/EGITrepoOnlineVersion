/***/
package org.eclipse.jface.preference;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;

/**
* A field editor to edit directory paths.
*/
public class PathEditor extends ListEditor {

    /**
* The last path, or <code>null</code> if none.
*/
    private String lastPath;

    /**
* The special label text for directory chooser,
* or <code>null</code> if none.
*/
    private String dirChooserLabelText;

    /**
* Creates a new path field editor
*/
    protected  PathEditor() {
    }

    /**
* Creates a path field editor.
*
* @param name the name of the preference this field editor works on
* @param labelText the label text of the field editor
* @param dirChooserLabelText the label text displayed for the directory chooser
* @param parent the parent of the field editor's control
*/
    public  PathEditor(String name, String labelText, String dirChooserLabelText, Composite parent) {
        init(name, labelText);
        this.dirChooserLabelText = dirChooserLabelText;
        createControl(parent);
    }

    @Override
    protected String createList(String[] items) {
        //$NON-NLS-1$
        StringBuffer path = new StringBuffer("");
        for (int i = 0; i < items.length; i++) {
            path.append(items[i]);
            path.append(File.pathSeparator);
        }
        return path.toString();
    }

    @Override
    protected String getNewInputObject() {
        DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.SHEET);
        if (dirChooserLabelText != null) {
            dialog.setMessage(dirChooserLabelText);
        }
        if (lastPath != null) {
            if (new File(lastPath).exists()) {
                dialog.setFilterPath(lastPath);
            }
        }
        String dir = dialog.open();
        if (dir != null) {
            dir = dir.trim();
            if (dir.length() == 0) {
                return null;
            }
            lastPath = dir;
        }
        return dir;
    }

    @Override
    protected String[] parseString(String stringList) {
        StringTokenizer st = new StringTokenizer(stringList, File.pathSeparator + //$NON-NLS-1$
        "\n\r");
        ArrayList<Object> v = new ArrayList();
        while (st.hasMoreElements()) {
            v.add(st.nextElement());
        }
        return v.toArray(new String[v.size()]);
    }
}
