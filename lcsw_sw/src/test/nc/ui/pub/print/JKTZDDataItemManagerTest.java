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
		// 这里请实例化自己的数据源。例子如下：
		rs = this.getDataSource();
		for (int i = 0; i < rs.getAllDataItemExpress().length; i++) {
			// 传入对应功能的funcnode
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
		// 调用removeAllPrintDataItemByFuncNode方法，传入对应功能的funcnode
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
