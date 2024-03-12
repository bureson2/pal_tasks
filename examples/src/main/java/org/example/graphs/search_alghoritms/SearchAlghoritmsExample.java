package org.example.graphs.search_alghoritms;

import org.example.graphs.realization.graph_elements.Graph;

// JUST BASIC IMPLEMENTATION WITHOUT CLOSING OR STACK IN DFS
// DFS with stack is used in topological sort
public class SearchAlghoritmsExample {
    public static void main(String[] args) {
        Graph graph = new Graph(7);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);

        System.out.println("BFS:");
        SearchAlgorhitms.breadthFirstSearch(graph, 0);
        System.out.println();
        System.out.println("DFS");
        SearchAlgorhitms.depthFirstSearch(graph, 0);
        System.out.println();
        System.out.println("TopologicalSort");
        TopologicalSort.topologicalSort(graph);
    }
}