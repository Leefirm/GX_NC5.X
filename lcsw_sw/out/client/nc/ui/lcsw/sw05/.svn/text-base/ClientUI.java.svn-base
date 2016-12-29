/**    Create By NCPlugin beta 0.1.   **/
package nc.ui.lcsw.sw05;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ArrayProcessor;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardBeforeEditListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillItemEvent;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.trade.button.IBillButton;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.uif.pub.exception.UifException;
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw03.KcpgHVO;
import nc.vo.lcsw.sw04.KcfksjBVO;
import nc.vo.lcsw.sw05.GcpgsjBVO;
import nc.vo.lcsw.sw05.GcpgsjHVO;
import nc.vo.lcsw.sw05.itf.IGcpgsj;
import nc.vo.lcsw.sw06.FbyjazVO;
import nc.vo.lcsw.sw11.SjfksjBVO;
import nc.vo.lcsw.sw11.SjfksjHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.trade.button.ButtonVO;
import nc.vo.trade.field.BillField;
import nc.vo.trade.pub.IBillStatus;

/**
 * 工程派工
 * @author Administrator
 * @createtime 2014-08-07 17:30:01
 * 
 */
public class ClientUI extends BillManageUI implements BillCardBeforeEditListener {

	public ClientUI() {
		super();
		getBillCardPanel().addBillEditListenerHeadTail(this);
		getBillCardPanel().setBillBeforeEditListenerHeadTail(this);
	}

	@Override
	protected AbstractManageController createController() {
		return new ClientCtrl();
	}

	@Override
	protected ManageEventHandler createEventHandler() {
		return new ClientEventHandler(this, getUIControl());
	}

	@Override
	public void setBodySpecialData(CircularlyAccessibleValueObject[] vos) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setHeadSpecialData(CircularlyAccessibleValueObject vo, int intRow) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setTotalHeadSpecialData(CircularlyAccessibleValueObject[] vos) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initSelfData() {
		// 提交审批后可修改不可删除
		((ButtonVO) getButtonManager().getButton(IBillButton.Delete).getData()).setBusinessStatus(new int[] { IBillStatus.FREE });
		nc.vo.pub.bill.BillRendererVO voCell = new nc.vo.pub.bill.BillRendererVO();
		// getBillCardPanel().getHeadItem("deptname").setLoadFormula(new
		// String[] { "deptname->pk_deptdoc;" });
		voCell.setShowZeroLikeNull(false);
		getBillCardPanel().setBodyShowFlags(voCell);

	}

	@Override
	public void setDefaultData() throws Exception {
		// TODO Auto-generated method stub
		BillField fileDef = BillField.getInstance();
		String billtype = getUIControl().getBillType();
		String pkCorp = ClientEnvironment.getInstance().getCorporation().getPrimaryKey();
		String[] itemkeys = new String[] { fileDef.getField_Corp(), "pk_deptdoc", fileDef.getField_Operator(), fileDef.getField_Billtype(),
				fileDef.getField_BillStatus(), KcpgHVO.DMAKEDATE, KcpgHVO.DBILLDATE };
		Object[] values = new Object[] { pkCorp, getPKDeptDoc(), ClientEnvironment.getInstance().getUser().getPrimaryKey(), billtype,
				new Integer(IBillStatus.FREE), new UFDate(new Date()), new UFDate(new Date()) // 制单日期
		};

		for (int i = 0; i < itemkeys.length; i++) {
			BillItem item = null;
			item = getBillCardPanel().getHeadItem(itemkeys[i]);
			if (item == null)
				item = getBillCardPanel().getTailItem(itemkeys[i]);
			if (item != null)
				item.setValue(values[i]);
		}
	}

