package nc.ui.lcsw.sw10;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.bill.BillItem;
import nc.ui.trade.bill.ISingleController;
import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.vo.lcsw.sw03.KcpgHVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;

public class SW10ManageEventHandler extends nc.ui.trade.manage.ManageEventHandler {

	public SW10ManageEventHandler(BillManageUI billUI, IControllerBase control) {
		super(billUI, control);
	}

	@Override
	protected void onBoEdit() throws Exception {

		BillItem lastModifier = getBillCardPanelWrapper().getBillCardPanel().getTailItem("modifier");
		BillItem lastModifyTime = getBillCardPanelWrapper().getBillCardPanel().getTailItem("dmodify");
		if (null == lastModifier) {
			lastModifier = getBillCardPanelWrapper().getBillCardPanel().getHeadItem("modifier");
		}
		if (null == lastModifyTime) {
			lastModifyTime = getBillCardPanelWrapper().getBillCardPanel().getHeadItem("dmodify");
		}
		lastModifier.setValue(_getOperator());
		lastModifyTime.setValue(ClientEnvironment.getServerTime().getDate());

		super.onBoEdit();
	}

	@Override
	protected void onBoSave() throws Exception {
		AggregatedValueObject billVO = getBillUI().getChangedVOFromUI();
		setTSFormBufferToVO(billVO);
		AggregatedValueObject checkVO = getBillUI().getVOFromUI();
		CircularlyAccessibleValueObject[] childrenVOs = checkVO.getChildrenVO();
		for (CircularlyAccessibleValueObject vo : childrenVOs) {
			vo.setStatus(VOStatus.UPDATED);
			if (vo.getPrimaryKey() == null || vo.getPrimaryKey().equals("")) {
				vo.setStatus(VOStatus.NEW);
			}
		}

		List<CircularlyAccessibleValueObject> list = new ArrayList<CircularlyAccessibleValueObject>();
		CircularlyAccessibleValueObject[] changbvo = billVO.getChildrenVO();
		if (changbvo != null && changbvo.length > 0) {
			for (CircularlyAccessibleValueObject vo : changbvo) {
				if (vo.getStatus() == VOStatus.DELETED) {
					list.add(vo);
				}
			}
		}
		for (CircularlyAccessibleValueObject vo : childrenVOs) {
			list.add(vo);
		}

		billVO.setChildrenVO(list.toArray(new CircularlyAccessibleValueObject[list.size()]));

		setTSFormBufferToVO(checkVO);
		Object o = null;
		ISingleController sCtrl = null;
		if (getUIController() instanceof ISingleController) {
			sCtrl = (ISingleController) getUIController();
			if (sCtrl.isSingleDetail()) {
				o = billVO.getParentVO();
				billVO.setParentVO(null);
			} else {
				o = billVO.getChildrenVO();
				billVO.setChildrenVO(null);
			}
		}
		boolean isSave = true;
		if (billVO.getParentVO() == null && (billVO.getChildrenVO() == null || billVO.getChildrenVO().length == 0))
			isSave = false;
		else if (getBillUI().isSaveAndCommitTogether())
			billVO = getBusinessAction().saveAndCommit(billVO, getUIController().getBillType(), _getDate().toString(), getBillUI().getUserObject(), checkVO);
		else
			billVO = getBusinessAction().save(billVO, getUIController().getBillType(), _getDate().toString(), getBillUI().getUserObject(), checkVO);
		if (sCtrl != null && sCtrl.isSingleDetail())
			billVO.setParentVO((CircularlyAccessibleValueObject) o);
		int nCurrentRow = -1;
		if (isSave)
			if (isEditing()) {
				if (getBufferData().isVOBufferEmpty()) {
					getBufferData().addVOToBuffer(billVO);
					nCurrentRow = 0;
				} else {
					getBufferData().setCurrentVO(billVO);
					nCurrentRow = getBufferData().getCurrentRow();
				}
			} else {
				getBufferData().addVOsToBuffer(new AggregatedValueObject[] { billVO });
				nCurrentRow = getBufferData().getVOBufferSize() - 1;
			}
		if (nCurrentRow >= 0)
			getBufferData().setCurrentRow(nCurrentRow);
		setAddNewOperate(isAdding(), billVO);
		setSaveOperateState();
	}

}
