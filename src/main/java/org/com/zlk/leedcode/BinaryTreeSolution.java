package org.com.zlk.leedcode;

import org.com.zlk.datastructure.tree.BasicOperationTree;
import org.com.zlk.datastructure.tree.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

import static org.com.zlk.datastructure.tree.BasicOperationTree.maxDepth;

/**
 * LeetCode 二叉树专题
 *
 * @Author zc217
 * @Date 2020/7/16
 */
public class BinaryTreeSolution {

    // 剑指 Offer 28. 对称的二叉树
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return symmetricHelp(root.left, root.right);
    }

    public boolean symmetricHelp(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return symmetricHelp(left.left, right.right) && symmetricHelp(left.right, right.left);
    }

    // 617. 合并二叉树
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // 递归 时间复杂度：O(N) 空间复杂度O(N) 栈空间
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    // 226. 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        // 递归函数的终止条件，节点为空时返回
        if (root == null) {
            return root;
        }
        //下面三句是将当前节点的左右子树交换
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        //递归交换当前节点的 左子树
        invertTree(root.left);
        //递归交换当前节点的 右子树
        invertTree(root.right);
        //函数返回时就表示当前这个节点，以及它的左右子树都已经交换完了
        return root;
    }

    // 637. 二叉树的层平均值
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            double sum = 0;
            int qsize = queue.size();
            for (int i = 0; i < qsize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                sum += node.val;
            }
            result.add(sum / qsize);
        }
        return result;
    }

    // 剑指 Offer 26. 树的子结构 (约定空树不是任意一个树的子结构)B是A的子结构
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 如果A或B都走完了还没找到，那应该就是找不到了
        if (A == null || B == null) {
            return false;
        }
        // DFS 看看当前节点/看看左边/看看右边
        return recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);

    }

    // 递归
    private boolean recur(TreeNode a, TreeNode b) {
        // b这边都看完了，还没挑出不同？那就是了吧！
        if (b == null) {
            return true;
        }
        // b这边还没看完了，a那边就null了
        if (a == null) {
            return false;
        }
        return (a.val == b.val) && recur(a.left, b.left) && recur(a.right, b.right);
    }

    // 404. 左叶子之和
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 判断节点是否是左叶子节点
        if (root.left!= null && root.left.left == null && root.left.right == null) {
            // 递归找其他的左孩子
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        // 非叶子节点 左子树和柚子树都要递归处理。
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    // 404. 左叶子之和
    public int sumOfLeftLeaves2(TreeNode root) {
        return sumOfLeftLeaves2Help(root, false);
    }

    public int sumOfLeftLeaves2Help(TreeNode root, boolean flag) {
        if (root == null) {
            return 0;
        }
        int leave = 0;
        // 左叶子节点
        if (flag && root.left == null && root.right == null) {
            leave = root.val;
        }
        // 标志位节点一定是左孩子
        int left = sumOfLeftLeaves2Help(root.left, true);
        int right = sumOfLeftLeaves2Help(root.right, false);
        return leave + left + right;
    }






    // 110. 平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 分别获取左右子树最大深度值
        int leftChildDepth = maxDepth(root.left);
        int rightChildDepth = maxDepth(root.right);
        // 平衡树左右子树高度差都小于等于1
        boolean match = Math.abs(leftChildDepth - rightChildDepth) < 2;
        return match && isBalanced(root.left) && isBalanced(root.right);
    }

    // 剑指 Offer 27. 二叉树的镜像
    public static TreeNode mirrorBinaryTree(TreeNode root) {
        if (null == root) {
            return null;
        }
        // 交换父节点的左右孩子
        BasicOperationTree.swap(root);
        mirrorBinaryTree(root.left);
        mirrorBinaryTree(root.right);
        return root;
    }


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


    // 98.验证二叉搜索树   面试题 04.05. 合法二叉搜索树
    // 节点的左子树只包含小于当前节点的数。节点的右子树只包含大于当前节点的数。 所有左子树和右子树自身必须也是二叉搜索树
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
    public BinaryTreeSolution(TreeNode root) {
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


    // 102. 二叉树的层序遍历 BFS  迭代实现
    // 时间复杂度： O(n) 空间复杂度：O(n)
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 结果集合
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // 使用队列
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        // 根节点入队
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new LinkedList<>();
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode treeNode = queue.poll();
                temp.add(treeNode.val);
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
            res.add(temp);
        }
        return res;
    }

    // 102. 二叉树的层序遍历-DFS递归
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        //用来存放最终结果
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelOrderDFS(1, root, res);
        return res;
    }

    private static void levelOrderDFS(int index, TreeNode root, List<List<Integer>> res) {
        // 插入一个空list放到res中
        if (index > res.size()) {
            res.add(new ArrayList<Integer>());
        }
        // 将当前节点的值加入到res中,index代表当前层  空间复杂度：O(h)，h 是树的高度
        res.get(index - 1).add(root.val);
        // 递归的处理左子树，右子树，同时将层数index+1
        if (root.left != null) {
            levelOrderDFS(index + 1, root.left, res);
        }
        if (root.right != null) {
            levelOrderDFS(index + 1, root.right, res);
        }
    }

    // 236. 二叉树中两个指定节点的最近公共祖先,最近公共祖先节点可以为节点本身,所有节点的值都是唯一的。
    // 时间复杂度：O(N)空间复杂度：O(N)，其中 N 是二叉树的节点数。递归调用的栈深度取决于二叉树的高度
    // 二叉树最坏情况下为一条链，此时高度为 N，因此空间复杂度为 O(N)
    // 递归方式
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = new TreeNode(0);
        lcaDFS(root, p, q, res);
        return res;
    }

    private static boolean lcaDFS(TreeNode root, TreeNode p, TreeNode q, TreeNode res) {
        // 当遍历到叶结点后就会返回null
        if (root == null) {
            return false;
        }
        // 递归遍历左子树和右子树是否包含p或q
        boolean lson = lcaDFS(root.left, p, q, res);
        boolean rson = lcaDFS(root.right, p, q, res);
        // root是 p, q的最近公共祖先
        // 1.p和q在root的子树中，且分列root的异侧
        // 2.p=root ，且 q在root的左或右子树中；
        // 3.q=root ，且 p在root的左或右子树中；
        if ((lson && rson) || (root.val == p.val || root.val == q.val) && (lson || rson)) {
            // 记录公共祖先
            res.val = root.val;
        }
        // 自底向上从叶子节点开始更新的，判断子树是否包含p或q
        // 祖先的定义：一个节点p在root的左（右）子树中，或p=root ，则称root是p的祖先
        return lson || rson || root.val == p.val || root.val == q.val;
    }


    // 235. 二叉搜索树的最近公共祖先
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        //根结点比两个结点都大 就在左子树找
        //根结点比两个结点都小 就在右子树找
        //否则就返回根结点
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestorBST(root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestorBST(root.right, p, q);
        return root;
    }

    // 230. 二叉搜索树中第K小的元素，假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数
    public static int kthSmallestBST(TreeNode root, int k) {
        // 中序遍历 时间复杂度：O(N) 空间复杂度：O(N)
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        TreeNode temp = root;
        while (temp != null || !stack.isEmpty()) {
            if (temp != null) {
                stack.push(temp);
                temp = temp.left;
            } else {
                TreeNode node = stack.pop();
                list.add(node.val);
                temp = node.right;
            }
            if (list.size() == k) {
                return list.get(k - 1);
            }
        }
        return -1;
    }

    // 103. 二叉树的锯齿形层次遍历
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 结果集合
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        //递归实现DFS ，双端队列保存同一层的所有节点，并交替插入方向（从首部插入或从尾部插入）得到需要的输出顺序。
        zigzagLevelOrderDFS(root, 1, res);
        return res;
    }

    private static void zigzagLevelOrderDFS(TreeNode root, int index, List<List<Integer>> res) {
        if (index > res.size()) {
            // 每层创建一个双端队列
            LinkedList<Integer> queue = new LinkedList<Integer>();
            queue.add(root.val);
            res.add(queue);
        }else {
            // 将遍历的节点值加入到对应queue中
            if (index % 2 != 0) {
                // 尾插入
                res.get(index-1).add(root.val);
            } else {
                // 头插入
                res.get(index-1).add(0, root.val);
            }
        }
        // 递归DFS
        if (root.left != null) {
            zigzagLevelOrderDFS(root.left, index + 1, res);
        }
        if (root.right != null) {
            zigzagLevelOrderDFS(root.right, index + 1, res);
        }
    }


    // 513. 找树左下角的值 (二叉树，在树的最后一行找到最左边的值)  *****
    public int findBottomLeftValue(TreeNode root){
        if (root == null) {
            return -1;
        }
        // BFS遍历.队列保存每层对应节点子节点
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 注意这里没有用临时节点
            root = queue.poll();
            // 注意这里先走右子树，如果最后一层有两个节点，应该最后一个弹出的元素是符合条件的。
            if (root.right != null) {
                queue.add(root.right);
            }
            if (root.left != null) {
                queue.add(root.left);
            }
        }
        return root.val;
    }

    // 513. 找树左下角的值
    int left_value = 0;
    int max_level = 0;
    public int findBottomLeftValue2(TreeNode root) {
        // 最后一行 可以用DFS遍历，记录最深层level
        bottomLeftDFS(root, 1);
        return left_value;
    }

    public void bottomLeftDFS(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level > max_level) {
            max_level = level;
            left_value = root.val;
        }
        bottomLeftDFS(root.left, level + 1);
        bottomLeftDFS(root.right, level + 1);
    }




    public static void main(String[] args) {
//        TreeNode treeNode1 = new TreeNode(1);
//        TreeNode treeNode2 = new TreeNode(2);
//        TreeNode treeNode3 = new TreeNode(3);
//        treeNode1.right = treeNode2;
//        treeNode2.left = treeNode3;
//        List<Integer> ret = inorderTraversal(treeNode1);
//        List<Integer> ret1 = inorder(treeNode1);
//        List<Integer> postorder = postorder(treeNode1);
//        List<Integer> postorder2 = postorder2(treeNode1);
//        for (Integer num : postorder2) {
//            System.out.print(num + "\t");
//        }
//
//        boolean validBST = isValidBST(treeNode1);
//        boolean validBST1 = isValidBST1(treeNode1);
//
//        System.out.println();
//        TreeNode t1 = new TreeNode(7);
//        TreeNode t2 = new TreeNode(3);
//        TreeNode t3 = new TreeNode(15);
//        TreeNode t4 = new TreeNode(9);
//        TreeNode t5 = new TreeNode(20);
//        t1.left =t2;
//        t1.right=t3;
//        t3.left =t4;
//        t3.right=t5;
//        BinaryTree bstIterator = new BinaryTree(t1);
//        while (bstIterator.hasNext()){
//            System.out.println(bstIterator.next());
//        }


        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        t1.left=t2;
        t1.right=t3;
        t3.left=t4;
        t3.right=t5;
        List<List<Integer>> lists = levelOrder(t1);
        List<List<Integer>> lists2 = levelOrder2(t1);
        System.out.println(lists.stream().collect(Collectors.toList()));
        System.out.println(lists2.stream().collect(Collectors.toList()));
        System.out.println(zigzagLevelOrder(t1).stream().collect(Collectors.toList()));

//        TreeNode t1 = new TreeNode(3);
//        TreeNode t2 = new TreeNode(5);
//        TreeNode t3 = new TreeNode(1);
//        TreeNode t4 = new TreeNode(6);
//        TreeNode t5 = new TreeNode(2);
//        TreeNode t6 = new TreeNode(0);
//        TreeNode t7 = new TreeNode(8);
//        TreeNode t8 = new TreeNode(7);
//        TreeNode t9 = new TreeNode(4);
//        t1.left=t2;
//        t1.right=t3;
//        t2.left=t4;
//        t2.right=t5;
//        t3.left=t6;
//        t3.right=t7;
//        t5.left=t8;
//        t5.right=t9;
//        TreeNode res = lowestCommonAncestor(t1, t7, t9);
//        System.out.println(res.val);


//        TreeNode t1 = new TreeNode(3);
//        TreeNode t2 = new TreeNode(1);
//        TreeNode t3 = new TreeNode(4);
//        TreeNode t4 = new TreeNode(2);
//        t1.left = t2;
//        t1.right = t3;
//        t3.right = t2;
//        int kthSmallestBST = kthSmallestBST(t1, 1);
//        System.out.println(kthSmallestBST);


    }
}
