/***/
package org.eclipse.ui.tests.keys;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PluginVersionIdentifier;
import org.eclipse.core.runtime.Preferences;

/**
* A utility class for setting preferences related to key bindings. This class
* currently uses the round-about procedure of manually writing a preferences
* file, and then loading it back into the application. In the future, it might
* use a direct API.
*
* @since 3.0
*/
public abstract class PreferenceMutator {

    /**
* Sets a key binding in the currently running Eclipse application. It
* accomplishes this by writing out an exported preferences file by hand,
* and then importing it back into the application.
*
* @param commandId
*           The command identifier to which the key binding should be
*           associated; should not be <code>null</code>.
* @param keySequenceText
*           The text of the key sequence for this key binding; must not be
*           <code>null</code>.
* @throws CoreException
*            If the exported preferences file is invalid for some reason.
* @throws FileNotFoundException
*            If the temporary file is removed before it can be read in.
*            (Wow)
* @throws IOException
*            If the creation of or the writing to the temporary file fails
*            for some reason.
*/
    static final void setKeyBinding(String commandId, String keySequenceText) throws CoreException, FileNotFoundException, IOException {
        // Set up the preferences.
        Properties preferences = new Properties();
        //$NON-NLS-1$
        String key = "org.eclipse.ui.workbench/org.eclipse.ui.commands";
        //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
        String value = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<org.eclipse.ui.commands><activeKeyConfiguration/><keyBinding commandId=\"" + commandId + "\" keySequence=\"" + keySequenceText + "\"/></org.eclipse.ui.commands>";
        preferences.put(key, value);
        String[] pluginIds = Platform.getExtensionRegistry().getNamespaces();
        for (String pluginId : pluginIds) {
            preferences.put(pluginId, new PluginVersionIdentifier(Platform.getBundle(pluginId).getHeaders().get(org.osgi.framework.Constants.BUNDLE_VERSION)));
        }
        // Export the preferences.
        //$NON-NLS-1$//$NON-NLS-2$
        File file = File.createTempFile("preferences", ".txt");
        file.deleteOnExit();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        preferences.store(bos, null);
        bos.close();
        // Attempt to import the key binding.
        Preferences.importPreferences(new Path(file.getAbsolutePath()));
    }
}
