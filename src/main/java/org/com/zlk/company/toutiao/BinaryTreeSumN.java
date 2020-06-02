package org.com.zlk.company.toutiao;

import org.com.zlk.datastructure.tree.InitTreeNoe;
import org.com.zlk.datastructure.tree.TreeNode;

/**
 * 给定一个二叉树和一个数字n，判断二叉树中是否有一个路径上的数字之和等于给定的数字n
 * 路径和定义为从 root 到 leaf 的所有节点的和。
 */
public class BinaryTreeSumN {


    /**
     * 递归第一个版本
     * target 给定和的值
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
        cur = root.val + cur;
        // 遍历到叶子节点
        if (root.left == null && root.right == null) {
            return cur == target;
        } else {
            // 递归
            return helper(root.left, cur, target) || helper(root.right, cur, target);
        }
    }


    public static boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        // 判断叶子节点，sum == 0为依据
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        // 遍历节点时 && 对sum做处理
        return hasPathSum2(root.left, sum - root.val) || hasPathSum2(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = InitTreeNoe.init1();
        boolean hasPathSum = hasPathSum(treeNode1, 22);
        boolean hasPathSum2 = hasPathSum2(treeNode1, 22);
        System.out.println(hasPathSum);
        System.out.println(hasPathSum2);
    }
}
