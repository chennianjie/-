package arithmetic.structure;



import java.util.Collection;
import java.util.HashMap;

/**
 * 并查集
 * 条件：一开始必须把所有元素插入，不能中途插入
 */
public class UnionFindSet {
    public static HashMap<Node, Node> fatherMap; //表示当前节点的父节点
    public static HashMap<Node, Integer> sizeMap;  //表示当前节点下一共有多少节点（只有是代表节点时才有效）

    public UnionFindSet(Collection<Node> nodes) {
        fatherMap.clear();
        sizeMap.clear();
        for (Node node : nodes){
            fatherMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    public static Node findFather(Node node) {
        Node father = fatherMap.get(node);
        if (node != father) {
            father = fatherMap.get(father);
        }

        fatherMap.put(node, father);
        return father;
    }

    public static boolean isSameSet(Node a, Node b) {
        return findFather(a) == findFather(b);
    }

    public static void union(Node a, Node b) {
        //先判断两节点是否在同一个集合里面
        Node aFather = findFather(a);
        Node bFather = findFather(b);

        if (aFather != bFather) {
            //不在一个集合里面，根据size大小进行合并，小的挂在大的下面
            Integer aSize = sizeMap.get(aFather);//这里只有是代表节点的时候才有效
            Integer bSize = sizeMap.get(bFather);
            if (aSize <= bSize) {
                fatherMap.put(aFather, bFather);
                sizeMap.put(bFather, aSize + bSize);
            }else {
                fatherMap.put(bFather, aFather);
                sizeMap.put(aFather, aSize + bSize);
            }
        }
    }



    static class Node{

    }
}
