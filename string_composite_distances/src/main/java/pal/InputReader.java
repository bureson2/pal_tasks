package pal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {
    public StringCompositeInput readLines() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String[] firstLine = reader.readLine().split(" ");
            int LS = Integer.parseInt(firstLine[0]);
            int LT = Integer.parseInt(firstLine[1]);
            int L1 = Integer.parseInt(firstLine[2]);
            int L2 = Integer.parseInt(firstLine[3]);

            char[] S = reader.readLine().toCharArray();
            char[] T = reader.readLine().toCharArray();

            return new StringCompositeInput(LS, LT, L1, L2, S, T);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
