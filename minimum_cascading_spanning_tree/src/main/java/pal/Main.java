package pal;

public class Main {

    public static void main(String args[]) {
        InputParser inputParser = new InputParser();
        inputParser.readLines();

        Graph graph = inputParser.getGraph();
        graph.sortEdges();

        long minimumCascadingSpanningTree = Long.MAX_VALUE;

        for (int vertex = 0; vertex < graph.getNumberOfVertices(); vertex++){

            graph.divideIntoEquivalenceClass(vertex);
            graph.sortEdgesType();
            UnionFind unionFind = new UnionFind(graph.getNumberOfVertices());

            long spannintTreesWeight = graph.findMinimumSpanningTreeInComponent(unionFind);
            long connectionsWeight = graph.findMinimumSpanningTreeOutFromComponent(unionFind);
            long candidateCascadinSpanningTree = spannintTreesWeight + connectionsWeight;

            if(minimumCascadingSpanningTree > candidateCascadinSpanningTree){
                minimumCascadingSpanningTree = candidateCascadinSpanningTree;
            }
            graph.clear();
        }

        System.out.println(minimumCascadingSpanningTree);
    }
}
