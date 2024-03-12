package pal;

import java.util.ArrayList;
import java.util.List;

public class PrimeUtil {

    // Získání prvočíselných dělitelů čísla
    public static List<Long> primeFactors(long n) {
        List<Long> factors = new ArrayList<>();
        for (long i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) factors.add(n);
        return factors;
    }

    public static List<Integer> generatePrimesUpToD(int D, boolean[] isPrime) {
        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= D; i++) {
            if (isPrime[i]){
                primes.add(i);
            }
        }

        return primes;
    }

    // Kontrola, zda má M-1 prvočíselné dělitele menší než D
    public static boolean hasPrimeFactorsLessThanD(long n, List<Integer> primesUpToD) {
        for (int prime : primesUpToD) {
            if (n % prime == 0) {
                while (n % prime == 0) {
                    n /= prime;
                }
                if (n == 1) {
                    return true;
                }
            }
        }
        return n == 1;
    }
}
