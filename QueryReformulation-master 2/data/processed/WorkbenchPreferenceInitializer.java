/***/
package org.eclipse.ui.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.util.OpenStrategy;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.osgi.service.prefs.BackingStoreException;

/**
* Implementation of the workbench plugin's preferences extension's
* customization element. This is needed in order to force the workbench
* plugin's preferences to be initialized properly when running without
* org.eclipse.core.runtime.compatibility. For more details, see bug 58975 - New
* preference mechanism does not properly initialize defaults.
*
* @since 3.0
*/
public class WorkbenchPreferenceInitializer extends AbstractPreferenceInitializer {

    @Override
    public void initializeDefaultPreferences() {
        IScopeContext context = DefaultScope.INSTANCE;
        IEclipsePreferences node = context.getNode(WorkbenchPlugin.getDefault().getBundle().getSymbolicName());
        node.putBoolean(IPreferenceConstants.SHOULD_PROMPT_FOR_ENABLEMENT, true);
        node.putBoolean(IPreferenceConstants.EDITORLIST_PULLDOWN_ACTIVE, false);
        node.putBoolean(IPreferenceConstants.EDITORLIST_DISPLAY_FULL_NAME, false);
        node.putBoolean(IPreferenceConstants.STICKY_CYCLE, false);
        node.putBoolean(IPreferenceConstants.REUSE_EDITORS_BOOLEAN, false);
        node.putBoolean(IPreferenceConstants.REUSE_DIRTY_EDITORS, true);
        node.putInt(IPreferenceConstants.REUSE_EDITORS, 8);
        node.putBoolean(IPreferenceConstants.OPEN_ON_SINGLE_CLICK, false);
        node.putBoolean(IPreferenceConstants.SELECT_ON_HOVER, false);
        node.putBoolean(IPreferenceConstants.OPEN_AFTER_DELAY, false);
        node.putInt(IPreferenceConstants.RECENT_FILES, 4);
        // FIXME this does not actually set the default since it is the wrong
        // node. It works because the default-default is false.
        node.putBoolean(IWorkbenchPreferenceConstants.DISABLE_OPEN_EDITOR_IN_PLACE, false);
        // 5 minute workbench save interval
        node.putInt(IPreferenceConstants.WORKBENCH_SAVE_INTERVAL, 5);
        node.putBoolean(IPreferenceConstants.USE_IPERSISTABLE_EDITORS, true);
        node.putBoolean(IPreferenceConstants.COOLBAR_VISIBLE, true);
        node.putBoolean(IPreferenceConstants.PERSPECTIVEBAR_VISIBLE, true);
        // high
        node.putInt(IPreferenceConstants.EDITOR_TAB_WIDTH, 3);
        node.putInt(IPreferenceConstants.OPEN_PERSP_MODE, IPreferenceConstants.OPM_ACTIVE_PAGE);
        //$NON-NLS-1$
        node.put(IPreferenceConstants.ENABLED_DECORATORS, "");
        node.putInt(IPreferenceConstants.EDITORLIST_SELECTION_SCOPE, // Current
        IPreferenceConstants.EDITORLIST_SET_PAGE_SCOPE);
        // Window
        node.putInt(IPreferenceConstants.EDITORLIST_SORT_CRITERIA, // Name Sort
        IPreferenceConstants.EDITORLIST_NAME_SORT);
        node.putBoolean(IPreferenceConstants.COLOR_ICONS, true);
        node.putInt(IPreferenceConstants.KEYS_PREFERENCE_SELECTED_TAB, 0);
        node.putBoolean(IPreferenceConstants.MULTI_KEY_ASSIST, true);
        node.putInt(IPreferenceConstants.MULTI_KEY_ASSIST_TIME, 1000);
        // Temporary option to enable wizard for project capability
        //$NON-NLS-1$
        node.putBoolean("ENABLE_CONFIGURABLE_PROJECT_WIZARD", false);
        // Temporary option to enable single click
        //$NON-NLS-1$
        node.putInt("SINGLE_CLICK_METHOD", OpenStrategy.DOUBLE_CLICK);
        // Temporary option to enable cool bars
        //$NON-NLS-1$
        node.putBoolean("ENABLE_COOL_BARS", true);
        // Temporary option to enable new menu organization
        //$NON-NLS-1$
        node.putBoolean("ENABLE_NEW_MENUS", true);
        //Temporary option to turn off the dialog font
        //$NON-NLS-1$
        node.putBoolean("DISABLE_DIALOG_FONT", false);
        // Heap status preferences
        // FIXME this does not actually set the default since it is the wrong
        // node. It works because the default-default is false.
        node.putBoolean(IWorkbenchPreferenceConstants.SHOW_MEMORY_MONITOR, false);
        node.putInt(IHeapStatusConstants.PREF_UPDATE_INTERVAL, 500);
        node.putBoolean(IHeapStatusConstants.PREF_SHOW_MAX, false);
        node.putBoolean(IPreferenceConstants.OVERRIDE_PRESENTATION, false);
        // Globalization preferences
        //$NON-NLS-1$
        node.put(IPreferenceConstants.NL_EXTENSIONS, "");
        node.putInt(IPreferenceConstants.LAYOUT_DIRECTION, SWT.NONE);
        node.putBoolean(IPreferenceConstants.BIDI_SUPPORT, false);
        //$NON-NLS-1$
        node.put(IPreferenceConstants.TEXT_DIRECTION, "");
        // Auto-save
        node.putBoolean(IPreferenceConstants.SAVE_AUTOMATICALLY, false);
        node.putInt(IPreferenceConstants.SAVE_AUTOMATICALLY_INTERVAL, 20);
        IEclipsePreferences rootNode = (IEclipsePreferences) Platform.getPreferencesService().getRootNode().node(InstanceScope.SCOPE);
        final String workbenchName = WorkbenchPlugin.getDefault().getBundle().getSymbolicName();
        try {
            if (rootNode.nodeExists(workbenchName)) {
                ((IEclipsePreferences) rootNode.node(workbenchName)).addPreferenceChangeListener(PlatformUIPreferenceListener.getSingleton());
            }
        } catch (BackingStoreException e) {
            IStatus status = new Status(IStatus.ERROR, WorkbenchPlugin.getDefault().getBundle().getSymbolicName(), IStatus.ERROR, e.getLocalizedMessage(), e);
            WorkbenchPlugin.getDefault().getLog().log(status);
        }
    }
}
