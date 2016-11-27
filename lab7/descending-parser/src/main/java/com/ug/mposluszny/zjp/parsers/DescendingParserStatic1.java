package com.ug.mposluszny.zjp.parsers;

import com.ug.mposluszny.zjp.parsers.model.NonTerminal;
import com.ug.mposluszny.zjp.parsers.model.ParsingResult;
import com.ug.mposluszny.zjp.parsers.model.Tree;

public class DescendingParserStatic1 extends Parser {

    private static NonTerminal S = new NonTerminal("S");
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
