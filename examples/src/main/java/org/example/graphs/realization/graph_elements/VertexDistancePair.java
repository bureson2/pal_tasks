package org.example.graphs.realization.graph_elements;

public class VertexDistancePair implements Comparable<VertexDistancePair> {
    public int vertex;
    public int distance;

    public VertexDistancePair(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    @Override
    public int compareTo(VertexDistancePair other) {
        return Integer.compare(this.distance, other.distance);
    }
}