package org.example.permutations.graycode;

import static org.example.permutations.graycode.GrayCodeConverter.grayToNormal;
import static org.example.permutations.graycode.GrayCodeConverter.normalToGray;

public class GrayCodeExample {
    public static void main(String[] args) {

        int normalNumber = 5;
        String grayCode = normalToGray(normalNumber);
        int convertedBack = grayToNormal(grayCode);

        System.out.println("Decimal number: " + normalNumber);
        System.out.println("Gray code: " + grayCode);
        System.out.println("Converted back: " + convertedBack);
    }

}
