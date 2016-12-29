package nc.vo.lcsw.sw04;

import java.util.Arrays;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.trade.pub.HYBillVO;

public class KcfksjAggVO extends HYBillVO {

	@Override
	public CircularlyAccessibleValueObject[] getChildrenVO() {
		// TODO Auto-generated method stub
		return (KcfksjBVO[]) super.getChildrenVO();
	}

	@Override
	public CircularlyAccessibleValueObject getParentVO() {
		// TODO Auto-generated method stub
		return (KcfksjHVO) super.getParentVO();
	}

	@Override
	public void setChildrenVO(CircularlyAccessibleValueObject[] children) {
		if (children == null || children.length == 0) {
			super.setChildrenVO(null);
		} else {
			super.setChildrenVO((CircularlyAccessibleValueObject[]) Arrays.asList(children).toArray(new KcfksjBVO[0]));
		}
	}

	@Override
	public void setParentVO(CircularlyAccessibleValueObject parent) {
		super.setParentVO((KcfksjHVO) parent);
	}

}
