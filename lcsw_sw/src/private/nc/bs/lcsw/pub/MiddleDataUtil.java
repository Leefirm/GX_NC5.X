package nc.bs.lcsw.pub;

import java.util.HashMap;
import java.util.Map;

public class MiddleDataUtil {
	public static final Map<String, String> yhflcMap = new HashMap<String, String>();
	static {
		String[] yhflcNames = new String[] { "�й���������", "�й���������", "�й�ũҵ����",
				"�й�����", "�й�������������", "��ͨ����", "��������������", "����ũ��������", "�й��������" };
		String[] yhflcValues = new String[] { "001", "098", "1704", "1900",
				"1934", "1937", "401", "751", "804" };
		for (int i = 0; i < yhflcValues.length; i++) {
			yhflcMap.put(yhflcNames[i], yhflcValues[i]);
		}
	}
}
