package com.derekbixler;

public class Driver {
    public static final int FIBLOOPBIGINTEGER = 0;
    public static final int FIBMATRIXBIGINTEGER = 1;
    public static final int FIBRECUR = 2;

    public static void main(String[] args) {
        Driver d = new Driver();
//        for (int i = 0; i < 15; i++) {
//            System.out.println(d.timeTestOneFunction(1, i, 1000));
//        }
        d.timeTests('X', 15, 10, 150);
    }

    public long timeTestOneFunction(int function, long x, int nTrials) {
        int trial;
        long startTime;
        long endTime;
        long averageTimeNanoSecs;
        BigInteger tmpResult;
        if (function == FIBLOOPBIGINTEGER) {
            startTime = System.nanoTime();
            for (trial = 0; trial < nTrials; trial++) {
                tmpResult = Fibonacci.fibLoopBigInteger(x);
            }
            endTime = System.nanoTime();
        } else if (function == FIBMATRIXBIGINTEGER) {
            startTime = System.nanoTime();
            for (trial = 0; trial < nTrials; trial++) {
                tmpResult = Fibonacci.fibMatrixBigInteger(x);
            }
            endTime = System.nanoTime();
        } else {
            endTime = 0;
            startTime = 0;
        }

        averageTimeNanoSecs = (endTime - startTime) / nTrials;
        return averageTimeNanoSecs;
    }

    public void timeTests(char XorN, long maxFibRecurX, int nTrials, int upperLimit) {
        int trial;
        long X, N, tmpResult;
        long averageTimeNanoseconds;
        println("# Performance data for BigInteger Fibonacci function variants");
        println("# Column Headers");
        println("#                  +-------------------------------- Time (Milliseconds) --------------------------------+");
        println("# Size(N)\tValue(X)\tFibLoopBigInteger\tFibMatrixBigInteger");

        for (X = 1; X <= upperLimit; X++) {
            N = (long) Math.floor(Math.log(X)) + 1;
            if (XorN == 'X' || X == (long) Math.pow(2, N) - 1) {
                print("  " + N + "\t\t\t" + X);
            }

            averageTimeNanoseconds = timeTestOneFunction(FIBLOOPBIGINTEGER, X, nTrials);
            print("\t\t\t" + averageTimeNanoseconds);

            averageTimeNanoseconds = timeTestOneFunction(FIBMATRIXBIGINTEGER, X, nTrials);
            print("\t\t\t\t" + averageTimeNanoseconds);
            println("");
        }
    }

    public void println(String s) {
        System.out.println(s);
    }

    public void print(String s) {
        System.out.print(s);
    }
}
