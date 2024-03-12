package org.example.permutations.ksubsets;

import java.util.ArrayList;
import java.util.List;

public class RankUtil {
    public static int getSubsetRank(List<Integer> subset, int n) {
        int rank = 0;
        int k = subset.size();
        for (int i = 0; i < k; i++) {
            int x = (i > 0) ? subset.get(i - 1) : 0;
            for (int j = x + 1; j < subset.get(i); j++) {
                rank += binomialCoefficient(n - j, k - i - 1);
            }
        }
        return rank;
    }

    public static List<Integer> getSubsetByRank(int rank, int n, int k) {
        List<Integer> subset = new ArrayList<>();
        int a = 0;
        for (int i = 0; i < k; i++) {
            a++;
            while (binomialCoefficient(n - a, k - i - 1) <= rank) {
                rank -= binomialCoefficient(n - a, k - i - 1);
                a++;
            }
            subset.add(a);
        }
        return subset;
    }

    private static int binomialCoefficient(int n, int k) {
        if (k > n - k) {
            k = n - k;
        }
        int result = 1;
        for (int i = 0; i < k; i++) {
            result *= (n - i);
            result /= (i + 1);
        }
        return result;
    }
}
