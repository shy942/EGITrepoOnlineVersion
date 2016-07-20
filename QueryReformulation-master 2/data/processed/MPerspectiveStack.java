/***/
package org.eclipse.e4.ui.model.application.ui.advanced;

import org.eclipse.e4.ui.model.application.ui.MGenericStack;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainerElement;
import org.eclipse.e4.ui.model.application.ui.basic.MWindowElement;

/**
* <!-- begin-user-doc -->
* A representation of the model object '<em><b>Perspective Stack</b></em>'.
* <!-- end-user-doc -->
*
* <!-- begin-model-doc -->
* <p>
* The PerspectiveStack is a collectin of Perspectives. Only one perspective may be
* visible at a time and is determined by the container's 'selectedElement'.
* </p>
* @since 1.0
* @noimplement This interface is not intended to be implemented by clients.
* <!-- end-model-doc -->
*
*
* @model
* @generated
*/
public interface MPerspectiveStack extends MUIElement, MGenericStack<MPerspective>, MPartSashContainerElement, MWindowElement {
}
// MPerspectiveStack
