package org.example;

import java.util.*;

public class StreetReducer {

    public static int maxVariability;

    public static void cutWeakStreet(List<Street> streets, int N, int[] scc) {
        boolean[] isWeak = new boolean[N];

        // nalezeni komponent, ktere nemaji byt v ukolove scc
        for (Street street : streets) {
            for (Street neighbor : street.neighbors) {
                if (scc[street.identifier] != scc[neighbor.identifier]) {
                    isWeak[street.identifier] = true;
                    break;
                }
            }
        }

        Iterator<Street> streetIterator = streets.iterator();
        Iterator<Street> neighborIterator;
        Street street;
        Street neighbor;


        while (streetIterator.hasNext()) {
             street = streetIterator.next();

            if (isWeak[street.identifier]) {

                streetIterator.remove(); // Odstraní weak street

            } else {

                neighborIterator = street.neighbors.iterator();
                while (neighborIterator.hasNext()) {
                    neighbor = neighborIterator.next();
                    if (isWeak[neighbor.identifier]) {

                        neighborIterator.remove();
                    }
                }
            }
        }

        int newIdentifier = 0;

        for (Street s : streets){
            s.identifier = newIdentifier;
            newIdentifier++;
        }
    }

    public static Map<Integer, Component> prepareComponents(List<Street> streets, int N, int[] scc){
        maxVariability = 0;

        Map<Integer,Component> components = new HashMap<>();

        Iterator<Street> neighborIterator;
        Street neighbor;

        for (Street street : streets){

            neighborIterator = street.neighbors.iterator();

            while (neighborIterator.hasNext()){
                neighbor = neighborIterator.next();
                if (scc[neighbor.identifier] != scc[street.identifier]){
                    neighborIterator.remove();
                }
            }

            int sccIdentifier = scc[street.identifier];
            if ( components.containsKey(sccIdentifier)){
                components.get(sccIdentifier).addStreet(street);
                maxVariability = Math.max(maxVariability, components.get(sccIdentifier).variability);
            } else {
                Component component = new Component();
                component.streets.add(street);
                components.put(sccIdentifier, component);
            }

        }

        reduceComponents(components);

        return components;

    }

    private static void reduceComponents(Map<Integer, Component> components){
        Iterator<Map.Entry<Integer, Component>> iterator = components.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, Component> entry = iterator.next();
            Component component = entry.getValue();

            if (component.variability < maxVariability) {
                iterator.remove();
            }
        }

    }

}
