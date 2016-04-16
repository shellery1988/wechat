package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

public class Browser {

	public static final int BUFFER_SIZE = 8192;
	public static final int CONN_TIME_OUT = 20 * 1000;
	public static final int READ_TIME_OUT = 20 * 1000;

	/**
	 * POST提交方式
	 */
	public static String loginPost(String url, Map<String, String> param)
			throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		// 创建表单参数列表
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		// 循环map放入参数
		if (param != null) {
			Set<String> setKey = param.keySet();
			for (String key : setKey) {
				qparams.add(new BasicNameValuePair(key, param.get(key)));
			}
		}
		// 填充表单
		post.setEntity(new UrlEncodedFormEntity(qparams, "UTF-8"));

		HttpResponse response2 = client.execute(post);
		int statusCode = response2.getStatusLine().getStatusCode();
		System.out.println("statusCode:" + statusCode);
		StringBuffer buffer = new StringBuffer();
		String temp = null;
		if (statusCode == 200) {
			HttpEntity entity2 = response2.getEntity();
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(
					entity2.getContent(),"UTF-8"));
			while ((temp = reader2.readLine()) != null) {
				buffer.append(temp + "\r\n");
			}
			return buffer.toString();
		} else if (statusCode == 302) {// 302表示重定向
			Header[] hs = response2.getHeaders("Location");
			if (hs.length > 0) {
				String url1 = hs[0].getValue();
				post.abort();
				return get(url1);
			}
		}
		return null;

	}

	public static String loginPost(String url, Map<String, String> param,
			String encode) throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		// 创建表单参数列表
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		// 循环map放入参数
		if (param != null) {
			Set<String> setKey = param.keySet();
			for (String key : setKey) {
				qparams.add(new BasicNameValuePair(key, param.get(key)));
			}
		}
		// 填充表单
		post.setEntity(new UrlEncodedFormEntity(qparams, encode));

		HttpResponse response2 = client.execute(post);
		int statusCode = response2.getStatusLine().getStatusCode();
		System.out.println("statusCode:" + statusCode);
		StringBuffer buffer = new StringBuffer();
		String temp = null;
		if (statusCode == 200) {
			HttpEntity entity2 = response2.getEntity();
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(
					entity2.getContent(), encode));
			while ((temp = reader2.readLine()) != null) {
				buffer.append(temp + "\r\n");
			}
			return buffer.toString();
		} else if (statusCode == 302) {// 302表示重定向
			Header[] hs = response2.getHeaders("Location");
			if (hs.length > 0) {
				String url1 = hs[0].getValue();
				post.abort();
				return get(url1);
			}
		}
		return null;
	}

	/**
	 * Get提交方式
	 */
	public static String get(String url) {
		//System.out.println("请求的url:" + url);
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 50000);
		HttpGet get = new HttpGet(url);

		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent(), "UTF-8"));
			StringBuffer buffer = new StringBuffer();
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				buffer.append(temp + "\r\n");
			}
			get.abort();
			return buffer.toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";

	}

	public static String getHttpContent(String url) {

		if (url == null) {
			return "";
		}

		String responseBody = "";
		HttpClient httpclient = new DefaultHttpClient();

		ResponseHandler<String> responseHandler = new BasicResponseHandler();

		try {
			HttpGet httpget = new HttpGet(url);
			System.out.println("executing request " + httpget.getURI());

			responseBody = httpclient.execute(httpget, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// When HttpClient instance is no longer needed,
		// shut down the connection manager to ensure
		// immediate deallocation of all system resources
		httpclient.getConnectionManager().shutdown();

		return responseBody;
	}

	public static String post(String url, String paramContent, String encode) {
		StringBuffer responseMessage = null;
		java.net.URLConnection connection = null;
		java.net.URL reqUrl = null;
		OutputStreamWriter reqOut = null;
		InputStream in = null;
		BufferedReader br = null;
		String param = paramContent;
		try {
			responseMessage = new StringBuffer();
			reqUrl = new java.net.URL(url);
			connection = reqUrl.openConnection();
			connection.setDoOutput(true);
			reqOut = new OutputStreamWriter(connection.getOutputStream());
			reqOut.write(param);
			reqOut.flush();
			int charCount = -1;
			in = connection.getInputStream();

			br = new BufferedReader(new InputStreamReader(in, encode));
			while ((charCount = br.read()) != -1) {
				responseMessage.append((char) charCount);
			}

		} catch (Exception ex) {
		} finally {
			try {
				in.close();
				reqOut.close();
			} catch (Exception e) {
			}
		}
		return responseMessage.toString();
	}

}
