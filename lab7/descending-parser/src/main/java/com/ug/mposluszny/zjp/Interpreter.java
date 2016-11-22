package com.ug.mposluszny.zjp;

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
            System.out.print("> ");
            String input, result;
            input = scanner.next();
            result = parser.parse(input);
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        Parser parser = new DescendingParser();
        Interpreter interpreter = new Interpreter(parser);
        interpreter.run();
    }
}
