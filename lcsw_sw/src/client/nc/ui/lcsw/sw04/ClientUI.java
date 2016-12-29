/**    Create By NCPlugin beta 0.1.   **/
package nc.ui.lcsw.sw04;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JComponent;

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
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw03.KcpgHVO;
import nc.vo.lcsw.sw04.KcfksjBVO;
import nc.vo.lcsw.sw04.KcfksjHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDate;
import nc.vo.trade.button.ButtonVO;
import nc.vo.trade.field.BillField;
import nc.vo.trade.pub.IBillStatus;

/**
 * ���췴������
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
		// �ύ��������޸Ĳ���ɾ��
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
				new Integer(IBillStatus.FREE), new UFDate(new Date()), new UFDate(new Date()) // �Ƶ�����
		};

		for (int i = 0; i < itemkeys.length; i++) {
			BillItem item = null;
			item = getBillCardPanel().getHeadItem(itemkeys[i]);
			if (item == null)
				item = getBillCardPanel().getTailItem(itemkeys[i]);
			if (item != null)
				item.setValue(values[i]);
		}
		
		//zhujie�������ˡ�Ĭ�ϵ�½��Ա����
		JComponent jComponent = getBillCardPanel().getHeadItem("jbry").getComponent();
		if(jComponent instanceof UIRefPane) {
			Vector matchData = ((UIRefPane)jComponent).getRefModel().matchData("docname", ClientEnvironment.getInstance().getUser().getUserName());
			if(matchData != null && matchData.size() > 0) {
				((UIRefPane)jComponent).setPK((String) ((Vector)matchData.get(0)).get(2));
			}
		}
		
		//----add by crf 2015-06-15
		getBillCardPanel().setHeadItem("kcfkrq", new UFDate(new Date()));
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

	// ��ͷ
	public boolean beforeEdit(BillItemEvent e) {
		BillCardPanel billCardPanel = getBillCardPanel();
		BillItem item = e.getItem();
		if ("pk_jobbasfil".equals(item.getKey())) {
			UIRefPane ref = (UIRefPane) billCardPanel.getHeadItem("pk_jobbasfil").getComponent();
			ref.getRefModel().addWherePart(
					" and pk_jobbasfil in (select kcpg.PK_JOBBASFIL from LCSW_KCPG_H kcpg where nvl(kcpg.dr,0)=0 and kcpg.vbillstatus='1' and kcpg.pk_corp='"
					+ getEnvironment().getCorporation().getPrimaryKey() + "' )  "
					+ " and pk_jobbasfil not in (select kcfksj.PK_JOBBASFIL from lcsw_kcfksj_h kcfksj where nvl(kcfksj.dr,0)=0 and kcfksj.pk_corp='"
					+ getEnvironment().getCorporation().getPrimaryKey() + "'  and kcfksj.vbillstatus='1')");
		}
		return true;
	}

	// ����
	@Override
	public boolean beforeEdit(BillEditEvent e) {
		// TODO Auto-generated method stub
		// ȡ�ÿ�Ƭ
		IUAPQueryBS iuap = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class);
		BillCardPanel billCardPanel = getBillCardPanel();
		String key = e.getKey();

		// ѡ����
		int row = e.getRow();

		if (e.getPos() == BillItem.BODY) {
		}
		return super.beforeEdit(e);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void afterEdit(BillEditEvent e) {
		// ȡ�ÿ�Ƭ
		IUAPQueryBS iuap = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class);
		BillCardPanel billCardPanel = getBillCardPanel();
		String key = e.getKey();
		// ѡ����
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
			}else if(key.equals(KcfksjHVO.JBRY)||key.equals(KcfksjHVO.KCRY)||key.equals(KcfksjHVO.KCSJRY)||
					key.equals(KcfksjHVO.YQXGR)||key.equals(KcfksjHVO.KCRQ)||key.equals(KcfksjHVO.KCFKRQ)||
					key.equals(KcfksjHVO.JHXTRQ)||key.equals(KcfksjHVO.YJHHXRQ)||key.equals(KcfksjHVO.SJPSWCRQ)||
					key.equals(KcfksjHVO.YJSJPSRQ)||key.equals(KcfksjHVO.YJSJRQ)||key.equals(KcfksjHVO.YQXGRQ)
					||key.equals(KcfksjHVO.WGYQTS)){
				String jbry = getBillCardPanel().getHeadItem(KcfksjHVO.JBRY).getValue();
				String kcry = getBillCardPanel().getHeadItem(KcfksjHVO.KCRY).getValue();
				String kcsjry = getBillCardPanel().getHeadItem(KcfksjHVO.KCSJRY).getValue();
				String yqxgr = getBillCardPanel().getHeadItem(KcfksjHVO.YQXGR).getValue();
				// ��������
				String kcrq = getBillCardPanel().getHeadItem(KcfksjHVO.KCRQ).getValue();
				// ���췴������
				String kcfkrq = getBillCardPanel().getHeadItem(KcfksjHVO.KCFKRQ).getValue();
				// �Ӻ���ͼ����
				String jhxtrq = getBillCardPanel().getHeadItem(KcfksjHVO.JHXTRQ).getValue();
				// �ƽ�����������
				String yjhhxrq = getBillCardPanel().getHeadItem(KcfksjHVO.YJHHXRQ).getValue();
				// ��������������
				String sjpswcrq = getBillCardPanel().getHeadItem(KcfksjHVO.SJPSWCRQ).getValue();
				// �ƽ������������
				String yjsjpsrq = getBillCardPanel().getHeadItem(KcfksjHVO.YJSJPSRQ).getValue();
				// �ƽ��������
				String yjsjrq = getBillCardPanel().getHeadItem(KcfksjHVO.YJSJRQ).getValue();
				// �����޸�����
				String yqxgrq = getBillCardPanel().getHeadItem(KcfksjHVO.YQXGRQ).getValue();
				// �깤��������
				String wgyqts = getBillCardPanel().getHeadItem(KcfksjHVO.WGYQTS).getValue();
				for (int i = 0; i < rowcount; i++) {
					getBillCardPanel().setBodyValueAt(jbry, i, KcfksjBVO.JBRY);
					getBillCardPanel().setBodyValueAt(kcry, i, KcfksjBVO.KCRY);
					getBillCardPanel().setBodyValueAt(kcsjry, i, KcfksjBVO.KCSJRY);
					getBillCardPanel().setBodyValueAt(yqxgr, i, KcfksjBVO.YQXGR);
					getBillCardPanel().setBodyValueAt(kcrq, i, KcfksjBVO.KCRQ);
					getBillCardPanel().setBodyValueAt(kcfkrq, i, KcfksjBVO.KCFKRQ);
					getBillCardPanel().setBodyValueAt(jhxtrq, i, KcfksjBVO.JHXTRQ);
					getBillCardPanel().setBodyValueAt(yjhhxrq, i, KcfksjBVO.YJHHXRQ);
					getBillCardPanel().setBodyValueAt(sjpswcrq, i, KcfksjBVO.SJPSWCRQ);
					getBillCardPanel().setBodyValueAt(yjsjpsrq, i, KcfksjBVO.YJSJPSRQ);
					getBillCardPanel().setBodyValueAt(yjsjrq, i, KcfksjBVO.YJSJRQ);
					getBillCardPanel().setBodyValueAt(yqxgrq, i, KcfksjBVO.YQXGRQ);
					getBillCardPanel().setBodyValueAt(wgyqts, i, KcfksjBVO.WGYQTS);
				}
			}
		}

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
		if(KcfksjHVO.SFYJSJ.equals(e.getKey())){
			String sjyjsj=getBillCardPanel().getHeadItem(KcfksjHVO.SFYJSJ).getValue();
			if(sjyjsj.equals("true")){
				try {
					getBillCardPanel().dataNotNullValidate();
					getBillCardPanel().getHeadItem(KcfksjHVO.YJSJRQ).setValue(new UFDate());
				} catch (ValidationException e1) {
					getBillCardPanel().getHeadItem(KcfksjHVO.SFYJSJ).setValue("false");
					showWarningMessage(e1.toString());
					e1.printStackTrace();
				}
			}else{
				getBillCardPanel().getHeadItem(KcfksjHVO.YJSJRQ).setValue("");
			}
		}
		// getBillCardPanel().getBillModel().loadLoadRelationItemValue();
		super.afterEdit(e);
	}

	/**
	 * ��ͷ��Ŀ�༭����
	 * 
	 * @param iuap
	 * @param rowcount
	 * @param pk_corp
	 * @throws Exception
	 */
	private void setxm(IUAPQueryBS iuap, int rowcount, String pk_corp) throws Exception {
		int thisrow = 0;
		String pk_jobbasfil = getBillCardPanel().getHeadItem("pk_jobbasfil").getValue();
		if (pk_jobbasfil != null) {
			BzsqVO[] bzsqvos = (BzsqVO[]) HYPubBO_Client.queryByCondition(BzsqVO.class, " pk_jobbasfil ='" + pk_jobbasfil + "' and nvl(dr,0)=0 and "
					+ BzsqVO.VBILLSTATUS + " = '1' and bzzt= " + EmunBzzt.KCPG + " ");
			if (bzsqvos != null && bzsqvos.length > 0 && rowcount < 1) {

				LcswBzxxHVO[] bzxxcheckvo = (LcswBzxxHVO[]) HYPubBO_Client.queryByCondition(LcswBzxxHVO.class, LcswBzxxHVO.PK_JOBBASFIL + " = '" + pk_jobbasfil
						+ "' and nvl(dr,0)=0 and PK_BZXX not in (select pk_bzxx from lcsw_kcfksj_b where PK_JOBBASFIL='" + pk_jobbasfil
						+ "' and nvl(dr,0)=0)  ");

				if (bzxxcheckvo == null || bzxxcheckvo.length < 1) {
					getBillCardPanel().setHeadItem("pk_jobbasfil", "");
					this.showErrorMessage("����Ŀû�ж�Ӧ�ı�װ��Ϣ,���߶�Ӧ��װ��Ϣ�Ѿ����˷����ƽ���װ����");
					return;
				}

				for (int i = 0; i < bzsqvos.length; i++) {
					LcswBzxxHVO[] lcswbzxxhvos = (LcswBzxxHVO[]) HYPubBO_Client.queryByCondition(LcswBzxxHVO.class,
							LcswBzxxHVO.PK_BZSQ + " = '" + bzsqvos[i].getPk_bzsq() + "' and nvl(dr,0)=0 and bzzt= " + EmunBzzt.KCPG + " ");
					if (lcswbzxxhvos != null && lcswbzxxhvos.length > 0) {

					} else {
						getBillCardPanel().setHeadItem("pk_jobbasfil", "");
						this.showErrorMessage("����Ŀû�ж�Ӧ�ı�װ��Ϣ��");
						return;
					}
				}
				
				
				//������װ���� <�Ƿ�������>��<�Ƿ�ͬ����> 
				BzsqVO[] bzsqHVO=(BzsqVO[])HYPubBO_Client.queryByCondition(BzsqVO.class, " pk_jobbasfil ='" + pk_jobbasfil + "' and nvl(dr,0)=0 and "
						+  BzsqVO.VBILLSTATUS +"='1'  order by ts desc ");
				if(bzsqHVO!=null && bzsqHVO.length>0){
					if(bzsqHVO[0].getGtcz() !=null)
						getBillCardPanel().getHeadItem("gtcz").setValue(bzsqHVO[0].getGtcz());
					if(bzsqHVO[0].getSfsyx() !=null )
						getBillCardPanel().getHeadItem("sfsyx").setValue(bzsqHVO[0].getSfsyx());
				}

				for (int i = 0; i < bzsqvos.length; i++) {
					LcswBzxxHVO[] lcswbzxxhvos = (LcswBzxxHVO[]) HYPubBO_Client.queryByCondition(LcswBzxxHVO.class,
							LcswBzxxHVO.PK_BZSQ + " = '" + bzsqvos[i].getPk_bzsq() + "' and nvl(dr,0)=0 and bzzt= " + EmunBzzt.KCPG + " ");
					if (lcswbzxxhvos != null && lcswbzxxhvos.length > 0) {
						for (int j = 0; j < lcswbzxxhvos.length; j++) {
							getBillCardPanel().addLine();
							getBillCardPanel().setBodyValueAt(bzsqvos[i].getPk_jobbasfil(), thisrow, "pk_jobbasfil");
							String jobname = this.execFormular("getColValue(bd_jobbasfil, jobname, pk_jobbasfil, pk_jobbasfil)", bzsqvos[i].getPk_jobbasfil());
							getBillCardPanel().setBodyValueAt(jobname, thisrow, "xm");

							// ������Ա
							Object[] jbrcode = (Object[]) iuap.executeQuery("SELECT psncode FROM bd_psndoc WHERE pk_corp = '" + pk_corp
									+ "' and pk_psnbasdoc ='" + lcswbzxxhvos[j].getBzjbry() + "' and nvl(dr,0)=0 ", new ArrayProcessor());
							if (jbrcode != null && jbrcode.length > 0) {
								getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getBzjbry(), thisrow, "jbry");
								getBillCardPanel().setBodyValueAt(jbrcode[0], thisrow, "jbrcode");
								String psname = this.execFormular("getColValue(bd_psnbasdoc,psnname,pk_psnbasdoc,jbry)", lcswbzxxhvos[j].getBzjbry());
								getBillCardPanel().setBodyValueAt(psname, thisrow, "jbrname");

							}

							// ������Ա
							Object[] kcrcode = (Object[]) iuap.executeQuery("SELECT psncode FROM bd_psndoc WHERE pk_corp = '" + pk_corp
									+ "' and pk_psnbasdoc ='" + lcswbzxxhvos[j].getKcry() + "' and nvl(dr,0)=0 ", new ArrayProcessor());
							if (kcrcode != null && kcrcode.length > 0) {
								getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getKcry(), thisrow, "kcry");
								getBillCardPanel().setBodyValueAt(kcrcode[0], thisrow, "kcrcode");
								String kcrname = this.execFormular("getColValue(bd_psnbasdoc,psnname,pk_psnbasdoc,kcry)", lcswbzxxhvos[j].getKcry());
								getBillCardPanel().setBodyValueAt(kcrname, thisrow, "kcrname");

							}

							// ���������Ա
							Object[] kcsjrcode = (Object[]) iuap.executeQuery("SELECT psncode FROM bd_psndoc WHERE pk_corp = '" + pk_corp
									+ "' and pk_psnbasdoc ='" + lcswbzxxhvos[j].getKcsjry() + "' and nvl(dr,0)=0 ", new ArrayProcessor());
							if (kcsjrcode != null && kcsjrcode.length > 0) {
								getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getKcsjry(), thisrow, "kcsjry");
								getBillCardPanel().setBodyValueAt(kcsjrcode[0], thisrow, "kcsjrcode");
								String kcsjrname = this.execFormular("getColValue(bd_psnbasdoc,psnname,pk_psnbasdoc,kcsjry)", lcswbzxxhvos[j].getKcsjry());
								getBillCardPanel().setBodyValueAt(kcsjrname, thisrow, "kcsjrname");

							}

							getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getPk_bzxx(), thisrow, "pk_bzxx");
							getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getHm(), thisrow, "hm");
							getBillCardPanel().setBodyValueAt(bzsqvos[i].getPk_bzsq(), thisrow, "pk_bzsq");
							getBillCardPanel().setBodyValueAt(pk_corp, thisrow, "pk_corp");
							
							//������Ŀ���롢��װ��Ϣ��š���װ��ַ����ϵ�ˡ���ϵ�绰
							String jobcode=this.execFormular("getColValue(bd_jobbasfil, jobcode,pk_jobbasfil , pk_jobbasfil)", lcswbzxxhvos[j].getPk_jobbasfil());
							getBillCardPanel().setBodyValueAt(jobcode, thisrow, "jobcode");//��Ŀ����
							String bzbh=this.execFormular("getColValue(lcsw_bzxx, bzbh, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
							getBillCardPanel().setBodyValueAt(bzbh, thisrow, "bzbh");//��װ��Ϣ���
							String azdz=this.execFormular("getColValue(lcsw_bzxx, azdz, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
							getBillCardPanel().setBodyValueAt(azdz, thisrow, "azdz");//��װ��ַ
							String lxr=this.execFormular("getColValue(lcsw_bzxx, lxr, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
							getBillCardPanel().setBodyValueAt(lxr, thisrow, "lxr");//��ϵ��
							String lxdh=this.execFormular("getColValue(lcsw_bzxx, lxdd, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
							getBillCardPanel().setBodyValueAt(lxdh, thisrow, "lxdh");//��ϵ�绰
							
							thisrow++;
						}
					} else {
						getBillCardPanel().setHeadItem("pk_jobbasfil", "");
						this.showErrorMessage("����Ŀû�ж�Ӧ�ı�װ��Ϣ��");
						return;
					}
				}
			} else {
				getBillCardPanel().setHeadItem("pk_jobbasfil", "");
				this.showErrorMessage("����Ŀû�ж�Ӧ�ı�װ���룡");
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
				// ��vlues����toString(vlues)ת����
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
	 * ���÷����������ֶ�����ȡĳObject�����ֶε�ֵ
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
