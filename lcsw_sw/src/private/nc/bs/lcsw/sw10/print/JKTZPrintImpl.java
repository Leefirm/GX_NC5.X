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
	
		//��ѯ��ӡ����
		JKTZPrintVO result = queryJKTZPrinVO(pk_bzsq);
		return result;
	}

	/**
	 * ��ӡ�ɿ�֪ͨ������
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
        jktz.setFullName1("��������ˮ��ˮ������޹�˾");
        jktz.setOpenBankAccount1("���н���֧��2102106009245077243");
        //������Ʒ�
        UFDouble sumKcsjf=new UFDouble(0,2);
        //������Ʒ�
        UFDouble sumAfterPay=new UFDouble(0,2);
        //��װ���̿�
        UFDouble sumProjePay=new UFDouble(0,2);
        //���칤�̿�
        UFDouble sumReProjePay=new UFDouble(0,2);
		if(list !=null && list.size()>0) {
		for(BzfyBVO bzfyB : list){
        	if(bzfyB.getPk_costsubj().equals("0001AA1000000000KDSS")  ){//��Ŀ����Ϊ������Ʒ�
        		sumKcsjf=sumKcsjf.add(bzfyB.getAmount(),2,2);
        		if(bzfyB.getXzrq() !=null) {
        		jktz.setPayDate1(bzfyB.getXzrq().toString());//���Ľɿ�����1
        		}
        		if(bzfyB.getAmount() !=null){
        		jktz.setKcsjf(sumKcsjf.toString());
        		}
        	}
        	
        	else if(bzfyB.getPk_costsubj().equals("0001AA1000000000KDSW")){//����������Ʒ�
        		sumAfterPay=sumAfterPay.add(bzfyB.getAmount(),2,2);
        		if(bzfyB.getAmount()!=null){
        		jktz.setAfterPayKcsjf(sumAfterPay.toString());
        		}
        		if(bzfyB.getXzrq()!=null){
        		jktz.setPayDate2(bzfyB.getXzrq().toString());
        		}
        	}
        	else if(bzsqVo.getGcxz().equals("��װ") && (bzfyB.getPk_costsubj().equals("0001AA1000000000KDST") || bzfyB.getPk_costsubj().equals("0001AA1000000000KDSU") ||
        			bzfyB.getPk_costsubj().equals("0001AA1000000000KDSV")) ){//һ�ڶ������ھ��㹤�̿�
        		
        		sumProjePay=sumProjePay.add(bzfyB.getAmount(),2,2);
        		if(sumProjePay !=null ){
        		jktz.setNewProjectPay(sumProjePay.toString());
        		}
        		if(bzfyB.getXzrq() !=null){
        		jktz.setPayDate3(bzfyB.getXzrq().toString());
        		}
        		
        	}
        	else if( (bzsqVo.getGcxz().equals("����") || bzsqVo.getGcxz().equals("��С") || bzsqVo.getGcxz().equals("��װ") )  && (bzfyB.getPk_costsubj().equals("0001AA1000000000KDST") || bzfyB.getPk_costsubj().equals("0001AA1000000000KDSU") ||
        			bzfyB.getPk_costsubj().equals("0001AA1000000000KDSV")) ){//һ�ڶ������ھ��㹤�̿�
        		
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