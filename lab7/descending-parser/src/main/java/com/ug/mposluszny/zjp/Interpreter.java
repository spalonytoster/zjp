package com.ug.mposluszny.zjp;

import com.ug.mposluszny.zjp.parsers.Parser;

import java.util.Scanner;

public class Interpreter {

    private Parser parser;

    public Interpreter(Parser parser) {
        this.parser = parser;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter input:");

        while (true) {
            String input, result;
            System.out.print("> ");
            input = scanner.next();
            result = parser.parse(input).toString();
            System.out.println(result);
        }
    }
}
