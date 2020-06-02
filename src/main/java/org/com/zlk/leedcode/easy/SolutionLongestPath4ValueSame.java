package org.com.zlk.leedcode.easy;

import org.com.zlk.datastructure.tree.TreeNode;

/**
 *
 *              1
 *             / \
 *            4   5
 *           / \   \
 *          4   4   5
 *
 *          Output : 2
 *
 * 相同节点值的最大路径长度
 * 1. 路径长度：指节点值相同的节点组成的路径中边的个数
 * 2. 可以经过根节点，也可以不经过
 */
public class SolutionLongestPath4ValueSame {

    private static int path = 0;

    public static int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return path;
    }

    private static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);
        int lpath = root.left != null && root.left.val == root.val ? l + 1 : 0;
        int rpath = root.right != null && root.right.val == root.val ? r + 1 : 0;
        path = Math.max(path, lpath + rpath);
        return Math.max(lpath, rpath);
    }


    public static void main(String[] args) {
        TreeNode treeNode =new TreeNode(1);
        int longestUnivaluePath = longestUnivaluePath(treeNode);
        System.out.println(longestUnivaluePath);
    }
}
