package com.ug.mposluszny.zjp.parsers;

import com.ug.mposluszny.zjp.parsers.model.NonTerminal;
import com.ug.mposluszny.zjp.parsers.model.ParsingResult;
import com.ug.mposluszny.zjp.parsers.model.Tree;

public class DescendingParserStatic implements Parser {

    private String input;
    private int index;
    private Tree tree;
    private static NonTerminal S = new NonTerminal("S");
    private static NonTerminal B = new NonTerminal("B");

    public DescendingParserStatic() {
        init();
    }

    private void init() {
        index = -1;
        tree = new Tree(S);
    }

    public ParsingResult parse(String input) {
        init();
        this.input = input;

        ParsingResult resultS = checkS();

        if (resultS.isSuccess() && checkWholeInputParsed()) {
            return resultS;
        }
        return new ParsingResult(false, null);
    }

    private void backtrack() {
        index--;
    }

    private boolean checkWholeInputParsed() {
        return index == input.length()-1;
    }

    private ParsingResult checkTerminal(char terminal) {
        if (index < input.length()-1 && terminal == input.charAt(++index)) {
            return new ParsingResult(true, new Tree(terminal));
        }
        backtrack();
        return new ParsingResult(false, null);
    }

    private ParsingResult checkS() {
        Tree tree = new Tree(S);

        ParsingResult resultTermA = checkTerminal('a');
        if (resultTermA.isSuccess()) {
            ParsingResult resultS = checkS();
            if (resultS.isSuccess()) {
                ParsingResult resultTermD = checkTerminal('d');
                if (resultTermD.isSuccess()) {
                    tree.addChild(resultTermA.getTree());
                    tree.addChild(resultS.getTree());
                    tree.addChild(resultTermD.getTree());
                    return new ParsingResult(true, tree);
                }
            }
        }

        ParsingResult resultB = checkB();
        if (resultB.isSuccess()) {
            tree.addChild(resultB.getTree());
            return new ParsingResult(true, tree);
        }

        return new ParsingResult(false, null);
    }

    private ParsingResult checkB() {
        Tree tree = new Tree(B);

        ParsingResult resultTermB = checkTerminal('b');
        if (resultTermB.isSuccess()) {
            ParsingResult resultB = checkB();
            if (resultB.isSuccess()) {
                ParsingResult resultTermC = checkTerminal('c');
                if (resultTermC.isSuccess()) {
                    tree.addChild(resultTermB.getTree());
                    tree.addChild(resultB.getTree());
                    tree.addChild(resultTermC.getTree());
                    return new ParsingResult(true, tree);
                }
            }
        }
        ParsingResult resultTermE = checkTerminal('e');
        if (resultTermE.isSuccess()) {
            tree.addChild(resultTermE.getTree());
            return new ParsingResult(true, tree);
        }

        return new ParsingResult(false, null);
    }
}
