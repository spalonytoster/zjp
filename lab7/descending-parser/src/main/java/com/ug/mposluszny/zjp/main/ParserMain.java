package com.ug.mposluszny.zjp.main;

import com.ug.mposluszny.zjp.Interpreter;
import com.ug.mposluszny.zjp.parsers.DescendingParserStatic3;
import com.ug.mposluszny.zjp.parsers.Parser;

public class ParserMain {

    public static void main(String[] args) {
        Parser parser = new DescendingParserStatic3();
        Interpreter interpreter = new Interpreter(parser);
        interpreter.run();
    }
}
