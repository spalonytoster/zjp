package com.ug.mposluszny.zjp.parsers;

import com.ug.mposluszny.zjp.parsers.model.ParsingResult;
import com.ug.mposluszny.zjp.parsers.model.Tree;

public abstract class Parser {

    String input;
    private int index;

    public abstract ParsingResult parse(String input);

    Parser() {
        init();
    }

    void init() {
        index = -1;
    }

    private void backtrack() {
        index--;
    }

    boolean checkWholeInputParsed() {
        return index == input.length()-1;
    }

    ParsingResult checkTerminal(char terminal) {
        if (index < input.length()-1 && terminal == input.charAt(++index)) {
            return new ParsingResult(true, new Tree(terminal));
        }
        backtrack();
        return new ParsingResult(false, null);
    }

}
