package org.example.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NFA {
    private Set<Integer> states;
    private Map<Integer, Map<Character, Set<Integer>>> transitionTable;
    private int initialState;
    private Set<Integer> finalStates;

    public NFA() {
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
        transitionTable.get(fromState).computeIfAbsent(symbol, k -> new HashSet<>()).add(toState);
    }

    public boolean accepts(String input) {
        return accepts(input, initialState);
    }

    private boolean accepts(String input, int currentState) {
        if (input.isEmpty()) {
            return finalStates.contains(currentState);
        }

        char symbol = input.charAt(0);
        String remainingInput = input.substring(1);

        if (transitionTable.get(currentState).containsKey(symbol)) {
            for (int nextState : transitionTable.get(currentState).get(symbol)) {
                if (accepts(remainingInput, nextState)) {
                    return true;
                }
            }
        }

        return false;
    }
}
