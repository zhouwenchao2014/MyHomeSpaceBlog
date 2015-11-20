package com.zhou.blog.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.queryparser.xml.QueryBuilderFactory;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequestBuilder;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhou.blog.commom.FinalFields;
import com.zhou.blog.model.ArticleDo;

/**
 * es接口
 * 
 * @author zhouwenchao
 *
 */
public class ESearch {
	
	private Logger logger=LoggerFactory.getLogger(ESearch.class);

	private Client client;

	public ESearch() {
		// 集群连接超时设置
		/*
		 * Settings settings = ImmutableSettings.settingsBuilder().put(
		 * "client.transport.ping_timeout", "10s").build(); client = new
		 * TransportClient(settings);
		 */
		client = new TransportClient().addTransportAddress(
				new InetSocketTransportAddress(FinalFields.ELASTIC_SEARCH_IP, FinalFields.ELASTIC_SEARCH_PORT));
	}

	/**
	 * 建立索引,索引建立好之后,会在elasticsearch-0.20.6\data\elasticsearch\nodes\0创建所以你看
	 * 为索引库名，一个es集群中可以有多个索引库。 名称必须为小写
	 * Type为索引类型，是用来区分同索引库下不同类型的数据的，一个索引库下可以有多个索引类型。
	 * 
	 * @param jsondata
	 *            json格式的数据集合
	 * 
	 * @return
	 */
	public void createIndexResponse(List<String> jsondata) {
		// 创建索引库 需要注意的是.setRefresh(true)这里一定要设置,否则第一次建立索引查找不到数据
		IndexRequestBuilder requestBuilder = client
				.prepareIndex(FinalFields.ELASTIC_SEARCH_INDEX_BLOG, FinalFields.ELASTIC_SEARCH_TYPE_ARTICLE)
				.setRefresh(true);
		for (int i = 0; i < jsondata.size(); i++) {
			requestBuilder.setSource(jsondata.get(i)).execute().actionGet();
		}

	}

	/**
	 * 创建索引
	 * 
	 * @param client
	 * @param jsondata
	 * @return
	 */
	public IndexResponse createIndexResponse(String jsondata) {
		IndexResponse response = client
				.prepareIndex(FinalFields.ELASTIC_SEARCH_INDEX_BLOG, FinalFields.ELASTIC_SEARCH_TYPE_ARTICLE)
				.setSource(jsondata).execute().actionGet();
		return response;
	}

	/**
	 * 执行搜索
	 * 
	 * @param queryBuilder
	 * @return
	 */
	public SearchHits searcher(HttpServletRequest request) {
		// SearchResponse searchResponse =
		// client.prepareSearch().execute().actionGet();
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(FinalFields.ELASTIC_SEARCH_INDEX_BLOG)
				.setTypes(FinalFields.ELASTIC_SEARCH_TYPE_ARTICLE).setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		String uuid = request.getParameter("uuid");
		if (StringUtils.isNotBlank(uuid)) {
			searchRequestBuilder.setQuery(QueryBuilders.termQuery(FinalFields.ELASTIC_SEARCH_UUID, uuid));
		}
		String title = request.getParameter("title");
		if (StringUtils.isNotBlank(title)) {
			searchRequestBuilder.setQuery(QueryBuilders.prefixQuery(FinalFields.ELASTIC_SEARCH_TITLE, title));
		}
		String author = request.getParameter("author");
		if (StringUtils.isNotBlank(author)) {
			searchRequestBuilder.setQuery(QueryBuilders.termQuery(FinalFields.ELASTIC_SEARCH_AUTHOR, author));
		}
		String content = request.getParameter("content");
		if (StringUtils.isNotBlank(content)) {
			searchRequestBuilder.setQuery(QueryBuilders.prefixQuery(FinalFields.ELASTIC_SEARCH_CONTENT, content));
		}
		int pageSize = request.getParameter("pageSize")==null?20:Integer.parseInt(request.getParameter("pageSize"));
		int pageStart = request.getParameter("pageStart")==null?0:Integer.parseInt(request.getParameter("pageStart"));
		int pageEnd = pageSize + pageStart;
		SearchResponse searchResponse = searchRequestBuilder.setFrom(pageStart).setSize(pageSize).setExplain(true)
				.execute().actionGet();
		SearchHits hits = searchResponse.getHits();
		System.out.println("查询到记录数=" + hits.getTotalHits());
		return hits;
	}
	
	public SearchHits queryAll(HttpServletRequest request){
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(FinalFields.ELASTIC_SEARCH_INDEX_BLOG)
				.setTypes(FinalFields.ELASTIC_SEARCH_TYPE_ARTICLE).setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		if(request!=null){
			String operationType=request.getParameter("operationType");
			int pageSize = request.getParameter("pageSize")==null?20:Integer.parseInt(request.getParameter("pageSize"));
			int pageStart = request.getParameter("pageStart")==null?0:Integer.parseInt(request.getParameter("pageStart"));
			QueryBuilder query = QueryBuilders.fuzzyLikeThisQuery().likeText(operationType).likeText(StringUtils.upperCase(operationType)).likeText(StringUtils.lowerCase(operationType)).likeText(operationType.replaceFirst(operationType.substring(0, 1),operationType.substring(0, 1).toUpperCase())); 
			SearchResponse searchResponse = searchRequestBuilder.setQuery(query).setFrom(pageStart).setSize(pageSize).setExplain(true)
					.execute().actionGet();
			SearchHits hits = searchResponse.getHits();
			return hits;
		}else{
			SearchResponse searchResponse = searchRequestBuilder.setFrom(0).setSize(20).setExplain(true)
					.execute().actionGet();
			SearchHits hits = searchResponse.getHits();
			return hits;
		}
		
		
	}

	public void createIndex(Object object) {
		IndexRequestBuilder requestBuilder = client.prepareIndex("blog", "article").setRefresh(true);
		ArticleDo article=(ArticleDo) object;
		requestBuilder.setSource(Utils.generateJson(article)).execute().actionGet();
		
	}
	
	public void updateByUUid(ArticleDo article){
		deleteByParam("uuid",article.getUuid());
		createIndex(article);
	}
	
	public SearchHits queryByUuid(String type,String value){
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(FinalFields.ELASTIC_SEARCH_INDEX_BLOG)
				.setTypes(FinalFields.ELASTIC_SEARCH_TYPE_ARTICLE).setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		SearchResponse searchResponse = searchRequestBuilder.setQuery(QueryBuilders.termQuery(type, value)).setExplain(true)
				.execute().actionGet();
		SearchHits hits = searchResponse.getHits();
	      if(hits.totalHits()!=0){
	    	  logger.info("ES查询成功！查询Type："+type+",value:"+value);
	      }else{
	    	  logger.error("ES查询失败！查询Type："+type+",value:"+value);
	      }
	     return hits;
	}
	
	public void deleteByParam(String type,String value){
	      DeleteByQueryResponse deleteByQueryResponse=client.prepareDeleteByQuery("blog").setQuery(QueryBuilders.termQuery(type, value)).execute().actionGet();
	      if(deleteByQueryResponse!=null){
	    	  logger.info("ES删除成功！删除Type："+type+",value:"+value);
	      }else{
	    	  logger.error("ES删除失败！删除Type："+type+",value:"+value);
	      }
	}
}