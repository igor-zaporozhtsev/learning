package algoritms.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/*
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int maxSize;

    public LRUCache(int maxSize) {
        super(maxSize, 0.75f, true);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxSize;
    }

    public static void main(String[] args) {
	    LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "one");
        System.out.println(cache); // {1=one}
        cache.put(2, "two");
        System.out.println(cache); //{1=one, 2=two}
        cache.put(3, "three");
        System.out.println(cache); //{1=one, 2=two, 3=three}
        cache.put(4, "four");
        System.out.println(cache); // remove 1=one as old FIFO {2=two, 3=three, 4=four}
        cache.get(2);
        System.out.println(cache); // access "two" move in the end {3=three, 4=four, 2=two}
        cache.put(5, "five");
        System.out.println(cache); // remove 3=three as old FIFO { 4=four, 2=two, 5=five}
    }
}

*/
