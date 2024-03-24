public class HashMap<K, V> {
    private class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getkey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private final int SIZE = 5;
    private Entry<K, V> table[];

    public HashMap() {
        table = new Entry[SIZE];
    }

    public void put(K key, V value) {
        int hash = key.hashCode() % SIZE;
        Entry<K, V> e = table[hash];
        if (e == null) {
            table[hash] = new Entry<K, V>(key, value);
        } else {
            while (e.next != null) {
                if (e.getkey() == key) {
                    e.setValue(value);
                    return;
                }
                e = e.next;
            }

            if (e.getkey() == key) {
                e.setValue(value);
                return;
            }

            e.next = new Entry<K, V>(key, value);
        }
    }

    public V get(K key) {
        int hash = key.hashCode() % SIZE;
        Entry<K, V> e = table[hash];

        if (e == null) {
            return null;
        }
        while (e != null) {
            if (e.getkey() == key) {
                return e.getValue();
            }
            e = e.next;
        }
        return null;
    }

    public Entry<K, V> remove(K key) {
        int hash = key.hashCode() % SIZE;
        Entry<K, V> e = table[hash];
        if (e == null) {
            return null;
        }
        if (e.getkey() == key) {
            table[hash] = e.next;
            e.next = null;
            return e;
        }
        Entry<K, V> prev = e;
        e = e.next;
        while (e != null) {
            if (e.getkey() == key) {
                prev.next = e.next;
                e.next = null;
                return e;
            }
            prev = e;
            e = e.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        for (Entry<K, V> e : table) {
            while (e != null) {
                if (e.getkey().equals(key)) {
                    return true;
                }
                e = e.next;
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (Entry<K, V> e : table) {
            while (e != null) {
                if (e.getValue().equals(value)) {
                    return true;
                }
                e = e.next;
            }
        }
        return false;
    }

    public int size() {
        int count = 0;
        for (Entry<K, V> e : table) {
            while (e != null) {
                count++;
                e = e.next;
            }
        }
        return count;
    }

}