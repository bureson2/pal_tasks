package org.example;

import java.util.*;

public class Component {
    public List<Street> streets;
    public int variability;
    public int prospectsNumber = 0;

    public Component() {
        this.streets = new ArrayList<>();
        variability = 0;
    }

    public void addStreet(Street street){
        variability++;
        streets.add(street);
    }

    public int calculateCosts() {

        int min = Integer.MAX_VALUE;

        for (Street street : streets) {
            int totalCost = dijkstra(street);

            int totalBackCost = 0;
            for (Street otherStreet : streets) {
                totalBackCost += findShortestPathLength(otherStreet, street);
            }

            street.cost = totalCost + totalBackCost;
            if (street.cost < min) {
                min = street.cost;
                prospectsNumber = 1;
            } else if (street.cost == min){
                prospectsNumber++;
            }
        }

        return min;
    }

    private int dijkstra(Street source) {
        Map<Street, Integer> dist = new HashMap<>();
        PriorityQueue<StreetDistancePair> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        Set<Street> settled = new HashSet<>();

        // Inicializace vzdáleností
        for (Street street : streets) {
            dist.put(street, Integer.MAX_VALUE);
            pq.add(new StreetDistancePair(street, Integer.MAX_VALUE));
        }
        dist.put(source, 0);
        pq.add(new StreetDistancePair(source, 0));

        while (!pq.isEmpty()) {
            Street u = pq.poll().street;
            if (!settled.add(u)) continue; // Pokud už byl vrchol zpracován, přeskočíme ho

            for (Street v : u.neighbors) {
                if (!settled.contains(v)) {
                    int alt = dist.get(u) + 2; // Předpokládáme váhu 2 pro každou hranu
                    if (alt < dist.get(v)) {
                        dist.put(v, alt);
                        pq.add(new StreetDistancePair(v, alt)); // Aktualizace vzdálenosti v prioritní frontě
                    }
                }
            }
        }

        return dist.values().stream().filter(d -> d != Integer.MAX_VALUE).mapToInt(Integer::intValue).sum();
    }

    public int findShortestPathLength(Street source, Street destination) {
        Map<Street, Integer> dist = new HashMap<>();
        PriorityQueue<StreetDistancePair> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        Set<Street> settled = new HashSet<>();

        // Inicializace vzdáleností
        for (Street street : streets) {
            dist.put(street, Integer.MAX_VALUE);
            pq.add(new StreetDistancePair(street, Integer.MAX_VALUE));
        }
        dist.put(source, 0);
        pq.add(new StreetDistancePair(source, 0));

        while (!pq.isEmpty()) {
            Street u = pq.poll().street;
            if (u.equals(destination)) {
                return dist.get(u); // Nalezena nejkratší cesta k cíli
            }
            if (!settled.add(u)) continue; // Pokud už byl vrchol zpracován, přeskočíme ho

            for (Street v : u.neighbors) {
                if (!settled.contains(v)) {
                    int alt = dist.get(u) + 1; // Každá hrana má hodnotu 1
                    if (alt < dist.get(v)) {
                        dist.put(v, alt);
                        pq.add(new StreetDistancePair(v, alt)); // Aktualizace vzdálenosti v prioritní frontě
                    }
                }
            }
        }

        throw new IllegalStateException("Cesta měla být nalezena, ale nebyla.");

    }
}
