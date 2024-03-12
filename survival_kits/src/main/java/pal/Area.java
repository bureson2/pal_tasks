package pal;

import java.util.List;

// Strongly Connected Component (SCC)
public class Area {
    private final List<Integer> points;
    private final int numberOfPoints;
//    identifier is same as index in Areas List. It is prepared for easier searching and finding in condensed park
    private final int identifier;

    public Area(List<Integer> points, int identifier) {
        this.points = points;
        this.identifier = identifier;
        numberOfPoints = points.size();
    }

    public List<Integer> getPoints() {
        return points;
    }

    public int getIdentifier() {
        return identifier;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }



}
