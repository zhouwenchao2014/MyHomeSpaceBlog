package com.zhou.blog.commom;

public class FinalFields {
	
	public static final String ELASTIC_SEARCH_INDEX_BLOG = "blog";// es 的index
														// 这里的index相当于关系型数据库mysql的数据库，oracle中的表空间

	public static final String ELASTIC_SEARCH_TYPE_ARTICLE = "article"; // es的type
															// 这里的type相当于关系型数据库mysql，和oracle的表
	public static final String ELASTIC_SEARCH_TYPE_IP = "ip";

	public static final String ELASTIC_SEARCH_LAST_TYPE = "raw";
	
	public static final String ELASTIC_SEARCH_IP="120.26.102.2";
	
	public static final int ELASTIC_SEARCH_PORT=9300;
	
	public static final String ELASTIC_SEARCH_TITLE="article.title.raw";

	public static final String ELASTIC_SEARCH_CONTENT="article.content.raw";

	public static final String ELASTIC_SEARCH_AUTHOR="article.author.raw";

	public static final String ELASTIC_SEARCH_UUID="article.uuid.raw";

	public static final String ELASTIC_SEARCH_WRITETIME="article.writeTime.raw";

	public static final String ELASTIC_SEARCH_UPDATETIME="article.updateTime.raw";
	
	public static final String MEM_CACHE_IP="120.26.102.2";

	public static final String MEM_CACHE_PORT="11211";
	
	public static final String USER_NAME="zhou";
	
	public static final String PASS_WORD="123abc456def";

	
}
