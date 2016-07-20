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
import org.apache.commons.jxpath.ri.model.NodeIterator;
import org.apache.commons.jxpath.ri.model.NodePointer;

/**
* Combines attribute node iterators of all elements of a collection into one
* aggregate attribute node iterator.
*
*/
public class CollectionAttributeNodeIterator extends CollectionNodeIterator {

    private QName name;

    /**
* Create a new CollectionAttributeNodeIterator.
* @param pointer collection pointer
* @param name attribute name
*/
    public  CollectionAttributeNodeIterator(CollectionPointer pointer, QName name) {
        super(pointer, false, null);
        this.name = name;
    }

    @Override
    protected NodeIterator getElementNodeIterator(NodePointer elementPointer) {
        return elementPointer.attributeIterator(name);
    }
}
