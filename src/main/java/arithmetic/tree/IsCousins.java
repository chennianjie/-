package arithmetic.tree;

import arithmetic.structure.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 二叉树的堂兄弟节点
 * @Author: nianjie.chen
 * @Date: 1/21/2020
 */
public class IsCousins {

   private Map<Integer, Integer> deepthMap;
   private Map<Integer, TreeNode> parMap;

    /**
     * 二叉树两个节点深度相同，父节点不同，他们就是堂兄弟
     * @param root
     * @param x
     * @param y
     * @return
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        //求出每个节点的深度和父节点
        this.deepthMap = new HashMap<>();
        this.parMap = new HashMap<>();
        dfs(root, null);
        return this.deepthMap.get(x)==this.deepthMap.get(y) && this.parMap.get(x) != this.parMap.get(y);

    }

    private void dfs(TreeNode node, TreeNode par) {
        //base case
        if (node != null) {
            //父节点深度+1
            this.deepthMap.put(node.val, par==null ? 0 : this.deepthMap.get(par.val));
            //设置父节点
            this.parMap.put(node.val, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }
}
