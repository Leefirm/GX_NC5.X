package nc.ui.lcsw.sw10;

import nc.vo.lcsw.sw10.AggBzfyVO;
import nc.vo.lcsw.sw10.BzfyHVO;
import nc.vo.lcsw.sw10.BzfyBVO;
import nc.ui.button.IButtons;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.vo.trade.pub.HYBillVO;

/**
 * <b>报装费用控制类</b>
 * @version 1.0
 * @since 1.0
 * @author xuns
 * @time 2014-08-12 16:09:22
 */
public class ClientCtrl extends AbstractManageController {

	public String[] getCardBodyHideCol() {
		return null;
	}

	public int[] getCardButtonAry() {
		return new int[]{
//				IBillButton.Add,
				IBillButton.Edit,
				IBillButton.Cancel,
//				IBillButton.Delete,
				IBillButton.Save,
				IBillButton.Line,
				IBillButton.Query,
				IBillButton.Refresh,
				IBillButton.Return,
//				IBillButton.Commit,
//				IBillButton.Audit,
//				IBillButton.CancelAudit,
//				IBillButton.ApproveInfo,//审批情况
				IButtons.BTN_FUZHU,
				IBillButton.Print
		};
	}

	public boolean isShowCardRowNo() {
		return true;
	}

	public boolean isShowCardTotal() {
		return false;
	}

	public String getBillType() {
		return "SW10";
	}

	public String[] getBillVoName() {
		return new String[]{
				AggBzfyVO.class.getName(),
				BzfyHVO.class.getName(),
				BzfyBVO.class.getName()
		};
	}

	public String getBodyCondition() {
		return null;
	}

	public String getBodyZYXKey() {
		return null;
	}

	public int getBusinessActionType() {
		return IBusinessActionType.PLATFORM;
	}

	public String getChildPkField() {
		return new BzfyBVO().getPKFieldName();
	}

	public String getHeadZYXKey() {
		return null;
	}

	public String getPkField() {
		return new BzfyHVO().getPKFieldName();
	}

	public Boolean isEditInGoing() throws Exception {
		return null;
	}

	public boolean isExistBillStatus() {
		return true;
	}

	public boolean isLoadCardFormula() {
		return true;
	}

	public String[] getListBodyHideCol() {
		return null;
	}

	public int[] getListButtonAry() {
		return new int[]{
//				IBillButton.Add,
				IBillButton.Edit,
				IBillButton.Cancel,
//				IBillButton.Delete,
				IBillButton.Save,
				IBillButton.Line,
				IBillButton.Query,
				IBillButton.Refresh,
				IBillButton.Card,
//				IBillButton.Commit,
//				IBillButton.Audit,
//				IBillButton.CancelAudit,
//				IBillButton.ApproveInfo,//审批情况
				IButtons.BTN_FUZHU,
				IBillButton.Print
		};
	}

	public String[] getListHeadHideCol() {
		return null;
	}

	public boolean isShowListRowNo() {
		return true;
	}

	public boolean isShowListTotal() {
		return false;
	}

}
