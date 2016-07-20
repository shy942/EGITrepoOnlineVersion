/***/
package org.eclipse.core.tests.internal.databinding.beans;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* A bean in which all property change events are fired according to an annoying
* provision in the bean spec, where <code>(oldValue == null && newValue ==
* null)</code> indicates that an unknown change occured.
*
* @since 3.2
*/
public class AnnoyingBean extends Bean {

    @Override
    public void setValue(String value) {
        this.value = value;
        changeSupport.firePropertyChange("value", null, null);
    }

    @Override
    public void setArray(Object[] array) {
        this.array = array;
        changeSupport.firePropertyChange("array", null, null);
    }

    @Override
    public void setList(List list) {
        this.list = list;
        changeSupport.firePropertyChange("list", null, null);
    }

    @Override
    public void setSet(Set set) {
        this.set = set;
        changeSupport.firePropertyChange("set", null, null);
    }

    @Override
    public void setMap(Map map) {
        this.map = map;
        changeSupport.firePropertyChange("map", null, null);
    }
}
