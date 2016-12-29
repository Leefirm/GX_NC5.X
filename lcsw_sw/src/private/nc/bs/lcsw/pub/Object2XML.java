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
 * 解析xml
 * @author crf
 *
 */
public class Object2XML {
	//解析xml节点信息
	HashMap<String, String> userMap = new HashMap<String, String>();
	
    public HashMap<String,String> xmlElements(String xmlDoc) throws BusinessException{
        //创建一个新的字符串
        StringReader read = new StringReader(xmlDoc);
        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        //创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        try {
            //通过输入源构造一个Document
            Document doc = sb.build(source);
            //取的根元素
            Element root = doc.getRootElement();
            System.out.println(root.getName());//输出根元素的名称（测试）
            //得到根元素所有子元素的集合
            List jiedian = root.getChildren();
            Element et = null;
            for(int i=0;i<jiedian.size();i++){
                et = (Element) jiedian.get(i);//循环依次得到子元素
                String bianhao = et.getChildText("bzbh");//报装编号唯一
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