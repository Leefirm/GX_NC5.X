package nc.vo.lcsw.sw10.itf;

import nc.vo.lcsw.sw10.JKTZPrintVO;

public interface JKTZPrintService {

    //取VO打印的缴款通知单    
	public JKTZPrintVO queryJKTZPrint(String pk_bzsq) throws Exception; 
}
