package nc.ui.lcsw.sw10.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComponent;

import com.ufida.eg.riskmg.vo.evalocrcrit.EvalocrcritVO;

import nc.ui.lcsw.sw10.ClientUI;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.RowAttribute;
import nc.ui.pub.print.IDataSource;

public class JKTZSDataSource implements IDataSource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ClientUI ui;
	
	public JKTZSDataSource() {
	}

	public JKTZSDataSource(ClientUI ui) {
		this.ui = ui;
	}

	public String[] getItemValuesByExpress(String itemExpress) {
		BillItem[] items = ui.getBillCardPanel().getBodyItems();
		Map<String, Integer> columnIDMap = new HashMap<String, Integer>();
		for (int i = 0; i < items.length; i++) {
			columnIDMap.put(items[i].getKey(), i);
		}
		
		int rowCount = ui.getBillCardPanel().getBillModel().getRowCount();
		Map<String, List<Vector>> mapTemp = new HashMap<String, List<Vector>>();
		Vector v = ui.getBillCardPanel().getBillModel().getDataVector();
		for (int i = 0; i < rowCount; i++) {
			Object obj = ((Vector)v.elementAt(i)).elementAt(columnIDMap.get("bzyhbh"));
			if(obj != null) {
				if(mapTemp.get(obj.toString()) == null) {
					List<Vector> list = new ArrayList<Vector>();
					list.add((Vector)v.elementAt(i));
					mapTemp.put(obj.toString(), list);
				} else {
					List<Vector> list = mapTemp.get(obj.toString());
					list.add((Vector)v.elementAt(i));
					mapTemp.put(obj.toString(), list);
				}
			} else {
				
			}
		}
		
		String[] resultStr = new String[mapTemp.size()];
		if (Arrays.asList("azdz", "sngj", "ygj").contains(itemExpress)) {
			BillItem item = ui.getBillCardPanel().getHeadItem(itemExpress);
			if(item != null) {
				for (int i = 0; i < resultStr.length; i++) {
					resultStr[i] = item.getValue();
				}
			}
		} else if(Arrays.asList("bzhm", "bzyhbh", "ygj").contains(itemExpress)) {
			for (int i = 0; i < resultStr.length; i++) {
				Object obj = ((Vector)v.elementAt(i)).elementAt(columnIDMap.get(itemExpress));
				resultStr[i] = obj == null ? "" : obj.toString();
			}
		} else if(Arrays.asList("kcsjf", "kcsjfjkrq", "bjkcsjf", "bjkcsjfjkrq").contains(itemExpress)) {
			Iterator it = mapTemp.keySet().iterator();
			for (int i = 0; it.hasNext(); i++) {
				List<Vector> list = mapTemp.get(it.next().toString());
				
				for (int j = 0; j < list.size(); j++) {
					Object obj = (list.get(j)).elementAt(columnIDMap.get("pk_costsubj"));
					if(obj != null && "0001AA1000000000KDSS".equals(obj.toString())) {
						if("kcsjf".equals(itemExpress)) {
							Object amount = (list.get(j)).elementAt(columnIDMap.get("amount"));
							resultStr[i] = amount == null ? "" : amount.toString();
						} else if("kcsjfjkrq".equals(itemExpress)) {
							Object amount = (list.get(j)).elementAt(columnIDMap.get("tfrq"));
							resultStr[i] = amount == null ? "" : amount.toString();
						}
					} else if(obj != null && "0001AA1000000000KDSW".equals(obj.toString())) {
						if("bjkcsjf".equals(itemExpress)) {
							Object amount = (list.get(j)).elementAt(columnIDMap.get("amount"));
							resultStr[i] = amount == null ? "" : amount.toString();
						} else if("bjkcsjfjkrq".equals(itemExpress)) {
							Object amount = (list.get(j)).elementAt(columnIDMap.get("tfrq"));
							resultStr[i] = amount == null ? "" : amount.toString();
						}
					}
				}
			}
		} else if(Arrays.asList("xzgckqc", "hbgzgckqc").contains(itemExpress)) {
			JComponent item = ui.getBillCardPanel().getHeadItem("khyh").getComponent();
			if(item != null) {
				for (int i = 0; i < resultStr.length; i++) {
					if(item instanceof UIRefPane) {
						resultStr[i] = ((UIRefPane) item).getRefModel().getRefNameValue();
					}
				}
			}
		} else if(Arrays.asList("xzgckbank", "hbgzgckbank").contains(itemExpress)) {
			BillItem item = ui.getBillCardPanel().getHeadItem("yhzh");
			if(item != null) {
				for (int i = 0; i < resultStr.length; i++) {
					resultStr[i] = item.getValue();
				}
			}
		} else if(Arrays.asList("pyear", "pmonth", "pday").contains(itemExpress)) {
			Calendar c = Calendar.getInstance();
			if("pyear".equals(itemExpress)) {
				for (int i = 0; i < resultStr.length; i++) {
					resultStr[i] = String.valueOf(c.get(Calendar.YEAR));
				}
			} else if("pmonth".equals(itemExpress)) {
				for (int i = 0; i < resultStr.length; i++) {
					resultStr[i] = String.valueOf(c.get(Calendar.MONTH) + 1);
				}
			} else if("pday".equals(itemExpress)) {
				for (int i = 0; i < resultStr.length; i++) {
					resultStr[i] = String.valueOf(c.get(Calendar.DATE));
				}
			}
		}
		return resultStr;
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
		return new String[] { "pyear", "pmonth", "pday", "bzhm", "bzyhbh", "azdz", "sngj", "ygj", "kcsjf",
				"kcsjfjkrq", "bjkcsjf", "bjkcsjfjkrq", "xzgck", "xzgckjkrq",
				"xzgckqc", "xzgckbank", "hbgzgck", "hbgzgckrq", "hbgzgckqc",
				"hbgzgckbank", "lxdh", "lxr", "biaojing", "jbry"};
	}

	public String[] getAllDataItemNames() {
		return new String[] {"年", "月", "日", "用户名称","报装编号","安装地址","新装入库管径","原有入户管径", "勘测设计费",
				"交款日期","补交勘测设计费","交款日期","新装工程款","交款日期",
				"全称","开户银行账号","户表改造工程","交款日期","全称",
				"开户银行账号","联系电话", "联系人", "进户表径", "经办人"};
	}

	public String getModuleName() {
		// TODO Auto-generated method stub
		return null;
	}

}
