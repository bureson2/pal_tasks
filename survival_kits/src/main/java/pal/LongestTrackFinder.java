package pal;

import java.sql.SQLOutput;
import java.util.*;

public class LongestTrackFinder {
    private final List<Integer> topologicalOrderBeforePeak;
    private final List<Integer> topologicalOrderAfterPeak;
    private final List<List<Integer>> adjencyList;
    private final List<List<Integer>> previousPaths;
    private final int[] longestPathBeforePeak;
    private final int[] longestPathAfterPeak;
    int maxInSecondHalf = 0;
    int maxInFirstHalf = 0;
    private final int cpPeakArea;

    public LongestTrackFinder(List<Integer> topologicalOrderBeforePeak,
                              List<Integer> topologicalOrderAfterPeak,
                              List<List<Integer>> adjencyList,
                              int numberOfAreas,
                              int cpPeakArea) {
        this.topologicalOrderBeforePeak = topologicalOrderBeforePeak;
        this.topologicalOrderAfterPeak = topologicalOrderAfterPeak;
        this.adjencyList = adjencyList;
        this.previousPaths = new ArrayList<>(numberOfAreas);
        this.cpPeakArea = cpPeakArea;

        longestPathBeforePeak = new int[numberOfAreas];
        longestPathAfterPeak = new int[numberOfAreas];
        Arrays.fill(longestPathBeforePeak, 1);
        Arrays.fill(longestPathAfterPeak, 1);

        for (int i = 0; i < numberOfAreas; i++) {
            previousPaths.add(new ArrayList<>());
        }
    }

    public int findLongestPath() {
        int longestTrackAfterCPpeak = findPartOfLongestTrack(longestPathAfterPeak, topologicalOrderAfterPeak);
        int longestTrackBeforeCPpeak = findPartOfBeforeLongestTrack(longestPathBeforePeak, topologicalOrderBeforePeak);

        maxInFirstHalf = longestTrackBeforeCPpeak;
        maxInSecondHalf = longestTrackAfterCPpeak;

        return longestTrackBeforeCPpeak + longestTrackAfterCPpeak - 1;

    }

    private int findPartOfLongestTrack(int[] longestPath, List<Integer> topologicalOrder) {
        int maxPath = 0;

        for (int mainArea : topologicalOrder) {
            for (int neighborArea : adjencyList.get(mainArea)) {
                if (longestPath[neighborArea] < longestPath[mainArea] + 1) {
                    longestPath[neighborArea] = longestPath[mainArea] + 1;
                        clearPreviousPath(neighborArea);
                        previousPaths.get(neighborArea).add(mainArea);
                    if (longestPath[neighborArea] > maxPath) {
                        maxPath = longestPath[neighborArea];
                    }
                }
                else if (longestPath[neighborArea] == longestPath[mainArea] + 1) {
                    previousPaths.get(neighborArea).add(mainArea);
                }
            }
        }

        return maxPath;
    }

    private int findPartOfBeforeLongestTrack(int[] longestPath, List<Integer> topologicalOrder) {
        int maxPath = 0;

        for (int mainArea : topologicalOrder) {
            for (int neighborArea : adjencyList.get(mainArea)) {
                if(neighborArea < cpPeakArea) continue;
                if (longestPath[neighborArea] < longestPath[mainArea] + 1) {
                    longestPath[neighborArea] = longestPath[mainArea] + 1;
                    clearPreviousPath(neighborArea);
                    previousPaths.get(neighborArea).add(mainArea);
                    if (longestPath[neighborArea] > maxPath) {
                        maxPath = longestPath[neighborArea];
                    }
                }
                else if (longestPath[neighborArea] == longestPath[mainArea] + 1) {
                    previousPaths.get(neighborArea).add(mainArea);
                }
            }
        }

        return longestPath[cpPeakArea];
    }

    public Set<Integer> findAllAreasOnLongestPath() {

        List<Integer> startingCalculatePoints = new ArrayList<>();
        Set<Integer> allAreasOnLongestPath = new HashSet<>();

        for (int i = 0; i <= cpPeakArea; i++) {
            if (longestPathAfterPeak[i] == maxInSecondHalf) {
                startingCalculatePoints.add(i);
            }
        }


        startingCalculatePoints.add(cpPeakArea);

        for (int area : startingCalculatePoints) {
            allAreasOnLongestPath.add(area);
            addPreviousPaths(area, allAreasOnLongestPath);
        }

        return allAreasOnLongestPath;

    }

    private void addPreviousPaths(int area, Set<Integer> allAreasOnLongestPath) {
        List<Integer> trackedAreas = previousPaths.get(area);

        for (int trackedArea : trackedAreas) {
            if (!allAreasOnLongestPath.contains(trackedArea)) {
                allAreasOnLongestPath.add(trackedArea);
                addPreviousPaths(trackedArea, allAreasOnLongestPath);
            }
        }
    }

    private void clearPreviousPath(int clearIndex) {
        previousPaths.get(clearIndex).clear();
    }
}
