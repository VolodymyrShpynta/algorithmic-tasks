package com.vshpynta;

public class SingleNumber {

    //https://leetcode.com/problems/single-number/
    public static int singleNumber(int[] nums) {
        var result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }
}
