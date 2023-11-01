package Cmd_Line.Small_Func;

import Cmd_Line.Word;
import java.util.Comparator;

public class SortDictionary implements Comparator<Word> {
    @Override
    public int compare(Word word1, Word word2) {
        return word1.getTarget().compareTo(word2.getTarget());
    }
}

