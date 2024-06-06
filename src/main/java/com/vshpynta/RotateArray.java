package com.vshpynta;

import java.util.Arrays;

// 189. Rotate Array
// https://leetcode.com/problems/rotate-array/
public class RotateArray {

    //nums = [1,2,3,4,5,6,7], k = 3
    public static void rotate(int[] nums, int k) {
        // Reduce k to its equivalent value within array length range.
        k = k % nums.length;

        // Reverse the whole array
        reverse(nums, 0, nums.length);// result: 7,6,5,4,3,2,1

        // Reverse the first part of the array (from 0 to k)
        reverse(nums, 0, k); //result: 5,6,7,4,3,2,1

        // Reverse the second part of the array (from k to length)
        reverse(nums, k, nums.length); //result: 5,6,7,1,2,3,4
    }

    private static void reverse(int[] nums, int from, int to) {
        var l = from;
        var r = to - 1;
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    private static void swap(int[] array, int left, int right) {
        var temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args) {
        var nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
}
