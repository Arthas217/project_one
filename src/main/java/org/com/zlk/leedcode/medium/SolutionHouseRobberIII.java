package org.com.zlk.leedcode.medium;

import org.com.zlk.datastructure.tree.TreeNode;

/**
 * 间隔遍历
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 */
public class SolutionHouseRobberIII {

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 2个儿子  vs  1爷爷、4孙子
        int val = root.val;
        if (root.left != null) {
            val += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val += rob(root.right.left) + rob(root.right.right);
        }
        int val1 = rob(root.left) + rob(root.right);
        int max = Math.max(val, val1);
        return max;
    }
}
