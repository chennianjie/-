package javase.list;

import java.util.ArrayList;
import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        HashMap<Domain, String> domainStringHashMap = new HashMap<>();
        Domain chennianjie = new Domain("chennianjie", 22);
        Domain chennianjie1 = new Domain("chennianjie", 22);
        domainStringHashMap.put(chennianjie, "1");
        domainStringHashMap.put(chennianjie1, "2");
        System.out.println(domainStringHashMap.size());



    }

    void listToArray(){
        //集合转数组
        ArrayList<String> list = new ArrayList<>();
        list.add("chennianjie");
        list.add("wangling");
        String[] strings = list.toArray(new String[0]);
        System.out.println(strings[0]+strings[1]);
        System.out.println(strings.length);
    }
}
