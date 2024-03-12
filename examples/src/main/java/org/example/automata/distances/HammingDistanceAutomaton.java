package org.example.automata.distances;

public class HammingDistanceAutomaton {
    public static int computeHammingDistance(String str1, String str2) {
        if (str1.length() != str2.length()) {
            throw new IllegalArgumentException("Strings must be of the same length");
        }

        int distance = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }
}
