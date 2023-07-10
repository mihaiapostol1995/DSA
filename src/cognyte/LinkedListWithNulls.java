package cognyte;

import java.util.*;

public class LinkedListWithNulls {

    public static void main(String[] args) {
        List<String> nulls = new LinkedList<>();
        nulls.add("hi"); nulls.add("hello");

        ArrayList<String> nulls2 = new ArrayList<>(nulls);

       // nulls2.add("bye");
       // nulls2.remove(1);
        nulls2.clear();

        nulls.remove("hi");

        nulls.add(null);

        nulls.forEach(System.out::println);

        List<String> nulls3 = new LinkedList<>();
        nulls3.add(null); nulls3.add("hi"); nulls3.add(null); nulls3.add("hello"); nulls3.add(null);
        Set<String> nullSet = new HashSet<>();
        nullSet.addAll(nulls3);

        for (String s : nulls3) {
            System.out.println(s);
        }
    }
}