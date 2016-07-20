/***/
package org.eclipse.e4.ui.model.application.ui.menu;

/**
* <!-- begin-user-doc -->
* A representation of the model object '<em><b>Tool Item</b></em>'.
* <!-- end-user-doc -->
*
* <!-- begin-model-doc -->
* <p>
* This is the base type for both Direct and Handled tool items.
* </p>
* @since 1.0
* @noimplement This interface is not intended to be implemented by clients.
* <!-- end-model-doc -->
*
* <p>
* The following features are supported:
* </p>
* <ul>
*   <li>{@link org.eclipse.e4.ui.model.application.ui.menu.MToolItem#getMenu <em>Menu</em>}</li>
* </ul>
*
* @model abstract="true"
* @generated
*/
public interface MToolItem extends MItem, MToolBarElement {

    /**
* Returns the value of the '<em><b>Menu</b></em>' containment reference.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* <!-- begin-model-doc -->
* <p>
* When set this represents the menu that appears when the 'drop down' arrow is
* clicked on this tool item.
* </p>
* <!-- end-model-doc -->
* @return the value of the '<em>Menu</em>' containment reference.
* @see #setMenu(MMenu)
* @model containment="true"
* @generated
*/
    MMenu getMenu();

    /**
* Sets the value of the '{@link org.eclipse.e4.ui.model.application.ui.menu.MToolItem#getMenu <em>Menu</em>}' containment reference.
* <!-- begin-user-doc -->
* <!-- end-user-doc -->
* @param value the new value of the '<em>Menu</em>' containment reference.
* @see #getMenu()
* @generated
*/
    void setMenu(MMenu value);
}
// MToolItem
