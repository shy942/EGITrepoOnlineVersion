/***/
package org.eclipse.jface.viewers.deferred;

import java.util.HashMap;

/* package */
class IntHashMap {

    private HashMap map;

    /**
* @param size
* @param loadFactor
*/
    public  IntHashMap(int size, float loadFactor) {
        map = new HashMap(size, loadFactor);
    }

    /**
*
*/
    public  IntHashMap() {
        map = new HashMap();
    }

    /**
* @param key
*/
    public void remove(Object key) {
        map.remove(key);
    }

    /**
* @param key
* @param value
*/
    public void put(Object key, int value) {
        map.put(key, Integer.valueOf(value));
    }

    /**
* @param key
* @return the int value at the given key
*/
    public int get(Object key) {
        return get(key, 0);
    }

    /**
* @param key
* @param defaultValue
* @return the int value at the given key, or the default value if this map does not contain the given key
*/
    public int get(Object key, int defaultValue) {
        Integer result = (Integer) map.get(key);
        if (result != null) {
            return result.intValue();
        }
        return defaultValue;
    }

    /**
* @param key
* @return <code>true</code> if this map contains the given key, <code>false</code> otherwise
*/
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    /**
* @return the number of key/value pairs
*/
    public int size() {
        return map.size();
    }
}
