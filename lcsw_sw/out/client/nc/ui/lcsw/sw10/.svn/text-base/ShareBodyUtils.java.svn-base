package nc.ui.lcsw.sw10;

import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.trade.business.HYPubBO_Client;
import nc.uif.pub.exception.UifException;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.lcsw.sw10.BzfyBVO;
import nc.vo.lcsw.sw10.BzfyHVO;
import nc.vo.pub.lang.UFDouble;

public class ShareBodyUtils {

	public static void ShareBody(String pkcostsubj,String fy,BillCardPanel billCardPanel) {
		ShareBody(pkcostsubj, fy, billCardPanel, null, null, null,null);
	}
	
	/**
	 * 根据费用分摊到表体
	 * @param kcsjf
	 * pkcostsubj  项目
	 * fy		   费用字段
	 * billCardPanel  面板
	 * jbrq			经办日期
	 * jbry			经办人员
	 * skfs			收款方式
	 */
	public static void ShareBody(String pkcostsubj,String fy,BillCardPanel billCardPanel,String jbrq,String jbry,String skfs,UFDouble je) {
		String pk_bzsq = billCardPanel.getHeadItem("pk_bzsq").getValue();
		int rowcount = billCardPanel.getRowCount();
		try {
			//获取报装信息
			LcswBzxxHVO[] xxvos = (LcswBzxxHVO[])HYPubBO_Client.queryByCondition(LcswBzxxHVO.class, "pk_bzsq = '"+pk_bzsq+"'");
			if(xxvos == null || xxvos.length == 0)
				return;
			//金额平均
			UFDouble value = UFDouble.ZERO_DBL;
			if(fy != null)
				value = new UFDouble(billCardPanel.getHeadItem(fy).getValue());
			else
				value = je;
			
			Double yushu = value.getDouble()%xxvos.length;
			Double pingjun = (value.getDouble()-yushu)/xxvos.length;
			int index = 1;
			//查询该费用主键是否存在表体
			boolean ishas = true;
			for (int i = 0; i < rowcount; i++) {
				String pk_costsubj = billCardPanel.getBodyValueAt(i, BzfyBVO.PK_COSTSUBJ).toString();
				String pk_bzxx = billCardPanel.getBodyValueAt(i, BzfyBVO.PK_BZXX).toString();
				for (LcswBzxxHVO xxvo : xxvos) {
					if(pk_costsubj.equals(pkcostsubj) && pk_bzxx.equals(xxvo.getPk_bzxx())){
						if(index == xxvos.length)
							billCardPanel.setBodyValueAt(pingjun+yushu, i, BzfyBVO.AMOUNT);
						else
							billCardPanel.setBodyValueAt(pingjun, i, BzfyBVO.AMOUNT);
						
						if(jbrq != null)
							billCardPanel.setBodyValueAt(jbrq, i, BzfyBVO.TFRQ);
						if(jbry != null)
							billCardPanel.setBodyValueAt(jbry, i, BzfyBVO.SFRY);
						if(skfs != null)
							billCardPanel.setBodyValueAt(skfs, i, BzfyBVO.PK_BALATYPE);
						ishas = false;
						index ++;
						billCardPanel.getBillModel().execLoadFormula();
					}					
				}
			}
			if(ishas){
				for (LcswBzxxHVO bzxxvo : xxvos) {
					billCardPanel.getBillModel().addLine();
					int rowindex = billCardPanel.getRowCount()-1;
					billCardPanel.setBodyValueAt(bzxxvo.getPk_bzxx(), rowindex, BzfyBVO.PK_BZXX);
					billCardPanel.setBodyValueAt(pkcostsubj, rowindex, BzfyBVO.PK_COSTSUBJ);
					//经办日期
					if(jbrq == null)
						billCardPanel.setBodyValueAt(ClientEnvironment.getInstance().getDate(), rowindex, BzfyBVO.TFRQ);
					else
						billCardPanel.setBodyValueAt(jbrq, rowindex, BzfyBVO.TFRQ);
					//收款方式
					if(skfs == null)
						billCardPanel.setBodyValueAt(billCardPanel.getHeadItem(BzfyHVO.FKFS).getValue(), rowindex, BzfyBVO.PK_BALATYPE);
					else
						billCardPanel.setBodyValueAt(skfs, rowindex, BzfyBVO.PK_BALATYPE);
					//经办人员
					if(jbry != null)
						billCardPanel.setBodyValueAt(jbry, rowindex, BzfyBVO.SFRY);
					
					if(index == xxvos.length)
						billCardPanel.setBodyValueAt(pingjun+yushu, rowindex, BzfyBVO.AMOUNT);
					else
						billCardPanel.setBodyValueAt(pingjun, rowindex, BzfyBVO.AMOUNT);
					index ++;
					billCardPanel.getBillModel().execLoadFormula();
				}
			}
			
			billCardPanel.getBillModel().loadLoadRelationItemValue();
		} catch (UifException e) {
			e.printStackTrace();
		}
		
	}
}
