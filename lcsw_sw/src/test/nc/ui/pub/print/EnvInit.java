package nc.ui.pub.print;

import java.util.Properties;

import nc.bs.framework.common.RuntimeEnv;
import nc.vo.jcom.lang.StringUtil;

/**
 * 设计器连接中间件环境时的初始设置<br>
 * 默认为本机，80端口，以后可以扩展在设计器里设置连具体的中间件地址和端口
 * @author cch
 *
 */
public class EnvInit {
	
	public static Properties initClientEnv()
	{
		/*
		try {
			FileWriter writer = new FileWriter(new File("c:/aaaa.txt"));
			writer.write(System.getProperty("CLIENT_URL_MDE"));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;*/
		
		Properties props = new Properties();
		RuntimeEnv.getInstance().setProperty("CLIENT_COMMUNICATOR","nc.bs.framework.comn.cli.JavaURLCommunicator");
		props.setProperty("CLIENT_COMMUNICATOR","nc.bs.framework.comn.cli.JavaURLCommunicator");
		RuntimeEnv.getInstance().setRunningInServer(false);
		String customURL = System.getProperty("CLIENT_URL_MDE");
		String baseURL = StringUtil.isEmpty(customURL)?"http://localhost:8085":customURL;
		RuntimeEnv.getInstance().setProperty("SERVICEDISPATCH_URL",
				baseURL + "/ServiceDispatcherServlet");
		props.setProperty("SERVICEDISPATCH_URL", baseURL
				+ "/ServiceDispatcherServlet");
		RuntimeEnv.getInstance().setProperty("CLIENT_COMMUNICATOR",
				"nc.bs.framework.comn.cli.JavaURLCommunicator");
		props.setProperty("CLIENT_COMMUNICATOR","nc.bs.framework.comn.cli.JavaURLCommunicator");
		return props;
		
	}
}
