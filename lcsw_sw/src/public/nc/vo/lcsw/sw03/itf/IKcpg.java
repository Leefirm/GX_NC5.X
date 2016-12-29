package nc.vo.lcsw.sw03.itf;

import java.util.Map;

import nc.vo.lcsw.sw03.KcpgBVO;

public interface IKcpg {

	/**
	 * ��д��װ���롢��װ��Ϣ�ı�װ״̬
	 * 
	 * @param pk_bzxx
	 * @param pk_bzsq
	 * @throws Exception
	 */
	public void updateBzxx(KcpgBVO[] bvos, String hid) throws Exception;

	/**
	 * ��ѯδ������ѵĵ���
	 * 
	 * @param pk_bzsq
	 * @return
	 * @throws Exception
	 */
	public String queryPayBill(String pk_bzsq) throws Exception;

	/**
	 * ����д��װ���롢��װ��Ϣ�ı�װ״̬
	 * 
	 * @param pk_bzxx
	 * @param pk_bzsq
	 * @throws Exception
	 */
	public void updateUnprove(KcpgBVO[] bvos, String hid) throws Exception;

	/**
	 * ִ�����ݿ����¶���ֶ�
	 * 
	 * @param tabname
	 *            ����
	 * @param tabId
	 *            ������
	 * @param tabIdValue
	 *            ������ֵ
	 * @param field
	 *            �ֶ�
	 * @param fieldValue
	 *            �ֶ�ֵ
	 * @param fieldCount
	 *            �ֶ�����
	 * @throws Exception
	 */
	// public void updateTabSource(String tabname, String tabId, String
	// tabIdValue, Map<Integer, String> field, Map<Integer, String> fieldValue,
	// int fieldCount)
	// throws Exception;

}
