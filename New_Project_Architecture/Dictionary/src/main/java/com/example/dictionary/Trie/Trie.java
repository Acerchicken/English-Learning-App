package com.example.dictionary.Trie;

import java.util.*;

public class Trie {
    private Node root;

    public Trie() {
        this.root = new Node();
    }

    public void insert(String word) {
        Node current = root;

        for (char l : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(l, c -> new Node());
        }
        current.setEndOfWord(true);
    }

    private void findAllWordsFromNode(Node node, String currentPrefix, List<String> result) {
        if (node.getEndOfWord()) {
            result.add(currentPrefix);
        }

        Map<Character, Node> children = node.getChildren();
        for (Map.Entry<Character, Node> entry : children.entrySet()) {
            char ch = entry.getKey();
            Node childNode = entry.getValue();
            findAllWordsFromNode(childNode, currentPrefix + ch, result);
        }
    }

    public List<String> findAllPrefixWords(String prefix) {
        List<String> result = new ArrayList<>();
        Node prefixNode = findNode(prefix);

        if (prefixNode != null) {
            findAllWordsFromNode(prefixNode, prefix, result);
        }

        return result;
    }

    private Node findNode(String prefix) {
        Node current = root;

        for (char l : prefix.toCharArray()) {
            current = current.getChildren().get(l);

            if (current == null) {
                return null;
            }
        }
        return current;
    }
}
