package org.com.zlk.leedcode.easy;

import org.com.zlk.datastructure.tree.TreeNode;

/**
 * 二叉树中第二小的节点
 * 树的特点如下：
 * 1 非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0
 * 2 如果一个节点有两个子节点的话，那么这个节点的值<=它的子节点的值。
 * 输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1
 *
 * 输入:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 *
 * 输出: 5
 */
public class SolutionSecondMinValueTree {

    /**
     * todo
     *  根节点肯定是值最小的节点，所以这个问题就是找子树中与根节点不相等的最小值。
     */
    public static int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        //只有根节点
        if (root.left == null && root.right == null) return -1;

        int leftVal = root.left.val;
        int rightVal = root.right.val;
        if (leftVal == root.val) {
            leftVal = findSecondMinimumValue(root.left);
        }
        if (rightVal == root.val){
            rightVal = findSecondMinimumValue(root.right);
        }
        if (leftVal != -1 && rightVal != -1) {
            return Math.min(leftVal, rightVal);
        }
        if (leftVal != -1){
            return leftVal;
        }
        return rightVal;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 =new TreeNode(2);
        TreeNode treeNode2 =new TreeNode(2);
        TreeNode treeNode3 =new TreeNode(5);
        TreeNode treeNode4 =new TreeNode(5);
        TreeNode treeNode5 =new TreeNode(7);
        treeNode1.left = treeNode2;
        treeNode1.right =treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;
        int secondMinimumValue = findSecondMinimumValue(treeNode1);
        System.out.println(secondMinimumValue);
    }
}
