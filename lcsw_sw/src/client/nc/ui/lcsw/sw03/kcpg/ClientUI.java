/**    Create By NCPlugin beta 0.1.   **/
package nc.ui.lcsw.sw03.kcpg;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JComponent;


import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;

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
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw03.KcpgBVO;
import nc.vo.lcsw.sw03.KcpgHVO;
import nc.vo.lcsw.sw03.itf.IKcpg;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.trade.button.ButtonVO;
import nc.vo.trade.field.BillField;
import nc.vo.trade.pub.IBillStatus;

/**
 * 勘察派工
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
				fileDef.getField_BillStatus(), KcpgHVO.DMAKEDATE };
		Object[] values = new Object[] { pkCorp, getPKDeptDoc(), ClientEnvironment.getInstance().getUser().getPrimaryKey(), billtype,
				new Integer(IBillStatus.FREE), new UFDate(new Date()) // 制单日期
		};

		for (int i = 0; i < itemkeys.length; i++) {
			BillItem item = null;
			item = getBillCardPanel().getHeadItem(itemkeys[i]);
			if (item == null)
				item = getBillCardPanel().getTailItem(itemkeys[i]);
			if (item != null)
				item.setValue(values[i]);
		}
		//panqh“勘探派工人”默认登陆人员名字
		JComponent jComponent = getBillCardPanel().getHeadItem("kcpgry").getComponent();
		if(jComponent instanceof UIRefPane) {
			Vector matchData = ((UIRefPane)jComponent).getRefModel().matchData("docname", ClientEnvironment.getInstance().getUser().getUserName());
			if(matchData != null && matchData.size() > 0) {
				((UIRefPane)jComponent).setPK((String) ((Vector)matchData.get(0)).get(2));
			}
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
		 UIRefPane ref = (UIRefPane)
		 billCardPanel.getHeadItem("pk_jobbasfil").getComponent();
		 ref.getRefModel().addWherePart(
				 " and  pk_jobbasfil in (select bzsq.PK_JOBBASFIL from lcsw_bzsq bzsq where nvl(bzsq.dr,0)=0 and bzsq.vbillstatus='1' and bzsq.pk_corp='"
				 + getEnvironment().getCorporation().getPrimaryKey() + "')"
		 + " and pk_jobbasfil not in (select kcpg.PK_JOBBASFIL from LCSW_KCPG_H kcpg where nvl(kcpg.dr,0)=0 and kcpg.pk_corp='"
		 + getEnvironment().getCorporation().getPrimaryKey() + "'  and kcpg.vbillstatus='1')");
		 
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
			if ("psn".equals(key)) {
				UIRefPane ref = (UIRefPane) billCardPanel.getBodyItem("psn").getComponent();
				Object kcpgry = billCardPanel.getBodyValueAt(row, "kcpgry");
				if (kcpgry != null) {
					ref.setPK(kcpgry);
				}
				ref.getRefModel().addWherePart("");
			}
			if ("xm".equals(key)) {
				UIRefPane ref = (UIRefPane) billCardPanel.getBodyItem("xm").getComponent();
				Object pk_jobbasfil = billCardPanel.getBodyValueAt(row, "pk_jobbasfil");
				if (pk_jobbasfil != null) {
					ref.setPK(pk_jobbasfil);
				}
				ref.getRefModel().addWherePart("");
			}
		}
		return super.beforeEdit(e);
	}

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
					setxm(iuap, rowcount, pk_corp);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if("kcpgry".equals(key)){
				//add by crf 2015-06-15 编辑勘察派工人员
				String kcpgry = (String) getBillCardPanel().getHeadItem("kcpgry").getValueObject();
				if(kcpgry != null)
					for (int i = 0; i < rowcount; i++) {
						getBillCardPanel().setBodyValueAt(kcpgry, i, "kcpgry");
					}
			}else if("kcpgrq".equals(key)){
				String kcpgrq = (String) getBillCardPanel().getHeadItem("kcpgrq").getValueObject();
				if(kcpgrq != null)
					for (int i = 0; i < rowcount; i++) {
						getBillCardPanel().setBodyValueAt(kcpgrq, i, "kcpgrq");
					}
				//-------------end ------------------
			}
		}

		if (e.getPos() == BillItem.BODY) {
			if ("psn".equals(key)) {

				UIRefPane ref = (UIRefPane) billCardPanel.getBodyItem("psn").getComponent();
				String psn = ref.getRefPK();

				String psname = this.execFormular("getColValue(bd_psnbasdoc,psnname,pk_psnbasdoc,kcpgry)", psn);
				getBillCardPanel().setBodyValueAt(psname, row, "psname");

				getBillCardPanel().setBodyValueAt(psn, row, "kcpgry");
			}
			if ("xm".equals(key)) {

				UIRefPane ref = (UIRefPane) billCardPanel.getBodyItem("xm").getComponent();
				String psn = ref.getRefPK();

				getBillCardPanel().setBodyValueAt(psn, row, "pk_jobbasfil");
			}
		}
		getBillCardPanel().getBillModel().loadLoadRelationItemValue();
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
		int thisrow = 0;

		IKcpg kcpgsservice = NCLocator.getInstance().lookup(IKcpg.class);

		// 项目档案主键
		String pk_jobbasfil = getBillCardPanel().getHeadItem("pk_jobbasfil").getValue();
		if (pk_jobbasfil != null) {
			// 根据项目档案查询报装申请
			BzsqVO[] bzsqvos = (BzsqVO[]) HYPubBO_Client.queryByCondition(BzsqVO.class, " pk_jobbasfil ='" + pk_jobbasfil + "' and nvl(dr,0)=0 and "
					+ BzsqVO.VBILLSTATUS + " = '1' and " + BzsqVO.BZZT + " = '" + EmunBzzt.TJSQ + "' ");
			// 判断是否三优先、勘察设计费
			if (bzsqvos != null && bzsqvos.length > 0 && rowcount < 1) {

				// 根据项目档案查询报装信息
				LcswBzxxHVO[] bzxxcheckvo = (LcswBzxxHVO[]) HYPubBO_Client.queryByCondition(LcswBzxxHVO.class, LcswBzxxHVO.PK_JOBBASFIL + " = '" + pk_jobbasfil
						+ "' and nvl(dr,0)=0 and " + LcswBzxxHVO.PK_BZXX + " not in (select " + KcpgBVO.PK_BZXX + " from LCSW_KCPG_B where nvl(dr,0)=0 )  ");
				if (bzxxcheckvo == null || bzxxcheckvo.length < 1) {
					getBillCardPanel().setHeadItem("pk_jobbasfil", "");
					this.showErrorMessage("该项目没有对应的报装信息,或者对应报装信息已经做了勘察派工单！");
					return;
				}

				// 循环报装申请联查报装信息判断每个用户是否已缴费
				for (int i = 0; i < bzsqvos.length; i++) {
					if (bzsqvos[i].getSfsyx().equals(UFBoolean.FALSE)) {
						// 根据报装申请查询报装信息
						LcswBzxxHVO[] lcswbzxxhvos = (LcswBzxxHVO[]) HYPubBO_Client.queryByCondition(LcswBzxxHVO.class, LcswBzxxHVO.PK_BZSQ + " = '"
								+ bzsqvos[i].getPk_bzsq() + "' and nvl(dr,0)=0 and " + LcswBzxxHVO.PK_BZXX + " not in (select " + KcpgBVO.PK_BZXX
								+ " from LCSW_KCPG_B where nvl(dr,0)=0 ) ");

						// 缺少报装费用的单据
						String codeneed = kcpgsservice.queryPayBill(bzsqvos[i].getPk_bzsq());
						if (codeneed != null && !codeneed.equals("")) {
							getBillCardPanel().setHeadItem("pk_jobbasfil", "");
							this.showErrorMessage("报装信息编码为" + codeneed + "的单据未交勘察费！");
							return;
						}
					}
				}
				
				//带出报装申请 <是否三优先>、<是否共同出资> 
				BzsqVO[] bzsqHVO=(BzsqVO[])HYPubBO_Client.queryByCondition(BzsqVO.class, " pk_jobbasfil ='" + pk_jobbasfil + "' and nvl(dr,0)=0 and "
						+  BzsqVO.VBILLSTATUS +"='1'  order by ts desc ");
				if(bzsqHVO!=null && bzsqHVO.length>0){
					if(bzsqHVO[0].getGtcz() !=null)
						getBillCardPanel().getHeadItem("gtcz").setValue(bzsqHVO[0].getGtcz());
					if(bzsqHVO[0].getSfsyx() !=null )
						getBillCardPanel().getHeadItem("sfsyx").setValue(bzsqHVO[0].getSfsyx());
				}
				

				// 根据项目档案增加表体信息
				for (int i = 0; i < bzsqvos.length; i++) {
					// 根据报装申请查询报装信息
					LcswBzxxHVO[] lcswbzxxhvos = (LcswBzxxHVO[]) HYPubBO_Client.queryByCondition(LcswBzxxHVO.class,
							LcswBzxxHVO.PK_BZSQ + " = '" + bzsqvos[i].getPk_bzsq() + "' and nvl(dr,0)=0  and " + LcswBzxxHVO.PK_BZXX + " not in (select "
									+ KcpgBVO.PK_BZXX + " from LCSW_KCPG_B where nvl(dr,0)=0 ) ");

					if (lcswbzxxhvos != null && lcswbzxxhvos.length > 0) {
						for (int j = 0; j < lcswbzxxhvos.length; j++) {
							getBillCardPanel().addLine();
							getBillCardPanel().setBodyValueAt(bzsqvos[i].getPk_jobbasfil(), thisrow, "pk_jobbasfil");
							String jobname = this.execFormular("getColValue(bd_jobbasfil, jobname, pk_jobbasfil, pk_jobbasfil)", bzsqvos[i].getPk_jobbasfil());
							getBillCardPanel().setBodyValueAt(jobname, thisrow, "xm");

							Object[] psncode = (Object[]) iuap.executeQuery("SELECT psncode FROM bd_psndoc WHERE pk_corp = '" + pk_corp
									+ "' and pk_psnbasdoc ='" + lcswbzxxhvos[j].getKcpgry() + "' and nvl(dr,0)=0 ", new ArrayProcessor());
							if (psncode != null && psncode.length > 0) {
								getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getKcpgry(), thisrow, "kcpgry");
								getBillCardPanel().setBodyValueAt(psncode[0], thisrow, "psn");
								String psname = this.execFormular("getColValue(bd_psnbasdoc,psnname,pk_psnbasdoc,kcpgry)", lcswbzxxhvos[j].getKcpgry());
								getBillCardPanel().setBodyValueAt(psname, thisrow, "psname");

							}
							getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getPk_bzxx(), thisrow, "pk_bzxx");
							getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getHm(), thisrow, "hm");
							getBillCardPanel().setBodyValueAt(bzsqvos[i].getPk_bzsq(), thisrow, "pk_bzsq");
							getBillCardPanel().setBodyValueAt(pk_corp, thisrow, "pk_corp");
							
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
					}
					// else {
					// getBillCardPanel().setHeadItem("pk_jobbasfil", "");
					// this.showErrorMessage("该项目没有对应的报装信息！");
					// return;
					// }
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
