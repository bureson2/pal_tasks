package org.example.automata;

public class DFAnumberOfZeros {
    private enum State {
        EVEN_ZERO, ODD_ZERO
    }

    private State currentState;

    public DFAnumberOfZeros() {
        this.currentState = State.EVEN_ZERO;
    }

    public void transition(char symbol) {
        switch (currentState) {
            case EVEN_ZERO:
                if (symbol == '0') {
                    currentState = State.ODD_ZERO;
                }
                break;
            case ODD_ZERO:
                if (symbol == '0') {
                    currentState = State.EVEN_ZERO;
                }
                break;
        }
    }


    public boolean isAccepting() {
        return currentState == State.EVEN_ZERO;
    }
}
