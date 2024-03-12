package org.example.random_generators;

import org.example.random_generators.generators.BlumBlumShubGenerator;
import org.example.random_generators.generators.CombinedLinearCongruentGenerator;
import org.example.random_generators.generators.LehmerGenerator;
import org.example.random_generators.generators.LinearCongruentGenerator;

public class RandomGeneratorExample {
    public static void main(String[] args) {

        // EXAMPLE 1
        LinearCongruentGenerator lcg = new LinearCongruentGenerator(4, 18, 7, 5 );
        printLCG(lcg, 18);

        // EXAMPLE 2
        lcg = new LinearCongruentGenerator(8, 15, 11, 6 );
        printLCG(lcg, 10);

        // EXAMPLE 3
        lcg = new LinearCongruentGenerator(7, 13, 5, 11 );
        printLCG(lcg, 1);

        // EXAMPLE 4 - HULL-DOBELL THEOREM TEST
        System.out.print("\n\nHULL-DOBELL THEOREM TEST:");
        //        Condition 1. violated
        lcg = new LinearCongruentGenerator(0, 18, 7, 6 );
        printLCG(lcg, 3);
        HullDobellTheoremChecker.checkConditions(lcg.getM(), lcg.getA(), lcg.getC());

        //        Condition 2. violated
        lcg = new LinearCongruentGenerator(0, 20, 17, 7 );
        printLCG(lcg, 4);
        HullDobellTheoremChecker.checkConditions(lcg.getM(), lcg.getA(), lcg.getC());

        //        Condition 2. violated
        lcg = new LinearCongruentGenerator(0, 17, 7, 6 );
        printLCG(lcg, 16);
        HullDobellTheoremChecker.checkConditions(lcg.getM(), lcg.getA(), lcg.getC());

        //        Condition 3. violated
        lcg = new LinearCongruentGenerator(0, 20, 11, 7 );
        printLCG(lcg, 10);
        HullDobellTheoremChecker.checkConditions(lcg.getM(), lcg.getA(), lcg.getC());

        // All three conditions hold
        lcg = new LinearCongruentGenerator(0, 18, 7, 5 );
        printLCG(lcg, 18);
        HullDobellTheoremChecker.checkConditions(lcg.getM(), lcg.getA(), lcg.getC());

        // EXAMPLE 5 - Combined linear congruent generator
        CombinedLinearCongruentGenerator clcg = new CombinedLinearCongruentGenerator(12345, 67890, 2147483563, 40014, 0, 2147483399, 40692, 0);
        for (int i = 0; i < 10; i++) {
            System.out.print(clcg.next() + ", ");
        }
        System.out.println();

        // EXAMPLE 6
        LehmerGenerator lg = new LehmerGenerator(1, 13, 6);
        printLG(lg, 12);

        // EXAMPLE 7
        lg = new LehmerGenerator(2, 13, 5);
        printLG(lg, 4);

        // EXAMPLE 8
        BlumBlumShubGenerator bbsg = new BlumBlumShubGenerator(4,11,47);
        System.out.printf("\nBlum Blum Shub generator: M=%d%n", bbsg.getM());
        for (int i = 0; i < 50; i++){
            if (i < 44 ) System.out.print("\u001B[32m");
            else System.out.print("\u001B[0m");
            System.out.print(bbsg.next() + ", ");
        }
    }

    private static void printLCG(LinearCongruentGenerator lcg, int printColorIndex){
        System.out.printf("\nLCG: M=%d, A=%d, C=%d%n", lcg.getM(), lcg.getA(), lcg.getC());
        for (int i = 0; i < 2* lcg.getM(); i++){
            if (i < printColorIndex ) System.out.print("\u001B[32m");
            else System.out.print("\u001B[0m");
            System.out.print(lcg.next() + ", ");
        }
    }

    private static void printLG(LehmerGenerator lg, int printColorIndex){
        System.out.printf("\nLehmer generator: M=%d, A=%d%n", lg.getM(), lg.getA());
        for (int i = 0; i < 2* lg.getM(); i++){
            if (i < printColorIndex ) System.out.print("\u001B[32m");
            else System.out.print("\u001B[0m");
            System.out.print(lg.next() + ", ");
        }
    }

}