	@SuppressWarnings("unchecked")
	private String getPKDeptDoc() throws BusinessException {
		String sql = " select psn.pk_deptdoc " + " from bd_psndoc psn " + " where psn.pk_psnbasdoc in " + "  (select u.pk_psndoc "
				+ "  from sm_userandclerk u " + "  where u.userid='" + _getOperator() + "'" + "  ) ";
		IUAPQueryBS iuap = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class);
		ArrayList list = (ArrayList) iuap.executeQuery(sql, new ArrayListProcessor());
		if (list != null && list.size() > 0) {
			return (String) ((Object[]) list.get(0))[0];
		} else {
			return "";
		}
	}

	// 表头
	public boolean beforeEdit(BillItemEvent e) {
		BillCardPanel billCardPanel = getBillCardPanel();
		BillItem item = e.getItem();
		if ("pk_jobbasfil".equals(item.getKey())) {
			String id = billCardPanel.getHeadItem(GcpgsjHVO.PK_GCPGSJHID).getValue();
			if (id == null || id.equals("")) {
				id = "check";
			}
			UIRefPane ref = (UIRefPane) billCardPanel.getHeadItem("pk_jobbasfil").getComponent();
			ref.getRefModel()
					.addWherePart(
							" and pk_jobbasfil in (select kcpg.PK_JOBBASFIL from lcsw_sjfksj_h kcpg where nvl(kcpg.dr,0)=0 and kcpg.vbillstatus='1' and kcpg.pk_corp='"
									+ getEnvironment().getCorporation().getPrimaryKey()
									+ "' ) and  pk_jobbasfil not in (select a.PK_JOBBASFIL from LCSW_GCPGSJ_H a where a.pk_gcpgsjhid!='" + id
									+ "' and nvl(a.dr,0)=0 )");
		}
		return true;
	}

	// 表体
	@Override
	public boolean beforeEdit(BillEditEvent e) {
		// TODO Auto-generated method stub
		// 取得卡片
		IUAPQueryBS iuap = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class);
		BillCardPanel billCardPanel = getBillCardPanel();
		String key = e.getKey();

		// 选择行
		int row = e.getRow();

		String pk_corp = getBillCardPanel().getHeadItem("pk_corp").getValue();

		if (e.getPos() == BillItem.BODY) {
			try {
				if ("yqxgrcode".equals(key)) {
					Object yqc = this.getBillCardPanel().getBodyValueAt(row, "yqxgrcode");
					if (yqc != null && !yqc.equals("")) {
						Object[] obj = (Object[]) iuap.executeQuery("select pk_psnbasdoc from bd_psndoc where psncode= '" + yqc + "' and pk_corp ='" + pk_corp
								+ "'", new ArrayProcessor());
						this.getBillCardPanel().setBodyValueAt(obj[0], row, KcfksjBVO.YQXGR);
						String name = this.execFormular("getColValue(bd_psnbasdoc,psnname,pk_psnbasdoc,yqxgr)", obj[0].toString());
						this.getBillCardPanel().setBodyValueAt(name, row, "yqxgrname");
					}
				}
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return super.beforeEdit(e);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void afterEdit(BillEditEvent e) {
		// 取得卡片
		IUAPQueryBS iuap = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class);
		BillCardPanel billCardPanel = getBillCardPanel();
		String key = e.getKey();
		// 选择行
		int row = e.getRow();

		int rowcount = getBillCardPanel().getRowCount();

		String pk_corp = getBillCardPanel().getHeadItem("pk_corp").getValue();

		if (e.getPos() == BillItem.HEAD) {
			if ("pk_jobbasfil".equals(key)) {
				try {
					this.setxm(iuap, rowcount, pk_corp);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(key.equals(GcpgsjHVO.YQXGR)||key.equals(GcpgsjHVO.YQXGRQ)||key.equals(GcpgsjHVO.WGYQTS)||
					key.equals(GcpgsjHVO.SJDW)||key.equals(GcpgsjHVO.SNSGDW)||key.equals(GcpgsjHVO.SBGS)||
					key.equals(GcpgsjHVO.GC)||key.equals(GcpgsjHVO.SGTS)){
				String yqxgr = getBillCardPanel().getHeadItem(GcpgsjHVO.YQXGR).getValue();
				String yqxgrq = getBillCardPanel().getHeadItem(GcpgsjHVO.YQXGRQ).getValue();
				String wgyqts = getBillCardPanel().getHeadItem(GcpgsjHVO.WGYQTS).getValue();
				String sjdw = getBillCardPanel().getHeadItem(GcpgsjHVO.SJDW).getValue();
				String snsgdw = getBillCardPanel().getHeadItem(GcpgsjHVO.SNSGDW).getValue();
				String swsgdw = getBillCardPanel().getHeadItem(GcpgsjHVO.SWSGDW).getValue();
				String sbgs = getBillCardPanel().getHeadItem(GcpgsjHVO.SBGS).getValue();
				String gc = getBillCardPanel().getHeadItem(GcpgsjHVO.GC).getValue();
				String sgts = getBillCardPanel().getHeadItem(GcpgsjHVO.SGTS).getValue();
				for (int i = 0; i < rowcount; i++) {
					// 延期修改人
					getBillCardPanel().setBodyValueAt(yqxgr, i, GcpgsjBVO.YQXGR);
					// 延期修改日期
					getBillCardPanel().setBodyValueAt(yqxgrq, i, GcpgsjBVO.YQXGRQ);
					// 完工延迟日期
					getBillCardPanel().setBodyValueAt(wgyqts, i, GcpgsjBVO.WGYQTS);
					// 设计单位
					getBillCardPanel().setBodyValueAt(sjdw, i, GcpgsjBVO.SJDW);
					// 室内施工单位
					getBillCardPanel().setBodyValueAt(snsgdw, i, GcpgsjBVO.SNSGDW);
					// 室外施工单位
					getBillCardPanel().setBodyValueAt(swsgdw, i, GcpgsjBVO.SWSGDW);
					// 水表个数
					getBillCardPanel().setBodyValueAt(sbgs, i, GcpgsjBVO.SBGS);
					// 管长
					getBillCardPanel().setBodyValueAt(new UFDouble(gc), i, GcpgsjBVO.GC);
					// 施工天数
					getBillCardPanel().setBodyValueAt(sgts, i, GcpgsjBVO.SGTS);
				}
			}
		}

		if (e.getPos() == BillItem.BODY) {
		}
		// getBillCardPanel().getBillModel().loadLoadRelationItemValue();
		super.afterEdit(e);
	}

	/**
	 * 表头项目编辑后处理
	 * 
	 * @param iuap
	 * @param rowcount
	 * @param pk_corp
	 * @throws Exception
	 */
	private void setxm(IUAPQueryBS iuap, int rowcount, String pk_corp) throws Exception {
		IGcpgsj gcpgsjService = NCLocator.getInstance().lookup(IGcpgsj.class);
		int thisrow = 0;
		String pk_jobbasfil = getBillCardPanel().getHeadItem("pk_jobbasfil").getValue();
		if (pk_jobbasfil != null) {
			//带出上游信息
			SjfksjHVO[] sjfkvos = (SjfksjHVO[]) HYPubBO_Client.queryByCondition(SjfksjHVO.class, " pk_jobbasfil ='" + pk_jobbasfil + "' and nvl(dr,0)=0 order　by　ts desc");
			if (sjfkvos != null && sjfkvos.length > 0 ) {
				getBillCardPanel().setHeadItem(GcpgsjHVO.SJTH,sjfkvos[0].getSjth());
			}
			
			BzsqVO[] bzsqvos = (BzsqVO[]) HYPubBO_Client.queryByCondition(BzsqVO.class, " pk_jobbasfil ='" + pk_jobbasfil + "' and nvl(dr,0)=0 and "
					+ BzsqVO.VBILLSTATUS + " = '1' and bzzt= " + EmunBzzt.SJCG + " ");
			if (bzsqvos != null && bzsqvos.length > 0 && rowcount < 1) {

				getBillCardPanel().setHeadItem("gtcz",bzsqvos[0].getGtcz());
				getBillCardPanel().setHeadItem("sfsyx",bzsqvos[0].getSfsyx());
				
				// 判断 预算工程款/补交勘察费
				for (int i = 0; i < bzsqvos.length; i++) {
					if(bzsqvos[i].getSfsyx().equals(UFBoolean.FALSE)){//如果非三优先用户,不校验是否交费
						LcswBzxxHVO[] hvo = gcpgsjService.queryBzxx(bzsqvos[i]);
						if (hvo != null && hvo.length > 0) {
							this.showErrorMessage("该项目有用户未交 预算工程款/补交勘察费 ！");
							getBillCardPanel().setHeadItem("pk_jobbasfil", "");
							return;
						}
					}

					
					getBillCardPanel().getHeadItem(GcpgsjHVO.SBGS).setValue(bzsqvos[i].getYshs());//[水表个数]默认该项目的户数
					
					
				}
				

				LcswBzxxHVO[] bzxxcheckvo = (LcswBzxxHVO[]) HYPubBO_Client
						.queryByCondition(LcswBzxxHVO.class, LcswBzxxHVO.PK_JOBBASFIL + " = '" + pk_jobbasfil
								+ "' and nvl(dr,0)=0 and PK_BZXX not in (select pk_bzxx from lcsw_gcpgsj_b where PK_JOBBASFIL='" + pk_jobbasfil
								+ "' and nvl(dr,0)=0) ");

				if (bzxxcheckvo == null || bzxxcheckvo.length < 1) {
					getBillCardPanel().setHeadItem("pk_jobbasfil", "");
					this.showErrorMessage("该项目没有对应的报装信息,或者对应报装信息已经做了派工反馈单！");
					return;
				}

				// 循环表体设值
				for (int i = 0; i < bzsqvos.length; i++) {
					LcswBzxxHVO[] lcswbzxxhvos = (LcswBzxxHVO[]) HYPubBO_Client.queryByCondition(LcswBzxxHVO.class,
							LcswBzxxHVO.PK_BZSQ + " = '" + bzsqvos[i].getPk_bzsq()
									+ "' and nvl(dr,0)=0 and PK_BZXX in (select pk_bzxx from lcsw_sjfksj_b where PK_JOBBASFIL='" + pk_jobbasfil
									+ "' and nvl(dr,0)=0) and bzzt= " + EmunBzzt.SJCG + " ");
					if (lcswbzxxhvos != null && lcswbzxxhvos.length > 0) {
						for (int j = 0; j < lcswbzxxhvos.length; j++) {
							getBillCardPanel().addLine();
							getBillCardPanel().setBodyValueAt(bzsqvos[i].getPk_jobbasfil(), thisrow, "pk_jobbasfil");
							String jobname = this.execFormular("getColValue(bd_jobbasfil, jobname, pk_jobbasfil, pk_jobbasfil)", bzsqvos[i].getPk_jobbasfil());
							getBillCardPanel().setBodyValueAt(jobname, thisrow, "xm");

							// getBillCardPanel().setHeadItem("",
							// lcswbzxxhvos[j].getSjdw());
							// getBillCardPanel().setHeadItem("",
							// lcswbzxxhvos[j].getSnsgdw());
							// getBillCardPanel().setHeadItem("",
							// lcswbzxxhvos[j].getSwsgdw());
							// getBillCardPanel().setHeadItem("",
							// lcswbzxxhvos[j].getSbgs());
							// getBillCardPanel().setHeadItem("",
							// lcswbzxxhvos[j].getGc());

							getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getPk_bzxx(), thisrow, "pk_bzxx");
							getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getHm(), thisrow, "hm");
							getBillCardPanel().setBodyValueAt(bzsqvos[i].getPk_bzsq(), thisrow, "pk_bzsq");
							getBillCardPanel().setBodyValueAt(pk_corp, thisrow, "pk_corp");
							getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getSbgs(), thisrow, "sbgs");
							
							
							//增加项目编码、报装信息编号、安装地址、联系人、联系电话
							String jobcode=this.execFormular("getColValue(bd_jobbasfil, jobcode,pk_jobbasfil , pk_jobbasfil)", lcswbzxxhvos[j].getPk_jobbasfil());
							getBillCardPanel().setBodyValueAt(jobcode, thisrow, "jobcode");//项目编码
							String bzbh=this.execFormular("getColValue(lcsw_bzxx, bzbh, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
							getBillCardPanel().setBodyValueAt(bzbh, thisrow, "bzbh");//报装信息编号
							String azdz=this.execFormular("getColValue(lcsw_bzxx, azdz, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
							getBillCardPanel().setBodyValueAt(azdz, thisrow, "azdz");//安装地址
							String lxr=this.execFormular("getColValue(lcsw_bzxx, lxr, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
							getBillCardPanel().setBodyValueAt(lxr, thisrow, "lxr");//联系人
							String lxdh=this.execFormular("getColValue(lcsw_bzxx, lxdd, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
							getBillCardPanel().setBodyValueAt(lxdh, thisrow, "lxdh");//联系电话
							
							thisrow++;
						}
					} else {
						getBillCardPanel().setHeadItem("pk_jobbasfil", "");
						this.showErrorMessage("该项目没有对应的报装信息！");
						return;
					}
				}
			} else {
				getBillCardPanel().setHeadItem("pk_jobbasfil", "");
				this.showErrorMessage("该项目没有对应的报装申请！");
				return;
			}
		}
	}

	protected String execFormular(String formula, String value) {
		nc.ui.pub.formulaparse.FormulaParse f = new nc.ui.pub.formulaparse.FormulaParse();
		if (formula != null && !formula.equals("")) {
			f.setExpress(formula);
			nc.vo.pub.formulaset.VarryVO varry = f.getVarry();
			Hashtable h = new Hashtable();
			for (int j = 0; j < varry.getVarry().length; j++) {
				String key = varry.getVarry()[j];
				String[] vs = new String[1];
				// 对vlues进行toString(vlues)转换。
				// vs[0] = value;
				vs[0] = nc.vo.pub.formulaset.util.StringUtil.toString(value);
				h.put(key, vs);
			}
			f.setDataS(h);
			if (varry.getFormulaName() != null && !varry.getFormulaName().trim().equals(""))
				return f.getValueS()[0];
			else
				return f.getValue();
		} else {
			return null;
		}
	}

	@Override
	protected void initPrivateButton() {
		super.initPrivateButton();
	}

	private ButtonVO setPrivateBtnVO(int btnNo, String btnName, int[] operateStatus, int[] businessStatus, int[] childAry) {
		ButtonVO privateBtn = new ButtonVO();
		privateBtn.setBtnNo(btnNo);
		privateBtn.setBtnName(btnName);
		privateBtn.setOperateStatus(operateStatus);
		privateBtn.setBusinessStatus(businessStatus);
		privateBtn.setChildAry(childAry);
		addPrivateButton(privateBtn);
		return privateBtn;
	}

	/**
	 * 公用方法：根据字段名获取某Object类型字段的值
	 * 
	 * @param i
	 * @param name
	 * @return
	 */
	public Object getObjValue(int i, String name) {

		Object obj = null;

		obj = this.getBillCardWrapper().getBillCardPanel().getBodyValueAt(i, name);

		return obj;

	}

}
