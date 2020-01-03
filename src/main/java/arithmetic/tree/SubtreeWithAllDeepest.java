package arithmetic.tree;

import arithmetic.structure.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 具有所有最深结点的子结点
 * @Author: nianjie.chen
 * @Date: 1/3/2020
 */
public class SubtreeWithAllDeepest {
    Map<TreeNode, Integer> depth;
    int max_depth;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        depth = new HashMap();
        //目的为了设计头结点的深度为0
        depth.put(null, -1);
        dfs(root, null);
        max_depth = -1;
        for (Integer d: depth.values())
            max_depth = Math.max(max_depth, d);

        return answer(root);
    }

    /**
     * 查找二叉树中所有结点的深度
     * @param node
     * @param parent
     */
    public void dfs(TreeNode node, TreeNode parent) {
        if (node != null) {
            depth.put(node, depth.get(parent) + 1);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

    public TreeNode answer(TreeNode node) {
        if (node == null || depth.get(node) == max_depth)
            return node;
        TreeNode L = answer(node.left),
                R = answer(node.right);
        if (L != null && R != null) return node;
        if (L != null) return L;
        if (R != null) return R;
        return null;
    }
}
