package com.aishang.po;

public class News {
	private int id;
	private String title;
	private String content;
	private int typeid;
	private int flag;
	private String createtime;
	private long uid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long l) {
		this.uid = l;
	}
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", content=" + content
				+ ", typeid=" + typeid + ", flag=" + flag + ", createtime="
				+ createtime + ", uid=" + uid + "]";
	}
	
}
