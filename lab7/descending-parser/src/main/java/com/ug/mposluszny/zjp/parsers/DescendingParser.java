package com.ug.mposluszny.zjp.parsers;

import com.ug.mposluszny.zjp.parsers.model.Grammar;
import com.ug.mposluszny.zjp.parsers.model.ParsingResult;

public class DescendingParser extends Parser {

    private Grammar grammar;

    public DescendingParser(Grammar grammar) {
        this.grammar = grammar;
    }

    public ParsingResult parse(String input) {
        return null;
    }

    public Grammar getGrammar() {
        return grammar;
    }
}
