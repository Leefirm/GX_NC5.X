package com.lcsw.his;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.common.RuntimeEnv;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ArrayProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.md.util.EnvInit;
import nc.vo.pub.BusinessException;

public class SfryMapCode {
	static final String NCDS = "nc57";
	public static void main(String[] args) throws BusinessException{
		// 初始化环境
		Properties props = EnvInit.initClientEnv();
		String baseURL = "http://localhost:8085";
		RuntimeEnv.getInstance().setProperty("SERVICEDISPATCH_URL",
				baseURL + "/ServiceDispatcherServlet");
		props.setProperty("SERVICEDISPATCH_URL", baseURL
				+ "/ServiceDispatcherServlet");

		long conntime = System.currentTimeMillis();
		
		SfryCode();
		long resetTime = System.currentTimeMillis();
		System.out
				.println("获取map语句，耗时：" + (resetTime - conntime) / 1000 + "秒；");
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public static void SfryCode()  throws BusinessException {
		InvocationInfoProxy.getInstance().setUserDataSource(NCDS);
		@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
		Map<String,String> map = new HashMap();
		@SuppressWarnings("rawtypes")
		List<String> list =  (List) NCLocator.getInstance().lookup(IUAPQueryBS.class)
		.executeQuery("select cuserid from sm_user ", new ColumnListProcessor( ));
		for (String cuserid:list){
			String user_name =  (String) NCLocator.getInstance().lookup(IUAPQueryBS.class)
					.executeQuery("select user_name from sm_user where cuserid = "+"'"+cuserid.toString()+"'"
							, new ColumnProcessor(null ));
	        map.put(cuserid, user_name);
			//System.out.println( "NCSfry.put( "+cuserid.toString()+ ","+ user_name.toString()+" );");
		
		}
		
		for(Entry<String, String>  entry: map.entrySet()){
			System.out.println("NCSfry.put( "+"\""+entry.getKey()+"\"" +" , "+"\""+ entry.getValue()+"\""+" );");
			
		}
	}
}
