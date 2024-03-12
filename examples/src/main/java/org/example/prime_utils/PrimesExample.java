package org.example.prime_utils;

import java.math.BigInteger;

import static org.example.prime_utils.ErastosthenesSieve.generatePrimes;

public class PrimesExample {
    public static void main(String[] args) {

        boolean[] primes = generatePrimes(100);
        printPrimes(primes);

        // PREPARE SOME NOT SMALL PRIME
        primes = generatePrimes(100000000);
        int primeForTest = primes.length-1;
        while (!primes[primeForTest]){
            primeForTest--;
        }

//      EXAMPLE 1 - Fermat Little Theorem
        boolean primeTest = PrimalityTest.fermatTest(primeForTest, 5);
        System.out.println("Number " + primeForTest + " is " + (primeTest ? "probably prime." : "composite."));

//      EXAMPLE 2 - Miller Rabin primality test
        primeTest = PrimalityTest.millerRabinTest(primeForTest, 5);
        System.out.println("Number " + primeForTest + " is " + (primeTest ? "probably prime." : "composite."));

//        EXAMPLE 3 - Integer factorization - The biggest GCD [Pollard Rho algorithm]
        BigInteger n = new BigInteger("8051");
        BigInteger factor = PollardsRhoAlghoritm.solve(n);
        if (!factor.equals(BigInteger.ONE)) System.out.println("Searched factor of number " + n + " is: " + factor);
        else System.out.println("No factor is find " + n);

        n = new BigInteger(String.valueOf(primeForTest));
        factor = PollardsRhoAlghoritm.solve(n);
        if (!factor.equals(BigInteger.ONE)) System.out.println("Searched factor of number " + n + " is: " + factor);
        else System.out.println("No factor is find " + n);

    }

    private static void printPrimes(boolean[] primes){
        for (int i = 2; i < primes.length; i++) {
            if (primes[i]) {
                System.out.print(i + ", ");
            }
        }
        System.out.println();
    }

}
