package com.ug.mposluszny.zjp.parsers.model;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private Object root;
    private List<Tree> children;

    public Tree(Object root) {
        this.root = root;
        this.children = new ArrayList<>();
    }

    public Tree(Object root, List<Tree> children) {
        this.root = root;
        this.children = children;
    }

    public void addChild(Tree child) {
        children.add(child);
    }

    public boolean isNonTerminal() {
        return root instanceof NonTerminal;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public Object getRoot() {
        return root;
    }

    public List<Tree> getChildren() {
        return children;
    }
}
