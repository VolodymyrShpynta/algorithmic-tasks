package com.vshpynta.tree;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Deque;
import java.util.LinkedList;


//https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
public class SortedArrayToBST {

    public static TreeNode sortedArrayToBSTIterative(int[] nums) {
        if (nums == null || nums.length < 1) {
            return null;
        }

        Deque<TreeNode> nodesStack = new LinkedList<>();
        Deque<Range> rangesStack = new LinkedList<>();

        var head = new TreeNode(0);
        nodesStack.push(head);
        rangesStack.push(new Range(0, nums.length));

        while (!nodesStack.isEmpty()) {
            var node = nodesStack.pop();
            var range = rangesStack.pop();
            var mid = range.from + (range.to - range.from) / 2;
            node.val = nums[mid];
            if (mid > range.from) {
                node.left = new TreeNode(0);
                nodesStack.push(node.left);
                rangesStack.push(new Range(range.from, mid));
            }
            if (mid + 1 < range.to) {
                node.right = new TreeNode(0);
                nodesStack.push(node.right);
                rangesStack.push(new Range(mid + 1, range.to));
            }
        }
        return head;
    }

    public static TreeNode sortedArrayToBSTRecursive(int[] nums) {
        if (nums == null) {
            return null;
        }
        return sortedArrayToBSTRecursive(nums, 0, nums.length);
    }

    private static TreeNode sortedArrayToBSTRecursive(int[] nums, int from, int to) {
        if (from >= to) {
            return null;
        }

        var mid = from + (to - from) / 2;
        var node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBSTRecursive(nums, from, mid);
        node.right = sortedArrayToBSTRecursive(nums, mid + 1, to);

        return node;
    }

    //Definition for a binary tree node.
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private static class Range {
        int from;
        int to;

        public Range(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[] args) {
        var array = new int[]{1, 2, 3};
        System.out.println(sortedArrayToBSTIterative(array));
    }
}
