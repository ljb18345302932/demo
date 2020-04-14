package com.aishang.po;

public class User {
	private int id;
	private int depID;
	private String userName;
	private String userPwd;
	private String userCode;
	private String userSex;
	private int userAge;
	private int userPower;
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", depID=" + depID + ", userName=" + userName
				+ ", userPwd=" + userPwd + ", userCode=" + userCode
				+ ", userSex=" + userSex + ", userAge=" + userAge
				+ ", userPower=" + userPower + "]";
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDepID() {
		return depID;
	}
	public void setDepID(int depID) {
		this.depID = depID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public int getUserPower() {
		return userPower;
	}
	public void setUserPower(int userPower) {
		this.userPower = userPower;
	}
}
