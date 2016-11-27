package com.ug.mposluszny.zjp.parsers;

import com.ug.mposluszny.zjp.parsers.model.NonTerminal;
import com.ug.mposluszny.zjp.parsers.model.ParsingResult;
import com.ug.mposluszny.zjp.parsers.model.Tree;

public class DescendingParserStatic2 extends Parser {

    private static NonTerminal S = new NonTerminal("S");
    private static NonTerminal A = new NonTerminal("A");
    private static NonTerminal B = new NonTerminal("B");

    public ParsingResult parse(String input) {
        init();
        this.input = input;

        ParsingResult resultS = checkS();

        if (resultS.isSuccess() && checkWholeInputParsed()) {
            return resultS;
        }
        return new ParsingResult(false, null);
    }

    private ParsingResult checkS() {
        Tree tree = new Tree(S);

        ParsingResult resultA = checkA();
        if (resultA.isSuccess()) {
            ParsingResult resultB = checkB();
            if (resultB.isSuccess()) {
                tree.addChild(resultA.getTree());
                tree.addChild(resultB.getTree());
                return new ParsingResult(true, tree);
            }
        }
        return new ParsingResult(false, null);
    }

    private ParsingResult checkA() {
        Tree tree = new Tree(A);

        ParsingResult resultTermA = checkTerminal('a');
        if (resultTermA.isSuccess()) {
            ParsingResult resultA = checkA();
            if (resultA.isSuccess()) {
                tree.addChild(resultTermA.getTree());
                tree.addChild(resultA.getTree());
                return new ParsingResult(true, tree);
            }
        }

        ParsingResult resultTermX = checkTerminal('x');
        if (resultTermX.isSuccess()) {
            tree.addChild(resultTermX.getTree());
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
                tree.addChild(resultTermB.getTree());
                tree.addChild(resultB.getTree());
                return new ParsingResult(true, tree);
            }
        }

        ParsingResult resultTermX = checkTerminal('x');
        if (resultTermX.isSuccess()) {
            tree.addChild(resultTermX.getTree());
            return new ParsingResult(true, tree);
        }
        return new ParsingResult(false, null);
    }

}
