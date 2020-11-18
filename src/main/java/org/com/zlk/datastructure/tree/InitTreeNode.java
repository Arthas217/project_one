package org.com.zlk.datastructure.tree;

public class InitTreeNode {

    public static TreeNode init1() {
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(8);
        TreeNode treeNode4 = new TreeNode(-11);
        TreeNode treeNode5 = new TreeNode(-13);
        TreeNode treeNode6 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(2);
        TreeNode treeNode9 = new TreeNode(1);
        TreeNode treeNode10 = new TreeNode(5);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode4.right = treeNode7;
        treeNode4.left = treeNode8;
        treeNode3.left = treeNode5;
        treeNode3.right = treeNode6;
        treeNode6.left = treeNode10;
        treeNode6.right = treeNode9;
        return treeNode1;
    }

    /**
     *         1
     *      2    3
     *    4  5  6
     */
    public static TreeNode init2() {
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
