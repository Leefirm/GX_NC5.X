package nc.bs.lcsw.sw01;

import nc.bs.dao.BaseDAO;

import nc.vo.lcsw.sw01.itf.IBzxx;


public class BzxxImpl implements IBzxx{

	public void updateJobName(String jobbasfil, String jobbasfil_name) throws Exception {
		
		
			BaseDAO dao = new BaseDAO();
			String sqlJobname= "update bd_jobbasfil set jobname = '"+jobbasfil_name+"' where pk_jobbasfil= '"+jobbasfil+"'";
			dao.executeUpdate(sqlJobname);
		
		
	
	}

}
