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

    public void addChild(Tree child) {
        children.add(child);
    }

    private boolean isNonTerminal() {
        return root instanceof NonTerminal;
    }

    private boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public String toString() {
        String result = "";
        result += printRoot();
        result += "\n";
        result += printChildren();
        return result;
    }

    private String printRoot() {
        if (isLeaf()) {
            return root.toString();
        }
        else if (isNonTerminal()) {
            return root.toString();
        }
        return "err";
    }

    private String printChildren() {
        if (children.isEmpty()) return null;
        StringBuilder result = new StringBuilder();
        StringBuilder childrenStr = new StringBuilder();
        children.stream().forEach(child -> {
            result.append(child.printRoot());
            result.append(" ");
            if (child.isNonTerminal()) {
                childrenStr.append(child.printChildren());
            }
        });
        result.append("\n");
        result.append(childrenStr);
        return result.toString();
    }
}
