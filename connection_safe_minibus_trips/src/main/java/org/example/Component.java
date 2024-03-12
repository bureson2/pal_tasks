package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Component {
    public int id;
    public Map<Integer, Component> neighbors;
    public int safetyStation;

    public Component(int id) {
        this.id = id;
        neighbors = new HashMap<>();
        safetyStation = 0;
    }

    public void addNeighbor(Component component) {
        neighbors.put(component.id, component);
    }

    public void increaseSafetyStation(){
        safetyStation++;
    }
}
