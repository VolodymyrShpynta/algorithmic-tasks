package com.vshpynta;

public class SumTwoIntegers {

    //https://leetcode.com/problems/sum-of-two-integers/
    public static int getSum(int a, int b) {
        while (b != 0) {
            var carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }

        return a;
    }
}
