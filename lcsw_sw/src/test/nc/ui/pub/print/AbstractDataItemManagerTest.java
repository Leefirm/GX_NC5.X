package nc.ui.pub.print;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;

import com.ufida.eg.common.forprinttemp.PrintDataItemVO;
import com.ufida.eg.common.service.IEgCommonService;

/**
 * �����ݿ��в���PrintDataItemVO���ݵ�testcase<br>
 * ����Ϊ�����࣬��DataItemManagerTest����һ�£�����ͨ���̳д���ʵ�ָ��ֽڵ�Ĵ�ӡ������
 * 
 * @author lidch
 * 
 */
abstract public class AbstractDataItemManagerTest extends AbstractTestCase {

	public List<PrintDataItemVO> batchInsert() {
		List<PrintDataItemVO> list = new ArrayList<PrintDataItemVO>();
		IDataSource rs = null;
		// ������ʵ�����Լ�������Դ���������£�
		rs = this.getDataSource();
		for (int i = 0; i < rs.getAllDataItemExpress().length; i++) {
			// �����Ӧ���ܵ�funcnode
			list.add(this.generatePrintDataItemVO(this.getFuncCode(), rs
					.getAllDataItemExpress()[i], rs.getAllDataItemNames()[i]));
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

	/**
	 * ��ȡ����Դʵ��
	 * 
	 * @return
	 * @author liurf created/modified at 2010-11-10����11:14:51
	 */
	public abstract IDataSource getDataSource();

	/**
	 * ��ȡ�ڵ����
	 * 
	 * @return
	 * @author liurf created/modified at 2010-11-10����11:14:23
	 */
	public abstract String getFuncCode();

	public void test() {
		IEgCommonService printInsService = NCLocator.getInstance().lookup(
				IEgCommonService.class);
		List<PrintDataItemVO> list = this.batchInsert();
		// ����removeAllPrintDataItemByFuncNode�����������Ӧ���ܵ�funcnode
		printInsService.removeAllPrintDataItemByFuncNode(this.getFuncCode());
		printInsService.insertBatchPrintDataItem(list
				.toArray(new PrintDataItemVO[0]));
	}
}
