package com.edge.edgeCollection.tree;

public class TreeNode {
    private int data;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int data) {
        this.data = data;
    }

    public void insert(int value) {
        if (value == data) return;
        if (value < data) {
            if (left == null) left = new TreeNode(value);
            else {
                left.insert(value);
            }
        } else {
            if (right == null) right = new TreeNode(value);
            else {
                right.insert(value);
            }
        }
    }


    public void traverseInOrder() {
        if (left != null) left.traverseInOrder();
        System.out.print(data + ", ");
        if (right != null) right.traverseInOrder();
    }
    public void traversePreOrder() {
        System.out.print(data + ", ");
        if (left != null) left.traversePreOrder();
        if (right != null) right.traversePreOrder();
    }

    public int min() {
        if (left != null) return left.min();
        else {
            return getData();
        }
    }

    public int max() {
        if (right != null) return right.max();
        else {
            return getData();
        }
    }

    public void get(int value) {
        if (left != null && value < data) {
            left.get(value);
        } else if (right != null && value > data) {
            right.get(value);
        } else if (value == data) {
            System.out.println("\nData = " + data);
        } else {
            System.out.println("\nData not found");
        }

    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }


}
