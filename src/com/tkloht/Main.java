package com.tkloht;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {


    public static void main(String[] args) {

        System.out.println("Welcome to Karatsuba!");
        System.out.println("Please enter the two numbers you want to multiply,\n in unsigned decimal representation");
        BigInteger x = readNumber();
        System.out.println("Good job! Now another one.");
        BigInteger y = readNumber();
        BigInteger result = karatsuba(x, y);
        System.out.println("Result:\n" + result);
    }

    public static BigInteger readNumber(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BigInteger result = null;
        while(result == null) {
            String input = null;
            try {
                input = reader.readLine();
            } catch (IOException e) {
                System.out.println("Error Reading your input!");
                e.printStackTrace();
            }
            try{
                result = new BigInteger(input);
                if (result.compareTo(BigInteger.ZERO) < 0) {
                    System.out.println("You entered a negative number. I told you not to but i have automatically converted it to its absolute value");
                    result = result.abs();
                }
            } catch (NumberFormatException e) {
                System.out.println("That was not a valid number. Please try again.");
            }

        }
        return result;
    }

    public static int getPivot(BigInteger x, BigInteger y){
        int length = Math.max(x.bitLength(), y.bitLength());
        // number of bits / 2, rounded up
        return (length /2) + (length % 2);
    }

    public static BigInteger karatsuba (BigInteger x, BigInteger y) {
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
        BigInteger ac = karatsuba(a, c);
        BigInteger bd = karatsuba(b, d);
        BigInteger abcd = karatsuba(a.add(b), c.add(d));

        return ac.add(
            abcd.subtract(ac).subtract(bd).shiftLeft(pivot).add(
                    bd.shiftLeft(2*pivot)
            )
        );
    }

}
