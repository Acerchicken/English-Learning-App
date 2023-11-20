package com.example.dictionary.Models;

public class Word {
    private String target;
    private String explain;

    public Word() {
        target = "";
        explain = "";
    }

    ;

    public Word(String target, String explain) {
        this.target = target;
        this.explain = explain;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getExplain() {
        return explain;
    }

    public int compareTo(String target) {
        if (this.target.compareTo(target) < 0) {
            return -1;
        } else if (this.target.compareTo(target) > 0) {
            return 1;
        }
        return 0;
    }
}
