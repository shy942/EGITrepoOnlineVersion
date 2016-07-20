/***/
package org.eclipse.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Device;

/**
* A registry for common images used by the workbench which may be useful
* to other plug-ins.
* <p>
* This class provides <code>Image</code> and <code>ImageDescriptor</code>s
* for each named image in the interface.  All <code>Image</code> objects provided
* by this class are managed by this class and must never be disposed
* by other clients.
* </p>
* <p>
* This interface is not intended to be implemented by clients.
* </p>
* @noimplement This interface is not intended to be implemented by clients.
* @noextend This interface is not intended to be extended by clients.
*/
public interface ISharedImages {

    /**
* Identifies the error overlay image.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_DEC_FIELD_ERROR = "IMG_DEC_FIELD_ERROR";

    /**
* Identifies the warning overlay image.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_DEC_FIELD_WARNING = "IMG_DEC_FIELD_WARNING";

    /**
* Identifies the default image used for views.
*/
    //$NON-NLS-1$
    public static final String IMG_DEF_VIEW = "IMG_DEF_VIEW";

    /**
* Identifies the collapse all image in the enabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ELCL_COLLAPSEALL = "IMG_ELCL_COLLAPSEALL";

    /**
* Identifies the collapse all image in the disabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ELCL_COLLAPSEALL_DISABLED = "IMG_ELCL_COLLAPSEALL_DISABLED";

    /**
* Identifies the remove image in the enabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ELCL_REMOVE = "IMG_ELCL_REMOVE";

    /**
* Identifies the remove image in the disabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ELCL_REMOVE_DISABLED = "IMG_ELCL_REMOVE_DISABLED";

    /**
* Identifies the remove all image in the enabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ELCL_REMOVEALL = "IMG_ELCL_REMOVEALL";

    /**
* Identifies the remove all image in the disabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ELCL_REMOVEALL_DISABLED = "IMG_ELCL_REMOVEALL_DISABLED";

    /**
* Identifies the stop image in the enabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ELCL_STOP = "IMG_ELCL_STOP";

    /**
* Identifies the stop image in the disabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ELCL_STOP_DISABLED = "IMG_ELCL_STOP_DISABLED";

    /**
* Identifies the synchronize image in the enabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ELCL_SYNCED = "IMG_ELCL_SYNCED";

    /**
* Identifies the synchronize image in the disabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ELCL_SYNCED_DISABLED = "IMG_ELCL_SYNCED_DISABLED";

    /**
* Identifies the clear image in the enabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ETOOL_CLEAR = "IMG_ETOOL_CLEAR";

    /**
* Identifies the clear image in the disabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ETOOL_CLEAR_DISABLED = "IMG_ETOOL_CLEAR_DISABLED";

    /**
* Identifies the default perspective image.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ETOOL_DEF_PERSPECTIVE = "IMG_ETOOL_DEF_PERSPECTIVE";

    /**
* Identifies the delete image in the enabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ETOOL_DELETE = "IMG_ETOOL_DELETE";

    /**
* Identifies the delete image in the disabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ETOOL_DELETE_DISABLED = "IMG_ETOOL_DELETE_DISABLED";

    /**
* Identifies the home image in the enabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ETOOL_HOME_NAV = "IMG_ETOOL_HOME_NAV";

    /**
* Identifies the home image in the disabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ETOOL_HOME_NAV_DISABLED = "IMG_ETOOL_HOME_NAV_DISABLED";

    /**
* Identifies the print image in the enabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ETOOL_PRINT_EDIT = "IMG_ETOOL_PRINT_EDIT";

    /**
* Identifies the print image in the disabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ETOOL_PRINT_EDIT_DISABLED = "IMG_ETOOL_PRINT_EDIT_DISABLED";

    /**
* Identifies the save image in the enabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ETOOL_SAVE_EDIT = "IMG_ETOOL_SAVE_EDIT";

    /**
* Identifies the save image in the disabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ETOOL_SAVE_EDIT_DISABLED = "IMG_ETOOL_SAVE_EDIT_DISABLED";

    /**
* Identifies the save all image in the enabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ETOOL_SAVEALL_EDIT = "IMG_ETOOL_SAVEALL_EDIT";

    /**
* Identifies the save all image in the disabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ETOOL_SAVEALL_EDIT_DISABLED = "IMG_ETOOL_SAVEALL_EDIT_DISABLED";

    /**
* Identifies the save as image in the enabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ETOOL_SAVEAS_EDIT = "IMG_ETOOL_SAVEAS_EDIT";

    /**
* Identifies the save as image in the disabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_ETOOL_SAVEAS_EDIT_DISABLED = "IMG_ETOOL_SAVEAS_EDIT_DISABLED";

    /**
* Identifies the help image.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_LCL_LINKTO_HELP = "IMG_LCL_LINKTO_HELP";

    /**
* Identifies the add image.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_OBJ_ADD = "IMG_OBJ_ADD";

    /**
* Identifies an element image.
*/
    //$NON-NLS-1$
    public static final String IMG_OBJ_ELEMENT = "IMG_OBJ_ELEMENTS";

