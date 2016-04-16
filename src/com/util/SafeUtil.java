package com.util;

import java.lang.reflect.Method;

public class SafeUtil {
	
	private static SafeUtil safeUtil = new SafeUtil();
	
	public static SafeUtil getInstance(){
		return safeUtil;
	}
	
	public static void trimObject(Object obj){
	    Class<? extends Object> clazz = obj.getClass();
	    try {
			for(Method method: clazz.getMethods()){
				String methodName = method.getName();
				String returnType = method.getReturnType().getName();
				if(methodName.startsWith("get") && 
						!"getClass".equals(methodName) && 
						"java.lang.String".equals(returnType) &&
						method.invoke(obj)!=null){
					String value = (String) method.invoke(obj);
					clazz.getMethod("set" + methodName.replace("get", ""), String.class).invoke(obj, value.trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
