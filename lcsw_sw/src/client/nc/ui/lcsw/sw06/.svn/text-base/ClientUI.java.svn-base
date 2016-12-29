/**    Create By NCPlugin beta 0.1.   **/
package nc.ui.lcsw.sw06;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
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
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw06.FbyjazBVO;
import nc.vo.lcsw.sw06.FbyjazVO;
import nc.vo.lcsw.sw11.SjfksjHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDate;
import nc.vo.trade.button.ButtonVO;
import nc.vo.trade.field.BillField;
import nc.vo.trade.pub.IBillStatus;

/**
 * 发包移交安装
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
				fileDef.getField_BillStatus(), FbyjazVO.DMAKEDATE, FbyjazVO.DBILLDATE };
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
			UIRefPane ref = (UIRefPane) billCardPanel.getHeadItem("pk_jobbasfil").getComponent();
			ref.getRefModel().addWherePart(
					" and pk_jobbasfil in (select kcpg.PK_JOBBASFIL from lcsw_gcpgsj_h kcpg where nvl(kcpg.dr,0)=0 and kcpg.vbillstatus='1' and kcpg.pk_corp='"
							+ getEnvironment().getCorporation().getPrimaryKey() + "' )  "
							+ " and pk_jobbasfil not in (select fbyjaz.PK_JOBBASFIL from lcsw_fbyjaz fbyjaz where nvl(fbyjaz.dr,0)=0 and fbyjaz.pk_corp='"
					+ getEnvironment().getCorporation().getPrimaryKey() + "'  and fbyjaz.vbillstatus='1')");
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

		if (e.getPos() == BillItem.BODY) {
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
				//add by crf 2015-07-09
			}else if(key.equals(FbyjazVO.HTH)||key.equals(FbyjazVO.YFKFKRQ)||key.equals(FbyjazVO.GCXZ)||key.equals(FbyjazVO.JDKFKRQ)||
					key.equals(FbyjazVO.JSRQ)||key.equals(FbyjazVO.JZLRQ)||key.equals(FbyjazVO.KFPRQ)||
					key.equals(FbyjazVO.YSSZJE)||key.equals(FbyjazVO.YHYSK)||key.equals(FbyjazVO.YHCZJE)
					||key.equals(FbyjazVO.GSCZJE)||key.equals(FbyjazVO.YHJSK)||key.equals(FbyjazVO.YFK)
					||key.equals(FbyjazVO.JDK)||key.equals(FbyjazVO.SGDWJSK)||key.equals(FbyjazVO.JSKFKRQ)){
				String hth = getBillCardPanel().getHeadItem(FbyjazVO.HTH).getValue();
				String yfkfkrq = getBillCardPanel().getHeadItem(FbyjazVO.YFKFKRQ).getValue();
				String gcxz = getBillCardPanel().getHeadItem(FbyjazVO.GCXZ).getValue();
				String jdkfkrq = getBillCardPanel().getHeadItem(FbyjazVO.JDKFKRQ).getValue();
				String jsrq = getBillCardPanel().getHeadItem(FbyjazVO.JSRQ).getValue();
				String jzlrq = getBillCardPanel().getHeadItem(FbyjazVO.JZLRQ).getValue();
				String kfprq = getBillCardPanel().getHeadItem(FbyjazVO.KFPRQ).getValue();
				String ysszje = getBillCardPanel().getHeadItem(FbyjazVO.YSSZJE).getValue();
				String yhysk = getBillCardPanel().getHeadItem(FbyjazVO.YHYSK).getValue();
				String yhczje = getBillCardPanel().getHeadItem(FbyjazVO.YHCZJE).getValue();
				String gsczje = getBillCardPanel().getHeadItem(FbyjazVO.GSCZJE).getValue();
				String yhjsk = getBillCardPanel().getHeadItem(FbyjazVO.YHJSK).getValue();
				String yfk = getBillCardPanel().getHeadItem(FbyjazVO.YFK).getValue();
				String jdk = getBillCardPanel().getHeadItem(FbyjazVO.JDK).getValue();
				String sgdwjsk = getBillCardPanel().getHeadItem(FbyjazVO.SGDWJSK).getValue();
				String jskfkrq = getBillCardPanel().getHeadItem(FbyjazVO.JSKFKRQ).getValue();

				//add by crf 2015-06-24表头信息
				/*是否有施工合同、是否需与用户结算、是否是包干价、是否已开发票*/
				String sfysght = getBillCardPanel().getHeadItem(FbyjazVO.SFYSGHT).getValue();
				String sfxyyhjs = getBillCardPanel().getHeadItem(FbyjazVO.SFXYYJJS).getValue();
				String sfsbgj = getBillCardPanel().getHeadItem(FbyjazVO.SFSBGJ).getValue();
				String sfykfp = getBillCardPanel().getHeadItem(FbyjazVO.SFYKFP).getValue();
				for (int i = 0; i < rowcount; i++) {
					
					// 合同号
					getBillCardPanel().setBodyValueAt(hth, i, FbyjazBVO.HTH);
					// 预付款日期
					getBillCardPanel().setBodyValueAt(yfkfkrq, i, FbyjazBVO.YFKFKRQ);
					// 工程性质
					getBillCardPanel().setBodyValueAt(gcxz, i, FbyjazBVO.GCXZ);
					// 进度款付款日期
					getBillCardPanel().setBodyValueAt(jdkfkrq, i, FbyjazBVO.JDKFKRQ);
					// 结算日期
					getBillCardPanel().setBodyValueAt(jsrq, i, FbyjazBVO.JSRQ);
					// 接资料日期
					getBillCardPanel().setBodyValueAt(jzlrq, i, FbyjazBVO.JZLRQ);
					// 开发票日期
					getBillCardPanel().setBodyValueAt(kfprq, i, FbyjazBVO.KFPRQ);
					// 预算书总金额
					getBillCardPanel().setBodyValueAt(ysszje, i, FbyjazBVO.YSSZJE);
					// 用户预算款
					getBillCardPanel().setBodyValueAt(yhysk, i, FbyjazBVO.YHYSK);

					// 用户出资金额
					getBillCardPanel().setBodyValueAt(yhczje, i, FbyjazBVO.YHCZJE);
					// 公司出资金额
					getBillCardPanel().setBodyValueAt(gsczje, i, FbyjazBVO.GSCZJE);
					// 用户结算款
					getBillCardPanel().setBodyValueAt(yhjsk, i, FbyjazBVO.YHJSK);
					// 预付款
					getBillCardPanel().setBodyValueAt(yfk, i, FbyjazBVO.YFK);
					// 进度款
					getBillCardPanel().setBodyValueAt(jdk, i, FbyjazBVO.JDK);
					// 施工单位结算款
					getBillCardPanel().setBodyValueAt(sgdwjsk, i, FbyjazBVO.SGDWJSK);
					// 结算款付款日期
					getBillCardPanel().setBodyValueAt(jskfkrq, i, FbyjazBVO.JSKFKRQ);
				}
			
			}
		}

		if (e.getPos() == BillItem.BODY) {
		}
		if(FbyjazVO.SFYJAZ.equals(e.getKey())){
			String sfyjaz=getBillCardPanel().getHeadItem(FbyjazVO.SFYJAZ).getValue();
			if(sfyjaz.equals("true")){
				try {
					getBillCardPanel().dataNotNullValidate();
					getBillCardPanel().getHeadItem(FbyjazVO.YJAZRQ).setValue(new UFDate());
				} catch (ValidationException e1) {
					getBillCardPanel().getHeadItem(FbyjazVO.SFYJAZ).setValue("false");
					showWarningMessage(e1.toString());
					e1.printStackTrace();
				}
			}else{
				getBillCardPanel().getHeadItem(FbyjazVO.YJAZRQ).setValue("");
			}
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
		getBillCardPanel().getBillModel().clearBodyData();//先清空表体行
		int thisrow = 0;
		String pk_jobbasfil = getBillCardPanel().getHeadItem("pk_jobbasfil").getValue();
		if (pk_jobbasfil != null) {
			
			//带出上游信息
			SjfksjHVO[] sjfkvos = (SjfksjHVO[]) HYPubBO_Client.queryByCondition(SjfksjHVO.class, " pk_jobbasfil ='" + pk_jobbasfil + "' and nvl(dr,0)=0 order　by　ts desc");
			if (sjfkvos != null && sjfkvos.length > 0 ) {
				getBillCardPanel().setHeadItem(FbyjazVO.SJTH,sjfkvos[0].getSjth());
			}
			
			BzsqVO[] bzsqvos = (BzsqVO[]) HYPubBO_Client.queryByCondition(BzsqVO.class, " pk_jobbasfil ='" + pk_jobbasfil + "' and nvl(dr,0)=0 and "
					+ BzsqVO.VBILLSTATUS + " = '1'  and bzzt= " + EmunBzzt.PGZ + " ");
			if (bzsqvos != null && bzsqvos.length > 0 ) {

				LcswBzxxHVO[] bzxxcheckvo = (LcswBzxxHVO[]) HYPubBO_Client.queryByCondition(LcswBzxxHVO.class, LcswBzxxHVO.PK_JOBBASFIL + " = '" + pk_jobbasfil
						+ "' and nvl(dr,0)=0 and PK_BZXX not in (select pk_bzxx from lcsw_fbyjaz_b where PK_JOBBASFIL='" + pk_jobbasfil
						+ "' and nvl(dr,0)=0)  ");

				if (bzxxcheckvo == null || bzxxcheckvo.length < 1) {
					getBillCardPanel().setHeadItem("pk_jobbasfil", "");
					this.showErrorMessage("该项目没有对应的报装信息,或者对应报装信息已经做了发包移交安装单！");
					return;
				}
				
				getBillCardPanel().setHeadItem("gtcz",bzsqvos[0].getGtcz());
				getBillCardPanel().setHeadItem("sfsyx",bzsqvos[0].getSfsyx());
				
				for (int i = 0; i < bzsqvos.length; i++) {
					LcswBzxxHVO[] lcswbzxxhvos = (LcswBzxxHVO[]) HYPubBO_Client.queryByCondition(LcswBzxxHVO.class,
							LcswBzxxHVO.PK_BZSQ + " = '" + bzsqvos[i].getPk_bzsq() + "' and nvl(dr,0)=0 and bzzt= " + EmunBzzt.PGZ + " ");
					if (lcswbzxxhvos != null && lcswbzxxhvos.length > 0) {
						for (int j = 0; j < lcswbzxxhvos.length; j++) {
							getBillCardPanel().addLine();
							getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getPk_jobbasfil(), thisrow, "pk_jobbasfil");
							String jobname = this.execFormular("getColValue(bd_jobbasfil, jobname, pk_jobbasfil, pk_jobbasfil)", bzsqvos[i].getPk_jobbasfil());
							getBillCardPanel().setBodyValueAt(jobname, thisrow, "xm");

							getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getPk_bzxx(), thisrow, "pk_bzxx");
							getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getHm(), thisrow, "bzyh");
							getBillCardPanel().setBodyValueAt(bzsqvos[i].getPk_bzsq(), thisrow, "pk_bzsq");
							getBillCardPanel().setBodyValueAt(pk_corp, thisrow, "pk_corp");

							getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getGcxz(), thisrow, FbyjazBVO.GCXZ);
							
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
