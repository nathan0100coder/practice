package practice.methods.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shiLong
 * @version 1.0
 * @desc 二叉树测试
 * @date 2022/3/9
 */

public class BinaryTreeTest {
    private static final List<Integer> res = new ArrayList<>();


    public static void main(String[] args) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        for (int i = 0; i < queue.size(); i++) {
            queue.remove();
        }
        System.out.println("queue = " + queue);


//        Stack<String> strings = new Stack<>();
//        strings.add("f");
//        strings.pop();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }



    private void bfs (TreeNode root) {
        // 利用队列先进先出的性质存储节点
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            res.add(curr.val);
            if (curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
    }
}
