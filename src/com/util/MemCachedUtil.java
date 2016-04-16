package com.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * 向memcached写入数据，或者
 * 
 * @author guiwenqing
 * 
 */
public class MemCachedUtil {

	protected static MemCachedClient mcc = new MemCachedClient();

	static {
		String[] servers = { "121.42.11.139:11211" };
		//String[] servers = { "192.168.67.42:11200" };//正式环境
		
		Integer[] weights = { 3 };

		SockIOPool pool = SockIOPool.getInstance();

		pool.setServers(servers);
		pool.setWeights(weights);

		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(5);
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		pool.setMaintSleep(30);

		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);

		pool.initialize();
	}

	/**
	 * 放入到memcached中，date参数是表示过期时间
	 * @param key
	 * @param value
	 * @param date 小时
	 * @return
	 * @date 2015-1-27
	 */
	public static boolean setLock(String key, String value, Integer date) {
		return mcc.add(key, value, new Date(date*60*60*1000));

	}
	
	/**
	 * 放入到memcached中，date参数是表示过期时间
	 * @param key
	 * @param value
	 * @param date 小时
	 * @return
	 * @date 2015-1-27
	 */
	public static boolean setLock(String key, Object value, Integer date) {
		return mcc.add(key, value, new Date(date*60*60*1000));
	}
	
	/**
	 * 放入到memcached中，date参数是表示过期时间
	 * @param key
	 * @param value
	 * @param date 毫秒
	 * @return
	 * @date 2015-1-27
	 */
	public static boolean setLockTime(String key, Object value, Integer date) {
		return mcc.add(key, value, new Date(date));

	}
	
	public static boolean setLock(String key, String value) {
		return mcc.set(key, value);
	}

	public static String getCache(String key) {
		return (String) mcc.get(key);
	}

	public static boolean deleteCache(String key) {
		return mcc.delete(key);
	}
	
	public static Object getCacheObj(String key){
		return mcc.get(key);
	}
	
	/**
	 * 更新缓存，按小时
	 * @param key
	 * @param value
	 * @param date
	 * @return
	 */
	public static boolean addOrUpdate(String key, Object value, Integer date) {
		return mcc.set(key, value, new Date(date*60*60*1000));
	}
	
	/**
	 * 更新缓存，按毫秒
	 * @param key
	 * @param value
	 * @param date
	 * @return
	 */
	public static boolean addOrUpdateTime(String key, Object value, Integer date) {
		return mcc.set(key, value, new Date(date));
	}
	
	/**
	 * 按分页获取的list缓存
	 * @param key
	 * @param pageno 页码
	 * @param pagesize 每页条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<?> getCacheListPage(String key,  int pageno, int pagesize){
		List<Object> list = (List<Object>) mcc.get(key);
		List<Object> aList = new ArrayList<Object>();
		if(pageno>0 && pagesize>0){
			for(int i=0;list!=null && list.size()>0 && i<list.size();i++){
				if(i>=(pageno-1)*pagesize && i<pageno*pagesize){
					aList.add(list.get(i));
				}
			}
			return aList;
		} else{
			return list;
		}
	}
}
