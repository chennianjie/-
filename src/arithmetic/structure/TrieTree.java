package arithmetic.structure;

import java.util.HashMap;

public class TrieTree {


    static class Node{
        public Integer pass = 0;
        public Integer end = 0;
        public HashMap<Character, Node> nexts;  //key存储查找的字符（边），value该边所对应的节点

        public Node() {
            nexts = new HashMap<>();
        }
    }

    static class Tree {
        public Node root;
        public Tree() {
            root = new Node();
        }

        /**
         * 插入一个字符串
         * @param value
         */
        public void insert(String value) {
            //把字符串拆分成char数组，按照前缀树的规则，生成
            char[] values = value.toCharArray();
            Node node = root;
            //挨个遍历char数组，验证并插入
            for (int i = 0; i < values.length; i++) {
                if (!node.nexts.containsKey(values[i])){
                    node.nexts.put(values[i], new Node());
                }
                //node等于他的下一个节点
                node = node.nexts.get(values[i]);
                node.pass++;
            }
            //遍历完成node节点来到最后一个
            node.end++;
        }

        /**
         * 查找一个字符串
         * @param value
         * @return
         */
        public int search(String value) {
            //把字符串拆分成char数组，按照前缀树的规则，生成
            char[] values = value.toCharArray();
            Node node = root;
            //挨个遍历char数组，验证如果右一个不存在前缀树中就停止
            for (int i = 0; i < values.length; i++) {
                if (!node.nexts.containsKey(values[i])) {
                    return 0;
                }
                node = node.nexts.get(values[i]);
            }
            return node.end;
        }

        /**
         * 删除一个字符串
         * @param value
         */
        public void delete(String value) {
           if (search(value) != 0) {
               //把字符串拆分成char数组，按照前缀树的规则，生成
               char[] values = value.toCharArray();
               Node node = root;
               //挨个遍历char数组，验证如果有一个节点的pass为
               for (int i = 0; i < values.length; i++) {
                    if (node.nexts.get(values[i]).pass - 1 == 0) {
                        node = null;
                        return;
                    }
                    node = node.nexts.get(values[i]);
                    node.pass--;
               }
               node.end--;
           }
        }


        /**
         * 查找以value开头的所有字符串
         * @param value
         * @return
         */
        public int preSearch(String value) {
            //把字符串拆分成char数组，按照前缀树的规则，生成
            char[] values = value.toCharArray();
            Node node = root;
            //挨个遍历char数组，验证如果右一个不存在前缀树中就停止
            for (int i = 0; i < values.length; i++) {
                if (!node.nexts.containsKey(values[i])) {
                    return 0;
                }
                node = node.nexts.get(values[i]);
            }
            return node.pass;
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert("陈年杰");
        tree.insert("陈年杰");
        tree.insert("陈年杰");
        tree.insert("王玲");
        tree.insert("陈独秀");
        tree.insert("陈浩然");
        System.out.println(tree.search("陈年杰"));
        System.out.println(tree.preSearch("陈"));
    }
}
