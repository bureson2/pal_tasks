package org.example.graphs.minimum_spanning_tree.alghorithms;

import org.example.graphs.realization.graph_elements.Edge;
import org.example.graphs.realization.graph_elements.Graph;
import org.example.graphs.realization.graph_elements.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsMST {
    public static void primMST(Graph graph){
        boolean[] inHeap = new boolean[graph.size];
        ResultSet[] resultSet = new ResultSet[graph.size];
        int[] keys = new int[graph.size];
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(graph.size);

        for (int i = 0; i < graph.size; i++) {
            keys[i] = Integer.MAX_VALUE;
            resultSet[i] = new ResultSet();
        }

        keys[0] = 0;
        priorityQueue.offer(new Edge(0, 0, 0));
        resultSet[0] = new ResultSet();
        resultSet[0].parent = -1;

        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();

            inHeap[edge.destination] = true;

//            LinkedList<Edge> list = graph.vertices[edge.destination];
            List<Integer> neighbors = graph.vertices.get(edge.destination).adjencyList;
            List<Integer> neighborsWeights = graph.vertices.get(edge.destination).adjencyListWeights;
            for (int i = 0; i < neighbors.size(); i++) {
                Integer v = neighbors.get(i);
                Integer weight = neighborsWeights.get(i);

                Vertex vertex = graph.vertices.get(v);
                if (!inHeap[vertex.identifier]) {
                    if (keys[vertex.identifier] > weight) {
                        keys[vertex.identifier] = weight;
                        priorityQueue.offer(new Edge(vertex.identifier, vertex.identifier, keys[vertex.identifier]));
                        resultSet[vertex.identifier].parent = edge.destination;
                        resultSet[vertex.identifier].weight = weight;
                    }
                }
            }
        }

        printMST(resultSet, graph.size);
    }

    private static void printMST(ResultSet[] resultSet, int vertices){
        int totalMinWeight = 0;
        System.out.println("Following are the edges of the constructed MST with PrimJarnik alghorithm:");
        for (int i = 1; i < vertices; i++) {
            System.out.println("Edge: " + i + " - " + resultSet[i].parent +
                    " weight: " + resultSet[i].weight);
            totalMinWeight += resultSet[i].weight;
        }
        System.out.println("Total cost of MST: " + totalMinWeight);

    }
}
