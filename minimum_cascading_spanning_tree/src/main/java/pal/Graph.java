package pal;

import java.util.*;

public class Graph {
    private final int numberOfVertices;
    private final List<List<Integer>> adjencyList;
    private final List<Integer> equivalenceClasses;
    private final List<Integer> components;
    private final List<Edge> edges;
    private final List<Edge> inComponentEdges;
    private final List<Edge> outOfComponentEdges;
    private Map<Integer, Boolean> componentsMap = new HashMap<>();

    public Graph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;

        this.edges = new LinkedList<>();
        this.adjencyList = new ArrayList<>(numberOfVertices);
        this.equivalenceClasses = new ArrayList<>(numberOfVertices);
        this.components = new ArrayList<>(numberOfVertices);

        this.inComponentEdges = new ArrayList<>();
        this.outOfComponentEdges = new ArrayList<>();

        for (int i = 0; i < numberOfVertices; i++) {
            adjencyList.add(new ArrayList<>());
            equivalenceClasses.add(0);
            components.add(0);
        }
    }

    public void addEdge(int source, int destination, int weight){
        Edge firstEdge = new Edge(source, destination, weight);
        adjencyList.get(source).add(destination);
        adjencyList.get(destination).add(source);
        edges.add(firstEdge);
    }

//    BFS alghorithm for calculating distances from currently serched vertex
    public void divideIntoEquivalenceClass(int startVertex) {
        boolean[] visited = new boolean[numberOfVertices];
        Queue<Integer> queue = new LinkedList<>();
        visited[startVertex] = true;
        queue.add(startVertex);

//        Depth mean distance from start vertex
        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int currentVertex = queue.poll();
                equivalenceClasses.set(currentVertex, depth);

                for (Integer vertex : adjencyList.get(currentVertex)) {
                    if (!visited[vertex]) {
                        visited[vertex] = true;
                        queue.add(vertex);
                    }
                }
            }
            depth++;
        }
    }

    public long findMinimumSpanningTreeInComponent(UnionFind unionFind){
        long weight = 0;

        for (Edge edge : inComponentEdges){
            int sourceVertex = edge.getSource();
            int destinationVertex = edge.getDestination();

            if (!unionFind.connected(sourceVertex, destinationVertex)) {
                unionFind.union(sourceVertex, destinationVertex);
                weight+=edge.getWeight();
            }
        }
        return weight;
    }

    public long findMinimumSpanningTreeOutFromComponent(UnionFind unionFind){
        long weight = 0;

        for (Edge edge : outOfComponentEdges){
            int sourceVertex = edge.getSource();
            int destinationVertex = edge.getDestination();

            if (!unionFind.connected(sourceVertex, destinationVertex)) {
                unionFind.union(sourceVertex, destinationVertex);
                weight+=edge.getWeight();
            }
        }
        return weight;
    }

    public void sortEdgesType(){
        for(Edge edge : edges){
            if (equivalenceClasses.get(edge.getSource()) == equivalenceClasses.get(edge.getDestination())){
                inComponentEdges.add(edge);
            } else {
                outOfComponentEdges.add(edge);
            }
        }
        sortEdgesByEquivalenceClass();
    }

    public void sortEdgesByEquivalenceClass() {
        outOfComponentEdges.sort((edge1, edge2) -> {
            int dist1 = equivalenceClasses.get(edge1.getSource()) + equivalenceClasses.get(edge1.getDestination());
            int dist2 = equivalenceClasses.get(edge2.getSource()) + equivalenceClasses.get(edge2.getDestination());

            if (dist1 == dist2) {
                return edge1.getWeight() - edge2.getWeight();
            }

            return dist1 - dist2;

        });
    }

    public void sortEdges() {
        Collections.sort(edges);
    }

    public void clear(){
        for (int i = 0; i < numberOfVertices; i++) {
            components.set(i, 0);
        }
        inComponentEdges.clear();
        outOfComponentEdges.clear();
        componentsMap.clear();
    }

    //    BASIC GETTERS SECTION
//    ------------------------------------------------------------------------------------
    public int getNumberOfVertices() { return numberOfVertices; }
}
