package nc.bs.lcsw.sw02;

import nc.bs.dao.BaseDAO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw02.itf.IBzsq;

public class BzsqImpl implements IBzsq {

	/**
	 * 弃审删除对应的报装信息、报装费用
	 * 
	 * @param vo
	 * @throws Exception
	 */
	
	public void updateUnprove(BzsqVO vo) throws Exception {
		BaseDAO dao = new BaseDAO();
		String bzxx_sql = "update lcsw_bzxx bzxx set bzxx.dr = 1 where bzxx.pk_bzsq= '" + vo.getPk_bzsq() + "' ";
		dao.executeUpdate(bzxx_sql);

		String bzfy_h_sql = "update lcsw_bzfy_h h set h.dr= 1 where h.pk_bzsq='" + vo.getPk_bzsq() + "'";
		dao.executeUpdate(bzfy_h_sql);

		String bzfy_b_sql = "update lcsw_bzfy_b b set b.dr=1 where b.pk_bzxx in (select x.pk_bzxx from lcsw_bzxx x where x.pk_bzsq='" + vo.getPk_bzsq() + "')";
		dao.executeUpdate(bzfy_b_sql);
	}
}
