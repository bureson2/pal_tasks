package org.example;

import java.util.ArrayList;
import java.util.List;

public class Street {
    public int identifier;
    private int oldIdentifier;
    public List<Street> neighbors;
    public int cost = 0;

    public Street(int identifier) {
        this.identifier = identifier;
        this.oldIdentifier = identifier;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(Street neighbor){
        neighbors.add(neighbor);
    }

}
