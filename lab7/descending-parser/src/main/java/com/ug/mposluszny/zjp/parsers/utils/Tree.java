package com.ug.mposluszny.zjp.parsers.utils;

import java.util.List;

public class Tree {

    private Object root;
    private List<Tree> children;
    private boolean nonTerminal;

    public Tree(Object root, boolean nonTerminal) {
        this.root = root;
        this.nonTerminal = nonTerminal;
    }

    public Tree(Object root, boolean nonTerminal, List<Tree> children) {
        super();
        this.children = children;
    }

    public Object getRoot() {
        return root;
    }

    public List<Tree> getChildren() {
        return children;
    }
}
