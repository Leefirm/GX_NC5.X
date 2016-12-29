package com.ui.lcsw.util;

public class LcswUIUtil {
	private LcswUIUtil(){}
	
	public static String modifySqlWhere(String sqlwhere) {
		return modifySqlWhere(sqlwhere, "bd_jobbasfil.jobcode");
	}
	
	/**
	 * 
	 * 替换字符串全部转成大写再查询
	 * @param sqlwhere
	 * @param jobcode
	 * @return
	 */
	public static String modifySqlWhere(String sqlwhere, String jobcode) {
		if (sqlwhere == null || sqlwhere.indexOf(jobcode) == -1) {
			return sqlwhere;
		} else {
			sqlwhere = sqlwhere + " "; 
			int index1 = sqlwhere.indexOf(jobcode);
			String str1 = sqlwhere.substring(0, index1);
			int index2 = sqlwhere.indexOf("' ", index1);
			String str2 = sqlwhere.substring(index2 + 1, sqlwhere.length());
			String jobstr = sqlwhere.substring(index1, index2 + 1);
			if (jobstr == null) {
				return sqlwhere;
			}

			String[] strings = jobstr.split("=");
			if (strings == null || strings.length < 2) {
				return sqlwhere;
			}

			String newstr = str1 + " " + "upper( " + strings[0] + ") = "
					+ "upper(" + strings[1] + ")" + " " + str2;

			return newstr;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println(modifySqlWhere("isnull(dr,0)=0", "jobcode"));
		System.out.println(modifySqlWhere("jobcode='abc'", "jobcode"));
		System.out.println(modifySqlWhere("1=1 and jobcode='abc'", "jobcode"));
		System.out.println(modifySqlWhere("1=1 and jobcode='abc' and 1=1",
				"jobcode"));
		System.out.println(modifySqlWhere("jobcode='abc' and 1=1", "jobcode"));
		System.out.println(modifySqlWhere("job='abc'", "jobcode"));
	}

}
