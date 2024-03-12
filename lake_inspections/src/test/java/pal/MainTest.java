package pal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    private static final int[] TEST_CASES = new int[]{
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10
    };
    private static final String SOURCE_DIR = "public_data/";

    private InputStream originalSystemIn;
    private PrintStream originalSystemOut;
    private ByteArrayOutputStream redirectedOutput;

    @BeforeEach
    void init() {
        originalSystemIn = System.in;
        originalSystemOut = System.out;
        redirectedOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(redirectedOutput));
    }

    @AfterEach
    void clean() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @MethodSource("graphTestSource")
    @ParameterizedTest
    void graphTest(String inputFileName, String outputFileName) throws IOException {
        System.setIn(new FileInputStream(SOURCE_DIR + inputFileName));
        String output = readFile(SOURCE_DIR + outputFileName);

        Main.main(new String[0]);
        String actualOutput = redirectedOutput.toString();

        assertEquals(output, actualOutput);
    }

    private static Stream<Arguments> graphTestSource() {
        Stream.Builder<Arguments> builder = Stream.builder();

        String name = "pub%02d.%s";
        for (int testCase : TEST_CASES) {
            builder.add(Arguments.of(
                    String.format(name, testCase, "in"),
                    String.format(name, testCase, "out")
            ));
        }

        return builder.build();
    }

    private String readFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine())
                        .append(System.lineSeparator());
            }
            return content.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}