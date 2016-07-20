/***/
package org.eclipse.ui.progress;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressConstants;

/**
* Constants relating to progress UI functionality of the workbench plug-in.
* <p>
* The constants define property keys that are used to associate UI related
* information with Jobs (<code>org.eclipse.core.runtime.jobs.Job</code>). This
* class is a superset of all previously defined progress constants.
*
* @see org.eclipse.core.runtime.jobs.Job#setProperty
* @see IProgressConstants
* @since 3.6
*
* @noimplement This interface is not intended to be implemented by clients.
*/
public interface IProgressConstants2 extends IProgressConstants {

    /**
* Common prefix for properties defined in this interface.
*/
    static final String PROPERTY_PREFIX = PlatformUI.PLUGIN_ID + //$NON-NLS-1$
    ".workbench.progress";

    /**
* This property is used to associate a <code>ParameterizedCommand</code>
* with a Job. If the Job is shown in the UI, the command might be
* represented as a button or hyper link to allow the user to trigger a job
* specific action, like showing the Job's results.
* <p>
* Note: Only one of <code>ACTION_PROPERTY</code> or
* <code>COMMAND_PROPERTY</code> should be used
* </p>
*
* @see org.eclipse.core.commands.ParameterizedCommand
**/
    public static final QualifiedName COMMAND_PROPERTY = new QualifiedName(PROPERTY_PREFIX, //$NON-NLS-1$
    "command");

    /**
* This property provides a hint to the progress UI to show the progress of
* the job in the application TaskBar
* <p>
* The property must be of type <code>Boolean</code> and the hint is used if
* its value is <code>true</code>.
* </p>
*/
    public static final QualifiedName SHOW_IN_TASKBAR_ICON_PROPERTY = new QualifiedName(PROPERTY_PREFIX, //$NON-NLS-1$
    "inTaskBarIcon");
}
