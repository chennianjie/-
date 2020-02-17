package arithmetic.tree.sensitiveService;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 敏感词过滤
 * @Author: nianjie.chen
 * @Date: 2/12/2020
 */
public class SensitiveService{

    private NodeTemp root = new NodeTemp();

    /**
     * 添加铭感词
     * 生成类似前缀树结构
     * @param word
     */
    public void addWord(String word) {
        if (word == null || word == ""){
            return;
        }

        NodeTemp temp = root;
        for (int i = 0; i < word.length(); i++){
            Character c = word.charAt(i);
            //查找root节点下是否有该字符对应的节点
            NodeTemp subNode = temp.getSubNode(c);
            //如果为null，说明该字符第一次插入root节点
            if (subNode == null){
                subNode = new NodeTemp();
                temp.addSubNode(c, subNode);
            }
            temp = subNode;
            //判断是否是结尾词
            if (i == word.length() - 1){
                temp.setEnd(true);
            }
        }
    }

    public String filter(String word){

        if (StringUtils.isBlank(word)) {
            return word;
        }
        //上指针position
        int postion = 0;
        //下指针begin，begin跟着position走
        int begin = 0;
        //前缀树指针
        NodeTemp nodeIndex = root;


        //记录结果
        StringBuilder result = new StringBuilder();
        String replace = "***";

        //遍历当前word开始过滤
        while (postion < word.length()){
            Character c = word.charAt(postion);
            nodeIndex = nodeIndex.getSubNode(c);
            if (nodeIndex == null) {//当前字符并未存在前缀树中
                result.append(word.charAt(begin));//这里拼接的是begin下标指向的字符
                postion = begin + 1;
                begin = postion;
                nodeIndex = root;
            }else if (nodeIndex.isEnd()){//是铭感词的时候
                result.append(replace);
                postion = postion + 1;
                begin = postion;
                nodeIndex = root;
            }else {
                postion++;
            }
        }
        //最后position遍历完之后，把index之后的部分拼接在结果上
        result.append(word.substring(begin));
        return result.toString();
    }

    private class NodeTemp{
        private boolean end = false;//是否是铭感词结束词
        private Map<Character, NodeTemp> subNodes = new HashMap<Character, NodeTemp>();//当前值对应节点下的

        private void setEnd(boolean end){
            this.end = end;
        }
        private boolean isEnd(){
            return this.end;
        }
        private void addSubNode(Character key, NodeTemp subNode){
            this.subNodes.put(key, subNode);
        }
        private NodeTemp getSubNode(Character key){
            return this.subNodes.get(key);
        }
    }

    public static void main(String[] args) {
        SensitiveService sensitiveService = new SensitiveService();
        sensitiveService.addWord("色情");
        sensitiveService.addWord("暴力");
        sensitiveService.addWord("赌博");
        String filter = sensitiveService.filter("我看了一部关于赌博的暴力电影");
        System.out.println(filter);
    }
}
