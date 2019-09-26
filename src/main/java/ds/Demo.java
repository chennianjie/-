package ds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;


/**
 * @Description:

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 使用SAX解析XML文件并封装到JavaBean对象中
 *
 * @Author: nianjie.chen
 * @Date: 9/23/2019
 */
public class Demo {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // 创建解析器工厂、获取解析器
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        // 创建xml读取器，绑定事件处理器
        XMLReader reader = parser.getXMLReader();
        GetStuInfoHandler stuHandler = new GetStuInfoHandler();
        reader.setContentHandler(stuHandler);
        reader.parse("C:\\Users\\U6079438\\Desktop\\work_doc\\test\\New Text Document.xml");
        List<Student> stu_list = stuHandler.GetStuList();
        for(Student stu:stu_list){
            System.out.println(stu.toString());
        }
    }
}

class GetStuInfoHandler extends DefaultHandler {
    private final String STU = "stu", EXAM = "exam", EXAMID = "examid", IDCARD = "idcard", NAME = "name",
            LOCATION = "location", GRADE = "grade";
    Student stu;
    private static String flag;
    private List<Student> stu_list = new ArrayList<>();

    public List GetStuList() {
        return stu_list;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 每次解析到一个标签都会触发该方法
        if (qName.equals(STU)) {
            stu = new Student();
            // 获取stu标签内的属性值
            if (attributes != null) {
                stu.setExamid(attributes.getValue(EXAMID));
                stu.setIdcard(attributes.getValue(IDCARD));
            }
        } else if (qName.equals(NAME)) {
            flag = NAME;
        } else if (qName.equals(LOCATION)) {
            flag = LOCATION;
        } else if (qName.equals(GRADE)) {
            flag = GRADE;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(flag==null) return;
        switch (flag) {
            case NAME:
                stu.setName(new String(ch, start, length));
                break;
            case LOCATION:
                stu.setLocation(new String(ch, start, length));
                break;
            case GRADE:
                stu.setGrade(new String(ch, start, length));
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        flag = null;
    }
}
