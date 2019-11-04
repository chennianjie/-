package arithmetic.tree;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 树的深度
 * @Author: nianjie.chen
 * @Date: 11/4/2019
 */
public class DeepLength {

    /**
     * 递归版
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {

        //base case
        if (root == null) {
            return 0;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        //左右子树深度最大+1就是当前节点的深度
        return Math.max(left, right) + 1;
    }


    /**
     * 按层遍历  非递归版
     * @param pRoot
     * @return
     */
    public int TreeDepth2(TreeNode pRoot)
    {
        if(pRoot == null){
            return 0;
        }
        //每次装一层的节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        //当前节点所在层数   当前一共遍历个数   下一层节点总数
        int depth = 0, count = 0, nextCount = 1;
        while(queue.size()!=0){
            TreeNode top = queue.poll();
            count++;
            if(top.left != null){
                queue.add(top.left);
            }
            if(top.right != null){
                queue.add(top.right);
            }
            //本层节点全部遍历完毕
            if(count == nextCount){
                nextCount = queue.size();
                count = 0;
                depth++;
            }
        }
        return depth;
    }

}
