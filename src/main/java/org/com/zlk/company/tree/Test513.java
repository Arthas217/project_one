package org.com.zlk.company.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 *     1
 *   /   \
 *  2     3
 *  \    / \
 *   4  5   6
 */
public class Test513 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 方法1：迭代，层序遍历，保存每层左边第一个元素为结果，遍历完成后直接返回结果
     */
    public static int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()) {
            // 队列中个数就是循环次数
            int count = queue.size();
            // 每层第一个节点赋值给res
            res = queue.peek().val;
            while (count-- > 0) {
                TreeNode tmp = queue.poll();
                if (tmp.left != null) {
                    queue.add(tmp.left);
                }
                if (tmp.right != null) {
                    queue.add(tmp.right);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode n1 = buidTree();
        System.out.println(findBottomLeftValue(n1));
    }

    private static TreeNode buidTree() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n1.left = n2;
        n1.right = n3;
        n2.right = n4;
        n3.left = n5;
        n3.right = n6;
        return n1;
    }
}
