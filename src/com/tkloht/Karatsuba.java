package com.tkloht;

import java.math.BigInteger;

/**
 * Created by tkloht on 12.11.14.
 * implements karatsuba multiplication algorithm
 */
public class Karatsuba {
    public static int getPivot(BigInteger x, BigInteger y){
        int length = Math.max(x.bitLength(), y.bitLength());
        // number of bits / 2, rounded up
        return (length /2) + (length % 2);
    }

    public static BigInteger mult(BigInteger x, BigInteger y) {
        if (x.compareTo(BigInteger.TEN) < 0 || y.compareTo(BigInteger.TEN) < 0 ) {
            return BigInteger.valueOf(x.intValue() * y.intValue());
        }
        int pivot = getPivot(x, y);

        // x = a + 2^pivot b
        BigInteger b = x.shiftRight(pivot);
        BigInteger a = x.subtract(b.shiftLeft(pivot));

        // y = c + 2^pivot d
        BigInteger d = y.shiftRight(pivot);
        BigInteger c = y.subtract(d.shiftLeft(pivot));

        // recursion
        BigInteger ac = mult(a, c);
        BigInteger bd = mult(b, d);
        BigInteger abcd = mult(a.add(b), c.add(d));

        return ac.add(
            abcd.subtract(ac).subtract(bd).shiftLeft(pivot).add(
                    bd.shiftLeft(2*pivot)
            )
        );
    }
}
