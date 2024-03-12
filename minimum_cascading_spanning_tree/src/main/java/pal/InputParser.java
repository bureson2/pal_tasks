package pal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputParser {

    private Graph graph;

    public void readLines(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));){

            String[] basicInformation = reader.readLine().split(" ");
            graph = new Graph(
                    Integer.parseInt(basicInformation[0])
            );

            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] parameters = line.split(" ");
                graph.addEdge(
                        Integer.parseInt(parameters[0])-1,
                        Integer.parseInt(parameters[1])-1,
                        Integer.parseInt(parameters[2])
                        );
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Graph getGraph() {
        return graph;
    }
}
