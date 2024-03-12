package org.example.automata;

public class AutomatonExample {
    public static void main(String[] args) {
        DFAnumberOfZeros dfaBasic = new DFAnumberOfZeros();
        String input = "001100";

        for (char symbol : input.toCharArray()) dfaBasic.transition(symbol);

        if (dfaBasic.isAccepting()) System.out.println("The automaton accepts " + input);
        else System.out.println("The automaton does not accept t" + input);

        DFA dfa = new DFA();
        dfa.addState(0);
        dfa.addState(1);
        dfa.addState(2);
        dfa.setInitialState(0);
        dfa.addFinalState(2);
        dfa.addTransition(0, 'a', 1);
        dfa.addTransition(1, 'b', 2);
        dfa.addTransition(2, 'a', 2);

        String testString = "abaaa";
        System.out.println("Does the DFA accept '" + testString + "'? " + dfa.accepts(testString));

        testString = "aba";
        NFA nfa = new NFA();
        nfa.addState(0);
        nfa.addState(1);
        nfa.addState(2);
        nfa.addState(3);
        nfa.addState(4);
        nfa.setInitialState(0);
        nfa.addFinalState(2);
        nfa.addFinalState(3);
        nfa.addTransition(0, 'a', 1);
        nfa.addTransition(1, 'b', 2);
        nfa.addTransition(2, 'a', 2);
        nfa.addTransition(2, 'b', 4);
        nfa.addTransition(0, 'a', 3);

        System.out.println("Does the NFA accept '" + testString + "'? " + nfa.accepts(testString));



    }
}
