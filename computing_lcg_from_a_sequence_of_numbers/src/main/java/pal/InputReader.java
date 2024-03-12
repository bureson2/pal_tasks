package pal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class InputReader {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    public int Pmax;
    public int Mmax;
    public int N;
//    public List<Integer> sequence = new ArrayList<>();
    public int[] sequence;

    // Make it faster with tokenizer
    public InputReader() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        String[] params = reader.readLine().split(" ");
        Pmax = Integer.parseInt(params[0]);
        Mmax = Integer.parseInt(params[1]);
        N = Integer.parseInt(params[2]);
        sequence = new int[N];

        String[] sequenceNumbers = reader.readLine().split(" ");
        int index = 0;
        for (String number : sequenceNumbers){
//            sequence.add(Integer.parseInt(number));
            sequence[index] = Integer.parseInt(number);
            index++;
        }
    }


}
