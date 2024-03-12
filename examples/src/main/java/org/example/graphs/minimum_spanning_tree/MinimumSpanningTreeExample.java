package org.example.graphs.minimum_spanning_tree;

import org.example.graphs.minimum_spanning_tree.alghorithms.BoruvkaMST;
import org.example.graphs.minimum_spanning_tree.alghorithms.KruskalMST;
import org.example.graphs.minimum_spanning_tree.alghorithms.PrimsMST;
import org.example.graphs.realization.graph_elements.Edge;
import org.example.graphs.realization.graph_elements.Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MinimumSpanningTreeExample {
    public static void main(String[] args) {
        int size = 9;
        List<Edge> edges = new ArrayList<Edge>(
                List.of(new Edge(7, 6, 1),
                        new Edge(8, 2, 2),
                        new Edge(6, 5, 3),
                        new Edge(0, 1, 4),
                        new Edge(2, 5, 5),
                        new Edge(8, 6, 6),
                        new Edge(2, 3, 7),
                        new Edge(7, 8, 13),
                        new Edge(0, 7, 8),
                        new Edge(1, 2, 12),
                        new Edge(3, 4, 9),
                        new Edge(5, 4, 10),
                        new Edge(1, 7, 11),
                        new Edge(3, 5, 14)
                        )
        );

        // EXAMPLE 1 - PrimJarnik alghoritm
        Graph graph = new Graph(size);
        for (Edge edge : edges){
            graph.addEdge(edge.source, edge.destination, edge.weight);
        }
        PrimsMST.primMST(graph);

        // EXAMPLE 2 - Kruskal alghoritm with Union Find
        // Sort the edges in non-decreasing order (increasing with repetition allowed)
        edges.sort(new Comparator<Edge>() {
            @Override public int compare(Edge o1, Edge o2)
            {
                return o1.weight - o2.weight;
            }
        });
        KruskalMST.kruskalMST(size, edges);

        // EXAMPLE 3 - Boruvka alghorithm
        BoruvkaMST.boruvkaMST(graph, edges);


    }
}

