package org.example;

import java.util.HashMap;
import java.util.Map;

public class GraphProcessor {
    private Map<Integer, Component> components;
    private Map<Integer, Boolean> visited;
    private int maxSafetyStationSum;

    public GraphProcessor(Map<Integer, Component> components) {
        this.components = components;
        this.visited = new HashMap<>();

        for (Integer componentId : components.keySet()){
            visited.put(componentId, false);
        }

        this.maxSafetyStationSum = 0;
    }

    public int findMaxSafetyStationSum() {
        for (Integer id : components.keySet()) {

            if (!visited.get(id)) {
                dfs(id, 0);
            }
        }
        return maxSafetyStationSum;
    }

    private void dfs(int componentId, int currentSum) {

        visited.put(componentId, true);
        currentSum += components.get(componentId).safetyStation;

        maxSafetyStationSum = Math.max(maxSafetyStationSum, currentSum);

        Map<Integer, Component> neighbors = components.get(componentId).neighbors;
        for (Component neighbor : neighbors.values()) {
            if (!visited.get(neighbor.id)) {
                dfs(neighbor.id, currentSum);
            }
        }

        // Backtrack
        visited.put(componentId, false);
    }
}
