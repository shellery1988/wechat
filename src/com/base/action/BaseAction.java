package com.base.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5571911244373329045L;
	protected String id;
	protected String[] ids;
	protected String logInfo;// 日志记录附加信息
	protected String redirectionUrl;// 操作提示后的跳转URL,为null则返回前一页
	protected Map<String, Object> data;
	protected String result = SUCCESS;
	protected String message;
	protected Long lid;

	public Long getLid() {
		return lid;
	}

	public void setLid(Long lid) {
		this.lid = lid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public String getRedirectionUrl() {
		return redirectionUrl;
	}

	public void setRedirectionUrl(String redirectionUrl) {
		this.redirectionUrl = redirectionUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// 获取Attribute
	public Object getAttribute(String name) {
		return ServletActionContext.getRequest().getAttribute(name);
	}

	// 设置Attribute
	public void setAttribute(String name, Object value) {
		ServletActionContext.getRequest().setAttribute(name, value);
	}

	// 获取Parameter
	public String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	// 获取Parameter数组
	public String[] getParameterValues(String name) {
		return getRequest().getParameterValues(name);
	}

	// 获取Session
	public static Object getSession(String name) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session.get(name);
	}

	// 获取Session
	public static Map<String, Object> getSession() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session;
	}

	// 设置Session
	public static void setSession(String name, Object value) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.put(name, value);
	}

	// 获取Request
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	// 获取Response
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	// 获取Application
	public static ServletContext getApplication() {
		return ServletActionContext.getServletContext();
	}

	public static void ajax(String content, String type) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void ajax(String content, String type, String charset) {
		try {
			charset = charset == null || "".equals(charset) ? "UTF-8" : charset;
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(type + ";charset="+charset);
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void ajax(String content, String type,HttpServletResponse response) {
		try {
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ajax 输出文本
	public static void ajaxText(String text) {
		ajax(text, "text/plain");
	}

	public static void ajaxText(String content,HttpServletResponse res){
		ajax(content,"text/plain",res);
	}
	
	// ajax 输出HTML
	public static void ajaxHtml(String html) {
		ajax(html, "text/html");
	}

	// ajax 输出XML
	public static void ajaxXml(String xml) {
		ajax(xml, "text/xml");
	}

	// ajax 根据字符串输出JSON
	public static void ajaxJson(String jsonString) {
		ajax(jsonString, "application/json");
	}

}
