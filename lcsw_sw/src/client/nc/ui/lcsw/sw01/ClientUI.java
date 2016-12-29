package nc.ui.lcsw.sw01;

import javax.swing.JComponent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.button.IButtons;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillItem;
import nc.ui.trade.base.IBillOperate;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.trade.button.ButtonVO;
import nc.vo.trade.pub.IBillStatus;

/**
 * <b>��װ��Ϣ������</b>
 * @version 1.0
 * @since 1.0
 * @author ��չ��
 * @time 2014-08-04 15:42:26
 */
public class ClientUI extends BillManageUI {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void initPrivateButton() {
		int[] listButns = getUIControl().getListButtonAry();
		boolean cancelOrder = false;
		for (int i = 0; i < listButns.length; i++) {
			if (listButns[i] == nc.ui.button.IButtons.BTN_CANCELORDER)
				cancelOrder = true;
		}
		int[] cardButns = getUIControl().getCardButtonAry();
		for (int i = 0; i < cardButns.length; i++) {
			if (cardButns[i] == nc.ui.button.IButtons.BTN_CANCELORDER)
				cancelOrder = true;
		}
		if (cancelOrder) {
			ButtonVO btnComine = new ButtonVO();
			btnComine.setBtnNo(IButtons.BTN_CANCELORDER);
			btnComine.setBtnName("����");
			btnComine.setBtnChinaName("����");
			btnComine.setHintStr("����");
			btnComine.setBtnCode(null);
			btnComine.setOperateStatus(new int[] { IBillOperate.OP_NOTEDIT,IBillOperate.OP_NO_ADDANDEDIT });
			btnComine.setBusinessStatus(new int[] { IBillStatus.ALL });
			addPrivateButton(btnComine);
		}
		super.initPrivateButton();
	}
	
	@Override
	protected AbstractManageController createController() {
		return new Controller();
	}

	@Override
	protected ManageEventHandler createEventHandler() {
		return new EventHandler(this, createController());
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
		
	}

	@Override
	public boolean beforeEdit(BillEditEvent e) {
		

		
		return true;
	}
	
	@Override
	public void afterEdit(BillEditEvent e) {
		
	
		
		//zhujie <��װ����>��<��װ��Ϣ>�����ݱ༭������[Ӫҵ����]��ѡ������ˮ���ʱ��[��ע]��Ϊɸѡ����
		if(("yyqy").equalsIgnoreCase(e.getKey())) {
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
	public Object getUserObject() {
		return new nc.vo.lcsw.sw01.SWGetCheckClass();
	}

	
	
}
