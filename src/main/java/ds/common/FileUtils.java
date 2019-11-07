package ds.common;

import java.io.*;
import java.util.HashSet;
import java.util.List;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 10/7/2019
 */
public class FileUtils {

    public static void showFileName(List<File> files) {
        System.out.println("=============files============");
        for (File file : files) {
            System.out.println(file.getName());
        }
        System.out.println("===========files end==========");
    }


    public void dealPermIds(String inFilePath,String outFilePath) {
        int count = 0;
        HashSet<String> set = new HashSet<>();
        File file = new File(inFilePath);
        PrintWriter pw = null;
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            pw = new PrintWriter(new FileWriter(outFilePath));
            String s = bufferedReader.readLine();
            while (s != null) {
                count++;
                set.add(s);
                s = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
        for (String str : set) {
//            System.out.println("'" + str + "',");
            pw.println(str + ",");
        }
        pw.close();
        System.out.println("一共的个数：" + count);
        System.out.println("去重一共的个数：" + set.size());

    }

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils();
        fileUtils.dealPermIds("C:\\Users\\U6079438\\Desktop\\New Text Document.txt", "C:\\Users\\U6079438\\Desktop\\New Text Document1.txt");
    }
}
