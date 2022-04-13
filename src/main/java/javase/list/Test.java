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

    /**
     * ArrayList and LinkedList both could add null value
     */
    static void testLinkedList(){
        LinkedList<String> test1 = new LinkedList<>();
        test1.add("111");
        test1.add(null);
        test1.add("222");
        System.out.println("index 1 value is " + test1.get(1));
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

    /**
     * hashmap 的 key值也可以是null
     */
    static void testHashMap(){
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("chen", "nianjie");
        stringStringHashMap.put("po", "ckey");
        stringStringHashMap.put(null, "null string");
        stringStringHashMap.put("老", "ban");
        System.out.println("key is null and value is " + stringStringHashMap.get(null));
    }
}
