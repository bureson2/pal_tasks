package org.example.permutations.permutations;

import java.util.Arrays;
import java.util.List;

public class PermutationExample {
    public static void main(String[] args) {
//        FOR SPEED TEST
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        System.out.println("Permutations get by standard heap algorithm");
        long startTimeHeap = System.nanoTime();
        Permutations.generatePermutations(list, list.size());
        long endTimeHeap = System.nanoTime();
        long durationHeap = (endTimeHeap - startTimeHeap) / 1_000_000;

        System.out.println("Permutations get by lexikographic approach");
        long startTimeLexico = System.nanoTime();
        Permutations.generatePermutationsLexicographically(list);
        long endTimeLexico = System.nanoTime();
        long durationLexico = (endTimeLexico - startTimeLexico) / 1_000_000;

//        With printing lexicographic variant is faster, without printing heap algorithm is better
        System.out.println("Heap algorithm duration: " + durationHeap + " ms");
        System.out.println("Lexicographic algorithm duration: " + durationLexico + " ms");
        System.out.println();


//        RANK AND UNRANK LEXIGORAPHIC PERMUTATION
        System.out.println("Rank: " + RankUtil.getPermutationRank(Arrays.asList(1,2,3,4)));
        System.out.println("Permutation: " + RankUtil.getPermutation(4, 0));

        System.out.println("Rank: " + RankUtil.getPermutationRank(Arrays.asList(3,1,2,4)));
        System.out.println("Permutation: " + RankUtil.getPermutation(4, 12));

        System.out.println("Rank: " + RankUtil.getPermutationRank(Arrays.asList(4,2,3,1)));
        System.out.println("Permutation: " + RankUtil.getPermutation(4, 21));

        System.out.println("Rank: " + RankUtil.getPermutationRank(Arrays.asList(4,3,2,1)));
        System.out.println("Permutation: " + RankUtil.getPermutation(4, 23));
    }
}
