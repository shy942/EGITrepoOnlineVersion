/***/
package org.eclipse.ui.internal.ide.dialogs;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.internal.ide.AboutInfo;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;

/**
* A simple editor input for the welcome editor
*/
public class WelcomeEditorInput implements IEditorInput {

    private AboutInfo aboutInfo;

    //$NON-NLS-1$
    private static final String FACTORY_ID = "org.eclipse.ui.internal.dialogs.WelcomeEditorInputFactory";

    //$NON-NLS-1$
    public static final String FEATURE_ID = "featureId";

    /**
* WelcomeEditorInput constructor comment.
*/
    public  WelcomeEditorInput(AboutInfo info) {
        super();
        if (info == null) {
            throw new IllegalArgumentException();
        }
        aboutInfo = info;
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public <T> T getAdapter(Class<T> adapter) {
        return null;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    @Override
    public String getName() {
        return IDEWorkbenchMessages.WelcomeEditor_title;
    }

    @Override
    public IPersistableElement getPersistable() {
        return new IPersistableElement() {

            @Override
            public String getFactoryId() {
                return FACTORY_ID;
            }

            @Override
            public void saveState(IMemento memento) {
                memento.putString(FEATURE_ID, aboutInfo.getFeatureId() + ':' + aboutInfo.getVersionId());
            }
        };
    }

    public AboutInfo getAboutInfo() {
        return aboutInfo;
    }

    @Override
    public boolean equals(Object o) {
        if ((o != null) && (o instanceof WelcomeEditorInput)) {
            if (((WelcomeEditorInput) o).aboutInfo.getFeatureId().equals(aboutInfo.getFeatureId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getToolTipText() {
        return NLS.bind(IDEWorkbenchMessages.WelcomeEditor_toolTip, aboutInfo.getFeatureLabel());
    }
}
