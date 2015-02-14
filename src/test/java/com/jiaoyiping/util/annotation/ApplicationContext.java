package com.jiaoyiping.util.annotation;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class ApplicationContext {
	Logger logger = Logger.getLogger("ApplicationContext");
	List<BeanDefine> beanList = new ArrayList<BeanDefine>();  
	Map<String, Object> sigletions = new HashMap<String, Object>();  
	public ApplicationContext(String xmlPath){
		this.readXml(xmlPath);
		this.InstanceBean();
		this.annotationInject();
		
	}
	/**
	 * 加载Xml配置文件
	 * @param filePath
	 */
	@SuppressWarnings("unchecked")
	private void readXml(String filePath){
		Document doc = null;
		SAXReader reader = new SAXReader();
		try {
			logger.info("开始读取配置文件");
			doc = reader.read(new FileInputStream(new File(filePath)));
			Element beans = doc.getRootElement();
			for(Iterator<Element> it = beans.elementIterator();it.hasNext();){
				Element element = it.next();
				String id = element.attributeValue("id");
				String className=element.attributeValue("class");
				BeanDefine beanDefien = new BeanDefine(id, className);
				beanList.add(beanDefien);
			}
			logger.info("读取配置文件结束");
		} catch (Exception e) {
			logger.info("读取配置文件出错");
		}
	}
	/**
	 * 实例化Bean
	 */
	private void InstanceBean(){
		if(this.beanList!=null){
			for(BeanDefine bean :beanList){
				try{
					System.out.println(bean.getName());
					System.out.println(bean.getClassName());
					String key = bean.getName();
					Object value=Class.forName(bean.getClassName()).newInstance();
					System.out.println("key==null"+key==null);
					System.out.println("value==null"+value==null);
				sigletions.put(key, value);
				}catch(Exception ex){
					logger.info("-----实例化bean出错-----");
					ex.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * 处理注解
	 */
	private void annotationInject(){
		for(String key : sigletions.keySet()){
			Object obj = sigletions.get(key);
			if(obj != null){
				this.dealPropertyAnnotation(obj);
				this.dealFieldAnnotation(obj
						
						);
			}
			
		}
		
	}
	/**
	 * 处理set方法上的注解
	 */
	private void dealFieldAnnotation(Object bean){
		try {  
            //获取其属性的描述  
            PropertyDescriptor[] ps =   
                Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();  
            for(PropertyDescriptor proderdesc : ps){  
                //获取所有可以设置值的方法方法(proderdesc.getReadMethod()是获取所有可以得到值的方法(类似get))  
                Method setter = proderdesc.getWriteMethod();  
                //判断set方法是否定义了注解  
                if(setter!=null && setter.isAnnotationPresent(Resource.class)){  
                    //获取当前注解，并判断name属性是否为空  
                    Resource resource = setter.getAnnotation(Resource.class);  
                    String name ="";  
                    Object value = null;  
                    if(resource.name()!=null&&!"".equals(resource.name())){  
                        //获取注解的name属性的内容  
                        name = resource.name();  
                        value = sigletions.get(name);  
                    }else{ //如果当前注解没有指定name属性,则根据类型进行匹配  
                        for(String key : sigletions.keySet()){  
                            //判断当前属性所属的类型是否在配置文件中存在  
                            if(proderdesc.getPropertyType().isAssignableFrom(sigletions.get(key).getClass())){  
                                //获取类型匹配的实例对象  
                                value = sigletions.get(key);  
                                break;  
                            }  
                        }  
                    }  
                    //允许访问private方法  
                    setter.setAccessible(true);  
                    //把引用对象注入属性  
                    setter.invoke(bean, value);   
                }  
            }  
        } catch (Exception e) {  
            logger.info("set方法注解解析异常..........");  
        }  
	}
	
	private Object getBean(String serviceKey){
		return this.sigletions.get(serviceKey);
	}
	
	/**
	 * 处理属性上的注解
	 */
	public void dealPropertyAnnotation(Object bean){
		try {  
            //获取其全部的字段描述  
            Field[] fields = bean.getClass().getFields();  
            for(Field f : fields){  
                if(f!=null && f.isAnnotationPresent(Resource.class)){  
                	Resource resource = f.getAnnotation(Resource.class);  
                    String name ="";  
                    Object value = null;  
                    if(resource.name()!=null&&!"".equals(resource.name())){  
                        name = resource.name();  
                        value = sigletions.get(name);  
                    }
                    //若根据名称没有找到合适的对象，就根据类型来匹配
                    else{  
                        for(String key : sigletions.keySet()){  
                            //判断当前属性所属的类型是否在配置文件中存在  
                            if(f.getType().isAssignableFrom(sigletions.get(key).getClass())){  
                                //获取类型匹配的实例对象  
                                value = sigletions.get(key);  
                                break;  
                            }  
                        }  
                    }  
                    //允许访问private字段  
                    f.setAccessible(true);  
                    //把引用对象注入属性  
                    f.set(bean, value);  
                }  
            }  
        } catch (Exception e) {  
            logger.info("字段注解解析异常..........");  
            //反射得到所有字段
            Field[] fields = bean.getClass().getFields();
            for(Field f:fields){
            	//若字段上有Resource注解
            	if(f!=null && f.isAnnotationPresent(Resource.class)){
            		Resource resource = f.getAnnotation(Resource.class);
            		String name = resource.name();
            		Object value = sigletions.get(name);
            		//压制警告(压制之后可以访问private类型的方法)
            		f.setAccessible(true);
            		try {
            			//向字段注入值
						f.set(bean, value);
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            		
            	}
            }
        }  
		
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ApplicationContext("C:\\Users\\Administrator\\git\\util1.1\\util\\src\\test\\java\\com\\jiaoyiping\\util\\annotation\\beans.xml");
		UserServiceImpl service = (UserServiceImpl)context.getBean("userServiceImpl");
		service.getUser();
		service.getUsesr1();
		
	}
}

class BeanDefine implements Serializable{
	private static final long serialVersionUID = -3154983247673987843L;
	private String name;
	private String className;
	public BeanDefine(String name, String className) {
		super();
		this.name = name;
		this.className = className;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}
  
