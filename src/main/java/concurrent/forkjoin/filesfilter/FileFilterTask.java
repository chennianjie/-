package concurrent.forkjoin.filesfilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @Description: 过滤遍历特定文件
 * RecursiveAction  forkjoin中执行行为
 * @Author: nianjie.chen
 * @Date: 8/30/2019
 */
public class FileFilterTask extends RecursiveAction {

    //搜寻盘符路径
    private File file;

    public FileFilterTask(File file) {
        this.file = file;
    }

    public static void main(String[] args) {
        FileFilterTask task = new FileFilterTask(new File("C:/"));
        ForkJoinPool pool = new ForkJoinPool();
        //异步执行
        pool.execute(task);
        System.out.println("主线程正在执行。。。");
        task.join();//主线程等待其完成
        System.out.println("主线程结束");
    }

    @Override
    protected void compute() {
        List<FileFilterTask> lists = new ArrayList<>();
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                //判断该文件是目录还是文件
                if (file.isDirectory()) {
                    lists.add(new FileFilterTask(file));
                } else {
                    String wholeName = file.getAbsolutePath();
                    if (wholeName!=null && wholeName.endsWith(".txt")) {
                        System.out.println(wholeName);
                    }
                }

                //如果lists集合中含有子任务，就要把其放入forkjoin框架中
                if (lists.size() > 0) {
                    for (FileFilterTask fileFilter:invokeAll(lists)) {
                        fileFilter.join();//子任务等待
                    }
                }
            }
        }
    }
}
