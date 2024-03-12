package org.example.automata.text_search;

public class BoyerMoore {
    private final int R;     // The radix; the size of the alphabet
    private int[] right;     // The rightmost occurrence of each character in the pattern
    private String pat;      // The pattern as a string

    // Constructor for the Boyer-Moore pattern matching algorithm
    public BoyerMoore(String pat) {
        this.R = 256; // Assuming an extended ASCII character set
        this.pat = pat;

        // Compute the rightmost occurrence of each character in the alphabet within the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1; // Initialize all characters as not found
        for (int j = 0; j < pat.length(); j++)
            right[pat.charAt(j)] = j; // Set the rightmost position for characters in the pattern
    }

    // Method to search the pattern in the given text
    public int search(String txt) {
        int m = pat.length();
        int n = txt.length();
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    // If a mismatch is found, calculate the skip distance using the right array
                    skip = Math.max(1, j - right[txt.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) return i;    // Pattern found
        }
        return n;                      // Pattern not found
    }
}
