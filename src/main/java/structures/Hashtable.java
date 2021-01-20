package structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Hashtable<K, V> {

    static class Entry<K, V> {
        int hash;
        K key;
        V value;
        Entry<K, V> next;

        Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Hashtable.Entry) {
                Entry<K, V> e = (Entry<K, V>) o;
                return Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue());
            }
            return false;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    private List<Entry<K, V>> table;
    private int capacity;
    private int numElements;
    private int threshold;
    private final float loadFactor = 0.75f;

    public Hashtable(int initCapacity) {
        if (initCapacity < 0) throw new IllegalArgumentException("Negative initial capacity: " + initCapacity);
        if (initCapacity == 0) initCapacity = 1;

        capacity = initCapacity;
        table = new ArrayList<>(initCapacity);
        threshold = (int) (initCapacity * loadFactor);

        for (int i = 0; i < capacity; i++) {
            table.add(i, null);
        }
    }

    public Hashtable() {
        this(16);
    }

    public int size() {
        return numElements;
    }

    public boolean isEmpty() {
        return (numElements == 0);
    }

    public V get(K key) {
        int hash = Objects.hashCode(key);
        int index = (hash % capacity);
        for (Entry<K, V> entry = table.get(index); entry != null; entry = entry.next) {
            if ((entry.hash == hash) && entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Unable to add element with null key into hashtable.");
        if (value == null) throw new IllegalArgumentException("Unable to add null into hashtable.");

        int hash = Objects.hashCode(key);
        int index = hash % capacity;
        if (numElements >= threshold) {
            increaseTable();
            hash = Objects.hashCode(key);
            index = (hash % capacity);
        }
        Entry<K, V> entry = table.get(index);
        table.set(index, new Entry<>(hash, key, value, entry));
        numElements++;
    }

    public boolean containsValue(V value) {
        if (value != null) {
            for (Entry<K, V> entry : table) {
                while (entry != null) {
                    if (entry.value.equals(value)) {
                        return true;
                    } else entry = entry.next;
                }
            }
        }
        return false;
    }

    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        int hash = Objects.hashCode(key);
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                if ((entry.hash == hash) && entry.key.equals(key)) {
                    return true;
                } else entry = entry.next;
            }
        }
        return false;
    }

    public boolean remove(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        int hash = Objects.hashCode(key);
        int index = (hash % capacity);
        Entry<K, V> entry = table.get(index);
        if (entry != null) {
            for (Entry<K, V> prev = null; entry != null; prev = entry, entry = entry.next) {
                if ((entry.hash == hash) && entry.key.equals(key)) {
                    if (prev != null) {
                        prev.next = entry.next;
                    } else {
                        table.set(index, entry.next);
                    }
                    numElements--;
                    return true;
                }
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            table.set(i, null);
        }
        numElements = 0;
    }

    public List<K> keys() {
        return table.stream().filter(Objects::nonNull).map(Entry::getKey).collect(Collectors.toList());
    }

    private void increaseTable() {
        int oldSize = capacity;
        List<Entry<K, V>> oldTable = table;
        capacity = oldSize * 2 + 1;
        table = new ArrayList<>(capacity);
        threshold = (int) (capacity * loadFactor);

        for (int i = 0; i < capacity; i++) {
            table.add(i, null);
        }

        for (int i = 0; i < oldSize; i++) {
            Entry<K, V> oldEntry = oldTable.get(i);
            while (oldEntry != null) {
                Entry<K, V> entry = oldEntry;
                oldEntry = oldEntry.next;
                int index = (entry.hash % capacity);
                entry.next = table.get(index);
                table.set(index, entry);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                result.append(i++).append("=[");
                while (entry != null) {
                    result.append("(")
                            .append(entry.key)
                            .append(",")
                            .append(entry.value)
                            .append("), ");
                    entry = entry.next;
                }
                result.delete(result.length() - 2, result.length()).append("]\n");
            }
        }
        if (result.length() > 0) {
            return result.delete(result.length() - 1, result.length()).toString();
        } else return "";
    }
}
