package pal;

import java.util.List;

public class SequenceValidator {
    public static boolean validateSequence(int[] testSequence, long A, long C, long M, long seed, int N) {

        if (seed != testSequence[0]) {
            return false;
        }

        for (int i = 1; i < N; i++) {
            seed = (A * seed + C) % M;
            if (seed == testSequence[i]) {
                continue;
            }
            return false;
        }

        return true;
    }
}
