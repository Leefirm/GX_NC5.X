package nc.ui.lcsw.sw01;

import javax.swing.JComponent;


import nc.bs.framework.common.NCLocator;

import nc.ui.button.IButtons;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.trade.base.IBillOperate;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lcsw.pub.EmunBzzt;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw01.itf.IBzxx;


/**
 * <b>报装信息事件类</b>
 * @version 1.0
 * @since 1.0
 * @author 梁展轩
 * @time 2014-08-04 15:42:26
 */
public class EventHandler extends ManageEventHandler {

	private ClientUI clientUI; 
	public EventHandler(BillManageUI billUI,IControllerBase control) {
		super(billUI, control);
		this.clientUI = (ClientUI) billUI;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onBoSave() throws Exception {
//		HYPubBO bo = new HYPubBO();
//		clientUI.getBillCardPanel().dataNotNullValidate();
////		if(clientUI.getBillCardPanel().getHeadItem(LcswBzxxHVO.MODIFIER).getValue()==null)
////			clientUI.getBillCardPanel().setHeadItem(LcswBzxxHVO.MODIFIER, ClientEnvironment.getInstance().getUser().getPrimaryKey());
////		if(clientUI.getBillCardPanel().getHeadItem(LcswBzxxHVO.DMODIFY).getValue()==null)
////			clientUI.getBillCardPanel().setHeadItem(LcswBzxxHVO.DMODIFY, ClientEnvironment.getInstance().getDate());
////		//设置报装项目名称 by leefirm
//		if( clientUI.getBillCardPanel().getHeadItem("jobbasfil_name").getValue() != null 
//				 && clientUI.getBillCardPanel().getHeadItem("jobbasfil_name").getValue().length()>0 ) {
//			 String jobbasfil =(String) clientUI.getBillCardPanel().getHeadItem("jobbasfil_name").getValue() ;
////			 
////	
////			 
////			 NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery("update bd_jobbasfil set jobname = '"+jobbasfil+"'"
////						, new ColumnProcessor(null));
////		
////		}
//			 
//			 
//		BaseDAO dao = new BaseDAO();
//		String sqlJobname= "update bd_jobbasfil set jobname ="+jobbasfil;
//		dao.executeUpdate(sqlJobname);
		clientUI.getBillCardPanel().dataNotNullValidate();
		String pk_jobbasfil  = clientUI.getBillCardPanel().getHeadItem("pk_jobbasfil").getValue();
		String jobbasfil_name  = clientUI.getBillCardPanel().getHeadItem("jobbasfil_name").getValue();
	
		if( pk_jobbasfil !=null &&  jobbasfil_name != null && jobbasfil_name.length() > 0 && pk_jobbasfil.length() >0 ){
				
			 NCLocator.getInstance().lookup(IBzxx.class).updateJobName(pk_jobbasfil,jobbasfil_name);
		}
		super.onBoSave();
		
	}
	
	@Override
	protected void onBoQuery() throws Exception {
		super.onBoQuery();
	
				
	}
	
	@Override
	protected void onBoEdit() throws Exception {
		
		
		BillItem yyqyItem = clientUI.getBillCardPanel().getHeadItem("yyqy");
		BillItem ysxzItem = clientUI.getBillCardPanel().getHeadItem("ysxz");
		String a = ysxzItem.getValue();
		if(yyqyItem != null && ysxzItem != null) {
			String yyqy = yyqyItem.getValue();
			JComponent ysxz = ysxzItem.getComponent();
			if(ysxz instanceof UIRefPane) {
				String sqlWhere = "";
				if(!StringUtil.isEmpty(yyqy)) {
					if("南宁".equalsIgnoreCase(yyqy) || "邕宁".equalsIgnoreCase(yyqy)) {
						sqlWhere += " and memo is null";
					} else {
						sqlWhere += " and memo = '" + yyqy + "'";
					}
				}
				((UIRefPane)ysxz).getRefModel().addWherePart(sqlWhere, true);
				ysxzItem.setValue(a);
			}  
		}
			
		super.onBoEdit();
		if (getBillUI().getBillOperate() == IBillOperate.OP_EDIT){
			clientUI.getBillCardPanel().getTailItem(LcswBzxxHVO.MODIFIER).setValue(ClientEnvironment.getInstance().getUser().getPrimaryKey());
			clientUI.getBillCardPanel().getTailItem(LcswBzxxHVO.DMODIFY).setValue(ClientEnvironment.getInstance().getDate());
		}
	}
	
	
	@Override
	protected void onBoElse(int intBtn) throws Exception {
		super.onBoElse(intBtn);
		
		if(intBtn==IButtons.BTN_CANCELORDER){//撤单
			int selectRow=clientUI.getBillListPanel().getHeadTable().getSelectedRow();
			LcswBzxxHVO hvo=(LcswBzxxHVO) getBufferData().getVOByRowNo(selectRow).getParentVO();
			if(hvo.getBzzt()!=null && hvo.getBzzt()==10){
				MessageDialog.showErrorDlg(clientUI, "提示", "选中单据已撤单!");
				return;
			}
			if(MessageDialog.showOkCancelDlg(clientUI, "提示！", "撤单后此单据流程将中止，是否确定要撤单？")==UIDialog.ID_OK){
				hvo.setBzzt(EmunBzzt.YTZ); //已停止
				HYPubBO_Client.update(hvo);
				MessageDialog.showHintDlg(clientUI, "提示", "撤单成功!");
				updateBuffer();
			}
		}
	}
	
	
}
