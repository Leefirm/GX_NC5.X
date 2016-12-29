package nc.vo.lcsw.sw05.itf;

import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw05.GcpgsjBVO;

public interface IGcpgsj {

	/**
	 * ��д��װ���롢��װ��Ϣ�ı�װ״̬
	 * 
	 * @param pk_bzxx
	 * @param pk_bzsq
	 * @throws Exception
	 */
	public void updateBzxx(GcpgsjBVO[] bvo, String hid) throws Exception;

	/**
	 * ��ѯ��װ��Ϣδ���������
	 * 
	 * @param hvo
	 * @return
	 * @throws Exception
	 */
	public LcswBzxxHVO[] queryBzxx(BzsqVO hvo) throws Exception;

	/**
	 * �����д��װ���롢��װ��Ϣ�ı�װ״̬
	 * 
	 * @param pk_bzxx
	 * @param pk_bzsq
	 * @throws Exception
	 */
	public void updateUnprove(GcpgsjBVO[] bvos, String hid) throws Exception;

}
