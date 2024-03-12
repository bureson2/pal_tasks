package org.example.graphs.realization.graph_elements;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    public int identifier;
    public List<Integer> adjencyList;
    public List<Integer> adjencyListWeights;
    public Vertex(int identifier) {
        adjencyList = new ArrayList<>();
        adjencyListWeights = new ArrayList<>();

        this.identifier = identifier;
    }

    public void addNeighbor(int vertex, int weight){
        adjencyList.add(vertex);
        adjencyListWeights.add(weight);
    }
}
