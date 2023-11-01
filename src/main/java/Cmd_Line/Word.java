package Cmd_Line;

public class Word {
    private String target;
    private String explain;

    public Word(){};

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


}
