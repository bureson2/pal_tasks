package org.example.random_generators.generators;

import lombok.Getter;

@Getter
public class LehmerGenerator {
    private long seed;
    private long M; // Modulus
    private long A; // Multiplier

    public LehmerGenerator(long seed, long m, long a) {
        this.seed = seed;
        M = m;
        A = a;
    }

    public long next(){
        seed = (seed * A) % M;
        return seed;
    }
}
