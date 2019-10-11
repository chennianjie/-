package ds.fileparse2;

import ds.common.PropertyUtil;
import ds.common.PropsStr;
import ds.fileparse.IncrementalStg;
import org.apache.log4j.Logger;
import org.dom4j.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DomXmlUtils {
	private static Logger logger = Logger.getLogger(DomXmlUtils.class);

	public static List<IncrementalStg> parserEntity(String fileName,
			String uuid, String entityXml) {
		Document document = null;
		try {
			document = DocumentHelper.parseText(entityXml);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			logger.error(entityXml);

			return null;
		}
		Element root = document.getRootElement();

		List<IncrementalStg> icstglist = listNodes(root, fileName, uuid);
		return icstglist;
	}

	private static List<IncrementalStg> getProperties(Element node) {

		@SuppressWarnings("unchecked")
		List<Element> eles = node.elements();

		int propSize = eles.size();

		if (propSize > 0) {
			List<IncrementalStg> inces = new ArrayList<IncrementalStg>(propSize);

			for (Element ele : eles) {

				IncrementalStg istg = new IncrementalStg();

				@SuppressWarnings("unchecked")
				List<Attribute> list = ele.attributes();
				for (Attribute attribute : list) {

					if (attribute.getName().equals("id")) {
						istg.setProperty_id(Long.parseLong(attribute.getValue()));
						if (SDIFileProcessor.propertyIdList.contains(Integer
								.parseInt(attribute.getValue()))) {
							istg.setReference_flag("Y");
						} else {

							istg.setReference_flag("N");
						}
					}

					@SuppressWarnings("unchecked")
					List<Element> propEles = ele.elements();

					for (Element propEle : propEles) {

						if (propEle.getName().equals("currValue")) {

							istg.setCurrent_value(propEle.getText());
							@SuppressWarnings("unchecked")
							List<Attribute> listCurrAttr = propEle.attributes();

							if (listCurrAttr.size() > 0) {
								for (Attribute atrrCode : listCurrAttr) {
									if (atrrCode.getName().equals("classifier")) {
										istg.setClassifier_type(atrrCode
												.getValue());
									}
								}

							}

						} else if (propEle.getName().equals("validFrom")) {
							istg.setValid_from(propEle.getText());

						} else if (propEle.getName()
								.equals("validFromWithTime")) {

							istg.setValid_from_inc_time(propEle.getText());

						} else if (propEle.getName().equals("validTo")) {

							istg.setValid_to(propEle.getText());

						} else if (propEle.getName().equals("language")) {

							istg.setLanguage(propEle.getText());

						}else if (propEle.getName().equals("validToWithTime")) {
							istg.setValid_to_inc_time(propEle.getText());

						}

					}

				}

				inces.add(istg);
			}

			return inces;
		} else {

			return null;
		}

	}

	private static Long getNdaPI(Element node) {
		@SuppressWarnings("unchecked")
		Iterator<Element> iterator = node.elementIterator();
		while (iterator.hasNext()) {
			Element e = iterator.next();
			if (e.getName().equals("PI")) {
				return Long.parseLong(e.getTextTrim());
			}
		}

		return null;
	}

	private static List<IncrementalStg> listNodes(Element node,
			String file_Name, String uuid) {

		@SuppressWarnings("unchecked")
		List<Attribute> list = node.attributes();

		String type = null;
		String subtype = null;
		String rcssubtype = null;
		String event = null;
		Long nda_pi = null;
		List<IncrementalStg> istglist = null;

		for (Attribute attribute : list) {
			String value = attribute.getValue();
			if (value != null && !value.equals("")) {
				if (attribute.getName().equals("type")) {
					type = value;
				} else if (attribute.getName().equals("subtype")) {
					subtype = value;
				} else if (attribute.getName().equals("rcssubtype")) {
					rcssubtype = value;
				} else if (attribute.getName().equals("event")) {
					event = value;
				}

			}

		}
		if (!(node.getTextTrim().equals(""))) {
		}

		@SuppressWarnings("unchecked")
		Iterator<Element> iterator = node.elementIterator();
		while (iterator.hasNext()) {
			Element e = iterator.next();

			if (e.getName().equals("header")) {
				nda_pi = getNdaPI(e);
			} else if (e.getName().equals("content")) {
				istglist = getProperties(e);
			}

		}

		for (IncrementalStg istg : istglist) {

			istg.setFileName(file_Name);
			istg.setBpm_batch_guid(uuid);
			istg.setNda_pi(nda_pi);
			istg.setEntity_type(type);
			istg.setEntity_sub_type(subtype);
			istg.setEntity_rcs_sub_type(rcssubtype);
			istg.setEntity_event(event);
			istg.setVersion(PropertyUtil.getPropValue(PropsStr.Version));
		}

		return istglist;

	}

	public static void main(String[] args) {
		String abc = "<entity type=\"Q\" subtype=\"ECS\" rcssubtype=\"COMSPOT\" event=\"I\"><header><PI>273012607</PI></header><content><property id=\"27369\"><currValue>7658C-NDU3E=OP</currValue><validFrom>20170920</validFrom><validFromWithTime>20170920181524</validFromWithTime></property>"
				+ "<property id=\"27369\"><currValue>DEUX3K7B.EW</currValue><language>aa</language><validFrom>20170830</validFrom><validFromWithTime>20170830222625</validFromWithTime><validTo>20170908</validTo><validToWithTime>20170908174658</validToWithTime></property>"
				+ "</content></entity>";

		List<IncrementalStg> istglist = parserEntity("abc", "abc", abc);

		for (IncrementalStg icst : istglist) {
			System.out.println("____________________________________");
			System.out.println("setNda_pi:" + icst.getNda_pi());
			System.out.println("setNda_pi:" + icst.getEntity_type());
			System.out.println("setNda_pi:" + icst.getEntity_sub_type());
			System.out.println("setNda_pi:" + icst.getEntity_rcs_sub_type());
			System.out.println("setNda_pi:" + icst.getEntity_event());
			System.out.println("propertyId:" + icst.getProperty_id());
			System.out.println("CurrentVal:" + icst.getCurrent_value());
			System.out.println("language:" + icst.getLanguage());
			System.out.println(icst.getClassifier_type());
			System.out.println(icst.getValid_from());
			System.out.println(icst.getValid_from_inc_time());
			System.out.println(icst.getValid_to());
			System.out.println(icst.getValid_to_inc_time());

		}

	}

}
