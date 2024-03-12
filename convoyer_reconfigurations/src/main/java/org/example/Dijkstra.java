package org.example;

import java.util.*;

public class Dijkstra {
    public static int dijkstraDistance(Map<Integer, Component> components, int startId, int endId) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Map<Integer, Integer> distances = new HashMap<>();

        // Inicializace
        for (int id : components.keySet()) {
            distances.put(id, Integer.MAX_VALUE);
        }

        distances.put(startId, 0);
        queue.add(new Node(startId, 0));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int currentId = currentNode.componentId;

            // Pokud jsme dosáhli cílového uzlu, můžeme skončit
            if (currentId == endId) {
                return distances.get(currentId);
            }

            // Projdeme všechny sousedy
            for (Map.Entry<Integer, Integer> neighborEntry : components.get(currentId).neighbors.entrySet()) {
                int neighborId = neighborEntry.getKey();
                int weight = neighborEntry.getValue();

                // Relaxace
                int newDist = distances.get(currentId) + weight;
                if (newDist < distances.get(neighborId)) {
                    distances.put(neighborId, newDist);
                    queue.add(new Node(neighborId, newDist));
                }
            }
        }

        // Pokud cesta neexistuje, vrátíme -1 nebo jinou hodnotu indikující chybu
        return distances.getOrDefault(endId, -1);
    }


}
