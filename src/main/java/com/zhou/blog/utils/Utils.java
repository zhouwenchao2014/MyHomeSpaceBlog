package com.zhou.blog.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhou.blog.model.LoginHistory;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhou.blog.model.ArticleDo;
import com.zhou.blog.model.JsonResult;

public class Utils {
	
	private static Logger log=LoggerFactory.getLogger(Utils.class);
	
	public static String newUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String now(String format){
		java.util.Date date=new java.util.Date();
		if(StringUtils.isNoneBlank(format)){
			format="yyyy-mm-dd HH:mm:ss";
		}
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
		String now=simpleDateFormat.format(date);
		return now;
	}

	public static String randomNum(){
		Random random=new Random();
		String num=random.nextInt()*10000+"";
		return num;
	}
	public static String ObjectToString(Object object){
		String result="";
		if(object instanceof Integer){
			result=object+"";
		}else if (object instanceof ArrayList) {
			
		}else if (object instanceof HashMap) {
			
		}else if (object instanceof Date) {
			
		}else if (object instanceof String) {
			
		}else if (object instanceof Double) {
			
		}else if (object instanceof Array) {
			
		}else if (object instanceof Float) {
			
		}else if (object instanceof Long) {
			
		}else if (object instanceof Boolean) {
			
		}
		return result;
	}
	
	public static String generateJson(ArticleDo article) {
		String json = "";
		try {
			XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject();
			contentBuilder.field("uuid", article.getUuid() + "");
			contentBuilder.field("title", article.getTitle());
			contentBuilder.field("writeTime", article.getWriteTime() + "");
			contentBuilder.field("updateTime", article.getUpdateTime() + "");
			contentBuilder.field("author", article.getAuthor() + "");
			contentBuilder.field("content", article.getContent() + "");
			json = contentBuilder.endObject().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static String generateJsonByLoginHistory(LoginHistory loginHistory) {
		String json = "";
		try {
			XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject();
			contentBuilder.field("loginName", loginHistory.getLoginName()+ "");
			contentBuilder.field("ipAddress", loginHistory.getIpAddress());
			contentBuilder.field("loginTime", loginHistory.getLoginTime() + "");
			contentBuilder.field("type", loginHistory.getType() + "");
			json = contentBuilder.endObject().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static void printSuccess(Object object,String msg,HttpServletResponse response){
		JsonResult result=new JsonResult();
		result.setAttr(object);
		result.setCode(1);
		result.setSuccess(true);
		result.setMsg(msg);
		print(result,response);
	}
	public static void printFailure(Object object,String msg,HttpServletResponse response){
		JsonResult result=new JsonResult();
		result.setAttr(object);
		result.setCode(0);
		result.setSuccess(true);
		result.setMsg(msg);
		print(result,response);
	}
	
	public static void print(JsonResult result,HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect));
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
	
	public static String getIp(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    } 
	    return ip;
	}
	
	public static ArticleDo getArticleDo(HttpServletRequest request){
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String uuid=newUUID();
		String writeTime=System.currentTimeMillis()+"";
		String updateTime="";
		String author="zhou";
		ArticleDo articleDo=new ArticleDo(uuid, title, writeTime, updateTime, author, content,0,0);
		return articleDo;
	}
	
	public static List<ArticleDo> getListFromHits(SearchHits searchHits){
		List<ArticleDo> articleDos = new ArrayList<ArticleDo>();
		if (searchHits != null&&searchHits.totalHits()!=0) {
			SearchHit[] searchHit = searchHits.getHits();
			for (SearchHit hit : searchHit) {
				ArticleDo articleDo = new ArticleDo();
				String uuid = (String) hit.getSource().get("uuid");
				String author = (String) hit.getSource().get("author");
				String title = (String) hit.getSource().get("title");
				String writeTime = (String) hit.getSource().get("writeTime");
				String content = (String) hit.getSource().get("content");
				String updateTime = (String) hit.getSource().get("updateTime");
				SimpleDateFormat sdf =new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
				String writeDate=null;
				if(StringUtils.isNoneBlank(writeTime)){
					writeDate=sdf.format(new Date(Long.parseLong(writeTime)));
				}
				String updateDate=null;
				if(StringUtils.isNoneBlank(updateTime)){
					updateDate=sdf.format(new Date(Long.parseLong(updateTime)));
				}
				
				articleDo.setAuthor(author);
				articleDo.setContent(content);
				articleDo.setTitle(title);
				articleDo.setUpdateTime(updateDate==null?"": updateDate);
				articleDo.setUuid(uuid);
				articleDo.setWriteTime(writeDate==null?"": writeDate);
				articleDos.add(articleDo);
			}
			return articleDos;
		}else {
			return null;
		}
	}
	
}
