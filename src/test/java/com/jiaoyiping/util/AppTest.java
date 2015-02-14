package com.jiaoyiping.util;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.jiaoyiping.util.encrypt.SHA1Util;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@Test
	public void testToDate(){
		String s = "1990-03-10";
		String s1 = "19999821";
		Date d1 = DateUtIl.StringToDate(s,false);
		Date d2 = DateUtIl.StringToDate(s1,false);
		Assert.assertTrue(d1!=null);
		Assert.assertTrue(d2==null);
	}
	
	@Test
	public void testToString(){
		Date d = new Date();
		Date d1 = DateUtIl.StringToDate("1990-03-10",false);
		String s1 = DateUtIl.dateToString(d);
		String s2 = DateUtIl.dateToString(d1);
		System.out.println(s1);
		System.out.println(s2);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testToTimeStamp(){
		String s = "1990-03-10 11:11:11";
		Date d = DateUtIl.StringToDate(s, true);
		System.out.println(d.toGMTString());
		Assert.assertTrue(d!=null);
	}
	@Test
	public void testSHA1(){
		String str="http://www.baidu.com/link?url=4Q8Tmhc87OEqrqNemiAnrGk8nHdehGrGQ5vgMf18pAseI_942015F5pR0lxwSmWNBn8VWQ4UR2IkHNYlAOh5IUJ3GiAftNZiipMNAvevFba";
		String result = SHA1Util.getMessageFingerPrint(str);
		System.out.println(result);
		
	}
}
