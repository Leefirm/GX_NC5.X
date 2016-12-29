package nc.bs.lcsw.pub;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import nc.vo.pub.BusinessException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;
/**
 * ����xml
 * @author crf
 *
 */
public class Object2XML {
	//����xml�ڵ���Ϣ
	HashMap<String, String> userMap = new HashMap<String, String>();
	
    public HashMap<String,String> xmlElements(String xmlDoc) throws BusinessException{
        //����һ���µ��ַ���
        StringReader read = new StringReader(xmlDoc);
        //�����µ�����ԴSAX ��������ʹ�� InputSource ������ȷ����ζ�ȡ XML ����
        InputSource source = new InputSource(read);
        //����һ���µ�SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        try {
            //ͨ������Դ����һ��Document
            Document doc = sb.build(source);
            //ȡ�ĸ�Ԫ��
            Element root = doc.getRootElement();
            System.out.println(root.getName());//�����Ԫ�ص����ƣ����ԣ�
            //�õ���Ԫ��������Ԫ�صļ���
            List jiedian = root.getChildren();
            Element et = null;
            for(int i=0;i<jiedian.size();i++){
                et = (Element) jiedian.get(i);//ѭ�����εõ���Ԫ��
                String bianhao = et.getChildText("bzbh");//��װ���Ψһ
                String user_no = et.getChildText("rhbh");
                userMap.put(bianhao, user_no);
            }
            Iterator it = userMap.keySet().iterator();
            while(it.hasNext()){
            	String key = (String) it.next();
            	String value = userMap.get(key);
            	System.out.println(key + ":" + value);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userMap;
    }
}