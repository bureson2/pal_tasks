package pal;

import java.util.ArrayList;
import java.util.List;

public class Park {
    private final int numberOfPoints;
    private final int cpPeak;
    private final List<Track> tracks;
    private final List<List<Integer>> adjencyList;


    public Park(int numberOfPoints, int numberOfTracks, int cpPeak) {
        this.numberOfPoints = numberOfPoints;
        this.cpPeak = cpPeak;
        this.tracks = new ArrayList<>(numberOfTracks);
        this.adjencyList = new ArrayList<>(numberOfPoints);

        for (int i = 0; i < numberOfPoints; i++) {
            this.adjencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination){
        tracks.add(new Track(source, destination));
        adjencyList.get(source).add(destination);
    }


//    BASIC GETTERS
//    ------------------------------------------------------------------

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public int getCpPeak() {
        return cpPeak;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public List<List<Integer>> getAdjencyList() {
        return adjencyList;
    }
}
