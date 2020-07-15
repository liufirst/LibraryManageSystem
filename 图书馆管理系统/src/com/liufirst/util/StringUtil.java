package com.liufirst.util;

public class StringUtil {
	/**
	 * 字符串工具类，判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str==null||"".equals(str)) {
			return true;
		}else {
		return false;
		}
	}
	/**
	 * 判断是否不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if(str!=null&& !"".equals(str.trim())) {
			return true;
		}else {
			return false;
		}
	}
}
