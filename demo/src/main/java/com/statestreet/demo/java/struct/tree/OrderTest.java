package com.statestreet.demo.java.struct.tree;

import java.util.*;

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
     * 非递归方式前序遍历
     * @param root
     * @return
     */
    public static List<Object> preorderTraversal(TreeNode root) {
        List<Object> rs = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || root != null) {
            while (root != null) {
                rs.add(root.getValue());
                stack.push(root);
                root = root.getLeft();
            }
            root = stack.pop();
            root = root.getRight();
        }
        return rs;
    }

    /**
     * test
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal2(TreeNode<Integer> root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while (root != null || !stack.isEmpty()){
            while (root != null){
                list.add(root.getValue());
                stack.push(root);
                root = root.getLeft();
            }
            root = stack.pop();
            root = root.getRight();
        }
        return list;
    }

    /**
     * test
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal3(TreeNode<Integer> root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while (root != null || !stack.isEmpty()){

            while (root != null){
                list.add(root.getValue());
                stack.push(root);
                root = root.getLeft();
            }
            TreeNode pop = stack.pop();
            root = pop.getRight();
        }
        return list;
    }

    /**
     * 非递归中序遍历
     * @param root
     * @return
     */
    public static List<Object> inorderTraversal(TreeNode root) {
        List<Object> rs = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
            root = stack.pop();
            rs.add(root.getValue());
            root = root.getRight();
        }
        return rs;
    }

    public static List<Integer> inorderTraversal2(TreeNode<Integer> root) {
        Stack<TreeNode<Integer>> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while (root != null && !stack.isEmpty()){

            while (root != null){
                stack.push(root);
                root = root.getLeft();
            }
            TreeNode<Integer> pop = stack.pop();
            list.add(pop.getValue());
            root = pop.getRight();
        }

        return list;
    }

    /**
     * test
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal3(TreeNode<Integer> root) {
        Stack<TreeNode<Integer>> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.getLeft();
            }
            root = stack.pop();
            list.add(root.getValue());
            root = root.getRight();
        }

        return list;
    }

    /**
     * 非递归后序遍历
     * @param root
     * @return
     */
    public static List<Object> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Object> rs = new LinkedList<>();
        if (root == null) {
            return rs;
        }
        stack.push(root);

        while (!stack.empty()) {
            TreeNode temp = stack.pop();
            rs.addFirst(temp.getValue());
            if (temp.getLeft() != null) {
                stack.push(temp.getLeft());
            }
            if (temp.getRight() != null) {
                stack.push(temp.getRight());
            }
        }
        return rs;
    }

    /**
     * test
     * @param root
     * @return
     */
    public static List<Object> postorderTraversal2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Object> rs = new LinkedList<>();
        if(root == null){
            return rs;
        }
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            rs.addFirst(pop.getValue());
            if(pop.getLeft() != null){
                stack.push(pop.getLeft());
            }
            if(pop.getRight() != null){
                stack.push(pop.getRight());
            }
        }
        return rs;
    }

    /**
     * 后序遍历 84526731
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
     * 删除叶子节点为target的值,递归删除,即删除一个之后还有叶子节点为target的也照样删除
     * @param root
     * @param target
     * @return
     */
    public TreeNode removeLeafNodes(TreeNode<Integer> root, int target) {
        if (root == null) {
            return null;
        }

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        if (root.getValue() == target && root.left == null && root.right == null) {
            return null;
        }
        return root;
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

    private static TreeNode<Integer> lowestCommonAncestor2(TreeNode<Integer> root,TreeNode<Integer> node1,TreeNode<Integer> node2){
        if(root == null || root.equals(node1) || root.equals(node2) ){
            return root;
        }
        TreeNode leftNode = lowestCommonAncestor2(root.getLeft(), node1, node2);
        TreeNode rightNode = lowestCommonAncestor2(root.getRight(), node1, node2);
        if(leftNode == null){
            return rightNode;
        }
        if(rightNode == null){
            return leftNode;
        }
        return root;
    }

    /**
     * 广度优先算法
     */
    private static void testBFS(TreeNode rootNode){
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(rootNode);
        while (!queue.isEmpty()){
            for(int i = 0;i<queue.size();i++){
                TreeNode node = queue.poll();
                System.out.print(node.getValue());
                if(node.getLeft() != null){
                    queue.offer(node.getLeft());
                }
                if(node.getRight() != null){
                    queue.offer(node.getRight());
                }
            }
        }


    }

    public static void testBFS2(TreeNode node){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            TreeNode poll = queue.poll();
            System.out.print(poll.getValue());
            if(poll.getLeft() != null){
                queue.offer(poll.getLeft());
            }
            if(poll.getRight() != null){
                queue.offer(poll.getRight());
            }

        }

    }

    /**
     * 二叉树最大深度
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.getLeft());
            int rightHeight = maxDepth(root.getRight());
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        if(Math.abs(maxDepth(root.getLeft()) - maxDepth(root.getRight())) > 1){
            return false;
        }else{
            return isBalanced(root.getLeft()) && isBalanced(root.getRight());
        }
    }
    /**
     * 深度优先算法(针对二叉树只要递归left和right节点如果是图的话需要获取所有子节点然后递归,并且存储一个已经访问过的节点集合,不重复访问)
     * 1248
     * @param rootNode
     */
    private static void testDFS(TreeNode rootNode){
        if(rootNode == null){
            return;
        }
        System.out.println(rootNode.getValue());
        testDFS(rootNode.getLeft());
        testDFS(rootNode.getRight());
    }

    /**
     * queue for 循环最好加上
     * @param rootNode
     */
    private static void testBFS3(TreeNode rootNode){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(rootNode);
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            System.out.print(poll.getValue());
            if(poll.getLeft() != null){
                queue.offer(poll.getLeft());
            }
            if(poll.getRight() != null){
                queue.offer(poll.getRight());
            }
        }
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

        System.out.println(postorderTraversal(node1));

        //前序遍历
//        preOrder(node1);
//        preorderTraversal(node1);
//        TreeNode ancestorNode = lowestCommonAncestor(node1, node4, node3);
//        System.out.println(ancestorNode);

//        testDFS(node1);

/*        testBFS(node1);
        System.out.println();
        testBFS2(node1);
        System.out.println();
        testBFS3(node1);*/


//        System.out.println("~~~~~~~~~~~");
//        preOrder(node1);//12485367
//        System.out.println();
//        inOrder(node1);//84251637
//        System.out.println();
//        postOrder(node1);//84526731
    }
}
