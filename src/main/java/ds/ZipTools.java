package ds;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 9/17/2019
 */
public class ZipTools {

    /**
     * 解压.gz文件
     * @param sourcedir
     */
    public static void unGzipFile(String sourcedir) {
        String ouputfile = "";
        try {
            //建立gzip压缩文件输入流
            FileInputStream fin = new FileInputStream(sourcedir);
            //建立gzip解压工作流
            GZIPInputStream gzin = new GZIPInputStream(fin);
            //建立解压文件输出流
            ouputfile = sourcedir.substring(0,sourcedir.lastIndexOf('.'));
            ouputfile = ouputfile.replace(".DAT",".XML");
            FileOutputStream fout = new FileOutputStream(ouputfile);

            int num;
            byte[] buf=new byte[1024];

            while ((num = gzin.read(buf,0,buf.length)) != -1)
            {
                fout.write(buf,0,num);
            }
            if (fin != null) {
                fin.close();
            }
            if (gzin != null) {
                gzin.close();
            }
            if (fout != null) {
                fout.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public static void main(String[] args) {
        unGzipFile("C:\\Users\\U6079438\\Desktop\\work_doc\\ds and pdp file\\IQM_TradingVenuesESMA141970_I_R_Add.DAT.gz");
    }
}
