package com.zhou.blog.test;

import java.util.Date;

@SuppressWarnings("ALL")
public class UtilsTest {
	@SuppressWarnings({"unchecked", "deprecation"})
	public static void main(String[] args) {
		Object object=new Date();
		String result="";
		Date date=(Date)object;
		//noinspection deprecation,deprecation
		result=result+date.getYear()+"-"+date.getMonth()+"-"+date.getDay()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		System.err.println(result);
		
	}
}
