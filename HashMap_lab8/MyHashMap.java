import java.util.Iterator;
import java.util.Set;
import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int initialSize = 16;
    private double loadFactor = 0.75;
    private int size;
    private int capa;
    private keyval list;
    private ArrayList<keyval> buckets;

    public MyHashMap() {
        buckets = new ArrayList<>(initialSize);
        for (int i = 0; i < initialSize; i++) {
            list = new keyval(null, null, null);
            buckets.add(list);
        }
        size = 0;
        capa = initialSize;
    };

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        buckets = new ArrayList<>(initialSize);
        for (int i = 0; i < initialSize; i++) {
            list = new keyval(null, null, null);
            buckets.add(list);
        }
        size = 0;
        capa = initialSize;
    };
    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        buckets = new ArrayList<>(initialSize);
        for (int i = 0; i < initialSize; i++) {
            list = new keyval(null, null, null);
            buckets.add(list);
        }
        size = 0;
        capa = initialSize;
    };

    private class keyval<K, V> {
        K key;
        V value;
        keyval next;
        keyval(K key, V value, keyval n) {
            this.key = key;
            this.value = value;
            next = n;
        }
        keyval get(K key) {
            if (key != null && key.equals(this.key)) {
                return this;
            }
            if (next == null) {
                return null;
            }
            return next.get(key);
        }
    }

    public void clear(){
        buckets = new ArrayList<>(capa);
        for (int i = 0; i < capa; i++) {
            list = new keyval(null, null, null);
            buckets.add(list);
        }
        size = 0;
    };
    public boolean containsKey(K key){
        int bucind = key.hashCode()%capa;
        if (bucind < 0) {bucind = capa + bucind;}
        return buckets.get(bucind).get(key) != null;
    };
    public V get(K key){
        int bucind = key.hashCode()%capa;
        if (bucind < 0) {bucind = capa + bucind;}
        if (!containsKey(key)) {return null;}
        return (V) buckets.get(bucind).get(key).value;

    };
    public int size() {
        return size;
    };
    public void put(K key, V value){
        int bucind = key.hashCode()%capa;
        if (bucind < 0) {bucind = capa + bucind;}
        keyval indkv = buckets.get(bucind);
        if (indkv.key != null && !indkv.key.equals(key)) {
            while (indkv.next != null && !indkv.next.key.equals(key)) {
                indkv = indkv.next;
            }
            if (indkv.next == null) {
                indkv.next = new keyval(key, value, null);
                size += 1;
            }
            else if(indkv.next.key.equals(key)) {
                indkv.next.value = value;}
        }
        else if (indkv.key != null && indkv.key.equals(key)) {
            indkv.value = value;
        }
        else {
            indkv.key = key;
            indkv.value = value;
            size += 1;
        }
        if ((float) size/(float) capa > loadFactor) {resize();}
    };

    public void resize(){
        capa = capa * 2;
        ArrayList<keyval> newbu = new ArrayList<>(capa);
        for (int i = 0; i < capa; i++) {
            list = new keyval(null, null, null);
            newbu.add(list);
        }
        for (keyval k: buckets) {
            while (k != null && k.key != null) {
                int bucind = k.key.hashCode()%capa;
                if (bucind < 0) {bucind = capa + bucind;}
                keyval indkv = newbu.get(bucind);
                if (indkv.key != null) {
                    while (indkv.next != null) {
                        indkv = indkv.next;
                    }
                    indkv.next = new keyval(k.key, k.value, null);
                }
                else {
                    indkv.key = k.key;
                    indkv.value = k.value;
                }
                k = k.next;
            }
        }
        buckets = newbu;
    }

    public Set<K> keySet(){
        Set<K> ks = new HashSet<K>();
        for (keyval k: buckets) {
            while (k!=null && k.key != null) {
                ks.add((K) k.key);
                k = k.next;
            }
        }
        return ks;
    };

    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    };

    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    };

    @Override
    public Iterator<K> iterator () {
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<K> {
        private ArrayList<keyval> cur;
        private keyval curkv;
        private int ind;
        private int curlen;

        public MyHashMapIterator() {
            cur = buckets;
            ind = 0;
            curkv = buckets.get(ind);
            curlen = capa;
        }

        @Override
        public boolean hasNext() {
            return curkv != null;
        }

        @Override
        public K next() {
            K ret = (K) curkv.key;
            curkv = curkv.next;
            if (curkv == null) {
                ind += 1;
                if (ind < curlen) {curkv = buckets.get(ind);}
            }
            return ret;
        }
    }
}
