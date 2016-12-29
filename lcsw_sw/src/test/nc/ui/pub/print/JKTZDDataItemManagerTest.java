package nc.ui.pub.print;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.ui.lcsw.sw10.print.JKTZSDataSource;

import com.ufida.eg.common.forprinttemp.PrintDataItemVO;
import com.ufida.eg.common.service.IEgCommonService;

public class JKTZDDataItemManagerTest {

	public List<PrintDataItemVO> batchInsert() {
		List<PrintDataItemVO> list = new ArrayList<PrintDataItemVO>();
		IDataSource rs = null;
		// ������ʵ�����Լ�������Դ���������£�
		rs = this.getDataSource();
		for (int i = 0; i < rs.getAllDataItemExpress().length; i++) {
			// �����Ӧ���ܵ�funcnode
			list.add(this.generatePrintDataItemVO(this.getFuncCode(),
					rs.getAllDataItemExpress()[i], rs.getAllDataItemNames()[i]));
		}

		return list;
	}

	public PrintDataItemVO generatePrintDataItemVO(String vnodecode,
			String varexpress, String varname) {
		PrintDataItemVO vo = new PrintDataItemVO();
		vo.setPk_corp("@@@@");
		vo.setVnodecode(vnodecode);
		vo.setVvarexpress(varexpress);
		vo.setVvarname(varname);
		return vo;
	}

	public static void main(String[] args) {

		EnvInit.initClientEnv();

		JKTZDDataItemManagerTest test = new JKTZDDataItemManagerTest();
		test.test();
	}

	public void test() {
		IEgCommonService printInsService = NCLocator.getInstance().lookup(
				IEgCommonService.class);
		List<PrintDataItemVO> list = this.batchInsert();
		// ����removeAllPrintDataItemByFuncNode�����������Ӧ���ܵ�funcnode
		// printInsService.removeAllPrintDataItemByFuncNode(getFuncCode());
		printInsService.insertBatchPrintDataItem(list
				.toArray(new PrintDataItemVO[0]));
	}

	public IDataSource getDataSource() {
		return new JKTZSDataSource();
	}

	public String getFuncCode() {
		// TODO Auto-generated method stub
		return "H6H301";
	}

}
