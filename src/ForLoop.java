import java.util.HashSet;
import java.util.Set;

public class ForLoop {

    public static void main(String[] args) {
        Set<Short> set = new HashSet<>();
        for (Short s: set) {
            System.out.println("hi on line 7");
        }

        short s = 2;
        set.add((short) 2);
        if (set.contains(s)) {
            System.out.println("hi on line 14");
        }
    }
}
