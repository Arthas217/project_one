package org.com.zlk.leedcode.easy;

import org.com.zlk.datastructure.tree.TreeNode;

/**
 * 修剪二叉查找树  给定最小边界L 和最大边界 R,使得所有节点的值在[L, R]中 (R>=L)
 * 输入:
 *     3
 *    / \
 *   0   4
 *    \
 *     2
 *    /
 *   1
 *
 *   L = 1
 *   R = 3
 *
 *  输出:
 *       3
 *      /
 *    2
 *   /
 *  1
 *
 */
public class SolutionTrimBST {

    /**
     * 从根结点开始修剪
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        // 如果结点为空，说明无需修剪，直接返回空即可。
        if (root == null) {return null;}
        // 如果根结点太小，说明根结点及其左子树都应该剪掉，因此直接返回右子树的修剪结果。
        if (root.val < L) {
            return trimBST(root.right, L, R);
        }
        // 如果根结点太大，说明根结点及其右子树都应该剪掉，因此直接返回左子树的修剪结果。
        if (root.val > R) {
            return trimBST(root.left, L, R);
        }
        // 如果根结点没问题，则递归地修剪左子结点和右子结点。
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        //  左右子结点都修剪完后，返回自身。
        return root;
    }
}
