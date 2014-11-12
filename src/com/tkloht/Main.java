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
        BigInteger result = Karatsuba.mult(x, y);
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

}
