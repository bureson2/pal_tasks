package pal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Component {
    public int id;
    public List<Component> neighbors;
    public int numberOfLakes;
    public int outOfTheComponent;

    public Component(int id) {
        this.id = id;
        neighbors = new ArrayList<>();
        numberOfLakes = 0;
        outOfTheComponent = 0;
    }

    public void addNeighbor(Component component) {
        neighbors.add(component);
    }

    public void increaseNumberOfLakes(){
        numberOfLakes++;
    }

    public void increaseOutOfTheComponent(){
        outOfTheComponent++;
    }
}
