package com.vshpynta;

// 27. Remove Element
// https://leetcode.com/problems/remove-element/description/
public class RemoveElementInArray {

    public int removeElement(int[] nums, int val) {
        var l = 0;
        var r = nums.length;
        while (l < r) {
            if (nums[r - 1] == val) {
                r--;
            } else {
                if (nums[l] == val) {
                    nums[l] = nums[r - 1];
                    nums[r - 1] = val;
                    r--;
                }
                l++;
            }
        }
        return r;
    }
}
