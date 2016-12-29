package nc.ui.lcsw.sw07;

import nc.ui.lcsw.sw10.SW10ManageEventHandler;
import nc.ui.pub.ClientEnvironment;
import nc.ui.trade.base.IBillOperate;
import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.vo.lcsw.sw07.AzxxfkHVO;
import nc.vo.pub.lang.UFDate;

/**
 * <b>安装反馈数据事件类</b>
 * @version 1.0
 * @since 1.0
 * @author 梁展轩
 * @time 2014-09-03 17:20:11
 */
public class ClientEventHandler extends SW10ManageEventHandler  {

	private ClientUI clientUI; 
	public ClientEventHandler(BillManageUI billUI,IControllerBase control) {
		super(billUI, control);
		this.clientUI = (ClientUI) billUI;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onBoSave() throws Exception {
		clientUI.getBillCardPanel().dataNotNullValidate();
		
		UFDate today = ClientEnvironment.getInstance().getBusinessDate();
		String kgrq=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.KGRQ).getValue();
		String yjazrq=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.YJAZRQ).getValue();
		String azrq=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.AZRQ).getValue();
		String jgrq=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.JGRQ).getValue();
		String azfkrq=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.AZFKRQ).getValue();
		String zbrq=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.ZBRQ).getValue();
		
		//装表日期<=当天；
		if(zbrq!=null && zbrq.length()>0){
			if(today.before(new UFDate(zbrq))){
					getBillUI().showErrorMessage("[装表日期]不能大于[当前日期]！");
					return;
			}
		}
		
		//[安装日期]<=装表日期；
		if(zbrq!=null && zbrq.length()>0){
			if(azrq!=null && azrq.length() > 0){
				if(new UFDate(zbrq).before(new UFDate(zbrq))){
					getBillUI().showErrorMessage("[安装日期]不能大于[装表日期]！");
					return;
				}
			}
		}
		
		//日期校验[开工日期]<=当天；
		if(kgrq!=null && kgrq.length()>0){
			if(today.before(new UFDate(kgrq))){
//			getBillUI().showErrorMessage("[开工日期]不能大于[当前日期]！");
				getBillUI().showErrorMessage("[当前日期]不能小于[开工日期]！");
				return;
			}
			
		}
		//[移交安装日期]<=[开工日期]
		if(yjazrq!=null && yjazrq.length()>0){
			if(kgrq!=null && kgrq.length()>0){
				if(new UFDate(kgrq).before(new UFDate(yjazrq))){
//				getBillUI().showErrorMessage("[移交安装日期]不能大于[开工日期]！");
					getBillUI().showErrorMessage("[开工日期]不能小于[移交安装日期]！");
					return;
				}
			}
		}
		//[安装日期]<=当天；
		if(azrq!=null && azrq.length()>0){
			if(today.before(new UFDate(azrq))){
				getBillUI().showErrorMessage("[安装日期]不能大于[当前日期]！");
//				getBillUI().showErrorMessage("[当前日期]不能小于[安装日期]！");
				return;
			}
		}
		//[开工日期]<=[安装日期]
		if(azrq!=null && azrq.length()>0){
			if(kgrq!=null && kgrq.length()>0){
				if(new UFDate(azrq).before(new UFDate(kgrq))){
//			getBillUI().showErrorMessage("[开工日期]不能大于[安装日期]！");
					getBillUI().showErrorMessage("[安装日期]不能小于[开工日期]！");
					return;
				}
			}
		}
		//[竣工日期]<=当天；
		
		if(jgrq != null && !jgrq.equals("") && today.before(new UFDate(jgrq))){
			getBillUI().showErrorMessage("[竣工日期]不能大于[当前日期]！");
//			getBillUI().showErrorMessage("[当前日期]不能小于[竣工日期]！");
			return;
		}
		//[安装日期]<=[竣工日期]
		if(jgrq != null && !jgrq.equals("") &&new UFDate(jgrq).before(new UFDate(azrq))){
//			getBillUI().showErrorMessage("[安装日期]不能大于[竣工日期]！");
			getBillUI().showErrorMessage("[竣工日期]不能大于[安装日期]！");
			return;
		}
		//[安装反馈日期]<=当天
		if(azfkrq!=null && azfkrq.length()>0){
			if(today.before(new UFDate(azfkrq))){
				getBillUI().showErrorMessage("[安装反馈日期]不能大于[当前日期]！");
//				getBillUI().showErrorMessage("[当前日期]不能小于[安装反馈日期]！");
				return;
			}
		}
		//[竣工日期]<=[安装反馈日期]。
		if(jgrq != null && !jgrq.equals("") && new UFDate(azfkrq).before(new UFDate(jgrq))){
//			getBillUI().showErrorMessage("[竣工日期]不能大于[安装反馈日期]！");
			getBillUI().showErrorMessage("[安装反馈日期]不能小于[竣工日期]！");
			return;
		}

		
		assignBodyByHead();
		super.onBoSave();
	}
	
	@Override
	protected void onBoQuery() throws Exception {
		super.onBoQuery();
	}
	
	@Override
	protected void onBoEdit() throws Exception {
		super.onBoEdit();
		if (getBillUI().getBillOperate() == IBillOperate.OP_EDIT){
			clientUI.getBillCardPanel().getTailItem(AzxxfkHVO.MODIFIER).setValue(ClientEnvironment.getInstance().getUser().getPrimaryKey());
			clientUI.getBillCardPanel().getTailItem(AzxxfkHVO.DMODIFY).setValue(ClientEnvironment.getInstance().getDate());
		}
	}
	
	/**
	 * 根据表头字段  如空则给表体同名字段赋值 
	 */
	@SuppressWarnings("deprecation")
	private void assignBodyByHead() {
		//项目
		String pk_jobbasfil=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.PK_JOBBASFIL).getValue();
		if(pk_jobbasfil!=null && pk_jobbasfil.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.PK_JOBBASFIL)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(pk_jobbasfil, i, AzxxfkHVO.PK_JOBBASFIL);
				}
			}
		}
		
		//安装反馈日期
		String azfkrq=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.AZFKRQ).getValue();
		if(azfkrq!=null && azfkrq.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.AZFKRQ)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(azfkrq, i, AzxxfkHVO.AZFKRQ);
				}
			}
		}
		//安装日期
		String azrq=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.AZRQ).getValue();
		if(azrq!=null && azrq.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.AZRQ)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(azrq, i, AzxxfkHVO.AZRQ);
				}
			}
		}
		//合同号
		String hth=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.HTH).getValue();
		if(hth!=null && hth.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.HTH)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(hth, i, AzxxfkHVO.HTH);
				}
			}
		}
		//竣工日期
		String jgrq=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.JGRQ).getValue();
		if(jgrq!=null && jgrq.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.JGRQ)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(jgrq, i, AzxxfkHVO.JGRQ);
				}
			}
		}
		//备注
		String remark=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.REMARK).getValue();
		if(remark!=null && remark.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.REMARK)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(remark, i, AzxxfkHVO.REMARK);
				}
			}
		}
		//完工延期天数
		String wgyqts=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.WGYQTS).getValue();
		if(wgyqts!=null && wgyqts.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.WGYQTS)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(wgyqts, i, AzxxfkHVO.WGYQTS);
				}
			}
		}
		//移交安装日期
		String yjazrq=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.YJAZRQ).getValue();
		if(yjazrq!=null && yjazrq.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.YJAZRQ)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(yjazrq, i, AzxxfkHVO.YJAZRQ);
				}
			}
		}

		//延期修改人
		String yqxgr=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.YQXGR).getValue();
		if(yqxgr!=null && yqxgr.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.YQXGR)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(yqxgr, i, AzxxfkHVO.YQXGR);
				}
			}
		}
		//延期修改日期
		String yqxgrq=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.YQXGRQ).getValue();
		if(yqxgrq!=null && yqxgrq.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.YQXGRQ)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(yqxgrq, i, AzxxfkHVO.YQXGRQ);
				}
			}
		}
		
		//验收人员
		String ysry=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.YSRY).getValue();
		if(ysry!=null && ysry.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.YSRY)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(ysry, i, AzxxfkHVO.YSRY);
				}
			}
		}
		//验收电话
		String ysdh=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.YSDH).getValue();
		if(ysdh!=null && ysdh.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.YSDH)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(ysdh, i, AzxxfkHVO.YSDH);
				}
			}
		}
		
		//资料反馈日期
		String zlfkrq=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.ZLFKRQ).getValue();
		if(zlfkrq!=null && zlfkrq.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.ZLFKRQ)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(zlfkrq, i, AzxxfkHVO.ZLFKRQ);
				}
			}
		}
		
		//开工日期
		String kgrq=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.KGRQ).getValue();
		if(kgrq!=null && kgrq.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.KGRQ)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(kgrq, i, AzxxfkHVO.KGRQ);
				}
			}
		}
		
		//装表日期
		String zbrq=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.ZBRQ).getValue();
		if(zbrq!=null && zbrq.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.ZBRQ)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(zbrq, i, AzxxfkHVO.ZBRQ);
				}
			}
		}
		
		//表位
		String biaowei=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.BIAOWEI).getValue();
		if(biaowei!=null && biaowei.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.BIAOWEI)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(biaowei, i, AzxxfkHVO.BIAOWEI);
				}
			}
		}
		
		//安装表径
		String azbj=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.AZBJ).getValue();
		if(azbj!=null && azbj.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.AZBJ)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(azbj, i, AzxxfkHVO.AZBJ);
				}
			}
		}
		
		//水表类型
		String biaobie=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.BIAOBIE).getValue();
		if(biaobie!=null && biaobie.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.BIAOBIE)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(biaobie, i, AzxxfkHVO.BIAOBIE);
				}
			}
		}
		
		//产地
		String chandi=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.CHANDI).getValue();
		if(chandi!=null && chandi.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.CHANDI)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(chandi, i, AzxxfkHVO.CHANDI);
				}
			}
		}
		
		//出库行度
		String ckxd=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.CKXD).getValue();
		if(ckxd!=null && ckxd.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.CKXD)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(ckxd, i, AzxxfkHVO.CKXD);
				}
			}
		}
		
		//表码
		String biaoma=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.BIAOMA).getValue();
		if(biaoma!=null && biaoma.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.BIAOMA)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(biaoma, i, AzxxfkHVO.BIAOMA);
				}
			}
		}
		
		//编码
		String bianma=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.BIANMA).getValue();
		if(bianma!=null && bianma.length() > 0)
		{
			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
			for (int i = 0; i < row; i++) {
				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.BIANMA)==null){
					this.clientUI.getBillCardPanel().setBodyValueAt(bianma, i, AzxxfkHVO.BIANMA);
				}
			}
		}
		
