package com.weixin.bean.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlCDATA;

@XmlRootElement(name = "xml")
public class WeChatReqBean {
	
	private String toUserName;
	private String fromUserName;
	private Long createTime;
	private String msgType;
	private String content;
	
	private String event;
	
	private Long msgId;

	public String getToUserName() {
		return toUserName;
	}
	
	@XmlCDATA
	@XmlElement(name = "ToUserName")
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}
	
	@XmlCDATA
	@XmlElement(name = "FromUserName")
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	@XmlElement(name = "CreateTime")
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	@XmlCDATA
	@XmlElement(name = "MsgType")
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	@XmlCDATA
	@XmlElement(name = "Content")
	public void setContent(String content) {
		this.content = content;
	}

	public Long getMsgId() {
		return msgId;
	}

	@XmlElement(name = "MsgId")
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
	
	public String getEvent() {
		return event;
	}

	@XmlCDATA
	@XmlElement(name = "Event")
	public void setEvent(String event) {
		this.event = event;
	}

	
}
