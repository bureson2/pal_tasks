package org.example.random_generators.generators;

import lombok.Getter;

import static org.example.random_generators.HullDobellTheoremChecker.gcd;

@Getter
public class BlumBlumShubGenerator {
    private long seed;
    private long M;

    public BlumBlumShubGenerator(long seed, long p, long q) {
        M = p * q;
        if (gcd(seed, M) != 1){
            System.out.println("It is not correct combination");
            return;
        }

        this.seed = seed;
    }

    public long next(){
        seed = (seed * seed) % M;
        return seed;
    }
}
