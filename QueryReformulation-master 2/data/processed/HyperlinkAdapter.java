/***/
package org.eclipse.ui.forms.events;

/**
* This adapter class provides default implementations for the methods
* described by the <code>HyperlinkListener</code> interface.
* <p>
* Classes that wish to deal with <code>HyperlinkEvent</code> s can extend
* this class and override only the methods which they are interested in.
* </p>
*
* @see IHyperlinkListener
* @see HyperlinkEvent
* @since 3.0
*/
public class HyperlinkAdapter implements IHyperlinkListener {

    /**
* Sent when the link is entered. The default behaviour is to do nothing.
*
* @param e
*            the event
*/
    @Override
    public void linkEntered(HyperlinkEvent e) {
    }

    /**
* Sent when the link is exited. The default behaviour is to do nothing.
*
* @param e
*            the event
*/
    @Override
    public void linkExited(HyperlinkEvent e) {
    }

    /**
* Sent when the link is activated. The default behaviour is to do nothing.
*
* @param e
*            the event
*/
    @Override
    public void linkActivated(HyperlinkEvent e) {
    }
}
