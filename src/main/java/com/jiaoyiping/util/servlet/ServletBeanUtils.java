package com.jiaoyiping.util.servlet;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;


/**
 * 从请求到bean的转换
 * @author 焦一平
 *
 */
public class ServletBeanUtils {
	/**
	 * 将请求中的参数映射到bean中取(可用于Action中页面表单向bean的自动转换)
	 * @param entryClass bean的类型
	 * @param request 请求对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object request2Bean(Class<?> entryClass,HttpServletRequest request){
		//request.get
		Object result = null;
		try {
			result = entryClass.newInstance();
			@SuppressWarnings("rawtypes")
			Map parameterMap = request.getParameterMap();
			BeanUtils.populate(result, parameterMap);//从请求中注入值到Bean中去
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
