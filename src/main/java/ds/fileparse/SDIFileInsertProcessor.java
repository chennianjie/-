package ds.fileparse;

import common.SleepTools;
import ds.common.*;
import ds.exception.DBFeedException;
import ds.exception.FeedException;
import ds.fileparse.staxparse.StaxTest2Thread;
import org.apache.hadoop.fs.FileUtil;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 9/30/2019
 */
public class SDIFileInsertProcessor implements IFeedFileProcessor {


    private FeedInfo feedInfo;
    private Connection DBConnection;
    public static AtomicInteger batch_index = null;
    public static CountDownLatch endControl = new CountDownLatch(Integer.parseInt(PropertyUtil.getPropValue(PropsStr.InsertThreadNum)));

    @Override
    public void process() throws FeedException {
        this.init();
        List<File> files = getLocalAbsFiles(PropertyUtil.getPropValue(PropsStr.WorkPath));
        String fileType = PropertyUtil.getPropValue(PropsStr.FileType);
        FileUtils.showFileName(files);
        int insertThreadNum;
        String uuid;
        for (File insertFile : files) {
            String fileName = insertFile.getName();
            if (fileType != null && fileType.equals(getFileType(fileName))) {
                try {
                    uuid = UUID.randomUUID().toString();
                    System.out.println("当前解析文件{}" + fileName+"    uuid{}" + uuid);
                    this.DBConnection = OracleConnection.getConnection();
                    insertFileStatus(this.DBConnection, uuid,
                            insertFile.getName(), "StartPDP");
                    this.DBConnection.close();
//                  Thread parseXmlThread = new Thread(new ParseXML(insertFile, uuid));

                    Thread parseXmlThread = new Thread(new StaxTest2Thread(insertFile, uuid));

                    parseXmlThread.setPriority(8);
                    parseXmlThread.start();
                    //start thread deal data in queue
                    insertThreadNum = Integer.parseInt(PropertyUtil.getPropValue(PropsStr.InsertThreadNum));
                    System.out.println("insert DB thread number{}" + insertThreadNum);
                    while (ProcessBatchQueues.IncrementalQueue2.size() == 0) {
                        SleepTools.ms(5000);
                    }
                    for (int i =0; i <insertThreadNum ; i++) {
                        new Thread(new IncrementalsInsertTask(fileName, uuid, OracleConnection.getConnection(), OracleConnection.getConnection())).start();
                    }

                    //wait util this file analysis is complete then change status
                    endControl.await();
                    this.DBConnection = OracleConnection.getConnection();
                    System.out.println("解析的property标签数量：" + ProcessBatchQueues.parseNum);
                    System.out.println("插入数据库的数据条数：" + ProcessBatchQueues.insertNum);
                    if (ProcessBatchQueues.IncrementalQueue2.size() == 1 && ProcessBatchQueues.IncrementalQueue2.take() == ParseXML.getDUMMY()){
                        System.out.println("===========文件解析插库成功==============");
                    }
                    moveFile(insertFile,
                            PropertyUtil
                                    .getPropValue(PropsStr.ArchievePath),
                            uuid);
                    insertFileStatus(this.DBConnection, uuid,
                            insertFile.getName(), "EndPDP");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        DBConnection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void setFeedInfo(FeedInfo feedInfo) {
        this.feedInfo = feedInfo;
    }

    @Override
    public FeedInfo getFeedInfo() {
        // TODO Auto-generated method stub
        return this.feedInfo;
    }

    @Override
    public int getExecutionOrder() {
        // TODO Auto-generated method stub
        return 0;
    }

    private void init() {
        ProcessBatchQueues.insertNum = new AtomicInteger(0);
        ProcessBatchQueues.parseNum = new AtomicInteger(0);
        batch_index = new AtomicInteger(1);
    }

    private int getInsertThreadNumberB(File file) {


        return 0;
    }

    private static boolean moveFile(File srcFile, String destPath, String uuid) {

        // Destination directory
        File dir = new File(destPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Move file to new directory
        boolean success = srcFile.renameTo(new File(dir, srcFile.getName()
                + "__" + uuid));

        return success;
    }

    private void insertFileStatus(Connection dbConnection2, String uuid_file,
                                  String name, String string) {
        CallableStatement cs = null;
        try {
            cs = dbConnection2.prepareCall("call RDC_FILE_status_PRC(?,?,?)");

            cs.setString(1, uuid_file);

            cs.setString(2, name);
            cs.setString(3, string);

            cs.execute();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new DBFeedException(e.getMessage());
        } finally {
            try {
                if (cs != null && !cs.isClosed())
                    cs.close();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                throw new DBFeedException(e.getLocalizedMessage());
            }
        }
    }

    public static String getFileType(String fileName) {
        String[] strArray = fileName.split("\\.");
        int suffixIndex = strArray.length - 1;
        return strArray[suffixIndex];
    }



    private static List<File> getLocalAbsFiles(String localPath) {
        File localfile = new File(localPath);
        File[] iFiles = localfile.listFiles();
        if (iFiles != null) {
            List<File> filelist = Arrays.asList(iFiles);
            Collections.sort(filelist, (o1, o2) -> {
                if (o1.isDirectory() && o2.isFile()) {
                    return -1;
                }
                if (o1.isFile() && o2.isDirectory()) {
                    return 1;
                }
                return o1.getName().compareTo(o2.getName());
            });
            return filelist;
        } else {
            return null;
        }

    }

}
