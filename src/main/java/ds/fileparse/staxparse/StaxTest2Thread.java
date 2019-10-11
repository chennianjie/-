package ds.fileparse.staxparse;


import ds.common.ProcessBatchQueues;
import ds.fileparse.IncrementalStg;
import ds.fileparse.ParseXML;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 10/11/2019
 */
public class StaxTest2Thread implements Runnable{

    private String type;
    private String subtype;
    private String rcssubtype;
    private String event;
    private String pi;

    private File file;
    private String uuid;

    public StaxTest2Thread() {
    }

    public StaxTest2Thread(File file, String uuid) {
        this.file = file;
        this.uuid = uuid;
    }

    //这里的3个对象是具体的POJO
    IncrementalStg incrementalStg = null;


    //基于事件流的方式来做的，通过使用流的ＡＰＩ，像指针一样的来处理文档，每一个节点都可以返回一个事件。处理完以后由JVM来回收内存。
    @Override
    public void run() {
        Long time = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        Reader fileReader = null;
        XMLStreamReader reader = null;
        try {
            fileReader = new FileReader(file);
            factory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);
            reader = factory.createXMLStreamReader(fileReader);
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
                            ProcessBatchQueues.IncrementalQueue2.add(incrementalStg);
                        }
                        break;
                    //文档的结束。
                    case XMLStreamConstants.END_DOCUMENT:
                        System.out.println("-----------end Document--------");
                        time = System.currentTimeMillis() - time;
                        System.out.println("耗时: " + time + "毫秒");
                        break;
                }

                try {
                    if (!reader.hasNext()) {
                        break;
                    }
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
                try {
                    eventReader = reader.next();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
                ProcessBatchQueues.IncrementalQueue2.add(ParseXML.getDUMMY());
                try {
                    reader.close();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
        }
    }

    private void printList(Queue<IncrementalStg> entityList) {
        for (IncrementalStg incrementalStg : entityList) {
            System.out.println(incrementalStg.toString());
        }
        System.out.println("一共解析property：" + entityList.size());
    }

}
