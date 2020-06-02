package org.com.zlk.leedcode.easy;

import org.com.zlk.datastructure.tree.TreeNode;

/**
 * 翻转树
 */
public class SolutionInvertTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        //这里类似交换两个变量的操作
        TreeNode left = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }
}
