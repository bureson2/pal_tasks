package pal;

class UnionFind {
    private int[] parent;

    public UnionFind(int numberOfVertices) {
        parent = new int[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            parent[i] = i;
        }
    }

    public int find(int vertex) {
        if (parent[vertex] == vertex) {
            return vertex;
        }
        parent[vertex] = find(parent[vertex]);
        return parent[vertex];
    }

    public void union(int vertex1, int vertex2) {
        int rootX = find(vertex1);
        int rootY = find(vertex2);

        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }

    public boolean connected(int vertex1, int vertex2) {
        return find(vertex1) == find(vertex2);
    }
}







