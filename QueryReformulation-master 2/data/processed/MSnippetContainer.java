/***/
package org.eclipse.e4.ui.model.application.ui;

import java.util.List;

/**
* <!-- begin-user-doc -->
* A representation of the model object '<em><b>Snippet Container</b></em>'.
* <!-- end-user-doc -->
*
* <!-- begin-model-doc -->
* <p>
* This provides a collection of model fragments that can be subsequently cloned and
* inserterd into the model using the EModelService. For example saving a customized
* Perspective will create a clone and store it in this container.
* </p>
* @since 1.0
* @noimplement This interface is not intended to be implemented by clients.
* <!-- end-model-doc -->
*
* <p>
* The following features are supported:
* </p>
* <ul>
*   <li>{@link org.eclipse.e4.ui.model.application.ui.MSnippetContainer#getSnippets <em>Snippets</em>}</li>
* </ul>
*
* @model interface="true" abstract="true"
* @generated
*/
public interface MSnippetContainer {

    /**
* Returns the value of the '<em><b>Snippets</b></em>' containment reference list.
* The list contents are of type {@link org.eclipse.e4.ui.model.application.ui.MUIElement}.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* <!-- begin-model-doc -->
* <p>
* <strong>Developers</strong>:
* Add more detailed documentation by editing this comment in
* org.eclipse.ui.model.workbench/model/UIElements.ecore.
* There is a GenModel/documentation node under each type and attribute.
* </p>
* <!-- end-model-doc -->
* @return the value of the '<em>Snippets</em>' containment reference list.
* @model containment="true"
* @generated
*/
    List<MUIElement> getSnippets();
}
// MSnippetContainer