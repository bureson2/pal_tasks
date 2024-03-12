package org.example.graphs.realization.matrices;

public class IncidenceMatrix {
    public int[][] matrix;

    public IncidenceMatrix(int numberOfEdges, int numberOfVertices) {
        this.matrix = new int[numberOfEdges][numberOfVertices];
    }

    public void addConnection(int edge, int source, int destination){
        matrix[source-1][edge-1] = -1;
        matrix[destination-1][edge-1] = 1;
    }
}
