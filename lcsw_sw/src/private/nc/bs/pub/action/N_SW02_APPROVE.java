/**    Create By NCPlugin beta 0.1.   **/
//insert into pub_busiclass (ACTIONTYPE, CLASSNAME, DR, ISBEFORE, PK_BILLTYPE, PK_BUSICLASS, PK_BUSINESSTYPE, PK_CORP, TS)
//values ('APPROVE', 'N_SW02_APPROVE', 0, 'N', 'SW02', '140804113943003NHR82', NULL, '0001', '2014-08-04 11:39:43')
//go

//insert into pub_billaction (ACTIONNOTE, ACTIONSTYLE, ACTIONSTYLEREMARK, ACTIONTYPE, CONSTRICTFLAG, CONTROLFLAG, DR, FINISHFLAG, PK_BILLACTION, PK_BILLTYPE, SHOWHINT, TS)
//values ('����', NULL, NULL, 'APPROVE', 'N', 'Y', 0, 'Y', '140804113943103JM4XU', 'SW02', NULL, '2014-08-04 11:39:43')
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
 * ��ע������:<b><code>SW02</code></b> ������ <tt>APPROVE</tt> ��ִ̬���ࡣ
 * 
 * @since �������ڣ�2014-08-04 11:39:43
 * @version UAP 5.x
 * @author Administrator
 * @author NC Plugin
 */
