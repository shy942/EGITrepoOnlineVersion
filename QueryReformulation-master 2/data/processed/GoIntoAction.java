/***/
package org.eclipse.ui.internal.navigator.framelist;

import org.eclipse.ui.PlatformUI;

/**
* Generic "Go Into" action which goes to the frame for the current selection.
* @since 3.4
*/
public class GoIntoAction extends FrameAction {

    //$NON-NLS-1$
    private static final String ID = "org.eclipse.ui.framelist.goInto";

    /**
* Constructs a new action for the specified frame list.
*
* @param frameList the frame list
*/
    public  GoIntoAction(FrameList frameList) {
        super(frameList);
        setId(ID);
        setText(FrameListMessages.GoInto_text);
        setToolTipText(FrameListMessages.GoInto_toolTip);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IFrameListHelpContextIds.GO_INTO_ACTION);
        update();
    }

    private Frame getSelectionFrame(int flags) {
        return getFrameList().getSource().getFrame(IFrameSource.SELECTION_FRAME, flags);
    }

    /**
* Calls <code>gotoFrame</code> on the frame list with a frame
* representing the currently selected container.
*/
    @Override
    public void run() {
        Frame selectionFrame = getSelectionFrame(IFrameSource.FULL_CONTEXT);
        if (selectionFrame != null) {
            getFrameList().gotoFrame(selectionFrame);
        }
    }

    /**
* Updates this action's enabled state.
* This action is enabled only when there is a frame for the current selection.
*/
    @Override
    public void update() {
        super.update();
        Frame selectionFrame = getSelectionFrame(0);
        setEnabled(selectionFrame != null);
    }
}
