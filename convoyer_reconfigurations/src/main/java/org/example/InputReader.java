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
    public int C;
    public List<Factory> factories;

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
        N = nextInt(); // Read number of points in the factory
        M = nextInt(); // Read the number of conveyors in the factory
        C = nextInt(); // Read the label of the point where the central store is located

        factories = new ArrayList<>();

        for (int i = 0; i < N; i++){
            factories.add(new Factory(i));
        }

        for (int i = 0; i < M; i++) {
            int start = nextInt();
            int end = nextInt();

            factories.get(start).addConvoyer(factories.get(end));
            factories.get(end).addOpositeConvoyer(factories.get(start));
        }

        reader.close();

    }
}