//		//施工部门
//		String sgbm=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.SGBM).getValue();
//		if(sgbm!=null && sgbm.length() > 0)
//		{
//			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
//			for (int i = 0; i < row; i++) {
//				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.SGBM)==null){
//					this.clientUI.getBillCardPanel().setBodyValueAt(sgbm, i, AzxxfkHVO.SGBM);
//				}
//			}
//		}
		
//		//施工负责人
//		String sgfzr=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.SGFZR).getValue();
//		if(sgfzr!=null && sgfzr.length() > 0)
//		{
//			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
//			for (int i = 0; i < row; i++) {
//				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.SGFZR)==null){
//					this.clientUI.getBillCardPanel().setBodyValueAt(sgfzr, i, AzxxfkHVO.SGFZR);
//				}
//			}
//		}
//		
//		//施工联系电话
//		String sglxdh=this.clientUI.getBillCardPanel().getHeadItem(AzxxfkHVO.SGLXDH).getValue();
//		if(sglxdh!=null && sglxdh.length() > 0)
//		{
//			int row=this.clientUI.getBillCardPanel().getBillModel().getRowCount();
//			for (int i = 0; i < row; i++) {
//				if(this.clientUI.getBillCardPanel().getBodyValueAt(i,AzxxfkHVO.SGLXDH)==null){
//					this.clientUI.getBillCardPanel().setBodyValueAt(sglxdh, i, AzxxfkHVO.SGLXDH);
//				}
//			}
//		}
		
	}
	
	
}
