package algorithms;

import java.util.HashMap;
import java.util.Map;

public class Fibonaci {
    private static final Map<Integer, Integer> cache = new HashMap<>();

    public static int fibonacci(int n) {
        if (cache.containsKey(n)) {
            System.out.println("Cache was hit for " + n);
            return cache.get(n);
        }

        int result;
        if (n <= 1) {
            result = n;
        } else {
            result = fibonacci(n - 1) + fibonacci(n - 2);
        }

        cache.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        int n = 10;
        int fib = fibonacci(n);
        System.out.println("Fibonacci of " + n + " is: " + fib);
    }
}
