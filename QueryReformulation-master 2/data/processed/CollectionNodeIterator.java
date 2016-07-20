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

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.jxpath.JXPathException;
import org.apache.commons.jxpath.ri.model.NodeIterator;
import org.apache.commons.jxpath.ri.model.NodePointer;

/**
* Combines node iterators of all elements of a collection into one
* aggregate node iterator.
*
*/
public abstract class CollectionNodeIterator implements NodeIterator {

    private CollectionPointer pointer;

    private boolean reverse;

    private NodePointer startWith;

    private int position;

    private List<Object> collection;

    /**
* Create a new CollectionNodeIterator.
* @param pointer collection pointer
* @param reverse iteration order
* @param startWith starting pointer
*/
    protected  CollectionNodeIterator(CollectionPointer pointer, boolean reverse, NodePointer startWith) {
        this.pointer = pointer;
        this.reverse = reverse;
        this.startWith = startWith;
    }

    /**
* Implemented by subclasses to produce child/attribute node iterators.
* @param elementPointer owning pointer
* @return NodeIterator
*/
    protected abstract NodeIterator getElementNodeIterator(NodePointer elementPointer);

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public boolean setPosition(int position) {
        if (collection == null) {
            prepare();
        }
        if (position < 1 || position > collection.size()) {
            return false;
        }
        this.position = position;
        return true;
    }

    @Override
    public NodePointer getNodePointer() {
        if (position == 0) {
            return null;
        }
        return (NodePointer) collection.get(position - 1);
    }

    /**
* Prepare...
*/
    private void prepare() {
        collection = new ArrayList();
        NodePointer ptr = (NodePointer) pointer.clone();
        int length = ptr.getLength();
        for (int i = 0; i < length; i++) {
            ptr.setIndex(i);
            NodePointer elementPointer = ptr.getValuePointer();
            NodeIterator iter = getElementNodeIterator(elementPointer);
            for (int j = 1; iter.setPosition(j); j++) {
                NodePointer childPointer = iter.getNodePointer();
                if (reverse) {
                    collection.add(0, childPointer);
                } else {
                    collection.add(childPointer);
                }
            }
        }
        if (startWith != null) {
            int index = collection.indexOf(startWith);
            if (index == -1) {
                throw new JXPathException("Invalid starting pointer for iterator: " + startWith);
            }
            while (collection.size() > index) {
                if (!reverse) {
                    collection.remove(collection.size() - 1);
                } else {
                    collection.remove(0);
                }
            }
        }
    }
}
