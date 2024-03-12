package pal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class InputReader {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;
    public int LA;
    public int CA;
    public int DO;
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
        LA = nextInt(); // Read the number of lakes
        CA = nextInt(); // Read the number of cannals
        DO = nextInt(); // Read the label of dock

        adjencyList = new ArrayList<>();

        for (int i = 0; i < LA; i++){
            adjencyList.add(new ArrayList<>());
        }

        for (int i = 0; i < CA; i++) {
            int start = nextInt();
            int end = nextInt();

            adjencyList.get(start).add(end);

        }

        reader.close();

    }
}
