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

import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.model.NodePointer;

/**
* An iterator of attributes of a JavaBean. Returns bean properties as
* well as the "xml:lang" attribute.
*
*/
public class EObjectAttributeIterator extends EStructuralFeatureIterator {

    private NodePointer parent;

    private int position = 0;

    private boolean includeXmlLang;

    /**
* Create a new BeanAttributeIterator.
* @param parent parent pointer
* @param name name of this bean
*/
    public  EObjectAttributeIterator(EStructuralFeatureOwnerPointer parent, QName name) {
        super(parent, (name.getPrefix() == null && (name.getName() == null || name.getName().equals("*"))) ? null : name.toString(), false, null);
        this.parent = parent;
        includeXmlLang = (name.getPrefix() != null && name.getPrefix().equals("xml")) && (name.getName().equals("lang") || name.getName().equals("*"));
    }

    @Override
    public NodePointer getNodePointer() {
        return includeXmlLang && position == 1 ? new LangAttributePointer(parent) : super.getNodePointer();
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public boolean setPosition(int position) {
        this.position = position;
        if (includeXmlLang) {
            return position == 1 || super.setPosition(position - 1);
        }
        return super.setPosition(position);
    }
}
