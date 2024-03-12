package pal;

import java.util.List;

import static pal.PrimeUtil.primeFactors;

public class PrimitiveRootUtil {

    // Hledání nejmenšího primitivního kořene pro dané M
    public static int findSmallestPrimitiveRoot(int M) {
        for (int R = 2; R < M; R++) {
            if (isPrimitiveRoot(R, M)) {
                return R;
            }
        }
        return -1;
    }

    // Kontrola, zda je číslo primitivním kořenem modulo M
    private static boolean isPrimitiveRoot(int R, long M) {
        long order = M - 1;
        List<Long> factors = primeFactors(order);
        for (long factor : factors) {
            if (powerMod(R, order / factor, M) == 1) {
                return false;
            }
        }
        return true;
    }

    // Výpočet (base^exponent) mod modulus
    private static long powerMod(long base, long exponent, long modulus) {
        long result = 1;
        base = base % modulus;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }
            exponent = exponent >> 1;
//            exponent = exponent / 2;
            base = (base * base) % modulus;
        }
        return result;
    }
}
