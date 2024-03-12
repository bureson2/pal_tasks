package org.example.permutations.permutations;

import java.util.Collections;
import java.util.List;

public class Permutations {
    // Function to generate all permutations of a list using Heap's algorithm
    public static void generatePermutations(List<Integer> list, int n) {
        if (n == 1) {
            System.out.println(list);
        } else {
            for (int i = 0; i < n - 1; i++) {
                generatePermutations(list, n - 1);
                if (n % 2 == 0) {
                    Collections.swap(list, i, n - 1);
                } else {
                    Collections.swap(list, 0, n - 1);
                }
            }
            generatePermutations(list, n - 1);
        }
    }

    // Function to generate all permutations of a list in lexicographic order
    public static void generatePermutationsLexicographically(List<Integer> list) {
        Collections.sort(list); // Ensure the list is in lexicographic order
        while (true) {
            System.out.println(list);

            // Find the largest index k such that a[k] < a[k + 1]
            int k = list.size() - 2;
            while (k >= 0 && list.get(k) >= list.get(k + 1)) {
                k--;
            }
            if (k < 0) break; // This is the last permutation

            // Find the largest index l greater than k such that a[k] < a[l]
            int l = list.size() - 1;
            while (list.get(k) >= list.get(l)) {
                l--;
            }

            // Swap the value of a[k] with that of a[l]
            Collections.swap(list, k, l);

            // Reverse the sequence from a[k + 1] up to and including the final element a[n]
            for (int i = k + 1, j = list.size() - 1; i < j; i++, j--) {
                Collections.swap(list, i, j);
            }
        }
    }

}
