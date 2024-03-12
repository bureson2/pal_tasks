package org.example.prime_utils;

import java.math.BigInteger;
import java.util.Random;

public class PrimalityTest {

    public static boolean fermatTest(int n, int k) {
        if (n <= 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            int a = rand.nextInt(n - 2) + 2;
            BigInteger aBig = BigInteger.valueOf(a);
            BigInteger nBig = BigInteger.valueOf(n);
            if (!aBig.modPow(nBig.subtract(BigInteger.ONE), nBig).equals(BigInteger.ONE)) {
                return false;
            }
        }
        return true;
    }

    public static boolean millerRabinTest(int n, int k) {
        if (n <= 2) {
            return n == 2;
        }
        if (n % 2 == 0) {
            return false;
        }

        // Division n - 1 to 2^r * d
        int r = 0;
        int d = n - 1;
        while (d % 2 == 0) {
            d /= 2;
            r++;
        }

        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            int a = rand.nextInt(n - 3) + 2;
            BigInteger aBig = BigInteger.valueOf(a);
            BigInteger nBig = BigInteger.valueOf(n);
            BigInteger dBig = BigInteger.valueOf(d);

            BigInteger x = aBig.modPow(dBig, nBig);
            if (x.equals(BigInteger.ONE) || x.equals(nBig.subtract(BigInteger.ONE))) {
                continue;
            }

            int j;
            for (j = 0; j < r - 1; j++) {
                x = x.modPow(BigInteger.valueOf(2), nBig);
                if (x.equals(BigInteger.ONE)) {
                    return false;
                }
                if (x.equals(nBig.subtract(BigInteger.ONE))) {
                    break;
                }
            }
            if (j == r - 1) {
                return false;
            }
        }
        return true;
    }

}
