package nc.ui.lcsw.sw04;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.ui.lcsw.sw10.SW10ManageEventHandler;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.trade.bill.BillCardPanelWrapper;
import nc.ui.trade.bill.ICardController;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.trade.manage.BillManageUI;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lcsw.sw03.KcpgHVO;
import nc.vo.lcsw.sw03.itf.IKcpg;
import nc.vo.lcsw.sw04.KcfksjBVO;
import nc.vo.lcsw.sw04.KcfksjHVO;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
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
		
		if(this.setSaveHandler())
			return;
			
		super.onBoSave();
	}

	@SuppressWarnings("deprecation")
	private Boolean setSaveHandler() throws Exception {
		String jbry = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.JBRY).getValue();
		String kcry = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.KCRY).getValue();
		String kcsjry = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.KCSJRY).getValue();
		String yqxgr = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.YQXGR).getValue();
		// 勘察日期
		String kcrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.KCRQ).getValue();
		// 勘察反馈日期
		String kcfkrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.KCFKRQ).getValue();
		// 接红线图日期
		String jhxtrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.JHXTRQ).getValue();
		// 移交画红线日期
		String yjhhxrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.YJHHXRQ).getValue();
		// 设计评审完成日期
		String sjpswcrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.SJPSWCRQ).getValue();
		// 移交设计评审日期
		String yjsjpsrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.YJSJPSRQ).getValue();
		// 移交设计日期
		String yjsjrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.YJSJRQ).getValue();
		// 延期修改日期
		String yqxgrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.YQXGRQ).getValue();
		// 完工延期天数
		String wgyqts = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.WGYQTS).getValue();
		
		//勘察派工的[勘察日期]<=[当前日期]；
		UFDate today = ClientEnvironment.getInstance().getBusinessDate();
		if(today.before(new UFDate(kcrq))){
			getBillUI().showErrorMessage("[勘察日期]不能大于[当前日期]！");
//			getBillUI().showErrorMessage("[当前日期]不能小于[勘察日期]！");
			return true;
		}
		//勘察派工的[审核日期]<=[勘察日期]
		String pk_jobbasfil = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.PK_JOBBASFIL).getValue();
		KcpgHVO[] pghvos  = (KcpgHVO[])HYPubBO_Client.queryByCondition(KcpgHVO.class, 
				" vbillstatus = 1 and pk_jobbasfil = '"+pk_jobbasfil+"' order by ts desc");
		if(pghvos[0].getDapprovedate() !=null) {
		if(pghvos.length > 0 && pghvos[0].getDapprovedate().after(new UFDate(kcrq))){
			getBillUI().showErrorMessage("[勘察日期]不能小于勘察派工的[审核日期]！");
			return true;
		}
		}
		//[勘察反馈日期]<=[当前日期]
		if(today.before(new UFDate(kcfkrq))){
			getBillUI().showErrorMessage("[勘察反馈日期]不能大于[当前日期]！");
//			getBillUI().showErrorMessage("[当前日期]不能小于[勘察反馈日期]！");
			return true;
		}
		//[勘察日期]<=[勘察反馈日期]；
		if(new UFDate(kcfkrq).before(new UFDate(kcrq))){
//			getBillUI().showErrorMessage("[勘察日期]不能大于[勘察反馈日期]！");
			getBillUI().showErrorMessage("[勘察反馈日期]不能小于[勘察日期]！");
			return true;
		}
		//[移交设计日期]<=[当前日期]
		if(yjsjrq != null && !yjsjrq.equals("") && today.before(new UFDate(yjsjrq))){
//			getBillUI().showErrorMessage("[移交设计日期]不能大于[当前日期]！");
			getBillUI().showErrorMessage("[当前日期]不能小于[移交设计日期]！");
			return true;
		}
		//[勘察反馈日期]<=[移交设计日期]；
		if(yjsjrq != null && !yjsjrq.equals("") && new UFDate(yjsjrq).before(new UFDate(kcfkrq))){
//			getBillUI().showErrorMessage("[勘察反馈日期]不能大于[移交设计日期]！");
			getBillUI().showErrorMessage("[移交设计日期]不能小于[勘察反馈日期]！");
			return true;
		}
		//其他日期<=[当前日期]。
		if(!StringUtil.isEmptyWithTrim(yjhhxrq) && today.before(new UFDate(yjhhxrq))){
			getBillUI().showErrorMessage("[移交画红线日期]不能大于[当前日期]！");
			return true;
		}
		if(!StringUtil.isEmptyWithTrim(jhxtrq) && today.before(new UFDate(jhxtrq))){
			getBillUI().showErrorMessage("[接红线图日期]不能大于[当前日期]！");
			return true;
		}
		if(!StringUtil.isEmptyWithTrim(yjsjpsrq) && today.before(new UFDate(yjsjpsrq))){
			getBillUI().showErrorMessage("[移交设计评审日期]不能大于[当前日期]！");
			return true;
		}
		if(!StringUtil.isEmptyWithTrim(sjpswcrq) && today.before(new UFDate(sjpswcrq))){
			getBillUI().showErrorMessage("[设计评审完成日期]不能大于[当前日期]！");
			return true;
		}
		

		IKcpg kcpgService = NCLocator.getInstance().lookup(IKcpg.class);

		int rowcount = getBillCardPanelWrapper().getBillCardPanel().getRowCount();

		for (int i = 0; i < rowcount; i++) {

			// 经办人
			Object jbryb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.JBRY);
			// 勘察人员
			Object kcryb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.KCRY);
			// 勘察设计人员
			Object kcsjryb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.KCSJRY);
			// 延期修改人
			Object yqxgrb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.YQXGR);
			// 勘察日期
			Object kcrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.KCRQ);
			// 勘察反馈日期
			Object kcfkrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.KCFKRQ);
			// 接红线图日期
			Object jhxtrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.JHXTRQ);
			// 移交画红线日期
			Object yjhhxrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.YJHHXRQ);
			// 设计评审完成日期
			Object sjpswcrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.SJPSWCRQ);
			// 移交设计评审日期
			Object yjsjpsrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.YJSJPSRQ);
			// 移交设计日期
			Object yjsjrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.YJSJRQ);
			// 延期修改日期
			Object yqxgrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.YQXGRQ);
			// 完工延期天数
			Object wgyqtsb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.WGYQTS);
			
			//modify by crf 2015-07-08
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jbry, i, KcfksjBVO.JBRY);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcry, i, KcfksjBVO.KCRY);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcsjry, i, KcfksjBVO.KCSJRY);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yqxgr, i, KcfksjBVO.YQXGR);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcrq, i, KcfksjBVO.KCRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcfkrq, i, KcfksjBVO.KCFKRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jhxtrq, i, KcfksjBVO.JHXTRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yjhhxrq, i, KcfksjBVO.YJHHXRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sjpswcrq, i, KcfksjBVO.SJPSWCRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yjsjpsrq, i, KcfksjBVO.YJSJPSRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yjsjrq, i, KcfksjBVO.YJSJRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yqxgrq, i, KcfksjBVO.YQXGRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(wgyqts, i, KcfksjBVO.WGYQTS);
			//end by crf 2015-07-08
			if (jbryb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jbry, i, KcfksjBVO.JBRY);
			}
			if (kcryb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcry, i, KcfksjBVO.KCRY);
			}
			if (kcsjryb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcsjry, i, KcfksjBVO.KCSJRY);
			}
			if (yqxgrb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yqxgr, i, KcfksjBVO.YQXGR);
			}
			if (kcrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcrq, i, KcfksjBVO.KCRQ);
			}
			if (kcfkrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcfkrq, i, KcfksjBVO.KCFKRQ);
			}
			if (jhxtrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jhxtrq, i, KcfksjBVO.JHXTRQ);
			}
			if (yjhhxrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yjhhxrq, i, KcfksjBVO.YJHHXRQ);
			}
			if (sjpswcrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sjpswcrq, i, KcfksjBVO.SJPSWCRQ);
			}
			if (yjsjpsrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yjsjpsrq, i, KcfksjBVO.YJSJPSRQ);
			}
			if (yjsjrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yjsjrq, i, KcfksjBVO.YJSJRQ);
			}
			if (yqxgrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yqxgrq, i, KcfksjBVO.YQXGRQ);
			}
			if (wgyqtsb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(wgyqts, i, KcfksjBVO.WGYQTS);
			}
		}
		return false;
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

}
