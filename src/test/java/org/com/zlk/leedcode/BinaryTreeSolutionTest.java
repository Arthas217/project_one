package org.com.zlk.leedcode;

import org.com.zlk.datastructure.tree.BasicOperationTree;
import org.com.zlk.datastructure.tree.InitTreeNode;
import org.com.zlk.datastructure.tree.TreeNode;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.com.zlk.datastructure.tree.InitTreeNode.init2;
import static org.com.zlk.leedcode.BinaryTreeSolution.*;

/**
 * @Author zc217
 * @Date 2020/7/31
 */
public class BinaryTreeSolutionTest {

    @Test
    public void testOrder() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.right = treeNode2;
        treeNode2.left = treeNode3;
        List<Integer> inorder = inorderTraversal(treeNode1);
        List<Integer> inorder2 = inorder(treeNode1);
        List<Integer> postorder = postorder(treeNode1);
        List<Integer> postorder2 = postorder2(treeNode1);
        for (Integer num : postorder2) {
            System.out.print(num + "\t");
        }
        System.out.println();
        boolean validBST = isValidBST(treeNode1);
        boolean validBST1 = isValidBST1(treeNode1);
        System.out.println(validBST);
        System.out.println(validBST1);
    }


    @Test
    public void testBST() {
        TreeNode t1 = new TreeNode(7);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(15);
        TreeNode t4 = new TreeNode(9);
        TreeNode t5 = new TreeNode(20);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        BinaryTreeSolution bstIterator = new BinaryTreeSolution(t1);
        while (bstIterator.hasNext()) {
            System.out.println(bstIterator.next());
        }
    }


    @Test
    public void testLevelOrder() {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        List<List<Integer>> lists = levelOrder(t1);
        List<List<Integer>> lists2 = levelOrder2(t1);
        System.out.println(lists.stream().collect(Collectors.toList()));
        System.out.println(lists2.stream().collect(Collectors.toList()));
        System.out.println(zigzagLevelOrder(t1).stream().collect(Collectors.toList()));
    }

    @Test
    public void testLowestCommonAncestor() {
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
        TreeNode res = lowestCommonAncestor(t1, t7, t9);
        System.out.println(res.val);
    }


    @Test
    public void testKthSmallestBST() {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(2);
        t1.left = t2;
        t1.right = t3;
        t3.right = t2;
        int kthSmallestBST = kthSmallestBST(t1, 1);
        System.out.println(kthSmallestBST);
    }

    @Test
    public void testPathSum2() {
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
        List<List<Integer>> res = pathSum2(t1, 22);
        System.out.println(res);
    }


    @Test
    public void testHasPathSum() {
        TreeNode treeNode1 = InitTreeNode.init1();
        boolean hasPathSum = hasPathSum(treeNode1, 22);
        boolean hasPathSum2 = hasPathSum2(treeNode1, 22);
        System.out.println(hasPathSum);
        System.out.println(hasPathSum2);
    }


    @Test
    public void testMaxPathSum() {
        TreeNode t1 = new TreeNode(-10);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        int maxPathSum = maxPathSum(t1);
        System.out.println(maxPathSum);
    }

    @Test
    public void testPathSum333() {
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
        int sum3 = pathSum333(t1, 8);
        System.out.println(sum3);
    }


    @Test
    public void testInvertTree(){
        BasicOperationTree.preOrder(invertTree(init2()));
    }

    @Test
    public void testSumOfLeftLeaves(){
        System.out.println(sumOfLeftLeaves(init2()));
    }


}