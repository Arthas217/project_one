package org.com.zlk.datastructure.tree;

public class InitTreeNode {

    /**
     *         5
     *      4      8
     *   -11    -13   4
     *  2    7       5   1
     */
    public static TreeNode init1() {
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(8);
        TreeNode treeNode4 = new TreeNode(-11);
        TreeNode treeNode5 = new TreeNode(-13);
        TreeNode treeNode6 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(2);
        TreeNode treeNode9 = new TreeNode(1);
        TreeNode treeNode10 = new TreeNode(5);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode4.right = treeNode7;
        treeNode4.left = treeNode8;
        treeNode3.left = treeNode5;
        treeNode3.right = treeNode6;
        treeNode6.left = treeNode10;
        treeNode6.right = treeNode9;
        return treeNode1;
    }

    /**
     *         1
     *      2    3
     *    4  5  6
     */
    public static TreeNode init2() {
        // 6个节点
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        return treeNode1;
    }

    public static TreeNode init22(){
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t2.right = t4;
        t3.right = t5;
        return t1;
    }

    /**
     *         1
     *      3    2
     */
    public static TreeNode init3(){
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.right = treeNode2;
        treeNode1.left = treeNode3;
        return treeNode1;
    }
    public static TreeNode init33(){
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(5);
        t1.left =t2;
        t1.right = t3;
        t2.left  = t4;
        return t1;
    }

    /**
     *         7
     *      3    15
     *    9  20
     */
    public static TreeNode init4(){
        TreeNode t1 = new TreeNode(7);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(15);
        TreeNode t4 = new TreeNode(9);
        TreeNode t5 = new TreeNode(20);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        return t1;
    }

    /**
     *         3
     *      9    20
     *    15  7
     */
    public static TreeNode init5(){
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        return t1;
    }

    /**
     *         3
     *      5      1
     *    6   2   0  8
     *       7  4
     */
    public static TreeNode init6(){
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(1);
        TreeNode t4 = new TreeNode(6);
        TreeNode t5 = new TreeNode(2);
        TreeNode t6 = new TreeNode(0);
        TreeNode t7 = new TreeNode(8);
        TreeNode t8 = new TreeNode(7);
        TreeNode t9 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        t5.left = t8;
        t5.right = t9;
        return t1;
    }

    /**
     *         3
     *      1    4
     *             2
     */
    public static TreeNode init7(){
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(2);
        t1.left = t2;
        t1.right = t3;
        t3.right = t2;
        return t1;
    }

    /**
     *         5
     *      4     8
     *    11    13   4
     *  7   2       5  1
     */
    public static TreeNode init8(){
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(4);
        TreeNode t3 = new TreeNode(8);
        TreeNode t4 = new TreeNode(11);
        TreeNode t5 = new TreeNode(13);
        TreeNode t6 = new TreeNode(4);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(2);
        TreeNode t9 = new TreeNode(5);
        TreeNode t10 = new TreeNode(1);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t3.left = t5;
        t3.right = t6;
        t4.left = t7;
        t4.right = t8;
        t6.left = t9;
        t6.right = t10;
        return t1;
    }

    /**
     *         -10
     *      9     20
     *          15   7
     */
    public static TreeNode init9(){
        TreeNode t1 = new TreeNode(-10);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        return t1;
    }


    /**
     *          10
     *      5       -3
     *    3    2       11
     *  3  -2    1
     */
    public static TreeNode init10(){
        TreeNode t1 = new TreeNode(10);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(-3);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(2);
        TreeNode t6 = new TreeNode(11);
        TreeNode t7 = new TreeNode(3);
        TreeNode t8 = new TreeNode(-2);
        TreeNode t9 = new TreeNode(1);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.right = t6;
        t4.left = t7;
        t4.right = t8;
        t5.right = t9;
        return t1;
    }

}
