package pal;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String args[]) {

        InputParser inputParser = new InputParser();
        Park park = inputParser.readLines();

        SCCDivider sccDivider = new SCCDivider(park);
//        Move as parameters, save as variable only for control

        List<Area> areas = sccDivider.findStronglyConnectedComponents();
//        System.out.println(areas.size());
        int[] pointAreasIdentifiers = sccDivider.getPointAreasIdentifier();

        ParkCondenser condenser = new ParkCondenser(
                sccDivider.getNumberOfComponents(),
                areas,
                pointAreasIdentifiers
        );

        condenser.proccessTracks(park.getTracks(), park.getCpPeak());
        CondensedPark condensedPark = condenser.getCondensedPark();

        condensedPark.findTopologicalSort();
//        printer.printTopologicalOrder(condensedPark.getTopologicalOrderBeforePeak(), condensedPark.getAdjencyList());
//        printer.printTopologicalOrder(condensedPark.getTopologicalOrderAfterPeak(), condensedPark.getAdjencyList());

        LongestTrackFinder trackFinder = new LongestTrackFinder(
                condensedPark.getTopologicalOrderBeforePeak(),
                condensedPark.getTopologicalOrderAfterPeak(),
                condensedPark.getAdjencyList(),
                condensedPark.getNumberOfAreas(),
                condensedPark.getCpPeakArea()
        );

        int result = trackFinder.findLongestPath();
        Set<Integer> areasForSurvivalKit = trackFinder.findAllAreasOnLongestPath();

        int pointSum = 0;
        for (int area : areasForSurvivalKit){
            pointSum += areas.get(area).getNumberOfPoints();
        }

        System.out.println(result + " " + pointSum);

    }
}
