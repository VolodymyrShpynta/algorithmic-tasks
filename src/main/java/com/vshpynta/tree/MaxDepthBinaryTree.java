package com.vshpynta.tree;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//https://leetcode.com/problems/maximum-depth-of-binary-tree/
public class MaxDepthBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    //Definition for a binary tree node.
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

}
