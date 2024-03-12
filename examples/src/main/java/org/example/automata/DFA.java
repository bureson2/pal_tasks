package org.example.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DFA {
    private Set<Integer> states;
    private Map<Integer, Map<Character, Integer>> transitionTable;
    private int initialState;
    private Set<Integer> finalStates;

    public DFA() {
        states = new HashSet<>();
        transitionTable = new HashMap<>();
        finalStates = new HashSet<>();
    }

    public void addState(int state) {
        states.add(state);
        transitionTable.put(state, new HashMap<>());
    }

    public void setInitialState(int state) {
        if (states.contains(state)) {
            initialState = state;
        } else {
            throw new IllegalArgumentException("State does not exist.");
        }
    }

    public void addFinalState(int state) {
        if (states.contains(state)) {
            finalStates.add(state);
        } else {
            throw new IllegalArgumentException("State does not exist.");
        }
    }

    public void addTransition(int fromState, char symbol, int toState) {
        if (!states.contains(fromState) || !states.contains(toState)) {
            throw new IllegalArgumentException("State does not exist.");
        }
        transitionTable.get(fromState).put(symbol, toState);
    }

    public boolean accepts(String input) {
        int currentState = initialState;
        for (char symbol : input.toCharArray()) {
            Map<Character, Integer> transitions = transitionTable.get(currentState);
            if (transitions.containsKey(symbol)) {
                currentState = transitions.get(symbol);
            } else {
                return false; // Symbol not found in transition table
            }
        }
        return finalStates.contains(currentState);
    }

}
