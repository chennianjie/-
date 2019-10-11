package ds.fileparse.staxparse;


import ds.common.Entity;
import ds.common.Property;
import ds.fileparse2.EntityXml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 10/11/2019
 */
public class StaxTest {

    //这里的3个对象是具体的POJO
    List<Entity> entityList = new ArrayList<>();
    Entity item = null;
    Property property = null;
    List<Property> propertyList = new ArrayList<>();//用来存放POJO的集合


    //基于事件流的方式来做的，通过使用流的ＡＰＩ，像指针一样的来处理文档，每一个节点都可以返回一个事件。处理完以后由JVM来回收内存。
    public static void main(String[] args) throws IOException,
            XMLStreamException {

        StaxTest test = new StaxTest();
        File file = new File("C:\\Users\\U6079438\\Desktop\\PDP\\RDC2017042690819617.DAT");
        test.read(file);
    }

    public void read(File file) throws IOException, XMLStreamException {
        Long time = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        Reader fileReader = new FileReader(file);
        factory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);
        XMLStreamReader reader = factory.createXMLStreamReader(fileReader);
        try {
            int event = reader.getEventType();//获取节点类型,结果是以整形的方式返回的。

            while (true) {
                switch (event) {
                    case XMLStreamConstants.START_DOCUMENT://表示的是文档的开通节点。
                        time = System.currentTimeMillis();
                        break;
                    case XMLStreamConstants.START_ELEMENT://开始解析开始节点
                        switch (reader.getLocalName()) {
                            case "entity": //判断节点的名字
                                item = new Entity();//实例化一个节点ＰＯＪＯ类
                                //给节点赋值
                                item.setType(reader.getAttributeValue(0));//getAttributeValue(index)获取属性的值，可能有多个属性。

                                item.setSubtype(reader.getAttributeValue(1));
                                item.setRcssubtype(reader.getAttributeValue(2));
                                item.setEvent(reader.getAttributeValue(3));
                                propertyList.clear();
                                break;
                            case "PI":
                                item.setPi(reader.getElementText());
                                break;
                            case "property":
                                property = new Property();
                                property.setId(reader.getAttributeValue(0));
                                break;
                            case "currValue":
                                property.setCurrValue(reader.getElementText());
                                break;
                            case "validFrom":
                                property.setCurrValue(reader.getElementText());
                                break;
                            case "validTo":
                                property.setCurrValue(reader.getElementText());
                                break;
                            case "validFromWithTime":
                                property.setCurrValue(reader.getElementText());
                                break;
                            case "validToWithTime":
                                property.setCurrValue(reader.getElementText());
                                break;
                            case "language":
                                property.setCurrValue(reader.getElementText());
                                break;
                        }

                        break;

                    case XMLStreamConstants.CHARACTERS:
                        // if(reader.isWhiteSpace())
                        // break;
                        break;
                    //文档的结束元素
                    case XMLStreamConstants.END_ELEMENT:
                        if (reader.getLocalName().equals("entity")) {
                            item.setPropertyList(propertyList);
                            entityList.add(item);
                        }
                        if (reader.getLocalName().equals("property")) {
                            propertyList.add(property);
                            property = null;
                        }
                        break;
                    //文档的结束。
                    case XMLStreamConstants.END_DOCUMENT:
                        System.out.println("-----------end Document--------");
                        time = System.currentTimeMillis() - time;
                        System.out.println("耗时: " + time + "毫秒");
                        break;
                }

                if (!reader.hasNext()) {
                    printList(entityList);
                    break;
                }
                event = reader.next();
            }
        } finally {
            reader.close();
        }
    }

    private void printList(List<Entity> entityList) {
        int num = 0;
        for (Entity entity : entityList) {
            System.out.println(entity.getPi());
//            System.out.println("propertySize:" + entity.getPropertyList().size());
            num += entity.getPropertyList().size();
        }
        System.out.println("一共解析entity：" + entityList.size());
        System.out.println("一共解析property：" + num);

    }

}
