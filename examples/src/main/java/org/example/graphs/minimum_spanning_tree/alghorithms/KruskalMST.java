package org.example.graphs.minimum_spanning_tree.alghorithms;

import org.example.graphs.minimum_spanning_tree.union_find.Subset;
import org.example.graphs.minimum_spanning_tree.union_find.UnionFind;
import org.example.graphs.realization.graph_elements.Edge;
import org.example.graphs.realization.graph_elements.Graph;

import java.util.List;

public class KruskalMST {
    public static void kruskalMST(int size, List<Edge> edges){
        int numberOfEdges = 0;
        int currentEdge = 0;

        Subset subsets[] = new Subset[size];
        Edge results[] = new Edge[size];

        // Create V subsets with single elements
        for (int i = 0; i < size; i++) {
            subsets[i] = new Subset(i, 0);
        }

        // Number of edges to be taken is equal to V-1
        while (numberOfEdges < size - 1) {
            // Pick the smallest edge. And increment the index for next iteration
            Edge nextEdge = edges.get(currentEdge);
            int vertexARoot = UnionFind.findRoot(subsets, nextEdge.source);
            int vertexBRoot = UnionFind.findRoot(subsets, nextEdge.destination);

            // If including this edge doesn't cause cycle, include it in result
            // and increment the index of result for next edge
            if (vertexARoot != vertexBRoot) {
                results[numberOfEdges] = nextEdge;
                UnionFind.union(subsets, vertexARoot, vertexBRoot);
                numberOfEdges++;
            }

            currentEdge++;
        }

        printMST(numberOfEdges, results);

    }

    private static void printMST(int numberOfEdges, Edge[] results){
        System.out.println(
                "Following are the edges of the constructed MST with Kruskal alghortihm:");
        int minCost = 0;
        for (int i = 0; i < numberOfEdges; i++) {
            System.out.println("Edge: " + results[i].source + " - "
                    + results[i].destination + " weight: "
                    + results[i].weight);
            minCost += results[i].weight;
        }
        System.out.println("Total cost of MST: " + minCost);
    }
}
