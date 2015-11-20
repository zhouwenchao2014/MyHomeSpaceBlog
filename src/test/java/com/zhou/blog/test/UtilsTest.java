package com.zhou.blog.test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UtilsTest {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Object object=new Date();
		String result="";
		if(object instanceof Integer){
			int a=(Integer)object;
			result=a+"";
		}else if (object instanceof ArrayList) {
			List<String> list=(List<String>)object;
			list.add("a");
			list.add("b");
			for(String a : list){
				result=result+","+a;
			}
		}else if (object instanceof HashMap) {
			Map<String,String> map=(Map<String,String>)object;
			map.put("a", "aa");
			map.put("b", "bb");
			Set<String> keySet=map.keySet();
			for(String key : keySet){
				String value=map.get(key);
				result=result +key+"="+value+",";
			}
		}else if (object instanceof Date) {
			Date date=(Date)object;
			result=result+date.getYear()+"-"+date.getMonth()+"-"+date.getDay()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds(); 
		}else if (object instanceof Double) {
			Double d=(Double)object;
			result=result+d+"";
		}else if (object instanceof Array) {
			
		}else if (object instanceof Float) {
			
		}else if (object instanceof Long) {
			
		}else if (object instanceof Boolean) {
			
		}
		System.err.println(result);
		
	}
}
