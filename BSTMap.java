/**
 * @author Nick
 * @version 1.0
 * @param <K> key
 * @param <V> value
 */
public class BSTMap<K extends Comparable, V> implements Map<K, V> {

    /**
     *
     * @param <K> key
     * @param <V> value
     */
    public class Entry<K extends Comparable, V> implements Map.Entry, Comparable {

        K key;
        V value;

        /**
         *
         * @param key key
         * @param value val
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         *
         * @return key
         */
        public K getKey() {
            return this.key;
        }

        /**
         *
         * @return val
         */
        public V getValue() {
            return this.value;
        }

        /**
         *
         * @return string
         */
        public String toString() {
            return "(" + this.key + ", " + this.value + ")";
        }

        /**
         *
         * @param o obj
         * @return boolean
         */
        public boolean equals(Object o) {
            if (o instanceof Entry) {
                Entry entry = (Entry) o;
                if (this.key == entry.key && this.value == entry.value) {
                    return true;
                }
            }
            if (!(o instanceof Entry)) {
                if (o.equals(key)) {
                    return true;
                }
            }
            return false;
        }

        /**
         *
         * @param o obj
         * @return int
         */
        @SuppressWarnings("unchecked")
        public int compareTo(Object o) {
            if (o instanceof Entry) {
                Entry entry = (Entry<K, V>) o;
                return this.key.compareTo(entry.key);
            } else {
                return this.key.compareTo(o);
            }
        }

    }

    private BST<Entry<K, V>> bst;

    /**
     * Constructor.
     */
    public BSTMap() {
        this.bst = new BST<>();
    }

    /**
     *
     * @return entry
     */
    public BST<Entry<K, V>> getTree() {
        return bst;
    }

    /**
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return val
     */
    public V put(K key, V value) {
        if (!bst.contains(key)) {
            Entry<K, V> e = new Entry<>(key, value);
            bst.add(e);
        } else {
            Entry<K, V> e = bst.get(key);
            e.value = value;
        }
        return value;
    }

    /**
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return val
     */
    public V putIfAbsent(K key, V value) {
        V v = get(key);
        Entry<K, V> e = new Entry<>(key, value);
        if (v == null) {
            e.value = value;
            bst.add(e);
            return null;
        }
        return v;
    }

    /**
     *
     * @return string
     */
    public String toString() {
        return null;
    }

    /**
     * Clear the map.
     */
    public void clear() {
        bst.clear();
    }

    /**
     *
     * @param key the key whose associated value is to be returned
     * @return val
     */
    public V get(K key) {
        if (!bst.contains(key)) {
            return null;
        }
        return bst.get(key).value;

    }

    /**
     *
     * @param key key whose presence in this map is to be tested
     * @return boolean
     */
    public boolean containsKey(K key) {
        return bst.contains(key);
    }

    /**
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     *
     * @param key key whose mapping is to be removed from the map
     * @return val
     */
    public V remove(K key) {
        if (bst.size() == 0) {
            return null;
        }
        if (bst.contains(key)) {
            bst.remove(key);
        }
        return get(key);
    }

    /**
     *
     * @return int
     */
    public int size() {
        return bst.size();
    }

}
