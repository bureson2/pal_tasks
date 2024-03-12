package org.example.automata.text_search;

import java.util.LinkedList;
import java.util.Queue;

public class AhoCorasick {
    private static final int ALPHABET_SIZE = 26;
    private AhoCorasickTrieNode root;

    public AhoCorasick() {
        root = new AhoCorasickTrieNode();
    }

    public void insert(String key) {
        AhoCorasickTrieNode node = root;
        for (int level = 0; level < key.length(); level++) {
            int index = key.charAt(level) - 'a';
            if (node.children[index] == null)
                node.children[index] = new AhoCorasickTrieNode();
            node = node.children[index];
        }
        node.outputs.add(key);
    }

    public void buildFailLinks() {
        Queue<AhoCorasickTrieNode> queue = new LinkedList<>();
        root.failNode = root;
        queue.add(root);

        while (!queue.isEmpty()) {
            AhoCorasickTrieNode node = queue.remove();

            for (int i = 0; i < ALPHABET_SIZE; i++) {
                AhoCorasickTrieNode child = node.children[i];
                if (child != null) {
                    child.failNode = (node == root) ? root : node.failNode.children[i];
                    if (child.failNode == null) child.failNode = root;
                    queue.add(child);
                } else {
                    if (node != root)
                        node.children[i] = node.failNode.children[i];
                }
            }
        }
    }

    public void search(String text) {
        AhoCorasickTrieNode node = root;
        for (int i = 0; i < text.length(); i++) {
            int index = text.charAt(i) - 'a';
            while (node != root && node.children[index] == null) {
                node = node.failNode;
            }
            node = (node.children[index] != null) ? node.children[index] : root;

            if (!node.outputs.isEmpty()) {
                for (String word : node.outputs) {
                    System.out.println("Searched word '" + word + "' on position " + (i - word.length() + 1));
                }
            }
        }
    }
}
