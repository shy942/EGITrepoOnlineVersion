/***/
package org.eclipse.ui.internal.navigator;

import org.eclipse.jface.viewers.ViewerLabel;

/**
* Provides a subclass of ViewerLabel that can be re-used for multiple viewer updates.
*
* @since 3.2
*
*/
public class ReusableViewerLabel extends ViewerLabel {

    private ViewerLabel original = null;

    /**
* Creates a ViewerLabel with null text and image.
*
*/
    public  ReusableViewerLabel() {
        super(null, null);
    }

    /**
* Updates the Background, Foreground, and Font to the given ViewerLabel. The
* Text and Image are reset to <b>null</b>.
*
* @param theOriginal The ViewerLabel to represent.
*/
    public void reset(ViewerLabel theOriginal) {
        original = theOriginal;
        setBackground(original.getBackground());
        setFont(original.getFont());
        setForeground(original.getForeground());
        setImage(null);
        setText(null);
    }

    /**
*
* @param theOriginal The ViewerLabel to fill with my values.
*/
    public void fill(ViewerLabel theOriginal) {
        theOriginal.setBackground(getBackground());
        theOriginal.setFont(getFont());
        theOriginal.setForeground(getForeground());
        theOriginal.setImage(getImage());
        //$NON-NLS-1$
        theOriginal.setText(getText() != null ? getText() : "");
    }

    /**
*
* @return True if the ReusableViewerLabel has different text or image than the original
*         ViewerLabel.
*/
    public boolean hasChanged() {
        boolean changed = false;
        if (original != null) {
            if (original.getText() == null ^ getText() != null)
                changed |= getText() != null;
            if (original.getText() != null && getImage() != null)
                changed |= !getImage().equals(original.getImage());
        }
        return changed;
    }

    /**
* @return True if the text is non-null and non-zero in length.
*/
    public boolean isValid() {
        return getText() != null && getText().length() > 0 && getImage() != null;
    }
}
