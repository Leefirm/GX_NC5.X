package nc.vo.lcsw.sw05.itf;

import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw05.GcpgsjBVO;

public interface IGcpgsj {

	/**
	 * 回写报装申请、报装信息的报装状态
	 * 
	 * @param pk_bzxx
	 * @param pk_bzsq
	 * @throws Exception
	 */
	public void updateBzxx(GcpgsjBVO[] bvo, String hid) throws Exception;

	/**
	 * 查询报装信息未付款的数据
	 * 
	 * @param hvo
	 * @return
	 * @throws Exception
	 */
	public LcswBzxxHVO[] queryBzxx(BzsqVO hvo) throws Exception;

	/**
	 * 弃审回写报装申请、报装信息的报装状态
	 * 
	 * @param pk_bzxx
	 * @param pk_bzsq
	 * @throws Exception
	 */
	public void updateUnprove(GcpgsjBVO[] bvos, String hid) throws Exception;

}
