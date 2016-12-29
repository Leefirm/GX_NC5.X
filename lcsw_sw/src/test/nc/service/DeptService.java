package nc.service;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class DeptService {

	/** Ψһһ��ʵ�� */
	private static DeptService instance = new DeptService();
	/** IP��ַ������ͨ�������ļ����ж�ȡ */
	private String IP = "192.168.100.129";
	//private String IP = "127.0.0.1";
	/** �˿� */
	private String PORT = "8090";
	//private String PORT = "80";
	/** ���ʵ�ַ */
	private String url = "http://" + IP + ":" + PORT
			+ "/uapws/service/NCService";
	/** �½����� */
	private Service service = new Service();

	public DeptService() {
	}

	/**
	 * 
	 * <p>
	 * ����Ψһһ��ʵ��
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
			call.setTargetEndpointAddress(url);// Զ�̵���·��
			call.setOperationName("getDeptdoc");// ���õķ�����

			
			QName qn = new QName("http://baina.so.itf.nc/IBainaInterface",
					"getDeptdoc");
			call.addParameter("string", // ������
					org.apache.axis.encoding.XMLType.XSD_STRING,// ��������:String
					javax.xml.rpc.ParameterMode.IN);// ����ģʽ��'IN' or 'OUT'
			// ���÷���ֵ���ͣ�
			call.setReturnType(qn, String.class);// ����ֵ���ͣ�String
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
	 * ����ʾ��
	 */
	public static void main(String[] args) {
		Object result = DeptService.getInstance().getBarCodeCount("");
		
		System.out.println(result);
	}

}
