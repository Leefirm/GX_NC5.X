/**    Create By NCPlugin beta 0.1.   **/
package nc.ui.lcsw.sw03.kcpg;

import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.ui.trade.bill.AbstractManageController;
import nc.vo.lcsw.sw03.KcpgAggVO;
import nc.vo.lcsw.sw03.KcpgBVO;
import nc.vo.lcsw.sw03.KcpgHVO;

/**
 * 
 * @author Administrator
 * @createtime 2014-08-07 17:30:01
 * 
 */
public class ClientCtrl extends AbstractManageController {

	public String[] getCardBodyHideCol() {
		return null;
	}

	public int[] getCardButtonAry() {

		return new int[] { IBillButton.Add, IBillButton.Edit, IBillButton.Save, IBillButton.Cancel, IBillButton.Commit, IBillButton.Audit,
				IBillButton.CancelAudit, IBillButton.ApproveInfo, IBillButton.Delete, IBillButton.Query, IBillButton.Return, IBillButton.Refresh,
				IBillButton.File,IBillButton.Print

		};
	}

	public int[] getListButtonAry() {

		return new int[] { IBillButton.Add, IBillButton.Edit, IBillButton.Line, IBillButton.Save, IBillButton.Cancel, IBillButton.Commit, IBillButton.Audit,
				IBillButton.CancelAudit, IBillButton.ApproveInfo, IBillButton.Delete, IBillButton.Query, IBillButton.Card, IBillButton.Refresh, IBillButton.Print};

	}

	public boolean isShowCardRowNo() {
		return true;
	}

	public boolean isShowCardTotal() {
		return true;
	}

	public String getBillType() {
		// return YJBillType.SHOPPROFITBAS;
		return "SW03";
	}

	public String[] getBillVoName() {
		return new String[] { KcpgAggVO.class.getName(), KcpgHVO.class.getName(), KcpgBVO.class.getName() };
	}

	public String getBodyCondition() {
		return null;
	}

	public String getBodyZYXKey() {
		return null;
	}

	public int getBusinessActionType() {
		return IBusinessActionType.PLATFORM;//
	}

	public String getChildPkField() {
		return "pk_kcpgbid";
	}

	public String getHeadZYXKey() {
		return null;
	}

	public String getPkField() {
		return "pk_kcpghid";
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

	public String[] getListHeadHideCol() {
		return null;
	}

	public boolean isShowListRowNo() {
		return true;
	}

	public boolean isShowListTotal() {
		return true;
	}

}