    /**
* Identifies a file image.
*/
    //$NON-NLS-1$
    public static final String IMG_OBJ_FILE = "IMG_OBJ_FILE";

    /**
* Identifies a folder image.
*/
    //$NON-NLS-1$
    public static final String IMG_OBJ_FOLDER = "IMG_OBJ_FOLDER";

    /**
* Identifies a project image.
*
* @deprecated in 3.0. This image is IDE-specific, and is therefore found
* only in IDE configurations. IDE-specific tools should use
* <code>org.eclipse.ui.ide.IDE.SharedImages.IMG_OBJ_PROJECT</code> instead.
*/
    @Deprecated
    public static final String //$NON-NLS-1$
    IMG_OBJ_PROJECT = "IMG_OBJ_PROJECT";

    /**
* Identifies a closed project image.
*
* @deprecated in 3.0. This image is IDE-specific, and is therefore found
* only in IDE configurations. IDE-specific tools should use
* <code>org.eclipse.ui.ide.IDE.SharedImages.IMG_OBJ_PROJECT_CLOSED</code> instead.
*/
    @Deprecated
    public static final String //$NON-NLS-1$
    IMG_OBJ_PROJECT_CLOSED = "IMG_OBJ_PROJECT_CLOSED";

    /**
* Identifies the default image used to indicate a bookmark.
*
* @deprecated in 3.0. This image is IDE-specific, and is therefore found
* only in IDE configurations. IDE-specific tools should use
* <code>org.eclipse.ui.ide.IDE.SharedImages.IMG_OBJS_BKMRK_TSK</code> instead.
*/
    @Deprecated
    public static final String //$NON-NLS-1$
    IMG_OBJS_BKMRK_TSK = "IMG_OBJS_BKMRK_TSK";

    /**
* Identifies the default image used to indicate errors.
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_ERROR_TSK = "IMG_OBJS_ERROR_TSK";

    /**
* Identifies the default image used to indicate information only.
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_INFO_TSK = "IMG_OBJS_INFO_TSK";

    /**
* Identifies the default image used to indicate a task.
*
* @deprecated in 3.0. This image is IDE-specific, and is therefore found
* only in IDE configurations. IDE-specific tools should use
* <code>org.eclipse.ui.ide.IDE.SharedImages.IMG_OBJS_TASK_TSK</code> instead.
*/
    @Deprecated
    public static final String //$NON-NLS-1$
    IMG_OBJS_TASK_TSK = "IMG_OBJS_TASK_TSK";

    /**
* Identifies the default image used to indicate warnings.
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_WARN_TSK = "IMG_OBJS_WARN_TSK";

    /**
* Identifies the image used for "open marker".
*
* @deprecated in 3.0. This image is IDE-specific, and is therefore found
* only in IDE configurations. IDE-specific tools should use
* <code>org.eclipse.ui.ide.IDE.SharedImages.IMG_OPEN_MARKER</code> instead.
*/
    @Deprecated
    public static final String //$NON-NLS-1$
    IMG_OPEN_MARKER = "IMG_OPEN_MARKER";

    /**
* Identifies the back image in the enabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_BACK = "IMG_TOOL_BACK";

    /**
* Identifies the back image in the disabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_BACK_DISABLED = "IMG_TOOL_BACK_DISABLED";

    /**
* Identifies the back image in the hover (colored) state.
*
* @deprecated in 3.0. This image is now the same as <code>IMG_TOOL_BACK</code>.
*   Enabled images are now in color.  The workbench itself no longer uses the hover image variants.
*/
    @Deprecated
    public static final String //$NON-NLS-1$
    IMG_TOOL_BACK_HOVER = "IMG_TOOL_BACK_HOVER";

    /**
* Identifies the copy image in the enabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_COPY = "IMG_TOOL_COPY";

    /**
* Identifies the copy image in the disabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_COPY_DISABLED = "IMG_TOOL_COPY_DISABLED";

    /**
* Identifies the copy image in the hover (colored) state.
*
* @deprecated in 3.0. This image is now the same as <code>IMG_TOOL_COPY</code>.
*   Enabled images are now in color.  The workbench itself no longer uses the hover image variants.
*/
    @Deprecated
    public static final String //$NON-NLS-1$
    IMG_TOOL_COPY_HOVER = "IMG_TOOL_COPY_HOVER";

