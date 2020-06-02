package org.com.zlk.leedcode.classic;

import org.com.zlk.datastructure.tree.TreeNode;

public class ValidBST {

    public boolean isValidBST(TreeNode root) {
        // TreeNode节点中value属性 类型是int   [2147483647]这个用例不行。
        return help(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean help(TreeNode root, Long lower, Long upper) {
        // 如有一个根节点
        if (root == null) {
            return true;
        }
        if (root.val >= upper || root.val <= lower) {
            return false;
        }
        return help(root.left, lower, (long) root.val) && help(root.right, (long) root.val, upper);
    }

}
