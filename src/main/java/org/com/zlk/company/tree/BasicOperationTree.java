package org.com.zlk.company.tree;

import java.util.LinkedList;
import java.util.Stack;

public class BasicOperationTree {

    /**
     * 先序遍历递归
     */
    public static void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.value + "  ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /**
     * 中序遍历递归
     */
    public static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.value + "  ");
            inOrder(root.right);
        }
    }

    /**
     * 后序遍历递归
     */
    public static void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.value + "  ");
        }
    }


    /**
     * 先序遍历非递归
     */
    public static void preOrder2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        // 终止条件 节点为null && 栈为空
        while (pNode != null || !stack.isEmpty()) {
            // 1 当前节点非空，打印值，元素进栈，访问左孩子
            // 2 如果左孩子非空，继续执行 1
            // 3 如果左孩子为空，元素出栈、访问右孩子 继续执行 1
            if (pNode != null) {
                System.out.print(pNode.value + "  ");
                stack.push(pNode);
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                pNode = node.right;
            }
        }
    }


    /**
     * 中序遍历非递归
     */
    public static void inOrder2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            // 1 当前节点非空，元素进栈，访问左孩子
            // 2 如果左孩子非空，继续执行 1
            // 3 如果左孩子为空，元素出栈、打印值、访问右孩子 继续执行 1
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                System.out.print(node.value + "  ");
                pNode = node.right;
            }
        }
    }

    /**
     * 非递归后序遍历
     */
    public static void postOrder2(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur;  //当前结点
        TreeNode pre = null; //上一次访问的结点标记
        s.push(root);
        while (!s.empty()) {
            cur = s.peek(); //当前节点始终指向栈顶位置
            boolean leafNode = (cur.left == null && cur.right == null);
            boolean isVisit = (pre != null && (pre == cur.left || pre == cur.right));
            // 如果当前结点没有孩子结点或者孩子节点都已被訪问过
            if (leafNode || isVisit) {
                // 打印、出栈、标记pro
                System.out.print(cur.value + "  ");
                s.pop();
                pre = cur;
            } else {
                // 主要入栈顺序
                if (cur.right != null) {
                    s.push(cur.right);
                }
                if (cur.left != null) {
                    s.push(cur.left);
                }
            }
        }
    }


    /**
     * 层次遍历 BFS
     * https://www.cnblogs.com/rever/p/7109572.html
     */
    public static void laywerTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList();
        queue.add(root);
        TreeNode currentNode;
        while (!queue.isEmpty()) {
            // 元素出队、打印
            currentNode = queue.poll();
            System.out.print(currentNode.value + "  ");
            // 该元素如果有孩子，那么依次进入队列
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
    }


    /**
     * 深度遍历 DFS
     * 事实上深度遍历就是上面的前序、中序和后序。可是为了保证与广度优先遍历相照顾，也写在这。
     * 代码也比較好理解，事实上就是前序遍历
     */
    public static void depthOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.value + "  ");
            // 注意入栈顺序和前序顺序
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }
}
