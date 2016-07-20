/***/
package org.eclipse.e4.ui.model.application.descriptor.basic;

import java.util.List;

/**
* <!-- begin-user-doc -->
* A representation of the model object '<em><b>Part Descriptor Container</b></em>'.
* <!-- end-user-doc -->
*
* <!-- begin-model-doc -->
* <p>
* A type specific collection of PartDescriptors.
* </p>
* @since 1.0
* @noimplement This interface is not intended to be implemented by clients.
* <!-- end-model-doc -->
*
* <p>
* The following features are supported:
* </p>
* <ul>
*   <li>{@link org.eclipse.e4.ui.model.application.descriptor.basic.MPartDescriptorContainer#getDescriptors <em>Descriptors</em>}</li>
* </ul>
*
* @model interface="true" abstract="true"
* @generated
*/
public interface MPartDescriptorContainer {

    /**
* Returns the value of the '<em><b>Descriptors</b></em>' containment reference list.
* The list contents are of type {@link org.eclipse.e4.ui.model.application.descriptor.basic.MPartDescriptor}.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* <!-- begin-model-doc -->
* <p>
* A collection of PartDescriptors.
* </p>
* <!-- end-model-doc -->
* @return the value of the '<em>Descriptors</em>' containment reference list.
* @model containment="true"
* @generated
*/
    List<MPartDescriptor> getDescriptors();
}
// MPartDescriptorContainer
