package com.vshpynta;

import java.util.Arrays;

//https://leetcode.com/problems/missing-number/
public class MissingNumber {

    //Given: [0, 1, 3]. Missing number: 2
    public static int missingNumber(int[] nums) {
        var naturalNumbersSequenceSum = (0 + nums.length) * (nums.length + 1) / 2;
        var arraySum = 0;
        for (int num : nums) {
            arraySum += num;
        }
        return naturalNumbersSequenceSum - arraySum;
    }

    // The idea is to use XOR operation. Since a^b^b =a (a XOR b XOR b = a), it means two XOR operations with the same
    // number will eliminate the number and reveal the original number.
    // In this solution, XOR operation applies to both the index and value of the array.
    // In a complete array with no missing numbers, the index and value should be perfectly corresponding
    // (nums[index] = index), so in a missing array, what left finally is the missing number.
    public static int missingNumberXOR(int[] nums) {
        // nums.length - is the max value in the sequence in case of not missing items.
        // For example, in case of [0, 1, 3] - the max value is 3, missing value - 2.
        var result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            result = result ^ i ^ nums[i];
        }
        return result;
    }

    public static int missingNumberBinarySearch(int[] nums) {
        Arrays.sort(nums);
        int left = 0, right = nums.length;
        while (left < right) {
            var mid = (left + right) / 2;
            if (nums[mid] > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        var nums = new int[]{0, 1, 3};
        System.out.println(missingNumberXOR(nums));
    }
}
