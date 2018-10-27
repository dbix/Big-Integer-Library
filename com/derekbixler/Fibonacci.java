package com.derekbixler;

public class Fibonacci {

    public static long fibRecur(long x) {
        long result = 1;
        if (x >= 2)
            result = fibRecur(x - 1) + fibRecur(x - 2);
        return result;
    }

    public static long fibLoop(long x) {
        long result = 1;
        if (x >= 2) {
            long last = 1;
            long secondLast = 1;
            for (int k = 2; k <= x; k++) {
                result = last + secondLast;
                secondLast = last;
                last = result;
            }
        }
        return result;
    }

    public static BigInteger fibLoopBigInteger(long x) {
        BigInteger result = new BigInteger("1");

        if (x >= 2) {
            BigInteger last = new BigInteger("1");
            BigInteger secondLast = new BigInteger("1");
            BigInteger tmp;
            for (int i = 2; i <= x; i++) {
                tmp = last;
                result = last.add(secondLast);
                secondLast = tmp;
                last = result;
            }
        }
        return result;
    }

    public static BigInteger fibMatrixBigInteger(long x) {
        BigInteger result = new BigInteger("1");
        if (x >= 2) {
            BigInteger baseMatrix[][] = {
                    {new BigInteger("1"), new BigInteger("1")},
                    {new BigInteger("1"), new BigInteger("0")}
            };
            BigInteger resultMatrix[][] = matrixPowerBigInteger2x2(baseMatrix, x);
            result = resultMatrix[0][0];
        }
        return result;
    }

    public static BigInteger[][] matrixPowerBigInteger2x2(BigInteger[][] baseMatrix, long power) {
        BigInteger[][] result = baseMatrix;
        for (int n = 1; n < power; n++) {
            result = matrixMultiplyBigInteger2x2(result, baseMatrix);
        }
        return result;

    }

    public static BigInteger[][] matrixMultiplyBigInteger2x2(BigInteger[][] a, BigInteger[][] b) {
        BigInteger[][] c = new BigInteger[2][2];
        BigInteger lTerm, rTerm;

        lTerm = a[0][0].multiply(b[0][0]);
        rTerm = a[0][1].multiply(b[1][0]);
        c[0][0] = lTerm.add(rTerm);

        lTerm = a[0][0].multiply(b[0][1]);
        rTerm = a[0][1].multiply(b[1][1]);
        c[0][1] = lTerm.add(rTerm);

        lTerm = a[1][0].multiply(b[0][0]);
        rTerm = a[1][1].multiply(b[1][0]);
        c[1][0] = lTerm.add(rTerm);

        lTerm = a[1][0].multiply(b[0][1]);
        rTerm = a[1][1].multiply(b[1][1]);
        c[1][1] = lTerm.add(rTerm);

        return c;
    }

    public static long fibMatrix(int x) {
        long result = 1;
        if (x >= 2) {
            long baseMatrix[][] = {{1, 1}, {1, 0}};
            long resultMatrix[][] = MatrixPower(baseMatrix, x);
            result = resultMatrix[0][0];
        }
        return result;

    }

    public static long[][] MatrixMultiply(long[][] a, long[][] b) {
        long[][] c;
        if (a[0].length != b.length) {
            c = null;
        } else {
            c = new long[a.length][b[0].length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    c[i][j] = 0L;
                }
            }

            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    for (int k = 0; k < a[0].length; k++) {
                        c[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
        }
        return c;
    }

    public static long[][] MatrixPower(long[][] matrix, int power) {
        long[][] result = matrix;
        for (int n = 1; n < power; n++) {
            result = MatrixMultiply(result, matrix);
        }
        return result;
    }

}
