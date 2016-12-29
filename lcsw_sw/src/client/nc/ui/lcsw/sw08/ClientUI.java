package nc.ui.lcsw.sw08;

import java.util.Hashtable;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.lcsw.sw08.ClientCtrl;
import nc.ui.lcsw.sw08.ClientEventHandler;
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
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw07.AzxxfkBVO;
import nc.vo.lcsw.sw07.AzxxfkHVO;
import nc.vo.lcsw.sw08.YsfksjBVO;
import nc.vo.lcsw.sw08.YsfksjHVO;
import nc.vo.lcsw.sw11.SjfksjHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.trade.field.BillField;
import nc.vo.trade.pub.IBillStatus;

/**
 * <b>���շ�����Ϣ������</b>
 * @version 1.0
 * @since 1.0
 * @author xuns
 * @time 2014-08-21 11:55:18
 */
public class ClientUI extends BillManageUI  implements BillCardBeforeEditListener{

	private static final long serialVersionUID = 1L;
	
	public ClientUI() {
		super();
		getBillCardPanel().addBillEditListenerHeadTail(this);
		getBillCardPanel().setBillBeforeEditListenerHeadTail(this);
	}
	
	@Override
	protected void initPrivateButton() {
		super.initPrivateButton();
	}
	
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
		
	}

	@Override
	public void setDefaultData() throws Exception {
		BillField fileDef = BillField.getInstance();
		String billtype = getUIControl().getBillType();
		String pkCorp = getEnvironment().getCorporation().getPrimaryKey();
		
		String[] itemkeys = new String[]{
				fileDef.getField_Corp(),
				fileDef.getField_Operator(),
				fileDef.getField_Billtype(),
				fileDef.getField_BillStatus(),
				};
		Object[] values = new Object[]{
				pkCorp,
				getEnvironment().getUser().getPrimaryKey(),
				billtype,
				new Integer(IBillStatus.FREE).toString()
				};
		getBillCardPanel().setTailItem("dmakedate", getEnvironment().getDate());
		getBillCardPanel().setHeadItem("dbilldate", getEnvironment().getDate());
		for(int i = 0; i < itemkeys.length; i++){
			BillItem item = null;
			item = getBillCardPanel().getHeadItem(itemkeys[i]);
			if(item == null)
				item = getBillCardPanel().getTailItem(itemkeys[i]);
			if(item != null)
				item.setValue(values[i]);
		}
	}

	
	@Override
	public boolean beforeEdit(BillEditEvent e) {
		return true;
	}
	
	@Override
	public void afterEdit(BillEditEvent e) {

		super.afterEdit(e);
		if (("ysy").equalsIgnoreCase(e.getKey()) ){
		    String ysy = (String) e.getValue();
		
				String yslxdh=null;
				try {
					yslxdh = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class)
					        .executeQuery("select  docname from bd_defdoc  where memo ="+"'"+ ysy+"'"
					        		, new ColumnProcessor(null));
				} catch (BusinessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
			getBillCardPanel().setHeadItem("yslxdh",yslxdh);
	}
		if(e.getKey().equals(YsfksjHVO.PK_JOBBASFIL)){// ��Ŀ 
			
			
			String pk_jobbasfil=(String) getBillCardPanel().getHeadItem(YsfksjHVO.PK_JOBBASFIL).getValueObject();
			getBillCardPanel().getBillModel().clearBodyData();//����ձ�����
			if(pk_jobbasfil!=null && pk_jobbasfil.length()>0){
					try {
						
//						Map map=ysfksj.querySFYJYS(pk_jobbasfil);
						
						AzxxfkHVO[] azxxfkvos=(AzxxfkHVO[]) HYPubBO_Client.queryByCondition(AzxxfkHVO.class, " pk_jobbasfil ='" + pk_jobbasfil + "' and nvl(dr,0)=0 and "
								+  BzsqVO.VBILLSTATUS +"='1'  order by ts desc ");
//						AzxxfkBVO[] azxxfkbvo=null;
//						if(azxxfkvos!=null && azxxfkvos[0].getPk_azxxfkhid().length()>0){
//							azxxfkbvo=(AzxxfkBVO[]) HYPubBO_Client.queryByCondition(AzxxfkBVO.class, " pk_azxxfkhid ="+ azxxfkvos[0].getPk_azxxfkhid()+ "' and nvl(dr,0)=0 and ");
//						}
						
						if(azxxfkvos!=null && azxxfkvos.length>0){
								if(azxxfkvos[0].getSfyjsy()==null || !azxxfkvos[0].getSfyjsy().booleanValue()){{
									showErrorMessage("����Ŀ���̻�δ�ƽ����գ����������գ�");
									return;
								}
									
								}
								if(azxxfkvos[0].getZbrq()!=null){
									UFDate zbrq= azxxfkvos[0].getZbrq();//װ������
									getBillCardPanel().setHeadItem(YsfksjHVO.ZBRQ, zbrq) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.ZBRQ, "") ;
								}
								
								if(azxxfkvos[0].getYjbwysrq()!=null){//�ƽ���λ��������
									getBillCardPanel().setHeadItem(YsfksjHVO.YJBWYSRQ, azxxfkvos[0].getYjbwysrq()) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.YJBWYSRQ, "") ;
								}
								
								if(azxxfkvos[0].getSgbm()!=null){
									String sgbm= azxxfkvos[0].getSgbm();//ʩ������
									getBillCardPanel().setHeadItem(YsfksjHVO.SGBM, sgbm) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.SGBM, "") ;
								}
								
								if(azxxfkvos[0].getSgfzr()!=null){
									String sgfzr= azxxfkvos[0].getSgfzr();//ʩ��������
									getBillCardPanel().setHeadItem(YsfksjHVO.SGFZR, sgfzr) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.SGFZR, "") ;
								}
								
								if(azxxfkvos[0].getSglxdh()!=null){
									String sglxdh= azxxfkvos[0].getSglxdh();//ʩ����ϵ�绰
									getBillCardPanel().setHeadItem(YsfksjHVO.SGLXDH, sglxdh) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.SGLXDH, "") ;
								}
								if(azxxfkvos[0].getAzbj()!=null){
//									UFDouble  azbj= azxxfkvos[0].getAzbj();//��װ��
									getBillCardPanel().setHeadItem(YsfksjHVO.AZBJ, azxxfkvos[0].getAzbj()) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.AZBJ, "") ;
								}
								if(azxxfkvos[0].getCkxd()!=null){
									String  cchd= azxxfkvos[0].getCkxd();//�����ж�
									getBillCardPanel().setHeadItem(YsfksjHVO.CCHD, cchd) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.CCHD, "") ;
								}
								if(azxxfkvos[0].getLd()!=null){
									String ld= (String) azxxfkvos[0].getLd();//·��
									getBillCardPanel().setHeadItem(YsfksjHVO.LD, ld) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.LD, "") ;
								}
								if(azxxfkvos[0].getLdqd()!=null){
									String ldqd= azxxfkvos[0].getLdqd();//·�����
									getBillCardPanel().setHeadItem(YsfksjHVO.LDQD, ldqd) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.LDQD, "") ;
								}
								if(azxxfkvos[0].getLdzd()!=null){
									String ldzd= azxxfkvos[0].getLdzd();//·���յ�
									getBillCardPanel().setHeadItem(YsfksjHVO.LDZD, ldzd) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.LDZD, "") ;
								}
								if(azxxfkvos[0].getSggcai()!=null){
									String sggcai=azxxfkvos[0].getSggcai();//ʩ���ܲ�
									getBillCardPanel().setHeadItem(YsfksjHVO.SGGCAI, sggcai) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.SGGCAI, "") ;
								}
								if(azxxfkvos[0].getSggchang()!=null){
									UFDouble  sggchang=azxxfkvos[0].getSggchang();//ʩ���ܳ�
									getBillCardPanel().setHeadItem(YsfksjHVO.SGGCHANG, sggchang) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.SGGCHANG, "") ;
								}
								if(azxxfkvos[0].getBiaoma()!=null){
									String biaoma=azxxfkvos[0].getBiaoma();//����
									getBillCardPanel().setHeadItem(YsfksjHVO.BIAOMA, biaoma) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.BIAOMA, "") ;
								}
								if(azxxfkvos[0].getChandi()!=null){
									String chandi=azxxfkvos[0].getChandi();//����
									getBillCardPanel().setHeadItem(YsfksjHVO.CHANDI, chandi) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.CHANDI, "") ;
								}
								
								if(azxxfkvos[0].getBianma()!=null){
									String bianma=(String) azxxfkvos[0].getBianma();//����
									getBillCardPanel().setHeadItem(YsfksjHVO.BIANMA, bianma) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.BIANMA, "") ;
								}
								if(azxxfkvos[0].getBiaobie()!=null){
									String biaobie=azxxfkvos[0].getBiaobie();//���
									getBillCardPanel().setHeadItem(YsfksjHVO.BIAOBIE, biaobie) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.BIAOBIE, "") ;
								}
								if(azxxfkvos[0].getBiaowei()!=null){
									String biaowei=azxxfkvos[0].getBiaowei();//��λ
									getBillCardPanel().setHeadItem(YsfksjHVO.BIAOWEI, biaowei) ;
								}else{
									getBillCardPanel().setHeadItem(YsfksjHVO.BIAOWEI, "") ;
								}
								
								if(azxxfkvos[0].getHth()!=null){
									getBillCardPanel().setHeadItem("hth",azxxfkvos[0].getHth());//��ͬ��
								}else{
									getBillCardPanel().setHeadItem("hth","");//��ͬ��
								}
								if(azxxfkvos[0].getSjth()!=null){
									getBillCardPanel().setHeadItem("sjth",azxxfkvos[0].getSjth());//���ͼ��
								}else{
									getBillCardPanel().setHeadItem("sjth","");//���ͼ��
								}
								
								if(azxxfkvos[0].getKgrq()!=null){
									getBillCardPanel().setHeadItem("kgrq",azxxfkvos[0].getKgrq());//��������
								}else{
									getBillCardPanel().setHeadItem("kgrq","");//��������
								}
								
								if(azxxfkvos[0].getAzrq()!=null){
									getBillCardPanel().setHeadItem("azrq",azxxfkvos[0].getAzrq());//��װ����
								}else{
									getBillCardPanel().setHeadItem("azrq","");//��װ����
								}
								
								if(azxxfkvos[0].getJgrq()!=null){
									getBillCardPanel().setHeadItem("jgrq",azxxfkvos[0].getJgrq());//��������
								}else{
									getBillCardPanel().setHeadItem("jgrq","");//��������
								}
								
								if(azxxfkvos[0].getAzfkrq()!=null){
									getBillCardPanel().setHeadItem("azfkrq",azxxfkvos[0].getAzfkrq());//��װ��������
								}else{
									getBillCardPanel().setHeadItem("azfkrq","");//��װ��������
								}
								
								
								getBillCardPanel().execHeadLoadFormulas();
								getBillCardPanel().execHeadEditFormulas();
								
								
								
								
								
								
								
								
								
								
								
								
								
						}
						
						
						
						
