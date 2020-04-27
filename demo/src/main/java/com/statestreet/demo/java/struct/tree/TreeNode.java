package com.statestreet.demo.java.struct.tree;

import java.util.Objects;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-04-27 9:10
 */
public class TreeNode<E> {

    private final E value;

    private TreeNode<E> left;

    private TreeNode<E> right;

    public TreeNode(E value){
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }

    public E getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode<?> treeNode = (TreeNode<?>) o;
        return Objects.equals(value, treeNode.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                '}';
    }
}
