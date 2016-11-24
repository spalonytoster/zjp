package com.ug.mposluszny.zjp.parsers;

import com.ug.mposluszny.zjp.parsers.utils.Tree;

enum NonTerminal {
    S,
    B
}

public class DescendingParserStatic implements Parser {

    private String input;
    private int index;
    private Tree tree;

    public DescendingParserStatic() {
        init();
    }

    public Tree parse(String input) {
        init();
        this.input = input;
        if (checkS() && checkWholeInputParsed()) {
            return tree;
        }
        return null;
    }

    private void init() {
        index = -1;
        tree = new Tree(NonTerminal.S, true);
    }

    private void backtrack() {
        index--;
    }

    private boolean checkWholeInputParsed() {
        return index == input.length()-1;
    }

    private boolean checkTerminal(char terminal) {
        if (index < input.length()-1 && terminal == input.charAt(++index)) {
            return true;
        }
        backtrack();
        return false;
    }

    private boolean checkS() {
        if (checkTerminal('a') && checkS() && checkTerminal('d') ||
                checkB()) {
            // TODO modify tree
            return true;
        }
        return false;
    }

    private boolean checkB() {
        if (checkTerminal('b') && checkB() && checkTerminal('c') ||
                checkTerminal('e')) {
            // TODO modify tree
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        DescendingParserStatic parser = new DescendingParserStatic();
    }
}
