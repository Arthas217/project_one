package org.com.zlk.company.toutiao;

import org.com.zlk.company.tree.InitTreeNoe;
import org.com.zlk.company.tree.TreeNode;

/**
 * 给定一个二叉树和一个数字n，判断二叉树中是否有一个路径上的数字之和等于给定的数字n
 */
public class BinaryTreeSumN {

    /**
     * 递归
     */
    public static boolean hasPathSum(TreeNode root, int target) {
        // 根节点root 、路径值0
        return helper(root, 0, target);
    }

    private static boolean helper(TreeNode root, int cur, int target) {
        if (root == null) {
            return false;
        }
        // 累计路径上值
        cur = root.value + cur;
        // 遍历到叶子节点
        if (root.left == null && root.right == null) {
            return cur == target;
        } else {
            // 递归
            return helper(root.left, cur, target) || helper(root.right, cur, target);
        }
    }


    public static void main(String[] args) {
        TreeNode treeNode1 = InitTreeNoe.init1();
        boolean hasPathSum = hasPathSum(treeNode1, 22);
        System.out.println(hasPathSum);
    }
}
