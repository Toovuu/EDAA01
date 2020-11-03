package map;

import java.security.Key;

public class SimpleHashMap<K,V> implements Map<K,V>{
    private static final double loadFactor = 0.75;
    private Entry<K,V>[] map;
    private int capacity;
    private int size;


    public SimpleHashMap(){
        this(16);
    }

    public SimpleHashMap(int capacity){
        this.capacity = capacity;
        map = (Entry<K,V>[]) new Entry[capacity];
    }

    @Override
    public V get(Object arg0) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public V put(K arg0, V arg1) {

        return null;
    }

    @Override
    public V remove(Object arg0) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public String show() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            sb.append(i + "\t");
            if (map[i] != null) {
                Entry<K, V> e = map[i];
                while (e != null) {
                    sb.append(e.toString() + ", ");
                    e = e.next;
                }
            } else {
                sb.append("empty");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private int index(K key){
        int index = Math.abs(key.hashCode() % capacity); //
        return index;
    }

    private Entry<K,V> find(int index, K key){
        if(map[index] == null){
            return null;
        }
        else{
            Entry<K,V> e = map[index];
            while(e!= null){
                if(e.getKey().equals(key)){
                    return e;
                }
                e = e.next;
            }

        }
        return null;
    }

    private void rehash(){

    }

    private static class Entry<K,V> implements Map.Entry<K,V>{
        private K key;
        private V value;
        private Entry<K, V> next;


        private Entry(K key, V value){
            this.key = key;
            this.value = value;

        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public String toString(){
            return getKey() + " = " + getValue();
        }
    }
}
