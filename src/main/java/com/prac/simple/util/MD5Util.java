package com.prac.simple.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具
 *
 */
public class MD5Util {

	public static String encode32(String sourceStr) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public static String encode16(String sourceStr) {
		return MD5Util.encode32(sourceStr).substring(8, 24);
	}
	
	public static void main(String[] args) {
		String code = encode32("123456");
		System.out.println(code);
	}

}
