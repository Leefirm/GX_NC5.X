package nc.vo.lcsw.utils;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class BaseTypeUtils {

	/**
	 * @since 6.0
	 */
	public static UFDouble abs(UFDouble d) {
		if (d == null) {
			return d;
		}
		return d.abs();
	}

	/**
	 * @return nc.vo.pub.lang.UFDouble
	 * @param key
	 *            java.lang.String
	 */
	public static UFDouble add(UFDouble... values) {
		if (values == null || values.length <= 0) {
			return null;
		}
		UFDouble ret = UFDouble.ZERO_DBL;
		for (UFDouble value : values) {
			if (value != null) {
				ret = ret.add(value);
			}
		}
		return ret;
	}

	

	/**
	 * @return nc.vo.pub.lang.UFDouble
	 * @param key
	 *            java.lang.String
	 */
	public static UFDouble div(UFDouble lvalue, UFDouble... rvalues) {
		if (lvalue == null || rvalues == null || rvalues.length <= 0) {
			return null;
		}
		UFDouble dret = null;
		for (UFDouble rvalue : rvalues) {
			if (rvalue == null) {
				return null;
			}
			if (rvalue.compareTo(UFDouble.ZERO_DBL) != 0) {
				dret = lvalue.div(rvalue);
			}
		}
		return dret;
	}

	/**
	 * UFDouble相除，如果两个数有null 返回 默认值
	 * 
	 * @param dvalue
	 * @param lvalue
	 * @param rvalue
	 * @return
	 * @since 6.0
	 */
	public static UFDouble divDefault(UFDouble dvalue, UFDouble lvalue,
			UFDouble... rvalue) {
		UFDouble dret = BaseTypeUtils.div(lvalue, rvalue);
		return dret == null ? dvalue : dret;
	}

	/**
	 * 方法功能描述：
	 * <p>
	 * 取绝对值 <b>参数说明</b>
	 * 
	 * @param num
	 * @return <p>
	 * @since 6.0
	 * @author chennn
	 * @time 2010-5-14 上午09:15:37
	 */
	public static UFDouble getAbsoluteValue(UFDouble num) {
		if (num == null) {
			return null;
		}
		return num.abs();
	}

	/**
	 * 创建日期：(2004-3-17 22:46:09)
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 * @param vos
	 *            nc.vo.pub.CircularlyAccessibleValueObject[]
	 * @param key
	 *            java.lang.String
	 */
	public static UFDouble getDigitUFDouble(UFDouble d, int digit) {

		if (d == null) {
			return null;
		}
		return d.setScale(0 - digit, UFDouble.ROUND_HALF_UP);

	}

	/**
	 * 判断两个数值是否相等,如果都为0或者null或者值相等，返回true
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static boolean isBothNullZeroOrEqual(UFDouble num1, UFDouble num2) {
		if (BaseTypeUtils.isNullOrZero(num1)
				&& BaseTypeUtils.isNullOrZero(num2)) {
			return true;
		}

		if (BaseTypeUtils.isNullOrZero(num1)
				|| BaseTypeUtils.isNullOrZero(num2)) {
			return false;
		}

		return BaseTypeUtils.isEquals(num1, num2);
	}

	

	/**
	 * 判断UFDouble 是否相等
	 * 
	 * @param num
	 * @return
	 * @since 6.0
	 */
	public static boolean isEquals(UFDouble d1, UFDouble d2) {
		if (d1 == null) {
			if (d2 != null) {
				return false;
			}
			return true;
		}
		if (d2 == null) {
			return false;
		}
		return d1.equals(d2);
	}

	/**
	 * 判断UFDouble 是否大于
	 * 
	 * @param num
	 * @return
	 * @since 6.0
	 */
	public static boolean isGt(UFDouble d1, UFDouble d2) {
		UFDouble d11 = d1 == null ? UFDouble.ZERO_DBL : d1;
		UFDouble d22 = d2 == null ? UFDouble.ZERO_DBL : d2;
		return d11.compareTo(d22) > 0;
	}

	/**
	 * 判断UFDouble 是否是大于等于0
	 * 
	 * @param num
	 * @return
	 * @since 6.0
	 */
	public static boolean isGtZero(UFDouble num) {

		if (num != null && num.compareTo(UFDouble.ZERO_DBL) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断UFDouble 是否是小于等于0
	 * 
	 * @param num
	 * @return
	 * @since 6.0
	 */
	public static boolean isLEZero(UFDouble num) {

		if (num == null || num.compareTo(UFDouble.ZERO_DBL) <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断UFDouble 是否是小于等于0
	 * 
	 * @param num
	 * @return
	 * @since 6.0
	 */
	public static boolean isLtZero(UFDouble num) {

		if (num != null && num.compareTo(UFDouble.ZERO_DBL) < 0) {
			return true;
		}
		return false;
	}

	/**
	 * @
	 */
	public static boolean isNull(Object value) {
		if (value == null || value.toString().trim().length() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断UFDouble 是否是Null或0
	 * 
	 * @param num
	 * @return
	 * @since 6.0
	 */
	public static boolean isNullOrZero(UFDouble num) {

		if (num == null || num.compareTo(UFDouble.ZERO_DBL) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @since 6.0
	 */
	public static UFDouble min(UFDouble num1, UFDouble num2) {

		if (num1 == null) {
			return num2;
		} else if (num2 == null) {
			return num1;
		}
		if (num1.compareTo(num2) > 0) {
			return num2;
		}
		return num1;
	}

	/**
	 * @return nc.vo.pub.lang.UFDouble
	 * @param key
	 *            java.lang.String
	 */
	public static UFDouble mult(UFDouble... values) {
		if (values == null || values.length <= 0) {
			return null;
		}
		UFDouble ret = null;
		for (UFDouble value : values) {
			if (value == null) {
				return null;
			}
			if (ret == null) {
				ret = value;
				continue;
			}
			ret = ret.multiply(value);
		}
		return ret;
	}

	/**
	 * 两数UFDouble相乘，如果两个数有null 返回 默认值
	 * 
	 * @param dvalue
	 * @param values
	 * @return
	 * @since 6.0
	 */
	public static UFDouble multDefault(UFDouble dvalue, UFDouble... values) {
		UFDouble ret = BaseTypeUtils.mult(values);
		return ret == null ? dvalue : ret;
	}

	/**
	 * 对UFDouble 取反
	 * 
	 * @param num
	 * @return
	 * @since 6.0
	 */
	public static UFDouble negUFDouble(UFDouble num) {
		if (num == null) {
			return num;
		}
		return UFDouble.ZERO_DBL.sub(num);
	}

	/**
	 * 如果为空，则当作0 否则返回d
	 * 
	 * @since 6.0
	 */
	public static UFDouble nullAsZero(UFDouble d) {
		return d == null ? UFDouble.ZERO_DBL : d;
	}

	/**
	 * @return nc.vo.pub.lang.UFDouble
	 * @param key
	 *            java.lang.String
	 */
	public static UFDouble sub(UFDouble lvalue, UFDouble... rvalues) {
		if (rvalues == null || rvalues.length <= 0) {
			return lvalue;
		}
		UFDouble ret = lvalue;
		if (lvalue == null) {
			ret = UFDouble.ZERO_DBL;
		}
		for (UFDouble rvalue : rvalues) {
			if (rvalue != null) {
				ret = ret.sub(rvalue);
			}
		}
		return ret;
	}


}

