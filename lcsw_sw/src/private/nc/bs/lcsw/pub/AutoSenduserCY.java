package nc.bs.lcsw.pub;

import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.trade.business.HYPubBO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.lcsw.pub.LcswCYVO;
import nc.vo.lcsw.sw01.LcswBzxxHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.guid.UUID;
import nc.vo.pub.guid.UUIDGenerator;
/**
 * 
 * @author ���
 * ��ҳ�������ܣ���Ӫ��ϵͳ���������ڣ�����Ա���������ڣ����� ��ҳ�� NC ϵͳ
 */
public class AutoSenduserCY implements IBackgroundWorkPlugin {
	static final String YXXT = "lcsw";//Ӫ��ϵͳ
	static final String BZXT = "nc57";//��װϵͳ
	
	private String message = "";
	private HYPubBO bo;

	public HYPubBO getHYPubBo() {
		if (bo == null)
			bo = new HYPubBO();
		return bo;
	}

	// ������
	public String executeTask(BgWorkingContext bgwc) throws BusinessException {
		String reMsg = null;
		try {
			Logger.debug("------------------��ѯ��ͼ---------------------");
			reMsg = checkView();
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			reMsg = e.getMessage();
		}
		return reMsg;
	}

	private String checkView() throws Exception {
		//String source = YXXT; // Ӫ��ϵͳ����Դ
		String source = BZXT; // NC����ϵͳ����Դ
		// �Ӳ�ҳ��ͼ��ȡ��Ҫ���µ�����,�ȶ��м��lcsw_cyfk
		Logger.debug("------------------����ͼ��ȡ����---------------------");
		DBManager db = new DBManager(source);
        //��NCϵͳ�в�����뻧��ŵ�����
		String sqlRhbh="select * from lcsw_bzxx where rhbh is not null and bch is null and bzzt='9' ";
		@SuppressWarnings("unchecked")
		List<LcswBzxxHVO> bzxxList = (List<LcswBzxxHVO>) db.executeQuery(sqlRhbh, new BeanListProcessor(LcswBzxxHVO.class));
		List<String> rhbhList=new ArrayList<String>();
		for(LcswBzxxHVO bzxx:bzxxList){
			rhbhList.add(bzxx.getRhbh());
		}
		source = YXXT; // Ӫ��ϵͳ����Դ
		db=new DBManager(source);
		List<LcswCYVO> list = new ArrayList<LcswCYVO>();
		if(rhbhList !=null){
		for(String rhbh:rhbhList){
		
			String sql=" select rhbh, bch, lhrq,cby,cbrq  from v_cyfk where cbrq is not null and rhbh = " +
					"'"+rhbh+"' and rhbh not in(select rhbh from lcsw_cyfk)";
			LcswCYVO cyvo =  (LcswCYVO) db.executeQuery(sql, new BeanProcessor(LcswCYVO.class));
			
			
			if(cyvo !=null){
			list.add(cyvo);
			}
		}
		}
//		// String sqlWhere = "where a.rhbh <> b.rhbh";
//		String sql = "select rhbh, bch, lhrq,cby,cbrq  from v_cyfk where cbrq is not null "
//				+ "minus (select a.rhbh, a.bch, a.lhrq,a.cby,a.cbrq  from v_cyfk a ,lcsw_cyfk b where a.rhbh =b.rhbh and a.cbrq is not null) ";
//		@SuppressWarnings("unchecked")
//		List<LcswCYVO> list = (List<LcswCYVO>) db.executeQuery(sql,  
//				new BeanListProcessor(LcswCYVO.class));
//		
		
		if (list != null && list.size() > 0) {		
			// ���ݸ��µ���װϵͳ
			source = BZXT;//��װϵͳ����Դ
			BaseDAO dao = new BaseDAO(source);
			//ͳ�Ʋ������������
			ArrayList<LcswCYVO> cyfkMiddle = new ArrayList<LcswCYVO>();
			for (LcswCYVO cyfk : list) {
				try {
				
					String isRhbh = (String) dao.executeQuery("select rhbh from lcsw_bzxx where rhbh="+"'"+cyfk.getRhbh()+"'"
							, new ColumnProcessor(null));
					//����UUID pk
					if(isRhbh !=null ){
					cyfk.setPk_cyfk(UUIDGenerator.getInstance().generateRandomBasedUUID().toString());
					cyfkMiddle.add(cyfk);
					dao.executeUpdate("update lcsw_bzxx set bch =" + "'"
							+ cyfk.getBch() + "'" + ','+"cby=" + "'"
							+ cyfk.getCby() + "'" + ','+"cbrq=" + "'"
							+ cyfk.getCbrq() + "'" + ','+"lhrq=" + "'"
							+ cyfk.getLhrq() + "'" + " where rhbh=" + "'"
							+ cyfk.getRhbh() + "'"); 
					
					}
				} catch (Exception e) {
					Logger.error("��ҳ��������", e);
					message = "��ҳ��������:" + e.getMessage();
				}

			}
			if(cyfkMiddle.size() !=0) {
			message = "��ҳ�����ɹ�����" + cyfkMiddle.size() + "������";
			}
			else {
			message = "��ҳ��������û��������Ҫ����";
			}
			//���뷴���ɹ��������ݸ��µ��м��
			Logger.debug("------------����Ҫ���µ��û����ݲ����м��---------------");
			source = YXXT; // Ӫ��ϵͳ����Դ
//			for (LcswCYVO lcswCYVO : list) {
//				lcswCYVO.setPk_cyfk(UUIDGenerator.getInstance().generateRandomBasedUUID().toString());
//			}
			new BaseDAO(source).insertVOArrayWithPK(cyfkMiddle.toArray(new LcswCYVO[cyfkMiddle.size()]));
				
		}else{
		message = "��ҳ��������û��������Ҫ����";
	
		}
		return message;
	}

	

}
