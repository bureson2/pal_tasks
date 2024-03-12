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
    int N; // Number of crossings
    int M; // Number of streets
    List<Street> streets; // List to store streets

    public InputReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        tokenizer = null;
        streets = new ArrayList<>();
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

    public void close() throws IOException {
        reader.close();
    }

    public void readInput() throws IOException {
        N = nextInt(); // Read number of crossings
        M = nextInt(); // Read number of streets

        // Initialize streets list with empty Street objects
        for (int i = 0; i < N; i++) {
            streets.add(new Street(i));
        }

        // Read streets and their neighbors
        for (int i = 0; i < M; i++) {
            int start = nextInt();
            int end = nextInt();

            streets.get(start).addNeighbor(streets.get(end));
        }
    }
}
