package nc.ui.lcsw.sw10;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.lcsw.sw10.BzfyBVO;
import nc.vo.pub.lang.UFBoolean;

public class XiaoZhangDialog extends UIDialog implements ValueChangedListener, ActionListener {

	private BillCardPanel sw10panel;
	private BillCardPanel billpanel;

	private static final long serialVersionUID = 1L;

	private UIPanel bnPanel = null;
	private UIButton okButton = null;
	private UIButton cancelButton = null;

	String pk_warehouse = null;
	Double money = null;

	public XiaoZhangDialog() {
		initialize();
	}

	public XiaoZhangDialog(BillCardPanel sw10panel) {
		// super(parent, title);
		this.sw10panel = sw10panel;
		initialize();
	}

	private void initialize() {
		this.setTitle("销帐");
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(500, 190));
		this.add(getBillCardPanel(), BorderLayout.CENTER);
		this.add(getBnPanel(), "South");
		getBillCardPanel().setHeadItem("skrq", ClientEnvironment.getInstance().getDate());
		getBillCardPanel().setHeadItem("skry", ClientEnvironment.getInstance().getUser().getPrimaryKey());
	}

	private UIPanel getBnPanel() {
		if (this.bnPanel == null) {
			this.bnPanel = new UIPanel();
			this.bnPanel.setLayout(null);
			this.bnPanel.setPreferredSize(new Dimension(80, 60));
			this.bnPanel.add(getOkButton(), null);
			this.bnPanel.add(getCancelButton(), null);
		}
		return this.bnPanel;
	}

	private UIButton getOkButton() {
		if (this.okButton == null) {
			this.okButton = new UIButton();
			this.okButton.setBounds(new Rectangle(120, 10, 30, 20));
			this.okButton.setText(NCLangRes.getInstance().getStrByID("common", "UC001-0000044"));

			this.okButton.setPreferredSize(new Dimension(30, 22));
			this.okButton.addActionListener(this);
		}
		return this.okButton;
	}

	private UIButton getCancelButton() {
		if (this.cancelButton == null) {
			this.cancelButton = new UIButton();
			this.cancelButton.setBounds(new Rectangle(220, 10, 30, 20));
			this.cancelButton.setText(NCLangRes.getInstance().getStrByID("common", "UC001-0000008"));

			this.cancelButton.setPreferredSize(new Dimension(60, 22));
			this.cancelButton.addActionListener(this);
		}
		return this.cancelButton;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(getOkButton())) {
			String ssje = getBillCardPanel().getHeadItem("ssje").getValue();
			String skrq = getBillCardPanel().getHeadItem("skrq").getValue();
			String skry = getBillCardPanel().getHeadItem("skry").getValue();
			String skfs = getBillCardPanel().getHeadItem("skfs").getValue();

			if (ssje == null || ssje.trim().equals("")) {
				MessageDialog.showErrorDlg(getBillCardPanel(), "错误", "实收金额不能为空");
				return;
			}
			if (skrq == null || skrq.trim().equals("")) {
				MessageDialog.showErrorDlg(getBillCardPanel(), "错误", "收款日期不能为空");
				return;
			}
			if (skry == null || skry.trim().equals("")) {
				MessageDialog.showErrorDlg(getBillCardPanel(), "错误", "收款人员不能为空");
				return;
			}
			if (skfs == null || skfs.trim().equals("")) {
				MessageDialog.showErrorDlg(getBillCardPanel(), "错误", "收款方式不能为空");
				return;
			}

			int[] rowindex = this.sw10panel.getBillTable().getSelectedRows();

			for (int i = 0; i < rowindex.length; i++) {
				sw10panel.setBodyValueAt(skfs, rowindex[i], BzfyBVO.PK_BALATYPE);
				sw10panel.setBodyValueAt(ssje, rowindex[i], BzfyBVO.AMOUNT);
				sw10panel.setBodyValueAt(skry, rowindex[i], BzfyBVO.SFRY);
				sw10panel.setBodyValueAt(skrq, rowindex[i], BzfyBVO.XZRQ);
				sw10panel.setBodyValueAt(UFBoolean.TRUE, rowindex[i], BzfyBVO.XZBZ);
			}

			setResult(1);
			getResult();
			dispose();
		} else if (e.getSource().equals(getCancelButton())) {
			setResult(2);
			dispose();
		}
	}

	public void valueChanged(ValueChangedEvent event) {

	}

	private BillCardPanel getBillCardPanel() {
		if (billpanel == null) {
			billpanel = new BillCardPanel();
			billpanel.loadTemplet("0001AA1000000000L0YJ");
			billpanel.setVisible(true);
			billpanel.setEnabled(true);
		}
		return this.billpanel;
	}
}