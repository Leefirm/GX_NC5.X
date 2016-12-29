package nc.vo.lcsw.sw10;

import java.util.Arrays;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.trade.pub.HYBillVO;

/**
 * 
 * 单子表/单表头/单表体聚合VO
 *
 * 创建日期:2014-08-12 16:25:45
 * @author xuns
 * @version NCPrj 1.0
 */
@SuppressWarnings("serial")
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.lcsw.sw10.BzfyHvo")
public class  AggBzfyVO extends HYBillVO {
	@Override
	public BzfyBVO[] getChildrenVO() {
		// TODO Auto-generated method stub
		return (BzfyBVO[])super.getChildrenVO();
	}

	@Override
	public BzfyHVO getParentVO() {
		// TODO Auto-generated method stub
		return (BzfyHVO)super.getParentVO();
	}

	@Override
	public void setChildrenVO(CircularlyAccessibleValueObject[] children) {
		if( children == null || children.length == 0 ){
			super.setChildrenVO(null);
		}
		else{
			super.setChildrenVO((CircularlyAccessibleValueObject[]) Arrays.asList(children).toArray(new BzfyBVO[0]));
		}
	}

	@Override
	public void setParentVO(CircularlyAccessibleValueObject parent) {
		super.setParentVO((BzfyHVO)parent);
	}
}
