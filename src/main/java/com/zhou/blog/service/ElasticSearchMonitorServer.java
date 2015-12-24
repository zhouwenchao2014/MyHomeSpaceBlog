package com.zhou.blog.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

/**
 * ES监控接口
 * @author zhouwenchao
 *
 */
@Produces({ "application/json; charset=UTF-8" })
@Consumes({ "application/x-www-form-urlencoded; charset=UTF-8" })
public interface ElasticSearchMonitorServer {
	
	/**
	 * 获取ES中的INDEX名称
	 * @param paramHttpServletRequest
	 * @return
	 */
	public String getIndexName(HttpServletRequest paramHttpServletRequest);

	/**
	 * 根据ES的INDEX获取详情从URL
	 * @param paramHttpServletRequest
	 * @return
	 */
	public String getMessageByIndexName(HttpServletRequest paramHttpServletRequest);

	/**
	 * 根据ES的INDEX获取详情从ESapi
	 * @param paramHttpServletRequest
	 * @return
	 */
	public String getMessageByIndexNameFromClient(HttpServletRequest paramHttpServletRequest);

}
