package util.xml;

import java.io.*;

/**
 * @Description:
 * 解析文本字符串并解析拼接
 * @Author: nianjie.chen
 * @Date: 2/26/2021
 */
public class XMLToString {

    public static String xmlFileToString(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null && !"".equals(line)){
            sb.append(line);
            line = bufferedReader.readLine();
        }
        return sb.toString();
    }

    public static void buildSript(String xmlString, String xmlType){
        int length = xmlString.length();
        int start = Integer.MAX_VALUE;
        int end;
        String xmlItem;
        for (int i = 0; i < length; i++){
            if (xmlString.charAt(i) == '<'){
                start = i;
            }
            if (xmlString.charAt(i) == '>'){
                end = i;
                xmlItem = xmlString.substring(start, end+1);
                System.out.println("DBMS_LOB.append("+xmlType+",'"+xmlItem+"');");
            }
        }
    }


    public static void main(String[] args) {
buildTableUpdateSQL();
//buildDiffXmlString();

    }


    public static void buildTableUpdateSQL(){
        try {
            String newXmlString = xmlFileToString("C:\\Users\\U6079438\\Desktop\\iqm asc\\XTAFOptionsDataItems.xsd");
            buildSript(newXmlString, "v_clob");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void buildDiffXmlString(){
        System.out.println("Set serveroutput on\n" +
                "DECLARE\n" +
                "v_clob_old clob;\n" +
                "v_clob_new clob;\n" +
                "v_diff_result clob;\n" +
                "begin\n" +
                "dbms_lob.createtemporary(v_clob_old,true);\n");
        try {
            String oldXmlString = xmlFileToString("C:\\Users\\U6079438\\Desktop\\iqm asc\\XTAFFuturesDataItems_Old.xsd");
            String newXmlString = xmlFileToString("C:\\Users\\U6079438\\Desktop\\iqm asc\\XTAFFuturesDataItems.xsd");
            buildSript(oldXmlString, "v_clob_old");
            System.out.println("dbms_lob.createtemporary(v_clob_new,true);");
            buildSript(newXmlString, "v_clob_new");
            System.out.println("select xmldiff(xmltype(v_clob_old),xmltype(v_clob_new)).getClobVal() into v_diff_result from dual;\n" +
                    "DBMS_OUTPUT.PUT_LINE( v_diff_result);\n" +
                    "end;\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
