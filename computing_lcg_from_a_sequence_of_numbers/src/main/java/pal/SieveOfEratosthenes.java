package pal;

import java.util.ArrayList;
import java.util.List;

public class SieveOfEratosthenes {

    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] isNotPrime = new boolean[n + 1];
        isNotPrime[0] = true;
        isNotPrime[1] = true;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        return isNotPrime;
    }

    public static List<Integer> sieveOfEratosthenesAsPrimeList(int n) {

        boolean[] isNotPrime = sieveOfEratosthenes(n);

        int approxPrimeCount = (int) Math.ceil(n / Math.log(n));
        List<Integer> primes = new ArrayList<>(approxPrimeCount);

        for (int i = 0; i < n + 1; i++) {
            if (!isNotPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }
}
