package com.tkloht;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by tkloht on 12.11.14.
 * Tests for karatsuba multiplication
 */
public class Test {
    public static void test(int n, BigInteger length){
        for (int i = 0; i < n; i++) {
            Random random = new Random();

            BigInteger x = new BigInteger(length.bitLength(), random);
            BigInteger y = new BigInteger(length.bitLength(), random);

            long startTime = System.nanoTime();
            BigInteger result = Karatsuba.mult(x, y);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println(x + " + " + y + " = " + result + " [" + duration + " nanoseconds]");
        }
    }
}
