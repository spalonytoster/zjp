package com.ug.mposluszny.zjp.parsers;

import com.ug.mposluszny.zjp.parsers.utils.Grammar;
import com.ug.mposluszny.zjp.parsers.utils.Tree;

public class DescendingParser implements Parser {

    private Grammar grammar;

    public DescendingParser(Grammar grammar) {
        this.grammar = grammar;
    }

    public Tree parse(String input) {
        return null;
    }

    public Grammar getGrammar() {
        return grammar;
    }
}
