package org.example.graphs.strongly_connected_components;

import org.example.graphs.realization.graph_elements.Graph;
import org.example.graphs.realization.graph_elements.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class TarjanSCC {

    private static int index = 0;
    private static final Stack<Integer> stack = new Stack<>();
    private static final List<List<Integer>> stronglyConnectedComponents = new ArrayList<>();
    private static int[] indices;
    private static int[] lowlinks;
    private static boolean[] onStack;

    public static List<List<Integer>> getSCC(Graph graph){
        indices = new int[graph.size];
        lowlinks = new int[graph.size];
        onStack = new boolean[graph.size];

        Arrays.fill(indices, -1);

        for (Vertex vertex : graph.vertices){
            if (indices[vertex.identifier] == -1){
                dfs(vertex.identifier, graph);
            }
        }

        return stronglyConnectedComponents;
    }

    private static void dfs(int vertex, Graph graph){
        indices[vertex] = lowlinks[vertex] = index++;
        stack.push(vertex);
        onStack[vertex] = true;

        List<Integer> neighbors = graph.vertices.get(vertex).adjencyList;
        for (int neighbor : neighbors){
            if (indices[neighbor] == -1){
                dfs(neighbor, graph);
                lowlinks[vertex] = Math.min(lowlinks[vertex], lowlinks[neighbor]);
            } else if (onStack[neighbor]){
                lowlinks[vertex] = Math.min(lowlinks[vertex], indices[neighbor]);
            }
        }

        if (lowlinks[vertex] == indices[vertex]){
            List<Integer> scc = new ArrayList<>();
            int currentVertex;

            do {
                currentVertex = stack.pop();
                onStack[currentVertex] = false;
                scc.add(currentVertex);
            } while (vertex != currentVertex);

            stronglyConnectedComponents.add(scc);
        }
    }
}
