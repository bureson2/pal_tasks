package org.example.automata.distances;

import static org.example.automata.distances.HammingDistanceAutomaton.computeHammingDistance;
import static org.example.automata.distances.LevensteinDistanceAutomaton.computeLevenshteinDistance;

public class DistancesExample {

    public static void main(String[] args) {
        String str1 = "karolin";
        String str2 = "kathrin";

        // Hamming distance
        System.out.println("Hamming Distance: " + computeHammingDistance(str1, str2));

        // Levenstein distance
        System.out.println("Levenstein Distance: " + computeLevenshteinDistance(str1, str2));

        str2 = "kathrin2";
        System.out.println("Levenstein Distance: " + computeLevenshteinDistance(str1, str2));

        str1 = "zarolin";
        System.out.println("Levenstein Distance: " + computeLevenshteinDistance(str1, str2));

    }

}
