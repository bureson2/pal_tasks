package org.example.graphs.realization.matrices;

public class AdjencyMatrix {
    public int[][] orientedMatrix;
    public int[][] unorientedMatrix;

    public AdjencyMatrix(int numberOfVertices) {
        this.orientedMatrix = new int[numberOfVertices][numberOfVertices];
        this.unorientedMatrix = new int[numberOfVertices][numberOfVertices];
    }

    public void addConnection(int source, int destination){
        addConnectionToOrientedGraph(source, destination);
        addConnectionTUnorientedGraph(source, destination);
    }

    public void addConnectionToOrientedGraph(int source, int destination){
        orientedMatrix[source-1][destination-1] = 1;
    }

    public void addConnectionTUnorientedGraph(int vertexA, int vertexB){
        unorientedMatrix[vertexA-1][vertexB-1] = 1;
        unorientedMatrix[vertexB-1][vertexA-1] = 1;
    }

}
