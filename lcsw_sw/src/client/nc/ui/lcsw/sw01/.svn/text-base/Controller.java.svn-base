package nc.ui.lcsw.sw01;

import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw01.LcswBzxxBVO;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.vo.trade.pub.HYBillVO;

/**
 * <b>报装信息控制类</b>
 * @version 1.0
 * @since 1.0
 * @author 梁展轩
 * @time 2014-08-04 15:42:26
 */
public class Controller extends AbstractManageController {

	public String[] getCardBodyHideCol() {
		return null;
	}

	public int[] getCardButtonAry() {
		return new int[]{
				IBillButton.Edit,
				IBillButton.Cancel,
//				IBillButton.Delete,
				IBillButton.Save,
//				IBillButton.Line,
				IBillButton.Query,
				IBillButton.Refresh,
				IBillButton.Return,
				IBillButton.Print,
				nc.ui.button.IButtons.BTN_CANCELORDER //撤单
		};
	}

	public boolean isShowCardRowNo() {
		return false;
	}

	public boolean isShowCardTotal() {
		return false;
	}

	public String getBillType() {
		return "SW01";
	}

	public String[] getBillVoName() {
		return new String[]{
				HYBillVO.class.getName(),
				LcswBzxxHVO.class.getName(),
				LcswBzxxBVO.class.getName()
		};
	}

	public String getBodyCondition() {
		return null;
	}

	public String getBodyZYXKey() {
		return null;
	}

	public int getBusinessActionType() {
		return IBusinessActionType.BD;
	}

	public String getChildPkField() {
		return new LcswBzxxBVO().getPKFieldName();
	}

	public String getHeadZYXKey() {
		return null;
	}

	public String getPkField() {
		return new LcswBzxxHVO().getPKFieldName();
	}

	public Boolean isEditInGoing() throws Exception {
		return null;
	}

	public boolean isExistBillStatus() {
		return false;
	}

	public boolean isLoadCardFormula() {
		return true;
	}

	public String[] getListBodyHideCol() {
		return null;
	}

	public int[] getListButtonAry() {
		return new int[]{
				IBillButton.Edit,
				IBillButton.Cancel,
//				IBillButton.Delete,
				IBillButton.Save,
//				IBillButton.Line,
				IBillButton.Query,
				IBillButton.Refresh,
				IBillButton.Card,
				IBillButton.Print,
				nc.ui.button.IButtons.BTN_CANCELORDER //撤单
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
