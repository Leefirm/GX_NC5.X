/**    Create By NCPlugin beta 0.1.   **/
package nc.ui.lcsw.sw06;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.ui.lcsw.sw10.SW10ManageEventHandler;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.trade.bill.BillCardPanelWrapper;
import nc.ui.trade.bill.ICardController;
import nc.ui.trade.manage.BillManageUI;
import nc.vo.lcsw.sw03.itf.IKcpg;
import nc.vo.lcsw.sw06.FbyjazBVO;
import nc.vo.lcsw.sw06.FbyjazVO;
import nc.vo.lcsw.sw06.itf.IFbyjaz;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
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
		if (getBillUI().showYesNoMessage("是否执行删除操作?") == MessageDialog.ID_YES) {
			super.onBoDel();
		}
	}

	@Override
	protected void onBoElse(int intBtn) throws Exception {

		super.onBoElse(intBtn);
	}

	// 删行事件
	@Override
	protected void onBoLineDel() throws Exception {
		int[] i = getBillCardPanelWrapper().getBillCardPanel().getBillTable().getSelectedRows();
		if (i.length > 0 && getBillUI().showYesNoMessage("是否删除选中的行?") == MessageDialog.ID_YES) {
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
	 * 查询
	 */
	@Override
	protected void onBoQuery() throws Exception {

		StringBuffer strWhere = new StringBuffer();
		if (askForQueryCondition(strWhere) == false)
			return;// 用户放弃了查询
		// 屏蔽条件: pk_corp='0001'
		String str = strWhere.toString();
		Pattern pattern = Pattern.compile(" and pk_corp='.{4}' ");

		Matcher matcher = pattern.matcher(str);
		str = matcher.replaceFirst(" ");
		SuperVO[] queryVos = queryHeadVOs(str);
		if (queryVos.length == 0) {
			getBillUI().showErrorMessage("没有符合要求的数据！");
		}
		getBufferData().clear();
		// 增加数据到Buffer

		addDataToBuffer(queryVos);
		updateBuffer();

	}

	@Override
	protected void onBoEdit() throws Exception {
		// TODO Auto-generated method stub
		BillItem lastModifier = getBillCardPanelWrapper().getBillCardPanel().getTailItem(FbyjazVO.MODIFIER);
		BillItem lastModifyTime = getBillCardPanelWrapper().getBillCardPanel().getTailItem(FbyjazVO.DMODIFY);
		if (null == lastModifier) {
			lastModifier = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.MODIFIER);
		}
		if (null == lastModifyTime) {
			lastModifyTime = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.DMODIFY);
		}
		lastModifier.setValue(_getOperator());
		lastModifyTime.setValue(ClientEnvironment.getServerTime());
		super.onBoEdit();

	}

	/**
	 * 通过行号和字段名，获取Double类型的值
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
			getBillUI().showErrorMessage("表体不能为空！");
			return;
		}

		this.setSaveHandler();

		this.checkSave();

		super.onBoSave();
	}

	@SuppressWarnings("deprecation")
	private void checkSave() throws Exception {
		UFDouble ysszje = new UFDouble(getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.YSSZJE).getValue());
		UFDouble yhysk = new UFDouble(getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.YHYSK).getValue());
		UFDouble yhczje = new UFDouble(getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.YHCZJE).getValue());
		UFDouble gsczje = new UFDouble(getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.GSCZJE).getValue());
		String sfht = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.SFYSGHT).getValue();
		String hth = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.HTH).getValue();
		String htqdrq=getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.HTQDRQ).getValue();
		String pk_jobbasfil= getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.PK_JOBBASFIL).getValue();
		IFbyjaz fbyjaz= NCLocator.getInstance().lookup(IFbyjaz.class);
		
		if (ysszje.toDouble() < yhysk.add(yhczje.add(gsczje)).toDouble()) {
			throw new BusinessException("预算书总金额>=用户预算款+用户出资金额+公司出资金额");
		}

		if (!gsczje.toDouble().equals(yhysk.sub(yhczje).toDouble())) {
			throw new BusinessException("公司出资金额=用户预算款-用户出资金额");
		}
		if (sfht != null && sfht.equals("true")) {
			if (hth == null || hth.equals("")) {
				throw new BusinessException("合同号不能为空");
			}
			if (htqdrq == null || htqdrq.equals("")) {
				throw new BusinessException("合同签订日期不能为空");
			}
		}

		if(pk_jobbasfil!=null && pk_jobbasfil.length()>0){
			String sfgtcz= fbyjaz.querySFGTCZ(pk_jobbasfil);
			if(sfgtcz!=null && sfgtcz.length()>0){
				if(sfgtcz.equals("N")){
					String zbsqdrq= getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.ZBSQDRQ).getValue();
					String szbjrq=getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.SZBJRQ).getValue();
					if(zbsqdrq==null || zbsqdrq.equals("")){
						throw new BusinessException("非共同出资项目,[质保书签订日期]不能为空!");
					}
					if(szbjrq==null || szbjrq.equals("")){
						throw new BusinessException("非共同出资项目,[收质保金日期]不能为空!");
					}
				}
			}
		}
	}

	@Override
	protected void onBoCommit() throws Exception {
		// 取得卡片
		BillCardPanelWrapper billCardPanelWrapper = getBillCardPanelWrapper();
		BillCardPanel billCardPanel = billCardPanelWrapper.getBillCardPanel();
		// 取得后台查询组件
		queryBS = queryBS == null ? (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class) : queryBS;
		super.onBoCommit();

	}

	@Override
	public void onBoAudit() throws Exception {
		super.onBoAudit();
	}

	@Override
	protected void onBoCard() throws Exception {
		// TODO 自动生成方法存根
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

	/**
	 * 保存动作事件
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	private void setSaveHandler() throws Exception {

		IKcpg kcpgService = NCLocator.getInstance().lookup(IKcpg.class);

		int rowcount = getBillCardPanelWrapper().getBillCardPanel().getRowCount();

		String hth = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.HTH).getValue();
		String yfkfkrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.YFKFKRQ).getValue();
		String gcxz = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.GCXZ).getValue();
		String jdkfkrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.JDKFKRQ).getValue();
		String jsrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.JSRQ).getValue();
		String jzlrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.JZLRQ).getValue();
		String kfprq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.KFPRQ).getValue();
		String ysszje = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.YSSZJE).getValue();
		String yhysk = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.YHYSK).getValue();
		String yhczje = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.YHCZJE).getValue();
		String gsczje = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.GSCZJE).getValue();
		String yhjsk = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.YHJSK).getValue();
		String yfk = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.YFK).getValue();
		String jdk = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.JDK).getValue();
		String sgdwjsk = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.SGDWJSK).getValue();
		String jskfkrq = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.JSKFKRQ).getValue();

		//add by crf 2015-06-24表头信息
		/*是否有施工合同、是否需与用户结算、是否是包干价、是否已开发票*/
		String sfysght = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.SFYSGHT).getValue();
		String sfxyyhjs = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.SFXYYJJS).getValue();
		String sfsbgj = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.SFSBGJ).getValue();
		String sfykfp = getBillCardPanelWrapper().getBillCardPanel().getHeadItem(FbyjazVO.SFYKFP).getValue();
		
		for (int i = 0; i < rowcount; i++) {
			// 合同号
			Object hthb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.HTH);
			// 预付款日期
			Object yfkfkrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.YFKFKRQ);
			// 工程性质
			Object gcxzb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.GCXZ);
			// 进度款付款日期
			Object jdkfkrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.JDKFKRQ);
			// 结算日期
			Object jsrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.JSRQ);
			// 接资料日期
			Object jzlrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.JZLRQ);
			// 开发票日期
			Object kfprqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.KFPRQ);
			// 预算书总金额
			Object ysszjeb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.YSSZJE);

			Object yhyskb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.YHYSK);

			Object yhczjeb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.YHCZJE);

			Object gsczjeb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.GSCZJE);

			Object yhjskb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.YHJSK);

			Object yfkb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.YFK);

			Object jdkb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.JDK);

			Object sgdwjskb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.SGDWJSK);

			Object jskfkrqb = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.JSKFKRQ);

			// 表体主键
			Object id = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.PK_FBYJAZ_B);

			//modify by crf 2014-07-08
			// 合同号
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(hth, i, FbyjazBVO.HTH);
//			// 预付款日期
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yfkfkrq, i, FbyjazBVO.YFKFKRQ);
//			// 工程性质
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(gcxz, i, FbyjazBVO.GCXZ);
//			// 进度款付款日期
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jdkfkrq, i, FbyjazBVO.JDKFKRQ);
//			// 结算日期
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jsrq, i, FbyjazBVO.JSRQ);
//			// 接资料日期
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jzlrq, i, FbyjazBVO.JZLRQ);
//			// 开发票日期
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kfprq, i, FbyjazBVO.KFPRQ);
//			// 预算书总金额
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(ysszje, i, FbyjazBVO.YSSZJE);
//			// 用户预算款
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yhysk, i, FbyjazBVO.YHYSK);
//
//			// 用户出资金额
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yhczje, i, FbyjazBVO.YHCZJE);
//			// 公司出资金额
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(gsczje, i, FbyjazBVO.GSCZJE);
//			// 用户结算款
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yhjsk, i, FbyjazBVO.YHJSK);
//			// 预付款
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yfk, i, FbyjazBVO.YFK);
//			// 进度款
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jdk, i, FbyjazBVO.JDK);
//			// 施工单位结算款
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sgdwjsk, i, FbyjazBVO.SGDWJSK);
//			// 结算款付款日期
//			getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jskfkrq, i, FbyjazBVO.JSKFKRQ);
			//end by crf 2015-07-08
