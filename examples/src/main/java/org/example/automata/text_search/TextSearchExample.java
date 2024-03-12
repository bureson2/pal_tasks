package org.example.automata.text_search;

public class TextSearchExample {

    public static void main(String[] args) {
        // EXAMPLE 1 - NFA searching

        WordSearchNFA nfa = new WordSearchNFA("word");
        String testString = "twordisnotworld";
        System.out.println("Does the text contain 'word'? " + nfa.accepts(testString));

        // EXAMPLE 2 - Aho-Corasick alghoritm

        AhoCorasick ac = new AhoCorasick();
        ac.insert("he");
        ac.insert("she");
        ac.insert("hers");
        ac.insert("his");

        ac.buildFailLinks();

        String text = "ahishers";
        ac.search(text);

        // EXAMPLE 3 - Boyer-Moore algorithm

        String pat = "example";
        String txt = "This is an example of the Boyer Moore algorithm.";
        BoyerMoore boyermoore = new BoyerMoore(pat);
        int offset = boyermoore.search(txt);
        System.out.println("pattern found on position: " + offset);

    }
}
