package com.vshpynta.backtracking;

import java.util.ArrayList;
import java.util.List;

// 131. Palindrome Partitioning
// https://leetcode.com/problems/palindrome-partitioning/description/
public class PalindromePartitioning {

    // Input: s = "aab"
    // Output: [["a","a","b"],["aa","b"]]
    public static List<List<String>> partition(String s) {
        if (s == null) {
            return null;
        }
        var result = new ArrayList<List<String>>();
        var partitions = new ArrayList<String>();
        dfsPartition(s, 0, partitions, result);
        return result;
    }

    private static void dfsPartition(String s, int start, List<String> currentPartitions, List<List<String>> result) {
        if (start >= s.length()) { //considered all elements in the string for current path
            //add the current partitions to the result:
            result.add(new ArrayList<>(currentPartitions)); // make a copy due to cleaning up this collection in the recursive backtracking
            return;
        }

        for (int j = start; j < s.length(); j++) {
            if (isPalindrome(s, start, j)) {
                currentPartitions.add(s.substring(start, j + 1));
                dfsPartition(s, j + 1, currentPartitions, result);
                currentPartitions.remove(currentPartitions.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }
}
