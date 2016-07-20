/***/
package org.eclipse.ui.views.markers.internal;

/**
* AbstractField is the abstract superclass for fields.
* @since 3.2
*
*/
public abstract class AbstractField implements IField {

    boolean visible = true;

    @Override
    public boolean isShowing() {
        return visible;
    }

    /**
* Set whether or not the receiver is showing.
* @param showing
*/
    @Override
    public void setShowing(boolean showing) {
        visible = showing;
    }
}
