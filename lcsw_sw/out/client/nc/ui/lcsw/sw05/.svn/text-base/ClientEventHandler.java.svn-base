/**    Create By NCPlugin beta 0.1.   **/
package nc.ui.lcsw.sw05;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.ui.lcsw.sw10.SW10ManageEventHandler;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.trade.bill.BillCardPanelWrapper;
import nc.ui.trade.bill.ICardController;
import nc.ui.trade.manage.BillManageUI;
import nc.vo.lcsw.sw03.itf.IKcpg;
import nc.vo.lcsw.sw05.GcpgsjBVO;
import nc.vo.lcsw.sw05.GcpgsjHVO;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

/**
 * 
 * @author Administrator
 * @createtime 2014-08-07 17:30:01
 * 
 */
public class ClientEventHandler extends SW10ManageEventHandler {

	ClientUI clientUI = null;
	private IUAPQueryBS queryBS;

	public ClientEventHandler(BillManageUI billUI, ICardController control) {
		super(billUI, control);
	}

	@Override
	public void onBoAdd(ButtonObject bo) throws Exception {
		super.onBoAdd(bo);
	}

	@Override
	protected void onBoDel() throws Exception {
		if (getBillUI().showYesNoMessage("是否执行删除操作?") == MessageDialog.ID_YES) {
			super.onBoDel();
		}
	}

	@Override
	protected void onBoElse(int intBtn) throws Exception {

		super.onBoElse(intBtn);
	}

	// 删行事件
	@Override
	protected void onBoLineDel() throws Exception {
		int[] i = getBillCardPanelWrapper().getBillCardPanel().getBillTable().getSelectedRows();
		if (i.length > 0 && getBillUI().showYesNoMessage("是否删除选中的行?") == MessageDialog.ID_YES) {
			super.onBoLineDel();
		}
	}

	@Override
	protected void onBoLineAdd() throws Exception {
		super.onBoLineAdd();
		int row = getBillCardPanelWrapper().getBillCardPanel().getRowCount() - 1;
		String pk_corp = getBillCardPanelWrapper().getBillCardPanel().getHeadItem("pk_corp").getValue();
		getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(pk_corp, row, "pk_corp");
	}

	/*
	 * 查询
	 */
	@Override
	protected void onBoQuery() throws Exception {

		StringBuffer strWhere = new StringBuffer();
		if (askForQueryCondition(strWhere) == false)
			return;// 用户放弃了查询
		// 屏蔽条件: pk_corp='0001'
		String str = strWhere.toString();
		Pattern pattern = Pattern.compile(" and pk_corp='.{4}' ");

		Matcher matcher = pattern.matcher(str);
		str = matcher.replaceFirst(" ");
		SuperVO[] queryVos = queryHeadVOs(str);
		if (queryVos.length == 0) {
			getBillUI().showErrorMessage("没有符合要求的数据！");
		}
		getBufferData().clear();
		// 增加数据到Buffer

		addDataToBuffer(queryVos);
		updateBuffer();

	}

	@Override
	protected void onBoEdit() throws Exception {
		// TODO Auto-generated method stub
		BillItem lastModifier = getBillCardPanelWrapper().getBillCardPanel().getTailItem(GcpgsjHVO.MODIFIER);
		BillItem lastModifyTime = getBillCardPanelWrapper().getBillCardPanel().getTailItem(GcpgsjHVO.DMODIFY);
		if (null == lastModifier) {
			lastModifier = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(GcpgsjHVO.MODIFIER);
		}
		if (null == lastModifyTime) {
			lastModifyTime = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(GcpgsjHVO.DMODIFY);
		}
		lastModifier.setValue(_getOperator());
		lastModifyTime.setValue(ClientEnvironment.getServerTime());
		super.onBoEdit();

	}

	/**
	 * 通过行号和字段名，获取Double类型的值
	 * 
	 * @param row
	 * @param name
	 * @return
	 */
	public UFDouble getUFValue(int row, String name) {

		Object obj = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(row, name);
		UFDouble ufValue = null;

		if (obj != null) {
			ufValue = new UFDouble(obj.toString());
			return ufValue;
		}

		return ufValue;
	}

	@Override
	protected void onBoSave() throws Exception {
		getBillCardPanelWrapper().getBillCardPanel().dataNotNullValidate();
		int rows = getBillCardPanelWrapper().getBillCardPanel().getRowCount();
		if (rows <= 0) {
			getBillUI().showErrorMessage("表体不能为空！");
			return;
		}
		this.setSaveHandler();
		super.onBoSave();
	}

