package nc.ui.lcsw.sw01.ref;

import nc.ui.bd.ref.AbstractRefModel;

public class BzxxRefModel  extends AbstractRefModel {

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
		return new String[] { "报装编号", "受理信息编号", "协议编号", "入户编号", "户名" };
	}

	@Override
	public String[] getHiddenFieldCode() {
		return new String[] { "pk_bzxx", "bzzt" };
	}

	@Override
	public String getPkFieldCode() {
		return "pk_bzxx";
	}

	@Override
	public String getRefTitle() {
		return "报装用户";
	}

	@Override
	public String getTableName() {
		return "lcsw_bzxx";
	}

	@Override
	public String buildBaseSql(String patch, String[] columns, String[] hiddenColumns, String tableName, String whereCondition) {
		// 添加查询条件：查询未删除的记录
		return super.buildBaseSql(patch, columns, hiddenColumns, tableName, whereCondition) + " and nvl(dr, 0) = 0 " ;
	}

}
