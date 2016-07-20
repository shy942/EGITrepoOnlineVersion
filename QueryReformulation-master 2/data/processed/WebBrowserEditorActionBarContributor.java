/***/
package org.eclipse.ui.internal.browser;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.EditorActionBarContributor;

/**
* ActionBarContributor for the Web browser. Just adds cut, copy, paste actions.
*/
public class WebBrowserEditorActionBarContributor extends EditorActionBarContributor {

    protected WebBrowserEditor editor;

    protected Action back;

    protected Action forward;

    protected Updater updater = new Updater();

    class Updater implements BrowserViewer.IBackNextListener {

        @Override
        public void updateBackNextBusy() {
            if (back == null)
                return;
            back.setEnabled(getWebBrowser().isBackEnabled());
            forward.setEnabled(getWebBrowser().isForwardEnabled());
        // busy.setBusy(getWebBrowser().loading);
        }
    }

    /**
* WebBrowserEditorActionBarContributor constructor comment.
*/
    public  WebBrowserEditorActionBarContributor() {
        super();
    }

    @Override
    public void setActiveEditor(IEditorPart targetEditor) {
        if (targetEditor instanceof WebBrowserEditor) {
            editor = (WebBrowserEditor) targetEditor;
        } else
            editor = null;
    }

    protected BrowserViewer getWebBrowser() {
        if (editor == null)
            return null;
        return editor.webBrowser;
    }
}
