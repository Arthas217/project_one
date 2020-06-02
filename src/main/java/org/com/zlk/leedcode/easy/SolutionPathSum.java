package org.com.zlk.leedcode.easy;

import org.com.zlk.datastructure.tree.TreeNode;

/**
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * Return 3. The paths that sum to 8 are:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 *
 * 统计路径和等于一个数的路径数量
 * 路径不一定以 root 开头，也不一定以 leaf 结尾，但是必须连续。
 */
public class SolutionPathSum {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        // 路径方向必须是向下的（只能从父节点到子节点）
        // 以当前节点作为头结点的路径数量 + 以当前节点的左孩子作为头结点的路径数量 + 以当前节点的右孩子作为头结点的路径数量
        return pathSumStartWithRoot(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int pathSumStartWithRoot(TreeNode root, int sum) {
        int num = 0;
        if (root == null) {
            return 0;
        }
        if (root.val == sum) {
            num++;
        }
        // 按照树的遍历方式模板，每到一个节点让sum-root.val
        num += pathSumStartWithRoot(root.left, sum - root.val) + pathSumStartWithRoot(root.right, sum - root.val);
        return num;
    }
}
