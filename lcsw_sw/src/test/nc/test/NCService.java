package nc.test;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class NCService {

	/** 唯一一个实例 */
	private static NCService instance = new NCService();
	/** IP地址，建议通过配置文件进行读取 */
	private String IP = "192.168.100.129";
	/** 端口 */
	private String PORT = "80";
	/** 访问地址 */
	private String url = "http://" + IP + ":" + PORT
			+ "/uapws/service/NCService";
	/** 新建服务 */
	private Service service = new Service();

	public NCService() {
	}
	/**
	 * 返回唯一一个实例
	 * @return NCService
	 */
	public static NCService getInstance() {
		return instance;
	}

	/***
	 * 调用营销系统
	 * @throws Exception
	 */
	public Object getSaleInterface(String string) {
		try {

			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);// 远程调用路径
			call.setOperationName("getDeptdoc");// 调用的方法名

			// 设置参数名:
			call.addParameter("string", // 参数名
					org.apache.axis.encoding.XMLType.XSD_STRING,// 参数类型:String
					javax.xml.rpc.ParameterMode.IN);// 参数模式：'IN' or 'OUT'
			QName qn = new QName("http://baina.so.itf.nc/IBainaInterface",
					"getDeptdoc(String)");
			// 设置返回值类型：
			call.setReturnType(qn, String.class);// 返回值类型：String
			return call.invoke(new Object[] { string,});
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
}
