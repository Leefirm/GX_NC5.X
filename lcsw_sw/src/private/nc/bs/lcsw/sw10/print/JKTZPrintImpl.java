package nc.bs.lcsw.sw10.print;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw10.BzfyBVO;
import nc.vo.lcsw.sw10.JKTZPrintVO;
import nc.vo.lcsw.sw10.itf.JKTZPrintService;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
public class JKTZPrintImpl implements JKTZPrintService {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	public JKTZPrintVO queryJKTZPrint(String pk_bzsq) throws Exception {
	
		//查询打印数据
		JKTZPrintVO result = queryJKTZPrinVO(pk_bzsq);
		return result;
	}

	/**
	 * 打印缴款通知单数据
	 * @param bzsq
	 * @return jktz
	 * @throws Exception
	 */
	private JKTZPrintVO queryJKTZPrinVO(String pk_bzsq) throws Exception {

		@SuppressWarnings("unchecked")
		BzsqVO bzsqVo=  (BzsqVO) new BaseDAO().executeQuery("select * from lcsw_bzsq where pk_bzsq='"+pk_bzsq+"'"
				, new BeanProcessor(BzsqVO.class));
		JKTZPrintVO jktz = new JKTZPrintVO();
		jktz.setUserName(bzsqVo.getHm());
		String jobcodeSql="select jobcode from  bd_jobbasfil b where  b.pk_jobbasfil= '"+bzsqVo.getPk_jobbasfil()+"'";
		String jobcode = (String) new BaseDAO().executeQuery(jobcodeSql, new ColumnProcessor (null));
		jktz.setBzbh(jobcode.toString());
		
	
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		
		jktz.setNoticeDate(time);
		jktz.setAzdz(bzsqVo.getAzdz());
		jktz.setNewRhgj(bzsqVo.getSwgj());
		jktz.setOldRhgj(bzsqVo.getYgj());
//		if(bzsqVo.getKcsjf() !=null && bzsqVo.getKcsjf().toString().length() >0){
//			jktz.setKcsjf(bzsqVo.getKcsjf().toString());
//			}
		String pk_bzfy_h = (String) new BaseDAO().executeQuery("select pk_bzfy_h from lcsw_bzfy_h where pk_bzsq='"+pk_bzsq+"'", new ColumnProcessor (null));
		@SuppressWarnings("unchecked")
		List<BzfyBVO> list = (List<BzfyBVO>) new BaseDAO().executeQuery("select * from lcsw_bzfy_b where (xzbz='N' or xzbz is null) and  pk_bzfy_h='"+pk_bzfy_h +"'", new BeanListProcessor(BzfyBVO.class));
        jktz.setFullName1("南宁市南水供水设计有限公司");
        jktz.setOpenBankAccount1("工行江南支行2102106009245077243");
        //勘测设计费
        UFDouble sumKcsjf=new UFDouble(0,2);
        //勘测设计费
        UFDouble sumAfterPay=new UFDouble(0,2);
        //新装工程款
        UFDouble sumProjePay=new UFDouble(0,2);
        //改造工程款
        UFDouble sumReProjePay=new UFDouble(0,2);
		if(list !=null && list.size()>0) {
		for(BzfyBVO bzfyB : list){
        	if(bzfyB.getPk_costsubj().equals("0001AA1000000000KDSS")  ){//项目类型为勘测设计费
        		sumKcsjf=sumKcsjf.add(bzfyB.getAmount(),2,2);
        		if(bzfyB.getXzrq() !=null) {
        		jktz.setPayDate1(bzfyB.getXzrq().toString());//表单的缴款日期1
        		}
        		if(bzfyB.getAmount() !=null){
        		jktz.setKcsjf(sumKcsjf.toString());
        		}
        	}
        	
        	else if(bzfyB.getPk_costsubj().equals("0001AA1000000000KDSW")){//补交勘测设计费
        		sumAfterPay=sumAfterPay.add(bzfyB.getAmount(),2,2);
        		if(bzfyB.getAmount()!=null){
        		jktz.setAfterPayKcsjf(sumAfterPay.toString());
        		}
        		if(bzfyB.getXzrq()!=null){
        		jktz.setPayDate2(bzfyB.getXzrq().toString());
        		}
        	}
        	else if(bzsqVo.getGcxz().equals("新装") && (bzfyB.getPk_costsubj().equals("0001AA1000000000KDST") || bzfyB.getPk_costsubj().equals("0001AA1000000000KDSU") ||
        			bzfyB.getPk_costsubj().equals("0001AA1000000000KDSV")) ){//一期二期三期决算工程款
        		
        		sumProjePay=sumProjePay.add(bzfyB.getAmount(),2,2);
        		if(sumProjePay !=null ){
        		jktz.setNewProjectPay(sumProjePay.toString());
        		}
        		if(bzfyB.getXzrq() !=null){
        		jktz.setPayDate3(bzfyB.getXzrq().toString());
        		}
        		
        	}
        	else if( (bzsqVo.getGcxz().equals("改造") || bzsqVo.getGcxz().equals("改小") || bzsqVo.getGcxz().equals("改装") )  && (bzfyB.getPk_costsubj().equals("0001AA1000000000KDST") || bzfyB.getPk_costsubj().equals("0001AA1000000000KDSU") ||
        			bzfyB.getPk_costsubj().equals("0001AA1000000000KDSV")) ){//一期二期三期决算工程款
        		
        		sumReProjePay=sumReProjePay.add(bzfyB.getAmount(),2,2);
        		if(sumReProjePay!=null){
        		jktz.setHbgzProjectPay(sumReProjePay.toString());
        		}
        		if(bzfyB.getXzrq()!=null){
        		jktz.setPayDate4(bzfyB.getXzrq().toString());
        		}
        		
        	}
          }
		}
		
		return jktz;
		
	}

}