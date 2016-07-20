/***/
package org.eclipse.ui.internal.views.markers;

/**
* @since 3.4
*	Mock Class needed for testing Sort
*/
public class MockMarkerEntry extends MarkerEntry {

    /**
*
*/
    public String name;

    /**
* @param name
*/
    public  MockMarkerEntry(String name) {
        super(null);
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return name.equals(((MockMarkerEntry) obj).name);
    }
}
