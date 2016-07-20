/***/
package org.eclipse.jface.databinding.viewers;

import java.util.Set;
import org.eclipse.core.databinding.observable.map.IMapChangeListener;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.map.MapChangeEvent;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.ViewerCell;

/**
* A label provider based on one or more observable maps that track attributes
* that this label provider uses for display. The default behavior is to display
* the first attribute's value. Clients may customize by subclassing and
* overriding {@link #update(ViewerCell)}.
*
* @since 1.3
*
*/
public class ObservableMapCellLabelProvider extends CellLabelProvider {

    /**
* Observable maps typically mapping from viewer elements to label values.
* Subclasses may use these maps to provide custom labels.
*
* @since 1.4
*/
    protected IObservableMap[] attributeMaps;

    private IMapChangeListener mapChangeListener = new IMapChangeListener() {

        @Override
        public void handleMapChange(MapChangeEvent event) {
            Set affectedElements = event.diff.getChangedKeys();
            LabelProviderChangedEvent newEvent = new LabelProviderChangedEvent(ObservableMapCellLabelProvider.this, affectedElements.toArray());
            fireLabelProviderChanged(newEvent);
        }
    };

    /**
* Creates a new label provider that tracks changes to one attribute.
*
* @param attributeMap
*/
    public  ObservableMapCellLabelProvider(IObservableMap attributeMap) {
        this(new IObservableMap[] { attributeMap });
    }

    /**
* Creates a new label provider that tracks changes to more than one
* attribute. This constructor should be used by subclasses that override
* {@link #update(ViewerCell)} and make use of more than one attribute.
*
* @param attributeMaps
*/
    protected  ObservableMapCellLabelProvider(IObservableMap[] attributeMaps) {
        System.arraycopy(attributeMaps, 0, this.attributeMaps = new IObservableMap[attributeMaps.length], 0, attributeMaps.length);
        for (int i = 0; i < attributeMaps.length; i++) {
            attributeMaps[i].addMapChangeListener(mapChangeListener);
        }
    }

    @Override
    public void dispose() {
        for (int i = 0; i < attributeMaps.length; i++) {
            attributeMaps[i].removeMapChangeListener(mapChangeListener);
        }
        super.dispose();
        this.attributeMaps = null;
        this.mapChangeListener = null;
    }

    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        Object value = attributeMaps[0].get(element);
        //$NON-NLS-1$
        cell.setText(value == null ? "" : value.toString());
    }
}
