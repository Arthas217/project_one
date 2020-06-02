package org.com.zlk.company.toutiao;

import org.com.zlk.datastructure.tree.InitTreeNoe;
import org.com.zlk.datastructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树 返回所有从根节点到叶子节点的路径。(不需要target参数）
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 */
public class BinaryTreeAllPath {


    public static List<String> binaryTreeAllPathsTarget(TreeNode root, int target) {
        LinkedList<String> paths = new LinkedList();
        construct_paths(root, "", paths, target);
        return paths;
    }

    public static void construct_paths(TreeNode root, String path, LinkedList<String> paths, int target) {
        if (root != null) {
            path += Integer.toString(root.value);
            target -= root.value;
            // 当前节点是叶子节点
//            if ((root.left == null) && (root.right == null))  // 返回所有路径
            if ((root.left == null) && (root.right == null) && target == 0)  // 所有路径中值满足target的路径
                paths.add(path);  // 把路径加入到答案中
            else {
                path += "->";  // 当前节点不是叶子节点，继续递归遍历
                construct_paths(root.left, path, paths, target);
                construct_paths(root.right, path, paths, target);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = InitTreeNoe.init1();
        List<String> paths = binaryTreeAllPathsTarget(treeNode, 0);
        for (String value : paths) {
            System.out.println(value);
        }
    }

}
