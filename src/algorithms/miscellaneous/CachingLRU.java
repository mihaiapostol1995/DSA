package algorithms.miscellaneous;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class CachingLRU {
    public static void main(String[] args) {
        LRUCache<Integer> cache = new LRUCache<>(4);
        cache.put(1);
        cache.put(2);
        cache.put(3);
        cache.put(1);
        cache.put(4);
        cache.put(5);
        cache.display();
    }
}

class LRUCache<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final Set<T> set = new HashSet<>();
    private final int MAX_SIZE;

    LRUCache(int size) {
        this.MAX_SIZE = size;
    }

    synchronized void put(T element) {
        if (set.contains(element)) {
            queue.remove(); // put it toward the beginning!
        }
        else if (set.size() == MAX_SIZE) {
            set.remove(queue.poll());
        }

        queue.add(element);
        set.add(element); // maybe the hashcode of the elements are the same BUT THEY ARE NOT EQUAL!
        // this is why we don't just return on the 1st "if" of the function
    }

    void display() {
        queue.forEach(e -> System.out.print(" " + e));
    }
}