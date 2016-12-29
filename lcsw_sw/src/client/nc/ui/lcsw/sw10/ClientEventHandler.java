package nc.ui.lcsw.sw10;

import java.util.HashMap;
import java.util.Map;

import com.ui.lcsw.util.LcswUIUtil;

import nc.bs.framework.common.NCLocator;
import nc.ui.button.IButtons;
import nc.ui.lcsw.sw10.print.JKTZDataSourceNew;
import nc.ui.lcsw.sw10.print.JKTZSDataSource;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.print.PrintEntry;
import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.pub.CardPanelPRTS;
import nc.vo.lcsw.sw03.itf.IKcpg;
import nc.vo.lcsw.sw10.BzfyBVO;
import nc.vo.lcsw.sw10.BzfyHVO;
import nc.vo.pub.SuperVO;
import nc.vo.pub.ValuepkUtils;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * <b>��װ�����¼���</b>
 * 
 * @version 1.0
 * @since 1.0
 * @author xuns
 * @time 2014-08-12 16:09:22
 */
public class ClientEventHandler extends SW10ManageEventHandler {

	private ClientUI clientUI;

	public ClientEventHandler(BillManageUI billUI, IControllerBase control) {
		super(billUI, control);
		this.clientUI = (ClientUI) billUI;
	}

	@Override
	protected void onBoSave() throws Exception {
		clientUI.getBillCardPanel().dataNotNullValidate();
		if (clientUI.getBillCardPanel().getHeadItem("pk_bzfy_h").getValue() != null
				&& !clientUI.getBillCardPanel().getHeadItem("pk_bzfy_h")
						.getValue().equals("")) {
			clientUI.getBillCardPanel().setTailItem("modifier",
					ClientEnvironment.getInstance().getUser().getPrimaryKey());
			clientUI.getBillCardPanel().setTailItem("dmodify",
					ClientEnvironment.getInstance().getDate());
		}

		UFDouble kcsjf = new UFDouble(0);
		UFDouble ysgck1q = new UFDouble(0);
		UFDouble ysgck2q = new UFDouble(0);
		UFDouble ysgck3q = new UFDouble(0);
		UFDouble bjkcf = new UFDouble(0);

		for (int i = 0; i < clientUI.getBillCardPanel().getRowCount(); i++) {
			if (clientUI.getBillCardPanel().getBodyValueAt(i,
					BzfyBVO.PK_COSTSUBJ).equals(ValuepkUtils.KCSJF))
				kcsjf = kcsjf.add(new UFDouble(clientUI.getBillCardPanel()
						.getBodyValueAt(i, BzfyBVO.AMOUNT) == null ? "0":clientUI.getBillCardPanel()
								.getBodyValueAt(i, BzfyBVO.AMOUNT).toString()));
			if (clientUI.getBillCardPanel().getBodyValueAt(i,
					BzfyBVO.PK_COSTSUBJ).equals(ValuepkUtils.YSGCK1Q))
				ysgck1q = ysgck1q.add(new UFDouble(clientUI.getBillCardPanel()
						.getBodyValueAt(i, BzfyBVO.AMOUNT) == null ? "0":clientUI.getBillCardPanel()
						.getBodyValueAt(i, BzfyBVO.AMOUNT).toString()));
			if (clientUI.getBillCardPanel().getBodyValueAt(i,
					BzfyBVO.PK_COSTSUBJ).equals(ValuepkUtils.YSGCK2Q))
				ysgck2q = ysgck2q.add(new UFDouble(clientUI.getBillCardPanel()
						.getBodyValueAt(i, BzfyBVO.AMOUNT) == null ? "0":clientUI.getBillCardPanel()
						.getBodyValueAt(i, BzfyBVO.AMOUNT).toString()));
			if (clientUI.getBillCardPanel().getBodyValueAt(i,
					BzfyBVO.PK_COSTSUBJ).equals(ValuepkUtils.YSGCK3Q))
				ysgck3q = ysgck3q.add(new UFDouble(clientUI.getBillCardPanel()
						.getBodyValueAt(i, BzfyBVO.AMOUNT) == null ? "0":clientUI.getBillCardPanel()
						.getBodyValueAt(i, BzfyBVO.AMOUNT).toString()));
			if (clientUI.getBillCardPanel().getBodyValueAt(i,
					BzfyBVO.PK_COSTSUBJ).equals(ValuepkUtils.BJKCF))
				bjkcf = bjkcf.add(new UFDouble(clientUI.getBillCardPanel()
						.getBodyValueAt(i, BzfyBVO.AMOUNT) == null ? "0":clientUI.getBillCardPanel()
						.getBodyValueAt(i, BzfyBVO.AMOUNT).toString()));
		}
		clientUI.getBillCardPanel().setHeadItem(BzfyHVO.KCSJF, kcsjf);
		clientUI.getBillCardPanel().setHeadItem(BzfyHVO.YSGCK1, ysgck1q);
		clientUI.getBillCardPanel().setHeadItem(BzfyHVO.YSGCK2, ysgck2q);
		clientUI.getBillCardPanel().setHeadItem(BzfyHVO.YSGCK3, ysgck3q);
		clientUI.getBillCardPanel().setHeadItem(BzfyHVO.BJKCF, bjkcf);

		super.onBoSave();
	}

