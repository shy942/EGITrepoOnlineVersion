/***/
package org.eclipse.core.databinding.property;

import org.eclipse.core.databinding.property.list.SimpleListProperty;
import org.eclipse.core.databinding.property.map.SimpleMapProperty;
import org.eclipse.core.databinding.property.set.SimpleSetProperty;
import org.eclipse.core.databinding.property.value.SimpleValueProperty;

/**
* A listener capable of adding or removing itself as a listener on a source
* object using the source's "native" listener API. Events received from the
* source objects are parlayed to the {@link ISimplePropertyListener} provided
* to the method that constructed this native listener instance.
*
* @param <S>
*            type of the source object
* @since 1.2
* @see NativePropertyListener
* @see SimpleValueProperty#adaptListener(ISimplePropertyListener)
* @see SimpleListProperty#adaptListener(ISimplePropertyListener)
* @see SimpleSetProperty#adaptListener(ISimplePropertyListener)
* @see SimpleMapProperty#adaptListener(ISimplePropertyListener)
*/
public interface INativePropertyListener<S> {

    /**
* Adds the receiver as a listener for property events on the specified
* property source.
*
* @param source
*            the property source (may be null)
* @noreference This method is not intended to be referenced by clients.
*/
    public void addTo(S source);

    /**
* Removes the receiver as a listener for property events on the specified
* property source.
*
* @param source
*            the property source (may be null)
* @noreference This method is not intended to be referenced by clients.
*/
    public void removeFrom(S source);
}
