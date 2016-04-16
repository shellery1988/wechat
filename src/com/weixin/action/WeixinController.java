package com.weixin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.action.BaseAction;
import com.util.MemCachedUtil;
import com.util.SHA1;
import com.util.WeChatUtil;
import com.weixin.bean.UserBean;
import com.weixin.bean.request.WeChatReqBean;
import com.weixin.bean.response.WeChatRespBean;

@Controller
@RequestMapping("/user")
public class WeixinController extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private static String TOKEN = "shellery1988";

	@RequestMapping({"/checktoken"})
	public void checktoken(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		//获取微信服务器校验参数
		String timestamp = request.getParameter("timestamp");
		String nonce =  request.getParameter("nonce");
		String signature =  request.getParameter("signature");
		String echostr =  request.getParameter("echostr");
		System.out.println("================TOKEAN:"+TOKEN);
        System.out.println("================timestamp:"+timestamp);
        System.out.println("================signature:"+signature);
        System.out.println("================echostr:"+echostr);
		//按字典排序后加密
        String[] str = { TOKEN, timestamp, nonce };
        Arrays.sort(str);
        String bigStr = str[0] + str[1] + str[2];
        String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
        System.out.println("================digest:"+digest);
        
        logger.info("signature:"+signature+"========"+"echostr:"+echostr+"========"
        		+"timestamp:"+timestamp+"========"+"nonce:"+nonce+"======="
        		+"digest:"+digest);
        
        if (digest.equals(signature)) {
        	if(echostr!=null){
            	ajaxText(echostr,response);
        	} else{
        		doPost(request,response);
        	}
        	
        }
    }

	/**
	 * 对服务器请求做出响应
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			Scanner scanner = new Scanner(request.getInputStream());
			response.setContentType("application/xml");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			// 1、获取用户发送的信息
			StringBuffer sb = new StringBuffer(100);
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
			
			// 2、解析用户的信息
			JAXBContext jc = JAXBContext.newInstance(WeChatReqBean.class);
			Unmarshaller u = jc.createUnmarshaller();
			WeChatReqBean reqBean = (WeChatReqBean) u.unmarshal(new StringReader(sb.toString()));
			
			// 3、判断消息类型
			String msgType = reqBean.getMsgType();
			if("event".equals(msgType)){
				String event = reqBean.getEvent();
				if("subscribe".equals(event)){	//关注时间
					String fromUserName = reqBean.getFromUserName();
					subscribe(fromUserName);
				} else if("unsubscribe".equals(event)){	//取消关注
					String fromUserName = reqBean.getFromUserName();
					unsubscribe(fromUserName);
				}
			}
			
			// 4、创建一个回复消息
			/*jc = JAXBContext.newInstance(WeChatRespBean.class);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {
				@Override
				public void escape(char[] arg0, int arg1, int arg2, boolean arg3,
						Writer arg4) throws IOException {
					arg4.write(arg0, arg1, arg2);
				}
			});
			m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			WeChatRespBean respBean = createRespBean(reqBean, "您好，你刚才回复的消息是："+reqBean.getContent());
			m.marshal(respBean, out);
			out.flush();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param reqBean
	 * @param content
	 * @return
	 */
	private WeChatRespBean createRespBean(WeChatReqBean reqBean, String content) {
		WeChatRespBean respBean = new WeChatRespBean();
		respBean.setFromUserName(reqBean.getToUserName());
		respBean.setToUserName(reqBean.getFromUserName());
		respBean.setMsgType(reqBean.getMsgType());
		respBean.setCreateTime(new Date().getTime());
		respBean.setContent(content);
		return respBean;
	}
	
	@RequestMapping({"/getUserList"})
	public ModelAndView getUserList(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/view/pages/wechat/user_manager");
		view.addObject("queryBean", getUserListForPage());
		return view;
	}
	
	private UserBean getUserListForPage(){
		@SuppressWarnings("unchecked")
		Map<String, UserBean> map = (Map<String, UserBean>) MemCachedUtil.getCacheObj("userlist_map");
		Long count = (Long) MemCachedUtil.getCacheObj("userlist_count");
		UserBean userlist = new UserBean();
		if(map==null || map.size()==0 || count==null || count==0){
			userlist = WeChatUtil.getUserList(null);
			MemCachedUtil.setLock("userlist_map", userlist.getPageDataMap(),12);
			MemCachedUtil.setLock("userlist_count", userlist.getTotalRecords(),12);
		} else{
			userlist.setPageDataMap(map);
			userlist.setPageCount(count);
		}
		return userlist;
	}
	
	private void subscribe(String openid){
		UserBean user = WeChatUtil.getUserBaseInfo(openid);
		if(user!=null){
			Map<String, UserBean> map;
			long count = 0;
			UserBean userlist = getUserListForPage();
			if(userlist!=null){
				map = userlist.getPageDataMap();
				count = userlist.getTotalRecords();
			} else{
				map = new HashMap<String, UserBean>();
			}
			map.put(user.getOpenid(), user);
			count++;
			
			MemCachedUtil.addOrUpdate("userlist_map", map, 12);
			MemCachedUtil.addOrUpdate("userlist_count", count, 12);
		}
	}
	
	private void unsubscribe(String openid){
		Map<String, UserBean> map;
		long count = 0;
		UserBean userlist = getUserListForPage();
		if(userlist!=null){
			map = userlist.getPageDataMap();
			count = userlist.getTotalRecords();
			
			map.remove(openid);
			if(count>0)count--;
			
			userlist.setPageDataMap(map);
			userlist.setTotalRecords(count);
			
			MemCachedUtil.addOrUpdate("userlist_map", map, 12);
			MemCachedUtil.addOrUpdate("userlist_count", count, 12);
		}
	}
	
}
