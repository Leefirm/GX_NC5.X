package nc.bs.lcsw.sw03;

import java.util.ArrayList;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.trade.business.HYPubBO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw03.KcpgBVO;
import nc.vo.lcsw.sw03.itf.IKcpg;
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.pub.VOStatus;
import nc.vo.pub.ValuepkUtils;

public class KcpgImpl implements IKcpg {

	/**
	 * 回写报装申请、报装信息的报装状态
	 * 
	 * @param pk_bzxx
	 * @param pk_bzsq
	 * @throws Exception
	 */
	public void updateBzxx(KcpgBVO[] bvos, String hid) throws Exception {
		HYPubBO bo = new HYPubBO();
		EmunBzzt emunbzzt = new EmunBzzt();

		BzsqVO[] vo_bzsq = (BzsqVO[]) bo.queryByCondition(BzsqVO.class, " nvl(dr,0)=0  and  exists (select 1 from lcsw_kcpg_b where " + KcpgBVO.PK_KCPGHID
				+ " = '" + hid + "' and nvl(dr,0)=0  and lcsw_bzsq.pk_bzsq=lcsw_kcpg_b.pk_bzsq ) ");

		LcswBzxxHVO[] vo_bzxx_h = (LcswBzxxHVO[]) bo.queryByCondition(LcswBzxxHVO.class, "  nvl(dr,0)=0  and  exists(select 1 from lcsw_kcpg_b where "
				+ KcpgBVO.PK_KCPGHID + " = '" + hid + "' and nvl(dr,0)=0  and lcsw_bzxx.pk_bzxx=lcsw_kcpg_b.pk_bzxx  ) ");

		for (LcswBzxxHVO hvo : vo_bzxx_h) {
			for (KcpgBVO bvo : bvos) {
				if (hvo.getPk_bzxx().equals(bvo.getPk_bzxx())) {
					hvo.setBzzt(emunbzzt.KCPG);
					hvo.setKcpgry(bvo.getKcpgry());
					hvo.setKcpgrq(bvo.getKcpgrq());
					hvo.setStatus(VOStatus.UPDATED);
				}
			}
		}

		for (BzsqVO bzsqVO : vo_bzsq) {
			bzsqVO.setBzzt(emunbzzt.KCPG);
			bzsqVO.setStatus(VOStatus.UPDATED);
		}

		bo.updateAry(vo_bzxx_h);
		bo.updateAry(vo_bzsq);

	}

	/**
	 * 弃审反写报装申请、报装信息的报装状态
	 * 
	 * @param pk_bzxx
	 * @param pk_bzsq
	 * @throws Exception
	 */
	public void updateUnprove(KcpgBVO[] bvos, String hid) throws Exception {
		HYPubBO bo = new HYPubBO();
		EmunBzzt emunbzzt = new EmunBzzt();

		BzsqVO[] vo_bzsq = (BzsqVO[]) bo.queryByCondition(BzsqVO.class, " nvl(dr,0)=0  and  exists (select 1 from lcsw_kcpg_b where " + KcpgBVO.PK_KCPGHID
				+ " = '" + hid + "' and nvl(dr,0)=0  and lcsw_bzsq.pk_bzsq=lcsw_kcpg_b.pk_bzsq ) ");

		LcswBzxxHVO[] vo_bzxx_h = (LcswBzxxHVO[]) bo.queryByCondition(LcswBzxxHVO.class, "  nvl(dr,0)=0  and  exists(select 1 from lcsw_kcpg_b where "
				+ KcpgBVO.PK_KCPGHID + " = '" + hid + "' and nvl(dr,0)=0  and lcsw_bzxx.pk_bzxx=lcsw_kcpg_b.pk_bzxx  ) ");

		for (LcswBzxxHVO hvo : vo_bzxx_h) {
			hvo.setBzzt(emunbzzt.TJSQ);
			hvo.setStatus(VOStatus.UPDATED);
		}

		for (BzsqVO bzsqVO : vo_bzsq) {
			bzsqVO.setBzzt(emunbzzt.TJSQ);
			bzsqVO.setStatus(VOStatus.UPDATED);
		}

		bo.updateAry(vo_bzxx_h);
		bo.updateAry(vo_bzsq);
	}

	/**
	 * 查询未交勘察费的单据
	 * 
	 * @param pk_bzsq
	 * @return
	 * @throws Exception
	 */

	public String queryPayBill(String pk_bzsq) throws Exception {
		BaseDAO dao = new BaseDAO();
		String sql = "select bzxx.bzbh from LCSW_BZFY_B bzfyb left join LCSW_BZXX bzxx on bzfyb.PK_BZXX=bzxx.PK_BZXX left join LCSW_BZSQ bzsq on bzxx.PK_BZSQ=bzsq.PK_BZSQ where nvl(bzfyb.XZBZ,'N')='N' and bzsq.PK_BZSQ='"
				+ pk_bzsq + "'  and bzfyb.pk_costsubj='" + ValuepkUtils.KCSJF + "'  and nvl(bzfyb.dr,0)=0 and nvl(bzxx.dr,0)=0 and nvl(bzsq.dr,0)=0   ";
		ArrayList list = (ArrayList) dao.executeQuery(sql, new ArrayListProcessor());
		String code = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				if (obj != null && obj.length > 0) {
					if (i == 0) {
						code = " [" + obj[0].toString() + "] ";
					} else {
						code = code + " , [" + obj[0].toString() + "] ";
					}
				}
			}
		}
		return code;
	}

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
	public void updateTabSource(String tabname, String tabId, String tabIdValue, Map<Integer, String> field, Map<Integer, String> fieldValue, int fieldCount)
			throws Exception {
		BaseDAO dao = new BaseDAO();
		// 拼装批量UPDATE SQL
		StringBuffer sb = new StringBuffer(" update " + tabname + " set ");
		for (int i = 0; i < fieldCount; i++) {
			if (i == 0) {
				sb.append(" " + field.get(i).toString() + " = '" + fieldValue.get(i).toString() + "' ");
			} else {
				sb.append(" , " + field.get(i).toString() + " = '" + fieldValue.get(i).toString() + "' ");
			}
		}
		sb.append(" where " + tabId + " = '" + tabIdValue + "' ");

		dao.executeUpdate(sb.toString());
	}
}
