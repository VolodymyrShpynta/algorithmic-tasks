package com.vshpynta.graph;

import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;


@FieldDefaults(level = PRIVATE)
public class Graph<T> {

    final Map<T, Set<T>> adjacencySets = new HashMap<>();


    public void addVertex(T node) {
        adjacencySets.putIfAbsent(node, new HashSet<>());
    }

    public void addEdge(T node1, T node2) {
        adjacencySets.get(node1).add(node2);
        adjacencySets.get(node2).add(node1);
    }

    public void showConnections() {
        adjacencySets.forEach((node, edges) -> {
            System.out.printf("%s --> %s %n", node, edges);
        });
    }


    public static void main(String[] args) {
        var graph = new Graph<Integer>();
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);

        graph.addEdge(3, 1);
        graph.addEdge(3, 4);
        graph.addEdge(4, 2);
        graph.addEdge(4, 5);
        graph.addEdge(1, 2);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(6, 5);

        graph.showConnections();
    }
}
