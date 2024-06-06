package com.vshpynta;

// 80. Remove Duplicates from Sorted Array II
// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
public class RemoveDuplicatesInArray {

    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length < 3) {
            return nums.length;
        }
        var resIndex = 1;
        var i = 2;
        while (i < nums.length) {
            if (nums[resIndex - 1] != nums[i] || nums[resIndex] != nums[i]) {
                nums[++resIndex] = nums[i];
            }
            i++;
        }
        return resIndex + 1;
    }
}
