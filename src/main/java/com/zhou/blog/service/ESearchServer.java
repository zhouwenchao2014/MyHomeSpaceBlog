package com.zhou.blog.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhouwenchao on 15/12/23.
 */
public interface ESearchServer {

    /**
     * 插入es
     * @param request
     */
    public void insertElasticSearch(HttpServletRequest request);

    /**
     * ES查询
     * @param request
     */
    public void searchElasticSearch(HttpServletRequest request);

}
