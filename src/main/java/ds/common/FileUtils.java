package ds.common;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    /**
     * 删除文件
     * @param filePath
     */
    public void delete(String filePath) {
        Path path = Paths.get(filePath);
        try {
            Files.delete(path);
            System.out.println("删除成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件
     * @param srcPath
     * @param targetPath
     */
    public void copy(String srcPath, String targetPath) {
        Path path1 = Paths.get(srcPath);
        Path path2 = Paths.get(targetPath);
        try {
            Files.copy(path1, path2);
            System.out.println(srcPath + "已复制到" + targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 移动文件
     * @param srcPath
     * @param targetPath
     */
    public void move(String srcPath, String targetPath) {
        Path path1 = Paths.get(srcPath);
        Path path2 = Paths.get(targetPath);
        try {
            Files.move(path1, path2);
            System.out.println(srcPath + "已移动到" + targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils();
//        fileUtils.dealPermIds("C:\\Users\\U6079438\\Desktop\\New Text Document.txt", "C:\\Users\\U6079438\\Desktop\\New Text Document1.txt");
       // fileUtils.delete("C:\\\\Users\\\\U6079438\\\\Desktop\\\\New Text Document1.txt");

        //fileUtils.copy("C:\\\\Users\\\\U6079438\\\\Desktop\\\\New Text Document.txt","C:\\\\Users\\\\U6079438\\\\Desktop\\\\New Text Document1.txt");

        fileUtils.move("C:\\\\Users\\\\U6079438\\\\Desktop\\\\New Text Document1.txt", "C:\\\\Users\\\\U6079438");
    }
}