	@Override
	protected void onBoElse(int intBtn) throws Exception {
		super.onBoElse(intBtn);
		getBillCardPanelWrapper().getBillCardPanel().stopEditing();

		if (intBtn == IButtons.BTN_KCSJFFT) {// ������Ʒѷ�̯
			Boolean isture = CheckVauleZero(BzfyHVO.KCSJF, "������Ʒ�");
			if (isture) {
				ShareBodyUtils.ShareBody(ValuepkUtils.KCSJF, BzfyHVO.KCSJF,
						getBillCardPanelWrapper().getBillCardPanel());
			}
		}
		if (intBtn == IButtons.BTN_YSGCK1Q) {// Ԥ�㹤�̿�1��
			Boolean isture = CheckVauleZero(BzfyHVO.YSGCK1, "Ԥ�㹤�̿�1��");
			if (isture) {
				ShareBodyUtils.ShareBody(ValuepkUtils.YSGCK1Q, BzfyHVO.YSGCK1,
						getBillCardPanelWrapper().getBillCardPanel());
			}
		}
		if (intBtn == IButtons.BTN_YSGCK2Q) {// Ԥ�㹤�̿�2��
			Boolean isture = CheckVauleZero(BzfyHVO.YSGCK2, "Ԥ�㹤�̿�2��");
			if (isture) {
				ShareBodyUtils.ShareBody(ValuepkUtils.YSGCK2Q, BzfyHVO.YSGCK2,
						getBillCardPanelWrapper().getBillCardPanel());
			}
		}
		if (intBtn == IButtons.BTN_YSGCK3Q) {// Ԥ�㹤�̿�3��
			Boolean isture = CheckVauleZero(BzfyHVO.YSGCK3, "Ԥ�㹤�̿�3��");
			if (isture) {
				ShareBodyUtils.ShareBody(ValuepkUtils.YSGCK3Q, BzfyHVO.YSGCK3,
						getBillCardPanelWrapper().getBillCardPanel());
			}
		}
		if (intBtn == IButtons.BTN_BJKCF) {// ��������ѷ�̯
			Boolean isture = CheckVauleZero(BzfyHVO.BJKCF, "��������ѷ�̯");
			if (isture) {
				ShareBodyUtils.ShareBody(ValuepkUtils.BJKCF, BzfyHVO.BJKCF,
						getBillCardPanelWrapper().getBillCardPanel());
			}
		}
		if (intBtn == IButtons.BTN_JSGCK) {// ���㹤�̿�
			// Boolean isture = CheckVauleZero(BzfyHVO.JSGCK,"���㹤�̿�");
			// if(isture){
			// ShareBody(ValuepkUtils.JSGCK,BzfyHVO.JSGCK);
			// }
			JueSuanDialog dialog = new JueSuanDialog(getBillCardPanelWrapper()
					.getBillCardPanel());
			dialog.showModal();
			getBillCardPanelWrapper().getBillCardPanel().getBillModel()
					.execLoadFormula();
		}
		if (intBtn == IButtons.BTN_TUIKUAN) {// �˿�
			TuikuanDialog dialog = new TuikuanDialog(getBillCardPanelWrapper()
					.getBillCardPanel());
			dialog.showModal();
			getBillCardPanelWrapper().getBillCardPanel().getBillModel()
					.execLoadFormula();
		}
		if (intBtn == IButtons.BTN_XIAOZHANG) {// ����
			int row = getBillCardPanelWrapper().getBillCardPanel()
					.getBillTable().getSelectedRow();
			if (row < 0) {
				MessageDialog.showErrorDlg(getBillUI(), "����", "��ѡ��һ�б���");
				return;
			}
			
			int[] rowindex = getBillCardPanelWrapper().getBillCardPanel().getBillTable().getSelectedRows();

			//panqh�Ѿ����ʲ�����������
			for (int i = 0; i < rowindex.length; i++) {
				Object bobj = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(rowindex[i], BzfyBVO.XZBZ);
				if(bobj instanceof Boolean && ((Boolean)bobj)) {
					MessageDialog.showErrorDlg(getBillUI(), "����", "�Ѿ����ʣ��������ٴ����ʣ�");
					return;
				}
			}
			
			XiaoZhangDialog dialog = new XiaoZhangDialog(
					getBillCardPanelWrapper().getBillCardPanel());
			dialog.showModal();
			getBillCardPanelWrapper().getBillCardPanel().getBillModel()
					.execLoadFormula();
		}
		//add by crf 2015-07-08
		if(intBtn == IButtons.BTN_CX_XIAOZHANG){
			//���������߼�
			int row = getBillCardPanelWrapper().getBillCardPanel().getBillTable().getSelectedRow();
			if(row < 0){
				MessageDialog.showErrorDlg(clientUI, "����", "��ѡ�������!");
				return;
			}
			//panqh
			//���ñ��
			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(null, row, BzfyBVO.PK_BALATYPE);
			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(null, row, BzfyBVO.AMOUNT);
			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(null, row, BzfyBVO.SFRY);
			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(null, row, BzfyBVO.XZRQ);
			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(UFBoolean.FALSE, row, BzfyBVO.XZBZ);
			getBillCardPanelWrapper().getBillCardPanel().getBillModel()
				.execLoadFormula();
			
			MessageDialog.showHintDlg(null, "��ʾ", "�������˳ɹ���");
		}
	}

