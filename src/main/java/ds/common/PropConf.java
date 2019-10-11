package ds.common;

import java.io.Serializable;

/**
 * Copyright 2017 ThomsonReuters all right reserved
 *
 * @author Tiger Shi
 *
 * 
 */
public class PropConf implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5083487472133336721L;
	
	private String name;
	private String value;
	private String desc;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
