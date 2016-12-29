package nc.ui.lcsw.sw02;

import java.util.Vector;

import javax.swing.JComponent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillItem;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.trade.field.BillField;
import nc.vo.trade.pub.IBillStatus;

/**
 * <b>��װ���������</b>
 * @version 1.0
 * @since 1.0
 * @author xuns
 * @time 2014-08-04 14:44:34
 */
public class ClientUI extends BillManageUI {

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
		getBillCardPanel().setHeadItem("dmakedate", getEnvironment().getDate());
		getBillCardPanel().setHeadItem("bzrq", getEnvironment().getDate());
		getBillCardPanel().setHeadItem("bzzt", EmunBzzt.TJSQ);
		for(int i = 0; i < itemkeys.length; i++){
			BillItem item = null;
			item = getBillCardPanel().getHeadItem(itemkeys[i]);
			if(item == null)
				item = getBillCardPanel().getTailItem(itemkeys[i]);
			if(item != null)
				item.setValue(values[i]);
		}
		//add by crf 2015-06-16
		getBillCardPanel().setHeadItem("yshs", new Integer(1));//���黧��
		getBillCardPanel().setHeadItem("jyskqs", new Integer(1));//��Ԥ�������
		getBillCardPanel().setHeadItem("sjbs", new UFBoolean(true));//��Ԥ�������
		
