package util.xml;

import java.io.*;

/**
 * @Description:
 * 解析文本字符串并解析拼接
 * @Author: nianjie.chen
 * @Date: 2/26/2021
 */
public class XMLToString {

    public static String xmlFileToString(String filePath, String xmlType) throws IOException {
        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null && !"".equals(line)){
            sb.append(line);
            line = bufferedReader.readLine();
            System.out.println("DBMS_LOB.append("+xmlType+",'"+line+"');");
        }
        return sb.toString();
    }




    public static void main(String[] args) {
buildTableUpdateSQL();
//buildDiffXmlString();

    }


    public static void buildTableUpdateSQL(){
        try {
            String newXmlString = xmlFileToString("C:\\Users\\U6079438\\Desktop\\work_doc\\iqm asc\\XTAFFuturesDataItems_Old.xsd", "v_clob");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
