package com.zhou.blog.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhou.blog.service.ElasticSearchMonitorServer;
import com.zhou.blog.utils.ElasticSearch;

@Service
public class ElasticSearchMonitorServerImpl implements ElasticSearchMonitorServer {
	private Logger logger=LoggerFactory.getLogger(ElasticSearchMonitorServerImpl.class);

	public String getIndexName(HttpServletRequest request) {
		if (!(isLogin(request))) {
			return "message:用户认证失败,code:100";
		}

		List indexList = new ArrayList();
		try {
			BufferedReader bufferedReader = getMessageFromUrl("http://120.26.102.2:9200/_cat/indices?v");
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.replaceAll("  ", " ");
				line = line.replaceAll("  ", " ");
				line = line.replaceAll("  ", " ");
				String[] index = line.split(" ");
				if (!(index[2].equals("index")))
					indexList.add("IndexName:" + index[2]);

				this.logger.info(index[2]);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSONArray.toJSONString(indexList);
	}

	public String getMessageByIndexName(HttpServletRequest request) {
		if (!(isLogin(request)))
			return "message:用户认证失败,code:100";

		if (!(isContainSize(request))) {
			return "message:参数缺失,code:102";
		}

		StringBuffer baseUrl = new StringBuffer("http://120.26.102.2:9200/blog/_search?");
		StringBuffer param = getMap(request);
		baseUrl.append(param);
		BufferedReader bufferedReader = getMessageFromUrl(baseUrl.toString());
		char[] message = new char[10000000];
		String returnStr = "";
		try {
			bufferedReader.read(message);
			String messagestr = new String(message);
			returnStr = JSON.toJSONString(messagestr);
			returnStr = returnStr.replace("\\u0000", "");
			returnStr = returnStr.replace("\\", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnStr;
	}

	/**
	 * 从URL获取信息
	 * @param urlstr
	 * @return
	 */
	private BufferedReader getMessageFromUrl(String urlstr) {
		BufferedReader bufferedReader = null;
		try {
			URL url = new URL(urlstr);
			URLConnection urlConnection = url.openConnection();
			bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedReader;
	}

	/**
	 * 是否是合法用户
	 * @param request
	 * @return
	 */
	private boolean isLogin(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if ((!(StringUtils.isNoneBlank(new CharSequence[] { username }))) || (!(username.equals("zhou")))) {
			this.logger.error("用户名不正确");
			return false;
		}
		if ((!(StringUtils.isNoneBlank(new CharSequence[] { password }))) || (!(password.equals("123abc456def")))) {
			this.logger.error("密码不正确");
			return false;
		}
		return true;
	}

	/**
	 * request参数是否带size
	 * @param request
	 * @return
	 */
	private boolean isContainSize(HttpServletRequest request) {
		String size = request.getParameter("size");
		if (!(StringUtils.isNoneBlank(new CharSequence[] { size }))) {
			this.logger.error("size为空");
			return false;
		}
		if (!(size.matches("^[1-9]\\d*$"))) {
			this.logger.error("size不为数字");
			return false;
		}
		if (Integer.parseInt(size) > 10000) {
			this.logger.error("size太大");
			return false;
		}
		if (Integer.parseInt(size) < 1) {
			this.logger.error("size太小");
			return false;
		}
		return true;
	}

	/**
	 * 从request里面生成参数url
	 * @param request
	 * @return
	 */
	public StringBuffer getMap(HttpServletRequest request) {
		Map requestMap = request.getParameterMap();
		Set set = requestMap.keySet();
		StringBuffer param = new StringBuffer();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			String str = (String) it.next();
			if ((!(str.equals("username"))) && (!(str.equals("password"))) && (!(str.equals("size"))))
				param.append("q=" + str + ":" + ((String[]) requestMap.get(str))[0] + "&");

			if (str.equals("size"))
				param.append("size=" + ((String[]) requestMap.get(str))[0] + "&");
		}

		return param;
	}

	
	/**
	 * 根据Index从ESapi获取详情
	 */
	public String getMessageByIndexNameFromClient(HttpServletRequest request) {
		if (!(isLogin(request)))
			return "message:用户认证失败,code:100";

		if (!(isContainSize(request)))
			return "message:参数缺失,code:102";

		ElasticSearch elasticSearch = new ElasticSearch();
		SearchHits searchHits = elasticSearch.getMessage(request);
		SearchHit[] searchHit = searchHits.getHits();
		StringBuffer message = new StringBuffer();
		SearchHit[] arrayOfSearchHit1 = searchHit;
		int i = arrayOfSearchHit1.length;
		for (int j = 0; j < i; ++j) {
			SearchHit hit = arrayOfSearchHit1[j];
			message.append("{");
			Set set = hit.getSource().keySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				String str = (String) it.next();
				message.append("\"" + str + "\":\"" + hit.getSource().get(str) + "\",");
			}
			message.append("},");
		}
		return message.toString();
	}
}