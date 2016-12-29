package nc.vo.lcsw.sw11;

import java.util.Arrays;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.trade.pub.HYBillVO;

@SuppressWarnings("serial")
public class SjfksjAggVO extends HYBillVO {
	@Override
	public CircularlyAccessibleValueObject[] getChildrenVO() {
		return (SjfksjBVO[]) super.getChildrenVO();
	}

	@Override
	public CircularlyAccessibleValueObject getParentVO() {
		// TODO Auto-generated method stub
		return (SjfksjHVO) super.getParentVO();
	}

	@Override
	public void setChildrenVO(CircularlyAccessibleValueObject[] children) {
		if (children == null || children.length == 0) {
			super.setChildrenVO(null);
		} else {
			super.setChildrenVO((CircularlyAccessibleValueObject[]) Arrays.asList(children).toArray(new SjfksjBVO[0]));
		}
	}

	@Override
	public void setParentVO(CircularlyAccessibleValueObject parent) {
		super.setParentVO((SjfksjHVO) parent);
	}

}
