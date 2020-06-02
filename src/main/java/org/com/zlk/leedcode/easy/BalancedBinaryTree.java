package org.com.zlk.leedcode.easy;

import org.com.zlk.datastructure.tree.BasicOperationTree;
import org.com.zlk.datastructure.tree.TreeNode;

/**
 * 平衡树左右子树高度差都小于等于 1
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int lDepth = BasicOperationTree.maxDepth(root.left);
        int rDepth = BasicOperationTree.maxDepth(root.right);
        boolean match = Math.abs(lDepth - rDepth) < 2;
        return match && isBalanced(root.left) && isBalanced(root.right);
    }
}
