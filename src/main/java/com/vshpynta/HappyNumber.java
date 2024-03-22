package com.vshpynta;

import java.util.HashSet;

//https://leetcode.com/problems/happy-number/
public class HappyNumber {

    public boolean isHappyWithFloydCycleDetectionAlgorithm(int n) {
        var slow = n;
        var fast = n;
        //Floyd Cycle detection algorithm:
        while (true) {
            slow = digitsSquareSum(slow);
            if (slow == 1) { //verify 'happy number' condition
                return true;
            }
            fast = digitsSquareSum(digitsSquareSum(fast));
            if (slow == fast) { //cycle detected
                return false;
            }
        }
    }

    public boolean isHappyWithSet(int n) {
        var numbers = new HashSet<Integer>();
        var number = n;
        while (true) {
            number = digitsSquareSum(number);
            if (number == 1) { //verify 'happy number' condition
                return true;
            }
            if (!numbers.add(number)) { //if number already exist in the numbers set (cycle detected)
                return false;
            }
        }

    }

    private int digitsSquareSum(int n) {
        var number = n;
        var result = 0;
        while (number > 0) {
            var fraction = number / 10;
            var remainder = number % 10;
            result += remainder * remainder;
            number = fraction;
        }
        return result;
    }
}
