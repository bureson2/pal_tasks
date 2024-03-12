package org.example.graphs.minimum_spanning_tree.union_find;

public class UnionFind {
    public static void union(Subset[] subsets, int vertexA, int vertexB){
        int rootA = findRoot(subsets, vertexA);
        int rootB = findRoot(subsets, vertexB);

        if (subsets[rootB].rank < subsets[  rootA].rank) {
            subsets[rootB].parent = rootA;
        }
        else if (subsets[rootA].rank
                < subsets[rootB].rank) {
            subsets[rootA].parent = rootB;
        }
        else {
            subsets[rootB].parent = rootA;
            subsets[rootA].rank++;
        }
    }

    // Function to find parent of a set
    public static int findRoot(Subset[] subsets, int vertex){
        if (subsets[vertex].parent == vertex)
            return subsets[vertex].parent;

        subsets[vertex].parent
                = findRoot(subsets, subsets[vertex].parent);
        return subsets[vertex].parent;
    }
}
