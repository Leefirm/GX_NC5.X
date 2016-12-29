package nc.bs.lcsw.sw05;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.bs.trade.business.HYPubBO;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw05.GcpgsjBVO;
import nc.vo.lcsw.sw05.itf.IGcpgsj;
import nc.vo.pub.VOStatus;
import nc.vo.pub.ValuepkUtils;

public class GcpgsjImpl implements IGcpgsj {

	/**
	 * 回写报装申请、报装信息的报装状态
	 * 
	 * @param pk_bzxx
	 * @param pk_bzsq
	 * @throws Exception
	 */
	public void updateBzxx(GcpgsjBVO[] bvos, String hid) throws Exception {
		BaseDAO dao = new BaseDAO();
		EmunBzzt emunbzzt = new EmunBzzt();
		HYPubBO bo = new HYPubBO();
		BzsqVO[] vo_bzsq = (BzsqVO[]) bo.queryByCondition(BzsqVO.class, " nvl(dr,0)=0  and   exists  (select 1 from  lcsw_gcpgsj_b  where "
				+ GcpgsjBVO.PK_GCPGSJHID + " = '" + hid + "' and nvl(dr,0)=0  and lcsw_bzsq.pk_bzsq=lcsw_gcpgsj_b.pk_bzsq   )  ");

		LcswBzxxHVO[] vo_bzxx_h = (LcswBzxxHVO[]) bo.queryByCondition(LcswBzxxHVO.class, "  nvl(dr,0)=0  and   exists  (select 1 from  lcsw_gcpgsj_b  where "
				+ GcpgsjBVO.PK_GCPGSJHID + " = '" + hid + "' and nvl(dr,0)=0  and lcsw_bzxx.pk_bzxx=lcsw_gcpgsj_b.pk_bzxx  )  ");

		for (BzsqVO vo : vo_bzsq) {
			vo.setBzzt(emunbzzt.PGZ);
			vo.setStatus(VOStatus.UPDATED);
		}

		for (LcswBzxxHVO hvo : vo_bzxx_h) {
			for (GcpgsjBVO bvo : bvos) {
				if (hvo.getPk_bzxx().equals(bvo.getPk_bzxx())) {
					hvo.setSjdw(bvo.getSjdw());
					hvo.setSnsgdw(bvo.getSnsgdw());
					hvo.setSwsgdw(bvo.getSwsgdw());
					hvo.setGc(bvo.getGc());
					hvo.setSbgs(bvo.getSbgs());
					hvo.setSgts(bvo.getSgts());
					hvo.setGcwgyqts(bvo.getWgyqts());
					hvo.setBzzt(emunbzzt.PGZ);
					hvo.setStatus(VOStatus.UPDATED);
				}
			}
		}

		bo.updateAry(vo_bzxx_h);
		bo.updateAry(vo_bzsq);

	}

	/**
	 * 弃审回写报装申请、报装信息的报装状态
	 * 
	 * @param pk_bzxx
	 * @param pk_bzsq
	 * @throws Exception
	 */
	public void updateUnprove(GcpgsjBVO[] bvos, String hid) throws Exception {
		BaseDAO dao = new BaseDAO();
		EmunBzzt emunbzzt = new EmunBzzt();
		HYPubBO bo = new HYPubBO();
		BzsqVO[] vo_bzsq = (BzsqVO[]) bo.queryByCondition(BzsqVO.class, "  nvl(dr,0)=0  and   exists  (select 1 from  lcsw_gcpgsj_b  where "
				+ GcpgsjBVO.PK_GCPGSJHID + " = '" + hid + "' and nvl(dr,0)=0  and lcsw_bzsq.pk_bzsq=lcsw_gcpgsj_b.pk_bzsq   )  ");

		LcswBzxxHVO[] vo_bzxx_h = (LcswBzxxHVO[]) bo.queryByCondition(LcswBzxxHVO.class, "  nvl(dr,0)=0  and   exists  (select 1 from  lcsw_gcpgsj_b  where "
				+ GcpgsjBVO.PK_GCPGSJHID + " = '" + hid + "' and nvl(dr,0)=0  and lcsw_bzxx.pk_bzxx=lcsw_gcpgsj_b.pk_bzxx  )  ");

		for (BzsqVO vo : vo_bzsq) {
			vo.setBzzt(emunbzzt.SJCG);
		}

		for (LcswBzxxHVO hvo : vo_bzxx_h) {
			hvo.setBzzt(emunbzzt.SJCG);
		}

		bo.updateAry(vo_bzxx_h);
		bo.updateAry(vo_bzsq);
	}

	/**
	 * 查询报装信息未付款的数据
	 * 
	 * @param hvo
	 * @return
	 * @throws Exception
	 */
	public LcswBzxxHVO[] queryBzxx(BzsqVO hvo) throws Exception {
		BaseDAO dao = new BaseDAO();
		String sql = "select bzxx.* from LCSW_BZXX bzxx left join LCSW_BZFY_B bzfyb on bzxx.PK_BZXX=bzfyb.PK_BZXX where bzxx.PK_BZSQ='" + hvo.getPk_bzsq()
				+ "' and nvl(bzxx.dr,0)=0 and nvl(bzfyb.dr,0)=0 and bzfyb.PK_COSTSUBJ in ('" + ValuepkUtils.BJKCF + "','" + ValuepkUtils.YSGCK1Q + "','" + ValuepkUtils.YSGCK2Q
				+ "','" + ValuepkUtils.YSGCK3Q + "') and nvl(bzfyb.XZBZ,'N') = 'N' and bzxx.PK_BZXX in (select pk_bzxx from lcsw_sjfksj_b where PK_JOBBASFIL='"
				+ hvo.getPk_jobbasfil() + "' and nvl(dr,0)=0)";
		ArrayList<LcswBzxxHVO> hvos = (ArrayList<LcswBzxxHVO>) dao.executeQuery(sql, new BeanListProcessor(LcswBzxxHVO.class));
		return hvos.toArray(new LcswBzxxHVO[hvos.size()]);
	}

}
