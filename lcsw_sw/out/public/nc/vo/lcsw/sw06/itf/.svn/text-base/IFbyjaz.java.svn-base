package nc.vo.lcsw.sw06.itf;

import nc.vo.lcsw.sw06.FbyjazBVO;

public interface IFbyjaz {

	/**
	 * 回写报装申请、报装信息的报装状态
	 * 
	 * @param pk_bzxx
	 * @param pk_bzsq
	 * @throws Exception
	 */
	public void updateBzxx(FbyjazBVO[] bvos, String hid) throws Exception;

	/**
	 * 
	 * @param bvos
	 * @param hid
	 * @throws Exception
	 */
	public void unProve(FbyjazBVO[] bvos, String hid) throws Exception;
	
	/**
	 * 根据项目主键查询 是否是共同出资
	 * @param pk_jobbasfil
	 * @return
	 * @throws Exception
	 */
	public String querySFGTCZ(String pk_jobbasfil)throws Exception;

}
