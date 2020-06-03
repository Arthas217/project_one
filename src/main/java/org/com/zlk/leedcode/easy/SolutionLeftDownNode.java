package org.com.zlk.leedcode.easy;

import org.com.zlk.datastructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 *
 *   return 7
 */
public class SolutionLeftDownNode {

    /**
     * BFS 遍历.用队列保存每层对应节点的子节点
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            // 注意这里顺序，求最左边的，先把右侧节点入队
            if (root.right != null) {
                queue.add(root.right);
            }
            if (root.left != null) {
                queue.add(root.left);
            }
        }
        return root.val;
    }
}
