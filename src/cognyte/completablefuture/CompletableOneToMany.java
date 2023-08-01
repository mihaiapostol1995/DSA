package cognyte.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CompletableOneToMany {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4);

        integerList.stream()
                .map(i -> new int[] {i - 1, i + 1})
                .flatMap(arrays -> Stream.of(arrays))
                .forEach(e -> System.out.println(String.format("element is %s, size is %s, class is %s", e, e.length, e.getClass())));

        integerList.stream()
                .map(i -> new Integer[] {i - 1, i + 1})
                .flatMap(Arrays::stream)
                .forEach(System.out::println);

        // works ONLY with array of Integer, not array of int!

        integerList.stream()
                .flatMap(i -> Stream.of(i - 1, i + 1))
                .forEach(System.out::println);
    }
}
