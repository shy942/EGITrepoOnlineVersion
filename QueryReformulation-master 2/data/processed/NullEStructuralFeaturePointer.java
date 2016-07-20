/**/
/*******************************************************************************
* Copyright (c) 2010 BestSolution.at and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     Tom Schindl <tom.schindl@bestsolution.at> - adjustment to EObject
******************************************************************************/
package org.eclipse.e4.emf.internal.xpath;

import org.apache.commons.jxpath.AbstractFactory;
import org.apache.commons.jxpath.JXPathAbstractFactoryException;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathInvalidAccessException;
import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.model.NodePointer;

/**
*
*/
public class NullEStructuralFeaturePointer extends EStructuralFeaturePointer {

    private String propertyName = "*";

    private boolean byNameAttribute = false;

    private static final long serialVersionUID = 5296593071854982754L;

    /**
* Create a new NullPropertyPointer.
* @param parent pointer
*/
    public  NullEStructuralFeaturePointer(NodePointer parent) {
        super(parent);
    }

    @Override
    public QName getName() {
        return new QName(propertyName);
    }

    @Override
    public void setPropertyIndex(int index) {
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public Object getBaseValue() {
        return null;
    }

    @Override
    public Object getImmediateNode() {
        return null;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public NodePointer getValuePointer() {
        return new NullPointer(this, new QName(getPropertyName()));
    }

    @Override
    protected boolean isActualProperty() {
        return false;
    }

    @Override
    public boolean isActual() {
        return false;
    }

    @Override
    public boolean isContainer() {
        return true;
    }

    @Override
    public void setValue(Object value) {
        if (parent == null || parent.isContainer()) {
            throw new JXPathInvalidAccessException("Cannot set property " + asPath() + ", the target object is null");
        }
        if (parent instanceof EStructuralFeatureOwnerPointer && ((EStructuralFeatureOwnerPointer) parent).isDynamicPropertyDeclarationSupported()) {
            // If the parent property owner can create
            // a property automatically - let it do so
            EStructuralFeaturePointer propertyPointer = ((EStructuralFeatureOwnerPointer) parent).getPropertyPointer();
            propertyPointer.setPropertyName(propertyName);
            propertyPointer.setValue(value);
        } else {
            throw new JXPathInvalidAccessException("Cannot set property " + asPath() + ", path does not match a changeable location");
        }
    }

    @Override
    public NodePointer createPath(JXPathContext context) {
        NodePointer newParent = parent.createPath(context);
        if (isAttribute()) {
            return newParent.createAttribute(context, getName());
        }
        if (parent instanceof NullPointer && parent.equals(newParent)) {
            throw createBadFactoryException(context.getFactory());
        }
        //    non-property pointer expand the collection by itself.
        if (newParent instanceof EStructuralFeatureOwnerPointer) {
            EStructuralFeatureOwnerPointer pop = (EStructuralFeatureOwnerPointer) newParent;
            newParent = pop.getPropertyPointer();
        }
        return newParent.createChild(context, getName(), getIndex());
    }

    @Override
    public NodePointer createPath(JXPathContext context, Object value) {
        NodePointer newParent = parent.createPath(context);
        if (isAttribute()) {
            NodePointer pointer = newParent.createAttribute(context, getName());
            pointer.setValue(value);
            return pointer;
        }
        if (parent instanceof NullPointer && parent.equals(newParent)) {
            throw createBadFactoryException(context.getFactory());
        }
        if (newParent instanceof EStructuralFeatureOwnerPointer) {
            EStructuralFeatureOwnerPointer pop = (EStructuralFeatureOwnerPointer) newParent;
            newParent = pop.getPropertyPointer();
        }
        return newParent.createChild(context, getName(), index, value);
    }

    @Override
    public NodePointer createChild(JXPathContext context, QName name, int index) {
        return createPath(context).createChild(context, name, index);
    }

    @Override
    public NodePointer createChild(JXPathContext context, QName name, int index, Object value) {
        return createPath(context).createChild(context, name, index, value);
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
* Set the name attribute.
* @param attributeValue value to set
*/
    public void setNameAttributeValue(String attributeValue) {
        this.propertyName = attributeValue;
        byNameAttribute = true;
    }

    @Override
    public boolean isCollection() {
        return getIndex() != WHOLE_COLLECTION;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public String[] getPropertyNames() {
        return new String[0];
    }

    @Override
    public String asPath() {
        if (!byNameAttribute) {
            return super.asPath();
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(getImmediateParentPointer().asPath());
        buffer.append("[@name='");
        buffer.append(escape(getPropertyName()));
        buffer.append("']");
        if (index != WHOLE_COLLECTION) {
            buffer.append('[').append(index + 1).append(']');
        }
        return buffer.toString();
    }

    /**
* Create a "bad factory" JXPathAbstractFactoryException for the specified AbstractFactory.
* @param factory AbstractFactory
* @return JXPathAbstractFactoryException
*/
    private JXPathAbstractFactoryException createBadFactoryException(AbstractFactory factory) {
        return new JXPathAbstractFactoryException("Factory " + factory + " reported success creating object for path: " + asPath() + " but object was null.  Terminating to avoid stack recursion.");
    }
}
