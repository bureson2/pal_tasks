package org.example.prime_utils;

import java.math.BigInteger;
import java.util.function.Function;

public class PollardsRhoAlghoritm {
    public static BigInteger solve(BigInteger n) {
        Function<BigInteger, BigInteger> f = x -> x.multiply(x).add(BigInteger.ONE).mod(n);
        BigInteger x = BigInteger.TWO, y = BigInteger.TWO, d = BigInteger.ONE;

        while (d.equals(BigInteger.ONE)) {
            x = f.apply(x);
            y = f.apply(f.apply(y));
            d = x.subtract(y).gcd(n);
        }

        return d.equals(n) ? BigInteger.ONE : d;
    }
}
