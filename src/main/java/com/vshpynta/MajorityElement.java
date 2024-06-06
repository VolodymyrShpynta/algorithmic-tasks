package com.vshpynta;

// 169. Majority Element
// https://leetcode.com/problems/majority-element/
public class MajorityElement {

    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        var maj = nums[0];
        var count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (maj == nums[i]) {
                count++;
            } else {
                count--;
                if (count < 0) {
                    maj = nums[i];
                    count = 1;
                }
            }
        }

        return maj;
    }
}
