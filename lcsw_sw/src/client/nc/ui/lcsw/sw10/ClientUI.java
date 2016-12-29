package nc.ui.lcsw.sw10;

import nc.ui.button.ButtonList;
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
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw10.BzfyHVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.trade.field.BillField;
import nc.vo.trade.pub.IBillStatus;

/**
 * <b>报装费用界面类</b>
 * @version 1.0
 * @since 1.0
 * @author xuns
 * @time 2014-08-12 16:09:22
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
		
		addPrivateButton(ButtonList.GetKcsjfButton());
		addPrivateButton(ButtonList.GetYsgck1qButton());
		addPrivateButton(ButtonList.GetYsgck2qButton());
		addPrivateButton(ButtonList.GetYsgck3qButton());
		addPrivateButton(ButtonList.GetBjkcfButton());
		addPrivateButton(ButtonList.GetJsgckButton());
		addPrivateButton(ButtonList.GetTuikuanButton());
		addPrivateButton(ButtonList.GetXiaozhangButton());
		//add by crf 2015-07-08增加撤销销账按钮
		addPrivateButton(ButtonList.GetCX_XiaozhangButton());
		addPrivateButton(ButtonList.GetFuzhuButton());

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
		if(e.getKey().equals(BzfyHVO.PK_BZSQ)){
			String pk_bzsq = getBillCardPanel().getHeadItem(BzfyHVO.PK_BZSQ).getValue();
			if(pk_bzsq != null){
				try {
					BzsqVO bzsqvo = (BzsqVO)HYPubBO_Client.queryByPrimaryKey(BzsqVO.class, pk_bzsq);
					 getBillCardPanel().setHeadItem("gcmc", bzsqvo.getPk_jobbasfil());
					 getBillCardPanel().setHeadItem("xybh", bzsqvo.getXybh());
					 getBillCardPanel().setHeadItem("slbh", bzsqvo.getSlbh());
					 getBillCardPanel().setHeadItem("lxdz", bzsqvo.getLxdz());
					 getBillCardPanel().setHeadItem("lxr", bzsqvo.getLxr());
					 getBillCardPanel().setHeadItem("lxdh", bzsqvo.getLxdd());
					 getBillCardPanel().setHeadItem("azdz", bzsqvo.getAzdz());
					 getBillCardPanel().setHeadItem("ysxz", bzsqvo.getYsxz());
					 getBillCardPanel().setHeadItem("gcxz", bzsqvo.getGcxz());
					 getBillCardPanel().setHeadItem("ygj", bzsqvo.getYgj());
					 getBillCardPanel().setHeadItem("swkj", bzsqvo.getSwkj());
					 getBillCardPanel().setHeadItem("swgj", bzsqvo.getSwgj());
					 getBillCardPanel().setHeadItem("snkj", bzsqvo.getSnkj());
					 getBillCardPanel().setHeadItem("sngj", bzsqvo.getSngj());
					 getBillCardPanel().setHeadItem("zgcs", bzsqvo.getZgcs());
					 getBillCardPanel().setHeadItem("jzmj", bzsqvo.getJzmj());
					 getBillCardPanel().setHeadItem("ysrs", bzsqvo.getYsrs());
					 getBillCardPanel().setHeadItem("zdsl", bzsqvo.getZdsl());
					 getBillCardPanel().setHeadItem("jbry", bzsqvo.getJbry());
					 getBillCardPanel().setHeadItem("jmlx", bzsqvo.getJmlx());
					 getBillCardPanel().setHeadItem("zlxfs", bzsqvo.getZlxfs());
					 getBillCardPanel().setHeadItem("sjbs", bzsqvo.getSjbs());
					 
					 getBillCardPanel().setHeadItem("fkfs", bzsqvo.getFkfs());
					 getBillCardPanel().setHeadItem("jyskqs", bzsqvo.getJyskqs());
					 getBillCardPanel().setHeadItem("khyh", bzsqvo.getKhyh());
					 getBillCardPanel().setHeadItem("yhzh", bzsqvo.getYhzh());
					 
				} catch (UifException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public Object getUserObject() {
		return new nc.vo.lcsw.sw10.SWGetCheckClass();
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
		BillCardPanel billCardPanel = getBillCardPanel();
		BillItem item = e.getItem();
		if ("pk_bzsq".equals(item.getKey())) {
			UIRefPane ref = (UIRefPane) billCardPanel.getHeadItem("pk_bzsq").getComponent();
			ref.getRefModel().addWherePart(" and pk_bzsq not in (select pk_bzsq from lcsw_bzfy_h where nvl(dr,0)=0 and pk_bzsq is not null ) ");
		}
		return true;
	}

	
}
