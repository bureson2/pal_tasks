package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SCC {
    private static int time;
    private static int sccCount;
    private static int[] low;
    private static boolean[] onStack;
    private static int[] scc;
    private static int N;
    private static Stack<Integer> stack;

    public static int[] divideIntoSCC(List<Street> streets, int n){
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
                dfs(streets, i);
            }
        }

        return scc;
    }

    private static void dfs(List<Street> streets, int u ) {
        low[u] = time++;
        scc[u] = low[u];
        stack.push(u);
        onStack[u] = true;

        for (Street v : streets.get(u).neighbors) {
            if (scc[v.identifier] == -1) {
                dfs(streets, v.identifier);
                low[u] = Math.min(low[u], low[v.identifier]);
            } else if (onStack[v.identifier]) {
                low[u] = Math.min(low[u], scc[v.identifier]);
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
