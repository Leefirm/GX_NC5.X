package nc.ui.lcsw.sw02.ref;

import nc.ui.bd.ref.AbstractRefModel;

public class BzsqRefModel extends AbstractRefModel {

	@Override
	public int getDefaultFieldCount() {
		// 默认显示全部字段
		return getFieldCode().length;
	}

	@Override
	public String[] getFieldCode() {
		return new String[] { "bzbh", "slbh", "xybh", "rhbh", "hm" };
	}

	@Override
	public String[] getFieldName() {
		return new String[] { "报装编号", "受理编号", "协议编号", "入户编号", "户名" };
	}

	@Override
	public String[] getHiddenFieldCode() {
		return new String[] { "pk_bzsq", "bzzt" };
	}

	@Override
	public String getPkFieldCode() {
		return "pk_bzsq";
	}

	@Override
	public String getRefTitle() {
		return "报装申请";
	}

	@Override
	public String getTableName() {
		return "lcsw_bzsq";
	}

	@Override
	public String buildBaseSql(String patch, String[] columns, String[] hiddenColumns, String tableName, String whereCondition) {
		// 添加查询条件：查询未删除的记录
		return super.buildBaseSql(patch, columns, hiddenColumns, tableName, whereCondition) + " and nvl(dr, 0) = 0 and vbillstatus = 1" ;
	}

}
