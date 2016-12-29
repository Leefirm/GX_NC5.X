package nc.ui.lcsw.sw04;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.ui.lcsw.sw10.SW10ManageEventHandler;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.trade.bill.BillCardPanelWrapper;
import nc.ui.trade.bill.ICardController;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.trade.manage.BillManageUI;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lcsw.sw03.KcpgHVO;
import nc.vo.lcsw.sw03.itf.IKcpg;
import nc.vo.lcsw.sw04.KcfksjBVO;
import nc.vo.lcsw.sw04.KcfksjHVO;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 
 * @author Administrator
 * @createtime 2014-08-07 17:30:01
 * 
 */
public class ClientEventHandler extends SW10ManageEventHandler {

	ClientUI clientUI = null;
	private IUAPQueryBS queryBS;

	public ClientEventHandler(BillManageUI billUI, ICardController control) {
		super(billUI, control);
	}

	@Override
	public void onBoAdd(ButtonObject bo) throws Exception {
		super.onBoAdd(bo);
	}

	@Override
	protected void onBoDel() throws Exception {
		if (getBillUI().showYesNoMessage("�Ƿ�ִ��ɾ������?") == MessageDialog.ID_YES) {
			super.onBoDel();
		}
	}

	@Override
	protected void onBoElse(int intBtn) throws Exception {

		super.onBoElse(intBtn);
	}

	// ɾ���¼�
	@Override
	protected void onBoLineDel() throws Exception {
		int[] i = getBillCardPanelWrapper().getBillCardPanel().getBillTable().getSelectedRows();
		if (i.length > 0 && getBillUI().showYesNoMessage("�Ƿ�ɾ��ѡ�е���?") == MessageDialog.ID_YES) {
			super.onBoLineDel();
		}
	}

	@Override
	protected void onBoLineAdd() throws Exception {
		super.onBoLineAdd();
		int row = getBillCardPanelWrapper().getBillCardPanel().getRowCount() - 1;
		String pk_corp = getBillCardPanelWrapper().getBillCardPanel().getHeadItem("pk_corp").getValue();
		getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(pk_corp, row, "pk_corp");
	}

	/*
	 * ��ѯ
	 */
	@Override
	protected void onBoQuery() throws Exception {

		StringBuffer strWhere = new StringBuffer();
		if (askForQueryCondition(strWhere) == false)
			return;// �û������˲�ѯ
		// ��������: pk_corp='0001'
		String str = strWhere.toString();
		Pattern pattern = Pattern.compile(" and pk_corp='.{4}' ");

		Matcher matcher = pattern.matcher(str);
		str = matcher.replaceFirst(" ");
		SuperVO[] queryVos = queryHeadVOs(str);
		if (queryVos.length == 0) {
			getBillUI().showErrorMessage("û�з���Ҫ������ݣ�");
		}
		getBufferData().clear();
		// �������ݵ�Buffer

		addDataToBuffer(queryVos);
		updateBuffer();

	}

	@Override
	protected void onBoEdit() throws Exception {
		// TODO Auto-generated method stub

		super.onBoEdit();

	}

	/**
	 * ͨ���кź��ֶ�������ȡDouble���͵�ֵ
	 * 
	 * @param row
	 * @param name
	 * @return
	 */
	public UFDouble getUFValue(int row, String name) {

		Object obj = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(row, name);
		UFDouble ufValue = null;

		if (obj != null) {
			ufValue = new UFDouble(obj.toString());
			return ufValue;
		}

		return ufValue;
	}

	@Override
	protected void onBoSave() throws Exception {
		getBillCardPanelWrapper().getBillCardPanel().dataNotNullValidate();
		int rows = getBillCardPanelWrapper().getBillCardPanel().getRowCount();
		if (rows <= 0) {
			getBillUI().showErrorMessage("���岻��Ϊ�գ�");
			return;
		}
		
		if(this.setSaveHandler())
			return;
			
		super.onBoSave();
	}

