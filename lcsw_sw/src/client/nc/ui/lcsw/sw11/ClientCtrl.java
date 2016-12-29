package nc.ui.lcsw.sw11;

import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.vo.lcsw.sw11.SjfksjAggVO;
import nc.vo.lcsw.sw11.SjfksjBVO;
import nc.vo.lcsw.sw11.SjfksjHVO;
import nc.vo.trade.pub.HYBillVO;

/**
 * <b>设计反馈数据控制类</b>
 * @version 1.0
 * @since 1.0
 * @author 梁展轩
 * @time 2014-08-14 15:16:25
 */
public class ClientCtrl extends AbstractManageController {

	public String[] getCardBodyHideCol() {
		return null;
	}

	public int[] getCardButtonAry() {
		return new int[]{
				IBillButton.Add,
				IBillButton.Edit,
				IBillButton.Cancel,
				IBillButton.Delete,
				IBillButton.Save,
				IBillButton.Line,
				IBillButton.Query,
				IBillButton.Refresh,
				IBillButton.Return,
				IBillButton.Commit,
				IBillButton.Audit,
				IBillButton.CancelAudit,
				IBillButton.ApproveInfo,//审批情况
				IBillButton.File,
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
		return "SW11";
	}

	public String[] getBillVoName() {
		return new String[]{
				SjfksjAggVO.class.getName(),
				SjfksjHVO.class.getName(),
				SjfksjBVO.class.getName()
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
		return new SjfksjBVO().getPKFieldName();
	}

	public String getHeadZYXKey() {
		return null;
	}

	public String getPkField() {
		return new SjfksjHVO().getPKFieldName();
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
				IBillButton.Add,
				IBillButton.Edit,
				IBillButton.Cancel,
				IBillButton.Delete,
				IBillButton.Save,
				IBillButton.Line,
				IBillButton.Query,
				IBillButton.Refresh,
				IBillButton.Card,
				IBillButton.Commit,
				IBillButton.Audit,
				IBillButton.CancelAudit,
				IBillButton.ApproveInfo,//审批情况
				IBillButton.File,
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
