package cognyte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmptyArray {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        String[] array = stringList.toArray(new String[0]);
        System.out.println(array.length);
       // System.out.println(array[0]==null);

        String[] array2 = Collections.singletonList("hi").toArray(new String[0]);
        System.out.println(array2.length);
        System.out.println(array2[0]==null);
    }
}