    /**
* Identifies the cut image in the enabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_CUT = "IMG_TOOL_CUT";

    /**
* Identifies the cut image in the disabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_CUT_DISABLED = "IMG_TOOL_CUT_DISABLED";

    /**
* Identifies the cut image in the hover (colored) state.
*
* @deprecated in 3.0. This image is now the same as <code>IMG_TOOL_CUT</code>.
*   Enabled images are now in color.  The workbench itself no longer uses the hover image variants.
*/
    @Deprecated
    public static final String //$NON-NLS-1$
    IMG_TOOL_CUT_HOVER = "IMG_TOOL_CUT_HOVER";

    /**
* Identifies the delete image in the enabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_DELETE = "IMG_TOOL_DELETE";

    /**
* Identifies the delete image in the disabled state.
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_DELETE_DISABLED = "IMG_TOOL_DELETE_DISABLED";

    /**
* Identifies the delete image in the hover (colored) state.
*
* @deprecated in 3.0. This image is now the same as <code>IMG_TOOL_DELETE</code>.
*   Enabled images are now in color.  The workbench itself no longer uses the hover image variants.
*/
    @Deprecated
    public static final String //$NON-NLS-1$
    IMG_TOOL_DELETE_HOVER = "IMG_TOOL_DELETE_HOVER";

    /**
* Identifies the forward image in the enabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_FORWARD = "IMG_TOOL_FORWARD";

    /**
* Identifies the forward image in the disabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_FORWARD_DISABLED = "IMG_TOOL_FORWARD_DISABLED";

    /**
* Identifies the forward image in the hover (colored) state.
*
* @deprecated in 3.0. This image is now the same as <code>IMG_TOOL_FORWARD</code>.
*   Enabled images are now in color.  The workbench itself no longer uses the hover image variants.
*/
    @Deprecated
    public static final String //$NON-NLS-1$
    IMG_TOOL_FORWARD_HOVER = "IMG_TOOL_FORWARD_HOVER";

    /**
* Identifies the new wizard image in the enabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_NEW_WIZARD = "IMG_TOOL_NEW_WIZARD";

    /**
* Identifies the new wizard image in the disabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_NEW_WIZARD_DISABLED = "IMG_TOOL_NEW_WIZARD_DISABLED";

    /**
* Identifies the new wizard image in the hover (colored) state.
*
* @deprecated in 3.0. This image is now the same as <code>IMG_TOOL_NEW_WIZARD</code>.
*   Enabled images are now in color.  The workbench itself no longer uses the hover image variants.
*/
    @Deprecated
    public static final String //$NON-NLS-1$
    IMG_TOOL_NEW_WIZARD_HOVER = "IMG_TOOL_NEW_WIZARD_HOVER";

    /**
* Identifies the paste image in the enabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_PASTE = "IMG_TOOL_PASTE";

    /**
* Identifies the paste image in the disabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_PASTE_DISABLED = "IMG_TOOL_PASTE_DISABLED";

    /**
* Identifies the paste image in the hover (colored) state.
*
* @deprecated in 3.0. This image is now the same as <code>IMG_TOOL_PASTE</code>.
*   Enabled images are now in color.  The workbench itself no longer uses the hover image variants.
*/
    @Deprecated
    public static final String //$NON-NLS-1$
    IMG_TOOL_PASTE_HOVER = "IMG_TOOL_PASTE_HOVER";

    /**
* Identifies the redo image in the enabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_REDO = "IMG_TOOL_REDO";

    /**
* Identifies the redo image in the disabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_REDO_DISABLED = "IMG_TOOL_REDO_DISABLED";

    /**
* Identifies the redo image in the hover (colored) state.
*
* @deprecated in 3.0. This image is now the same as <code>IMG_TOOL_REDO</code>.
*   Enabled images are now in color.  The workbench itself no longer uses the hover image variants.
*/
    @Deprecated
    public static final String //$NON-NLS-1$
    IMG_TOOL_REDO_HOVER = "IMG_TOOL_REDO_HOVER";

    /**
* Identifies the undo image in the enabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_UNDO = "IMG_TOOL_UNDO";

    /**
* Identifies the undo image in the disabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_UNDO_DISABLED = "IMG_TOOL_UNDO_DISABLED";

    /**
* Identifies the undo image in the hover (colored) state.
*
* @deprecated in 3.0. This image is now the same as <code>IMG_TOOL_UNDO</code>.
*   Enabled images are now in color.  The workbench itself no longer uses the hover image variants.
*/
    @Deprecated
    public static final String //$NON-NLS-1$
    IMG_TOOL_UNDO_HOVER = "IMG_TOOL_UNDO_HOVER";