//						LcswBzxxHVO[] lcswbzxxhvos = (LcswBzxxHVO[]) HYPubBO_Client.queryByCondition(LcswBzxxHVO.class," PK_JOBBASFIL='"+pk_jobbasfil+"'" 
//								+" and nvl(dr,0)=0 and pk_bzxx not in (select pk_bzxx from lcsw_ysfksj_b where pk_bzxx is not null and nvl(dr,0)=0)");
//						if(lcswbzxxhvos != null && lcswbzxxhvos.length > 0){
//							for (int i = 0; i < lcswbzxxhvos.length; i++) {

						//���ݱ�װ��Ϣ����  ��ѯ  ��װ����VO 
						BzsqVO[] bzsqvos = (BzsqVO[]) HYPubBO_Client.queryByCondition(BzsqVO.class, " pk_jobbasfil ='" + pk_jobbasfil + "' and nvl(dr,0)=0 and "
								+  BzsqVO.VBILLSTATUS +"='1'  and bzzt= "+EmunBzzt.AZWC+" ");
						if(bzsqvos!=null && bzsqvos.length>0){
							
							getBillCardPanel().setHeadItem("gtcz",bzsqvos[0].getGtcz());//��ͬ���� 
							getBillCardPanel().setHeadItem("sfsyx",bzsqvos[0].getSfsyx());//������
							
							for (int i = 0; i < bzsqvos.length; i++) {
								LcswBzxxHVO[] lcswbzxxhvos=	(LcswBzxxHVO[]) HYPubBO_Client.queryByCondition(LcswBzxxHVO.class,
										LcswBzxxHVO.PK_BZSQ + " = '" + bzsqvos[i].getPk_bzsq() + "' and nvl(dr,0)=0");
								for (int j = 0; j < lcswbzxxhvos.length; j++) {
									getBillCardPanel().addLine();
									getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getPk_bzxx(), j, YsfksjBVO.PK_BZXX);//��װ��Ϣ����
									getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getPk_jobbasfil(), j, YsfksjBVO.PK_JOBBASFIL);//��Ŀ��������
									String jobname = execFormular("getColValue(bd_jobbasfil, jobname, pk_jobbasfil, pk_jobbasfil)", lcswbzxxhvos[j].getPk_jobbasfil());
									getBillCardPanel().setBodyValueAt(jobname, j, "jobbasfil_name");//��Ŀ����
									String bzyh=execFormular("bzyh->getColValue(lcsw_bzxx, hm, pk_bzxx, pk_bzxx)", lcswbzxxhvos[j].getPk_bzxx());
									getBillCardPanel().setBodyValueAt(bzyh, j, "bzyh");//��װ�û�
									
									getBillCardPanel().setBodyValueAt(bzsqvos[i].getPk_bzsq(), j, YsfksjBVO.PK_BZSQ);//��װ��������
									
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
									
									
									getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getZbrq(), j, YsfksjBVO.ZBRQ);
									getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getBianma(), j, YsfksjBVO.BIANMA);
									getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getBiaobie(), j, YsfksjBVO.BIAOBIE);
									getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getBiaoma(), j, YsfksjBVO.BIAOMA);
									getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getBiaowei(), j, YsfksjBVO.BIAOWEI);
									getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getChandi(), j, YsfksjBVO.CHANDI);
									getBillCardPanel().setBodyValueAt(lcswbzxxhvos[j].getCkxd(), j, YsfksjBVO.CCHD);
								}
							}
						}else
							showErrorMessage("����Ŀû�ж�Ӧ��װ��Ϣ��");
					} catch (UifException e1) {
						e1.printStackTrace();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
			}
		}
		else if(e.getKey().equals(YsfksjHVO.YSJG)){
			String value = (String) e.getValue();
			if("���պϸ�".equals(value)) {
				int count = getBillCardPanel().getRowCount();
				for (int i = 0; i < count; i++) {
					getBillCardPanel().setBodyValueAt(UFBoolean.TRUE, i, "yshgbz");
				}
			}
			else {
				int count = getBillCardPanel().getRowCount();
				for (int i = 0; i < count; i++) {
					getBillCardPanel().setBodyValueAt(UFBoolean.FALSE, i, "yshgbz");
				}
			}
		}
	}
	
	@Override
	public Object getUserObject() {
		return new nc.vo.lcsw.sw08.SWGetCheckClass();
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
	public boolean beforeEdit(BillItemEvent e) {
		BillItem item = e.getItem();
		if ("pk_jobbasfil".equals(item.getKey())) {
			UIRefPane ref = (UIRefPane) getBillCardPanel().getHeadItem(SjfksjHVO.PK_JOBBASFIL).getComponent();
			ref.getRefModel().addWherePart(
					" and pk_jobbasfil in (select azfksj.PK_JOBBASFIL from lcsw_azxxfk_h azfksj where nvl(azfksj.dr,0)=0 and azfksj.vbillstatus=1 and azfksj.pk_corp='"
							+ getEnvironment().getCorporation().getPrimaryKey() + "' )  "
							+ " and pk_jobbasfil not in (select Ysfksj.PK_JOBBASFIL from Lcsw_Ysfksj_h Ysfksj where nvl(Ysfksj.dr,0)=0 and Ysfksj.pk_corp='"
							 + getEnvironment().getCorporation().getPrimaryKey() + "'  and Ysfksj.vbillstatus='1')");
		}
		return true;
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
