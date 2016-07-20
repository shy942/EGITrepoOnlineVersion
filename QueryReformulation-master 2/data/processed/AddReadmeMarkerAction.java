/***/
package org.eclipse.ui.examples.readmetool;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.MarkerUtilities;
import org.osgi.framework.Bundle;

/**
* Action for creating a readme marker with a specfic id
* attribute value.
*/
public class AddReadmeMarkerAction extends Action {

    private ITextEditor textEditor;

    private Object[][] customAttributes;

    private String message;

    //$NON-NLS-1$
    private static final String MARKER_TYPE = "org.eclipse.ui.examples.readmetool.readmemarker";

    /**
* Creates a new action for the given text editor.
*
* @param editor the text editor
* @param label the label for the action
* @param attributes the custom attributes for this marker
* @param message the message for the marker
*/
    public  AddReadmeMarkerAction(ITextEditor editor, String label, Object[][] attributes, String message) {
        textEditor = editor;
        setText(label);
        this.customAttributes = attributes;
        this.message = message;
    }

    /*
* @see IAction#run()
*/
    @Override
    public void run() {
        Map<String, Object> attributes = new HashMap(11);
        ITextSelection selection = (ITextSelection) textEditor.getSelectionProvider().getSelection();
        if (!selection.isEmpty()) {
            int start = selection.getOffset();
            int length = selection.getLength();
            if (length < 0) {
                length = -length;
                start -= length;
            }
            MarkerUtilities.setCharStart(attributes, start);
            MarkerUtilities.setCharEnd(attributes, start + length);
            // marker line numbers are 1-based
            int line = selection.getStartLine();
            MarkerUtilities.setLineNumber(attributes, line == -1 ? -1 : line + 1);
            // set custom attribute values
            for (int i = 0; i < customAttributes.length; i++) {
                attributes.put((String) customAttributes[i][0], customAttributes[i][1]);
            }
            MarkerUtilities.setMessage(attributes, message);
        }
        try {
            MarkerUtilities.createMarker(getResource(), attributes, MARKER_TYPE);
        } catch (CoreException x) {
            Bundle bundle = ReadmePlugin.getDefault().getBundle();
            Platform.getLog(bundle).log(x.getStatus());
            Shell shell = textEditor.getSite().getShell();
            String title = MessageUtil.getString("Add_readme_marker_error_title");
            String msg = MessageUtil.getString("Add_readme_marker_error_message");
            ErrorDialog.openError(shell, title, msg, x.getStatus());
        }
    }

    /**
* Returns the resource on which to create the marker,
* or <code>null</code> if there is no applicable resource. This
* queries the editor's input using <code>getAdapter(IResource.class)</code>.
*
* @return the resource to which to attach the newly created marker
*/
    protected IResource getResource() {
        IEditorInput input = textEditor.getEditorInput();
        return Adapters.adapt(input, IResource.class);
    }
}
