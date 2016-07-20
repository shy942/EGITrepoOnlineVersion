/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.swt.widgets.Composite;

/**
* {@link ProblemsSeverityAndDescriptionConfigurationArea} is the
* configuration area for the problems view.
* @since 3.4
*
*/
public class ProblemsSeverityAndDescriptionConfigurationArea extends SeverityAndDescriptionConfigurationArea {

    @Override
    public void createContents(Composite parent) {
        super.createContents(parent);
        createSeverityGroup(parent);
    }
}
