package nc.ui.lcsw.sw11;

import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JComponent;

import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardBeforeEditListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillItemEvent;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.uif.pub.exception.UifException;
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw04.KcfksjHVO;
import nc.vo.lcsw.sw11.SjfksjBVO;
import nc.vo.lcsw.sw11.SjfksjHVO;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * <b>设计反馈数据界面类</b>
 * @version 1.0
 * @since 1.0
 * @author 梁展轩
 * @time 2014-08-14 15:16:25
 */
public class ClientUI extends BillManageUI implements BillCardBeforeEditListener{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected AbstractManageController createController() {
		return new ClientCtrl();
	}

	@Override
	protected ManageEventHandler createEventHandler() {
		return new ClientEventHandler(this, createController());
	}
	
	@Override
	public void setBodySpecialData(CircularlyAccessibleValueObject[] vos)
			throws Exception {

	}

	@Override
	protected void setHeadSpecialData(CircularlyAccessibleValueObject vo,
			int intRow) throws Exception {

	}

	@Override
	protected void setTotalHeadSpecialData(CircularlyAccessibleValueObject[] vos)
			throws Exception {

	}

	@Override
	protected void initSelfData() {
		getBillCardPanel().addBillEditListenerHeadTail(this);
		getBillCardPanel().setBillBeforeEditListenerHeadTail(this);
	}

	@Override
	public void setDefaultData() throws Exception {
		//zhujie“经办人”默认登陆人员名字
		JComponent jComponent = getBillCardPanel().getHeadItem("jbry").getComponent();
		if(jComponent instanceof UIRefPane) {
			Vector matchData = ((UIRefPane)jComponent).getRefModel().matchData("docname", ClientEnvironment.getInstance().getUser().getUserName());
			if(matchData != null && matchData.size() > 0) {
				((UIRefPane)jComponent).setPK((String) ((Vector)matchData.get(0)).get(2));
			}
		}
	}

//	@Override
//	public boolean beforeEdit(BillEditEvent e) {
////		BillItem item = e.getItem();
//		if (SjfksjHVO.PK_JOBBASFIL.equals(e.getKey())) {
//			UIRefPane ref = (UIRefPane) getBillCardPanel().getHeadItem("pk_jobbasfil").getComponent();
//			ref.getRefModel().addWherePart(
//					" and pk_jobbasfil in (select sjfksj.PK_JOBBASFIL from lcsw_sjfksj_h sjfksj where nvl(sjfksj.dr,0)=0 and sjfksj.vbillstatus='1' and sjfksj.pk_corp='"
//							+ getBillCardPanel().getHeadItem("pk_corp").getValue() + "' )  ");
//		}
//		return super.beforeEdit(e);
//	}
	
	
	
	public boolean beforeEdit(BillItemEvent e) {
		BillItem item = e.getItem();
		if ("pk_jobbasfil".equals(item.getKey())) {
			UIRefPane ref = (UIRefPane) getBillCardPanel().getHeadItem(SjfksjHVO.PK_JOBBASFIL).getComponent();
			ref.getRefModel().addWherePart(
					" and pk_jobbasfil in (select kcfksj.PK_JOBBASFIL from lcsw_kcfksj_h kcfksj where nvl(kcfksj.dr,0)=0 and kcfksj.vbillstatus=1 and kcfksj.pk_corp='"
							+ getEnvironment().getCorporation().getPrimaryKey() + "' )  "
							+ " and pk_jobbasfil not in (select Sjfksj.PK_JOBBASFIL from Lcsw_Sjfksj_h Sjfksj where nvl(Sjfksj.dr,0)=0 and Sjfksj.pk_corp='"
							 + getEnvironment().getCorporation().getPrimaryKey() + "'  and Sjfksj.vbillstatus='1')");
		}
		return true;
	
	}
	
