package com.ug.mposluszny.zjp.main;

import com.ug.mposluszny.zjp.Interpreter;
import com.ug.mposluszny.zjp.parsers.DescendingParserStatic1;
import com.ug.mposluszny.zjp.parsers.Parser;

public class ParserMain {

    public static void main(String[] args) {
        Parser parser = new DescendingParserStatic1();
        Interpreter interpreter = new Interpreter(parser);
        interpreter.run();
    }
}
