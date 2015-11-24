package com.walter.csdn.csdn;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.walter.csdn.bean.CommonException;

public class DataUtil {
	public static String doGet(String urlStr) throws CommonException {
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.9) Gecko/20100315 Firefox/3.5.9");
			if (conn.getResponseCode() == 200) {
				InputStream is = conn.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				int len = 0;
				char[] buf = new char[1024];

				while ((len = isr.read(buf)) != -1) {
					sb.append(new String(buf, 0, len));
				}

				is.close();
				isr.close();
				conn.disconnect();
			} else {
				throw new CommonException("网络连接错误");
			}
		} catch (Exception e) {
			throw new CommonException("网络连接错误");
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = ' ';
			} else if ((c[i] > 65280) && (c[i] < 65375))
				c[i] = ((char) (c[i] - 65248));
		}
		return new String(c);
	}
}
