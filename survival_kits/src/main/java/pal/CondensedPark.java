package pal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CondensedPark {
    private final List<Area> areas;
    private final List<List<Integer>> adjencyList;
    private final List<Integer> topologicalOrderBeforePeak;
    private final List<Integer> topologicalOrderAfterPeak;
    private int cpPeakArea = 0;
    private int numberOfAreas = 0;

    public CondensedPark(int numberOfComponents) {
        this.areas = new ArrayList<>(numberOfComponents);
        this.adjencyList = new ArrayList<>(numberOfComponents);
        this.topologicalOrderBeforePeak = new ArrayList<>();
        this.topologicalOrderAfterPeak = new ArrayList<>();

        for (int i = 0; i < numberOfComponents; i++) {
            this.adjencyList.add(new ArrayList<>());
        }
    }

    public void addArea(Area area){
        areas.add(area);
        numberOfAreas++;
    }

    public void addTrack(int sourceArea, int destinationArea){
//        if (!adjencyList.get(sourceArea).contains(destinationArea)){
            adjencyList.get(sourceArea).add(destinationArea);
//        }
    }

    public void findTopologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[numberOfAreas];

        topologicalDFS(cpPeakArea, visited, stack);
        while (!stack.isEmpty()) {
            topologicalOrderAfterPeak.add(stack.pop());
        }

        Arrays.fill(visited, false);

        for (int area = cpPeakArea; area < numberOfAreas; area++) {
            topologicalDFSBeforeCPPeak(area, visited, stack);
        }
        while (!stack.isEmpty()) {
            topologicalOrderBeforePeak.add(stack.pop());
        }
    }

    private void topologicalDFS(int area, boolean[] visited, Stack<Integer> stack) {
        visited[area] = true;
        for (int neighborArea : adjencyList.get(area)) {
            if (!visited[neighborArea]) {
                topologicalDFS(neighborArea, visited, stack);
            }
        }
        stack.push(area);
    }

    private void topologicalDFSBeforeCPPeak(int area, boolean[] visited, Stack<Integer> stack) {
        visited[area] = true;
        for (int neighborArea : adjencyList.get(area)) {
            if (!visited[neighborArea] && neighborArea > cpPeakArea) {
                topologicalDFSBeforeCPPeak(neighborArea, visited, stack);
            }
        }
        stack.push(area);
    }


    public int getNumberOfAreas() {
        return numberOfAreas;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public List<List<Integer>> getAdjencyList() {
        return adjencyList;
    }

    public List<Integer> getTopologicalOrderBeforePeak() {
        return topologicalOrderBeforePeak;
    }

    public List<Integer> getTopologicalOrderAfterPeak() {
        return topologicalOrderAfterPeak;
    }

    public void setCpPeak(int cpPeakArea) {
        this.cpPeakArea = cpPeakArea;
    }

    public int getCpPeakArea() {
        return cpPeakArea;
    }
}