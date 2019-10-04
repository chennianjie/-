package ds.xmlparse;

import ds.Demo;
import oracle.jdbc.OracleCallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.JAVA_STRUCT;
import oracle.sql.StructDescriptor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @Description:调用存过向Icremental表插入数据
 * @Author: nianjie.chen
 * @Date: 9/25/2019
 */
public class ImportTask implements Runnable {

    private BlockingQueue<IncrementalStg> incQueue;
    private String fileName;
    private String uuid;

    private Connection connection;
    private OracleCallableStatement proc;
    public ImportTask() {
    }

    public ImportTask(BlockingQueue<IncrementalStg> incQueue, String fileName, String uuid, Connection connection) {
        this.incQueue = incQueue;
        this.fileName = fileName;
        this.uuid = uuid;
        this.connection = connection;
    }

    @Override
    public void run() {

        //从queue里面取出一批数据插入数据库
        boolean done = false;

        List<IncrementalStg> incList = new ArrayList<>();
        try {

            while (!done){
                IncrementalStg stg = incQueue.take();
                if (stg == Demo2.getDUMMY()) {
                    incQueue.put(stg);
                    done = true;
                }else {
                    incList.add(stg);
                }
                if (incList.size() == 100){//需要写在配置文件中
                    ///callInsertIncProcedure(incList);
                    System.out.println("threadName{"+Thread.currentThread().getName()+"}" + "batch_id{"+Demo2.batch_index.getAndIncrement()+"}"+"Size{"+incList.size()+"}");
                    Demo2.count.addAndGet(100);
                    incList.clear();
                }
            }
            if (!incList.isEmpty()) {
                //callInsertIncProcedure(incList);
                Demo2.countDownLatch.countDown();
                System.out.println("threadName{"+Thread.currentThread().getName()+"}" + "batch_id{"+Demo2.batch_index.getAndIncrement()+"}"+"Size{"+incList.size()+"}");
                Thread.sleep(20);
                Demo2.count.addAndGet(incList.size());
            }

        } catch (InterruptedException e) {
            Thread.interrupted();
            e.printStackTrace();
        } finally {
//            try {
//                if (proc != null) {
//                    proc.close();
//                }
//            } catch (SQLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            }catch (SQLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
        }
    }

    private  void callInsertIncProcedure(List<IncrementalStg> incList) throws SQLException {
        proc = (OracleCallableStatement) connection
                .prepareCall("{ call RDC_COLLECTED.RDC_INSERT_INC_PDP_PRC(?,?,?,?) }");

        proc.setString(1, fileName);
        proc.setString(2, uuid);
        int bat_index = Demo2.batch_index.getAndIncrement();
        proc.setInt(3, bat_index);

        //传入参数为istgList
        ARRAY resultArr = tran2Oracle(connection, incList,
                bat_index);
        proc.setARRAY(4, resultArr);

        proc.execute();
    }

    private static ARRAY tran2Oracle(Connection con,
                                     List<IncrementalStg> istgList,
                                     Integer batchIndex) throws SQLException {

        StructDescriptor structDesc = StructDescriptor.createDescriptor(
                "RDC_COLLECTED.RDC_INCR_TYPE", con);
        JAVA_STRUCT[] structs = new JAVA_STRUCT[istgList.size()];
        int listSeq = 0;

            for (int i = 0; i < istgList.size(); i++) {

                Object[] objeArry = new Object[19];
                objeArry[0] = istgList.get(i).getNda_pi();
                objeArry[1] = istgList.get(i).getVersion();
                objeArry[2] = istgList.get(i).getEntity_type();
                objeArry[3] = istgList.get(i).getEntity_sub_type();
                objeArry[4] = istgList.get(i).getEntity_rcs_sub_type();
                objeArry[5] = istgList.get(i).getEntity_event();
                objeArry[6] = istgList.get(i).getProperty_id();
                objeArry[7] = istgList.get(i).getCurrent_value();
                objeArry[8] = istgList.get(i).getClassifier_type();
                objeArry[9] = istgList.get(i).getValid_from();
                objeArry[10] = istgList.get(i).getValid_from_inc_time();
                objeArry[11] = istgList.get(i).getValid_to();
                objeArry[12] = istgList.get(i).getValid_to_inc_time();
                objeArry[13] = istgList.get(i).getLanguage();
                objeArry[14] = istgList.get(i).getBpm_batch_guid();
                objeArry[15] = batchIndex;
                objeArry[16] = istgList.get(i).getCreate_date();
                objeArry[17] = istgList.get(i).getCreate_by();
                objeArry[18] = istgList.get(i).getReference_flag();
                structs[listSeq++] = new JAVA_STRUCT(structDesc, con, objeArry);
            }

        ArrayDescriptor arryDesc = ArrayDescriptor.createDescriptor(
                "RDC_COLLECTED.RDC_INCR_COL_TYPE", con);
        ARRAY list = new ARRAY(arryDesc, con, structs);
        System.out.println(" list " + list.length());
        con.close();
        return list;
    }
}
