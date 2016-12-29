package nc.ui.lcsw.sw07;

import java.util.Hashtable;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardBeforeEditListener;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillItemEvent;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.uif.pub.exception.UifException;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw06.FbyjazVO;
import nc.vo.lcsw.sw07.AzxxfkBVO;
import nc.vo.lcsw.sw07.AzxxfkHVO;
import nc.vo.lcsw.sw11.SjfksjHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDate;

/**
 * <b>��װ�������ݽ�����</b>
 * @version 1.0
 * @since 1.0
 * @author ��չ��
 * @time 2014-09-03 17:20:11
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
		
	}

	public boolean beforeEdit(BillItemEvent e) {
		BillItem item = e.getItem();
		if ("pk_jobbasfil".equals(item.getKey())) {
			UIRefPane ref = (UIRefPane) getBillCardPanel().getHeadItem(SjfksjHVO.PK_JOBBASFIL).getComponent();
//			ref.getRefModel().addWherePart(
//					" and pk_jobbasfil in (select fbyjaz.PK_JOBBASFIL from lcsw_fbyjaz fbyjaz where nvl(fbyjaz.dr,0)=0 and fbyjaz.vbillstatus=1 and fbyjaz.pk_corp='"
//							+ getEnvironment().getCorporation().getPrimaryKey() + "' )  "
//							 + " and pk_jobbasfil not in (select Azxxfk.PK_JOBBASFIL from Lcsw_Azxxfk_h Azxxfk where nvl(Azxxfk.dr,0)=0 and Azxxfk.pk_corp='"
//							 + getEnvironment().getCorporation().getPrimaryKey() + "'  and Azxxfk.vbillstatus='1')");
			//panqh ֻҪ���ƽ����ƽ����ڼ�������װ����
			ref.getRefModel().addWherePart(
					" and pk_jobbasfil in (select fbyjaz.PK_JOBBASFIL from lcsw_fbyjaz fbyjaz where nvl(fbyjaz.dr,0)=0 and fbyjaz.sfyjaz='Y' and fbyjaz.yjazrq is not null and fbyjaz.pk_corp='"
							+ getEnvironment().getCorporation().getPrimaryKey() + "' )  "
							 + " and pk_jobbasfil not in (select Azxxfk.PK_JOBBASFIL from Lcsw_Azxxfk_h Azxxfk where nvl(Azxxfk.dr,0)=0 and Azxxfk.pk_corp='"
							 + getEnvironment().getCorporation().getPrimaryKey() + "'  and Azxxfk.vbillstatus='1')");
		}
		return true;
	
	}
	
	@Override
	public boolean beforeEdit(BillEditEvent e) {
		return true;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void afterEdit(BillEditEvent e) {
		super.afterEdit(e);
		
		//�޸� ʩ�������ˣ�ʩ����ϵ�绰�Զ����� byleefirm
		if (("sgfzr").equalsIgnoreCase(e.getKey()) ){
		    String sgfzr = (String) e.getValue();
		
				String sglxdh=null;
				try {
					sglxdh = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class)
					        .executeQuery("select  docname from bd_defdoc  where memo ="+"'"+ sgfzr+"'"
					        		, new ColumnProcessor(null));
				} catch (BusinessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
			getBillCardPanel().setHeadItem("sglxdh",sglxdh);
	}
		if(e.getKey().equals(AzxxfkHVO.PK_JOBBASFIL)){// ��Ŀ 
//			IAzxxfk azxxfk =NCLocator.getInstance().lookup(IAzxxfk.class);
			
			String pk_jobbasfil=(String) getBillCardPanel().getHeadItem(AzxxfkHVO.PK_JOBBASFIL).getValueObject();
			getBillCardPanel().getBillModel().clearBodyData();//����ձ�����
			if(pk_jobbasfil!=null && pk_jobbasfil.length()>0){
					try {
						//��鷢���ƽ��Ƿ��Ѿ�����
						FbyjazVO[] fbyjvos = (FbyjazVO[]) HYPubBO_Client.queryByCondition(FbyjazVO.class, " pk_jobbasfil ='" + pk_jobbasfil + "' and nvl(dr,0)=0 and sfyjaz='Y' and yjazrq is not null  order by ts desc ");
						if(fbyjvos!=null && fbyjvos.length>0){
							if(fbyjvos[0].getSfyjaz() == null || !fbyjvos[0].getSfyjaz().booleanValue()){
								showErrorMessage("����Ŀ���̻�δ�ƽ���װ��������װ������");
								return;
							}
						}
						
//						String yjazrq= azxxfk.queryAZYJRQ(pk_jobbasfil);
						UFDate yjazrq= fbyjvos[0].getYjazrq();
						if(yjazrq==null){
							getBillCardPanel().getHeadItem(AzxxfkHVO.YJAZRQ).setValue("");
						}
						if(yjazrq!=null && yjazrq.toString().length()>0){
							getBillCardPanel().getHeadItem(AzxxfkHVO.YJAZRQ).setValue(yjazrq);//�ƽ���װ����
						}
						
						getBillCardPanel().getHeadItem(AzxxfkHVO.HTH).setValue(fbyjvos[0].getHth());//��ͬ��
						getBillCardPanel().getHeadItem(AzxxfkHVO.SJTH).setValue(fbyjvos[0].getSjth());//���ͼ��
						
						//and bzzt= "+EmunBzzt.FBYJ+" 
						//���ݱ�װ��Ϣ����  ��ѯ  ��װ����VO 
						BzsqVO[] bzsqvos = (BzsqVO[]) HYPubBO_Client.queryByCondition(BzsqVO.class, " pk_jobbasfil ='" + pk_jobbasfil + "' and nvl(dr,0)=0 and "
								+  BzsqVO.VBILLSTATUS +"='1'  ");
						if(bzsqvos!=null && bzsqvos.length>0){
							
							getBillCardPanel().setHeadItem("gtcz",bzsqvos[0].getGtcz());//��ͬ���� 
							getBillCardPanel().setHeadItem("sfsyx",bzsqvos[0].getSfsyx());//������
							
							
							for (int i = 0; i < bzsqvos.length; i++) {
								LcswBzxxHVO[] lcswbzxxhvos=	(LcswBzxxHVO[]) HYPubBO_Client.queryByCondition(LcswBzxxHVO.class,
										LcswBzxxHVO.PK_BZSQ + " = '" + bzsqvos[i].getPk_bzsq() + "' and nvl(dr,0)=0");
								for (int j = 0; j < lcswbzxxhvos.length; j++) {
									getBillCardPanel().addLine();
									getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getPk_bzxx(), j, AzxxfkBVO.PK_BZXX);//������Ϣ����
									getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getPk_jobbasfil(), j, AzxxfkBVO.PK_JOBBASFIL);//��Ŀ��������
									String jobname = execFormular("getColValue(bd_jobbasfil, jobname, pk_jobbasfil, pk_jobbasfil)", lcswbzxxhvos[j].getPk_jobbasfil());
									getBillCardPanel().setBodyValueAt(jobname, j, "jobbasfil_name");//��Ŀ����
									String bzyh=execFormular("bzyh->getColValue(lcsw_bzxx, hm, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
									getBillCardPanel().setBodyValueAt(bzyh, j, "bzyh");//��װ�û�
									
									getBillCardPanel().setBodyValueAt(bzsqvos[i].getPk_bzsq(), j, AzxxfkBVO.PK_BZSQ);//��װ��������
									
									//������Ŀ���롢��װ��Ϣ��š���װ��ַ����ϵ�ˡ���ϵ�绰
									String jobcode=this.execFormular("getColValue(bd_jobbasfil, jobcode,pk_jobbasfil , pk_jobbasfil)", lcswbzxxhvos[j].getPk_jobbasfil());
									getBillCardPanel().setBodyValueAt(jobcode, j, "jobcode");//��Ŀ����
									String bzbh=this.execFormular("getColValue(lcsw_bzxx, bzbh, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
									getBillCardPanel().setBodyValueAt(bzbh, j, "bzbh");//��װ��Ϣ���
									String azdz=this.execFormular("getColValue(lcsw_bzxx, azdz, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
									getBillCardPanel().setBodyValueAt(azdz, j, "azdz");//��װ��ַ
									String lxr=this.execFormular("getColValue(lcsw_bzxx, lxr, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
									getBillCardPanel().setBodyValueAt(lxr, j, "lxr");//��ϵ��
									String lxdh=this.execFormular("getColValue(lcsw_bzxx, lxdd, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
									getBillCardPanel().setBodyValueAt(lxdh, j, "lxdh");//��ϵ�绰
									
								}
							}
						
						}
						else
							showErrorMessage("����Ŀû�ж�Ӧ��װ��Ϣ��");
//						LcswBzxxHVO[] lcswbzxxhvos = (LcswBzxxHVO[]) HYPubBO_Client.queryByCondition(LcswBzxxHVO.class," PK_JOBBASFIL='"+pk_jobbasfil+"'" 
//								+" and nvl(dr,0)=0 and pk_bzxx not in (select pk_bzxx from lcsw_azxxfk_b where pk_bzxx is not null and nvl(dr,0)=0)");
//						if(lcswbzxxhvos != null && lcswbzxxhvos.length > 0){
					} catch (UifException e1) {
						e1.printStackTrace();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
			}
		}if(AzxxfkHVO.SFYJSY.equals(e.getKey())){
			String sjyjsj=getBillCardPanel().getHeadItem(AzxxfkHVO.SFYJSY).getValue();
			if(sjyjsj.equals("true")){
				try {
					getBillCardPanel().dataNotNullValidate();
					getBillCardPanel().getHeadItem(AzxxfkHVO.YJBWYSRQ).setValue(new UFDate());
				} catch (ValidationException e1) {
					getBillCardPanel().getHeadItem(AzxxfkHVO.SFYJSY).setValue("false");
					showWarningMessage(e1.toString());
					e1.printStackTrace();
				}
			}else{
				getBillCardPanel().getHeadItem(AzxxfkHVO.YJBWYSRQ).setValue("");
			}
		}
		if(e.getKey().equals(AzxxfkHVO.PK_JOBBASFIL)||e.getKey().equals(AzxxfkHVO.AZFKRQ)||e.getKey().equals(AzxxfkHVO.AZRQ)||
				e.getKey().equals(AzxxfkHVO.HTH)||e.getKey().equals(AzxxfkHVO.JGRQ)||e.getKey().equals(AzxxfkHVO.REMARK)||
				e.getKey().equals(AzxxfkHVO.WGYQTS)||e.getKey().equals(AzxxfkHVO.YJAZRQ)||e.getKey().equals(AzxxfkHVO.YQXGR)||
				e.getKey().equals(AzxxfkHVO.YQXGRQ)||e.getKey().equals(AzxxfkHVO.YSRY)||e.getKey().equals(AzxxfkHVO.YSDH)||
				e.getKey().equals(AzxxfkHVO.ZLFKRQ)||e.getKey().equals(AzxxfkHVO.KGRQ)||e.getKey().equals(AzxxfkHVO.ZBRQ)||
				e.getKey().equals(AzxxfkHVO.BIAOWEI)||e.getKey().equals(AzxxfkHVO.AZBJ)||e.getKey().equals(AzxxfkHVO.BIAOBIE)||
				e.getKey().equals(AzxxfkHVO.CHANDI)||e.getKey().equals(AzxxfkHVO.CKXD)||e.getKey().equals(AzxxfkHVO.BIAOMA)||
				e.getKey().equals(AzxxfkHVO.BIANMA)){
			//add by crf 2015-07-08 ���ñ����Զ����£����������������ű����ݸ��¶����£�ͬʱ���������޸ı������ݵĹ��ܡ�
			int rowcount = getBillCardPanel().getRowCount();
			//��Ŀ����װ�������ڡ���װ���ڡ���ͬ�š��������ڡ���ע
			//�깤�����������ƽ���װ���ڡ������޸��ˡ������޸����ڡ�������Ա�����յ绰
			//���Ϸ������ڡ��������ڡ�װ�����ڡ���λ����װ����ˮ������
			//���ء������жȡ����롢����
			String pk_jobbasfil=getBillCardPanel().getHeadItem(AzxxfkHVO.PK_JOBBASFIL).getValue();
			String azfkrq=getBillCardPanel().getHeadItem(AzxxfkHVO.AZFKRQ).getValue();
			String azrq=getBillCardPanel().getHeadItem(AzxxfkHVO.AZRQ).getValue();
			String hth=getBillCardPanel().getHeadItem(AzxxfkHVO.HTH).getValue();
			String jgrq=getBillCardPanel().getHeadItem(AzxxfkHVO.JGRQ).getValue();
			String remark=getBillCardPanel().getHeadItem(AzxxfkHVO.REMARK).getValue();
			String wgyqts=getBillCardPanel().getHeadItem(AzxxfkHVO.WGYQTS).getValue();
			String yjazrq=getBillCardPanel().getHeadItem(AzxxfkHVO.YJAZRQ).getValue();
			String yqxgr=getBillCardPanel().getHeadItem(AzxxfkHVO.YQXGR).getValue();
			String yqxgrq=getBillCardPanel().getHeadItem(AzxxfkHVO.YQXGRQ).getValue();
			String ysry=getBillCardPanel().getHeadItem(AzxxfkHVO.YSRY).getValue();
			String ysdh=getBillCardPanel().getHeadItem(AzxxfkHVO.YSDH).getValue();
			String zlfkrq=getBillCardPanel().getHeadItem(AzxxfkHVO.ZLFKRQ).getValue();
			String kgrq=getBillCardPanel().getHeadItem(AzxxfkHVO.KGRQ).getValue();
			String zbrq=getBillCardPanel().getHeadItem(AzxxfkHVO.ZBRQ).getValue();
			String biaowei=getBillCardPanel().getHeadItem(AzxxfkHVO.BIAOWEI).getValue();
			String azbj=getBillCardPanel().getHeadItem(AzxxfkHVO.AZBJ).getValue();
			String biaobie=getBillCardPanel().getHeadItem(AzxxfkHVO.BIAOBIE).getValue();
			String chandi=getBillCardPanel().getHeadItem(AzxxfkHVO.CHANDI).getValue();
			String ckxd=getBillCardPanel().getHeadItem(AzxxfkHVO.CKXD).getValue();
			String biaoma=getBillCardPanel().getHeadItem(AzxxfkHVO.BIAOMA).getValue();
			String bianma=getBillCardPanel().getHeadItem(AzxxfkHVO.BIANMA).getValue();
			
			//panqh ע�͵�������������ò�ͬ��ֵ
//			for (int i = 0; i < rowcount ; i++) {
//				getBillCardPanel().setBodyValueAt(pk_jobbasfil, i, AzxxfkHVO.PK_JOBBASFIL);
//				getBillCardPanel().setBodyValueAt(azfkrq, i, AzxxfkHVO.AZFKRQ);
//				getBillCardPanel().setBodyValueAt(azrq, i, AzxxfkHVO.AZRQ);
//				getBillCardPanel().setBodyValueAt(hth, i, AzxxfkHVO.HTH);
//				getBillCardPanel().setBodyValueAt(jgrq, i, AzxxfkHVO.JGRQ);
//				getBillCardPanel().setBodyValueAt(remark, i, AzxxfkHVO.REMARK);
//				getBillCardPanel().setBodyValueAt(wgyqts, i, AzxxfkHVO.WGYQTS);
//				getBillCardPanel().setBodyValueAt(yjazrq, i, AzxxfkHVO.YJAZRQ);
//				getBillCardPanel().setBodyValueAt(yqxgr, i, AzxxfkHVO.YQXGR);
//				getBillCardPanel().setBodyValueAt(yqxgrq, i, AzxxfkHVO.YQXGRQ);
//				getBillCardPanel().setBodyValueAt(ysry, i, AzxxfkHVO.YSRY);
//				getBillCardPanel().setBodyValueAt(ysdh, i, AzxxfkHVO.YSDH);
//				getBillCardPanel().setBodyValueAt(zlfkrq, i, AzxxfkHVO.ZLFKRQ);
//				getBillCardPanel().setBodyValueAt(kgrq, i, AzxxfkHVO.KGRQ);
//				getBillCardPanel().setBodyValueAt(zbrq, i, AzxxfkHVO.ZBRQ);
//				getBillCardPanel().setBodyValueAt(biaowei, i, AzxxfkHVO.BIAOWEI);
//				getBillCardPanel().setBodyValueAt(azbj, i, AzxxfkHVO.AZBJ);
//				getBillCardPanel().setBodyValueAt(biaobie, i, AzxxfkHVO.BIAOBIE);
//				getBillCardPanel().setBodyValueAt(chandi, i, AzxxfkHVO.CHANDI);
//				getBillCardPanel().setBodyValueAt(ckxd, i, AzxxfkHVO.CKXD);
//				getBillCardPanel().setBodyValueAt(biaoma, i, AzxxfkHVO.BIAOMA);
//				getBillCardPanel().setBodyValueAt(bianma, i, AzxxfkHVO.BIANMA);
//			}
			//end
			//end panqh
		}
	
	}
	
	@Override
	public Object getUserObject() {
		return new nc.vo.lcsw.sw07.SW07GetCheckClass();
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
}
