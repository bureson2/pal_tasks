package org.example.permutations.permutations;

import java.util.ArrayList;
import java.util.List;

public class RankUtil {
    public static int getPermutationRank(List<Integer> permutation) {
        int rank = 0;
        int n = permutation.size();
        List<Integer> elements = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            elements.add(i);
        }

        for (int i = 0; i < n; i++) {
            int index = elements.indexOf(permutation.get(i));
            rank += index * factorial(n - i - 1);
            elements.remove(index);
        }

        return rank;
    }

    private static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static List<Integer> getPermutation(int size, int rank){
        List<Integer> permutation = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        int factorial = 1;

        for (int i = 1; i <= size; i++){
            numbers.add(i);
            factorial *= i;
        }

        for (int i = 0; i < size; i++){
            factorial /= size - i;
            int index = rank / factorial;
            rank %= factorial;
            permutation.add(numbers.remove(index));
        }

        return permutation;
    }
}
