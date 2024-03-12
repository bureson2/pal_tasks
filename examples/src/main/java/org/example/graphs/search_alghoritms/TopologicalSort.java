package org.example.graphs.search_alghoritms;

import org.example.graphs.realization.graph_elements.Graph;
import org.example.graphs.realization.graph_elements.Vertex;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {
    public static void topologicalSort(Graph graph){
        Stack<Integer> stack = new Stack<>();

        // Mark all the vertices as not visited
        boolean[] visited = new boolean[graph.size];

        // Call the recursive dfs function to store
        // Topological Sort starting from all vertices one by one

        for (int i = 0; i < graph.size; i++){
            if (!visited[i]){
                dfsWalk(graph, i, visited, stack);
            }
        }

        while (!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }

    }

    private static void dfsWalk(Graph graph, int vertex, boolean[] visited, Stack<Integer> stack){
        visited[vertex] = true;

        List<Integer> neighbors = graph.vertices.get(vertex).adjencyList;
        for (Integer neighbor : neighbors){
            if(!visited[neighbor]) {
                dfsWalk(graph, neighbor, visited, stack);
            }
        }

        stack.push(vertex);
    }
}
