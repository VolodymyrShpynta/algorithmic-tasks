package com.vshpynta;

import java.util.LinkedList;
import java.util.List;

public class FizzBuzz {

    //https://leetcode.com/problems/fizz-buzz/
    public static List<String> fizzBuzz(int n) {
        var result = new LinkedList<String>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }
}
