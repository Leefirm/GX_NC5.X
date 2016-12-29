/**    Create By NCPlugin beta 0.1.   **/
//insert into pub_busiclass (ACTIONTYPE, CLASSNAME, DR, ISBEFORE, PK_BILLTYPE, PK_BUSICLASS, PK_BUSINESSTYPE, PK_CORP, TS)
//values ('APPROVE', 'N_SW02_APPROVE', 0, 'N', 'SW02', '140804113943003NHR82', NULL, '0001', '2014-08-04 11:39:43')
//go

//insert into pub_billaction (ACTIONNOTE, ACTIONSTYLE, ACTIONSTYLEREMARK, ACTIONTYPE, CONSTRICTFLAG, CONTROLFLAG, DR, FINISHFLAG, PK_BILLACTION, PK_BILLTYPE, SHOWHINT, TS)
//values ('审批', NULL, NULL, 'APPROVE', 'N', 'Y', 0, 'Y', '140804113943103JM4XU', 'SW02', NULL, '2014-08-04 11:39:43')
//go

package nc.bs.pub.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.trade.business.HYPubBO;
import nc.hr.utils.PubEnv;
import nc.itf.uap.bd.job.IJobbasedoc;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.bd.CorpVO;
import nc.vo.bd.b38.JobbasfilVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw10.BzfyBVO;
import nc.vo.lcsw.sw10.BzfyHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.ValidationException;
import nc.vo.pub.ValuepkUtils;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：单据:<b><code>SW02</code></b> 的审批 <tt>APPROVE</tt> 动态执行类。
 * 
 * @since 创建日期：2014-08-04 11:39:43
 * @version UAP 5.x
 * @author Administrator
 * @author NC Plugin
 */