		//panqh�������ˡ�Ĭ�ϵ�½��Ա����
		JComponent jComponent = getBillCardPanel().getHeadItem("jbry").getComponent();
		if(jComponent instanceof UIRefPane) {
			Vector matchData = ((UIRefPane)jComponent).getRefModel().matchData("docname", ClientEnvironment.getInstance().getUser().getUserName());
			if(matchData != null && matchData.size() > 0) {
				((UIRefPane)jComponent).setPK((String) ((Vector)matchData.get(0)).get(2));
			}
		}
		//panqh ��������Զ��嵵��0001AA1000000000RA9HĬ��
		String sql = "select MEMO from bd_defdoc where pk_defdoclist='0001AA1000000000RA9H' and doccode='"+ClientEnvironment.getInstance().getUser().getUserCode()+"'";
		String qy = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, new ColumnProcessor());
		if(qy != null) {
			JComponent yyqy = getBillCardPanel().getHeadItem("yyqy").getComponent();
			if(yyqy instanceof UIRefPane) {
				Vector matchData = ((UIRefPane)yyqy).getRefModel().matchData("docname", qy);
				if(matchData != null && matchData.size() > 0) {
					((UIRefPane)yyqy).setPK((String) ((Vector)matchData.get(0)).get(2));
				}
			}
		}
		
		if(getBillCardPanel().getHeadItem("yyqy") != null && getBillCardPanel().getHeadItem("ysxz") != null) {
			String yyqy = (String) getBillCardPanel().getHeadItem("yyqy").getValueObject();
			JComponent ysxz = getBillCardPanel().getHeadItem("ysxz").getComponent();
			if(ysxz instanceof UIRefPane) {
				String sqlWhere = "";
				if(!StringUtil.isEmpty(yyqy)) {
					if("����".equalsIgnoreCase(yyqy) || "����".equalsIgnoreCase(yyqy)) {
						sqlWhere += " and memo is null";
					} else {
						sqlWhere += " and memo = '" + yyqy + "'";
					}
				}
				((UIRefPane)ysxz).getRefModel().addWherePart(sqlWhere, true);
			}  
		}
	}

	@Override
	public boolean beforeEdit(BillEditEvent e) {
		return true;
	}
	
	@Override
	public void afterEdit(BillEditEvent e) {
		//add by crf 2015-06-15 
		//��ϵ��   �ӡ�����������
		if(("hm").equalsIgnoreCase(e.getKey())){
			String hm = (String) getBillCardPanel().getHeadItem("hm").getValueObject();
			getBillCardPanel().setHeadItem("lxr", hm);
			getBillCardPanel().setHeadItem("zlxfs", hm);
//			String azdz = (String) getBillCardPanel().getHeadItem("azdz").getValueObject();
//			String swkj = (String) getBillCardPanel().getHeadItem("swkj").getValueObject();
//			String gcxz = (String) getBillCardPanel().getHeadItem("gcxz").getValueObject();
//			String gcmc = azdz + hm + "DN" + swkj + gcxz + "����";
			String gcmc = getProjName();
			getBillCardPanel().setHeadItem("reserve3", gcmc);
		}else if(("lxr").equalsIgnoreCase(e.getKey())||("lxdd").equalsIgnoreCase(e.getKey())||("reserve1").equalsIgnoreCase(e.getKey())){
			String lxr = (String) getBillCardPanel().getHeadItem("lxr").getValueObject();
			String lxdh = (String) getBillCardPanel().getHeadItem("lxdd").getValueObject();
			String reserve1 = (String) getBillCardPanel().getHeadItem("reserve1").getValueObject();
			
			String zlxfs = ((lxr==null)?"":lxr) + (lxdh==null?"":lxdh);
			zlxfs = zlxfs + "/" + (reserve1==null?"":reserve1);
			getBillCardPanel().setHeadItem("zlxfs", zlxfs);
		}else if(("azdz").equalsIgnoreCase(e.getKey())||("swkj").equalsIgnoreCase(e.getKey())
				||("gcxz").equalsIgnoreCase(e.getKey())||("yshs").equalsIgnoreCase(e.getKey())){
			//��ˮ�ѵ��ʹ��ַ������ԭ����ϵ��ַ�����ӡ���װ��ַ������
			String azdz = (String) getBillCardPanel().getHeadItem("azdz").getValueObject();
//			String swkj = (String) getBillCardPanel().getHeadItem("swkj").getValueObject();
//			String gcxz = (String) getBillCardPanel().getHeadItem("gcxz").getValueObject();
//			String hm = (String) getBillCardPanel().getHeadItem("hm").getValueObject();
			String gcmc = getProjName();
//			String gcmc = azdz + hm + "DN" + swkj + gcxz + "����";
			getBillCardPanel().setHeadItem("lxdz", azdz);//��ϵ��ַ
			getBillCardPanel().setHeadItem("reserve3", gcmc);//��������
		}
		//zhujie <��װ����>��<��װ��Ϣ>�����ݱ༭������[Ӫҵ����]��ѡ������ˮ���ʱ��[��ע]��Ϊɸѡ����
		else if(("yyqy").equalsIgnoreCase(e.getKey())) {
			String yyqy = (String) getBillCardPanel().getHeadItem("yyqy").getValueObject();
			JComponent ysxz = getBillCardPanel().getHeadItem("ysxz").getComponent();
			if(ysxz instanceof UIRefPane) {
				String sqlWhere = "";
				if(!StringUtil.isEmpty(yyqy)) {
					if("����".equalsIgnoreCase(yyqy) || "����".equalsIgnoreCase(yyqy)) {
						sqlWhere += " and memo is null";
					} else {
						sqlWhere += " and memo = '" + yyqy + "'";
					}
				}
				((UIRefPane)ysxz).getRefModel().addWherePart(sqlWhere, true);
			}  
		}
	}
	
	private String getProjName() {
		
		String yshs = (String) getBillCardPanel().getHeadItem("yshs").getValueObject();
		String azdz = (String) getBillCardPanel().getHeadItem("azdz").getValueObject();
		String swkj = (String) getBillCardPanel().getHeadItem("swkj").getValueObject();
		String gcxz = (String) getBillCardPanel().getHeadItem("gcxz").getValueObject();
		String hm = (String) getBillCardPanel().getHeadItem("hm").getValueObject();
		
		String str = "";
		if (yshs != null && Integer.parseInt(yshs) > 1) {
			str = "��" + yshs + "��";
		}
		String jobname = azdz + hm + str + "DN" + swkj
				+ "��" + gcxz + "����";
		
		return jobname;
	}
	
	@Override
	public Object getUserObject() {
		return new nc.vo.lcsw.sw02.SWGetCheckClass();
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


}
