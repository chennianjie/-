package ds.common;

import java.io.File;
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
}
