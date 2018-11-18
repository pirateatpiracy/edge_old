package com.edge.edgeCollection.tree;

public class Tree {
    private TreeNode root;

    public void insert(int value) {
        if (root == null) root = new TreeNode(value);
        else root.insert(value);
    }

    public void delete(int value) {
        root = delete(value, root);

    }

    public TreeNode delete(int value, TreeNode subTree) {
        if (subTree == null) {
            return subTree;
        }
        if (value < subTree.getData()) subTree.setLeft(delete(value, subTree.getLeft()));
        else if (value > subTree.getData()) subTree.setRight(delete(value, subTree.getRight()));
        else {
            // case 1 = with no child and two=with one child
            if (subTree.getLeft() == null) return subTree.getRight();
            else {
                if (subTree.getRight() == null) return subTree.getLeft();
            }
            //case 3 = with two children
            subTree.setData(subTree.getRight().min());
            subTree.setRight(delete(subTree.getData(), subTree.getRight()));
        }
        return subTree;
    }

    public void treverseInOrder() {
        if (root != null) root.traverseInOrder();
        System.out.println();
    }
    public void treversePreOrder() {
        if (root != null) root.traversePreOrder();
        System.out.println();
    }
    public void get(int value) {
        root.get(value);
    }

    public void min() {
        System.out.println(root.min());
    }

    public void max() {
        System.out.println(root.max());
    }
}
