package org.example.graphs.search_alghoritms;

import org.example.graphs.realization.graph_elements.Graph;

import java.util.LinkedList;
import java.util.List;

public class SearchAlgorhitms {
    public static void breadthFirstSearch(Graph graph, int vertex){
        // Mark all the vertices as not visited(By default set as false)
        boolean visited[] = new boolean[graph.size];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        visited[vertex] = true;
        queue.add(vertex);

        while (!queue.isEmpty()) {

            // Dequeue a vertex from queue and print it
            vertex = queue.poll();
            System.out.print(vertex + " ");

            // Get all adjacent vertices of the dequeued vertex
            // If an adjacent has not been visited, then mark it visited and enqueue it
            List<Integer> vertexNeighbors = graph.vertices.get(vertex).adjencyList;
            for (int neighbor : vertexNeighbors) {
                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void depthFirstSearch(Graph graph, int vertex){
        // Mark all the vertices as not visited(By default set as false)
        boolean[] visited = new boolean[graph.size];

        dfsWalk(graph, vertex, visited);

    }

    private static void dfsWalk(Graph graph, int vertex, boolean[] visited){
        visited[vertex] = true;
        System.out.print(vertex + " ");

        // Get all adjacent vertices of the dequeued vertex
        // If an adjacent has not been visited, then mark it visited and enqueue it
        List<Integer> vertexNeighbors = graph.vertices.get(vertex).adjencyList;
        for (int neighbor : vertexNeighbors) {
            if(!visited[neighbor]){
                dfsWalk(graph, neighbor, visited);
            }
        }
    }
}
