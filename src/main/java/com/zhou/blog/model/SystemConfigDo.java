package com.zhou.blog.model;

import java.io.Serializable;

public class SystemConfigDo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6725680816256144565L;
	
	private String  uuid;
	private String  name;
	private String  MyKey;
	private String  MyValue;
	private String  description;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMyKey() {
		return MyKey;
	}
	public void setMyKey(String MyKey) {
		this.MyKey = MyKey;
	}
	public String getMyMyValue() {
		return MyValue;
	}
	public void setMyValue(String MyValue) {
		this.MyValue = MyValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "SystemConfig [uuid=" + uuid + ", name=" + name + ", MyKey=" + MyKey + ", MyValue=" + MyValue + ", decription="
				+ description + "]";
	}
	public SystemConfigDo(String uuid, String name, String MyKey, String MyValue, String description) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.MyKey = MyKey;
		this.MyValue = MyValue;
		this.description = description;
	}
	
	public SystemConfigDo(){}
	

}
