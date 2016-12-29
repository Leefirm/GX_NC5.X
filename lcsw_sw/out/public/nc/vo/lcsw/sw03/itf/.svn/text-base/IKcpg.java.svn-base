package nc.vo.lcsw.sw03.itf;

import java.util.Map;

import nc.vo.lcsw.sw03.KcpgBVO;

public interface IKcpg {

	/**
	 * 回写报装申请、报装信息的报装状态
	 * 
	 * @param pk_bzxx
	 * @param pk_bzsq
	 * @throws Exception
	 */
	public void updateBzxx(KcpgBVO[] bvos, String hid) throws Exception;

	/**
	 * 查询未交勘察费的单据
	 * 
	 * @param pk_bzsq
	 * @return
	 * @throws Exception
	 */
	public String queryPayBill(String pk_bzsq) throws Exception;

	/**
	 * 弃审反写报装申请、报装信息的报装状态
	 * 
	 * @param pk_bzxx
	 * @param pk_bzsq
	 * @throws Exception
	 */
	public void updateUnprove(KcpgBVO[] bvos, String hid) throws Exception;

	/**
	 * 执行数据库表更新多个字段
	 * 
	 * @param tabname
	 *            表名
	 * @param tabId
	 *            表主键
	 * @param tabIdValue
	 *            表主键值
	 * @param field
	 *            字段
	 * @param fieldValue
	 *            字段值
	 * @param fieldCount
	 *            字段数量
	 * @throws Exception
	 */
	// public void updateTabSource(String tabname, String tabId, String
	// tabIdValue, Map<Integer, String> field, Map<Integer, String> fieldValue,
	// int fieldCount)
	// throws Exception;

}
