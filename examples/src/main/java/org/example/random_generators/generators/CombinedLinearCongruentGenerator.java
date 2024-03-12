package org.example.random_generators.generators;

public class CombinedLinearCongruentGenerator {
    private long seed1, seed2, M1, A1, C1, M2, A2, C2;

    public CombinedLinearCongruentGenerator(long seed1, long see2, long m1, long a1, long c1, long m2, long a2, long c2) {
        this.seed1 = seed1;
        this.seed2 = seed2;
        M1 = m1;
        A1 = a1;
        C1 = c1;
        M2 = m2;
        A2 = a2;
        C2 = c2;
    }

    public long next() {
        long y1 = (A1 * seed1 + C1) % M1;
        long y2 = (A2 * seed2 + C2) % M2;
        seed1 = y1;
        seed2 = y2;

        long result = (y1 - y2) % (M1 - 1);

        if (result < 0) {
            result += (M1 - 1);
        }

        return result;
    }
}
