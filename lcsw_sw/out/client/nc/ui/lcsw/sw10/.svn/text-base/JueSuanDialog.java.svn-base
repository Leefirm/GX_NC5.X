package nc.ui.lcsw.sw10;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nc.ui.lcsw.sw01.ref.BzxxRefModel;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw10.BzfyBVO;
import nc.vo.lcsw.sw10.BzfyHVO;
import nc.vo.pub.ValuepkUtils;
import nc.vo.pub.lang.UFDouble;

public class JueSuanDialog  extends UIDialog implements ValueChangedListener,
		ActionListener {
	
	private BillCardPanel sw10panel;
	private BillCardPanel billpanel;
	
	private static final long serialVersionUID = 1L;

	private UIPanel bnPanel = null;
	private UIButton okButton = null;
	private UIButton cancelButton = null;

	String pk_warehouse = null;
	Double money=null;
	public JueSuanDialog() {
		initialize();
	}
	
	public JueSuanDialog(BillCardPanel sw10panel) {
//		super(parent, title);
		this.sw10panel = sw10panel;
		initialize();
	}


	private void initialize() {
		this.setTitle("决算工程款");
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(500, 190));
		this.add(getBillCardPanel(), BorderLayout.CENTER);
		this.add(getBnPanel(),"South");
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
			this.okButton.setText(NCLangRes.getInstance().getStrByID("common",
					"UC001-0000044"));

			this.okButton.setPreferredSize(new Dimension(30, 22));
			this.okButton.addActionListener(this);
		}
		return this.okButton;
	}

	private UIButton getCancelButton() {
		if (this.cancelButton == null) {
			this.cancelButton = new UIButton();
			this.cancelButton.setBounds(new Rectangle(220, 10, 30, 20));
			this.cancelButton.setText(NCLangRes.getInstance().getStrByID(
					"common", "UC001-0000008"));

			this.cancelButton.setPreferredSize(new Dimension(60, 22));
			this.cancelButton.addActionListener(this);
		}
		return this.cancelButton;
	}

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(getOkButton())) {
			String jsje = getBillCardPanel().getHeadItem("jsje").getValue();
			String jbrq = getBillCardPanel().getHeadItem("jbrq").getValue();
			String jbry = getBillCardPanel().getHeadItem("jbry").getValue();
			String skfs = getBillCardPanel().getHeadItem("skfs").getValue();
			
			if(jsje == null || jsje.trim().equals("")){
				MessageDialog.showErrorDlg(getBillCardPanel(), "错误", "决算金额不能为空");
				return;
			}
			if(jbrq == null || jbrq.trim().equals("")){
				MessageDialog.showErrorDlg(getBillCardPanel(), "错误", "经办日期不能为空");
				return;
			}
			if(jbry == null || jbry.trim().equals("")){
				MessageDialog.showErrorDlg(getBillCardPanel(), "错误", "经办人员不能为空");
				return;
			}
			if(skfs == null || skfs.trim().equals("")){
				MessageDialog.showErrorDlg(getBillCardPanel(), "错误", "收款方式不能为空");
				return;
			}
			
			this.sw10panel.setHeadItem(BzfyHVO.JSGCK, jsje);
			ShareBodyUtils.ShareBody(ValuepkUtils.JSGCK, null, this.sw10panel, jbrq, jbry, skfs,new UFDouble(jsje));
			
//			this.sw10panel.getBillModel().addLine();
//			int rowindex = this.sw10panel.getRowCount()-1;
//			sw10panel.setBodyValueAt(ValuepkUtils.JSGCK, rowindex, BzfyBVO.PK_COSTSUBJ);
//			sw10panel.setBodyValueAt(skfs, rowindex, BzfyBVO.PK_BALATYPE);
//			sw10panel.setBodyValueAt(jsje, rowindex, BzfyBVO.AMOUNT);
//			sw10panel.setBodyValueAt(jbry, rowindex, BzfyBVO.SFRY);
//			sw10panel.setBodyValueAt(jbrq, rowindex, BzfyBVO.TFRQ);
			
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


	
	
	private BillCardPanel  getBillCardPanel(){
		if (billpanel == null) {
			billpanel = new BillCardPanel();
			billpanel.loadTemplet("0001AA1000000000L0Y4");
			billpanel.setVisible(true);
			billpanel.setEnabled(true);
		}
		return this.billpanel;
	}
}