package org.example;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader();
        reader.readInput();



        List<Factory> factories = reader.factories;

        Map<Integer, Component> components = new HashMap<>();

        int[] scc = SCC.divideIntoSCC(factories, reader.N);
        boolean[] isComponentStartingPoint = new boolean[SCC.sccCount];
        Arrays.fill(isComponentStartingPoint, true);

        Factory factory;
        Component component;
        for (int i = 0; i < reader.N; i++) {

            factory = factories.get(i);

            int componentId = scc[i];

            if(components.containsKey(componentId)){
                component = components.get(componentId);
            } else {
                component = new Component(componentId);
                components.put(componentId, component);
            }

            for (Factory neighbor : factory.directNeighbor) {
                int sccIdentifier = scc[neighbor.indetifier];
                if (componentId != sccIdentifier) {
                    component.addNeighbor(scc[neighbor.indetifier], 0);
                }
            }

            for (Factory opositeNeighbor : factory.opositeDirectionNeighbor) {
                int sccIdentifier = scc[opositeNeighbor.indetifier];
                if (componentId != sccIdentifier) {
                    component.addNeighbor(scc[opositeNeighbor.indetifier], 1);
                    isComponentStartingPoint[componentId] = false;
                }
            }
        }

        int reconfigurations = 0;
        int destinationComponentId = scc[reader.C];

        for (int i = 0; i < SCC.sccCount; i++) {
            if (isComponentStartingPoint[i]) {
                int increment = Dijkstra.dijkstraDistance(components, destinationComponentId, i);
                reconfigurations += increment;
            }
        }

        System.out.println(reconfigurations);
    }
}