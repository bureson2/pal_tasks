package org.example.automata.text_search;

import java.util.ArrayList;
import java.util.List;

public class AhoCorasickTrieNode {
    private static final int ALPHABET_SIZE = 26;
    public AhoCorasickTrieNode[] children = new AhoCorasickTrieNode[ALPHABET_SIZE];
    public AhoCorasickTrieNode failNode;
    public List<String> outputs = new ArrayList<>();


    public AhoCorasickTrieNode() {
        failNode = null;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            children[i] = null;
        }
    }
}
