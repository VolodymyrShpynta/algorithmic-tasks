package com.vshpynta.tree;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//https://leetcode.com/problems/validate-binary-search-tree/description/
public class BinarySearchTreeValidator {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, new ValidationResult());
    }

    private boolean isValidBST(TreeNode root, ValidationResult result) {
        //The Inorder Depth First Search (DFS) traversal is being used to visit tree nodes:
        if (root.left != null) {
            isValidBST(root.left, result);
        }
        if (!result.isValid) {
            return false;
        }

        //The one of the characteristics of the Inorder DFS traversal is that all elements should appear in the increasing value order.
        //So, if this invariant is violated, it means that tree is not a valid Binary Search Tree.
        if (result.prevValue != null && result.prevValue >= root.val) {
            result.isValid = false;
            return false;
        }

        //for the validation purposes we just need to store the last encountered value
        result.prevValue = root.val;

        if (root.right != null) {
            isValidBST(root.right, result);
        }
        return result.isValid;
    }

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

    private static class ValidationResult {
        boolean isValid;
        Integer prevValue;

        public ValidationResult() {
            isValid = true;
        }
    }
}
