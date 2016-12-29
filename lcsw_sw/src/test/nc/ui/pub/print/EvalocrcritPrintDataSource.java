/**
 * 文件创建于2010-11-10 下午04:55:04
 */
package nc.ui.pub.print;

import java.util.Arrays;
import java.util.List;

import nc.ui.pub.print.IDataSource;
import nc.ui.uif2.model.HierachicalDataAppModel;
import nc.vo.pub.CircularlyAccessibleValueObject;

import com.ufida.eg.common.helper.ArrayClassConvertUtil;
import com.ufida.eg.common.ui.batchTable.BatchBillTableModel;
import com.ufida.eg.riskmg.ui.evalocrcrit.panel.evalstandard.EvalStandardAppModel;
import com.ufida.eg.riskmg.vo.evalocrcrit.EvalocrcritVO;
import com.ufida.eg.riskmg.vo.evalspec.AggEvalspecVO;
import com.ufida.eg.riskmg.vo.evalspec.EvalatchVO;
import com.ufida.eg.riskmg.vo.evalspec.EvalspecVO;
import com.ufida.eg.riskmg.vo.riskidcyc.RkidcycClsVO;

/**
 * 风险发生可能性评估标准的打印数据源
 * 
 * @author liurf created at 2010-11-10 下午04:55:04
 * 
 */
public class EvalocrcritPrintDataSource implements IDataSource {

	/**
	 * 
	 * @author liurf added at 2010-11-10下午04:55:31
	 */
	private static final long serialVersionUID = 6841808947622740476L;

	/**
	 * 周期信息
	 * 
	 * @author liurf added at 2010-11-11上午10:57:15
	 */
	private final RkidcycClsVO rkidcycClsVO;

	/**
	 * 周期树模型，如果rkidcycVO没有信息则从此模型中获取当前选择的数据为准
	 * 
	 * @author liurf added at 2010-11-11下午01:48:33
	 */
	private HierachicalDataAppModel cycTreeModel;

	/**
	 * 指标信息
	 * 
	 * @author liurf added at 2010-11-11上午10:56:56
	 */
	private final EvalocrcritVO[] evalocrcritVOs;

	/**
	 * 指标的数据模型，如果evalocrcritVOs没有数据，则会从模型中数据
	 * 
	 * @author liurf added at 2010-11-11下午01:49:57
	 */
	private BatchBillTableModel indexModel;

	/**
	 * 标准说明信息
	 * 
	 * @author liurf added at 2010-11-11上午10:57:46
	 */
	private final AggEvalspecVO aggEvalspecVO;

	/**
	 * 标准说明的数据模型，如果aggEvalspecVOs中没有数据则从此处获取数据
	 * 
	 * @author liurf added at 2010-11-11下午01:51:22
	 */
	private EvalStandardAppModel standardAppModel;

	/**
	 * 
	 */
	public EvalocrcritPrintDataSource() {
		this(null, null, null);
	}

	/**
	 * @param rkidcycVO
	 * @param evalocrcritVOs
	 * @param aggEvalspecVOs
	 */
	public EvalocrcritPrintDataSource(RkidcycClsVO rkidcycClsVO,
			EvalocrcritVO[] evalocrcritVOs, AggEvalspecVO aggEvalspecVO) {
		super();
		this.rkidcycClsVO = rkidcycClsVO;
		this.evalocrcritVOs = evalocrcritVOs;
		this.aggEvalspecVO = aggEvalspecVO;
	}

