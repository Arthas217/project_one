package org.com.zlk.datastructure.tree;


import static org.com.zlk.datastructure.tree.InitTreeNode.init2;

public class TreeTest {

    public static void main(String[] args) {
        TreeNode root = init2();
        // 前中后遍历--递归
//        BasicOperationTree.preOrder(root);
        System.out.println();
//        BasicOperationTree.inOrder(root);
        System.out.println();
//        BasicOperationTree.postOrder(root);

        // 前中后遍历--非递归
        System.out.println();
//        BasicOperationTree.preOrder2(root);
        System.out.println();
//        BasicOperationTree.inOrder2(root);
        System.out.println();
//        BasicOperationTree.postOrder2(root);

        System.out.println();
//        BasicOperationTree.laywerTraversal(root);
        System.out.println();
        BasicOperationTree.depthOrderTraverse(root);
        System.out.println();
        BasicOperationTree.depthOrderTraverse1(root);

//        int depth = BasicOperationTree.maxDepth(root);
//        System.out.println("树高度："+depth);


    }

}
