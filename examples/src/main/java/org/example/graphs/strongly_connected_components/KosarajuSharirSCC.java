package org.example.graphs.strongly_connected_components;

import org.example.graphs.realization.graph_elements.Graph;
import org.example.graphs.realization.graph_elements.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KosarajuSharirSCC {
    public static List<List<Integer>> getSCC(Graph graph, Graph reversedGraph){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.size];

        for (Vertex vertex : graph.vertices){
            if (!visited[vertex.identifier]){
                firstDFS(vertex.identifier, visited, stack, graph);
            }
        }

        visited = new boolean[graph.size];

        List<List<Integer>> stronglyConnectedComponents = new ArrayList<>();
        while (!stack.isEmpty()){
            int vertex = stack.pop();

            if (!visited[vertex]){
                List<Integer> scc = new ArrayList<>();
                secondDFS(vertex, visited, scc, reversedGraph);
                stronglyConnectedComponents.add(scc);
            }
        }

        return stronglyConnectedComponents;
    }

    private static void firstDFS(int vertex, boolean[] visited, Stack<Integer> stack, Graph graph){
        visited[vertex] = true;

        for (int neighbor : graph.vertices.get(vertex).adjencyList){
            if (!visited[neighbor]) {
                firstDFS(neighbor, visited, stack, graph);
            }
        }

        stack.push(vertex);
    }

    private static void secondDFS(int vertex, boolean[] visited, List<Integer> scc, Graph graph){
        visited[vertex] = true;
        scc.add(vertex);

        for (int neighbor : graph.vertices.get(vertex).adjencyList){
            if (!visited[neighbor]) {
                secondDFS(neighbor, visited, scc, graph);
            }
        }

    }
}
