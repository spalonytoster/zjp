package com.ug.mposluszny.zjp.parsers.model;

import java.util.Map;

class Production {

    private String name;
    private Object[][] outputs;

    public Production(String name, Object[][] outputs) {
        this.name = name;
        this.outputs = outputs;
    }

    public String getName() {
        return name;
    }

    public Object[][] getOutputs() {
        return outputs;
    }

    @Override
    public String toString() {
        return null;
    }
}

public class Grammar {

    private Map<String, Production> productions;

    public Grammar(Map<String, Production> productions) {
        this.productions = productions;
    }

    public Map<String, Production> getProductions() {
        return productions;
    }
}
