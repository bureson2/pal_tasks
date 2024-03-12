package org.example.graphs.realization.graph_elements;

import org.example.graphs.realization.matrices.DistanceMatrixSolver;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public int size;
    public List<Vertex> vertices;

    public Graph(int size) {
        this.vertices = new ArrayList<>();
        this.size = size;

        for (int i = 0; i < size; i++){
            this.vertices.add(new Vertex(i));
        }
    }

    public void addEdge(int vertexA, int vertexB, int weight){
        vertices.get(vertexA).addNeighbor(vertexB, weight);
        vertices.get(vertexB).addNeighbor(vertexA, weight);
    }

    public void addEdge(int vertexA, int vertexB){
        vertices.get(vertexA).addNeighbor(vertexB, 0);
        vertices.get(vertexB).addNeighbor(vertexA, 0);
    }

    public void addOrientedEge(int source, int destination){
        vertices.get(source).addNeighbor(destination, 0);
    }

    public int[][] distanceMatrix(){
        DistanceMatrixSolver solver = new DistanceMatrixSolver(vertices.size());

//        EXAMPLE 1 FLOYD WARSHALL ALGORITHM
//        return solver.calculateWithFloydWarshallAlgorhitm(vertices);

//        EXAMPLE 2 DIJKSTRA ALGORITHM
        return solver.calculateWithDijkstraAlgorhitm(vertices);
    }
}
