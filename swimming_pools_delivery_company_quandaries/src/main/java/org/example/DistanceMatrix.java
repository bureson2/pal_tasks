package org.example;

import java.util.Arrays;
import java.util.List;

public class DistanceMatrix {
    private final int INF = Integer.MAX_VALUE;
    private int N;
    private int[][] matrix;
    public int[] costs;
    public int minCost = Integer.MAX_VALUE;



    public DistanceMatrix(int N) {
        this.N = N;
        this.matrix = new int[N][N];
        this.costs = new int[N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(matrix[i], INF);
            matrix[i][i] = 0;
        }
    }

    public void calculateDistanceMatrix(List<Street> streets){
        for (Street street : streets) {
            for (Street neighbor : street.neighbors) {
                matrix[street.identifier][neighbor.identifier] = 1;
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (matrix[i][k] != INF && matrix[k][j] != INF && matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }

        calculateCosts();
    }

    private void calculateCosts() {

        for (int i = 0; i < N; i++) {
            int totalCost = 0;
            for (int j = 0; j < N; j++) {
                int cost = matrix[i][j];
                if (cost != INF) {
                    totalCost += cost;
                }
            }
            costs[i] = 2 * totalCost;
            if (costs[i] < minCost){
                minCost = costs[i];
            }

        }

        for (int i = 0; i < N; i++) {
            int totalCost = 0;
            for (int j = 0; j < N; j++) {
                int cost = matrix[j][i];
                if (cost != INF) {
                    totalCost += cost;
                }
            }
            costs[i] += totalCost;
        }
    }

    public void printMatrix() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == INF) {
                    System.out.print("∞ ");
                } else {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
