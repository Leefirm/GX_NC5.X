package nc.vo.lcsw.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * <b> JAVA String的一些工具方法 </b>
 * <p>
 * </p>
 * 创建日期:2009-11-09 10:21:14
 * 
 * @author
 * @version NC60
 */

public class StringUtil {

	/**
	 * 构造设置默认值的数组
	 * 
	 * @param value
	 * @param length
	 * @return
	 */
	public static String[] constructArrWithValue(String value, int length) {
		if (0 == length) {
			return null;
		}
		String[] retStr = new String[length];
		int i = 0;
		while (i < length) {
			retStr[i++] = value;
		}
		return retStr;
	}

	public static boolean isContain(String[] collection, String element) {
		if (collection == null && element == null) {
			return true;
		} else if (collection == null) {
			return false;
		}
		for (String temp : collection) {
			if (StringUtil.isStringEqual(temp, element)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return boolean
	 * @param java
	 *            .lang.String
	 */
	public static boolean isNullStringOrNull(String s) {
		return s == null ? true : s.trim().equals("null");
	}

	/**
	 * @return boolean
	 * @param java
	 *            .lang.String
	 */
	public static boolean isSEmptyOrNull(String s) {
		return s == null ? true : s.trim().length() <= 0;
	}

	public static boolean isSEmptyOrNullForAll(String[] strArray) {
		if (strArray == null || strArray.length == 0) {
			return true;
		}
		for (String s : strArray) {
			if (!StringUtil.isSEmptyOrNull(s)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 创建日期:2009-04-23 15:30:50
	 */
	public static boolean isStringEqual(String str1, String str2) {
		String s1 = str1;
		if (s1 == null) {
			s1 = String.valueOf("");
		}
		String s2 = str2;
		if (s2 == null) {
			s2 = String.valueOf("");
		}
		return s1.trim().equals(s2.trim());
	}

	/**
	 * param Object[] objs 合并的内容 param String nulltoken null的占位符 可以为null param
	 * splittoken 分割符 可以为null return 合并后的字符串 创建日期:2009-04-23 15:30:50
	 */
	public static String mergeString(Object[] objs, String nulltoken,
			String splittoken) {
		if (objs == null || objs.length <= 0) {
			return null;
		}
		StringBuilder sf = new StringBuilder("");
		String nvl = nulltoken;
		if (nvl == null) {
			nvl = "null";
		}
		String svalue = null;
		for (int i = 0; i < objs.length; i++) {
			if (i > 0 && splittoken != null) {
				sf.append(splittoken);
			}
			svalue = objs[i] == null ? null : objs[i].toString();
			if (!StringUtil.isSEmptyOrNull(svalue)) {
				sf.append(svalue);
			} else {
				sf.append(nvl);
			}
		}
		return sf.toString();
	}

	public static String mergeTowString(Object obja, Object objb) {
		Object[] objs = new Object[] { obja, objb };
		return StringUtil.mergeString(objs, null, null);
	}

	/**
	 * 如果为null，将null转化为空字符串，否则trim
	 * 
	 * @param str
	 *            原始字符串
	 * @return 处理后的字符串
	 */
	public static String nullToEmptyElseTrim(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	public static String[] removeNullString(String[] strs) {
		List<String> retList = new ArrayList<String>();
		for (String str : strs) {
			if (!StringUtil.isNullStringOrNull(str)) {
				continue;
			}
			retList.add(str);
		}
		return retList.toArray(new String[0]);
	}

	public static String[] removeString(String[] strs, String removeStr) {
		List<String> retList = new ArrayList<String>();
		for (String str : strs) {
			if (!StringUtil.isStringEqual(str, removeStr)) {
				continue;
			}
			retList.add(str);
		}
		return retList.toArray(new String[0]);

	}

	/**
	 * 从数组删除某个字符串
	 * 
	 * @param strs
	 * @param removeStr
	 * @return
	 */
	public static String[] getArrayWithOutStr(String[] strs, String removeStr) {
		List<String> retList = new ArrayList<String>();
		for (String str : strs) {
			if (StringUtil.isStringEqual(str, removeStr)) {
				continue;
			}
			retList.add(str);
		}
		return retList.toArray(new String[0]);
	}

	public static String getStrWithInt(int intValue, int length) {
		String str = String.valueOf(intValue);
		if (str.length() >= length) {
			return str.substring(0, length);
		}
		StringBuffer buffer = new StringBuffer(str);
		while (buffer.length() < length) {
			buffer.insert(0, '0');
		}
		return buffer.toString();
	}
}
