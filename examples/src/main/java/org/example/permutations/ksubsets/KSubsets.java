package org.example.permutations.ksubsets;

import java.util.Arrays;
import java.util.List;

public class KSubsets {
    // Function to generate all k-element subsets
    public static void generateKSubsets(List<Integer> set, int k) {
        int n = set.size();
        int[] subset = new int[k];
        generateKSubsetsRecursive(set, subset, 0, n - 1, 0, k);
    }

    // Recursive function to generate k-element subsets
    private static void generateKSubsetsRecursive(List<Integer> set, int[] subset, int start, int end, int index, int k) {
        if (index == k) {
            System.out.println(Arrays.toString(subset));
            return;
        }

        for (int i = start; i <= end && end - i + 1 >= k - index; i++) {
            subset[index] = set.get(i);
            generateKSubsetsRecursive(set, subset, i + 1, end, index + 1, k);
        }
    }
}
