package org.com.zlk.leedcode;

import org.com.zlk.datastructure.tree.TreeNode;

import java.util.*;


/**
 * LeetCode 二叉树专题
 *
 * @Author zc217
 * @Date 2020/7/16
 */
public class BinaryTreeSolution {

    public BinaryTreeSolution() {

    }


    // 144. 二叉树的前序遍历-递归
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

    //前序遍历-递归
    public static void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + "  ");
            preOrder(root.left);
            preOrder(root.right);
        }
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

    // 中序遍历 递归
    public static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val + "  ");
            inOrder(root.right);
        }
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

    // 后序遍历 递归
    public static void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.val + "  ");
        }
    }


    /**
     * 先序遍历 非递归
     */
    public static void preOrder2(TreeNode root) {
        // LinkedList是一个双向链表
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        // 终止条件 节点为null && 栈为空
        while (pNode != null || !stack.isEmpty()) {
            // 1 当前节点非空，打印值，元素进栈，访问左孩子
            // 2 如果左孩子非空，继续执行 1
            // 3 如果左孩子为空，元素出栈、访问右孩子 继续执行 1
            if (pNode != null) {
                System.out.print(pNode.val + "  ");
                stack.push(pNode);
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                pNode = node.right;
            }
        }
    }

    // 144. 二叉树的前序遍历-非递归
    public List<Integer> preorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            if (treeNode != null) {
                list.add(treeNode.val);
                stack.push(treeNode);
                treeNode = treeNode.left;
            } else {
                TreeNode node = stack.pop();
                treeNode = node.right;
            }
        }
        return list;
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
                System.out.print(node.val + "  ");
                pNode = node.right;
            }
        }
    }

    /**
     * 94-中序遍历-非递归
     */
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

    // 145. 二叉树的后序遍历 双栈方法(最容易理解）
    public static List<Integer> postorder(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        // 栈1 存前序 中左右。
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode t = stack1.pop();
            stack2.push(t);
            //栈2存顺序将其转化成中右左。压栈优先压入左子树。
            if (t.left != null) {
                stack1.push(t.left);
            }
            if (t.right != null) {
                stack1.push(t.right);
            }
        }
        // 倒序打印结果  左右中。
        while (!stack2.isEmpty()) {
            ret.add(stack2.pop().val);
        }
        return ret;
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
                System.out.print(cur.val + "  ");
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

    // 145. 二叉树的后序遍历 pre指针方法
    public static List<Integer> postorder2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur;
        TreeNode pre = null; // 记录上次访问节点标记
        stack.push(root); // 压栈顺序 根右左
        while (!stack.empty()) {
            cur = stack.peek(); // 当前节点始终指向栈顶位置
            if (cur == null) { // if这个判断添加leetcode才能通过
                stack.pop();
                continue;
            }
            boolean leafNode = (cur.left == null && cur.right == null);
            boolean isVisit = (pre != null && (pre == cur.left || pre == cur.right));
            // 如果当前结点没有孩子结点或者孩子节点都已被訪问过
            if (leafNode || isVisit) {
                result.add(cur.val);
                stack.pop();
                pre = cur;
            } else {
                // 注意入栈顺序
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
        return result;
    }


    //层次遍历 BFS（广度优先遍历）
    public static List<Integer> layerTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList();
        queue.add(root);
        TreeNode p;
        while (!queue.isEmpty()) {
            // 元素出队、打印
            p = queue.poll();
            result.add(p.val);
            // 该元素如果有孩子，那么依次进入队列
            if (p.left != null) {
                queue.add(p.left);
            }
            if (p.right != null) {
                queue.add(p.right);
            }
        }
        return result;
    }

    // 102. 二叉树的层序遍历 BFS 迭代实现  时间复杂度：O(n)  空间复杂度：O(n)
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
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
            result.add(temp);
        }
        return result;
    }

    // 102. 二叉树的层序遍历-DFS递归
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        //用来存放最终结果
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelOrderDFS(root, res, 1);
        return res;
    }

    private static void levelOrderDFS(TreeNode root, List<List<Integer>> res, int index) {
        // 插入一个空list放到res中
        if (index > res.size()) {
            res.add(new ArrayList<>());
        }
        // 将当前节点的值加入到res中,index代表当前层
        res.get(index - 1).add(root.val);
        // 递归的处理左子树，右子树，同时将层数index+1
        if (root.left != null) {
            levelOrderDFS(root.left, res, index + 1);
        }
        if (root.right != null) {
            levelOrderDFS(root.right, res, index + 1);
        }
    }

    // 637. 二叉树的层平均值 层次遍历的进化
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
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

    /**
     * 深度遍历-递归
     */
    static List<Integer> depResult = new ArrayList<>();

    public static List<Integer> depthOrder(TreeNode root) {
        if (root == null) {
            return depResult;
        }
        depResult.add(root.val);
        depthOrder(root.left);
        depthOrder(root.right);
        return depResult;
    }

    /**
     * 深度遍历-非递归
     */
    public static List<Integer> depthOrder1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            // 注意入栈顺序和前序顺序
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }


    /**
     * 递归-树的高度（深度）
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 交换树左右孩子
     */
    public static void swapLRnode(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    // 剑指 Offer 28. 对称的二叉树
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return symmetricHelp(root.left, root.right);
    }

    public static boolean symmetricHelp(TreeNode left, TreeNode right) {
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

    // 226. 翻转二叉树/二叉树的镜像
    public static TreeNode invertTree(TreeNode root) {
        // 递归函数的终止条件，节点为空时返回
        if (root == null) {
            return null;
        }
        swapLRnode(root);
        //递归交换当前节点的 左子树
        invertTree(root.left);
        //递归交换当前节点的 右子树
        invertTree(root.right);
        //函数返回时就表示当前这个节点，以及它的左右子树都已经交换完了
        return root;
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


    // 617. 合并二叉树 时间复杂度：O(N) 空间复杂度O(N)
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
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


    // 404. 左叶子之和
    public static int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 判断节点是否是左叶子节点
        if (root.left != null && root.left.left == null && root.left.right == null) {
            // 递归找其他的左孩子
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        // 非叶子节点 左子树和右子树都要递归处理。
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

    // 114. 将二叉树展开为链表
    public static TreeNode flatten(TreeNode root) {
        if (root == null) {
            return root;
        }
        // flatten函数：输入一个节点root，那么以root为根的二叉树就会被拉平为一条链表。
        // 1、将root的左子树和右子树拉平
        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;
        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;
        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
        return root;
    }

    // 116. 填充二叉树节点的右侧指针
    public TreeNode connect(TreeNode root) {
        if(root == null)
            return root;
        connectTwoNode(root.left,root.right);
        return root;
    }

    public void connectTwoNode(TreeNode n1,TreeNode n2){
        if(n1==null || n2 ==null){
            return;
        }
        n1.next = n2;
        connectTwoNode(n1.left, n1.right);
        connectTwoNode(n2.left, n2.right);
        connectTwoNode(n1.right,n2.left);
    }

    /////////////////////////////////////////////////////////////////////////////////////

    // 230. 二叉搜索树中第K小的元素 (中序遍历有序）
    public static int kthSmallest(TreeNode root, int k) {
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

    // 记录结果
    static int res = 0;
    // 记录当前元素的排名
    static int rank = 0;
    public static int kthSmallest1(TreeNode root, int k) {
        // 利用 BST 的中序遍历特性
        traverse(root, k);
        return res;
    }
    private static void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        traverse(root.left, k);
        /* 中序遍历代码位置 */
        rank++;
        if (k == rank) {
            // 找到第 k 小的元素
            res = root.val;
            return;
        }
        /*****************/
        traverse(root.right, k);
    }


    // 98.验证二叉搜索树   面试题 04.05. 合法二叉搜索树
    // 节点的左子树只包含小于当前节点的数。节点的右子树只包含大于当前节点的数。 所有左子树和右子树自身必须也是二叉搜索树
    public static boolean isValidBST(TreeNode root) {
        // 函数表示考虑以 root 为根的子树，判断子树中所有节点的值是否都在 (l,r) 的范围内
        return help(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean help(TreeNode root, long lower, long upper) {
        if (root == null) {
            return true;
        }
        if (root.val >= upper || root.val <= lower) {
            return false;
        }
        return help(root.left, lower, root.val) && help(root.right, root.val, upper);
    }

    // 98.二叉搜索树、也称为二叉查找树（英语：Binary Search Tree）、有序二叉树（ordered binary tree）或排序二叉树（sorted binary tree）
    public static boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 「中序遍历」得到的值构成的序列一定是升序的
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
                min = node.val; // 实时检查当前节点的值是否大于前一个中序遍历到的节点的值即可
                treeNode = node.right;// 别忘记遍历右孩子
            }
        }
        return true;
    }


    // 173. 二叉搜索树迭代器  二叉搜索树的中序遍历是有序
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

    /**
     * @return the next smallest number
     */
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

    /**
     * @return whether we have a next smallest number
     */
    public static boolean hasNext() {
        return !nodeStack.isEmpty();
    }


    // 236. 二叉树中两个指定节点的最近公共祖先,最近公共祖先节点可以为节点本身,所有节点的值都是唯一的。
    // 时间复杂度：O(N)空间复杂度：O(N)，二叉树最坏情况下为一条链，此时高度为 N，因此空间复杂度为 O(N)。
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode result = new TreeNode(0);
        lcaDFS(root, p, q, result);   // 递归调用的栈深度取决于二叉树的高度
        return result;
    }

    private static boolean lcaDFS(TreeNode root, TreeNode p, TreeNode q, TreeNode res) {
        // 当遍历到叶结点后就会返回null
        if (root == null) {
            return false;
        }
        // 递归遍历左右子树是否包含p或q
        boolean lson = lcaDFS(root.left, p, q, res);
        boolean rson = lcaDFS(root.right, p, q, res);
        // 祖先定义：一个节点p在root的左(右)子树中，或p=root，则称root是p的祖先
        // root是p, q最近公共祖先(三种情况）
        // 1.p和q在root的子树中，且分列root的异侧
        // 2.p=root ，且 q在root的左或右子树中；
        // 3.q=root ，且 p在root的左或右子树中；
        if ((lson && rson) || (root.val == p.val || root.val == q.val) && (lson || rson)) {
            res.val = root.val;  // 记录公共祖先
        }
        // 自底向上从叶子节点开始更新的，判断子树是否包含p或q
        return lson || rson || root.val == p.val || root.val == q.val;
    }


    // 235. 二叉搜索树的最近公共祖先  找到该树中两个指定节点的最近公共祖先。所有节点的值都是唯一的。
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        //根结点比两个结点都大 就在左子树找
        //根结点比两个结点都小 就在右子树找
        //否则就返回根结点
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestorBST(root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestorBST(root.right, p, q);
        return root;
    }

    // 230. 二叉搜索树中第K小的元素，假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数
    // 中序遍历  时间复杂度：O(N) 空间复杂度：O(N)
    public static int kthSmallestBST(TreeNode root, int k) {
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
        // 递归实现DFS 双端队列保存同一层的所有节点，并交替插入（从首部插入或从尾部插入）得到需要的输出顺序。
        zigzagLevelOrderDFS(root, 1, res);
        return res;
    }

    private static void zigzagLevelOrderDFS(TreeNode root, int index, List<List<Integer>> res) {
        if (index > res.size()) {
            // 每层创建一个双端队列
            LinkedList<Integer> queue = new LinkedList<Integer>();
            queue.add(root.val);
            res.add(queue);
        } else {
            // 将遍历的节点值加入到对应queue中
            if (index % 2 != 0) {
                // 尾插入
                res.get(index - 1).add(root.val);
            } else {
                // 头插入
                res.get(index - 1).add(0, root.val);
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
    public int findBottomLeftValue(TreeNode root) {
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

    // 257. 二叉树的所有路径 (递归）返回所有从根节点到叶子节点的路径
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        construct_path(root, "", paths);
        return paths;
    }

    private void construct_path(TreeNode root, String s, List<String> paths) {
        if (root != null) {
            s += Integer.toString(root.val);
            // 当前节点是叶子节点
            if ((root.left == null) && (root.right == null)) {
                // 把路径加入到集合
                paths.add(s);
            } else {
                s += "->";
                // 递归
                construct_path(root.left, s, paths);
                construct_path(root.right, s, paths);
            }
        }
    }

    // 257. 二叉树的所有路径 (迭代）
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<String> pathQueue = new LinkedList();
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        pathQueue.add(Integer.toString(root.val));
        nodeQueue.add(root);
        TreeNode node;
        String path;
        while (!nodeQueue.isEmpty()) {
            // 保存每次得到的节点和路径
            node = nodeQueue.poll();
            path = pathQueue.poll();
            // 叶子节点
            if (node.left == null && node.right == null) {
                res.add(path);
            }
            // 将root左右孩子加入到nodeQueue中
            if (node.left != null) {
                nodeQueue.add(node.left);
                pathQueue.add(path + "->" + node.left.val);
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
                pathQueue.add(path + "->" + node.right.val);
            }
        }
        return res;
    }

    // 112. 路径总和(判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和)
    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        // 递归
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    // 112. 路径总和 (dfs）
    public static boolean hasPathSum2(TreeNode root, int sum) {
        return helper(root, 0, sum);
    }

    private static boolean helper(TreeNode root, int cur, int target) {
        if (root == null) {
            return false;
        }
        // 累计路径上值
        cur = root.val + cur;
        // 遍历到叶子节点
        if (root.left == null && root.right == null) {
            return cur == target;
        } else {
            return helper(root.left, cur, target) || helper(root.right, cur, target);
        }
    }


    // 113. 路径总和 II （剑指 Offer 34） 二叉树中和为某一值的所有路径 (树的根节点开始往下一直到叶节点所经过的节点形成一条路径)
    public static List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        // 第二个参数是数组对象 传的是引用
        pathSumDFS(root, new ArrayList<>(), res, sum);
        return res;
    }

    private static void pathSumDFS(TreeNode root, List<Integer> path, List<List<Integer>> res, int sum) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        int remain = sum - root.val;
        if (root.left == null && root.right == null) {
            if (remain == 0) {
                res.add(path);
                return;
            } else { // 注意else别忘记了
                return;
            }
        }
        pathSumDFS(root.left, new ArrayList<>(path), res, remain); // 新建一个ArrayList对象然后拷贝path值
        pathSumDFS(root.right, new ArrayList<>(path), res, remain);
    }

    // 437. 路径总和 III  路径方向必须是向下的（只能从父节点到子节点）找出路径和等于给定数值的路径总数
    // 注意count不能作为dfs函数参数。因为不是以根节点为起始点。
    private static int count = 0;

    public static int pathSum3(TreeNode root, int sum) {
        // 双重DFS: 先序递归遍历每个节点;
        if (root == null) {
            return count;
        }
        pathSum3DFS(root, sum);
        pathSum3(root.left, sum);
        pathSum3(root.right, sum);
        return count;
    }

    // 以每个节点作为起始节点DFS寻找满足条件的路径.
    private static void pathSum3DFS(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        sum -= root.val;
        if (sum == 0) {
            count++;
        }
        pathSum3DFS(root.left, sum);
        pathSum3DFS(root.right, sum);
    }

    // 437. 路径总和 III  同pathSum3方法
    public static int pathSum33(TreeNode root, int sum) {
        if (root == null) return 0;
        return helper(root, sum) + pathSum33(root.left, sum) + pathSum33(root.right, sum);
    }

    private static int helper(TreeNode root, int sum) {
        if (root == null) return 0;
        sum -= root.val;
        return (sum == 0 ? 1 : 0) + helper(root.left, sum) + helper(root.right, sum);
    }

    // 437. 路径总和 III  DFS加回溯  别人做法
    // https://leetcode-cn.com/problems/path-sum-iii/solution/liang-chong-fang-fa-jian-dan-yi-dong-ban-ben-by-a3/
    public static int pathSum333(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // key 路径和 value 次数
        map.put(0, 1);
        return helper(root, map, sum, 0);
    }

    public static int helper(TreeNode root, HashMap<Integer, Integer> map, int sum, int pathSum) {
        int res = 0;
        if (root == null) {
            return 0;
        }
        pathSum += root.val;
        // 满足条件的路径
        res += map.getOrDefault(pathSum - sum, 0);

        map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
        // dfs
        res = helper(root.left, map, sum, pathSum) + helper(root.right, map, sum, pathSum) + res;
        // 回溯
        map.put(pathSum, map.get(pathSum) - 1);
        return res;
    }

    // 124. 二叉树中最大路径和 (一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点)
    private static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxGain(root);// 根节点递归得到每个节点的最大贡献值
        return maxSum;
    }

    public static int maxGain(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 递归计算左右子节点的最大贡献值,只有在最大贡献值大于0时，才会选取对应子节点
        int leftGain = Math.max(maxGain(root.left), 0);
        int rightGain = Math.max(maxGain(root.right), 0);
        // 计算该节点最大路径和为该节点值+该节点左节点最大贡献值+右子节点最大贡献值
        int priceNewPath = root.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, priceNewPath);// 更新最大路径和长度
        return root.val + Math.max(leftGain, rightGain);// 计算该节点最大贡献值
    }

}
