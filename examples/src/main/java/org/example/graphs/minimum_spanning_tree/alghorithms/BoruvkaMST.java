package org.example.graphs.minimum_spanning_tree.alghorithms;

import org.example.graphs.realization.graph_elements.Edge;
import org.example.graphs.realization.graph_elements.Graph;

import java.util.Arrays;
import java.util.List;

public class BoruvkaMST {

    public static void boruvkaMST(Graph graph, List<Edge> edges){
        // Array to store the cheapest edge of each component
        int[] cheapest = new int[graph.size];
        // Array to store the component to which each vertex belongs
        int[] components = new int[graph.size];
        int minCost = 0;

        // Initially, each vertex is in its own component
        for (int i = 0; i < graph.size; i++) {
            components[i] = i;
        }

        // Initially, the number of components is equal to the number of vertices
        int numComponents = graph.size;
        System.out.println(
                "Following are the edges of the constructed MST with Boruvka alghortihm:");

        // The algorithm continues until all vertices are connected (i.e., one component remains)
        while (numComponents > 1) {
            // Reset the cheapest array for the next iteration
            Arrays.fill(cheapest, -1);

            // Iterate over all vertices to find the cheapest edge for each component
            for (int i = 0; i < graph.size; i++) {
                for (Integer edgeIndex : graph.vertices.get(i).adjencyList) {
                    Edge edge = edges.get(edgeIndex);
                    // Find the components of the vertices connected by the edge
                    int comp1 = find(components, edge.source);
                    int comp2 = find(components, edge.destination);

                    // If the edge connects two different components
                    // Update the cheapest edge for each component if necessary
                    if (comp1 != comp2) {
                        if (cheapest[comp1] == -1 || edge.weight < edges.get(cheapest[comp1]).weight) {
                            cheapest[comp1] = edgeIndex;
                        }
                        if (cheapest[comp2] == -1 || edge.weight < edges.get(cheapest[comp2]).weight) {
                            cheapest[comp2] = edgeIndex;
                        }
                    }
                }
            }

            // Connect the components using the cheapest edges found
            for (int i = 0; i < graph.size; i++) {
                // If the edge connects two different components
                if (cheapest[i] != -1) {
                    Edge edge = edges.get(cheapest[i]);
                    int comp1 = find(components, edge.source);
                    int comp2 = find(components, edge.destination);

                    if (comp1 != comp2) {
                        numComponents--;
                        System.out.println("Edge: " + edge.source + " - " + edge.destination +
                                " weight: " + edge.weight);
                        minCost+=edge.weight;
                        union(components, comp1, comp2);
                    }
                }
            }


            System.out.println("Total cost of MST: " + minCost);
        }
    }

// ------------------ EASIER IMPLEMENTAION OF UNION FIND ------------------

    // Method to find the representative of a component
    private static int find(int[] component, int i) {
        // Path compression for efficiency
        if (component[i] != i) {
            component[i] = find(component, component[i]);
        }
        return component[i];
    }

    // Method to union (merge) two components
    private static void union(int[] component, int i, int j) {
        int comp1 = find(component, i);
        int comp2 = find(component, j);
        // Assigning the representative of one component to the other
        component[comp1] = comp2;
    }

}