	@Override
	public void afterEdit(BillEditEvent e) {
		super.afterEdit(e);
		if(e.getKey().equals(SjfksjHVO.PK_JOBBASFIL)){// 项目 
			String pk_jobbasfil=(String) getBillCardPanel().getHeadItem(SjfksjHVO.PK_JOBBASFIL).getValueObject();
			getBillCardPanel().getBillModel().clearBodyData();//先清空表体行
			if(pk_jobbasfil!=null && pk_jobbasfil.length()>0){
					try {
						//根据报装信息主键  查询  报装申请VO 
						BzsqVO[] bzsqvos = (BzsqVO[]) HYPubBO_Client.queryByCondition(BzsqVO.class, " pk_jobbasfil ='" + pk_jobbasfil + "' and nvl(dr,0)=0 and "
								+  BzsqVO.VBILLSTATUS +"='1'  and bzzt= "+EmunBzzt.KCCG+" ");
						if(bzsqvos!=null && bzsqvos.length>0){
//							
//						LcswBzxxHVO[] lcswbzxxhvos = (LcswBzxxHVO[]) HYPubBO_Client.queryByCondition(LcswBzxxHVO.class," PK_JOBBASFIL='"+pk_jobbasfil+"'" 
//								+" and nvl(dr,0)=0 and pk_bzxx not in (select pk_bzxx from lcsw_sjfksj_b where pk_bzxx is not null and nvl(dr,0)=0)");
							
							//带出报装申请 <是否三优先>、<是否共同出资> 
							BzsqVO[] bzsqHVO=(BzsqVO[])HYPubBO_Client.queryByCondition(BzsqVO.class, " pk_jobbasfil ='" + pk_jobbasfil + "' and nvl(dr,0)=0 and "
									+  BzsqVO.VBILLSTATUS +"='1'  order by ts desc ");
							if(bzsqHVO!=null && bzsqHVO.length>0){
								if(bzsqHVO[0].getGtcz() !=null)
									getBillCardPanel().getHeadItem("gtcz").setValue(bzsqHVO[0].getGtcz());
								if(bzsqHVO[0].getSfsyx() !=null )
									getBillCardPanel().getHeadItem("sfsyx").setValue(bzsqHVO[0].getSfsyx());
							}
							
							
							//带出上游<勘察反馈> 的 移交设计日期
							KcfksjHVO[] kcfksjvos = (KcfksjHVO[]) HYPubBO_Client.queryByCondition(KcfksjHVO.class, " pk_jobbasfil ='" + pk_jobbasfil + "' and nvl(dr,0)=0 order　by　ts desc");
							if (kcfksjvos != null && kcfksjvos.length > 0 ) {	
								
								if(kcfksjvos!=null && kcfksjvos.length>0){
									if(kcfksjvos[0].getSfyjsj() == null || !kcfksjvos[0].getSfyjsj().booleanValue()){
										showErrorMessage("该项目工程还未勘察，不允许设计反馈！");
										return;
									}
								}
								
								if(kcfksjvos[0].getYjsjrq()!=null && kcfksjvos[0].toString().length() > 0){
									getBillCardPanel().setHeadItem(SjfksjHVO.YJSJRQ,kcfksjvos[0].getYjsjrq());
								}
							}
							
								for (int i = 0; i < bzsqvos.length; i++) {
									LcswBzxxHVO[] lcswbzxxhvos=	(LcswBzxxHVO[]) HYPubBO_Client.queryByCondition(LcswBzxxHVO.class,
											LcswBzxxHVO.PK_BZSQ + " = '" + bzsqvos[i].getPk_bzsq() + "' and nvl(dr,0)=0");
									for (int j = 0; j < lcswbzxxhvos.length; j++) {
										getBillCardPanel().addLine();
										getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getPk_bzxx(), j, SjfksjBVO.PK_BZXX);//报装信息主键
										getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getPk_jobbasfil(), j, SjfksjBVO.PK_JOBBASFIL);//项目档案主键
										String jobname = execFormular("getColValue(bd_jobbasfil, jobname, pk_jobbasfil, pk_jobbasfil)", lcswbzxxhvos[j].getPk_jobbasfil());
										getBillCardPanel().setBodyValueAt(jobname, j, "jobbasfil_name");//项目参照
										String bzyh=execFormular("bzyh->getColValue(lcsw_bzxx, hm, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
										getBillCardPanel().setBodyValueAt(bzyh, j, "bzyh");//报装用户
										
										getBillCardPanel().setBodyValueAt(bzsqvos[i].getPk_bzsq(), j, SjfksjBVO.PK_BZSQ);//报装申请主键
										
										//增加项目编码、报装信息编号、安装地址、联系人、联系电话
										String jobcode=this.execFormular("getColValue(bd_jobbasfil, jobcode,pk_jobbasfil , pk_jobbasfil)", lcswbzxxhvos[j].getPk_jobbasfil());
										getBillCardPanel().setBodyValueAt(jobcode, j, "jobcode");//项目编码
										String bzbh=this.execFormular("getColValue(lcsw_bzxx, bzbh, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
										getBillCardPanel().setBodyValueAt(bzbh, j, "bzbh");//报装信息编号
										String azdz=this.execFormular("getColValue(lcsw_bzxx, azdz, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
										getBillCardPanel().setBodyValueAt(azdz, j, "azdz");//安装地址
										String lxr=this.execFormular("getColValue(lcsw_bzxx, lxr, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
										getBillCardPanel().setBodyValueAt(lxr, j, "lxr");//联系人
										String lxdh=this.execFormular("getColValue(lcsw_bzxx, lxdd, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
										getBillCardPanel().setBodyValueAt(lxdh, j, "lxdh");//联系电话
										
									}
								}
						}else{
								showErrorMessage("该项目没有对应报装信息！");
						}
						
					} catch (UifException e1) {
						e1.printStackTrace();
					}
			}
		}
	}
	
	@Override
	public Object getUserObject() {
		return new nc.vo.lcsw.sw11.SWGetCheckClass();
	}

	@Override
	public void update(java.util.Observable o, java.lang.Object arg) {
		super.update(o,arg);
		try {
			if (null !=getBufferData().getCurrentVO()) {
			int billstate  =  getButtonManager().getBillStatus(getBufferData().getCurrentVO().getParentVO());
			if (nc.vo.pub.pf.IPfRetBackCheckInfo.NOSTATE_UIFACTORY ==billstate ){
				getButtonManager().getButton(nc.ui.trade.button.IBillButton.Delete).setEnabled(true);
				getButtonManager().getButton(nc.ui.trade.button.IBillButton.Edit).setEnabled(true);
			}else{
				getButtonManager().getButton(nc.ui.trade.button.IBillButton.Delete).setEnabled(false);
				getButtonManager().getButton(nc.ui.trade.button.IBillButton.Edit).setEnabled(false);
			}
				updateButtons();
			}
		} catch (Exception e) {
			e.printStackTrace();
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
}
