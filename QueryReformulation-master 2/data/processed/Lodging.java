/***/
package org.eclipse.jface.examples.databinding.model;

import org.eclipse.jface.examples.databinding.ModelObject;

public class Lodging extends ModelObject {

    private String name;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String string) {
        Object oldValue = description;
        description = string;
        firePropertyChange("description", oldValue, description);
    }

    public void setName(String string) {
        Object oldValue = name;
        name = string;
        firePropertyChange("name", oldValue, name);
    }

    public String getName() {
        return name;
    }
}
