package nc.bs.lcsw.pub;

import java.util.HashMap;
import java.util.Map;

public class MiddleDataUtil {
	public static final Map<String, String> yhflcMap = new HashMap<String, String>();
	static {
		String[] yhflcNames = new String[] { "中国工商银行", "中国建设银行", "中国农业银行",
				"中国银行", "中国邮政储蓄银行", "交通银行", "广西北部湾银行", "广西农村信用社", "中国光大银行" };
		String[] yhflcValues = new String[] { "001", "098", "1704", "1900",
				"1934", "1937", "401", "751", "804" };
		for (int i = 0; i < yhflcValues.length; i++) {
			yhflcMap.put(yhflcNames[i], yhflcValues[i]);
		}
	}
}
