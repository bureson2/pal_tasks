package org.example.graphs.strongly_connected_components;

import org.example.graphs.realization.graph_elements.Edge;
import org.example.graphs.realization.graph_elements.Graph;
import org.example.printer.SCCPrinter;

import java.util.ArrayList;
import java.util.List;

public class StronglyConnectedComponentExample {
    public static void main(String[] args) {
        Graph graph = prepareGraph();
        Graph reversedOrientedGraph = prepareGraphWithReverseOrientation();

        // EXAMPLE 1 - KOSARAJU SHARIR ALGHORITHM
        List<List<Integer>> stronglyConnectedComponents1 = KosarajuSharirSCC.getSCC(graph, reversedOrientedGraph);
        SCCPrinter.printSCC(stronglyConnectedComponents1);

        // EXAMPLE 2 - TARJAN ALGHORITHM
        List<List<Integer>> stronglyConnectedComponents2 = TarjanSCC.getSCC(graph);
        SCCPrinter.printSCC(stronglyConnectedComponents2);

    }

    private static Graph prepareGraph(){
        int size = 8;

        Graph graph = new Graph(size);
        for (Edge edge : edges()){
            graph.addOrientedEge(edge.source, edge.destination);
        }

        return graph;
    }

    private static Graph prepareGraphWithReverseOrientation(){
        int size = 8;

        Graph graph = new Graph(size);
        for (Edge edge : edges()){
            graph.addOrientedEge(edge.destination, edge.source);
        }

        return graph;
    }

    private static List<Edge> edges(){
        return new ArrayList<Edge>(
                List.of(new Edge(0, 1),
                        new Edge(1, 2),
                        new Edge(1, 4),
                        new Edge(1, 5),
                        new Edge(2, 3),
                        new Edge(2, 6),
                        new Edge(3, 2),
                        new Edge(3, 7),
                        new Edge(4, 0),
                        new Edge(4, 5),
                        new Edge(5, 6),
                        new Edge(6, 5),
                        new Edge(7, 6),
                        new Edge(7, 3)
                )
        );
    }
}
