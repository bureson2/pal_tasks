package org.example.graphs.realization;

import org.example.graphs.realization.graph_elements.Graph;
import org.example.graphs.realization.matrices.AdjencyMatrix;
import org.example.graphs.realization.matrices.IncidenceMatrix;
import org.example.graphs.realization.matrices.LaplacianMatrix;
import org.example.printer.MatrixPrinter;

public class GraphRealizationExample {
    public static void main(String[] args) {

        // EXAMPLE 1 - Adjency matrix
        AdjencyMatrix adjencyMatrix = new AdjencyMatrix(5);
        adjencyMatrix.addConnection(1,2);
        adjencyMatrix.addConnection(1,3);
        adjencyMatrix.addConnection(3,2);
        adjencyMatrix.addConnection(4,1);
        adjencyMatrix.addConnection(4,2);
        adjencyMatrix.addConnection(4,3);
        adjencyMatrix.addConnection(5,2);
        adjencyMatrix.addConnection(5,3);

        System.out.println("Adjency matrix for oriented graph:");
        MatrixPrinter.printArrayMatrix(adjencyMatrix.orientedMatrix);
        System.out.println("Adjency matrix for unoriented graph:");
        MatrixPrinter.printArrayMatrix(adjencyMatrix.unorientedMatrix);

        // EXAMPLE 2 - Laplacian Matrix
        LaplacianMatrix laplacianMatrix = new LaplacianMatrix(5);
        laplacianMatrix.addConnection(1,2);
        laplacianMatrix.addConnection(1,3);
        laplacianMatrix.addConnection(3,2);
        laplacianMatrix.addConnection(4,1);
        laplacianMatrix.addConnection(4,2);
        laplacianMatrix.addConnection(4,3);
        laplacianMatrix.addConnection(5,2);
        laplacianMatrix.addConnection(5,3);
        System.out.println("Laplacian matrix:");
        MatrixPrinter.printArrayMatrix(laplacianMatrix.matrix);

        // EXAMPLE 3 - Distance Matrix
        // Vertex in graph use AdjencyList and dijkstra variant use Priority Queue
        // 2 implementation: FLOYD WARSHALL or DIJKSTRA ALGORITHM
        Graph graph = new Graph(8);
        graph.addEdge(0,6,4);
        graph.addEdge(1,5,2);
        graph.addEdge(2,5,1);
        graph.addEdge(5,6,5);
        graph.addEdge(6,7,4);
        graph.addEdge(7,4,7);
        graph.addEdge(7,3,1);
        System.out.println("Distance matrix:");
        MatrixPrinter.printArrayMatrix(graph.distanceMatrix());

        IncidenceMatrix incidenceMatrix = new IncidenceMatrix(5,8);
        incidenceMatrix.addConnection(1,5,3);
        incidenceMatrix.addConnection(2,4,3);
        incidenceMatrix.addConnection(3,4,1);
        incidenceMatrix.addConnection(4,1,2);
        incidenceMatrix.addConnection(5,2,5);
        incidenceMatrix.addConnection(6,3,2);
        incidenceMatrix.addConnection(7,1,3);
        incidenceMatrix.addConnection(8,4,2);
        System.out.println("Incidence matrix");
        MatrixPrinter.printArrayMatrix(incidenceMatrix.matrix);

    }
}