public class N_SW02_APPROVE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW02_APPROVE 构造子注解。
	 */
	public N_SW02_APPROVE() {
		super();
	}

	/*
	 * 备注：平台编写规则类 接口执行类
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {

			super.m_tmpVo = vo;
			// ****该组件为单动作工作流处理开始...不能进行修改****
			Object m_sysflowObj = procActionFlow(vo);
			if (m_sysflowObj != null) {
				return m_sysflowObj;
			}
			// 审核通过回写项目档案
			PushJobbasfil((BzsqVO) vo.m_preValueVo.getParentVO());

			// 审核通过后回写报装信息，数量为用户数
			PushBzxxbyyhs((BzsqVO) vo.m_preValueVo.getParentVO());

			// ****该组件为单动作工作流处理结束...不能进行修改****
			Object retObj = runClass("nc.bs.trade.comstatus.BillApprove",
					"approveBill", "nc.vo.pub.AggregatedValueObject:01", vo,
					m_keyHas, m_methodReturnHas);
			return retObj;
		} catch (Exception ex) {
			Logger.error("", ex);
			if (ex instanceof BusinessException)
				throw (BusinessException) ex;
			else
				throw new PFBusinessException(ex.getMessage(), ex);
		}
	}

	/**
	 * 生成报装信息
	 * 
	 * @param vo
	 */
	private void PushBzxxbyyhs(BzsqVO vo) {
		int yhs = vo.getYshs();
		LcswBzxxHVO[] bzxxhvos = new LcswBzxxHVO[yhs];
		try {
			for (int i = 0; i < yhs; i++) {
				LcswBzxxHVO hvo = new LcswBzxxHVO();
				// 生成单据号
				nc.bs.pub.billcodemanage.BillcodeGenerater gene = new nc.bs.pub.billcodemanage.BillcodeGenerater();
				String billno = gene.getBillCode("SW01", vo.getPk_corp(), null,
						null);
				hvo.setBzbh(billno);
				hvo.setSlbh(vo.getSlbh());
				hvo.setXybh(vo.getXybh());
				hvo.setRhbh(vo.getRhbh());
				hvo.setHm(vo.getHm());
				hvo.setBzzt(vo.getBzzt());
				hvo.setBzrq(vo.getBzrq());
				hvo.setGtcz(vo.getGtcz());
				hvo.setEqgcbz(vo.getEqgcbz());
				hvo.setLxdz(vo.getLxdz());
				hvo.setLxr(vo.getLxr());
				hvo.setLxdd(vo.getLxdd());
				hvo.setAzdz(vo.getAzdz());
				hvo.setYsxz(vo.getYsxz());
				hvo.setGcxz(vo.getGcxz());
				hvo.setYgj(vo.getYgj());
				hvo.setSwkj(vo.getSwkj());
				hvo.setSnkj(vo.getSnkj());
				hvo.setSwgj(vo.getSwgj());
				hvo.setSngj(vo.getSngj());
				hvo.setYshs(vo.getYshs());
				hvo.setZgcs(vo.getZgcs());
				hvo.setJzmj(vo.getJzmj());
				hvo.setYsrs(vo.getYsrs());
				hvo.setZdsl(vo.getZdsl());
				if (vo.getKcsjf() != null) {
					hvo.setKcsjf(new UFDouble(vo.getKcsjf().getDouble() % yhs));
				}
				hvo.setJbry(vo.getJbry());
				hvo.setJmlx(vo.getJmlx());
				hvo.setZlxfs(vo.getZlxfs());
				hvo.setJyskqs(vo.getJyskqs());
				hvo.setSjbs(vo.getSjbs());
				hvo.setFkfs(vo.getFkfs());
				hvo.setKhyh(vo.getKhyh());
				hvo.setYhzh(vo.getYhzh());
				hvo.setPk_jobbasfil(vo.getPk_jobbasfil());
				hvo.setKcxk(vo.getKcxk());
				hvo.setYshs(1);
				hvo.setPk_corp(vo.getPk_corp());
				hvo.setPk_billtype("SW01");
				hvo.setPk_bzsq(vo.getPk_bzsq());
				hvo.setBzzt(EmunBzzt.TJSQ);
				hvo.setDr(0);
				hvo.setDbilldate(PubEnv.getServerDate());

				hvo.setYyqy(vo.getYyqy());// 营业区域
				hvo.setYyzzh(vo.getYyzzh());// 营业执照号
				hvo.setSfsyx(vo.getSfsyx());// 是否三优先
				hvo.setGtcz(vo.getGtcz());// 是否共同出资
				hvo.setJbry(vo.getJbry());// 经办人员
				hvo.setSfzh(vo.getSfzh());// 身份证号
				hvo.setRemark(vo.getRemark());// 备注

				bzxxhvos[i] = hvo;

			}

			HYPubBO bo = new HYPubBO();
			String[] insertpks = bo.insertAry(bzxxhvos);

			// 生成报装费用
			BzfyHVO fyvo = new BzfyHVO();
			fyvo.setPk_bzsq(vo.getPk_bzsq());
			fyvo.setKcsjf(vo.getKcsjf());
			fyvo.setDbilldate(PubEnv.getServerDate());
			fyvo.setKhyh(vo.getKhyh());
			fyvo.setYhzh(vo.getYhzh());
			fyvo.setFkfs(vo.getFkfs());
			fyvo.setJyskqs(vo.getJyskqs());
			fyvo.setPk_corp(vo.getPk_corp());
			fyvo.setPk_billtype("SW10");
			fyvo.setVbillstatus(8);
			fyvo.setDr(0);
			fyvo.setDmakedate(PubEnv.getServerDate());
			fyvo.setVoperatorid(PubEnv.getPk_user());

			fyvo.setSlbh(vo.getSlbh());// 受理编号

			fyvo.setGcmc(vo.getPk_jobbasfil());// 项目PK

			// add by crf 2015-06-17
			fyvo.setSfsyx(vo.getSfsyx());
			fyvo.setGtcz(vo.getGtcz());
			fyvo.setYshs(vo.getYshs());
			// ------end------------
			// 生成单据号
			nc.bs.pub.billcodemanage.BillcodeGenerater gene = new nc.bs.pub.billcodemanage.BillcodeGenerater();
			String billno = gene.getBillCode("SW10", vo.getPk_corp(), null,
					null);
			fyvo.setVbillcode(billno);

			String bzfypk = bo.insert(fyvo);
			// 生成报装费用表体
			List<BzfyBVO> list = new ArrayList<BzfyBVO>();
			// 勘察设计费
			if (vo.getKcsjf() != null && vo.getKcsjf().doubleValue() != 0) {
				// 金额平均
				Double yushu = vo.getKcsjf().getDouble() % yhs;
				Double pingjun = (vo.getKcsjf().getDouble() - yushu) / yhs;
				int index = 1;
				for (String pk_bzxx : insertpks) {
					BzfyBVO fybvo = new BzfyBVO();
					fybvo.setPk_bzxx(pk_bzxx);
					fybvo.setPk_bzfy_h(bzfypk);
					fybvo.setPk_costsubj(ValuepkUtils.KCSJF);
					fybvo.setTfrq(PubEnv.getServerDate());
					fybvo.setPk_balatype(vo.getFkfs());

					// modify by crf 2015-08-03 通过自定义项1,保存原有金额
					fybvo.setReserve1(vo.getFkfs());// 付款方式--原始
					fybvo.setReserve2(PubEnv.getServerDate().toString());// 销账日期--原始
					if (index == insertpks.length) {
						UFDouble fy = new UFDouble(pingjun + yushu);
						fybvo.setAmount(fy);
						fybvo.setReserve3(fy.toString());// 销账费用--原始
					} else {
						UFDouble fy = new UFDouble(pingjun);
						fybvo.setAmount(new UFDouble(pingjun));
					}
					index++;
					list.add(fybvo);
				}
			}
			// 交预算款期数
			// if (vo.getJyskqs() != null && vo.getJyskqs().doubleValue() != 0)
			// {
			// // 金额平均
			// int yushu = vo.getJyskqs() % yhs;
			// int pingjun = (vo.getJyskqs() - yushu) / yhs;
			// int index = 1;
			// for (String pk_bzxx : insertpks) {
			// BzfyBVO fybvo = new BzfyBVO();
			// fybvo.setPk_bzxx(pk_bzxx);
			// fybvo.setPk_bzfy_h(bzfypk);
			// fybvo.setPk_costsubj(ValuepkUtils.JYSKQS);
			// fybvo.setTfrq(PubEnv.getServerDate());
			// fybvo.setPk_balatype(vo.getFkfs());
			// if (index == insertpks.length)
			// fybvo.setAmount(new UFDouble(pingjun + yushu));
			// else
			// fybvo.setAmount(new UFDouble(pingjun));
			// index++;
			// list.add(fybvo);
			// }
			// }
			bo.insertAry(list.toArray(new BzfyBVO[list.size()]));

		} catch (ValidationException e) {
			new BusinessException(e.getMessage());
		} catch (BusinessException e) {
			new BusinessException(e.getMessage());
		}

	}

	/**
	 * 生成项目档案
	 * 
	 * @param vo
	 * @throws BusinessException 
	 */
	private void PushJobbasfil(BzsqVO vo) throws BusinessException {
		if (vo.getPk_jobbasfil() == null) {
			// IJobbasedoc iJob = NCLocator.getInstance().lookup(
			// IJobbasedoc.class);
			// String jobname = vo.getAzdz() + vo.getHm() + "DN"
			// + vo.getBiaojing() + vo.getGcxz() + "工程";
			// JobbasfilVO jvo = new JobbasfilVO();
			// jvo.setPk_corp("0001");
			// jvo.setJobname(jobname);
			// // jvo.setJobcode(getMaxJobcode("a"));
			// jvo.setJobcode(getMaxJobcode("0001A310000000000DDR", vo));
			// // jvo.setPk_jobtype("0001AA1000000000JCRF");// 项目类型-开发项目
			// jvo.setPk_jobtype("0001A310000000000DDR");// 项目类型-在建工程
			// String pk_job = iJob.insertJobbasfilVO(jvo);

			String pk_job = getMaxJobcode(vo);

			if (pk_job == null) {
				throw new BusinessException("没有正确生成项目档案");
			}

			vo.setPk_jobbasfil(pk_job);
			vo.setStatus(VOStatus.UPDATED);
			new HYPubBO().update(vo);

		}

	}

	private String getMaxJobcode(BzsqVO vo) throws BusinessException {

		// 在建工程
		String pk_jobtype = "0001A310000000000DDR";

		String jobcode = "";
		// 是否共同出资
		if (vo.getGtcz() == null || !vo.getGtcz().booleanValue()) {
			jobcode = "D";
		} else {
			jobcode = "A";
		}
		String pk_corp = vo.getPk_corp();
		if("0001".equals(pk_corp)) {
			throw new BusinessException("集团无法生成项目档案！");
		}
		CorpVO corpVO = (CorpVO) new BaseDAO().retrieveByPK(CorpVO.class, pk_corp);
		String sname = corpVO.getDef1();
		if (StringUtil.isEmptyWithTrim(sname)) {
			throw new BusinessException(corpVO.getUnitname()
					+ "没有维护拼音简称，请到公司目录节点的自定义项页签维护拼音简称信息！如果没有权限请联系管理员！");
		}
		if (sname.length() != 2) {
			throw new BusinessException(corpVO.getUnitname()
					+ "的拼音简称不是两位英文！请到公司目录节点的自定义项页签维护拼音简称信息！如果没有权限请联系管理员！");
		}
		jobcode += sname;

		// 二级分类
		createProj(jobcode, corpVO.getUnitname(), pk_jobtype);
		// 三级分类
		jobcode += "4";
		createProj(jobcode, "户表工程", pk_jobtype);
		// 四级分类
		/*
		 * 	001	新装	1	0	(null)	0001	0001AA1000000000J5TG
			002	改造	1	0	(null)	0001	0001AA1000000000J5TH
			003	改小	1	0	(null)	0001	0001F51000000000ODXJ
		001新装——新建X
		002改造——改造G
		003改小——其它Q
		 */
		if(StringUtil.isEmptyWithTrim(vo.getGcxz())) {
			throw new BusinessException("工程性质不能为空!");
		}
		if("新装".equals(vo.getGcxz().trim())) {
			jobcode += "X";
			createProj(jobcode, "新建", pk_jobtype);
		}
		else if("改造".equals(vo.getGcxz().trim())) {
			jobcode += "G";
			createProj(jobcode, "改造", pk_jobtype);
		}
		else if("改小".equals(vo.getGcxz().trim())) {
			jobcode += "Q";
			createProj(jobcode, "其它", pk_jobtype);
		}
		else {
			throw new BusinessException("未处理的工程性质：" + vo.getGcxz());
		}
		// 五级分类 年度
		UFDate bzrq = vo.getBzrq();
		if (bzrq == null) {
			throw new BusinessException("报装日期不能为空！");
		}
		String syear = (bzrq.getYear() + "").substring(2);
		if (syear.length() != 2) {
			throw new BusinessException("生成报装项目年度有误(不是2位数字)：" + syear);
		}
		jobcode += syear;
		createProj(jobcode, syear + "年", pk_jobtype);
		// 六级分类
		String sql = "select max(jobcode) from bd_jobbasfil where pk_jobtype='"
				+ pk_jobtype
				+ "' and nvl(dr,0)=0 and length(jobcode)=11 and jobcode like '"
				+ jobcode + "%'";
		String maxjobcode = (String) new BaseDAO().executeQuery(sql,
				new ColumnProcessor());
		String max = "0001";
		if (!StringUtil.isEmptyWithTrim(maxjobcode)) {
			max = "";
			String str = maxjobcode.substring(7);
			int index = Integer.parseInt(str) + 1;
			max = index + "";
			while (max.length() < 4) {
				max = "0" + max;
			}
		}

		// 用水户数
		String yshs = "";
		if (vo.getYshs() != null && vo.getYshs() > 1) {
			yshs = "等" + vo.getYshs() + "户";
		}

		String jobname = vo.getAzdz() + vo.getHm() + yshs + "DN" + vo.getSwkj()
				+ "表" + vo.getGcxz() + "工程";
		jobcode += max;
		createProj(jobcode, jobname, pk_jobtype);

		// 七级分类
		jobcode += "000";
		String pk_job = createProj(jobcode, jobname, pk_jobtype);
		// 如果是水建，需要扩展5位
		if (corpVO.getUnitname().indexOf("水建") != -1) {
			jobcode += "A000S";
			pk_job = createProj(jobcode, jobname, pk_jobtype);
		}

		return pk_job;

		// 这里是获取编码叠加的sql
		// String sql =
		// "select lpad(max(jobcode)+1,4,0) code from bd_jobbasfil where  pk_jobtype = '"
		// + pk_jobtype
		// + "' and length(jobcode) = 4 and isnull(dr,0) = 0 ";
		// BaseDAO dao = new BaseDAO();
		// try {
		// String temp = (String) dao.executeQuery(sql, new ColumnProcessor());
		// if (StringUtil.isEmptyWithTrim(temp)) {
		// temp = "0001";
		// }
		// return temp;
		// } catch (DAOException e) {
		// e.printStackTrace();
		// }
		// return "";
	}

	private String createProj(String code, String name, String pk_jobtype)
			throws BusinessException {
		String sql = "select count(1) from bd_jobbasfil where pk_jobtype='"
				+ pk_jobtype + "' and nvl(dr,0)=0 and jobcode='" + code + "'";
		Integer count = (Integer) new BaseDAO().executeQuery(sql,
				new ColumnProcessor());
		if (count == null || count == 0) {
			JobbasfilVO jvo = new JobbasfilVO();
			jvo.setPk_corp("0001");
			jvo.setJobname(name);
			jvo.setJobcode(code);
			jvo.setPk_jobtype("0001A310000000000DDR");// 项目类型-在建工程
			String pk_job = NCLocator.getInstance().lookup(IJobbasedoc.class)
					.insertJobbasfilVO(jvo);

			return pk_job;
		}
		return null;
	}

	private String getDefdocName(String pk_defdoclist) {
		String sql = "select docname from bd_defdoc where pk_defdoc = '"
				+ pk_defdoclist + "' and isnull(dr,0) = 0 ";
		BaseDAO dao = new BaseDAO();
		try {
			return (String) dao.executeQuery(sql, new ColumnProcessor());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/*
	 * 备注：平台编写原始脚本
	 */
	public String getCodeRemark() {
		return "	//****该组件为单动作工作流处理开始...不能进行修改****\n	procActionFlow@@;\n	//****该组件为单动作工作流处理结束...不能进行修改****\n	Object retObj  =runClassCom@ \"nc.bs.trade.comstatus.BillApprove\", \"approveBill\", \"nc.vo.pub.AggregatedValueObject:01\"@; \n	return retObj;\n";
	}

	/*
	 * 备注：设置脚本变量的HAS
	 */
	private void setParameter(String key, Object val) {
		if (m_keyHas == null) {
			m_keyHas = new Hashtable();
		}
		if (val != null) {
			m_keyHas.put(key, val);
		}
	}
}
