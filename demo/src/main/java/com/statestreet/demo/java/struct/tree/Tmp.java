package com.statestreet.demo.java.struct.tree;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2021-03-05 11:10
 */
public class Tmp {

    public void preOrder(TreeNode node){
        if(node == null){
            return;
        }
        System.out.println(node.getValue());
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }
}
