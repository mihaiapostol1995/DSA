package cognyte;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapRemove {
    public static void main(String[] args) {
        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        //concurrentHashMap.remove(null);
        concurrentHashMap.remove("hi");
    }
}
