package test;


public class Test {

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
    }
    public static void main(String[] args) {
        int i = Test.tableSizeFor(30);

        float v = 0.0f / 0.0f;
        //返回一个数的平方根
        Math.sqrt(-1);
        System.out.println(v);

        String test = ",, ,chennianjie,";
        System.out.println(Test.trim(test));
        System.out.println(Test.trim(test).trim());
        System.out.println(test);
        System.out.println((int) ',');
        System.out.println((int) ' ');
        System.out.println((int) 'a');
        System.out.println((int) 'A');



        //测试switch语句的String方式
        String param = null;
        switch (param){
            case "null":
                System.out.println("null");
                break;
            case "111":
                System.out.println("111");
                break;
            default:
                System.out.println("default");
        }
    }

    public static String trim(String value) {
        int len = value.length();
        int st = 0;
        char[] val = value.toCharArray();    /* avoid getfield opcode */

        while ((st < len) && (val[st] == ',')) {
            st++;
        }
        while ((st < len) && (val[len - 1] == ',')) {
            len--;
        }
        return ((st > 0) || (len < value.length())) ? value.substring(st, len) : value;
    }

}