    /**
* Identifies the up image in the enabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_UP = "IMG_TOOL_UP";

    /**
* Identifies the up image in the disabled state.
*/
    //$NON-NLS-1$
    public static final String IMG_TOOL_UP_DISABLED = "IMG_TOOL_UP_DISABLED";

    /**
* Identifies the up image in the hover (colored) state.
*
* @deprecated in 3.0. This image is now the same as <code>IMG_TOOL_UP</code>.
*   Enabled images are now in color.  The workbench itself no longer uses the hover image variants.
*/
    @Deprecated
    public static final String //$NON-NLS-1$
    IMG_TOOL_UP_HOVER = "IMG_TOOL_UP_HOVER";

    // The following set of constants represent the image pairs that are used
    // to construct cursors for drag and drop operations within the workbench
    // Each cursor is represented by two images; the 'source' and the 'mask'
    // These need to be combined using the following code snippet:
    //    source = getImageDescriptor(sourceId);
    //    mask = getImageDescriptor(maskId);
    //    cursor = new Cursor(display, source.getImageData(), mask.getImageData(), 16, 16);
    /**
* Cursor 'source' for the left arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_LEFT_SOURCE = "IMG_OBJS_DND_LEFT_SOURCE";

    /**
* Cursor 'mask' for the left arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_LEFT_MASK = "IMG_OBJS_DND_LEFT_MASK";

    /**
* Cursor 'source' for the right arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_RIGHT_SOURCE = "IMG_OBJS_DND_RIGHT_SOURCE";

    /**
* Cursor 'mask' for the right arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_RIGHT_MASK = "IMG_OBJS_DND_RIGHT_MASK";

    /**
* Cursor 'source' for the up arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_TOP_SOURCE = "IMG_OBJS_DND_TOP_SOURCE";

    /**
* Cursor 'mask' for the up arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_TOP_MASK = "IMG_OBJS_DND_TOP_MASK";

    /**
* Cursor 'source' for the down arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_BOTTOM_SOURCE = "IMG_OBJS_DND_BOTTOM_SOURCE";

    /**
* Cursor 'mask' for the down arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_BOTTOM_MASK = "IMG_OBJS_DND_BOTTOM_MASK";

    /**
* Cursor 'source' for the 'no drop' arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_INVALID_SOURCE = "IMG_OBJS_DND_INVALID_SOURCE";

    /**
* Cursor 'mask' for the 'no drop' arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_INVALID_MASK = "IMG_OBJS_DND_INVALID_MASK";

    /**
* Cursor 'source' for the 'in stack' arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_STACK_SOURCE = "IMG_OBJS_DND_STACK_SOURCE";

    /**
* Cursor 'mask' for the 'in stack' arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_STACK_MASK = "IMG_OBJS_DND_STACK_MASK";

    /**
* Cursor 'source' for the 'off-screen' (detached window) arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_OFFSCREEN_SOURCE = "IMG_OBJS_DND_OFFSCREEN_SOURCE";

    /**
* Cursor 'mask' for the 'off-screen' (detached window) arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_OFFSCREEN_MASK = "IMG_OBJS_DND_OFFSCREEN_MASK";

    /**
* Cursor 'source' for the 'fast-view' arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_TOFASTVIEW_SOURCE = "IMG_OBJS_DND_TOFASTVIEW_SOURCE";

    /**
* Cursor 'mask' for the 'fast-view' arrow cursor. For cursor construction see:
* @see Cursor#Cursor(Device, ImageData, ImageData, int, int)
* @since 3.5
*/
    //$NON-NLS-1$
    public static final String IMG_OBJS_DND_TOFASTVIEW_MASK = "IMG_OBJS_DND_TOFASTVIEW_MASK";

    /**
* Retrieves the specified image from the workbench plugin's image registry.
* Note: The returned <code>Image</code> is managed by the workbench; clients
* must <b>not</b> dispose of the returned image.
*
* @param symbolicName the symbolic name of the image; there are constants
* declared in this interface for build-in images that come with the workbench
* @return the image, or <code>null</code> if not found
*/
    public Image getImage(String symbolicName);

    /**
* Retrieves the image descriptor for specified image from the workbench's
* image registry. Unlike <code>Image</code>s, image descriptors themselves do
* not need to be disposed.
*
* @param symbolicName the symbolic name of the image; there are constants
* declared in this interface for build-in images that come with the workbench
* @return the image descriptor, or <code>null</code> if not found
*/
    public ImageDescriptor getImageDescriptor(String symbolicName);
}
