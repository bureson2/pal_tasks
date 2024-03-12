package org.example.random_generators.generators;

import lombok.Getter;

@Getter
public class LinearCongruentGenerator {
    private long seed;
    private long A;
    private long C;
    private long M;

    public LinearCongruentGenerator(long seed, long m, long a, long c) {
        this.seed = seed;
        A = a;
        C = c;
        M = m;
    }

    public long next(){
        seed = (A * seed + C) % M;
        return seed;
    }


}
