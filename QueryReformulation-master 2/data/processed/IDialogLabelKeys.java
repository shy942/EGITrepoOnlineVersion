/***/
package org.eclipse.jface.dialogs;

/**
* IDialogLabelKeys contains publicly accessible keys to the common dialog
* labels used throughout JFace.  <code>IDialogConstants</code> provides
* access to these labels using static constants.  This is the preferred
* method when the client is optimizing for performance and is known to be
* used in a single-locale system.  Using the keys and accessing the
* common dialog labels dynamically is the preferred method when the client
* may be running in a multi-locale system.
*
* @see IDialogConstants
* @since 3.7
*
* @noimplement This interface is not intended to be implemented by clients.
*/
public interface IDialogLabelKeys {

    /**
* The key used to retrieve the label for OK buttons. Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.OK_LABEL_KEY)</code> to retrieve the label
* dynamically when using multiple locales.
*
* @since 3.7
*/
    //$NON-NLS-1$
    public String OK_LABEL_KEY = "ok";

    /**
* The key used to retrieve the label for cancel buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.CANCEL_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String CANCEL_LABEL_KEY = "cancel";

    /**
* The key used to retrieve the label for yes buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.YES_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String YES_LABEL_KEY = "yes";

    /**
* The key used to retrieve the label for no buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.NO_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String NO_LABEL_KEY = "no";

    /**
* The key used to retrieve the label for no to all buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.NO_TO_ALL_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String NO_TO_ALL_LABEL_KEY = "notoall";

    /**
* The key used to retrieve the label for yes to all buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.YES_TO_ALL_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String YES_TO_ALL_LABEL_KEY = "yestoall";

    /**
* The key used to retrieve the label for skip buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.SKIP_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String SKIP_LABEL_KEY = "skip";

    /**
* The key used to retrieve the label for stop buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.STOP_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String STOP_LABEL_KEY = "stop";

    /**
* The key used to retrieve the label for abort buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.ABORT_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String ABORT_LABEL_KEY = "abort";

    /**
* The key used to retrieve the label for retry buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.RETRY_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String RETRY_LABEL_KEY = "retry";

    /**
* The key used to retrieve the label for ignore buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.IGNORE_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String IGNORE_LABEL_KEY = "ignore";

    /**
* The key used to retrieve the label for proceed buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.PROCEED_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String PROCEED_LABEL_KEY = "proceed";

    /**
* The key used to retrieve the label for open buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.OPEN_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String OPEN_LABEL_KEY = "open";

    /**
* The key used to retrieve the label for close buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.CLOSE_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String CLOSE_LABEL_KEY = "close";

    /**
* The key used to retrieve the label for show details buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.SHOW_DETAILS_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String SHOW_DETAILS_LABEL_KEY = "showDetails";

    /**
* The key used to retrieve the label for hide details buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.HIDE_DETAILS_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String HIDE_DETAILS_LABEL_KEY = "hideDetails";

    /**
* The key used to retrieve the label for back buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.BACK_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String BACK_LABEL_KEY = "backButton";

    /**
* The key used to retrieve the label for next buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.NEXT_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String NEXT_LABEL_KEY = "nextButton";

    /**
* The key used to retrieve the label for finish buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.FINISH_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String FINISH_LABEL_KEY = "finish";

    /**
* The key used to retrieve the label for help buttons.
* Clients should use the pattern
* <code>JFaceResources.getString(IDialogLabelKeys.HELP_LABEL_KEY)</code>
* to retrieve the label dynamically when using multiple locales.
* @since 3.7
*/
    //$NON-NLS-1$
    public String HELP_LABEL_KEY = "help";
}
