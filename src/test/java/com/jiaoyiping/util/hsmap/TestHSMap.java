package com.jiaoyiping.util.hsmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TestHSMap {
	
	List<SubEntry> subEntryList = new LinkedList<SubEntry>();
	
	public static void main(String[] args) {
		TestHSMap testHSMap = new TestHSMap();
		testHSMap.before();
		testHSMap.testCreate();
	}
	
	@Test
	public void testCreate(){
		HSMap<String,SubEntry> hsMap = new HSMap<String,SubEntry>("getEntryId",subEntryList);
//		hsMap.setMethodName("getEntryId");
//		hsMap.put(subEntryList);
		Set<String> ss = hsMap.keySet();
		for(Iterator<String> it = ss.iterator();it.hasNext();){
			String key = it.next();
			List<SubEntry> list = hsMap.get(key);
			for(SubEntry s : list){
				System.out.println(s.getSubEntryName());
			}
			System.out.println("==============");
			
		}
		
		
		
		
		
	}
	
	//准备测试数据
	@Before
	public void before(){
		subEntryList = new ArrayList<SubEntry>();
		
		SubEntry s1 = new SubEntry("1","1","第一组1");
		SubEntry s2 = new SubEntry("2","2","第二组1");
		SubEntry s3 = new SubEntry("3","3","第三组1");
		SubEntry s4 = new SubEntry("5","4","第四组1");
		SubEntry s5 = new SubEntry("5","1","第一组2");
		SubEntry s6 = new SubEntry("6","2","第二组2");
		SubEntry s7 = new SubEntry("7","3","第三组2");
		
		subEntryList.add(s1);
		subEntryList.add(s2);
		subEntryList.add(s3);
		subEntryList.add(s4);
		subEntryList.add(s5);
		subEntryList.add(s6);
		subEntryList.add(s7);
	}

}

class Entry{
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
class SubEntry{
	private String subEntryId;
	private String entryId;
	private String subEntryName;
	public String getSubEntryId() {
		return subEntryId;
	}
	public void setSubEntryId(String subEntryId) {
		this.subEntryId = subEntryId;
	}
	public String getEntryId() {
		return entryId;
	}
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	public String getSubEntryName() {
		return subEntryName;
	}
	public void setSubEntryName(String subEntryName) {
		this.subEntryName = subEntryName;
	}
	
	public SubEntry(String subEntryId,String entryId,String subEntryName){
		this.subEntryId = subEntryId;
		this.entryId = entryId;
		this.subEntryName = subEntryName;
	}
	
	
}
