package org.example.graphs.realization.matrices;

import java.util.Arrays;

public class LaplacianMatrix {
    public int[][] matrix;

    public LaplacianMatrix(int size) {
        this.matrix = new int[size][size];

        for (int[] ar : this.matrix){
            Arrays.fill(ar, 0);
        }
    }

    public void addConnection(int vertexA, int vertexB){
        matrix[vertexA-1][vertexB-1] = -1;
        matrix[vertexB-1][vertexA-1] = -1;
        matrix[vertexA-1][vertexA-1] = matrix[vertexA-1][vertexA-1] + 1;
        matrix[vertexB-1][vertexB-1] = matrix[vertexB-1][vertexB-1] + 1;
    }
}
