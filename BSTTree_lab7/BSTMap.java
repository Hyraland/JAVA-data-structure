import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>  {
    private int size;
    private K key;
    private V value;
    private BSTMap lchild;
    private BSTMap rchild;

    /** Removes all of the mappings from this map. */
    public BSTMap(K key, V value) {
        this.key = key;
        this.value = value;
        this.size += 1;
    }
    public BSTMap() {
        key = null;
        size = 0;
        value = null;
        lchild = null;
        rchild = null;
    }

    public void clear() {
        lchild = null;
        rchild = null;
        key = null;
        value = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {

        if (this == null || this.key == null) {
            return false;
        }

        if(key.compareTo(this.key) > 0) {
            return this.rchild.containsKey(key);
        }
        else if(key.compareTo(this.key) < 0) {
            return this.lchild.containsKey(key);
        }
        else {
            return true;
        }
    }

    public V get(K key) {
        if (this == null || this.key == null) {
            return null;
        }

        if(key.compareTo(this.key) > 0) {
            return (V) this.rchild.get(key);
        }
        else if(key.compareTo(this.key) < 0) {
            return (V) this.lchild.get(key);
        }
        else {
            return this.value;
        }
    }

    public int size() {
        return size;
    };

    public void put(K key, V value){
        if (this.key == null) {
            this.key = key;
            this.value = value;
            size += 1;
        }
        else if(key.compareTo(this.key) > 0) {
            if (this.rchild != null) {
                this.rchild.put(key, value);
            }
            else {
                this.rchild = new BSTMap<>(key, value);
            }
            size += 1;
        }
        else if(key.compareTo(this.key) < 0) {
            if (this.lchild != null) {
                this.lchild.put(key, value);
            }
            else {
                this.lchild = new BSTMap<>(key, value);
            }
            size += 1;
        }
    };

    public void printInOrder() {
        if (this == null) {
        }
        else if (this.lchild == null && this.rchild == null) {
            System.out.println(this.value);
        }
        else {
            this.lchild.printInOrder();
            System.out.println(this.value);
            this.rchild.printInOrder();
        }
    };

    @Override
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
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
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
