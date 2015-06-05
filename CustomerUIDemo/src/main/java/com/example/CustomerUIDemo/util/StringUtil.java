package com.example.CustomerUIDemo.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class StringUtil {
	public static String encodeURL(String value)
			throws UnsupportedEncodingException {
		if (!isEmpty(value)) {
			return URLEncoder.encode(value, "UTF-8");
		}
		return "";
	}

	public static String decodeURL(String value)
			throws UnsupportedEncodingException {
		if (!isEmpty(value)) {
			return URLDecoder.decode(value, "UTF-8");
		}
		return "";
	}

	public static String encodeBase64(String value) {
		if (!isEmpty(value)) {
			return Base64.encodeToString(value.getBytes(), Base64.DEFAULT);
		}
		return "";
	}

	public static String decodeBase64(String value) throws IOException {
		if (!isEmpty(value)) {
			return new String(Base64.decode(value,Base64.DEFAULT));
		}
		return "";
	}

	public static Bitmap convertBase64ToBitmap(String data) throws IOException {
		if (isEmpty(data)) {
			return null;
		}
		
		byte[] imageAsBytes = Base64.decode(data, Base64.DEFAULT);
		Bitmap bitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0,
				imageAsBytes.length);
		return bitmap;
	}

	public static boolean isEmpty(String value) {
		return value == null || value.trim().equals("") || value.length() == 0;
	}

	public static boolean isLong(String str) {
		if ("0".equals(str.trim())) {
			return true;
		}
		Pattern pattern = Pattern.compile("^[^0]\\d*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isFloat(String str) {
		if (isLong(str)) {
			return true;
		}
		Pattern pattern = Pattern.compile("\\d*\\.{1}\\d+");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isFloat(String str, int precision) {
		String regStr = "\\d*\\.{1}\\d{" + precision + "}";
		Pattern pattern = Pattern.compile(regStr);
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isNumber(String str) {
		if (isLong(str)) {
			return true;
		}
		Pattern pattern = Pattern.compile("(-)?(\\d*)\\.{0,1}(\\d*)");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isEMail(String str) {
		Pattern pattern = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher isEMail = pattern.matcher(str);
		if (!isEMail.matches()) {
			return false;
		}
		return true;
	}

	public static String format(String str, Object... args) {
		if (isEmpty(str))
			return "";
		if (args.length == 0)
			return str;
		String result = str;
		Pattern p = java.util.regex.Pattern.compile("\\{(\\d+)\\}");
		Matcher m = p.matcher(str);
		while (m.find()) {
			int index = Integer.parseInt(m.group(1));
			if (index < args.length && args[index] != null) {
				result = result.replace(m.group(), args[index].toString());
			}
		}
		return result;
	}

	public static String getURLEncodeStr(String url) {
		try {
			url = URLEncoder.encode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
		}
		return url;
	}

}
