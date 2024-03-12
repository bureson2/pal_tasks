package pal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputReader {
    public long Mmax;
    public int D;

    public void readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        Mmax = Long.parseLong(tokenizer.nextToken());
        D = Integer.parseInt(tokenizer.nextToken());
    }
}
