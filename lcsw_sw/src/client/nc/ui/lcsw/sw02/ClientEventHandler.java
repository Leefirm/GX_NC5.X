package nc.ui.lcsw.sw02;

import javax.swing.JComponent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.lcsw.sw10.print.JKTZDataSourceNew;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.print.PrintEntry;
import nc.ui.trade.base.AbstractBillUI;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.bd.b38.JobbasfilVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw03.KcpgBVO;
import nc.vo.pub.FkfsEmun;
import nc.vo.pub.YSXZEmun;

/**
 * <b>报装申请事件类</b>
 * 
 * @version 1.0
 * @since 1.0
 * @author xuns
 * @time 2014-08-04 14:44:34
 */
public class ClientEventHandler extends ManageEventHandler {

	private ClientUI clientUI;

	private IUAPQueryBS queryBS;

	public ClientEventHandler(BillManageUI billUI, IControllerBase control) {
		super(billUI, control);
		this.clientUI = (ClientUI) billUI;
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
	}

	@Override
	protected void onBoSave() throws Exception {
		queryBS = queryBS == null ? (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class) : queryBS;
		clientUI.getBillCardPanel().dataNotNullValidate();
		if (clientUI.getBillCardPanel().getHeadItem("pk_bzsq").getValue() != null && !clientUI.getBillCardPanel().getHeadItem("pk_bzsq").getValue().equals("")) {
			clientUI.getBillCardPanel().setHeadItem("modifier", ClientEnvironment.getInstance().getUser().getPrimaryKey());
			clientUI.getBillCardPanel().setHeadItem("dmodify", ClientEnvironment.getInstance().getDate());
		}

		// 根据付款方式
		String fkfs = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(BzsqVO.FKFS).getValue();
		if (fkfs != null && fkfs.equals(FkfsEmun.ZZ)) {
			String khyh = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(BzsqVO.KHYH).getValue();
			String yhzh = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(BzsqVO.YHZH).getValue();
			if (khyh == null || yhzh == null || khyh.equals("") || yhzh.equals("")) {
				getBillUI().showErrorMessage("开户银行和银行账号不能为空！");
				return;
			}
		}

		// 根据用水性质
//		String ysxz = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(BzsqVO.YSXZ).getValue();
//		String docname = (String) queryBS.executeQuery("select d.docname from bd_defdoc d where d.pk_defdoclist='" + YSXZEmun.YSXZ_BD_DEFDOCLIST_PK
//				+ "' and d.doccode='" + YSXZEmun.HB + "'", new ColumnProcessor());
//		if (ysxz != null && ysxz.equals(docname)) {
//			String sfzh = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(BzsqVO.SFZH).getValue();
//			if (sfzh == null || sfzh.equals("")) {
//				getBillUI().showErrorMessage("[ 身份证号 ]不能为空！");
//				return;
//			}
//		} else {
//			String yyzzh = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(BzsqVO.YYZZH).getValue();
//			String biaojing = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(BzsqVO.BIAOJING).getValue();
//			String swgj = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(BzsqVO.SWGJ).getValue();
//			if (yyzzh == null || yyzzh.equals("") || biaojing == null || biaojing.equals("") || swgj == null || swgj.equals("")) {
//				getBillUI().showErrorMessage("[营业执照号]、[表径]、[室外管径]不能为空！");
//				return;
//			}
//		}

		super.onBoSave();
	}

	@Override
	protected void onBoQuery() throws Exception {
		super.onBoQuery();
	}

	@Override
	public void onBoAudit() throws Exception {

		 clientUI.getBillCardPanel().setHeadItem(BzsqVO.VAPPROVEID,
		 ClientEnvironment.getInstance().getUser().getPrimaryKey());
		 clientUI.getBillCardPanel().setHeadItem(BzsqVO.DAPPROVEDATE,
		 ClientEnvironment.getInstance().getServerTime());

		super.onBoAudit();
	}

	@Override
	protected void onBoCancelAudit() throws Exception {
		// 弃审前校验
		String pk_bzsq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(BzsqVO.PK_BZSQ).getValue();
		KcpgBVO[] kcpgbvo = (KcpgBVO[]) HYPubBO_Client.queryByCondition(KcpgBVO.class, "  " + KcpgBVO.PK_BZSQ + " = '" + pk_bzsq + "' and nvl(dr,0)=0 ");
		if (kcpgbvo != null && kcpgbvo.length > 0) {
			getBillUI().showErrorMessage("该报装申请存在下游单据不能弃审！");
			return;
		}
		super.onBoCancelAudit();
	}
	
	@Override
	protected void onBoPrint() throws Exception {
		int row=clientUI.getBillListPanel().getHeadTable().getSelectedRow();
		BzsqVO bzfyvo =(BzsqVO) getBufferData().getVOByRowNo(row).getParentVO();
		
		String pk_bzsq = bzfyvo.getPk_bzsq();
		if(pk_bzsq == null) {
			throw new Exception("找不到对应的报装申请，无法打印");
		}
		 
		nc.ui.pub.print.IDataSource dataSource = new JKTZDataSourceNew(pk_bzsq);
		PrintEntry print = new PrintEntry(null, dataSource);
		print.setTemplateID(getBillUI()._getCorp().getPrimaryKey(), getBillUI()._getModuleCode(), getBillUI()._getOperator(), getBillUI().getBusinessType(), getBillUI().getNodeKey());
		if (print.selectTemplate() == 1)
			print.preview();
	}
}
