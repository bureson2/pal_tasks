package org.example.automata.text_search;

import java.util.*;

public class WordSearchNFA {
    private Map<Integer, Map<Character, Set<Integer>>> transitionTable;
    private int initialState;
    private Set<Integer> finalStates;

    public WordSearchNFA(String word) {
        transitionTable = new HashMap<>();
        finalStates = new HashSet<>();
        buildNFA(word);
    }

    private void buildNFA(String word) {
        int n = word.length();
        for (int i = 0; i <= n; i++) {
            transitionTable.putIfAbsent(i, new HashMap<>());

            if (i < n) {
                char currentChar = word.charAt(i);
                transitionTable.get(i).put(currentChar, new HashSet<>(Arrays.asList(i + 1)));
            }

            // Přidání přechodů pro ostatní znaky
            for (char c = 'a'; c <= 'z'; c++) {
                int nextState = findNextState(word, i, c);
                transitionTable.get(i).putIfAbsent(c, new HashSet<>());
                transitionTable.get(i).get(c).add(nextState);
            }
        }

        finalStates.add(n);
        initialState = 0;
    }

    private int findNextState(String word, int currentIndex, char nextChar) {
        for (int i = Math.max(1, currentIndex); i > 0; i--) {
            if (word.substring(0, i).equals(word.substring(0, Math.min(currentIndex, word.length())) + nextChar)) {
                return i;
            }
        }
        return 0;
    }

    public boolean accepts(String input) {
        Set<Integer> currentStates = new HashSet<>();
        currentStates.add(initialState);
        boolean isFinalStateReached = false;

        for (char symbol : input.toCharArray()) {
            Set<Integer> nextStates = new HashSet<>();
            for (int state : currentStates) {
                Set<Integer> transitions = transitionTable.getOrDefault(state, Collections.emptyMap()).get(symbol);
                if (transitions != null) {
                    nextStates.addAll(transitions);
                    if (transitions.stream().anyMatch(finalStates::contains)) {
                        isFinalStateReached = true;
                    }
                }
            }
            currentStates = nextStates;
        }

        return isFinalStateReached;
    }


}