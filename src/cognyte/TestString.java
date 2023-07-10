package cognyte;

import java.util.ArrayList;
import java.util.List;

public class TestString {

    private int i= 0;
    public static void main(String[] args) {
        int i = 2;
        System.out.println(i);
        int d;

        List<String> strings = new ArrayList<>();
        strings.add("1"); strings.add("2");
        strings.remove(0);
        System.out.println(strings.get(0));

        for (String s: strings) {
            System.out.println("print" + s);
        }

        strings.forEach(e->strings.remove(0));
    }

}
