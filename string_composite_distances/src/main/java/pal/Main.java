package pal;

public class Main {
    public static void main(String[] args){
        InputReader reader = new InputReader();
        StringCompositeInput input = reader.readLines();

        Trie trie = new Trie();

        trie.insertSubstrings(input.wordA, input.wordALength, input.minSearchedLength, input.maxSearchedLength);
        trie.insertSubstringsFromSecondWord(input.wordB, input.wordBLength, input.minSearchedLength, input.maxSearchedLength);

        int rcd = trie.productSum;
        int leaves = trie.numberOfBranches;
        int totalPathLength = trie.totalPathLength;

        System.out.println(
                rcd
                + " " + leaves
                + " " + totalPathLength
        );
    }
}
