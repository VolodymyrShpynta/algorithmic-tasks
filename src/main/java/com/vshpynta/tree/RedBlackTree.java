package com.vshpynta.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vshpynta.utils.JsonUtils;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

/**
 * The implementation of the Red-Black tree (self-balancing binary search tree).
 * For more details @see <a href="https://en.wikipedia.org/wiki/Red%E2%80%93black_tree">Red-black tree on Wiki</a>,
 * <a href="https://www.geeksforgeeks.org/introduction-to-red-black-tree/">here</a>
 * and <a href="https://www.geeksforgeeks.org/insertion-in-red-black-tree/">here</a>.
 * Algorithm animation could be found <a href="https://www.cs.usfca.edu/~galles/visualization/RedBlack.html">here</a>.
 */
@FieldDefaults(level = PRIVATE)
public class RedBlackTree {

    Node root;

    @Data
    @FieldDefaults(level = PRIVATE)
    public static class Node {
        int value;
        boolean red;
        @JsonIgnore
        @ToString.Exclude
        Node parent;
        Node left;
        Node right;

        public Node(Node parent, int value) {
            this.value = value;
            this.parent = parent;
            red = true;
        }
    }


    public void insert(int value) {
        var newNode = insert(root, value);
        if (newNode == null) {
            return;
        }
        if (root == null) {
            root = newNode;
        }
        recolorAndRotate(newNode);
    }

    public Node lookup(int value) {
        if (root == null) {
            return null;
        }
        return lookup(root, value);
    }

    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(null, value);
        }

        if (value < node.value) {
            if (node.left == null) {
                var newNode = new Node(node, value);
                node.left = newNode;
                return newNode;
            }
            return insert(node.left, value);
        }

        if (node.value < value) {
            if (node.right == null) {
                var newNode = new Node(node, value);
                node.right = newNode;
                return newNode;
            }
            return insert(node.right, value);
        }

        return null; //Do not insert duplicates
    }

    private void recolorAndRotate(Node node) {
        var parent = parent(node);
        if (parent == null) {
            paintToBlack(node); //root not should be black
            return;
        }

        if (isBlack(node) || isBlack(parent)) {
            return;
        }

        var uncle = uncle(node);
        if (isRed(uncle)) {
            paintToBlack(parent);
            paintToBlack(uncle);
            recolorAndRotate(paintToRed(grandParent(node)));
            return;
        }

        //Rotate and recolor:
        var grandParent = grandParent(node);
        var greatGrandParent = parent(grandParent);
        if (isLeftLeftCase(node, parent, grandParent)) {
            var newGrandParent = rightRotate(grandParent);
            swapColors(grandParent, newGrandParent);
            setChild(greatGrandParent, newGrandParent);
            return;
        }
        if (isLeftRightCase(node, parent, grandParent)) {
            var newParent = leftRotate(parent);
            setChild(grandParent, newParent);
            var newGrandParent = rightRotate(grandParent);
            swapColors(grandParent, newGrandParent);
            setChild(greatGrandParent, newGrandParent);
            return;
        }
        if (isRightRightCase(node, parent, grandParent)) {
            var newGrandParent = leftRotate(grandParent);
            swapColors(grandParent, newGrandParent);
            setChild(greatGrandParent, newGrandParent);
            return;
        }
        if (isRightLeftCase(node, parent, grandParent)) {
            var newParent = rightRotate(parent);
            setChild(grandParent, newParent);
            var newGrandParent = leftRotate(grandParent);
            swapColors(grandParent, newGrandParent);
            setChild(greatGrandParent, newGrandParent);
        }
    }

    private boolean isLeftLeftCase(Node node, Node parent, Node grandParent) {
        return node.value < parent.value
                && parent.value < grandParent.value;
    }

    private boolean isLeftRightCase(Node node, Node parent, Node grandParent) {
        return node.value > parent.value
                && parent.value < grandParent.value;
    }

    private boolean isRightRightCase(Node node, Node parent, Node grandParent) {
        return node.value > parent.value
                && parent.value > grandParent.value;
    }

    private boolean isRightLeftCase(Node node, Node parent, Node grandParent) {
        return node.value < parent.value
                && parent.value > grandParent.value;
    }

    private void setChild(Node parent, Node child) {
        if (parent == null) {
            root = child;
            return;
        }
        if (parent.value > child.value) {
            parent.left = child;
            return;
        }
        parent.right = child;
    }

    private Node parent(Node node) {
        if (node == null) {
            return null;
        }
        return node.getParent();
    }

    private Node grandParent(Node node) {
        return parent(parent(node));
    }

    private Node uncle(Node node) {
        var parent = parent(node);
        var grandParent = grandParent(node);
        if (grandParent == null) {
            return null;
        }
        if (grandParent.left != parent) {
            return grandParent.left;
        }
        return grandParent.right;
    }

    private boolean isBlack(Node node) {
        return !isRed(node);
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.isRed();
    }

    private Node paintToBlack(Node node) {
        if (node != null) {
            node.red = false;
        }
        return node;
    }

    private Node paintToRed(Node node) {
        if (node != null) {
            node.red = true;
        }
        return node;
    }

    private Node rightRotate(Node node) {
        var newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        return newParent;
    }

    private Node leftRotate(Node node) {
        var newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        return newParent;
    }

    private void swapColors(Node nodeOne, Node nodeTwo){
        var temp = nodeOne.red;
        nodeOne.red = nodeTwo.red;
        nodeTwo.red = temp;
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


    public static void main(String[] args) {
        var redBlackTree = new RedBlackTree();
        redBlackTree.insert(40);
        redBlackTree.insert(30);
        redBlackTree.insert(50);
        redBlackTree.insert(60);
        redBlackTree.insert(59);
        redBlackTree.insert(35);
        redBlackTree.insert(33);
        System.out.println(JsonUtils.toJson(redBlackTree.root));
    }
}
