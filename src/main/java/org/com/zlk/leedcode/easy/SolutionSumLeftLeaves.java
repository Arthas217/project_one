package org.com.zlk.leedcode.easy;

import org.com.zlk.datastructure.tree.TreeNode;

/**
 * 统计左叶子节点的和
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *  with values 9 and 15 respectively. Return 24.
 */
public class SolutionSumLeftLeaves {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left =treeNode4;
        treeNode3.right =treeNode5;
        int sumOfLeftLeaves = sumOfLeftLeaves(treeNode1);
        System.out.println(sumOfLeftLeaves);


    }

    public static int sumOfLeftLeaves(TreeNode root) {

        if (root == null) return 0;

        // 处理当前节点 如果是叶子节点
        if (isLeaf(root.left)){
            // 递归找其他的左孩子 取和累加
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        // 非叶子节点 左子树和柚子树都要递归处理。
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    private static boolean isLeaf(TreeNode node){
        if (node == null) return false;
        return node.left == null && node.right == null;
    }

}
