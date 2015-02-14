package com.jiaoyiping.util.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class HttpUtil {
	public static Document XmlForSendRequest(String urlstr,
			String requestMethod, String contentype, String content) {
		URL url = null;
		Document doc = null;
		try {
			url = new URL(urlstr);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod(requestMethod);
			if (contentype.length() > 0)
				connection.setRequestProperty("Content-Type", contentype);
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			// connection.setConnectTimeout(1000);
			connection.setUseCaches(false);
			connection.connect();
			if (content.length() > 0) {
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				out.write(content.getBytes("gb2312"));
				out.flush();
				out.close();
			}
			SAXReader builder = new SAXReader();
			builder.setEncoding("UTF-8");
			InputStreamReader it = new InputStreamReader(
					connection.getInputStream(), "UTF-8");

			doc = builder.read(it);

			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	public static String StrForSendRequest(String urlstr, String requestMethod,
			String contentype, String content) {
		URL url = null;
		StringBuffer strXmlBuffer = new StringBuffer();
		try {
			url = new URL(urlstr);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod(requestMethod);
			if (contentype.length() > 0)
				connection.setRequestProperty("Content-Type", contentype);
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			// connection.setConnectTimeout(1000);
			connection.setUseCaches(false);
			connection.connect();
			if (content.length() > 0) {
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				out.write(content.getBytes("gb2312"));
				out.flush();
				out.close();
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				strXmlBuffer.append(inputLine.trim());
			}
			in.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strXmlBuffer.toString();
	}

	public static Document XmlForSendRequest(String urlstr) {
		return XmlForSendRequest(urlstr, "GET", "text/html;charset=UTF-8", "");
	}

	public static int IntForSendRequest(String urlstr, String requestMethod,
			String contentype, String content) {
		URL url = null;
		int responseCode = 404;
		try {
			url = new URL(urlstr);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod(requestMethod);
			if (contentype.length() > 0)
				connection.setRequestProperty("Content-Type", contentype);
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			// connection.setConnectTimeout(1000);
			connection.setUseCaches(false);
			connection.connect();
			if (content.length() > 0) {
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				out.writeBytes(content);
				out.flush();
				out.close();
			}
			responseCode = connection.getResponseCode();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseCode;
	}
}