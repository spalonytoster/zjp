package com.ug.mposluszny.zjp.main;

import com.ug.mposluszny.zjp.Interpreter;
import com.ug.mposluszny.zjp.parsers.DescendingParserStatic;
import com.ug.mposluszny.zjp.parsers.Parser;

public class ParserMain {

    public static void main(String[] args) {
        Parser parser = new DescendingParserStatic();
        Interpreter interpreter = new Interpreter(parser);
        interpreter.run();
    }
}
