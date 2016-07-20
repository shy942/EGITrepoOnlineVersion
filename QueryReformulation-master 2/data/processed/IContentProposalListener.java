/***/
package org.eclipse.jface.fieldassist;

/**
* This interface is used to listen to notifications from a
* {@link ContentProposalAdapter}.
*
* @since 3.2
*/
public interface IContentProposalListener {

    /**
* A content proposal has been accepted.
*
* @param proposal
*            the accepted content proposal
*/
    public void proposalAccepted(IContentProposal proposal);
}
