package com.vshpynta;

public class MajorityElement {

    //https://leetcode.com/problems/majority-element/
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length < 1){
            return 0;
        }
        var candidate = nums[0];
        var appearances = 1;
        for (int i = 1; i < nums.length; i++) {
            if (candidate == nums[i]){
                appearances++;
            } else {
                appearances--;
                if (appearances < 1){
                    candidate = nums[i];
                    appearances = 1;
                }
            }
        }
        return candidate;
    }
}
