package org.example.permutations.graycode;

public class GrayCodeConverter {
    public static String normalToGray(int num) {
        return Integer.toBinaryString(num ^ (num >> 1));
    }

    public static int grayToNormal(String grayCode) {
        int num = Integer.parseInt(grayCode, 2);
        int grayCodeNumber = num;

        while (grayCodeNumber > 0) {
            grayCodeNumber >>= 1;
            num ^= grayCodeNumber;
        }
        return num;
    }
}
