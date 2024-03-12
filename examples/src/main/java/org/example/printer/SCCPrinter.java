package org.example.printer;

import java.util.List;

public class SCCPrinter {
    public static void printSCC(List<List<Integer>> stronglyConnectedComponents){
        for (List<Integer> component : stronglyConnectedComponents) {
            System.out.println(component);
        }
        System.out.println();
    }
}
