package com.jiaoyiping.util.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Util {
	
	/**
	 * 得到128位信息指纹(用于网页去重)
	 * @param sourceStrs 源字符串
	 * @return 信息指纹
	 */
	public static String getMessageFingerPrint(String sourceStrs){
		MessageDigest md = null;
		String result = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] resultBytes = md.digest(sourceStrs.getBytes());
			result = bytetoString(resultBytes);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	 private static String bytetoString(byte[] digest) {
	        String str = "";
	        String tempStr = "";
	        
	        for (int i = 1; i < digest.length; i++) {
	            tempStr = (Integer.toHexString(digest[i] & 0xff));
	            if (tempStr.length() == 1) {
	                str = str + "0" + tempStr;
	            }
	            else {
	                str = str + tempStr;
	            }
	        }
	        return str.toLowerCase();
	    }
	}


