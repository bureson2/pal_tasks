package pal;

public class StringCompositeInput {
    public final int wordALength, wordBLength, minSearchedLength, maxSearchedLength;
//    public final String wordA, wordB;
    public final char[] wordA, wordB;


    public StringCompositeInput(int LS, int LT, int l1, int l2, char[] s, char[] t) {
        this.wordALength = LS;
        this.wordBLength = LT;
        minSearchedLength = l1;
        maxSearchedLength = l2;
        wordA = s;
        wordB = t;
    }
}
