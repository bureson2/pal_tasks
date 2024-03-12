package pal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputParser {

    public Park readLines(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));){
            String line, token = null, delimiter = " ";

            line = reader.readLine();
            String[] basicInformation = line.split(" ");
            Park park = new Park(
                    Integer.parseInt(basicInformation[0]),
                    Integer.parseInt(basicInformation[1]),
                    Integer.parseInt(basicInformation[2])
            );

            line = reader.readLine();
            while (line != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, delimiter);
                while(tokenizer.hasMoreTokens()) {
                    int source = Integer.parseInt(tokenizer.nextToken());
                    int destination = Integer.parseInt(tokenizer.nextToken());
                    park.addEdge(source, destination);
                }
                line = reader.readLine();
            }
            return park;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}