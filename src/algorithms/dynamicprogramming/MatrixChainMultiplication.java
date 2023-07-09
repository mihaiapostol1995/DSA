package algorithms.dynamicprogramming;

import java.util.Arrays;

public class MatrixChainMultiplication {

    // Driver code
    public static void main(String[] args)
    {
        int[] matrices = new int[] {1, 2, 3, 4, 3, 4,5,2,5,2,5,2,5,6,6,4,3,3,4,4,5,6,4,3,3,2,3,4,5,5,6,6,7,8,6,4,2,3,4,4,5,4,4,3,4,1,5,2,4,5,6,1,3,5,5,6,5,2,1,4,5,4,1,3,5,5,6,7,7,6,5,4,2};
        int arrayLength = matrices.length;

        matrixChainOrder(matrices, arrayLength);
        System.out.println(MatrixChainOrder(matrices, arrayLength));
    }

    private static void matrixChainOrder(int[] matrixArray, int arrayLength) {
        int [][] table = new int[arrayLength][arrayLength];

        int difference = 1; // this equals to the number of possible matrices, counting 0 matrix
        while (difference < arrayLength - 1) {
            for (int i = 0; i + difference < arrayLength - 1; i++) {

                int min = Integer.MAX_VALUE;
                for (int k = i; k < i + difference; k++) {
                    int innerMin = table[i][k] + table[k + 1][i + difference]
                            +
                            matrixArray[i] // rows of i matrix
                            * matrixArray[k + 1] // columns of k matrix
                            * matrixArray[i + difference + 1]; //columns of "difference index" matrix
                    //

                    if (innerMin < min) min = innerMin;
                }

                if (min != Integer.MAX_VALUE) table[i][i + difference] = min;
            }

            difference++;
        }


        System.out.println(table[0][arrayLength-2]);
    }

    // Matrix Ai has dimension p[i-1] x p[i] for i = 1..n
    static int MatrixChainOrder(int p[], int n)
    {
        /* For simplicity of the
        program, one extra row and
        one extra column are allocated in m[][].  0th row
        and 0th column of m[][] are not used */
        int m[][] = new int[n][n];

        int i, j, k, L, q;

        /* m[i, j] = Minimum number of scalar
        multiplications needed to compute the matrix
        A[i]A[i+1]...A[j] = A[i..j] where
        dimension of A[i] is p[i-1] x p[i] */

        // cost is zero when multiplying one matrix.
        for (i = 1; i < n; i++)
            m[i][i] = 0;

        // L is chain length.
        for (L = 2; L < n; L++)
        {
            for (i = 1; i < n - L + 1; i++)
            {
                j = i + L - 1;
                if (j == n)
                    continue;
                m[i][j] = Integer.MAX_VALUE;
                for (k = i; k <= j - 1; k++)
                {
                    // q = cost/scalar multiplications
                    q = m[i][k] + m[k + 1][j]
                            + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j])
                        m[i][j] = q;
                }
            }
        }

        return m[1][n - 1];
    }
}
