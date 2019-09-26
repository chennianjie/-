package ds.xmlparse;

import ds.common.OracleConnection;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * @Description:
 * 使用SAX解析XML文件并封装到JavaBean对象中
 *
 * @Author: nianjie.chen
 * @Date: 9/23/2019
 */
public class Demo2 {

    private final static IncrementalStg DUMMY = new IncrementalStg();

    public static IncrementalStg getDUMMY() {
        return DUMMY;
    }

    private static List<File> getLocalAbsFiles(String localPath) {
        File localfile = new File(localPath);
        File[] iFiles = localfile.listFiles();
        if (iFiles != null) {
            List<File> filelist = Arrays.asList(iFiles);
            Collections.sort(filelist, (o1, o2) -> {
                if (o1.isDirectory() && o2.isFile())
                    return -1;
                if (o1.isFile() && o2.isDirectory())
                    return 1;
                return o1.getName().compareTo(o2.getName());
            });
            return filelist;
        } else {
            return null;
        }

    }

    public static List<Integer> getPropertyIds(){
        Connection connection = OracleConnection.getConnection();
        List<Integer> propertyIdList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT   value    FROM iqm_core.domain_values      WHERE     domain_id =        (SELECT id FROM iqm_core.domains WHERE name = 'RDC_REFERENCE'        )");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int propertyID = Integer.parseInt(rs.getString(1));
                propertyIdList.add(propertyID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return propertyIdList;
    }


    public static void excute(File file) throws ParserConfigurationException, SAXException, IOException{
        Long start = System.currentTimeMillis();
        // 创建解析器工厂、获取解析器
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        // 创建xml读取器，绑定事件处理器
        XMLReader reader = parser.getXMLReader();
        String uuid = UUID.randomUUID().toString();
        GetStuInfoHandler2 stuHandler = new GetStuInfoHandler2(file.getName(), uuid);
        reader.setContentHandler(stuHandler);
        reader.parse(file.getAbsolutePath());
        // reader.parse("C:\\Users\\U6079438\\Desktop\\RDC2017042690819617.DAT");
        BlockingQueue<IncrementalStg> incQueue = stuHandler.GetStuList();
        Long end = System.currentTimeMillis();
        //启动多个线程读取queue
        int TASK_THREAD = 10;
        for (int i = 0; i < 10; i++) {
            new Thread(new ImportTask(incQueue, file.getName(), uuid, OracleConnection.getConnection()));
        }

        System.out.println("花费的时间是："+ (end - start));
        System.out.println(incQueue.size());
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        List<File> localAbsFiles = getLocalAbsFiles("C:\\Users\\U6079438\\Desktop\\");
        for (File file : localAbsFiles) {
            //设置file_batch_uuid
            System.out.println(file.getName());
            excute(file);

        }
    }
}




/**
 * GetStuInfoHandler2
 * XML文件解析类
 */
class GetStuInfoHandler2 extends DefaultHandler {
    static PrintWriter pw;
    private List<Integer> propertyIds = new ArrayList<>();
    private String fileName;
    private String uuid;

    private String type;
    private String subtype;
    private String rcssubtype;
    private String event;
    private String pi;
    private final String ENTITY = "entity", PI = "PI",  PROPERTY = "property", CURRVALUE = "currValue",
            VALIDFROM = "validFrom", VALIDFROMWITHTIME = "validFromWithTime", VALIDTO = "validTo", LANGUAGE = "language",
            VALIDTOWITHTIME = "validToWithTime";
    IncrementalStg inc;
    private static String flag;
    static BlockingQueue<IncrementalStg> incQueue = new LinkedBlockingQueue<>();

    {
        try {
            pw = new PrintWriter(new FileWriter("C:\\Users\\U6079438\\Desktop\\Demo2.txt"));
//            propertyIds = Demo2.getPropertyIds();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //构造方法
    public GetStuInfoHandler2() {
    }

    public GetStuInfoHandler2(String fileName, String uuid) {
        this.fileName = fileName;
        this.uuid = uuid;
    }

    public BlockingQueue GetStuList() {
        return incQueue;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 每次解析到一个标签都会触发该方法
        switch (qName) {
            case ENTITY:
                // 获取entity标签内的属性值
                if (attributes != null) {
                    type = attributes.getValue("type");
                    subtype = attributes.getValue("subtype");
                    rcssubtype = attributes.getValue("rcssubtype");
                    event = attributes.getValue("event");
                }
                break;
            case PI:
                flag = PI;
                break;
            case PROPERTY:
                inc = new IncrementalStg();
                inc.setProperty_id(Long.valueOf(attributes.getValue("id")));
                inc.setEntity_type(type);
                inc.setEntity_sub_type(subtype);
                inc.setEntity_rcs_sub_type(rcssubtype);
                inc.setEntity_event(event);
                inc.setNda_pi(Long.valueOf(pi));
                break;
            case CURRVALUE:
                flag = CURRVALUE;
                break;
            case VALIDFROM:
                flag = VALIDFROM;
                break;
            case VALIDFROMWITHTIME:
                flag = VALIDFROMWITHTIME;
                break;
            case VALIDTOWITHTIME:
                flag = VALIDTOWITHTIME;
                break;
            case VALIDTO:
                flag = VALIDTO;
                break;
            case LANGUAGE:
                flag = LANGUAGE;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(flag==null) return;
        switch (flag) {
            case VALIDFROMWITHTIME:
                inc.setValid_from_inc_time(new String(ch, start, length));
                break;
            case VALIDFROM:
                inc.setValid_from(new String(ch, start, length));
                break;
            case CURRVALUE:
                inc.setCurrent_value(new String(ch, start, length));
                break;
            case PI:
                pi = new String(ch, start, length);
                break;
            case VALIDTOWITHTIME:
                inc.setValid_to_inc_time(new String(ch, start, length));
                break;
            case VALIDTO:
                inc.setValid_to(new String(ch, start, length));
                break;
            case LANGUAGE:
                inc.setLanguage(new String(ch, start, length));
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals(PROPERTY)){
            //添加进队列之前，对其property id进行校验
            if (propertyIds.contains(inc.getProperty_id())) {
                inc.setReference_flag("Y");
            }else {
                inc.setReference_flag("N");
            }
            incQueue.add(inc);//使用阻塞队列
//          incQueue.remove(inc);
            //pw.println(inc.toString());
        }
        flag = null;
    }
}
