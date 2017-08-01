package cn.waynechu.topblog.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static boolean isBlank(String value) {
		return !allNotBlank(value);
	}

	public static boolean allNotBlank(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !StringUtils.isBlank(value);
			}
		}
		return result;
	}

	public static String toUnderline(String s) {
		if (s == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i >= 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					if (i > 0)
						sb.append('_');
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	public static String upperCaseFirst(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		int length = str.length();
		StringBuilder sb = new StringBuilder(length);
		sb.append(Character.toUpperCase(str.charAt(0)));
		if (length > 1) {
			String remaining = str.substring(1);
			sb.append(remaining);
		}
		return sb.toString();
	}

	public static byte[] toBytes(String str) {
		return str == null ? null : str.getBytes();
	}

}
