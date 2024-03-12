package pal;


import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        InputReader reader = new InputReader();
        List<Integer> primes = SieveOfEratosthenes.sieveOfEratosthenesAsPrimeList(reader.Pmax);
        Mtester tester = new Mtester(primes);

//        int x2 = reader.sequence.get(1);
        int x2 = reader.sequence[1];
        long M = 4;
        long A, C;
//        int counter = 0;

        for (; M <= reader.Mmax; M++) {
            // testM validity
            if (tester.testM(M)) {
                boolean increment4 = M % 4 == 0;

                long startA = findStartAminus1(tester.primeFactors, tester.lastPrimeFactorIndex);

                if (increment4) {
                    startA = lcm(4, startA);
                }

                // A is divisible by all prime factors
                for (A = startA + 1; A < M; A = A + startA) {
                    for (C = 1; C < M; C++) {
                        if (isGcdCM1(tester.primeFactors, C)) {
                            long seedMultiplicativeInverse = multiplicativeInverse(A, M);
                            long seed = ((x2 - C + M) * seedMultiplicativeInverse) % M;

//                            counter++;
                            boolean testResult = SequenceValidator.validateSequence(reader.sequence, A, C, M, seed, reader.N);
                            if (testResult) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(A).append(" ").append(C).append(" ").append(M);
                                System.out.println(sb);
//                                System.out.println(counter);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public static long multiplicativeInverse(long a, long modulus) {
        long s = 0, r = modulus, old_s = 1, old_r = a;
        while (r != 0) {
            long quotient = old_r / r;
            long temp = r;
            r = old_r - quotient * r;
            old_r = temp;
            temp = s;
            s = old_s - quotient * s;
            old_s = temp;
        }
        return old_s < 0 ? old_s + modulus : old_s;
    }

    // Pocitat potencialni smysluplnost podle faktoru
    public static boolean isGcdCM1(List<Integer> primeFactors, long C) {
        for ( Integer factor : primeFactors){
            long tempC = C % factor;
            if (tempC == 0) return false;
        }
        return true;
    }

    public static long gcd(long a, long b) {
//        return b == 0 ? (a < 0 ? -a : a) : gcd(b, a % b);
        return b == 0 ? a : gcd(b, a % b);
    }

    public static boolean testAminus1Divisibility(List<Integer> primeFactors, long Aminus1) {

        for (int factor : primeFactors) {
            if (Aminus1 % factor == 0) continue;
            else return false;
        }
        return true;
    }

    public static long lcm(long a, long b) {
        long lcm = (a / gcd(a, b)) * b;
        return lcm > 0 ? lcm : -lcm;
    }

    public static long findStartAminus1(List<Integer> primeFactors, int primeFactorLastIndex) {
        long startVal = primeFactors.get(0);
        for (int i = 1; i <= primeFactorLastIndex; i++) {
            startVal = lcm(startVal, primeFactors.get(i));
        }
        return startVal;
    }
}