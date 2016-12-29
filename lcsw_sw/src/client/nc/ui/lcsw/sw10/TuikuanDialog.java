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
import nc.vo.lcsw.sw10.BzfyBVO;
import nc.vo.lcsw.sw10.BzfyHVO;

public class TuikuanDialog  extends UIDialog implements ValueChangedListener,
		ActionListener {
	
	private BillCardPanel sw10panel;
	private BillCardPanel billpanel;
	
	private static final long serialVersionUID = 1L;

	private UIPanel bnPanel = null;
	private UIButton okButton = null;
	private UIButton cancelButton = null;

	String pk_warehouse = null;
	Double money=null;
	public TuikuanDialog() {
		initialize();
	}
	
	public TuikuanDialog(BillCardPanel sw10panel) {
//		super(parent, title);
		this.sw10panel = sw10panel;
		initialize();
	}


	private void initialize() {
		this.setTitle("退款");
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(500, 190));
		this.add(getBillCardPanel(), BorderLayout.CENTER);
		this.add(getBnPanel(),"South");
		
		//设置报装信息的过滤条件
		String pk_bzsq = this.sw10panel.getHeadItem("pk_bzsq").getValue();
		UIRefPane pane = (UIRefPane)getBillCardPanel().getHeadItem("tkyh").getComponent();
		BzxxRefModel model = (BzxxRefModel)pane.getRefModel();
		model.addWherePart(" and pk_bzsq = '"+pk_bzsq+"'");
		pane.setRefModel(model);
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
			String tkyh = getBillCardPanel().getHeadItem("tkyh").getValue();
			String tfxm = getBillCardPanel().getHeadItem("tfxm").getValue();
			String tkje = getBillCardPanel().getHeadItem("tkje").getValue();
			String tkrq = getBillCardPanel().getHeadItem("tkrq").getValue();
			String skfs = getBillCardPanel().getHeadItem("skfs").getValue();
			String tkry = getBillCardPanel().getHeadItem("tkry").getValue();
			
			if(tkyh == null || tkyh.trim().equals("")){
				MessageDialog.showErrorDlg(getBillCardPanel(), "错误", "退款用户不能为空");
				return;
			}
			if(tfxm == null || tfxm.trim().equals("")){
				MessageDialog.showErrorDlg(getBillCardPanel(), "错误", "退费项目不能为空");
				return;
			}
			if(tkje == null || tkje.trim().equals("")){
				MessageDialog.showErrorDlg(getBillCardPanel(), "错误", "退款金额不能为空");
				return;
			}
			if(tkrq == null || tkrq.trim().equals("")){
				MessageDialog.showErrorDlg(getBillCardPanel(), "错误", "退款日期不能为空");
				return;
			}
			if(skfs == null || skfs.trim().equals("")){
				MessageDialog.showErrorDlg(getBillCardPanel(), "错误", "收款方式不能为空");
				return;
			}
			if(tkry == null || tkry.trim().equals("")){
				MessageDialog.showErrorDlg(getBillCardPanel(), "错误", "退费人员不能为空");
				return;
			}
			
			this.sw10panel.getBillModel().addLine();
			int rowindex = this.sw10panel.getRowCount()-1;
			sw10panel.setBodyValueAt(tkyh, rowindex, BzfyBVO.PK_BZXX);
			sw10panel.setBodyValueAt(tfxm, rowindex, BzfyBVO.PK_COSTSUBJ);
			sw10panel.setBodyValueAt(skfs, rowindex, BzfyBVO.PK_BALATYPE);
			sw10panel.setBodyValueAt(tkje, rowindex, BzfyBVO.AMOUNT);
			sw10panel.setBodyValueAt(tkrq, rowindex, BzfyBVO.TFRQ);
			sw10panel.setBodyValueAt(tkry, rowindex, BzfyBVO.SFRY);
			
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
			billpanel.loadTemplet("0001AA1000000000KSGN");
			billpanel.setVisible(true);
			billpanel.setEnabled(true);
		}
		return this.billpanel;
	}
}