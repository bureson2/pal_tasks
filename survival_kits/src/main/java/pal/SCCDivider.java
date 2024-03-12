package pal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SCCDivider {
    private final Park park;
    private int time = 0;
    private int numberOfComponents = 0;
    private final int[] lowLinks;
    private final int[] discoveryTimes;
    private final boolean[] onStack;
    private final int[] pointAreasIdentifier;
    private final Stack<Integer> stack;

    public SCCDivider(Park park) {
        this.park = park;
        int numberOfPoints = park.getNumberOfPoints();

        lowLinks = new int[numberOfPoints];
        discoveryTimes = new int[numberOfPoints];
        onStack = new boolean[numberOfPoints];
        pointAreasIdentifier = new int[numberOfPoints];

        stack = new Stack<>();
    }


    public List<Area> findStronglyConnectedComponents(){
        List<Area> stronglyConnectedComponents = new ArrayList<>();
        for(int point = 0; point < park.getNumberOfPoints(); point++){
            if(discoveryTimes[point] == 0){
                tarjanSCC(point, stronglyConnectedComponents);
            }
        }
        return stronglyConnectedComponents;
    }


    private void tarjanSCC(int actualPoint, List<Area> stronglyConnectedComponents){
        discoveryTimes[actualPoint] = time + 1;
        lowLinks[actualPoint] = time + 1;
        time++;
        stack.push(actualPoint);
        onStack[actualPoint] = true;

        for (int comparedPoint : park.getAdjencyList().get(actualPoint)){
            if (discoveryTimes[comparedPoint] == 0){
                tarjanSCC(comparedPoint, stronglyConnectedComponents);
                lowLinks[actualPoint] = Math.min(lowLinks[actualPoint], lowLinks[comparedPoint]);
            } else if (onStack[comparedPoint]){
                lowLinks[actualPoint] = Math.min(lowLinks[actualPoint], lowLinks[comparedPoint]);
            }
        }

        if (lowLinks[actualPoint] == discoveryTimes[actualPoint]){
            List<Integer> findedStronglyConnectedComponent = new ArrayList<>();
            int returnedPoint;
            do {
                returnedPoint = stack.pop();
                onStack[returnedPoint] = false;
                findedStronglyConnectedComponent.add(returnedPoint);

//               number of component is used as identifier of component
                pointAreasIdentifier[returnedPoint] = numberOfComponents;
            } while (actualPoint != returnedPoint);

            Area area = new Area(findedStronglyConnectedComponent, numberOfComponents);
            numberOfComponents++;
            stronglyConnectedComponents.add(area);
        }
    }

    public int[] getPointAreasIdentifier() {
        return pointAreasIdentifier;
    }

    public int getNumberOfComponents() {
        return numberOfComponents;
    }
}
