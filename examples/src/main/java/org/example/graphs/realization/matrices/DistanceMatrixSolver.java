package org.example.graphs.realization.matrices;

import org.example.graphs.realization.graph_elements.Vertex;
import org.example.graphs.realization.graph_elements.VertexDistancePair;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DistanceMatrixSolver {
    private int size;

    public DistanceMatrixSolver(int size) {
        this.size = size;
    }

    public int[][] calculateWithFloydWarshallAlgorhitm(List<Vertex> vertices) {
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            Arrays.fill(matrix[i], Integer.MAX_VALUE);
            matrix[i][i] = 0;
        }

        for (Vertex vertex : vertices) {
            int vertexId = vertex.identifier;
            for (int i = 0; i < vertex.adjencyList.size(); i++) {
                int neighborId = vertex.adjencyList.get(i);
                int weight = vertex.adjencyListWeights.get(i);
                matrix[vertexId][neighborId] = weight;
            }
        }

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (matrix[i][k] != Integer.MAX_VALUE && matrix[k][j] != Integer.MAX_VALUE
                            && matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }

        return matrix;
    }

    public int[][] calculateWithDijkstraAlgorhitm(List<Vertex> vertices) {
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            Arrays.fill(matrix[i], Integer.MAX_VALUE);
            dijkstra(
                    i,
                    matrix[i],
                    vertices
            );
        }

        return matrix;
    }

    private void dijkstra(int source, int[] dist, List<Vertex> vertices) {
        PriorityQueue<VertexDistancePair> pq = new PriorityQueue<>();
        pq.add(new VertexDistancePair(source, 0));
        dist[source] = 0;

        while (!pq.isEmpty()) {
            VertexDistancePair pair = pq.poll();
            int vertex = pair.vertex;
            int distance = pair.distance;

            if (distance > dist[vertex]) {
                continue;
            }

            for (int i = 0; i < vertices.get(vertex).adjencyList.size(); i++) {
                int neighbor = vertices.get(vertex).adjencyList.get(i);
                int weight = vertices.get(vertex).adjencyListWeights.get(i);
                int newDist = dist[vertex] + weight;

                if (newDist < dist[neighbor]) {
                    dist[neighbor] = newDist;
                    pq.add(new VertexDistancePair(neighbor, newDist));
                }
            }
        }
    }
}