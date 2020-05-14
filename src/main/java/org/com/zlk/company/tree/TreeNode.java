package org.com.zlk.company.tree;

/**
 * 二叉树节点 基本结构
 */
public class TreeNode {

    /**
     * 节点值
     */
    public int value;
    /**
     * 左孩子
     */
    public TreeNode left;
    /**
     * 右孩子
     */
    public  TreeNode right;
    /**
     * 指向父结点的指针（test12 use）
     */
    public TreeNode next;


    public TreeNode(int value) {
        this.value = value;
    }
}
