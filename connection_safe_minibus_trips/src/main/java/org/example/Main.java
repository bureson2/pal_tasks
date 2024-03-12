package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader();
        reader.readInput();

        List<List<Integer>> adjencyList = reader.adjencyList;

        SCC tarjan = new SCC(reader.adjencyList, reader.N);
        int[] scc = tarjan.findSCCs();

        Component component;
        Map<Integer, Component> components = tarjan.components;

        boolean isNotSafety[] = new boolean[reader.N];
        for (int i = 0; i < reader.N; i++){
            component = components.get(scc[i]);
            int componentId = component.id;

            List<Integer> neighbors = adjencyList.get(i);
            for (int neighbor : neighbors){
                int neighborComponentId = scc[neighbor];

                if (componentId != neighborComponentId) {
                    component.addNeighbor(components.get(neighborComponentId));
                    isNotSafety[i] = true;
                    isNotSafety[neighbor] = true;
                }
            }
        }

        for (int i = 0; i < reader.N; i++){
            if (!isNotSafety[i]){
                int componentId = scc[i];
                components.get(componentId).increaseSafetyStation();
            }
        }


        GraphProcessor processor = new GraphProcessor(components);
        int maxSum = processor.findMaxSafetyStationSum();


        System.out.println(maxSum);
    }
}