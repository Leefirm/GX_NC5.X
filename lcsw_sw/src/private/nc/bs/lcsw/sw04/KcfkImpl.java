package nc.bs.lcsw.sw04;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.trade.business.HYPubBO;
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw04.KcfksjAggVO;
import nc.vo.lcsw.sw04.KcfksjBVO;
import nc.vo.lcsw.sw04.KcfksjHVO;
import nc.vo.lcsw.sw04.itf.IKcfk;

public class KcfkImpl implements IKcfk {

	/**
	 * 回写报装申请、报装信息的报装状态
	 * 
	 * @param pk_bzxx
	 * @param pk_bzsq
	 * @throws Exception
	 */
	public void updateBzxx(KcfksjBVO[] bvos, String hid) throws Exception {
		BaseDAO dao = new BaseDAO();
		EmunBzzt emunbzzt = new EmunBzzt();
		HYPubBO bo = new HYPubBO();
		BzsqVO[] vo_bzsq = (BzsqVO[]) bo.queryByCondition(BzsqVO.class, " nvl(dr,0)=0 and exists (select 1 from lcsw_kcfksj_b where " + KcfksjBVO.PK_KCFKSJHID
				+ " = '" + hid + "' and nvl(dr,0)=0   and lcsw_bzsq.pk_bzsq=lcsw_kcfksj_b.pk_bzsq  ) ");

		LcswBzxxHVO[] vo_bzxx_h = (LcswBzxxHVO[]) bo.queryByCondition(LcswBzxxHVO.class, " nvl(dr,0)=0 and exists ( select 1 from lcsw_kcfksj_b where "
				+ KcfksjBVO.PK_KCFKSJHID + " = '" + hid + "' and nvl(dr,0)=0  and lcsw_bzxx.pk_bzxx=lcsw_kcfksj_b.pk_bzxx )  ");

		for (LcswBzxxHVO hvo : vo_bzxx_h) {
			for (KcfksjBVO bvo : bvos) {
				if (hvo.getPk_bzxx().equals(bvo.getPk_bzxx())) {
					hvo.setBzzt(emunbzzt.KCCG);
					hvo.setKcry(bvo.getKcry());
					hvo.setKcsjry(bvo.getKcsjry());
					hvo.setSjpswcrq(bvo.getSjpswcrq());
					hvo.setYjhhxrq(bvo.getYjhhxrq());
					hvo.setYjsjpsrq(bvo.getYjsjpsrq());
					hvo.setYjsjrq(bvo.getYjsjrq());
					hvo.setKcrq(bvo.getKcrq());
					hvo.setKcfkrq(bvo.getKcfkrq());
					hvo.setJhxtrq(bvo.getJhxtrq());
					hvo.setKcwgyqts(bvo.getWgyqts());
					hvo.setYqxgr(bvo.getYqxgr());
					hvo.setYqxgrq(bvo.getYqxgrq());
					hvo.setKcjbr(bvo.getJbry());

				}
			}
		}

		for (BzsqVO vo : vo_bzsq) {
			vo.setBzzt(emunbzzt.KCCG);
		}

		bo.updateAry(vo_bzsq);
		bo.updateAry(vo_bzxx_h);

	}

	/**
	 * 弃审回写报装申请、报装信息的报装状态
	 * 
	 * @param bvo
	 * @throws Exception
	 */
	public void updateUnprove(KcfksjBVO[] bvos, String hid) throws Exception {
		BaseDAO dao = new BaseDAO();
		EmunBzzt emunbzzt = new EmunBzzt();
		HYPubBO bo = new HYPubBO();
		BzsqVO[] vo_bzsq = (BzsqVO[]) bo.queryByCondition(BzsqVO.class, " nvl(dr,0)=0 and exists (select 1 from lcsw_kcfksj_b where " + KcfksjBVO.PK_KCFKSJHID
				+ " = '" + hid + "' and nvl(dr,0)=0   and lcsw_bzsq.pk_bzsq=lcsw_kcfksj_b.pk_bzsq  ) ");

		LcswBzxxHVO[] vo_bzxx_h = (LcswBzxxHVO[]) bo.queryByCondition(LcswBzxxHVO.class, " nvl(dr,0)=0 and exists ( select 1 from lcsw_kcfksj_b where "
				+ KcfksjBVO.PK_KCFKSJHID + " = '" + hid + "' and nvl(dr,0)=0  and lcsw_bzxx.pk_bzxx=lcsw_kcfksj_b.pk_bzxx )  ");

		for (LcswBzxxHVO hvo : vo_bzxx_h) {
			hvo.setBzzt(emunbzzt.KCPG);
		}

		for (BzsqVO vo : vo_bzsq) {
			vo.setBzzt(emunbzzt.KCPG);
		}

		bo.updateAry(vo_bzsq);
		bo.updateAry(vo_bzxx_h);
	}
}
