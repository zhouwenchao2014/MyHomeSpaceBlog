package com.zhou.blog.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhou.blog.commom.FinalFields;
import com.zhou.blog.model.ArticleDo;

public class ElasticSearch {
	private Logger logger=LoggerFactory.getLogger(ESearch.class);

	private Client client;

	public ElasticSearch() {
		// 集群连接超时设置
		/*
		 * Settings settings = ImmutableSettings.settingsBuilder().put(
		 * "client.transport.ping_timeout", "10s").build(); client = new
		 * TransportClient(settings);
		 */
		client = new TransportClient().addTransportAddress(
				new InetSocketTransportAddress(FinalFields.ELASTIC_SEARCH_IP, FinalFields.ELASTIC_SEARCH_PORT));
	}
	
	public void createIndexArticle(String message) {
		IndexRequestBuilder requestBuilder = client.prepareIndex("blog", "ip").setRefresh(true);
		requestBuilder.setSource(message).execute().actionGet();
	}
	public SearchHits getMessage(HttpServletRequest request){
		int pageSize = 0;
		int pageStart = request.getParameter("pageStart")==null?0:Integer.parseInt(request.getParameter("pageStart"));
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(FinalFields.ELASTIC_SEARCH_INDEX_BLOG)
				.setTypes(FinalFields.ELASTIC_SEARCH_TYPE_IP).setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		Map<String, String[]> requestMap=request.getParameterMap();
		Set<String> set=requestMap.keySet();
		Iterator<String> it = set.iterator();  
		while (it.hasNext()) {  
		  String str = it.next();  
		  if(!str.equals("username")&&!str.equals("password")&&!str.equals("size")){
			  searchRequestBuilder.setQuery(QueryBuilders.termQuery(FinalFields.ELASTIC_SEARCH_TYPE_IP+"."+str, requestMap.get(str)[0]));
		  }if(str.equals("size")){
			  pageSize= Integer.parseInt(requestMap.get(str)[0]);
		  } 
		}
		SearchResponse searchResponse = searchRequestBuilder.setFrom(pageStart).setSize(pageSize).setExplain(true)
				.execute().actionGet();
		SearchHits hits = searchResponse.getHits();
		return hits;
	}
}
