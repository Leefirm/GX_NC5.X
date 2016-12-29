package nc.ui.lcsw.sw02;

import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.trade.pub.HYBillVO;

/**
 * <b>��װ���������</b>
 * @version 1.0
 * @since 1.0
 * @author xuns
 * @time 2014-08-04 14:44:34
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
				IBillButton.Query,
				IBillButton.Refresh,
				IBillButton.Return,
				IBillButton.Commit,
				IBillButton.Audit,
				IBillButton.CancelAudit,
				IBillButton.ApproveInfo,//�������
				IBillButton.Print

		};
	}

	public boolean isShowCardRowNo() {
		return false;
	}

	public boolean isShowCardTotal() {
		return false;
	}

	/**
	 * ��������
	 */
	public String getBillType() {
		return "SW02";
	}

	public String[] getBillVoName() {
		return new String[]{
				HYBillVO.class.getName(),
				BzsqVO.class.getName(),
				BzsqVO.class.getName()
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
		return new BzsqVO().getPKFieldName();
	}

	public String getHeadZYXKey() {
		return null;
	}

	public String getPkField() {
		return new BzsqVO().getPKFieldName();
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
				IBillButton.Query,
				IBillButton.Refresh,
				IBillButton.Card,
				IBillButton.Commit,
				IBillButton.Audit,
				IBillButton.CancelAudit,
				IBillButton.ApproveInfo,//�������
				IBillButton.Print

		};
	}

	public String[] getListHeadHideCol() {
		return null;
	}

	/**
	  * �Ƿ��б�״̬��ʾ�к�
	  */
	public boolean isShowListRowNo() {
		return true;
	}

	 /**
	  * �Ƿ��б�״̬��ʾ�ϼ�
	  */
	public boolean isShowListTotal() {
		return false;
	}

}
