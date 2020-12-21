package org.com.zlk.leedcode;

import org.com.zlk.datastructure.tree.InitTreeNode;
import org.com.zlk.datastructure.tree.TreeNode;
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
        // 前中后遍历--递归
        bts.preOrder(root);
        System.out.println();
        bts.inOrder(root);
        System.out.println();
        bts.postOrder(root);
        // 前中后遍历--非递归
        System.out.println();
        bts.preOrder2(root);
        System.out.println();
        bts.inOrder2(root);
        System.out.println();
        bts.postOrder2(root);
        System.out.println();
        List<Integer> list = bts.layerTraversal(root);
        System.out.println(list);
        List<Integer> dep = bts.depthOrder(root);
        System.out.println(dep);
        List<Integer> dep1 = bts.depthOrder1(root);
        System.out.println(dep1);
        System.out.println("树高度：" + bts.maxDepth(root));
    }

    @Test
    public void testisSymmetric(){
        TreeNode root = initTreeNode.init3();
        boolean result = bts.isSymmetric(root);
        System.out.println(result);
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
        TreeNode treeNode = bts.mergeTrees(t1,t2);
        System.out.println(bts.levelOrder(treeNode));
    }

    @Test
    public void testaverageOfLevels(){
        TreeNode t = initTreeNode.init2();
        System.out.println(bts.averageOfLevels(t));
    }

    @Test
    public void testOrder() {
        TreeNode treeNode = initTreeNode.init3();
        List<Integer> inorder = bts.inorderTraversal(treeNode);
        List<Integer> inorder2 = bts.inorder(treeNode);
        List<Integer> postorder = bts.postorder(treeNode);
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
        List<List<Integer>> lists = bts.levelOrder(treeNode);
        List<List<Integer>> lists2 = bts.levelOrder2(treeNode);
        System.out.println(lists.stream().collect(Collectors.toList()));
        System.out.println(lists2.stream().collect(Collectors.toList()));
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
    public void testInvertTree() {
        bts.preOrder(bts.invertTree(initTreeNode.init2()));
    }

    @Test
    public void testSumOfLeftLeaves() {
        System.out.println(bts.sumOfLeftLeaves(initTreeNode.init2()));
    }

    @Test
    public void testflatten() {
        bts.inOrder(bts.flatten(initTreeNode.init2()));
    }

}