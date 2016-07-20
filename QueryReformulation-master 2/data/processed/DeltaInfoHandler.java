/***/
package org.eclipse.ui.examples.contributions.editor;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.examples.contributions.ContributionMessages;
import org.eclipse.ui.examples.contributions.model.IPersonService;
import org.eclipse.ui.examples.contributions.model.Person;
import org.eclipse.ui.handlers.HandlerUtil;

/**
* Show if there is any delta from the model for the active editor.
*
* @since 3.3
*/
public class DeltaInfoHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        InfoEditor editor = (InfoEditor) HandlerUtil.getActiveEditorChecked(event);
        Person local = editor.getCurrentPerson();
        IPersonService service = editor.getSite().getService(IPersonService.class);
        Person model = service.getPerson(local.getId());
        boolean delta = false;
        StringBuffer buf = new StringBuffer();
        buf.append(ContributionMessages.InfoEditor_surname);
        if (!model.getSurname().equals(local.getSurname())) {
            delta = true;
            buf.append(' ');
            buf.append(model.getSurname());
            //$NON-NLS-1$
            buf.append(", ");
            buf.append(local.getSurname());
        }
        //$NON-NLS-1$
        buf.append(" - ");
        buf.append(ContributionMessages.InfoEditor_givenname);
        if (!model.getGivenname().equals(local.getGivenname())) {
            delta = true;
            buf.append(' ');
            buf.append(model.getGivenname());
            //$NON-NLS-1$
            buf.append(", ");
            buf.append(local.getGivenname());
        }
        //$NON-NLS-1$
        buf.append(" - ");
        if (delta) {
            buf.append(ContributionMessages.DeltaInfoHandler_found);
        } else {
            buf.append(ContributionMessages.DeltaInfoHandler_notFound);
        }
        MessageDialog.openInformation(editor.getSite().getShell(), ContributionMessages.DeltaInfoHandler_shellTitle, buf.toString());
        return null;
    }
}
