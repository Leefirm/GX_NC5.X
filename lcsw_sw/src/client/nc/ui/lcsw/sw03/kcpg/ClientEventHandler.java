/**    Create By NCPlugin beta 0.1.   **/
package nc.ui.lcsw.sw03.kcpg;

import java.util.HashMap;
import java.util.Map;
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
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw03.KcpgBVO;
import nc.vo.lcsw.sw03.KcpgHVO;
import nc.vo.lcsw.sw03.itf.IKcpg;
import nc.vo.lcsw.sw04.KcfksjBVO;
import nc.vo.lcsw.sw10.BzfyBVO;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.ValuepkUtils;
import nc.vo.pub.lang.UFBoolean;
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

	@Override
	protected void onBoLineAdd() throws Exception {
		super.onBoLineAdd();
		int row = getBillCardPanelWrapper().getBillCardPanel().getRowCount() - 1;
		String pk_corp = getBillCardPanelWrapper().getBillCardPanel().getHeadItem("pk_corp").getValue();
		getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(pk_corp, row, "pk_corp");
	}

	// 删行事件
	@Override
	protected void onBoLineDel() throws Exception {
		int[] i = getBillCardPanelWrapper().getBillCardPanel().getBillTable().getSelectedRows();
		if (i.length > 0 && getBillUI().showYesNoMessage("是否删除选中的行?") == MessageDialog.ID_YES) {
			super.onBoLineDel();
		}
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
		
//		UFBoolean sfsyx = new UFBoolean(getBillCardPanelWrapper().getBillCardPanel().getHeadItem("sfsyx").getValue());
		UFDate today = ClientEnvironment.getInstance().getBusinessDate();
		UFDate kcpgrqh =new UFDate(getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcpgHVO.KCPGRQ).getValue());			
		//三优先的，报装申请的[审核日期]<=[勘察派工日期]<=[当前日期]。
		if(today!=null && kcpgrqh!=null){
			if(today.before(kcpgrqh)){
				getBillUI().showErrorMessage("[勘察派工日期]不能大于[当前日期]！");
				return;
			}
		}
		String pk_bzsq = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(0, KcpgBVO.PK_BZSQ).toString();
		BzsqVO bzsqvo = (BzsqVO)HYPubBO_Client.queryByPrimaryKey(BzsqVO.class, pk_bzsq);
		if(bzsqvo!=null){
			if(bzsqvo.getSfsyx().booleanValue()){
				//报装申请的[审核日期]
				if(bzsqvo.getDapprovedate()!=null){
					if(bzsqvo.getDapprovedate().after(kcpgrqh)){
//						getBillUI().showErrorMessage("三优先工程的报装申请的[审核日期]不能大于[勘察派工日期]！");
						getBillUI().showErrorMessage("三优先工程的[勘察派工日期]不能小于报装申请的[审核日期]！");
						return;
					}
				}
			}
			//非三优先的，勘察设计费的[销账日期]<=[勘察派工日期]<=[当前日期]；
			else{
				for (int i = 0; i < rows; i++) {
					String pk_bzxx = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcpgBVO.PK_BZXX).toString();
					BzfyBVO[] fybvos = (BzfyBVO[])HYPubBO_Client.queryByCondition(BzfyBVO.class, "pk_bzxx = '"+pk_bzxx+"' and pk_costsubj = '"+ValuepkUtils.KCSJF+"' and dr = 0");
					if(fybvos == null || fybvos.length == 0){
						getBillUI().showErrorMessage("该工程未交勘察设计费！");
						return;
					}
					for (BzfyBVO bzfybvo : fybvos) {
						if(bzfybvo.getXzrq() == null ){
							LcswBzxxHVO xxhvo =(LcswBzxxHVO)HYPubBO_Client.queryByPrimaryKey(LcswBzxxHVO.class, bzfybvo.getPk_bzxx());
							getBillUI().showErrorMessage("该工程报装信息编号【"+xxhvo.getBzbh()+"】未交勘察设计费！");
							return;
						}else
						if(bzfybvo.getXzrq().after(kcpgrqh)){
//							getBillUI().showErrorMessage("非三优先工程的勘察设计费的[销账日期]不能大于[勘察派工日期]！");
							getBillUI().showErrorMessage("非三优先工程的[勘察派工日期]不能小于勘察设计费的[销账日期]！");
							return;
						}
					}
				}
			}
		}

		

		IKcpg kcpgService = NCLocator.getInstance().lookup(IKcpg.class);

		String hkcpgry = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcpgBVO.KCPGRY).getValue();
		String hkcpgrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcpgBVO.KCPGRQ).getValue();

		int rowcount = getBillCardPanelWrapper().getBillCardPanel().getRowCount();

		for (int i = 0; i < rowcount; i++) {

			Object kcpgry = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcpgBVO.KCPGRY);
			Object kcpgrq = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcpgBVO.KCPGRQ);

			if (kcpgry == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(hkcpgry, i, KcpgBVO.KCPGRY);
			}
			if (kcpgrq == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(hkcpgrq, i, KcpgBVO.KCPGRQ);
			}

		}
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

}