	/**
	 * 获取aggEvalspecVO
	 * 
	 * @see #aggEvalspecVO
	 * @return the aggEvalspecVO
	 */
	public AggEvalspecVO getAggEvalspecVO() {
		if (this.aggEvalspecVO == null) {
			return (AggEvalspecVO) this.getStandardAppModel().getSelectedData();
		} else {
			return this.aggEvalspecVO;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.ui.pub.print.IDataSource#getAllDataItemExpress()
	 */
	public String[] getAllDataItemExpress() {
		return new String[] { EvalocrcritVO.PK_RKIDCYC,
				EvalocrcritVO.PK_EVALOCRCRIT, EvalocrcritVO.AFLEVEL,
				EvalocrcritVO.SCORE, EvalocrcritVO.QUALIT_DISP,
				EvalocrcritVO.RATION_DISP, EvalspecVO.PK_EVALSPEC,
				EvalspecVO.CONTENT, EvalatchVO.FILECODE, EvalatchVO.FILENAME,
				EvalatchVO.DOCFILE };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.ui.pub.print.IDataSource#getAllDataItemNames()
	 */
	public String[] getAllDataItemNames() {
		return new String[] { "周期标识", "发生可能性评估指标标识", "影响等级", "分值", "定性标准",
				"定量标准", "标准说明标识", "标准说明信息", "附件文件编码", "附件文件名称", "附件列表" };
	}

	/**
	 * 获取cycTreeModel
	 * 
	 * @see #cycTreeModel
	 * @return the cycTreeModel
	 */
	public HierachicalDataAppModel getCycTreeModel() {
		return this.cycTreeModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nc.ui.pub.print.IDataSource#getDependentItemExpressByExpress(java.lang
	 * .String)
	 */
	public String[] getDependentItemExpressByExpress(String itemExpress) {
		return null;
	}

	/**
	 * 获取evalocrcritVOs
	 * 
	 * @see #evalocrcritVOs
	 * @return the evalocrcritVOs
	 */
	public EvalocrcritVO[] getEvalocrcritVOs() {
		if (this.evalocrcritVOs == null) {
			List<Object> rows = this.getIndexModel().getRows();
			return ArrayClassConvertUtil.convert(rows.toArray(new Object[rows
					.size()]), EvalocrcritVO.class);
		} else {
			return this.evalocrcritVOs;
		}
	}

	/**
	 * 获取indexModel
	 * 
	 * @see #indexModel
	 * @return the indexModel
	 */
	public BatchBillTableModel getIndexModel() {
		return this.indexModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.ui.pub.print.IDataSource#getItemValuesByExpress(java.lang.String)
	 */
	public String[] getItemValuesByExpress(String itemExpress) {
		try {
			// 对于指标信息，从指标数组中获取
			if (Arrays.asList(EvalocrcritVO.AFLEVEL, EvalocrcritVO.SCORE,
					EvalocrcritVO.QUALIT_DISP, EvalocrcritVO.RATION_DISP)
					.contains(itemExpress)) {
				EvalocrcritVO[] vos = this.getEvalocrcritVOs();
				if (vos == null || vos.length == 0) {
					return new String[0];
				} else {
					String[] values = new String[vos.length];
					for (int i = 0; i < vos.length; i++) {
						EvalocrcritVO evalocrcritVO = vos[i];
						Object attributeValue = evalocrcritVO
								.getAttributeValue(itemExpress);
						if (attributeValue instanceof String) {
							values[i] = (String) attributeValue;
						} else
						// 分值域
						if (attributeValue instanceof Integer) {
							values[i] = ((Integer) attributeValue).toString();
						} else
						// 其他情况，不应该发生的
						{
							values[i] = "";
						}
					}
					return values;
				}
			}
			// 周期信息
			else if (itemExpress.equals(EvalocrcritVO.PK_RKIDCYC)) {
				return new String[] { this.getRkidcycClsVO().getPk_rkidcyc() };
			}
			// 标准说明信息
			else if (itemExpress.equals(EvalspecVO.CONTENT)) {
				return new String[] { ((EvalspecVO) this.getAggEvalspecVO()
						.getParentVO()).getContent() };
			}
			// 附件信息，文件基本信息（编码、名称）
			else if (Arrays.asList(EvalatchVO.FILECODE, EvalatchVO.FILENAME)
					.contains(itemExpress)) {
				CircularlyAccessibleValueObject[] childrenVO = this
						.getAggEvalspecVO().getChildrenVO();
				if (childrenVO == null || childrenVO.length == 0) {
					return new String[0];
				} else {
					String[] values = new String[childrenVO.length];
					for (int i = 0; i < childrenVO.length; i++) {
						EvalatchVO evalatchVO = (EvalatchVO) childrenVO[i];
						values[i] = (String) evalatchVO
								.getAttributeValue(itemExpress);
					}
					return values;
				}
			}
			// 附件信息，文件附件
			else if (Arrays.asList(EvalatchVO.DOCFILE).contains(itemExpress)) {

			}

			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.ui.pub.print.IDataSource#getModuleName()
	 */
	public String getModuleName() {
		return null;
	}

	/**
	 * 获取rkidcycClsVO
	 * 
	 * @see #rkidcycClsVO
	 * @return the rkidcycClsVO
	 */
	public RkidcycClsVO getRkidcycClsVO() {
		if (this.rkidcycClsVO == null) {
			Object selectedData = this.getCycTreeModel().getSelectedData();
			RkidcycClsVO rkidcycClsVO = (RkidcycClsVO) selectedData;
			return rkidcycClsVO;
		} else {
			return this.rkidcycClsVO;
		}
	}

	/**
	 * 获取standardAppModel
	 * 
	 * @see #standardAppModel
	 * @return the standardAppModel
	 */
	public EvalStandardAppModel getStandardAppModel() {
		return this.standardAppModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.ui.pub.print.IDataSource#isNumber(java.lang.String)
	 */
	public boolean isNumber(String itemExpress) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 设置cycTreeModel
	 * 
	 * @see #cycTreeModel
	 * @param cycTreeModel
	 *            the cycTreeModel to set
	 */
	public void setCycTreeModel(HierachicalDataAppModel cycTreeModel) {
		this.cycTreeModel = cycTreeModel;
	}

	/**
	 * 设置indexModel
	 * 
	 * @see #indexModel
	 * @param indexModel
	 *            the indexModel to set
	 */
	public void setIndexModel(BatchBillTableModel indexModel) {
		this.indexModel = indexModel;
	}

	/**
	 * 设置standardAppModel
	 * 
	 * @see #standardAppModel
	 * @param standardAppModel
	 *            the standardAppModel to set
	 */
	public void setStandardAppModel(EvalStandardAppModel standardAppModel) {
		this.standardAppModel = standardAppModel;
	}

}
