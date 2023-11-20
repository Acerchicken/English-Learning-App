package com.example.dictionary.Trie;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private Map<Character, Node> children;
    private boolean isEndOfWord;

    public Node() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
    }

    public Map<Character, Node> getChildren() {
        return this.children;
    }

    public boolean getEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        this.isEndOfWord = endOfWord;
    }
}
