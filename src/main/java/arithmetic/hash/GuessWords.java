package arithmetic.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @Description:
 *
 * 猜字谜
 * 输入：
 * words = ["aaaa","asas","able","ability","actt","actor","access"],
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * 输出：[1,1,3,2,4,0]
 *
 * 规则：1.单词word中包含puzzle首字母  2.单词word中的每一个字母都可以在puzzle中找到
 * 满足以上两个条件，word就是puzzle的一个谜底
 * @Author: nianjie.chen
 * @Date: 1/16/2020
 */
public class GuessWords {


    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        if (puzzles == null || puzzles.length == 0 || words == null || words.length == 0){
            return new ArrayList<>();
        }
        int[] result = new int[puzzles.length];
        HashSet wordsSet = new HashSet();
        HashSet puzzlesSet = new HashSet();
        for (int i = 0; i < puzzles.length; i++) {
            word:for (int j = 0; j < words.length; j++) {
                wordsSet.clear();
                puzzlesSet.clear();
                for (int k = 0; k < words[j].length(); k++){
                    wordsSet.add(words[j].charAt(k));
                }
                for (int k = 0; k < puzzles[i].length(); k++){
                    puzzlesSet.add(puzzles[i].charAt(k));
                }
                //检查是否满足条件一
                if (!wordsSet.contains(puzzles[i].charAt(0))){
                    continue word;
                }else {
                    //检查是否满足条件二
                    int distinct = puzzlesSet.size() - wordsSet.size();
                    puzzlesSet.removeAll(wordsSet);
                    if (puzzlesSet.size() == distinct) {
                        result[i]++;
                    }
                }
            }
        }
        ArrayList list = new ArrayList();
        for (int i : result) {
            list.add(i);
        }
        return list;
    }


    public static void main(String[] args) {
        List<Integer> numOfValidWords = new GuessWords().findNumOfValidWords(new String[]{"aaaa", "asas", "able", "ability", "actt", "actor", "access"},
                new String[]{"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"});
        System.out.println(Arrays.toString(numOfValidWords.toArray()));
    }
}
