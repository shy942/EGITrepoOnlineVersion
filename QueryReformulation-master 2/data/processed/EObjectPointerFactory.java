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

import java.util.Locale;
import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.model.NodePointer;
import org.apache.commons.jxpath.ri.model.NodePointerFactory;
import org.eclipse.e4.emf.internal.xpath.helper.JXPathEObjectInfo;
import org.eclipse.emf.ecore.EObject;

/**
* Implements NodePointerFactory for JavaBeans.
*
*/
public class EObjectPointerFactory implements NodePointerFactory {

    /** factory order constant */
    public static final int BEAN_POINTER_FACTORY_ORDER = 900;

    @Override
    public int getOrder() {
        return BEAN_POINTER_FACTORY_ORDER;
    }

    @Override
    public NodePointer createNodePointer(QName name, Object bean, Locale locale) {
        JXPathEObjectInfo bi = new JXPathEObjectInfo(((EObject) bean).eClass());
        return new EObjectPointer(name, bean, bi, locale);
    }

    @Override
    public NodePointer createNodePointer(NodePointer parent, QName name, Object bean) {
        if (bean == null) {
            return new NullPointer(parent, name);
        }
        JXPathEObjectInfo bi = new JXPathEObjectInfo(((EObject) bean).eClass());
        return new EObjectPointer(parent, name, bean, bi);
    }
}
