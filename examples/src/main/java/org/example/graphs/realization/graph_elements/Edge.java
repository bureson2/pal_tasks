package org.example.graphs.realization.graph_elements;

public class Edge implements Comparable<Edge> {
    public int source;
    public int destination;
    public int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Edge(int source, int destination) {
        this.source = source;
        this.destination = destination;
        this.weight = 0;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}
