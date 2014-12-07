package com.hui.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * Cookie的操作类 <一句话功能简述> <功能详细描述>
 * 
 * @author LuLiLi
 * @version [版本号, 2014-6-19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CookieHandler {
	/**
	 * 创建cookie
	 * 
	 * @param response
	 *            回应
	 * @param nameValues
	 *            存入cookie的键值对
	 * @param days
	 *            设置cookie的有效期
	 */
	public static void createCookie(HttpServletResponse response,
			Hashtable<String, String> nameValues, int days) {
		Set<String> set = nameValues.keySet();
		Iterator<String> it = set.iterator();
		for (; it.hasNext();) {
			String name = (String) it.next();
			String value = (String) nameValues.get(name);
			try {
				value = URLEncoder.encode(value, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
			// 生成新的cookie
			Cookie cookie = new Cookie(name, value);
			// 设置有效日期
			cookie.setMaxAge(days * 24 * 60 * 60);
			// 设置路径（默认）
			cookie.setPath("/");
			//设置cookie httpOnly
//			response.addHeader("Set-Cookie", name+"="+value+"; Path=/; HttpOnly;");
			// 把cookie放入响应中
			response.addCookie(cookie);
		}
	}

	/**
	 * 读取Cookie
	 * 
	 * @param request
	 * @return Hashtable 返回cookie的键值对
	 */
	public static Hashtable<String, String> getCookies(
			HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Hashtable<String, String> cookieHt = new Hashtable<String, String>();
		if (null!=cookies&&cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				try {
					cookieHt.put(cookie.getName(), URLDecoder.decode(cookie.getValue(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return cookieHt;
	}

	/**
	 * 修改cookie中指定键的值
	 * 
	 * @param request
	 * @param name
	 *            指定的键
	 * @param value
	 *            值
	 */
	public static void setCookieValueByName(HttpServletRequest request,
			String name, String value) {
		try {
			value = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		Cookie[] cookies = request.getCookies();
		if (null!=cookies&&cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (name.equalsIgnoreCase(cookies[i].getName())) {
					cookies[i].setValue(value);
				}
			}
		}
	}

	/**
	 * 得到指定键的值
	 * 
	 * @param request
	 * @param name
	 *            指定的键
	 * @return String 值
	 */
	public static String getCookieValueByName(HttpServletRequest request,
			String name) {
		Cookie[] cookies = request.getCookies();
		String resValue = "";
		if (null!=cookies&&cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (name.equalsIgnoreCase(cookies[i].getName())) {
					try {
						resValue = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return resValue;
	}

	/**
	 * 销毁cookie
	 * 
	 * @param request
	 * @param response
	 */
	public static void deleteCookie(HttpServletRequest request,
			HttpServletResponse response,String keyName) {
		Cookie[] cookies = request.getCookies();
		if (null!=cookies&&cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				// 销毁
				if (keyName.equals(cookie.getName())) {  
		            cookie.setValue("");  
		            cookie.setMaxAge(0);  
		            cookie.setPath("/");
		            response.addCookie(cookie);  
		        } 
			}
		}
	}
}
