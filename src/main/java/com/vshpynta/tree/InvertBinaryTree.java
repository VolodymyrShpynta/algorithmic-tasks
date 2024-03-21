package com.vshpynta.tree;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/invert-binary-tree/
public class InvertBinaryTree {

    public TreeNode invertTreeRecursive(TreeNode root) {
        if (root == null) {
            return null;
        }

        var temp = root.left;
        root.left = invertTreeRecursive(root.right);
        root.right = invertTreeRecursive(temp);

        return root;
    }

    public TreeNode invertTreeDFS(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        pushIfNotNull(stack, root);

        while (!stack.isEmpty()) {
            var node = stack.pop();
            var left = node.left;
            node.left = node.right;
            node.right = left;
            pushIfNotNull(stack, node.left);
            pushIfNotNull(stack, node.right);
        }

        return root;
    }

    public TreeNode invertTreeBFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        addIfNotNull(queue, root);

        while (!queue.isEmpty()) {
            var node = queue.poll();
            var left = node.left;
            node.left = node.right;
            node.right = left;
            addIfNotNull(queue, node.left);
            addIfNotNull(queue, node.right);
        }

        return root;
    }

    private static void pushIfNotNull(Deque<TreeNode> stack, TreeNode node) {
        if (node != null) {
            stack.push(node);
        }
    }

    private static void addIfNotNull(Queue<TreeNode> queue, TreeNode node) {
        if (node != null) {
            queue.add(node);
        }
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
