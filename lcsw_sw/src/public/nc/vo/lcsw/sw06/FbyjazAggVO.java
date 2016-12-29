package nc.vo.lcsw.sw06;

import java.util.Arrays;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.trade.pub.HYBillVO;

public class FbyjazAggVO extends HYBillVO {

	@Override
	public CircularlyAccessibleValueObject[] getChildrenVO() {
		// TODO Auto-generated method stub
		return (FbyjazBVO[]) super.getChildrenVO();
	}

	@Override
	public CircularlyAccessibleValueObject getParentVO() {
		// TODO Auto-generated method stub
		return (FbyjazVO) super.getParentVO();
	}

	@Override
	public void setChildrenVO(CircularlyAccessibleValueObject[] children) {
		if (children == null || children.length == 0) {
			super.setChildrenVO(null);
		} else {
			super.setChildrenVO((CircularlyAccessibleValueObject[]) Arrays.asList(children).toArray(new FbyjazBVO[0]));
		}
	}

	@Override
	public void setParentVO(CircularlyAccessibleValueObject parent) {
		super.setParentVO((FbyjazVO) parent);
	}

}
