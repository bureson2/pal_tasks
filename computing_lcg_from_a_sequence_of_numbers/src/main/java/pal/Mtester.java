package pal;

import java.util.ArrayList;
import java.util.List;

public class Mtester {
    private List<Integer> primes;
    private int nOPrimes;
    public List<Integer> primeFactors = new ArrayList<>();
    public int lastPrimeFactorIndex = 0;

    public Mtester(List<Integer> primes) {
        this.primes = primes;
        this.nOPrimes = primes.size();
    }

    public boolean testM(long M){
        boolean isSuccess = false;
        primeFactors.clear();
        lastPrimeFactorIndex = 0;

        for (int i = 0; i < nOPrimes; i++){
            long primeSquare = primes.get(i) * primes.get(i);

            if (primeSquare > M) return false;
            double tempM = M / (double) primeSquare;
            if (tempM == (long) tempM) {
                isSuccess = testMRec((long) tempM, i + 1, isSuccess);
                if (isSuccess) {
                    primeFactors.add(primes.get(i));
                    return true;
                }
            }
        }

        return isSuccess;
    }

    private boolean testMRec(long currentM, int startIndex, boolean isSuccess){
        if (currentM == 1) return true;

        for (int i = startIndex; i < nOPrimes; i++){
            long primeSquare = primes.get(i) * primes.get(i);

            if (primeSquare > currentM) return false;
            double tempM = currentM / (double) primeSquare;
            if (tempM == (long) tempM) {
                isSuccess = testMRec((long) tempM, i + 1, isSuccess);
                if (isSuccess) {
                    primeFactors.add(primes.get(i));
                    lastPrimeFactorIndex++;
                    return true;
                }
            }
        }

        return false;
    }
}
