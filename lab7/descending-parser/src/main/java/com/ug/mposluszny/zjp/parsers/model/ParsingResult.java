package com.ug.mposluszny.zjp.parsers.model;

public class ParsingResult {
    private boolean success;
    private Tree tree;

    public ParsingResult(boolean success, Tree tree) {
        this.success = success;
        this.tree = tree;
    }

    public boolean isSuccess() {
        return success;
    }

    public Tree getTree() {
        return tree;
    }
}
