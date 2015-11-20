package com.zhou.blog.test;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.zhou.blog.model.ArticleDo;
import com.zhou.blog.utils.ESearch;
import com.zhou.blog.utils.Utils;

public class ElasticSearchTest {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@Before
	public void before() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		request.setCharacterEncoding("UTF-8");
		request.setParameter("content", "oracle");
		request.setParameter("pageSize", "20");
		request.setParameter("pageStart", "0");
		request.setParameter("operationType", "Oracle");
	}

	@Test
	public void get() {
		ESearch eSearch = new ESearch();
		SearchHits hits = eSearch.searcher(request);
		SearchHit[] searchHits = hits.getHits();
		System.out.println(searchHits.length);
		for (SearchHit searchHit : searchHits) {
			System.out.println(searchHit.getSource().get("uuid"));
			System.out.println(searchHit.getSource().get("title"));
			System.out.println(searchHit.getSource().get("author"));
			System.out.println(searchHit.getSource().get("writeTime"));
			System.out.println(searchHit.getSource().get("updateTime"));
			System.out.println(searchHit.getSource().get("content"));
			System.out.println();
		}
	}
	
	@Test
	public void getAllByParam() {
		ESearch eSearch = new ESearch();
		SearchHits hits = eSearch.queryAll(request);
		SearchHit[] searchHits = hits.getHits();
		System.out.println(searchHits.length);
		for (SearchHit searchHit : searchHits) {
			System.out.println(searchHit.getSource().get("uuid"));
			System.out.println(searchHit.getSource().get("title"));
			System.out.println(searchHit.getSource().get("author"));
			System.out.println(searchHit.getSource().get("writeTime"));
			System.out.println(searchHit.getSource().get("updateTime"));
			System.out.println(searchHit.getSource().get("content"));
			System.out.println();
		}
	}
	@Test
	public void getAll() {
		ESearch eSearch = new ESearch();
		SearchHits hits = eSearch.queryAll(null);
		SearchHit[] searchHits = hits.getHits();
		System.out.println(searchHits.length);
		for (SearchHit searchHit : searchHits) {
			System.out.println(searchHit.getSource().get("uuid"));
			System.out.println(searchHit.getSource().get("title"));
			System.out.println(searchHit.getSource().get("author"));
			System.out.println(searchHit.getSource().get("writeTime"));
			System.out.println(searchHit.getSource().get("updateTime"));
			System.out.println(searchHit.getSource().get("content"));
			System.out.println();
		}
	}
	
	@Test
	public void delete() {
		ESearch eSearch = new ESearch();
		eSearch.deleteByParam("uuid", "22611f116773421f9ab8b9c73e3b1358");
	}
	
	
	@Test
	public void update(){
		ArticleDo article = new ArticleDo("e93966825ba1458bac26e51ff1814983", "Mybatis 批量插入带Oracle序列 例子", "1447831383033", new Date().getTime() + "", "zhou",
				"<!-- 批量插入 -->"+
    "<insert id=\"inserts\" parameterType=\"java.util.List\">"+
       "insert into PRESON"+
        "select SEQ_PRESON_ID.NEXTVAL,A.* from("+
        "<foreach collection=\"list\" item=\"item\" index=\"index\" "+
            "separator=\"UNION\">"+
            "SELECT"+
            "#{item.presonName},"+
            "#{item.presonTel},"+
            "#{item.presonEmail},"+
            "#{item.presonAge}"+
            "from dual"+
            " </foreach>"+
        ") A"+
    "</insert>");
		ESearch eSearch = new ESearch();
		eSearch.updateByUUid(article);
	}
	
	@Test
	public void set() {
		ESearch eSearch = new ESearch();
		ArticleDo article = new ArticleDo(Utils.newUUID(), "java数组转换成list性能比较", new Date().getTime() + "", "", "zhou",
				"今天遇到要用到将数组转换成list，看了一下Arrays里面有一个方法asList可以将数组转换成list。但是我们亦可以用for循环把数组转换成list。经过试验发现两种方法性能相差不较大。"+

"试验代码："+

"packagecom.zhou.utils.test;"+

"importjava.util.ArrayList;"+

"importjava.util.Arrays;"+

"importjava.util.List;"+

"publicclassArrayToListTest {"+

"publicstaticvoidmain(String[]args) {"+

"Stringstr=\"1,1,1,1,1,1,11,1,1,1,1,1,1,1,11,1,1,1,1,1,1,11,1,1,1,1,1,11,1,1,,,2,2,22,2,2,2,22,2,2,2,2,22,2,2,2,2,2,22,2,2,2,2,2,22,2,2,2,2,2,2,22,,,2,2,2,22,2,,2,2,2,2,2,22,2,2,2,2,22,2,2,2,2,22,2,2,2,2,2,22,2,2,2,2,22,2,,22,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,,,2,2,,2,2,2,2,2,2,2,2,,2,2,2,2,,2,2,2,2,2,,2,2,2 \";"+          

"useArraysUtils(str);"+

"useFor(str);"+

"}"+
"publicstaticvoiduseArraysUtils(Stringstr){"+

"longstart=System.nanoTime();"+

"String[]strs=str.split(\",\");"+

"Lista=Arrays.asList(strs);"+

"longend=System.nanoTime();"+

"System.out.println(\"=========\"+a.size()+\"=====useTime:\"+(end-start));"+

"}"+
"publicstaticvoiduseFor(Stringstr){"+

"longstart=System.nanoTime();"+

"Listlist=newArrayList();"+

"String[]strs=str.split(\",\");"+

"for(Stringstr1:strs){"+

"list.add(str1);"+

"}"+
"longend=System.nanoTime();"+

"System.out.println(\"=========\"+list.size()+\"=====useTime:\"+(end-start));"+

"}"+
"}"+
"输出结果："+

"=========158=====useTime:1365970"+

"=========158=====useTime:400812"+
"第一个是用java的util类里面的方法，第二个是自己写的for循环，相差在三倍"+

"我们在换短一点的str,这次我们用"+

"Stringstr=\"2,2,2,2,2\";"+
"得出结果如下:"+

"=========5=====useTime:472153"+

"=========5=====useTime:60424"+
"相差甚大。由此可见Arrays.asList方法的性能不如for循环。"+

"所以我们在开发的时候不一定要用java提供的方法。我们自己写的也许性能更好");
		eSearch.createIndex(article);
	}
}
