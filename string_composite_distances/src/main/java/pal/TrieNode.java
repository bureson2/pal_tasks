package pal;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    TrieNode children0 = null;
    TrieNode children1 = null;
    boolean hasChildren = false;
    boolean isEndOfWord = false;
    int numberOfSubstringsInTrieA = 0;

    public TrieNode() {}

    public void setChildren0(TrieNode children0) {
        hasChildren = true;
        this.children0 = children0;
    }

    public void setChildren1(TrieNode children1) {
        hasChildren = true;
        this.children1 = children1;
    }
}
