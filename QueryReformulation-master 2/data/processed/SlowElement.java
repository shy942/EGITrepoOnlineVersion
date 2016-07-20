/***/
package org.eclipse.ui.examples.jobs.views;

import java.util.*;
import org.eclipse.core.runtime.PlatformObject;

public class SlowElement extends PlatformObject {

    private String name;

    private SlowElement parent;

     SlowElement(String name) {
        this(null, name, null);
    }

     SlowElement(String name, SlowElement[] children) {
        this(null, name, children);
    }

     SlowElement(SlowElement parent, String name, SlowElement[] children) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public SlowElement getParent() {
        return parent;
    }

    public SlowElement[] getChildren() {
        Random r = new Random();
        int random = r.nextInt(15);
        List children = new ArrayList();
        for (int i = 0; i < random; i++) {
            //$NON-NLS-1$
            children.add(new SlowElement("child" + i));
        }
        return (SlowElement[]) children.toArray(new SlowElement[children.size()]);
    }
}
