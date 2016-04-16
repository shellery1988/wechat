package com.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.weixin.bean.UserBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WeChatUtil {
	
	private static String APPID = "wx7b53e9628ab6f4b9";
	
	private static String APPSECRET = "d20cffe1ce72e47450dc572057280e9c";
	
	private static String getAccessToken(){
		String token = MemCachedUtil.getCache("wechat_token");
		try {
			if(token==null || "".equals(token)){
				String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;
				String jsonStr = Browser.get(getTokenUrl);
				JSONObject jsonObject = JSONObject.fromObject(jsonStr);
				if(jsonObject.get("errcode")!=null){
					token = "error";
				} else{
					token = (String) jsonObject.get("access_token");
					MemCachedUtil.setLockTime("wechat_token", token, (Integer)jsonObject.get("expires_in")*1000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			token = "error";
		}
		return token;
	}
	
	public static void createButton(){
		String body =  
				"{\"button\":["+
			     "{\"type\":\"click\",\"name\":\"今日歌曲排行\",\"key\":\"V1001_TODAY_MUSIC\"}"+
			     	",{\"name\":\"菜单\",\"sub_button\":["+
			           "{\"type\":\"view\", \"name\":\"搜索\", \"url\":\"http://www.soso.com/\"},"+
			           "{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\"},"+
			           "{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]"+
			    "}]}";
		String creatButUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+getAccessToken();
		Map<String,String> param = new HashMap<String,String>();
		param.put("body", body);
		String jsonStr = "";
		try {
			jsonStr = Browser.post(creatButUrl, body, "UTF-8");
		} catch (Exception e) {
			System.out.println("创建自定义菜单失败！："+e.getMessage());
		}
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		if(jsonObject.get("errcode")!=null){
			Integer errcode = (Integer) jsonObject.get("errcode");
			if(errcode==0){
				System.out.println("创建自定义菜单成功！");
			} else{
				System.out.println("创建自定义菜单失败！："+jsonStr);
			}
		}
	}
	
	public static void deleteButton(){
		String creatButUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+getAccessToken();
		String jsonStr = "";
		try {
			jsonStr = Browser.get(creatButUrl);
		} catch (Exception e) {
			System.out.println("删除自定义菜单失败！："+e.getMessage());
		}
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		if(jsonObject.get("errcode")!=null){
			Integer errcode = (Integer) jsonObject.get("errcode");
			if(errcode==0){
				System.out.println("删除自定义菜单成功！");
			} else{
				System.out.println("删除自定义菜单失败！："+jsonStr);
			}
		}
	}
	
	/**
	 * 获取关注用户列表
	 * @param next_openid
	 * @return
	 */
	public static UserBean getUserList(String next_openid){
		String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+
				getAccessToken()+(next_openid==null?"": "&next_openid="+next_openid);
		String jsonStr = "";
		try {
			jsonStr = Browser.get(url);
		} catch (Exception e) {
			System.out.println("获取微信关注用户列表失败！："+e.getMessage());
		}
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		if(jsonObject.get("errcode")!=null){
			return null;
		}
		//用户用户列表
		//List<UserBean> list = new ArrayList<UserBean>();
		Map<String, UserBean> map = new HashMap<String, UserBean>();
		JSONObject data = jsonObject.getJSONObject("data");
		for(Object obj:(JSONArray)data.get("openid")){
			String openid = (String)obj;
			UserBean user = getUserBaseInfo(openid);
			if(user!=null){
				//list.add(user);
				map.put(user.getOpenid(), user);
			}
		}
		UserBean userlist = new UserBean();
		userlist.setTotalRecords(jsonObject.getInt("total"));
		userlist.setNext_openid(jsonObject.get("NEXT_OPENID")==null ? null:jsonObject.getString("NEXT_OPENID"));
		//userlist.setPageData(list);
		userlist.setPageDataMap(map);
		return userlist;
	}
	
	public static UserBean getUserBaseInfo(String openid){
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+getAccessToken()+
				"&openid="+openid+"&lang=zh_CN";
		String jsonStr = "";
		try {
			jsonStr = Browser.get(url);
		} catch (Exception e) {
			System.out.println("获取用户基本信息失败！："+e.getMessage());
			return null;
		}
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		UserBean user = new UserBean();
		user.setSubscribe(jsonObject.getInt("subscribe"));
		user.setOpenid(openid);
		user.setNickname(jsonObject.getString("nickname"));
		user.setSex(jsonObject.getInt("sex"));
		user.setCity(jsonObject.getString("city"));
		user.setProvince(jsonObject.getString("province"));
		user.setCountry(jsonObject.getString("country"));
		user.setHeadimgurl(jsonObject.getString("headimgurl"));
		user.setGroupid(jsonObject.getString("groupid"));
		return user;
	}
	
	public static void main(String[] args) {
		MemCachedUtil.deleteCache("userlist_map");
		MemCachedUtil.deleteCache("userlist_count");
	}
}
