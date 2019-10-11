package ds.fileparse.staxparse;


import ds.fileparse.IncrementalStg;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 10/11/2019
 */
public class StaxTest2 {

    private String type;
    private String subtype;
    private String rcssubtype;
    private String event;
    private String pi;

    //这里的3个对象是具体的POJO
    Queue<IncrementalStg> incrementalStgList = new LinkedBlockingQueue<>();
    IncrementalStg incrementalStg = null;


    //基于事件流的方式来做的，通过使用流的ＡＰＩ，像指针一样的来处理文档，每一个节点都可以返回一个事件。处理完以后由JVM来回收内存。
    public static void main(String[] args) throws IOException,
            XMLStreamException {

        StaxTest2 test = new StaxTest2();
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
            int eventReader = reader.getEventType();//获取节点类型,结果是以整形的方式返回的。

            while (true) {
                switch (eventReader) {
                    case XMLStreamConstants.START_DOCUMENT://表示的是文档的开通节点。
                        time = System.currentTimeMillis();
                        break;
                    case XMLStreamConstants.START_ELEMENT://开始解析开始节点
                        switch (reader.getLocalName()) {
                            case "entity": //判断节点的名字
                                //给节点赋值
                                type = reader.getAttributeValue(0);//getAttributeValue(index)获取属性的值，可能有多个属性。

                                subtype = reader.getAttributeValue(1);
                                rcssubtype = reader.getAttributeValue(2);
                                event = reader.getAttributeValue(3);
                                break;
                            case "PI":
                                pi = reader.getElementText();
                                break;
                            case "property":
                                incrementalStg = new IncrementalStg();
                                incrementalStg.setEntity_type(type);
                                incrementalStg.setEntity_sub_type(subtype);
                                incrementalStg.setEntity_rcs_sub_type(rcssubtype);
                                incrementalStg.setEntity_event(event);
                                incrementalStg.setNda_pi(Long.valueOf(pi));
                                incrementalStg.setProperty_id(Long.valueOf(reader.getAttributeValue(0)));
                                break;
                            case "currValue":
                                incrementalStg.setCurrent_value(reader.getElementText());
                                break;
                            case "validFrom":
                                incrementalStg.setValid_from(reader.getElementText());
                                break;
                            case "validTo":
                                incrementalStg.setValid_to(reader.getElementText());
                                break;
                            case "validFromWithTime":
                                incrementalStg.setValid_from_inc_time(reader.getElementText());
                                break;
                            case "validToWithTime":
                                incrementalStg.setValid_to_inc_time(reader.getElementText());
                                break;
                            case "language":
                                incrementalStg.setLanguage(reader.getElementText());
                                break;
                        }
                        break;
                    //文档的结束元素
                    case XMLStreamConstants.END_ELEMENT:
                        if (reader.getLocalName().equals("property")) {
                            incrementalStgList.add(incrementalStg);
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
                    printList(incrementalStgList);
                    break;
                }
                eventReader = reader.next();
            }
        } finally {
            reader.close();
        }
    }

    private void printList(Queue<IncrementalStg> entityList) {
        for (IncrementalStg incrementalStg : entityList) {
            System.out.println(incrementalStg.toString());
        }
        System.out.println("一共解析property：" + entityList.size());
    }

}
