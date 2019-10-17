package arithmetic.tree;

import arithmetic.structure.TreeNode;

import java.util.ArrayList;

/**
 * @Description:
 * 二叉树和为某一值的路径
 * 由于寻找路径是从根路径向下遍历，所以先序遍历比较适合
 * 由于二叉树无法从子节点回到父节点，所以需要用一个容器存储
 * @Author: nianjie.chen
 * @Date: 10/17/2019
 */
public class FindPathSum {

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>(new ArrayList<>());
        if (root == null) {
            return lists;
        }
        int current = 0;
        ArrayList<Integer> path = new ArrayList<>();
        return findPath2(root, lists, path, target, current);
    }


    public ArrayList<ArrayList<Integer>> findPath2(TreeNode node, ArrayList<ArrayList<Integer>> lists, ArrayList<Integer> path,  int target, int current) {

        //更新当前值，更新path
        current += node.val;
        path.add(node.val);

        //如果当前节点是叶节点
        boolean isLeaf = node.left == null && node.right == null;
        if (current == target && isLeaf) {
            lists.add((ArrayList<Integer>) path.clone());
        }

        //如果有左子树
        if (node.left != null) {
            findPath2(node.left, lists, path, target, current);
        }

        //如果有右子树
        if (node.right != null) {
            findPath2(node.right, lists, path, target, current);
        }

        //该路径不满足条件，回到父节点，path删除当前节点
        path.remove(path.size() - 1);
        return lists;
    }
}