package org.example;

import java.util.*;

public class Component {
    public int identifier;
    public Map<Integer, Integer> neighbors = new HashMap(); // componentIdentifier, convey cost

    public Component(int identifier) {
        this.identifier = identifier;
    }

    public void addNeighbor(int componentId, int weight){
        neighbors.put(componentId, weight);
    }
}
