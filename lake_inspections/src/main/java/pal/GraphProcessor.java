package pal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphProcessor {
    private Map<Integer, Component> components;
    private Map<Integer, Boolean> visited;
    private int maxNumberOfLakes;
    private int realMax;

    public GraphProcessor(Map<Integer, Component> components) {
        this.components = components;
        this.visited = new HashMap<>();
        this.realMax = 0;

        for (Integer componentId : components.keySet()){
            visited.put(componentId, false);
        }
    }

    public int findMaxLakesCount(int componentId, int DOcomponentID) {
        this.maxNumberOfLakes = 0;
        this.realMax = 0;

        dfs(componentId, 0, DOcomponentID);

        return realMax;
    }

    private void dfs(int componentId, int currentSum, int DOcomponentID) {

        visited.put(componentId, true);
        currentSum += components.get(componentId).numberOfLakes;

//        maxNumberOfLakes = Math.max(maxNumberOfLakes, currentSum);

        List<Component> neighbors = components.get(componentId).neighbors;
        for (Component neighbor : neighbors) {
            if (!visited.get(neighbor.id)) {
                if (componentId != DOcomponentID) {
                    if (neighbor.outOfTheComponent == 1) {
                        visited.put(neighbor.id, true);
                    }

                    if (neighbor.id == DOcomponentID){
                        int currenEndingSum = currentSum + neighbor.numberOfLakes;
                        realMax = Math.max(realMax, currenEndingSum);

                    } else {
                        dfs(neighbor.id, currentSum, DOcomponentID);
                    }
                }
            }
//            if (DOVisited) break;
        }

        // Backtrack
        visited.put(componentId, false);
    }
}
