package com.statestreet.demo.java.struct.tree;

/**
 * @author Xu Jianglin
 * @version 1.0
 * @create 2020-04-27 9:14
 */
public class OrderTest {

    /**
     * 前序遍历
     * @param root
     */
    private static void preOrder(TreeNode<Integer> root){
        if(root == null){
            return;
        }
        System.out.print(root.getValue());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    /**
     * 中序
     * @param root
     */
    private static void inOrder(TreeNode<Integer> root){
        if(root == null){
            return;
        }
        inOrder(root.getLeft());
        System.out.print(root.getValue());
        inOrder(root.getRight());
    }

    /**
     * 后序遍历
     * @param root
     */
    private static void postOrder(TreeNode<Integer> root){
        if(root == null){
            return;
        }
        postOrder(root.getLeft());
        postOrder(root.getRight());
        System.out.print(root.getValue());
    }

    /**
     * 获取node1和node2的最近的公共父亲节点
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    private static TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root,TreeNode<Integer> node1,TreeNode<Integer> node2){
        if(root == null || root.equals(node1) || root.equals(node2)){
            return root;
        }
        System.out.print(root.getValue());
        TreeNode left = lowestCommonAncestor(root.getLeft(), node1, node2);
        TreeNode right = lowestCommonAncestor(root.getRight(), node1, node2);
        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }
        return root;

    }


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode<Integer>(1);
        TreeNode node2 = new TreeNode<Integer>(2);
        TreeNode node3 = new TreeNode<Integer>(3);
        TreeNode node4 = new TreeNode<Integer>(4);
        TreeNode node5 = new TreeNode<Integer>(5);
        TreeNode node6 = new TreeNode<Integer>(6);
        TreeNode node7 = new TreeNode<Integer>(7);
        TreeNode node8 = new TreeNode<Integer>(8);

        node1.setLeft(node2);
        node1.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);

        node4.setLeft(node8);

        node3.setLeft(node6);
        node3.setRight(node7);
        //前序遍历
//        preOrder(node1);

        TreeNode ancestorNode = lowestCommonAncestor(node1, node8, node5);
        System.out.println(ancestorNode);


    }
}