	@Override
	protected void onBoCommit() throws Exception {
		// 取得卡片
		BillCardPanelWrapper billCardPanelWrapper = getBillCardPanelWrapper();
		BillCardPanel billCardPanel = billCardPanelWrapper.getBillCardPanel();
		// 取得后台查询组件
		queryBS = queryBS == null ? (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class) : queryBS;
		super.onBoCommit();

	}

	@Override
	public void onBoAudit() throws Exception {
		super.onBoAudit();
	}

	@Override
	protected void onBoCard() throws Exception {
		// TODO 自动生成方法存根
		super.onBoCard();
	}

	@Override
	protected void onBoCancel() throws Exception {
		// TODO Auto-generated method stub
		super.onBoCancel();
	}

	@Override
	protected void onBoCancelAudit() throws Exception {
		// TODO Auto-generated method stub
		super.onBoCancelAudit();
	}

	/**
	 * 保存动作事件
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	private void setSaveHandler() throws Exception {

		IKcpg kcpgService = NCLocator.getInstance().lookup(IKcpg.class);

		int rowcount = getBillCardPanelWrapper().getBillCardPanel().getRowCount();

		for (int i = 0; i < rowcount; i++) {
			String yqxgr = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(GcpgsjHVO.YQXGR).getValue();
			String yqxgrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(GcpgsjHVO.YQXGRQ).getValue();
			String wgyqts = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(GcpgsjHVO.WGYQTS).getValue();
			String sjdw = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(GcpgsjHVO.SJDW).getValue();
			String snsgdw = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(GcpgsjHVO.SNSGDW).getValue();
			String swsgdw = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(GcpgsjHVO.SWSGDW).getValue();
			String sbgs = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(GcpgsjHVO.SBGS).getValue();
			String gc = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(GcpgsjHVO.GC).getValue();
			String sgts = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(GcpgsjHVO.SGTS).getValue();

			Object yqxgrb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, GcpgsjBVO.YQXGR);
			Object yqxgrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, GcpgsjBVO.YQXGRQ);
			Object wgyqtsb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, GcpgsjBVO.WGYQTS);
			Object sjdwb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, GcpgsjBVO.SJDW);
			Object snsgdwb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, GcpgsjBVO.SNSGDW);
			Object swsgdwb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, GcpgsjBVO.SWSGDW);
			Object sbgsb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, GcpgsjBVO.SBGS);
			Object gcb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, GcpgsjBVO.GC);
			Object sgtsb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, GcpgsjBVO.SGTS);
			Object id = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, GcpgsjBVO.PK_GCPGSJBID);

			//modify by crf 
			// 延期修改人
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yqxgr, i, GcpgsjBVO.YQXGR);
//			// 延期修改日期
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yqxgrq, i, GcpgsjBVO.YQXGRQ);
//			// 完工延迟日期
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(wgyqts, i, GcpgsjBVO.WGYQTS);
//			// 设计单位
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sjdw, i, GcpgsjBVO.SJDW);
//			// 室内施工单位
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(snsgdw, i, GcpgsjBVO.SNSGDW);
//			// 室外施工单位
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(swsgdw, i, GcpgsjBVO.SWSGDW);
//			// 水表个数
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sbgs, i, GcpgsjBVO.SBGS);
//			// 管长
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(new UFDouble(gc), i, GcpgsjBVO.GC);
//			// 施工天数
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sgts, i, GcpgsjBVO.SGTS);
			//end by crf 2015-07-08
			if (yqxgrb == null) {
				// 延期修改人
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yqxgr, i, GcpgsjBVO.YQXGR);
			}

			if (yqxgrqb == null) {
				// 延期修改日期
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yqxgrq, i, GcpgsjBVO.YQXGRQ);
			}

			if (wgyqtsb == null) {
				// 完工延迟日期
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(wgyqts, i, GcpgsjBVO.WGYQTS);
			}

			if (sjdwb == null) {
				// 设计单位
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sjdw, i, GcpgsjBVO.SJDW);
			}

			if (snsgdwb == null) {
				// 室内施工单位
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(snsgdw, i, GcpgsjBVO.SNSGDW);
			}

			if (swsgdwb == null) {
				// 室外施工单位
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(swsgdw, i, GcpgsjBVO.SWSGDW);
			}
			if (sbgsb == null) {
				// 水表个数
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sbgs, i, GcpgsjBVO.SBGS);
			}
			if (gcb == null) {
				// 管长
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(new UFDouble(gc), i, GcpgsjBVO.GC);
			}
			if (sgtsb == null) {
				// 施工天数
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sgts, i, GcpgsjBVO.SGTS);
			}
		}

	}

}
