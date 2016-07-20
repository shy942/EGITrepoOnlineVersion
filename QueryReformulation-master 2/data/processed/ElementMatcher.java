/***/
package org.eclipse.e4.ui.workbench.modeling;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.e4.ui.model.application.MApplicationElement;
import org.eclipse.e4.ui.workbench.Selector;

/**
* This is an implementation of a Selector that implements the existing 'findElements'. Clients may
* subclass this and override the 'select' method in order to define custom filters.
*
* @since 1.1
*
*/
public class ElementMatcher implements Selector {

    private String id = null;

    private Class<?> clazz = null;

    private List<String> tagsToMatch = null;

    /**
* @param id
*            The elementId of the desired element
* @param clazz
*            The class specification of the desired element
* @param tag
*            A tag which must be specified on the desired element
*
*/
    public  ElementMatcher(String id, Class<?> clazz, String tag) {
        this.id = id;
        this.clazz = clazz;
        if (tag != null) {
            tagsToMatch = new ArrayList();
            tagsToMatch.add(tag);
        }
    }

    /**
* @param id
*            The elementId of the desired element
* @param clazz
*            The class specification of the desired element
* @param tagsToMatch
*            A list of tags which must <b>all</b> be specified on the desired element
*
*/
    public  ElementMatcher(String id, Class<?> clazz, List<String> tagsToMatch) {
        this.id = id;
        this.clazz = clazz;
        this.tagsToMatch = tagsToMatch;
    }

    @Override
    public boolean select(MApplicationElement element) {
        if (id != null && !id.equals(element.getElementId())) {
            return false;
        }
        if (clazz != null && !(clazz.isInstance(element))) {
            return false;
        }
        if (tagsToMatch != null) {
            List<String> elementTags = element.getTags();
            for (String tag : tagsToMatch) {
                if (!elementTags.contains(tag)) {
                    return false;
                }
            }
        }
        return true;
    }
}
