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
 * <b> JAVA �������һЩ���߷��� </b>
 * <p>
 * </p>
 * ��������:2009-11-09 10:21:14
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
	 * ����ϲ�
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
	 * ת��Object����Ϊ�������͵�����
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param <E>
	 * @param objs
	 *            ��Ҫת�͵�Object����
	 * @return ת�ͺ�����ΪE������
	 * @throws ���objs�������鲻����Ҫת�͵�����
	 *             ������ת���쳣
	 *             <p>
	 *             ʾ���� if (object.getClass().isArray()) { T[] vos =
	 *             CollectionUtils.convertArrayType((Object[])object); }
	 *             ���У�T����Ҫת�ͺ������
	 * @since 6.0
	 * @author songhy
	 * @time 2010-7-13 ����09:58:37
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
	 * ��������������
	 * <p>
	 * ���ز�����NULL������ <b>����˵��</b>
	 * 
	 * @param <T>
	 * @param vos
	 * @param tclazz
	 * @return <p>
	 * @since 6.0
	 * @author chennn
	 * @time 2010-5-5 ����06:40:25
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
	 * ��VO���� ת��ΪMap������key ������Ψһ��
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
	 * �ж�List��Ԫ���Ƿ�ȫ��
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
	 * �ж�List��Ԫ���Ƿ�ȫ��
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
	 * �ж���֯�Ƿ�ȫ��
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
	 * ���б�ת��Ϊ���飬�б�ĳ��ȴ���0�ǣ���һ��Ԫ�ر���ǿ� Ӧ�ó���������һЩ��ȷ�б�Ԫ�����͵����������ʹ�ø÷�����ʹ��jdk
	 * list.toArray���ɡ� �ṩ�������������ǣ�����һЩ�����㷨���б��Ԫ��ֻ�ܱ��ΪһЩ�����ͣ���ICBillVO��
	 * ��Ҫʹ�ø÷������б�ת��Ϊʵ�������͵����顣
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
	 * ���б����Ƴ�Ԫ��
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
