package pal;

import java.util.Arrays;
import java.util.List;

import java.util.*;

public class SCC {
    private List<List<Integer>> adjencyList;
    private int[] low;
    private int[] ids;
    private boolean[] onStack;
    private Deque<Integer> stack;
    private int id;
    private int N;
    public Map<Integer, Component> components;

    public SCC(List<List<Integer>> adjencyList, int N) {
        this.adjencyList = adjencyList;
        this.N = N;
        components = new HashMap<>();
    }

    public int[] findSCCs() {
        int n = N;
        low = new int[n];
        ids = new int[n];
        onStack = new boolean[n];
        stack = new ArrayDeque<>();
        Arrays.fill(ids, -1);

        for (int i = 0; i < n; i++) {
            if (ids[i] == -1) {
                dfs(i);
            }
        }

        return low;
    }

    private void dfs(int at) {
        stack.push(at);
        onStack[at] = true;
        ids[at] = low[at] = id++;

        for (int to : adjencyList.get(at)) {
            if (ids[to] == -1) dfs(to);
            if (onStack[to]) low[at] = Math.min(low[at], low[to]);
        }

        if (ids[at] == low[at]) {

            if (!components.containsKey(ids[at])){
                Component component = new Component(ids[at]);
                components.put(ids[at], component);
            }

            for (int node = stack.pop(); ; node = stack.pop()) {
                onStack[node] = false;
                low[node] = ids[at];
                if (node == at) break;
            }
        }
    }
}
