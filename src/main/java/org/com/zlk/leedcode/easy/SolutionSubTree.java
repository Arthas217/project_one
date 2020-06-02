package org.com.zlk.leedcode.easy;

import org.com.zlk.datastructure.tree.TreeNode;

/**
 * s中是否有子树t
 */
public class SolutionSubTree {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        return isSubtreeWithRoot(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSubtreeWithRoot(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        return isSubtreeWithRoot(s.left,t.left) && isSubtreeWithRoot(s.right,t.right);
    }

}
