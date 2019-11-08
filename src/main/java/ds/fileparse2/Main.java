package ds.fileparse2;



/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 10/7/2019
 */
public class Main {
    public static void main(String[] args) {
        SDIFileProcessor processor = new SDIFileProcessor();
        Long start = System.currentTimeMillis();
        processor.process();
        Long end = System.currentTimeMillis();
        System.out.println("一共花费时间(ms)：" + (end - start));
    }
}
