package org.example;

import java.util.*;

public class SCC {
    private static int time;
    public static int sccCount;
    private static int[] low;
    private static boolean[] onStack;
    private static int[] scc;
    private static int N;
    private static Stack<Integer> stack;

    public static int[] divideIntoSCC(List<Factory> factories, int n){
        time = 0;
        sccCount = 0;
        N = n;
        low = new int[N];
        onStack = new boolean[N];
        scc = new int[N];
        stack = new Stack<>();
        Arrays.fill(scc, -1); // Initialize SCC array with -1

        for (int i = 0; i < N; i++) {
            if (scc[i] == -1) {
                dfs(factories, i);
            }
        }

        return scc;
    }

    private static void dfs(List<Factory> factories, int u ) {
        low[u] = time++;
        scc[u] = low[u];
        stack.push(u);
        onStack[u] = true;

        for (Factory v : factories.get(u).directNeighbor) {
            if (scc[v.indetifier] == -1) {
                dfs(factories, v.indetifier);
                low[u] = Math.min(low[u], low[v.indetifier]);
            } else if (onStack[v.indetifier]) {
                low[u] = Math.min(low[u], scc[v.indetifier]);
            }
        }

        if (low[u] == scc[u]) {
            while (!stack.isEmpty()) {
                int v = stack.pop();
                onStack[v] = false;
                scc[v] = sccCount;
                if (v == u) break;
            }
            sccCount++;
        }
    }
}
