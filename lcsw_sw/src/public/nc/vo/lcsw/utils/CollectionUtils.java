package nc.vo.lcsw.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;

/**
 * <b> JAVA 集合类的一些工具方法 </b>
 * <p>
 * </p>
 * 创建日期:2009-11-09 10:21:14
 * 
 * @author
 * @version NC60
 */

public class CollectionUtils {

	/*
   * 
   */
	public static <T> void addArrayToList(List<T> l, T[] a) {
		if (l == null || a == null || a.length <= 0) {
			return;
		}
		for (T r : a) {
			l.add(r);
		}
	}

	public static <K, V> void addArrayToMap(Map<K, V> m, K[] keys, V[] values) {
		if (m == null || keys == null || keys.length <= 0 || values == null
				|| values.length <= 0 || keys.length != values.length) {
			return;
		}
		for (int i = 0; i < keys.length; i++) {
			m.put(keys[i], values[i]);
		}
	}

	public static <T> void addArrayToSet(Set<T> s, T[] a) {
		List<T> l = new ArrayList<T>();
		CollectionUtils.addArrayToList(l, a);
		s.addAll(l);
	}

	/*
	 * 数组合并
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] combineArrs(T[]... arrs) {
		if (arrs == null) {
			return null;
		}
		int length = 0;
		int count = 0;
		T[] ret = null;
		for (T[] arr : arrs) {
			if (arr == null || arr.length <= 0) {
				continue;
			}
			if (ret == null) {
				ret = arr;
			}
			count++;
			length += arr.length;
		}
		if (length == 0 || count == 1 || ret == null) {
			return ret;
		}
		ret = (T[]) Array.newInstance(ret[0].getClass(), length);
		int ipos = 0;
		for (Object[] arr : arrs) {
			if (arr == null || arr.length <= 0) {
				continue;
			}
			System.arraycopy(arr, 0, ret, ipos, arr.length);
			ipos += arr.length;
		}
		return ret;
	}

	/**
	 * 转换Object数组为具体类型的数组
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param <E>
	 * @param objs
	 *            需要转型的Object数组
	 * @return 转型后类型为E的数组
	 * @throws 如果objs参数数组不是所要转型的类型
	 *             ，将抛转型异常
	 *             <p>
	 *             示例： if (object.getClass().isArray()) { T[] vos =
	 *             CollectionUtils.convertArrayType((Object[])object); }
	 *             其中：T是需要转型后的类型
	 * @since 6.0
	 * @author songhy
	 * @time 2010-7-13 下午09:58:37
	 */
	@SuppressWarnings("unchecked")
	public static <E> E[] convertArrayType(Object[] objs) {
		if (ValueCheckUtil.isNullORZeroLength(objs)) {
			return null;
		}
		E[] convertArray = (E[]) Array.newInstance(objs[0].getClass(),
				objs.length);
		if (convertArray.getClass().isAssignableFrom(objs.getClass())) {
			return (E[]) objs;
		}
		System.arraycopy(objs, 0, convertArray, 0, objs.length);
		return convertArray;
	}

	/**
	 * 方法功能描述：
	 * <p>
	 * 返回不包含NULL的数组 <b>参数说明</b>
	 * 
	 * @param <T>
	 * @param vos
	 * @param tclazz
	 * @return <p>
	 * @since 6.0
	 * @author chennn
	 * @time 2010-5-5 下午06:40:25
	 */
	public static <T> T[] getArrayWithoutNull(T[] vos, Class<T> tclazz) {
		if (vos == null || vos.length == 0) {
			return null;
		}
		List<T> voList = new ArrayList<T>();
		for (T vo : vos) {
			if (vo == null) {
				continue;
			}
			voList.add(vo);
		}
		@SuppressWarnings("unchecked")
		T[] ret = (T[]) Array.newInstance(tclazz, voList.size());
		voList.toArray(ret);
		return ret;
	}


	/*
	 * 将VO数组 转换为Map，其中key 必须是唯一键
	 */
	public static <T extends CircularlyAccessibleValueObject> Map<String, T> hashVOArray(
			String key, T[] vos) {
		if (key == null || vos == null || vos.length <= 0
				|| CollectionUtils.isNullForAllTElements(vos)) {
			return null;
		}
		Map<String, T> hsret = new HashMap<String, T>();
		String keyvalue = null;
		for (T vo : vos) {
			if (vo != null && vo.getAttributeValue(key) != null) {
				keyvalue = (String) vo.getAttributeValue(key);
				hsret.put(keyvalue, vo);
			}
		}
		return hsret;
	}

	

	/*
   * 
   */
	public static int[] integerArrayChange(List<Integer> l) {
		if (l == null || l.size() <= 0) {
			return null;
		}
		int[] ret = new int[l.size()];
		Integer it = null;
		for (int i = 0; i < l.size(); i++) {
			it = l.get(i);
			if (it == null) {
				continue;
			}
			ret[i] = it.intValue();
		}
		return ret;
	}

	/**
	 * 判断List中元素是否全空
	 * 
	 * @param list
	 * @return
	 * @since 6.0
	 */
	public static boolean isNullForAllElements(List<Object> list) {
		for (Object temp : list) {
			if (temp != null) {
				return false;
			}
		}
		return true;

	}

	/**
	 * 判断List中元素是否全空
	 * 
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> boolean isNullForAllTElements(List<T> list) {
		for (Object temp : list) {
			if (temp != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断组织是否全空
	 * 
	 * @param <T>
	 * @param objects
	 * @return
	 */
	public static <T> boolean isNullForAllTElements(T[] objects) {
		if (objects == null || objects.length < 1) {
			return true;
		}
		for (T temp : objects) {
			if (temp != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 将列表转换为数组，列表的长度大于0是，第一个元素必须非空 应用场景：对于一些明确列表元素类型的情况，无需使用该方法，使用jdk
	 * list.toArray即可。 提供这个方法的情况是，对于一些公共算法，列表的元素只能表达为一些父类型，如ICBillVO。
	 * 需要使用该方法把列表转换为实际子类型的数组。
	 */
	@SuppressWarnings("unchecked")
	public static <E> E[] listToArray(List<E> l) {
		if (ValueCheckUtil.isNullORZeroLength(l)) {
			return null;
		}
		E[] ret = (E[]) Array.newInstance(l.get(0).getClass(), l.size());
		return l.toArray(ret);
	}



	/*
	 * 从列表中移除元素
	 */
	public static <T> void removeFromList(List<T> l, T[] a) {
		if (l == null || a == null || a.length <= 0) {
			return;
		}
		for (int i = 0; i < a.length; i++) {
			l.remove(a[i]);
		}
	}

	@SuppressWarnings("unchecked")
	public static <E> E[] setToArray(Set<E> s) {
		if (ValueCheckUtil.isNullORZeroLength(s)) {
			return null;
		}
		Iterator<E> iter = s.iterator();
		if (!iter.hasNext()) {
			return null;
		}
		E v = iter.next();
		E[] ret = (E[]) Array.newInstance(v.getClass(), s.size());
		return s.toArray(ret);
	}

	

}
