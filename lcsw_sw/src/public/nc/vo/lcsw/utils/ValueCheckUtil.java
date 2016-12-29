package nc.vo.lcsw.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：值（任意类型）常用检查、判断工具类</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuzy
 * @time 2010-3-30 下午08:21:25
 */
public class ValueCheckUtil {

	/**
	 * 方法功能描述：一个Boolean值，是否为true
	 * 
	 * @param b
	 * @return
	 */
	public static boolean isTrue(Boolean b) {
		return b == null ? false : b.booleanValue();
	}

	/**
	 * 方法功能描述：一个UFBoolean值，是否为true
	 * 
	 * @param b
	 * @return
	 */
	public static boolean isTrue(UFBoolean b) {
		return b == null ? false : b.booleanValue();
	}

	/**
	 * 方法功能描述：一个UFDouble值，既不等于空也不等于0
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param d
	 * @return <p>
	 * @since 6.0
	 * @author liuzy
	 * @time 2010-3-30 下午08:25:00
	 */
	public static boolean isNotNullNotEqualZERO(UFDouble d) {
		if (null != d && d.compareTo(UFDouble.ZERO_DBL) != 0) {
			return true;
		}
		return false;
	}

	/**
	 * 方法功能描述：如果数组为空或者长度等于0则返回true
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param strs
	 * @return <p>
	 * @since 6.0
	 * @author liuzy
	 * @time 2010-4-1 上午10:39:44
	 */
	public static boolean isNullORZeroLength(Object[] objs) {
		if (null == objs || objs.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 两个数组长度是否相等
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean isLengthEqual(Object[] obj1, Object[] obj2) {
		if (ValueCheckUtil.isNullORZeroLength(obj1)
				&& ValueCheckUtil.isNullORZeroLength(obj2)) {
			return true;
		} else if (!ValueCheckUtil.isNullORZeroLength(obj1)
				&& !ValueCheckUtil.isNullORZeroLength(obj2)
				&& obj1.length == obj2.length) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 方法功能描述：如果List为空或者长度等于0则返回true
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param l
	 * @return <p>
	 * @since 6.0
	 * @author liuzy
	 * @time 2010-4-1 上午10:52:03
	 */
	public static boolean isNullORZeroLength(List<? extends Object> l) {
		if (null == l || l.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 方法功能描述：如果Map为空或者长度等于0则返回true
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param m
	 * @return <p>
	 * @since 6.0
	 * @author liuzy
	 * @time 2010-4-1 上午10:52:48
	 */
	public static boolean isNullORZeroLength(
			Map<? extends Object, ? extends Object> m) {
		if (null == m || m.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 方法功能描述：如果Set为空或者长度等于0则返回true
	 * <p>
	 * 
	 * @author songhy
	 */
	public static boolean isNullORZeroLength(Set<? extends Object> set) {
		if (null == set || set.size() == 0) {
			return true;
		}
		return false;
	}

}
