package org.example;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader();
        reader.readInput();

        List<Street> streets = reader.streets;
        int N = reader.N;
        int[] scc = SCC.divideIntoSCC(streets, N);

        StreetReducer.cutWeakStreet(streets, N, scc);
        N = streets.size();
        scc = SCC.divideIntoSCC(streets, N);

        Map<Integer, Component> components = StreetReducer.prepareComponents(streets, N, scc);

        // Setting varibility
        int maxVariability = StreetReducer.maxVariability;
        int minCost = Integer.MAX_VALUE;
        int actualMinCost = Integer.MAX_VALUE;
        int prospectiveCrossing = 0;

        for (Component component : components.values()){
            actualMinCost = component.calculateCosts();
            if ( actualMinCost < minCost ){
                prospectiveCrossing = component.prospectsNumber;
                minCost = actualMinCost;
            } else if (actualMinCost == minCost){
                prospectiveCrossing += component.prospectsNumber;
            }
        }

         StringBuilder sb = new StringBuilder();
         sb.append(prospectiveCrossing)
                 .append(" ")
                 .append(maxVariability)
                 .append(" ")
                 .append(minCost);

        System.out.println(sb);

    }
}