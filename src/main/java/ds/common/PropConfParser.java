package ds.common;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Copyright 2017 ThomsonReuters all right reserved
 *
 * @author Tiger Shi
 *
 * 
 */
public class PropConfParser {

public static  Map<String, PropConf> getConfs(InputStream inputStream){
	SAXReader reader = new SAXReader();
	Document doc = null;
	// InputStream is = null;
	try {
		// is = new FileInputStream(path);
		doc = reader.read(inputStream);
	} catch (DocumentException e) {
		e.printStackTrace();
		// LogInstance.outLogger(LogLevel.ERROR, "invalid configration
		// file");
		throw new RuntimeException("invalid configration file");
	}
	String xpath = "//PropConf";
	@SuppressWarnings("unchecked")
	List<Element> beanList = doc.selectNodes(xpath);
	if (beanList != null) {
		Map<String, PropConf> map = new HashMap<String, PropConf>();
		for (Element e : beanList) {
			PropConf bean = new PropConf();
			String name = e.element("name").getTextTrim();
			String value = e.elementTextTrim("value");
			String desc = e.elementTextTrim("desc");
			bean.setName(name);
			bean.setValue(value);
			bean.setDesc(desc);
			map.put(bean.getName(), bean);
			}
			
		
			return map;
			
		
	}else{
           return null;
	}
}

}
