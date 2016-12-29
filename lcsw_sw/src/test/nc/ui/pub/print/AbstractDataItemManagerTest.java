package nc.ui.pub.print;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;

import com.ufida.eg.common.forprinttemp.PrintDataItemVO;
import com.ufida.eg.common.service.IEgCommonService;

/**
 * 像数据库中插入PrintDataItemVO数据的testcase<br>
 * 此类为抽象类，与DataItemManagerTest作用一致，可以通过继承此类实现个字节点的打印测试类
 * 
 * @author lidch
 * 
 */
abstract public class AbstractDataItemManagerTest extends AbstractTestCase {

	public List<PrintDataItemVO> batchInsert() {
		List<PrintDataItemVO> list = new ArrayList<PrintDataItemVO>();
		IDataSource rs = null;
		// 这里请实例化自己的数据源。例子如下：
		rs = this.getDataSource();
		for (int i = 0; i < rs.getAllDataItemExpress().length; i++) {
			// 传入对应功能的funcnode
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
	 * 获取数据源实例
	 * 
	 * @return
	 * @author liurf created/modified at 2010-11-10上午11:14:51
	 */
	public abstract IDataSource getDataSource();

	/**
	 * 获取节点编码
	 * 
	 * @return
	 * @author liurf created/modified at 2010-11-10上午11:14:23
	 */
	public abstract String getFuncCode();

	public void test() {
		IEgCommonService printInsService = NCLocator.getInstance().lookup(
				IEgCommonService.class);
		List<PrintDataItemVO> list = this.batchInsert();
		// 调用removeAllPrintDataItemByFuncNode方法，传入对应功能的funcnode
		printInsService.removeAllPrintDataItemByFuncNode(this.getFuncCode());
		printInsService.insertBatchPrintDataItem(list
				.toArray(new PrintDataItemVO[0]));
	}
}
