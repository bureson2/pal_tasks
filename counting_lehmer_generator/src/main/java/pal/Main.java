package pal;

import java.io.IOException;
import java.util.*;

import static pal.PrimeUtil.*;
import static pal.PrimitiveRootUtil.findSmallestPrimitiveRoot;
import static pal.SieveOfEratosthenes.prepareSieveOfEratothenes;

public class Main {
    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader();
        reader.readInput();

        long Mmax = reader.Mmax;
        int D = reader.D;

        int L = 0; // Počet vhodných generátorů
        int Rmax = 0; // Maximální hodnota R mezi všemi generátory

        boolean[] isPrime = prepareSieveOfEratothenes((int) Mmax);
        // Generování množiny prvočísel až do hodnoty D
        List<Integer> primesUpToD = generatePrimesUpToD(D, isPrime);

        // Iterace přes všechna možná M a hledání nejmenšího primitivního kořene R
        for (int M = 2; M <= Mmax; M++) {

            // Kontrola, zda je M prvočíslo a zda PF(M-1) obsahuje pouze prvočísla menší než D
            if (isPrime[M] && hasPrimeFactorsLessThanD(M - 1, primesUpToD)) {
                int R = findSmallestPrimitiveRoot(M);
                if (R != -1) {
                    L++;
                    Rmax = Math.max(Rmax, R);
                }
            }
        }

        System.out.println(L + " " + Rmax);
    }
}
