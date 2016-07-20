/***/
package org.eclipse.ui.model;

/**
* Instances of this service are capable of providing standard mechanisms that
* clients may use to order, display, and generally work with contributions to
* the Workbench.
*
* @since 3.4
*
*/
public interface IContributionService {

    /**
* contributionType value for the PropertyDialog
*/
    //$NON-NLS-1$
    public static final String TYPE_PROPERTY = "property";

    /**
* contributionType value for Preferences
*/
    //$NON-NLS-1$
    public static final String TYPE_PREFERENCE = "preference";

    /**
* Return a comparator for ordering contributions within the user interface.
*
* @param contributionType
*            the type of contribution, must not be <code>null</code>.
* @return the comparator
* @see #TYPE_PREFERENCE
* @see #TYPE_PROPERTY
*/
    public ContributionComparator getComparatorFor(String contributionType);
}
