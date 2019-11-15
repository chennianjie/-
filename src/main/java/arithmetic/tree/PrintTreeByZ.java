package arithmetic.tree;

import arithmetic.structure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Description: 按”之“字型打印二叉树
 * @Author: nianjie.chen
 * @Date: 11/15/2019
 */
public class PrintTreeByZ {

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if (pRoot == null) {
            return null;
        }
        Deque<Integer> queue = new LinkedList<>();
        queue.add(pRoot.val);
        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                queue.poll();
            }
        }
    }
}
