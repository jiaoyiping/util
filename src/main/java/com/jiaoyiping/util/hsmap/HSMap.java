package com.jiaoyiping.util.hsmap;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/**
 * 该类主要作用是为了给类似主子表这样的对象进行分组(字表应包含主表的ID)
 * key   一般为主表对象的主键
 * value 为子表对象
 * methodName 是子表对象中获得主表主键的get方法
 * @author Administrator
 *
 * @param <K>
 * @param <V>
 */
public class HSMap<K,V> extends HashMap<K,List<V>> {

	
	private static final long serialVersionUID = 2958855009088637171L;
	
	private String methodName;
	
	public HSMap(){
		super();
	}
	/**
	 * 
	 * @param methodName get方法名
	 * @param vaules 子表列表
	 */
	public HSMap(String methodName,List<V> vaules){
		this.setMethodName(methodName);
		for(V value : vaules){
			put(value);
		}
	}
	/**
	 * 
	 * @param methodName get方法名
	 * @param values 子表数据 
	 */
	public HSMap(String methodName,V[] values){
		this.setMethodName(methodName);
		for(V value : values){
			put(value);
		}
	}
	
	
	/**
	 * 已知key和value 直接进行put
	 * @param key
	 * @param value
	 */
	public void putItem(K key,V value){
		List<V> values = get(key);
		if(values == null){
			values = new LinkedList<V>();
			put(key,values);
		}
		values.add(value);
		
	}
	
	/**
	 * 根据value对象和相关的方法名 使用反射来动态调用get方法得到key值，然后再进行put
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public void put(V value){
		if(value == null){
			return ;
		}
		try {
			Method m = value.getClass().getMethod(getMethodName());
			K key = (K)m.invoke(value);
			putItem(key,value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	

}
