package pal;


import java.io.IOException;
import java.util.*;

public class Main {

    // SCC = Component

    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader();
        reader.readInput();

        List<List<Integer>> adjencyList = reader.adjencyList;
        int LA = reader.LA;
        int DO = reader.DO;

        SCC sccDivider = new SCC(adjencyList, LA);
        int[] scc = sccDivider.findSCCs();

        Map<Integer, Component> components = sccDivider.components;

        int DOscc = scc[DO]; // Dock componentID
        for (int i = 0; i < LA; i++) {

            // find potential starters
            int lakeSCC = scc[i];

            // connect components
            Component lakeComponent = components.get(lakeSCC);
            lakeComponent.increaseNumberOfLakes();
            for (int neighbor : adjencyList.get(i)) {
                int neighborSCC = scc[neighbor];
                if (lakeSCC != neighborSCC) {
                    lakeComponent.addNeighbor(components.get(neighborSCC));

                }
            }
        }

        // Find potential started components
        Set<Integer> potentialStarterComponents = new HashSet<>();
        for (Component component : components.values()) {
            for (Component neighbor : component.neighbors) {
                if (neighbor.id == DOscc) {
                    potentialStarterComponents.add(component.id);
                }
                component.increaseOutOfTheComponent();
                neighbor.increaseOutOfTheComponent();
            }
        }

        GraphProcessor graphProcessor = new GraphProcessor(components);
        int maxLakes = 0;

        for (int componentId : potentialStarterComponents) {
            if (components.get(componentId).outOfTheComponent > 1){
                int tmpMax = graphProcessor.findMaxLakesCount(componentId, DOscc);
                maxLakes = Math.max(maxLakes, tmpMax);
            }
        }


        System.out.println(maxLakes);
    }
}