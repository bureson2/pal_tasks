package org.example.permutations.ksubsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.permutations.ksubsets.KSubsets.generateKSubsets;

public class KSubsetsExample {
    public static void main(String[] args) {
//        List<Integer> set = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        List<Integer> set = Arrays.asList(1,2,3,4,5,6);
        int k = 4;
        generateKSubsets(set, k);
        System.out.println();

//        RANK AND UNRANK LEXIGORAPHIC PERMUTATION
        System.out.println("Rank:" + RankUtil.getSubsetRank(Arrays.asList(1,2,3,4),6));
        System.out.println("K-set:" + RankUtil.getSubsetByRank(0, 6, 4));

        System.out.println("Rank:" + RankUtil.getSubsetRank(Arrays.asList(2,3,4,5),6));
        System.out.println("K-set:" + RankUtil.getSubsetByRank(10, 6, 4));

        System.out.println("Rank:" + RankUtil.getSubsetRank(Arrays.asList(3,4,5,6),6));
        System.out.println("K-set:" + RankUtil.getSubsetByRank(14, 6, 4));

    }
}