	private Boolean CheckVauleZero(String kcsjf, String mes) {
		String value = getBillCardPanelWrapper().getBillCardPanel()
				.getHeadItem(kcsjf).getValue();
		if (value == null || value.equals("")) {
			MessageDialog.showErrorDlg(clientUI, "��ʾ", mes + "����Ϊ��!");
			return false;
		} else if (new UFDouble(value).getDouble() == 0) {
			MessageDialog.showErrorDlg(clientUI, "��ʾ", mes + "���ܵ���0!");
			return false;
		}
		return true;
	}
	/**
	 * by lijie
	 * ���²�ѯ�������� Сдȫ��ת��Ϊ��д
	 */
	protected SuperVO[] queryHeadVOs(String strWhere)
			throws Exception, ClassNotFoundException
		{
			String newstrwhere = LcswUIUtil.modifySqlWhere(strWhere);
			return super.queryHeadVOs(newstrwhere);
		}

	@Override
	protected void onBoQuery() throws Exception {
		super.onBoQuery();
	}
	
	@Override
	protected void onBoPrint() throws Exception {
		
		int row=clientUI.getBillListPanel().getHeadTable().getSelectedRow();
		BzfyHVO bzfyvo =(BzfyHVO) getBufferData().getVOByRowNo(row).getParentVO();
		String pk_bzsq = bzfyvo.getPk_bzsq();
		
		if(pk_bzsq == null) {
			throw new Exception("�Ҳ�����Ӧ�ı�װ���룬�޷���ӡ");
		}
		 
		nc.ui.pub.print.IDataSource dataSource = new JKTZDataSourceNew(pk_bzsq);
		PrintEntry print = new PrintEntry(null, dataSource);
		print.setTemplateID(getBillUI()._getCorp().getPrimaryKey(), getBillUI()._getModuleCode(), getBillUI()._getOperator(), getBillUI().getBusinessType(), getBillUI().getNodeKey());
		if (print.selectTemplate() == 1)
			print.preview();
		
//		nc.ui.pub.print.IDataSource dataSource = new JKTZSDataSource((ClientUI) getBillUI());
//		PrintEntry print = new PrintEntry(null, dataSource);
//		print.setTemplateID(getBillUI()._getCorp().getPrimaryKey(), getBillUI()._getModuleCode(), getBillUI()._getOperator(), getBillUI().getBusinessType(), getBillUI().getNodeKey());
//		if (print.selectTemplate() == 1)
//			print.preview();
	}

}
