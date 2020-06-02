package org.com.zlk.offer;

import org.com.zlk.datastructure.tree.BasicOperationTree;
import org.com.zlk.datastructure.tree.TreeNode;

public class TreeMirror {

    public static TreeNode MirrorBinaryTree(TreeNode root) {
        if (null == root) {
            return null;
        }
        // 交换父节点的左右孩子
        swap(root);
        MirrorBinaryTree(root.left);
        MirrorBinaryTree(root.right);
        return root;
    }

    private static void swap(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t3.right = t5;
        BasicOperationTree.preOrder(t1);
        System.out.println();
        MirrorBinaryTree(t1);
        BasicOperationTree.laywerTraversal(t1);
        System.out.println();
        BasicOperationTree.inOrder(t1);

    }
}
