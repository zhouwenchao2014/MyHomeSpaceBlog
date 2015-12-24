package com.zhou.blog.utils;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
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
}
