package com.vshpynta.tree;

import java.util.HashMap;
import java.util.Map;

//208: https://leetcode.com/problems/implement-trie-prefix-tree/description/
public class Trie {

    Node root;

    public Trie() {
        this.root = new Node((char) 0);
    }

    public void insert(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        var current = root;
        for (char c : word.toCharArray()) {
            var node = current.findChildBy(c);
            if (node == null) {
                node = new Node(c);
                current.addChild(node);
            }
            current = node;
        }
        current.endOfWord = true;
    }

    public boolean search(String word) {
        var lastSymbolNode = findNodeForLastSymbol(word);
        return lastSymbolNode != null && lastSymbolNode.endOfWord;
    }

    public boolean startsWith(String prefix) {
        var lastSymbolNode = findNodeForLastSymbol(prefix);
        return lastSymbolNode != null;
    }

    private Node findNodeForLastSymbol(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }

        var current = root;
        for (char c : str.toCharArray()) {
            var node = current.findChildBy(c);
            if (node == null) {
                return null;
            }
            current = node;
        }
        return current;
    }

    private static class Node {
        boolean endOfWord;
        char value;
        Map<Character, Node> children;

        public Node(char value) {
            this.value = value;
        }

        public void addChild(Node node) {
            if (children == null) {
                children = new HashMap<>();
            }
            children.put(node.value, node);
        }

        public Node findChildBy(char value) {
            if (children == null) {
                return null;
            }

            return children.get(value);
        }
    }

    public static void main(String[] args) {
        var trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}
