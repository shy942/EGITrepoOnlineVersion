/***/
package org.eclipse.jface.tests.databinding.scenarios;

/**
* @since 3.2
*
*/
public class CustomBeanModelType {

    private String propertyName;

    private Object object;

    private Class type;

    /**
* @param object
* @param propertyName
* @param type
*/
    public  CustomBeanModelType(Object object, String propertyName, Class type) {
        this.object = object;
        this.propertyName = propertyName;
        this.type = type;
    }

    /**
* @return
*/
    public String getPropertyName() {
        return propertyName;
    }

    /**
* @return
*/
    public Object getObject() {
        return object;
    }

    /**
* @return
*/
    public Class getType() {
        return type;
    }
}
