package org.com.zlk.datastructure.tree;

import org.com.zlk.leedcode.easy.BalancedBinaryTree;

public class TreeTest {

    public static void main(String[] args) {
        TreeNode root = initTreeNode();
        // 前中后遍历--递归
        BasicOperationTree.preOrder(root);
        System.out.println();
        BasicOperationTree.inOrder(root);
        System.out.println();
        BasicOperationTree.postOrder(root);

        // 前中后遍历--非递归
        System.out.println();
        BasicOperationTree.preOrder2(root);
        System.out.println();
        BasicOperationTree.inOrder2(root);
        System.out.println();
        BasicOperationTree.postOrder2(root);

        System.out.println();
        BasicOperationTree.laywerTraversal(root);
        System.out.println();
        BasicOperationTree.depthOrderTraverse(root);
        System.out.println();

        int depth = BasicOperationTree.maxDepth(root);
        System.out.println("树高度："+depth);


    }

    private static TreeNode initTreeNode() {
        // 6个节点
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        return treeNode1;
    }
}
