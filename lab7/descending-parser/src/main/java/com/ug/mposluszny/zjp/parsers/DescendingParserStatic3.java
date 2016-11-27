package com.ug.mposluszny.zjp.parsers;

import com.ug.mposluszny.zjp.parsers.Parser;
import com.ug.mposluszny.zjp.parsers.model.NonTerminal;
import com.ug.mposluszny.zjp.parsers.model.ParsingResult;
import com.ug.mposluszny.zjp.parsers.model.Tree;

public class DescendingParserStatic3 extends Parser {

    private static NonTerminal A = new NonTerminal("A");
    private static NonTerminal B = new NonTerminal("B");

    public ParsingResult parse(String input) {
        init();
        this.input = input;

        ParsingResult resultA = checkA();

        if (resultA.isSuccess() && checkWholeInputParsed()) {
            return resultA;
        }
        return new ParsingResult(false, null);
    }

    private ParsingResult checkA() {
        Tree tree = new Tree(A);

        ParsingResult resultB = checkB();
        if (resultB.isSuccess()) {
            ParsingResult resultPlus;
            ParsingResult resultInnerB = null;
            do {
                resultPlus = checkTerminal('+');
                if (resultPlus.isSuccess()) {
                    resultInnerB = checkB();
                    if (resultInnerB.isSuccess()) {
                        tree.addChild(resultB.getTree());
                        tree.addChild(resultPlus.getTree());
                        tree.addChild(resultInnerB.getTree());
                    }
                    /*
                     *  jeżeli w części otoczonej klamerkami dostaniemy plus a potem się
                     *  coś wywali to wiemy, że input był niepoprawny, więc należy
                     *  zwrócić negatywny wynik parsowania całej produkcji
                     */
                    else {
                        return new ParsingResult(false, null);
                    }
                }
                else {
                    tree.addChild(resultB.getTree());
                }
            } while(resultPlus.isSuccess() && resultInnerB.isSuccess());
            return new ParsingResult(true, tree);
        }
        return new ParsingResult(false, null);
    }

    private ParsingResult checkB() {
        Tree tree = new Tree(B);

        ParsingResult resultTermA = checkTerminal('a');
        if (resultTermA.isSuccess()) {
            tree.addChild(resultTermA.getTree());
            return new ParsingResult(true, tree);
        }

        ParsingResult resultOpeningBrace = checkTerminal('(');
        if (resultOpeningBrace.isSuccess()) {
            ParsingResult resultA = checkA();
            if (resultA.isSuccess()) {
                ParsingResult resultClosingBrace = checkTerminal(')');
                if (resultClosingBrace.isSuccess()) {
                    tree.addChild(resultOpeningBrace.getTree());
                    tree.addChild(resultA.getTree());
                    tree.addChild(resultClosingBrace.getTree());
                    return new ParsingResult(true, tree);
                }
            }
        }
        return new ParsingResult(false, null);
    }
}
