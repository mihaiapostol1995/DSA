package algorithms.backtracking;

class MaximumIntegerWithKSwaps {
    private static String maxNumber;

    public static String findMaximumInteger(String num, int K) {
        maxNumber = num;
        findMaxNumber(num.toCharArray(), K, 0);
        return maxNumber;
    }

    private static void findMaxNumber(char[] digits, int K, int start) {
        if (K == 0 || start >= digits.length) {
            return;
        }

        int n = digits.length;
        char maxDigit = digits[start];

        for (int i = start + 1; i < n; i++) {
            if (digits[i] > maxDigit) {
                maxDigit = digits[i];
            }
        }

        if (maxDigit != digits[start]) {
            K--;
        }

        for (int i = start; i < n; i++) {
            if (digits[i] == maxDigit) {
                swap(digits, start, i);
                String currentNumber = String.valueOf(digits);
                if (currentNumber.compareTo(maxNumber) > 0) {
                    maxNumber = currentNumber;
                }
                findMaxNumber(digits, K, start + 1);
                swap(digits, start, i);
            }
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int M = 129814999;
        int K = 4;
        String num = String.valueOf(M);
        String maximumInteger = findMaximumInteger(num, K);
        System.out.println("Maximum integer after " + K + " swaps: " + maximumInteger);
    }
}
