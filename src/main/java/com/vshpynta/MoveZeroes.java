package com.vshpynta;

public class MoveZeroes {

    //https://leetcode.com/problems/move-zeroes
    public static void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }
        var index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                if (i != index) {
                    nums[i] = 0;
                }
                index++;
            }
        }
    }
}
