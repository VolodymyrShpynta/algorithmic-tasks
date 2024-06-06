package com.vshpynta.tree;

// 116. Populating Next Right Pointers in Each Node
// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
public class TreeNextRightPointers {

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public static Node connect(Node root) {
        dfsConnect(root);
        return root;
    }

    private static void dfsConnect(Node node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            node.left.next = node.right;
        }
        if (node.next != null && node.right != null) {
            node.right.next = node.next.left;
        }

        dfsConnect(node.left);
        dfsConnect(node.right);
    }

    public static void main(String[] args) {
        var root = new Node(1, new Node(2), new Node(3), null);
        System.out.println(connect(root));
    }
}
