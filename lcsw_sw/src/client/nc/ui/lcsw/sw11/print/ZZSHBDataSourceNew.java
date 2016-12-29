package nc.ui.lcsw.sw11.print;

import nc.bs.framework.common.NCLocator;
import nc.ui.pub.print.IDataSource;
import nc.vo.lcsw.sw11.ZZSHBPrintVO;
import nc.vo.lcsw.sw11.itf.ZZSHBPrintService;


public class ZZSHBDataSourceNew implements IDataSource{

	private static final long serialVersionUID = 1L;

	private String pk_bzsq;
	private ZZSHBPrintVO vo = null;

	public ZZSHBDataSourceNew() {
	}

	public ZZSHBDataSourceNew(String pk_bzsq) throws Exception {
		this.pk_bzsq = pk_bzsq;
		vo =(ZZSHBPrintVO) NCLocator.getInstance().lookup(ZZSHBPrintService.class).queryZZSHBPrint(pk_bzsq);
		
	}

	public String[] getItemValuesByExpress(String itemExpress) {
		if (vo == null) {
			return new String[] {};
		}
		if("pk_bzsq".equals(itemExpress)) {
			return new String[]{pk_bzsq};
		}
		return new String[] { (String) vo.getAttributeValue(itemExpress).toString() };
	}

	public boolean isNumber(String itemExpress) {
		// TODO Auto-generated method stub
		return false;
	}

	public String[] getDependentItemExpressByExpress(String itemExpress) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getAllDataItemExpress() {
		return new String[] { "gcmc","jobcode","lxdh","lxr","intoPipe","intoWatch"
				,"kcsjf","jbr","kcry","czway","ysxz","sblx","sbyt","pk_bzsq" };
	}

	public String[] getAllDataItemNames() {
		return new String[] { "工程名称","项目编码","联系电话","联系人","进户管径","进户表径"
				,"勘测设计费","经办人","勘察人员","出资方式","用水性质","水表类型","水表用途" ,"报装申请主键"};
	}

	public String getModuleName() {
		// TODO Auto-generated method stub
		return null;
	}

}