public class N_SW02_APPROVE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_SW02_APPROVE ������ע�⡣
	 */
	public N_SW02_APPROVE() {
		super();
	}

	/*
	 * ��ע��ƽ̨��д������ �ӿ�ִ����
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {

			super.m_tmpVo = vo;
			// ****�����Ϊ����������������ʼ...���ܽ����޸�****
			Object m_sysflowObj = procActionFlow(vo);
			if (m_sysflowObj != null) {
				return m_sysflowObj;
			}
			// ���ͨ����д��Ŀ����
			PushJobbasfil((BzsqVO) vo.m_preValueVo.getParentVO());

			// ���ͨ�����д��װ��Ϣ������Ϊ�û���
			PushBzxxbyyhs((BzsqVO) vo.m_preValueVo.getParentVO());

			// ****�����Ϊ�������������������...���ܽ����޸�****
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
	 * ���ɱ�װ��Ϣ
	 * 
	 * @param vo
	 */
	private void PushBzxxbyyhs(BzsqVO vo) {
		int yhs = vo.getYshs();
		LcswBzxxHVO[] bzxxhvos = new LcswBzxxHVO[yhs];
		try {
			for (int i = 0; i < yhs; i++) {
				LcswBzxxHVO hvo = new LcswBzxxHVO();
				// ���ɵ��ݺ�
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

				hvo.setYyqy(vo.getYyqy());// Ӫҵ����
				hvo.setYyzzh(vo.getYyzzh());// Ӫҵִ�պ�
				hvo.setSfsyx(vo.getSfsyx());// �Ƿ�������
				hvo.setGtcz(vo.getGtcz());// �Ƿ�ͬ����
				hvo.setJbry(vo.getJbry());// ������Ա
				hvo.setSfzh(vo.getSfzh());// ���֤��
				hvo.setRemark(vo.getRemark());// ��ע

				bzxxhvos[i] = hvo;

			}

			HYPubBO bo = new HYPubBO();
			String[] insertpks = bo.insertAry(bzxxhvos);

			// ���ɱ�װ����
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

			fyvo.setSlbh(vo.getSlbh());// ������

			fyvo.setGcmc(vo.getPk_jobbasfil());// ��ĿPK

			// add by crf 2015-06-17
			fyvo.setSfsyx(vo.getSfsyx());
			fyvo.setGtcz(vo.getGtcz());
			fyvo.setYshs(vo.getYshs());
			// ------end------------
			// ���ɵ��ݺ�
			nc.bs.pub.billcodemanage.BillcodeGenerater gene = new nc.bs.pub.billcodemanage.BillcodeGenerater();
			String billno = gene.getBillCode("SW10", vo.getPk_corp(), null,
					null);
			fyvo.setVbillcode(billno);

			String bzfypk = bo.insert(fyvo);
			// ���ɱ�װ���ñ���
			List<BzfyBVO> list = new ArrayList<BzfyBVO>();
			// ������Ʒ�
			if (vo.getKcsjf() != null && vo.getKcsjf().doubleValue() != 0) {
				// ���ƽ��
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

					// modify by crf 2015-08-03 ͨ���Զ�����1,����ԭ�н��
					fybvo.setReserve1(vo.getFkfs());// ���ʽ--ԭʼ
					fybvo.setReserve2(PubEnv.getServerDate().toString());// ��������--ԭʼ
					if (index == insertpks.length) {
						UFDouble fy = new UFDouble(pingjun + yushu);
						fybvo.setAmount(fy);
						fybvo.setReserve3(fy.toString());// ���˷���--ԭʼ
					} else {
						UFDouble fy = new UFDouble(pingjun);
						fybvo.setAmount(new UFDouble(pingjun));
					}
					index++;
					list.add(fybvo);
				}
			}
			// ��Ԥ�������
			// if (vo.getJyskqs() != null && vo.getJyskqs().doubleValue() != 0)
			// {
			// // ���ƽ��
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
	 * ������Ŀ����
	 * 
	 * @param vo
	 * @throws BusinessException 
	 */
	private void PushJobbasfil(BzsqVO vo) throws BusinessException {
		if (vo.getPk_jobbasfil() == null) {
			// IJobbasedoc iJob = NCLocator.getInstance().lookup(
			// IJobbasedoc.class);
			// String jobname = vo.getAzdz() + vo.getHm() + "DN"
			// + vo.getBiaojing() + vo.getGcxz() + "����";
			// JobbasfilVO jvo = new JobbasfilVO();
			// jvo.setPk_corp("0001");
			// jvo.setJobname(jobname);
			// // jvo.setJobcode(getMaxJobcode("a"));
			// jvo.setJobcode(getMaxJobcode("0001A310000000000DDR", vo));
			// // jvo.setPk_jobtype("0001AA1000000000JCRF");// ��Ŀ����-������Ŀ
			// jvo.setPk_jobtype("0001A310000000000DDR");// ��Ŀ����-�ڽ�����
			// String pk_job = iJob.insertJobbasfilVO(jvo);

			String pk_job = getMaxJobcode(vo);

			if (pk_job == null) {
				throw new BusinessException("û����ȷ������Ŀ����");
			}

			vo.setPk_jobbasfil(pk_job);
			vo.setStatus(VOStatus.UPDATED);
			new HYPubBO().update(vo);

		}

	}

	private String getMaxJobcode(BzsqVO vo) throws BusinessException {

		// �ڽ�����
		String pk_jobtype = "0001A310000000000DDR";

		String jobcode = "";
		// �Ƿ�ͬ����
		if (vo.getGtcz() == null || !vo.getGtcz().booleanValue()) {
			jobcode = "D";
		} else {
			jobcode = "A";
		}
		String pk_corp = vo.getPk_corp();
		if("0001".equals(pk_corp)) {
			throw new BusinessException("�����޷�������Ŀ������");
		}
		CorpVO corpVO = (CorpVO) new BaseDAO().retrieveByPK(CorpVO.class, pk_corp);
		String sname = corpVO.getDef1();
		if (StringUtil.isEmptyWithTrim(sname)) {
			throw new BusinessException(corpVO.getUnitname()
					+ "û��ά��ƴ����ƣ��뵽��˾Ŀ¼�ڵ���Զ�����ҳǩά��ƴ�������Ϣ�����û��Ȩ������ϵ����Ա��");
		}
		if (sname.length() != 2) {
			throw new BusinessException(corpVO.getUnitname()
					+ "��ƴ����Ʋ�����λӢ�ģ��뵽��˾Ŀ¼�ڵ���Զ�����ҳǩά��ƴ�������Ϣ�����û��Ȩ������ϵ����Ա��");
		}
		jobcode += sname;

		// ��������
		createProj(jobcode, corpVO.getUnitname(), pk_jobtype);
		// ��������
		jobcode += "4";
		createProj(jobcode, "������", pk_jobtype);
		// �ļ�����
		/*
		 * 	001	��װ	1	0	(null)	0001	0001AA1000000000J5TG
			002	����	1	0	(null)	0001	0001AA1000000000J5TH
			003	��С	1	0	(null)	0001	0001F51000000000ODXJ
		001��װ�����½�X
		002���졪������G
		003��С��������Q
		 */
		if(StringUtil.isEmptyWithTrim(vo.getGcxz())) {
			throw new BusinessException("�������ʲ���Ϊ��!");
		}
		if("��װ".equals(vo.getGcxz().trim())) {
			jobcode += "X";
			createProj(jobcode, "�½�", pk_jobtype);
		}
		else if("����".equals(vo.getGcxz().trim())) {
			jobcode += "G";
			createProj(jobcode, "����", pk_jobtype);
		}
		else if("��С".equals(vo.getGcxz().trim())) {
			jobcode += "Q";
			createProj(jobcode, "����", pk_jobtype);
		}
		else {
			throw new BusinessException("δ����Ĺ������ʣ�" + vo.getGcxz());
		}
		// �弶���� ���
		UFDate bzrq = vo.getBzrq();
		if (bzrq == null) {
			throw new BusinessException("��װ���ڲ���Ϊ�գ�");
		}
		String syear = (bzrq.getYear() + "").substring(2);
		if (syear.length() != 2) {
			throw new BusinessException("���ɱ�װ��Ŀ�������(����2λ����)��" + syear);
		}
		jobcode += syear;
		createProj(jobcode, syear + "��", pk_jobtype);
		// ��������
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

		// ��ˮ����
		String yshs = "";
		if (vo.getYshs() != null && vo.getYshs() > 1) {
			yshs = "��" + vo.getYshs() + "��";
		}

		String jobname = vo.getAzdz() + vo.getHm() + yshs + "DN" + vo.getSwkj()
				+ "��" + vo.getGcxz() + "����";
		jobcode += max;
		createProj(jobcode, jobname, pk_jobtype);

		// �߼�����
		jobcode += "000";
		String pk_job = createProj(jobcode, jobname, pk_jobtype);
		// �����ˮ������Ҫ��չ5λ
		if (corpVO.getUnitname().indexOf("ˮ��") != -1) {
			jobcode += "A000S";
			pk_job = createProj(jobcode, jobname, pk_jobtype);
		}

		return pk_job;

		// �����ǻ�ȡ������ӵ�sql
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
			jvo.setPk_jobtype("0001A310000000000DDR");// ��Ŀ����-�ڽ�����
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
	 * ��ע��ƽ̨��дԭʼ�ű�
	 */
	public String getCodeRemark() {
		return "	//****�����Ϊ����������������ʼ...���ܽ����޸�****\n	procActionFlow@@;\n	//****�����Ϊ�������������������...���ܽ����޸�****\n	Object retObj  =runClassCom@ \"nc.bs.trade.comstatus.BillApprove\", \"approveBill\", \"nc.vo.pub.AggregatedValueObject:01\"@; \n	return retObj;\n";
	}

	/*
	 * ��ע�����ýű�������HAS
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
