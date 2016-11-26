package com.ug.mposluszny.zjp.parsers.model;

public class NonTerminal {

    private String name;

    public NonTerminal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}