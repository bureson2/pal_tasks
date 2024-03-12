package org.example;

class Node implements Comparable<Node> {
    public int componentId;
    public int distance;

    public Node(int componentId, int distance) {
        this.componentId = componentId;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.distance, other.distance);
    }
}

