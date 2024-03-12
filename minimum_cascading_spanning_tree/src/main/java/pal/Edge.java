package pal;

import java.util.Objects;

public class Edge implements Comparable<Edge>{
    private final int destination;
    private final int source;
    private final int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int getSource() { return source; }

    public int getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        if (destination == edge.destination && source == edge.source && weight == edge.weight){
            return true;
        } else if (destination == edge.source && source == edge.destination && weight == edge.weight){
            return true;
        } else {
          return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, source, weight);
    }
}
