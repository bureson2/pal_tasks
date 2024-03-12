package org.example;

import java.util.ArrayList;
import java.util.List;

public class Factory {
    public int indetifier;
    public List<Factory> directNeighbor = new ArrayList<>();
    public List<Factory> opositeDirectionNeighbor = new ArrayList<>();

    public Factory(int indetifier) {
        this.indetifier = indetifier;
    }

    public void addConvoyer(Factory factory){
        directNeighbor.add(factory);
    }

    public void addOpositeConvoyer(Factory factory){
        opositeDirectionNeighbor.add(factory);
    }
}