	@SuppressWarnings("deprecation")
	private Boolean setSaveHandler() throws Exception {
		String jbry = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.JBRY).getValue();
		String kcry = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.KCRY).getValue();
		String kcsjry = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.KCSJRY).getValue();
		String yqxgr = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.YQXGR).getValue();
		// ��������
		String kcrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.KCRQ).getValue();
		// ���췴������
		String kcfkrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.KCFKRQ).getValue();
		// �Ӻ���ͼ����
		String jhxtrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.JHXTRQ).getValue();
		// �ƽ�����������
		String yjhhxrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.YJHHXRQ).getValue();
		// ��������������
		String sjpswcrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.SJPSWCRQ).getValue();
		// �ƽ������������
		String yjsjpsrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.YJSJPSRQ).getValue();
		// �ƽ��������
		String yjsjrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.YJSJRQ).getValue();
		// �����޸�����
		String yqxgrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.YQXGRQ).getValue();
		// �깤��������
		String wgyqts = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.WGYQTS).getValue();
		
		//�����ɹ���[��������]<=[��ǰ����]��
		UFDate today = ClientEnvironment.getInstance().getBusinessDate();
		if(today.before(new UFDate(kcrq))){
			getBillUI().showErrorMessage("[��������]���ܴ���[��ǰ����]��");
//			getBillUI().showErrorMessage("[��ǰ����]����С��[��������]��");
			return true;
		}
		//�����ɹ���[�������]<=[��������]
		String pk_jobbasfil = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(KcfksjHVO.PK_JOBBASFIL).getValue();
		KcpgHVO[] pghvos  = (KcpgHVO[])HYPubBO_Client.queryByCondition(KcpgHVO.class, 
				" vbillstatus = 1 and pk_jobbasfil = '"+pk_jobbasfil+"' order by ts desc");
		if(pghvos[0].getDapprovedate() !=null) {
		if(pghvos.length > 0 && pghvos[0].getDapprovedate().after(new UFDate(kcrq))){
			getBillUI().showErrorMessage("[��������]����С�ڿ����ɹ���[�������]��");
			return true;
		}
		}
		//[���췴������]<=[��ǰ����]
		if(today.before(new UFDate(kcfkrq))){
			getBillUI().showErrorMessage("[���췴������]���ܴ���[��ǰ����]��");
//			getBillUI().showErrorMessage("[��ǰ����]����С��[���췴������]��");
			return true;
		}
		//[��������]<=[���췴������]��
		if(new UFDate(kcfkrq).before(new UFDate(kcrq))){
//			getBillUI().showErrorMessage("[��������]���ܴ���[���췴������]��");
			getBillUI().showErrorMessage("[���췴������]����С��[��������]��");
			return true;
		}
		//[�ƽ��������]<=[��ǰ����]
		if(yjsjrq != null && !yjsjrq.equals("") && today.before(new UFDate(yjsjrq))){
//			getBillUI().showErrorMessage("[�ƽ��������]���ܴ���[��ǰ����]��");
			getBillUI().showErrorMessage("[��ǰ����]����С��[�ƽ��������]��");
			return true;
		}
		//[���췴������]<=[�ƽ��������]��
		if(yjsjrq != null && !yjsjrq.equals("") && new UFDate(yjsjrq).before(new UFDate(kcfkrq))){
//			getBillUI().showErrorMessage("[���췴������]���ܴ���[�ƽ��������]��");
			getBillUI().showErrorMessage("[�ƽ��������]����С��[���췴������]��");
			return true;
		}
		//��������<=[��ǰ����]��
		if(!StringUtil.isEmptyWithTrim(yjhhxrq) && today.before(new UFDate(yjhhxrq))){
			getBillUI().showErrorMessage("[�ƽ�����������]���ܴ���[��ǰ����]��");
			return true;
		}
		if(!StringUtil.isEmptyWithTrim(jhxtrq) && today.before(new UFDate(jhxtrq))){
			getBillUI().showErrorMessage("[�Ӻ���ͼ����]���ܴ���[��ǰ����]��");
			return true;
		}
		if(!StringUtil.isEmptyWithTrim(yjsjpsrq) && today.before(new UFDate(yjsjpsrq))){
			getBillUI().showErrorMessage("[�ƽ������������]���ܴ���[��ǰ����]��");
			return true;
		}
		if(!StringUtil.isEmptyWithTrim(sjpswcrq) && today.before(new UFDate(sjpswcrq))){
			getBillUI().showErrorMessage("[��������������]���ܴ���[��ǰ����]��");
			return true;
		}
		

		IKcpg kcpgService = NCLocator.getInstance().lookup(IKcpg.class);

		int rowcount = getBillCardPanelWrapper().getBillCardPanel().getRowCount();

		for (int i = 0; i < rowcount; i++) {

			// ������
			Object jbryb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.JBRY);
			// ������Ա
			Object kcryb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.KCRY);
			// ���������Ա
			Object kcsjryb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.KCSJRY);
			// �����޸���
			Object yqxgrb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.YQXGR);
			// ��������
			Object kcrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.KCRQ);
			// ���췴������
			Object kcfkrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.KCFKRQ);
			// �Ӻ���ͼ����
			Object jhxtrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.JHXTRQ);
			// �ƽ�����������
			Object yjhhxrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.YJHHXRQ);
			// ��������������
			Object sjpswcrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.SJPSWCRQ);
			// �ƽ������������
			Object yjsjpsrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.YJSJPSRQ);
			// �ƽ��������
			Object yjsjrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.YJSJRQ);
			// �����޸�����
			Object yqxgrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.YQXGRQ);
			// �깤��������
			Object wgyqtsb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, KcfksjBVO.WGYQTS);
			
			//modify by crf 2015-07-08
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jbry, i, KcfksjBVO.JBRY);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcry, i, KcfksjBVO.KCRY);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcsjry, i, KcfksjBVO.KCSJRY);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yqxgr, i, KcfksjBVO.YQXGR);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcrq, i, KcfksjBVO.KCRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcfkrq, i, KcfksjBVO.KCFKRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jhxtrq, i, KcfksjBVO.JHXTRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yjhhxrq, i, KcfksjBVO.YJHHXRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sjpswcrq, i, KcfksjBVO.SJPSWCRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yjsjpsrq, i, KcfksjBVO.YJSJPSRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yjsjrq, i, KcfksjBVO.YJSJRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yqxgrq, i, KcfksjBVO.YQXGRQ);
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(wgyqts, i, KcfksjBVO.WGYQTS);
			//end by crf 2015-07-08
			if (jbryb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jbry, i, KcfksjBVO.JBRY);
			}
			if (kcryb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcry, i, KcfksjBVO.KCRY);
			}
			if (kcsjryb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcsjry, i, KcfksjBVO.KCSJRY);
			}
			if (yqxgrb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yqxgr, i, KcfksjBVO.YQXGR);
			}
			if (kcrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcrq, i, KcfksjBVO.KCRQ);
			}
			if (kcfkrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kcfkrq, i, KcfksjBVO.KCFKRQ);
			}
			if (jhxtrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jhxtrq, i, KcfksjBVO.JHXTRQ);
			}
			if (yjhhxrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yjhhxrq, i, KcfksjBVO.YJHHXRQ);
			}
			if (sjpswcrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sjpswcrq, i, KcfksjBVO.SJPSWCRQ);
			}
			if (yjsjpsrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yjsjpsrq, i, KcfksjBVO.YJSJPSRQ);
			}
			if (yjsjrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yjsjrq, i, KcfksjBVO.YJSJRQ);
			}
			if (yqxgrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yqxgrq, i, KcfksjBVO.YQXGRQ);
			}
			if (wgyqtsb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(wgyqts, i, KcfksjBVO.WGYQTS);
			}
		}
		return false;
	}

	@Override
	protected void onBoCommit() throws Exception {
		// ȡ�ÿ�Ƭ
		BillCardPanelWrapper billCardPanelWrapper = getBillCardPanelWrapper();
		BillCardPanel billCardPanel = billCardPanelWrapper.getBillCardPanel();
		// ȡ�ú�̨��ѯ���
		queryBS = queryBS == null ? (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class) : queryBS;
		super.onBoCommit();

	}

	@Override
	public void onBoAudit() throws Exception {
		super.onBoAudit();
	}

	@Override
	protected void onBoCard() throws Exception {
		// TODO �Զ����ɷ������
		super.onBoCard();
	}

	@Override
	protected void onBoCancel() throws Exception {
		// TODO Auto-generated method stub
		super.onBoCancel();
	}

	@Override
	protected void onBoCancelAudit() throws Exception {
		// TODO Auto-generated method stub
		super.onBoCancelAudit();
	}

}