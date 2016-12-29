package nc.test;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class NCService {

	/** Ψһһ��ʵ�� */
	private static NCService instance = new NCService();
	/** IP��ַ������ͨ�������ļ����ж�ȡ */
	private String IP = "192.168.100.129";
	/** �˿� */
	private String PORT = "80";
	/** ���ʵ�ַ */
	private String url = "http://" + IP + ":" + PORT
			+ "/uapws/service/NCService";
	/** �½����� */
	private Service service = new Service();

	public NCService() {
	}
	/**
	 * ����Ψһһ��ʵ��
	 * @return NCService
	 */
	public static NCService getInstance() {
		return instance;
	}

	/***
	 * ����Ӫ��ϵͳ
	 * @throws Exception
	 */
	public Object getSaleInterface(String string) {
		try {

			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);// Զ�̵���·��
			call.setOperationName("getDeptdoc");// ���õķ�����

			// ���ò�����:
			call.addParameter("string", // ������
					org.apache.axis.encoding.XMLType.XSD_STRING,// ��������:String
					javax.xml.rpc.ParameterMode.IN);// ����ģʽ��'IN' or 'OUT'
			QName qn = new QName("http://baina.so.itf.nc/IBainaInterface",
					"getDeptdoc(String)");
			// ���÷���ֵ���ͣ�
			call.setReturnType(qn, String.class);// ����ֵ���ͣ�String
			return call.invoke(new Object[] { string,});
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
}