//			// 合同号
			if (hthb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(hth, i, FbyjazBVO.HTH);
			}

			// 预付款日期
			if (yfkfkrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yfkfkrq, i, FbyjazBVO.YFKFKRQ);
			}

			// 工程性质
			if (gcxzb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(gcxz, i, FbyjazBVO.GCXZ);
			}

			// 进度款付款日期
			if (jdkfkrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jdkfkrq, i, FbyjazBVO.JDKFKRQ);
			}

			// 结算日期
			if (jsrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jsrq, i, FbyjazBVO.JSRQ);
			}

			// 接资料日期
			if (jzlrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jzlrq, i, FbyjazBVO.JZLRQ);
			}

			// 开发票日期
			if (kfprqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(kfprq, i, FbyjazBVO.KFPRQ);
			}

			// 预算书总金额
			if (ysszjeb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(ysszje, i, FbyjazBVO.YSSZJE);
			}

			// 用户预算款
			if (yhyskb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yhysk, i, FbyjazBVO.YHYSK);
			}

			// 用户出资金额
			if (yhczjeb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yhczje, i, FbyjazBVO.YHCZJE);
			}

			// 公司出资金额
			if (gsczjeb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(gsczje, i, FbyjazBVO.GSCZJE);
			}

			// 用户结算款
			if (yhjskb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yhjsk, i, FbyjazBVO.YHJSK);
			}

			// 预付款
			if (yfkb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(yfk, i, FbyjazBVO.YFK);
			}

			// 进度款
			if (jdkb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jdk, i, FbyjazBVO.JDK);
			}

			// 施工单位结算款
			if (sgdwjskb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sgdwjsk, i, FbyjazBVO.SGDWJSK);
			}

			// 结算款付款日期
			if (jskfkrqb == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(jskfkrq, i, FbyjazBVO.JSKFKRQ);
			}

			// add by crf 2015-06-24 表体
			/*是否有施工合同、是否需与用户结算、是否是包干价、是否已开发票*/
			Object sfysght_item = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.SFYSGHT);
			Object sfxyyhjs_item = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.SFXYYJJS);
			Object sfsbgj_item = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.SFSBGJ);
			Object sfykfp_item = getBillCardPanelWrapper().getBillCardPanel().getBodyValueAt(i, FbyjazBVO.SFYKFP);

			if (sfysght_item == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sfysght, i, FbyjazBVO.SFYSGHT);
			}
			if (sfxyyhjs_item == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sfxyyhjs, i, FbyjazBVO.SFXYYJJS);
			}
			if (sfsbgj_item == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sfsbgj, i, FbyjazBVO.SFSBGJ);
			}
			if (sfykfp_item == null) {
				getBillCardPanelWrapper().getBillCardPanel().setBodyValueAt(sfykfp, i, FbyjazBVO.SFYKFP);
			}
			//--------------end---------------------
			
		}
	}

}
