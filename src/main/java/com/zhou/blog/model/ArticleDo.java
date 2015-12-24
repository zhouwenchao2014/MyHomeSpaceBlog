package com.zhou.blog.model;

import java.io.Serializable;

public class ArticleDo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1453594822673360390L;
	private String uuid;
	private String title;
	private String writeTime;
	private String UpdateTime;
	private String author;
	private String content;
	private int likeNum;
	private int notLikeNum;

	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

	public int getNotLikeNum() {
		return notLikeNum;
	}

	public void setNotLikeNum(int notLikeNum) {
		this.notLikeNum = notLikeNum;
	}

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}
	public String getUpdateTime() {
		return UpdateTime;
	}
	public void setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public ArticleDo(String uuid, String title, String writeTime, String updateTime, String author, String content, int likeNum, int notLikeNum) {
		this.uuid = uuid;
		this.title = title;
		this.writeTime = writeTime;
		UpdateTime = updateTime;
		this.author = author;
		this.content = content;
		this.likeNum = likeNum;
		this.notLikeNum = notLikeNum;
	}

	public ArticleDo() {
	}
}
