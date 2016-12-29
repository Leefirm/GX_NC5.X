package nc.bs.lcsw.sw11.print;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.lcsw.sw02.BzsqVO;
import nc.vo.lcsw.sw10.JKTZPrintVO;
import nc.vo.lcsw.sw11.ZZSHBPrintVO;
import nc.vo.lcsw.sw11.itf.ZZSHBPrintService;
import nc.vo.pub.lang.UFDouble;

public class ZZSHBPrintImpl implements ZZSHBPrintService {
	public static Map<String,String> NCJbry = new HashMap<String,String>();
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	public ZZSHBPrintVO queryZZSHBPrint(String pk_sjfksjhid) throws Exception {

		
		
		//查询打印数据kcry
		ZZSHBPrintVO result = queryZZSHBPrintVO(pk_sjfksjhid);
		return result;
	}

	private ZZSHBPrintVO queryZZSHBPrintVO(String pk_sjfksjhid) throws Exception {
		String pk_bzsq=  (String) new BaseDAO().executeQuery("select bzsq.pk_bzsq  from lcsw_sjfksj_h h, lcsw_sjfksj_b b, lcsw_bzsq bzsq where b.pk_bzsq=bzsq.pk_bzsq  and h.pk_sjfksjhid=b.pk_sjfksjhid and h.pk_sjfksjhid='"+pk_sjfksjhid+"'"
				,new ColumnProcessor (null));
		
		BzsqVO bzsqVo=  (BzsqVO) new BaseDAO().executeQuery("select * from lcsw_bzsq where pk_bzsq='"+pk_bzsq+"'"
				, new BeanProcessor(BzsqVO.class));
		
		ZZSHBPrintVO  zzshb= new ZZSHBPrintVO();
		
		String jobcodeSql="select jobcode from  bd_jobbasfil b where  b.pk_jobbasfil= '"+bzsqVo.getPk_jobbasfil()+"'";
		String jobcode = (String) new BaseDAO().executeQuery(jobcodeSql, new ColumnProcessor (null));
		String gcmc = (String) new BaseDAO().executeQuery("select jobname from bd_jobbasfil where jobcode='"+jobcode+"'", new ColumnProcessor (null));
		zzshb.setGcmc(gcmc);
		zzshb.setJobcode(jobcode);
		zzshb.setLxdh(bzsqVo.getLxdd());
		zzshb.setLxr(bzsqVo.getLxr());
		//通知日期
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy MM dd");
		String time=format.format(date);
		time.replace("-", "  ");
		
		
		zzshb.setNoticeDate(time);
		
		zzshb.setIntoPipe(bzsqVo.getSwgj());
		zzshb.setIntoWatch(bzsqVo.getSwkj());
		UFDouble kcsjf=new UFDouble(0,2);
		kcsjf=bzsqVo.getKcsjf().setScale(2, UFDouble.ROUND_HALF_UP);
		zzshb.setKcsjf(kcsjf.toString());
		//经办人员KCRY
		NCJbry.put( "0001F51000000000SL11","李娟");
		NCJbry.put( "0001AA1000000000TPG6","唐学艳");
		NCJbry.put( "0001AA1000000000TPG7","吴捷凯");
		NCJbry.put("0001AA1000000000JB8N","梁丽云");
		NCJbry.put( "0001AA1000000000TPG3","吴波");
		NCJbry.put( "0001AA1000000000TPG4","欧玲");
		NCJbry.put( "0001AA1000000000TPG5","张晶");
		NCJbry.put( "0001AA1000000000JB8O","陈秋玲");
		NCJbry.put("0001F51000000000OLUT","黄丽冰");
		NCJbry.put("0001A31000000000QB1J","梁静");
		NCJbry.put("0001AA100000000A7MKO","梁欣欣");
		
		String jbry = NCJbry.get(bzsqVo.getJbry());
		
//		zzshb.setJbr(jbry);
		String idJbr = bzsqVo.getVoperatorid();
		String jbrName = (String) new BaseDAO().executeQuery("select user_name from sm_user where cuserid='"+idJbr+"'", new ColumnProcessor (null));
		zzshb.setJbr(jbrName);
		String kcrySql="select kcry from  lcsw_kcfksj_h b where  b.pk_jobbasfil= '"+bzsqVo.getPk_jobbasfil()+"'";
		String kcryCode = (String) new BaseDAO().executeQuery(kcrySql, new ColumnProcessor (null));
		
		zzshb.setKcry(kcryCode);
		if(bzsqVo.getGtcz()!=null){
		if(bzsqVo.getGtcz().toString().equals("Y")){
			zzshb.setCzway("是共同出资");
		}
			else if(bzsqVo.getGtcz().toString().equals("N")){
				zzshb.setCzway("不是共同出资");
			}
		}
		
		zzshb.setYsxz(bzsqVo.getYsxz());
		String sblxSql="select h.reserve1  from lcsw_sjfksj_h h, lcsw_sjfksj_b b, lcsw_bzsq bzsq where b.pk_bzsq=bzsq.pk_bzsq  and h.PK_SJFKSJHID=b.PK_SJFKSJHID and b.pk_bzsq='"
				+bzsqVo.getPk_bzsq()+"'";
		String sblx = (String) new BaseDAO().executeQuery(sblxSql, new ColumnProcessor (null));
		if(sblx!=null && sblx.length()>0){
		zzshb.setSblx(sblx);
		}
		String sbytSql="select h.reserve10  from lcsw_sjfksj_h h, lcsw_sjfksj_b b, lcsw_bzsq bzsq where b.pk_bzsq=bzsq.pk_bzsq  and h.PK_SJFKSJHID=b.PK_SJFKSJHID and b.pk_bzsq='"
				+bzsqVo.getPk_bzsq()+"'";
		String sbyt = (String) new BaseDAO().executeQuery(sbytSql, new ColumnProcessor (null));
		if(sbyt!=null && sbyt.length()>0) {
		zzshb.setSbyt(sbyt);
		}
		return zzshb;
	}

}
