package ds.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Copyright 2017 ThomsonReuters all right reserved
 *
 * @author Tiger Shi
 *
 * 
 */
public class PropertyUtil {
	private static Map<String, PropConf> confs =null;
	static{
		
		String filename= null;
		   try {
			    filename = System.getProperty( "com.oracle.st.xmldb.pm.ConnectionParameters", "PropConfig.xml" ) ;
				 confs= PropConfParser.getConfs(new FileInputStream("cfg/"+filename));
			  // confs= PropConfParser.getConfs(new FileInputStream("cfg/PropConfig.xml"));
		   			
		   } catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			try {
						confs= PropConfParser.getConfs(PropertyUtil.class.getClassLoader().getResourceAsStream(filename));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
				
			}
		}
		
		
		
	}

	/**
	 * get property_ids
	 * @return
	 */
	public static ArrayList<Integer> getPropertyIds(){
		String propertyIds = getPropValue("PropertyIds");
		String[] split = propertyIds.split(",");
		ArrayList<Integer> list = new ArrayList<>();
		for (String s : split) {
			list.add(Integer.parseInt(s));

		}
		return list;
	}

	public static void main(String[] args) {
		List<Integer> propertyIds = getPropertyIds();
		for (Integer a : propertyIds) {
			System.out.println(a);
		}
	}
	
	
public static  Map<String, PropConf> getProps(){
	return confs;
}


public static PropConf getProp(String key){
	return confs.get(key);
}
public static String getPropValue(String key){
	return confs.get(key).getValue().trim();
}
}
