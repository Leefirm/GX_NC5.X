package nc.service;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class DeptService {

	/** 唯一一个实例 */
	private static DeptService instance = new DeptService();
	/** IP地址，建议通过配置文件进行读取 */
	private String IP = "192.168.100.129";
	//private String IP = "127.0.0.1";
	/** 端口 */
	private String PORT = "8090";
	//private String PORT = "80";
	/** 访问地址 */
	private String url = "http://" + IP + ":" + PORT
			+ "/uapws/service/NCService";
	/** 新建服务 */
	private Service service = new Service();

	public DeptService() {
	}

	/**
	 * 
	 * <p>
	 * 返回唯一一个实例
	 * </p>
	 * 
	 * @return NCService
	 */
	public static DeptService getInstance() {
		return instance;
	}
	

	/***
	 * 
	 * 
	 * @param vreceiptcode
	 * @return
	 * @throws Exception
	 */
	public Object getBarCodeCount(String code) {
		try {

			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);// 远程调用路径
			call.setOperationName("getDeptdoc");// 调用的方法名

			
			QName qn = new QName("http://baina.so.itf.nc/IBainaInterface",
					"getDeptdoc");
			call.addParameter("string", // 参数名
					org.apache.axis.encoding.XMLType.XSD_STRING,// 参数类型:String
					javax.xml.rpc.ParameterMode.IN);// 参数模式：'IN' or 'OUT'
			// 设置返回值类型：
			call.setReturnType(qn, String.class);// 返回值类型：String
			return call.invoke(new Object[] { code });

		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param args
	 * 调用示例
	 */
	public static void main(String[] args) {
		Object result = DeptService.getInstance().getBarCodeCount("");
		
		System.out.println(result);
	}

}
