package com.vshpynta.tree;

import com.vshpynta.utils.JsonUtils;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

/**
 * The implementation of the AVL tree (self-balancing binary search tree).
 * For more details @see <a href="https://en.wikipedia.org/wiki/AVL_tree">AVL tree on Wiki</a>,
 * <a href="https://medium.com/basecs/the-little-avl-tree-that-could-86a3cae410c7">here</a>
 * and <a href="https://www.tutorialspoint.com/data_structures_algorithms/avl_tree_algorithm.htm">here</a>.
 * Algorithm animation could be found <a href="https://www.cs.usfca.edu/~galles/visualization/AVLtree.html">here</a>.
 */
@FieldDefaults(level = PRIVATE)
public class AVLTree {

    Node root;

    @Data
    @FieldDefaults(level = PRIVATE)
    public static class Node {
        int value;
        int height;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            this.height = 1;
        }
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    public Node lookup(int value) {
        if (root == null) {
            return null;
        }
        return lookup(root, value);
    }

    public void remove(int value) {
        root = remove(root, value);
    }

    private Node remove(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (value < node.value) {
            node.left = remove(node.left, value);
        } else if (node.value < value) {
            node.right = remove(node.right, value);
        } else { //node.value == value:
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }

            //node.left and node.right are present
            var minRight = minValue(node.right);
            node.value = minRight;
            node.right = remove(node.right, minRight);
        }

        node.height = calculateHeight(node);
        return rebalance(node);
    }

    private int minValue(Node node) {
        var current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    private Node lookup(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (node.value == value) {
            return node;
        }
        if (value < node.value) {
            return lookup(node.left, value);
        }
        return lookup(node.right, value);
    }

    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            if (node.left == null) {
                node.left = new Node(value);
            } else {
                node.left = insert(node.left, value);
            }
        } else if (node.value < value) { //Do not insert duplicates
            if (node.right == null) {
                node.right = new Node(value);
            } else {
                node.right = insert(node.right, value);
            }
        }

        node.height = calculateHeight(node);
        return rebalance(node);
    }

    private static Node rebalance(Node node) {
        var balance = getBalance(node);
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        return node;
    }

    private static int getBalance(Node node) {
        return height(node.left) - height(node.right);
    }

    private static Node leftRotate(Node node) {
        var newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        node.height = calculateHeight(node);
        newParent.height = calculateHeight(newParent);
        return newParent;
    }

    private static Node rightRotate(Node node) {
        var newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        node.height = calculateHeight(node);
        newParent.height = calculateHeight(newParent);
        return newParent;
    }

    private static int calculateHeight(Node node) {
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    private static int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public static void main(String[] args) {
        var avlTree = new AVLTree();
        avlTree.insert(40);
        avlTree.insert(30);
        avlTree.insert(50);
        avlTree.insert(60);
        avlTree.insert(25);
        avlTree.insert(35);
        System.out.println(JsonUtils.toJson(avlTree.root));

        avlTree.remove(40);
        System.out.println(JsonUtils.toJson(avlTree.root));
    }
}
