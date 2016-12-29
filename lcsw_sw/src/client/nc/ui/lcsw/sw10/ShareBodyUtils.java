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
	 * ���ݷ��÷�̯������
	 * @param kcsjf
	 * pkcostsubj  ��Ŀ
	 * fy		   �����ֶ�
	 * billCardPanel  ���
	 * jbrq			��������
	 * jbry			������Ա
	 * skfs			�տʽ
	 */
	public static void ShareBody(String pkcostsubj,String fy,BillCardPanel billCardPanel,String jbrq,String jbry,String skfs,UFDouble je) {
		String pk_bzsq = billCardPanel.getHeadItem("pk_bzsq").getValue();
		int rowcount = billCardPanel.getRowCount();
		try {
			//��ȡ��װ��Ϣ
			LcswBzxxHVO[] xxvos = (LcswBzxxHVO[])HYPubBO_Client.queryByCondition(LcswBzxxHVO.class, "pk_bzsq = '"+pk_bzsq+"'");
			if(xxvos == null || xxvos.length == 0)
				return;
			//���ƽ��
			UFDouble value = UFDouble.ZERO_DBL;
			if(fy != null)
				value = new UFDouble(billCardPanel.getHeadItem(fy).getValue());
			else
				value = je;
			
			Double yushu = value.getDouble()%xxvos.length;
			Double pingjun = (value.getDouble()-yushu)/xxvos.length;
			int index = 1;
			//��ѯ�÷��������Ƿ���ڱ���
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
					//��������
					if(jbrq == null)
						billCardPanel.setBodyValueAt(ClientEnvironment.getInstance().getDate(), rowindex, BzfyBVO.TFRQ);
					else
						billCardPanel.setBodyValueAt(jbrq, rowindex, BzfyBVO.TFRQ);
					//�տʽ
					if(skfs == null)
						billCardPanel.setBodyValueAt(billCardPanel.getHeadItem(BzfyHVO.FKFS).getValue(), rowindex, BzfyBVO.PK_BALATYPE);
					else
						billCardPanel.setBodyValueAt(skfs, rowindex, BzfyBVO.PK_BALATYPE);
					//������Ա
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
