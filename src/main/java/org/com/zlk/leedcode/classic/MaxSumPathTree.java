package org.com.zlk.leedcode.classic;

import org.com.zlk.datastructure.tree.TreeNode;

public class MaxSumPathTree {

    private static int max = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        int maxSum = maxSum(root);
        System.out.println("root -> leaf " + maxSum);
        return max;
    }

    /**
     * 得到当前树的最大的路径和（通过递归后序遍历的方式）
     */
    private static int maxSum(TreeNode root) {
        if (null == root) {
            return 0;
        }
        // 先计算出左子树最大路径和（根->叶子）
        int leftMax = Math.max(maxSum(root.left), 0);
        // 再计算出右子树最大路径和（根->叶子）
        int rightMax = Math.max(maxSum(root.right), 0);
        // 最后计算当前树的最大路径和
        max = Math.max(max, leftMax + rightMax + root.val);
        // 计算树中每个节点作为根时，根到叶子节点最大路径和
        return root.val + Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
//        TreeNode t1 = new TreeNode(-10);
//        TreeNode t2 = new TreeNode(9);
//        TreeNode t3 = new TreeNode(20);
//        TreeNode t4 = new TreeNode(15);
//        TreeNode t5 = new TreeNode(7);
//        t1.left = t2;
//        t1.right = t3;
//        t3.left = t4;
//        t3.right = t5;


        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t3.right = t5;

        int maxPathSum = maxPathSum(t1);
        System.out.println(maxPathSum);
    }

}
