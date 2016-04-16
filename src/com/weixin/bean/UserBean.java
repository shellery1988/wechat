package com.weixin.bean;

import java.io.Serializable;

import com.base.entity.PageEntity;

public class UserBean extends PageEntity<UserBean> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int subscribe; //用户是否订阅该公众号标识 0 否 1是
	private String openid; //用户的标识，对当前公众号唯一 
	private String nickname;//用户昵称
	private int sex;		//性别
	private String city;	//所在城市
	private String country; //用户所在国家
	private String province;//所在省份
	private String language;//用户的语言
	private String headimgurl;//头像地址
	private String subscribe_time;//用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 
	private String groupid;	//用户所在分组
	private String next_openid;	//第一个拉取的OPENID，不填默认从头开始拉取 
	public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getNext_openid() {
		return next_openid;
	}
	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	
}
