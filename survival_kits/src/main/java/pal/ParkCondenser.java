package pal;

import java.util.List;

public class ParkCondenser {

    private final CondensedPark park;
    private final int[] pointsInAreas;
    public ParkCondenser(
            int numberOfComponents,
            List<Area> areas,
            int[] pointAreasIdentifiers
    ) {
        this.park = new CondensedPark(numberOfComponents);
        this.pointsInAreas = pointAreasIdentifiers;
        for (Area area : areas){
            park.addArea(area);
        }
    }

    public void proccessTracks(List<Track> tracks, int cpPeak){
        for (Track track : tracks){
//            TODO edit
            int sourcePoint = track.getSource();
            int destinationPoint = track.getDestination();
            int sourceArea = findPointComponent(sourcePoint);
            int destinationArea = findPointComponent(destinationPoint);

            if (sourcePoint == cpPeak){
                park.setCpPeak(sourceArea);
            } else if (destinationPoint == cpPeak){
                park.setCpPeak(destinationArea);
            }

            if (sourceArea != destinationArea) {
                park.addTrack(sourceArea, destinationArea);
            }
        }
    }

    private int findPointComponent(int searchedPoint){
        return pointsInAreas[searchedPoint];
    }

    public CondensedPark getCondensedPark() {
        return park;
    }
}
