package pal;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    public int totalPathLength = 0;
    public int numberOfBranches = 1;
    public int productSum = 0;


    // Method to insert all substrings of a given string within a specified range
    public void insertSubstrings(char[] text, int textLength, int minLength, int maxLength) {
        int searchedLength = textLength - minLength + 1;


        for (int i = 0; i < searchedLength; i++) {
            TrieNode node = root;
            for (int j = i; j < textLength && j < i + maxLength; j++) {
                char currentChar = text[j];

                boolean addBranch = node.hasChildren;
                if (currentChar == '0'){
                    if (node.children0 == null){
                        TrieNode newNode = new TrieNode();
                        node.setChildren0(newNode);
                        if (addBranch) numberOfBranches++;
                    }
                    node = node.children0;
                } else {
                    if (node.children1 == null){
                        TrieNode newNode = new TrieNode();
                        node.setChildren1(newNode);
                        if (addBranch) numberOfBranches++;
                    }
                    node = node.children1;
                }

                int depth = j - i + 1;
                if (depth >= minLength) {
                    if (!node.isEndOfWord && (j == i + maxLength - 1 || j == textLength - 1)) {
                        totalPathLength += depth;
                    }
                    node.isEndOfWord = true;
                    node.numberOfSubstringsInTrieA++;
                }
            }
        }
    }

    public void insertSubstringsFromSecondWord(char[] text, int textLength, int minLength, int maxLength) {
        int searchedLength = textLength - minLength + 1;

        for (int i = 0; i < searchedLength; i++) {
            TrieNode node = root;
            for (int j = i; j < textLength && j < i + maxLength; j++) {

                char currentChar = text[j];

                TrieNode childNode = currentChar == '0' ? node.children0 : node.children1;

                if (childNode == null) {
                    break;
                }
                node = childNode;

                if (j - i + 1 >= minLength) {
//                    (j-i+1) depth
                    productSum += (j-i+1) * node.numberOfSubstringsInTrieA;
                }
            }
        }
    }
}
