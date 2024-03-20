package com.vshpynta;

//https://leetcode.com/problems/rotate-array/
public class RotateArray {

    public void rotate(int[] nums, int k) {
        // Reduce k to its equivalent value within array length range.
        k = k % nums.length;

        // Reverse the whole array
        reverse(nums, 0, nums.length);

        // Reverse the first part of the array (from 0 to k)
        reverse(nums, 0, k);

        // Reverse the second part of the array (from k to length)
        reverse(nums, k, nums.length);
    }

    private static void reverse(int[] nums, int start, int end) {
        for (int left = start, right = end - 1; left < right; left++, right--) {
            swap(nums, left, right);
        }
    }

    private static void swap(int[] array, int left, int right) {
        var temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
