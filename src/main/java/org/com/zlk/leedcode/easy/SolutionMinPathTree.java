package org.com.zlk.leedcode.easy;

import org.com.zlk.datastructure.tree.TreeNode;

/**
 * 树的根节点到叶子节点的最小路径长度(最小路径）
 */
public class SolutionMinPathTree {

    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = minDepth(root.left);
        int r = minDepth(root.right);
        if (l == 0 || r == 0) {
            return l + r + 1;
        }
        // 返回当前节点到叶子节点中最小的路径和
        return Math.min(l, r) + 1;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode4.left = treeNode7;
        treeNode4.right = treeNode8;
        treeNode3.left = treeNode5;
        treeNode3.right = treeNode6;
        treeNode6.left = treeNode9;
        int minDepth = minDepth(treeNode1);
        System.out.println(minDepth);
    }
}
