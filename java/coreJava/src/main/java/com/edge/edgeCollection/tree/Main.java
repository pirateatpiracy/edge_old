package com.edge.edgeCollection.tree;

public class Main {

    public static void main(String[] args) {
        Tree intTree =new Tree();
        intTree.insert(65);
        intTree.insert(99);
        intTree.insert(5);
        intTree.insert(38);
        intTree.insert(19);
        intTree.insert(1);
        intTree.insert(29);
        intTree.insert(7);
        intTree.insert(35);
        intTree.insert(67);
        intTree.insert(83);
        intTree.insert(11);

        intTree.treverseInOrder();
        intTree.treversePreOrder();
        intTree.get(1);
        intTree.max();
        intTree.delete(259);
        intTree.treverseInOrder();
    }

}
