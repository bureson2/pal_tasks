package org.example.random_generators;

public class HullDobellTheoremChecker {

    public static boolean checkConditions(long M, long A, long C) {
        // Condition 1: C and M are coprimes.
        if (gcd(C, M) != 1) {
            System.out.println("\nError: C a M are not coprimes.");
            return false;
        }

        // Condition 2: A-1 is divisible by each prime factor of M.
        if (!isDivisibleByAllPrimeFactorsOfM(A - 1, M)) {
            System.out.println("\nError: A - 1 is not divisible by each prime factor of M.");
            return false;
        }

        // Condition 3: If 4 divides M then also 4 divides A-1.
        if (M % 4 == 0 && (A - 1) % 4 != 0) {
            System.out.println("\nError: 4 divides M, but 4 does not divide A - 1.");
            return false;
        }

        System.out.println("\nSUCCESS: Everything is correct\n");
        return true;
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private static boolean isDivisibleByAllPrimeFactorsOfM(long number, long M) {
        for (long i = 2; i <= M / i; i++) {
            if (M % i == 0) {
                if (number % i != 0) {
                    return false;
                }
                while (M % i == 0) {
                    M /= i;
                }
            }
        }
        return M > 1 ? number % M == 0 : true;
    }
}
