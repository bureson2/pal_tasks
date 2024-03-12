package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class InputReader {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;
    public int N;
    public int M;
    public List<List<Integer>> adjencyList;

    public InputReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public void readInput() throws IOException {
        N = nextInt(); // Read number of minibus station
        M = nextInt(); // Read the number of connections

        adjencyList = new ArrayList<>();

        for (int i = 0; i < N; i++){
            adjencyList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int start = nextInt();
            int end = nextInt();

            adjencyList.get(start).add(end);

        }

        reader.close();

    }

}
