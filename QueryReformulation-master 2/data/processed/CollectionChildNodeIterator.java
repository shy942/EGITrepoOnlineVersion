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

import org.apache.commons.jxpath.ri.compiler.NodeTest;
import org.apache.commons.jxpath.ri.model.NodeIterator;
import org.apache.commons.jxpath.ri.model.NodePointer;

/**
* Combines child node iterators of all elements of a collection into one
* aggregate child node iterator.
*
*/
public class CollectionChildNodeIterator extends CollectionNodeIterator {

    private NodeTest test;

    /**
* Create a new CollectionChildNodeIterator.
* @param pointer CollectionPointer
* @param test child test
* @param reverse iteration order
* @param startWith starting pointer
*/
    public  CollectionChildNodeIterator(CollectionPointer pointer, NodeTest test, boolean reverse, NodePointer startWith) {
        super(pointer, reverse, startWith);
        this.test = test;
    }

    @Override
    protected NodeIterator getElementNodeIterator(NodePointer elementPointer) {
        return elementPointer.childIterator(test, false, null);
    }
}
