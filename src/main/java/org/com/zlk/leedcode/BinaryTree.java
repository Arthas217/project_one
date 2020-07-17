package org.com.zlk.leedcode;

import org.com.zlk.datastructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树
 *
 * @Author zc217
 * @Date 2020/7/16
 */
public class BinaryTree {

    // 94-中序遍历-递归
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderHelp(list, root);
        return list;
    }

    private static void inorderHelp(List<Integer> list, TreeNode root) {
        if (root != null) {
            if (root.left != null) {
                inorderHelp(list, root.left);
            }
            list.add(root.val);
            if (root.right != null) {
                inorderHelp(list, root.right);
            }
        }
    }

    // 94-中序遍历-非递归
    public static List<Integer> inorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        // 利用栈
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode treeNode = root;
        // 循环结束条件是节点为空 && 栈中无元素
        while (treeNode != null || !stack.isEmpty()) {
            if (treeNode != null) {
                // 压入栈，如果左孩子非空，继续执行
                stack.push(treeNode);
                treeNode = treeNode.left;
            } else {
                // 如果左孩子为空，元素出栈、访问右孩子
                TreeNode node = stack.pop();
                list.add(node.val);
                treeNode = node.right;
            }
        }
        return list;
    }

    // 144. 二叉树的前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderHelp(list, root);
        return list;
    }

    private void preorderHelp(List<Integer> list, TreeNode root) {
        if (root != null) {
            list.add(root.val);
            if (root.left != null) {
                preorderHelp(list, root.left);
            }
            if (root.right != null) {
                preorderHelp(list, root.right);
            }
        }
    }

    // 144. 二叉树的前序遍历-非递归
    public List<Integer> preorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode treeNode = root;
        // 循环结束条件是节点为空 && 栈中无元素
        while (treeNode != null || !stack.isEmpty()) {
            if (treeNode != null) {
                list.add(treeNode.val);
                stack.push(treeNode);
                treeNode = treeNode.left;
            } else {
                // 如果左孩子为空，元素出栈、访问右孩子
                TreeNode node = stack.pop();
                treeNode = node.right;
            }
        }
        return list;

    }

    // 145. 二叉树的后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderHelp(list, root);
        return list;
    }

    private void postorderHelp(List<Integer> list, TreeNode root) {
        if (root != null) {
            if (root.left != null) {
                postorderHelp(list, root.left);
            }
            if (root.right != null) {
                postorderHelp(list, root.right);
            }
            list.add(root.val);
        }
    }

    // 145. 二叉树的后序遍历 双栈方法
    public static List<Integer> postorder(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        // 前序遍历的过程 是中左右。
        while (!stack1.isEmpty()) {
            TreeNode t = stack1.pop();
            stack2.push(t);
            // 将其转化成中右左。也就是压栈的过程中优先压入左子树，在压入右子树。
            if (t.left != null) {
                stack1.push(t.left);
            }
            if (t.right != null) {
                stack1.push(t.right);
            }
        }
        // 然后将这个结果返回来，这里是利用栈的先进后出倒序打印。
        while (!stack2.isEmpty()) {
            ret.add(stack2.pop().val);
        }
        return ret;
    }

    // 145. 二叉树的后序遍历 pre指针方法
    public static List<Integer> postorder2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur;  //当前结点
        TreeNode pre = null; //上一次访问的结点标记
        s.push(root);
        while (!s.empty()) {
            cur = s.peek(); //当前节点始终指向栈顶位置
            // if这个判断添加leetcode才能通过
            if (cur == null) {
                s.pop();
                continue;
            }
            boolean leafNode = (cur.left == null && cur.right == null);
            boolean isVisit = (pre != null && (pre == cur.left || pre == cur.right));
            // 如果当前结点没有孩子结点或者孩子节点都已被訪问过
            if (leafNode || isVisit) {
                ret.add(cur.val);
                s.pop();
                pre = cur;
            } else {
                // 注意入栈顺序
                if (cur.right != null) {
                    s.push(cur.right);
                }
                if (cur.left != null) {
                    s.push(cur.left);
                }
            }
        }
        return ret;
    }


    // 98.验证二叉搜索树（节点的左子树只包含小于当前节点的数。节点的右子树只包含大于当前节点的数。 所有左子树和右子树自身必须也是二叉搜索树）
    public static boolean isValidBST(TreeNode root) {
        // 函数表示考虑以 root 为根的子树，判断子树中所有节点的值是否都在 (l,r) 的范围内
        return help(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean help(TreeNode root, long upper, long lower) {
        if (root == null) {
            return true;
        }
        if (root.val >= upper || root.val <= lower) {
            return false;
        }
        return help(root.left, lower, root.val) && help(root.right, root.val, upper);
    }

    // 98.二叉搜索树「中序遍历」得到的值构成的序列一定是升序的，实时检查当前节点的值是否大于前一个中序遍历到的节点的值即可
    public static boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 中序遍历
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode treeNode = root;
        long min = Long.MIN_VALUE;
        while (!stack.isEmpty() || treeNode != null) {
            if (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            } else {
                TreeNode node = stack.pop();
                if (node.val <= min) {
                    return false;
                }
                min = node.val;
                // 别忘记遍历右孩子
                treeNode = node.right;
            }
        }
        return true;
    }


    // 173. 二叉搜索树迭代器 二叉搜索树特点参见  98.验证二叉搜索树 中序遍历是有序的
    // next()和hasNext()操作的时间复杂度是O(1)，并使用O(h) 内存，其中h是树的高度。
    // 构造函数名称主要体现了BSTIterator二叉搜索树迭代器含义，名称为了和类名一致
    public static Stack<TreeNode> nodeStack = null;
    public BinaryTree(TreeNode root) {
        nodeStack = new Stack<>();
        while (root != null) {
            nodeStack.push(root);
            root = root.left;
        }
    }
    // 用一个栈缓存从根节点到叶子节点的路径上所经过的且还没有输出的节点，相当于保存了上下文环境
    // 调用next()将返回二叉搜索树中的下一个最小的数
    /** @return the next smallest number */
    public static int next() {
        // 左下部分子树全部访问之后,根据栈内的节点继续访问右上部分的子树
        TreeNode peek = nodeStack.peek();
        nodeStack.pop(); // 必须在while之前，否则temp加入节点后，此时peek节点还在栈中，不符合逻辑。
        TreeNode temp = peek.right;
        while (temp != null) {
            nodeStack.push(temp);
            temp = temp.left;
        }
        return peek.val;

    }
    /** @return whether we have a next smallest number */
    public static boolean hasNext() {
        return !nodeStack.isEmpty();
    }


    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.right = treeNode2;
        treeNode2.left = treeNode3;
        List<Integer> ret = inorderTraversal(treeNode1);
        List<Integer> ret1 = inorder(treeNode1);
        List<Integer> postorder = postorder(treeNode1);
        List<Integer> postorder2 = postorder2(treeNode1);
        for (Integer num : postorder2) {
            System.out.print(num + "\t");
        }

        boolean validBST = isValidBST(treeNode1);
        boolean validBST1 = isValidBST1(treeNode1);

        System.out.println();
        TreeNode t1 = new TreeNode(7);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(15);
        TreeNode t4 = new TreeNode(9);
        TreeNode t5 = new TreeNode(20);
        t1.left =t2;
        t1.right=t3;
        t3.left =t4;
        t3.right=t5;
        BinaryTree bstIterator = new BinaryTree(t1);
        while (bstIterator.hasNext()){
            System.out.println(bstIterator.next());
        }

    }
}
