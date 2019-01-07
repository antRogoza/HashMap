public class HashMap implements Map {

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    static final int MAXIMUM_CAPACITY = 1 << 30;

    private transient int size = 0;

    HashEntry[] map;

    final float loadFactor;

    int capacity;

    private  HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            this.capacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        this.loadFactor = loadFactor;
        this.capacity = initialCapacity;

        map = new HashEntry[initialCapacity];

        for (int i = 0; i < initialCapacity; i++)
            map[i] = null;
    }

    public HashMap(int initialCapacity) {

        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap() {

        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public int size() {
        return size;
    }

    public long get(int key) {

        int initialHash = -1;

        int hash = calculateHash(key % capacity, initialHash, key);

        if (map[hash] == null)
            return -1;
        else
            return map[hash].getValue();
    }

    public void put(int key, long value) {

        if (isNeededResizing())
            resize();

        int initialHash = -1;

        int hash = calculateHash(key % capacity, initialHash, key);

        if (initialHash != hash)
            if (map[hash] != null && map[hash].getKey() == key)
                map[hash].setValue(value);
            else
                map[hash] = new HashEntry(key, value);

        size++;
    }

    private int calculateHash(int hash, int initialHash, int key) {

        while (hash != initialHash
                && (map[hash] != null
                && map[hash].getKey() != key)) {

            if (initialHash == -1)
                initialHash = hash;

            hash = (hash + 1) % capacity;
        }

        return hash;
    }

    private boolean isNeededResizing() {

        float boundaryNumberOfElements = capacity * loadFactor;

        return size >= boundaryNumberOfElements;
    }

    private void resize() {

        HashEntry[] hashEntries = new HashEntry[map.length];

        for (int i = 0; i < map.length; i++) {
            hashEntries[i] = map[i];
        }
        capacity = capacity << 1;

        map = new HashEntry[capacity];

        for (int i = 0; i < hashEntries.length; i++) {
            map[i] = hashEntries[i];
        }

    }

}
