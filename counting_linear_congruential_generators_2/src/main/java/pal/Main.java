package pal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static Map<Integer, List<Integer>> primeFactorsCache;
    private static Map<Integer, Integer> divisorsCountCache;

    public static void main(String[] args) throws IOException {
        primeFactorsCache = new HashMap<>();
        divisorsCountCache = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Načtení rozsahů A, C a M
        String[] aMinMax = reader.readLine().split(" ");
        int aMin = Integer.parseInt(aMinMax[0]);
        int aMax = Integer.parseInt(aMinMax[1]);

        String[] cMinMax = reader.readLine().split(" ");
        int cMin = Integer.parseInt(cMinMax[0]);
        int cMax = Integer.parseInt(cMinMax[1]);

        String[] mMinMax = reader.readLine().split(" ");
        int mMin = Integer.parseInt(mMinMax[0]);
        int mMax = Integer.parseInt(mMinMax[1]);

        // Načtení D
        int d = Integer.parseInt(reader.readLine());

        reader.close();

        // Předvýpočet prvočíselných faktorů a dělitelů pro všechna M
        precomputeForAllM(mMin, mMax, d);

        int count = 0;
        for (int m = mMin; m <= mMax; m++) {
            if (divisorsCountCache.get(m) >= d) {
                List<Integer> primeFactorsOfM = primeFactorsCache.get(m);
                for (int a = aMin; a <= aMax; a++) {
                    if (isAValid(a, m, primeFactorsOfM)) {
                        for (int c = cMin; c <= cMax; c++) {
                            if (areCoprime(c, m)) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }

    private static void precomputeForAllM(int mMin, int mMax, int d) {
        for (int m = mMin; m <= mMax; m++) {
            primeFactorsCache.put(m, getPrimeFactors(m));
            divisorsCountCache.put(m, countDivisors(m));
        }
    }

    private static int countDivisors(int number) {
        int divisors = 0;
        int sqrt = (int) Math.sqrt(number);
        for (int i = 1; i <= sqrt; i++) {
            if (number % i == 0) {
                divisors += (number / i == i) ? 1 : 2;
            }
        }
        return divisors;
    }

    private static boolean hasAtLeastDDivisors(int number, int d) {
        if (divisorsCountCache.containsKey(number)) {
            return divisorsCountCache.get(number) >= d;
        }

        int divisors = 0;
        int sqrt = (int) Math.sqrt(number);
        for (int i = 1; i <= sqrt; i++) {
            if (number % i == 0) {
                divisors += (number / i == i) ? 1 : 2;
            }
        }

        divisorsCountCache.put(number, divisors);
        return divisors >= d;
    }

    private static boolean isAValid(int a, int m, List<Integer> primeFactorsOfM) {
        if (m % 4 == 0 && (a - 1) % 4 != 0) {
            return false;
        }
        for (int factor : primeFactorsOfM) {
            if ((a - 1) % factor != 0) {
                return false;
            }
        }
        return true;
    }

    private static List<Integer> getPrimeFactors(int number) {
        if (primeFactorsCache.containsKey(number)) {
            return primeFactorsCache.get(number);
        }

        List<Integer> primeFactors = new ArrayList<>();
        int n = number;
        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                primeFactors.add(i);
                n /= i;
            }
        }
        if (n > 1) {
            primeFactors.add(n);
        }

        primeFactorsCache.put(number, primeFactors);
        return primeFactors;
    }

    private static boolean areCoprime(int a, int b) {
        return gcd(a, b) == 1;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}