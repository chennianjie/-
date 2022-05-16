package javase.list;


import org.junit.Assert;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

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

    static void assertThrowTest(){
        // Expected java.lang.NullPointerException to be thrown, but nothing was thrown.
        assertThrows(NullPointerException.class, ()->{
            Person person = new Person();
            person.getName();
        });
    }

    //提取时分秒
    static void testPattern(){
        String time = "09:55:01";
        Pattern pattern = Pattern.compile("([0-2]?\\d)\\:([0-5]\\d)\\:([0-5]\\d)");
        System.out.println(pattern.matcher(time).matches());
        Matcher matcher = pattern.matcher(time);
//        Assert.assertEquals("09", matcher.group(1));
        matcher.find();
        String hour = matcher.group(1);
        System.out.println(hour);
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
