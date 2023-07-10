package cognyte;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SynchronizedListExample {
    public static void main(String[] args) {
        List<String> linkedList = Collections.synchronizedList(new LinkedList<>());

        linkedList.add("hi");
        linkedList.add("hi2");

        linkedList.forEach(e-> System.out.println(e));
    }
}
