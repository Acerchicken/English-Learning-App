package Bai_tap_lon.Small_Func;

import Bai_tap_lon.Word;
import java.util.Comparator;

public class Comparator_Word implements Comparator<Word> {
    @Override
    public int compare(Word word1, Word word2) {
        return word1.getTarget().compareTo(word2.getTarget());
    }
}

