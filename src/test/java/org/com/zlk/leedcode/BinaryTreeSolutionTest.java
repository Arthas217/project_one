package org.com.zlk.leedcode;

import org.com.zlk.datastructure.BinaryTreeSolution;
import org.com.zlk.datastructure.tree.InitTreeNode;
import org.com.zlk.datastructure.tree.TreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author zc217
 * @Date 2020/7/31
 */
public class BinaryTreeSolutionTest {

    InitTreeNode initTreeNode;
    BinaryTreeSolution bts;
    @Before
    public void setUp() {
         initTreeNode = new InitTreeNode();
         bts = new BinaryTreeSolution();
    }


    @Test
    public void testBasicOrder() {
        TreeNode root = initTreeNode.init2();
        System.out.println("树高度：" + bts.maxDepth(root));
    }

    @Test
    public void testisSymmetric(){
        TreeNode root = initTreeNode.init3();
    }

    @Test
    public void testisSubStructure(){
        TreeNode root1 = initTreeNode.init2();
        TreeNode root2 = initTreeNode.init3();
        boolean subStructure = bts.isSubStructure(root1, root2);
        System.out.println(subStructure);
    }

    @Test
    public void testmergeTrees(){
        TreeNode t1 = initTreeNode.init22();
        TreeNode t2 = initTreeNode.init33();
    }

    @Test
    public void testaverageOfLevels(){
        TreeNode t = initTreeNode.init2();
        System.out.println(bts.averageOfLevels(t));
    }

    @Test
    public void testOrder() {
        TreeNode treeNode = initTreeNode.init3();

        List<Integer> postorder2 = bts.postorder2(treeNode);
        for (Integer num : postorder2) {
            System.out.print(num + "\t");
        }
        System.out.println();
        boolean validBST = bts.isValidBST(treeNode);
        boolean validBST1 = bts.isValidBST1(treeNode);
        System.out.println(validBST);
        System.out.println(validBST1);
    }


    @Test
    public void testBST() {
        TreeNode treeNode = initTreeNode.init4();
        BinaryTreeSolution bstIterator = new BinaryTreeSolution(treeNode);
        while (bstIterator.hasNext()) {
            System.out.println(bstIterator.next());
        }
    }


    @Test
    public void testLevelOrder() {
        TreeNode treeNode = initTreeNode.init5();
        System.out.println(bts.zigzagLevelOrder(treeNode).stream().collect(Collectors.toList()));
    }

    @Test
    public void testLowestCommonAncestor() {
        TreeNode treeNode = initTreeNode.init6();
        TreeNode res = bts.lowestCommonAncestor(treeNode, new TreeNode(8),  new TreeNode(4));
        System.out.println(res.val);
    }


    @Test
    public void testKthSmallestBST() {
        TreeNode treeNode = initTreeNode.init7();
        int kthSmallestBST = bts.kthSmallestBST(treeNode, 1);
        System.out.println(kthSmallestBST);
    }

    @Test
    public void testPathSum2() {
        TreeNode treeNode = initTreeNode.init8();
        List<List<Integer>> res = bts.pathSum2(treeNode, 22);
        System.out.println(res);
    }


    @Test
    public void testHasPathSum() {
        TreeNode treeNode1 = initTreeNode.init1();
        boolean hasPathSum = bts.hasPathSum(treeNode1, 22);
        boolean hasPathSum2 = bts.hasPathSum2(treeNode1, 22);
        System.out.println(hasPathSum);
        System.out.println(hasPathSum2);
    }


    @Test
    public void testMaxPathSum() {
        TreeNode treeNode = initTreeNode.init9();
        int maxPathSum = bts.maxPathSum(treeNode);
        System.out.println(maxPathSum);
    }

    @Test
    public void testPathSum333() {
        TreeNode treeNode = initTreeNode.init10();
        int sum3 = bts.pathSum333(treeNode, 8);
        System.out.println(sum3);
    }



    @Test
    public void testSumOfLeftLeaves() {
        int sumOfLeftLeaves2 = bts.sumOfLeftLeaves2(initTreeNode.init2());
        Assert.assertEquals(10,sumOfLeftLeaves2);
    }

    @Test
    public void testisBalanced(){
        TreeNode treeNode = initTreeNode.init2();
        boolean balanced = bts.isBalanced(treeNode);
        Assert.assertEquals(true,balanced);
    }


    @Test
    public void testkthSmallest(){
        TreeNode treeNode = initTreeNode.init11();
        System.out.println(bts.kthSmallest(treeNode, 3));
        System.out.println(bts.kthSmallest1(treeNode, 3));
    }

}