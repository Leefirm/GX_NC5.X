package nc.vo.lcsw.sw07;

import java.util.Arrays;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.trade.pub.HYBillVO;

@SuppressWarnings("serial")
public class AzxxfkAggVO extends HYBillVO {

	@Override
	public CircularlyAccessibleValueObject getParentVO() {
		return (AzxxfkHVO)super.getParentVO();
	}
	
	@Override
	public CircularlyAccessibleValueObject[] getChildrenVO() {
		return (AzxxfkBVO[])super.getChildrenVO();
	}
	
	
	@Override
	public void setParentVO(CircularlyAccessibleValueObject parent) {
		super.setParentVO((AzxxfkHVO) parent);
		}
	
	@Override
	public void setChildrenVO(CircularlyAccessibleValueObject[] children) {
		if (children == null || children.length == 0) {
			super.setChildrenVO(null);
		} else {
			super.setChildrenVO((CircularlyAccessibleValueObject[]) Arrays.asList(children).toArray(new AzxxfkBVO[0]));
		}
	}
	
}
