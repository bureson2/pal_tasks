package pal;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        long A = Long.parseLong(tokens[0]);
        long B = Long.parseLong(tokens[1]);
        long C = Long.parseLong(tokens[2]);
        long D = Long.parseLong(tokens[3]);
        int E = Integer.parseInt(tokens[4]);
        int F = Integer.parseInt(tokens[5]);

        List<Long> primes = findPrimes(A, B);

        BigInteger product = BigInteger.ONE;
        BigInteger sum = BigInteger.ZERO;
        int count = 0;

        for (long prime : primes) {
            if (countFCompositePrimitiveRoots(prime, C, D, F) >= E) {
                BigInteger bigPrime = BigInteger.valueOf(prime);
                product = product.multiply(bigPrime);
                sum = sum.add(bigPrime);
                count++;
            }
        }

        product = product.mod(sum);

        System.out.println(count + " " + product);
    }

    private static long mod(long a, long b) {
        long result = a % b;
        if (result < 0) result += b;
        return result;
    }

    private static List<Long> findPrimes(long A, long B) {
        int segmentSize = (int) Math.sqrt(B) + 1;
//        int segmentSize = Math.max((int) Math.sqrt(B) + 1, 1000000); // Zvýšení velikosti segmentu

        List<Long> primes = new ArrayList<>();
        boolean[] prime = new boolean[segmentSize];
        Arrays.fill(prime, true);

        for (long i = 2; i * i <= B; i++) {
            if (prime[(int) i]) {
                for (long j = i * i; j < segmentSize; j += i) {
                    prime[(int) j] = false;
                }
            }
        }

        boolean[] isPrime = new boolean[segmentSize];
        for (long low = A; low <= B; low += segmentSize) {
            Arrays.fill(isPrime, true);
            long high = Math.min(low + segmentSize - 1, B);

            for (int i = 2; i < segmentSize; i++) {
                if (prime[i]) {
                    long minMultiple = (low + i - 1) / i * i;
                    if (minMultiple < low) minMultiple += i;

                    for (long j = minMultiple; j <= high; j += i) {
                        isPrime[(int) (j - low)] = false;
                    }
                }
            }

            for (long j = low; j <= high; j++) {
                if (isPrime[(int) (j - low)] && j >= A) {
                    primes.add(j);
                }
            }
        }

        return primes;
    }


    private static int countFCompositePrimitiveRoots(long prime, long C, long D, int F) {
        int count = 0;
        for (long i = C; i <= D; i++) {
            if (isPrimitiveRoot(i, prime) && isFComposite(i, F)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isPrimitiveRoot(long a, long p) {
        if (a == 0 || a == 1) return false;
        long phi = p - 1;
        for (long factor : getFactors(phi)) {
            if (modularExponentiation(a, phi / factor, p) == 1) {
                return false;
            }
        }
        return true;
    }

    private static List<Long> getFactors(long n) {
        List<Long> factors = new ArrayList<>();
        for (long i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                factors.add(i);
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        if (n > 1) factors.add(n);
        return factors;
    }

    private static long modularExponentiation(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;
        while (exp > 0) {
            if (exp % 2 == 1) result = (result * base) % mod;
            exp = exp >> 1;
            base = (base * base) % mod;
        }
        return result;
    }


    private static boolean isFComposite(long number, int F) {
        int divisors = 2; // 1 a samotné číslo
        for (long i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                divisors += (number / i == i) ? 1 : 2;
            }
            if (divisors >= F) return true;
        }
        return divisors >= F;
    }
}