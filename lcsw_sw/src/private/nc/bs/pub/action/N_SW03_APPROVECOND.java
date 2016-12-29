/**    Create By NCPlugin beta 0.1.   **/

//insert into pub_billactiongroup(ACTIONSTYLE,ACTIONSTYLEREMARK,DR,PK_BILLACTIONGROUP,PK_BILLTYPE,TS) 
//values( 'BOASS','����',0,'140807173102883ZWT4H','SW03','2014-08-07 17:31:02')
//go

//insert into pub_billactionconfig(ACTIONTYPE,DR,PK_BILLACTIONCONFIG,PK_BILLACTIONGROUP,SYSINDEX,TS) 
//values( 'APPROVECOND',0,'140807173102883ZWT4H','14080717310298349P13',1,'2014-08-07 17:31:02')
//go

package nc.bs.pub.action;

import java.util.Hashtable;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.*;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pub.pf.PfUtilActionVO;
import nc.vo.uap.pf.PFBusinessException;
/**
 * ��ע������:<b><code>SW03</code></b> ��������� <tt>APPROVECOND</tt> ��ִ̬���ࡣ
 * 
 * @since �������ڣ�2014-08-07 17:31:02
 * @version UAP 5.x 
 * @author Administrator
 * @author NC Plugin
 */
public class N_SW03_APPROVECOND extends AbstractCompiler2
{

    public N_SW03_APPROVECOND()
    {
        m_methodReturnHas = new Hashtable();
        m_keyHas = null;
    }

    public Object runComClass(PfParameterVO vo)
        throws BusinessException
    {
        try
        {
            super.m_tmpVo = vo;
//            Object retObj = null;
            String infoAry[] = new String[3];
            infoAry[0] = (String)getVo().getParentVO().getAttributeValue("pk_billtype");
            infoAry[1] = getVo().getParentVO().getPrimaryKey();
            infoAry[2] = (String)getVo().getParentVO().getAttributeValue("pk_busitype");
            setUserObj(infoAry);
            PfUtilActionVO actionVo = new PfUtilActionVO();
            actionVo.setIsDLG(true);
            actionVo.setClassName("nc.ui.pub.workflownote.FlowStateDlg");
            actionVo.setMethod("");
            actionVo.setParameter("");
            actionVo.setUIObj(m_tmpVo.m_userObj);
            return actionVo;
        }
        catch(Exception ex)
        {
            if(ex instanceof BusinessException)
                throw (BusinessException)ex;
            else
                throw new PFBusinessException(ex.getMessage(), ex);
        }
    }

    public String getCodeRemark()
    {
        return "\t//****���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ****/\n Object retObj  =null;\n //****��Ҫ˵���������Ϊ����PNL�Ľ��淽�����ܽ����޸�****/\n String[] infoAry=new String[3];\n infoAry[0]=(String)getVo().getParentVO().getAttributeValue(\"pk_billtype\");\n infoAry[1]=getVo().getParentVO().getPrimaryKey();\n infoAry[2]=(String)getVo().getParentVO().getAttributeValue(\"pk_busitype\");\n setUserObj(infoAry);\n //********�÷���������ű��в��������return���********/\n PfUtilActionVO actionVo=new PfUtilActionVO();\n actionVo.setIsDLG(true);\n actionVo.setClassName(\"nc.ui.pub.workflownote.FlowStateDlg\");\n actionVo.setMethod( \"\");\n actionVo.setParameter( \"\");\n actionVo.setUIObj(m_tmpVo.m_userObj);\n return actionVo;\n //**************************************************/\n";
    }

    private void setParameter(String key, Object val)
    {
        if(m_keyHas == null)
            m_keyHas = new Hashtable();
        if(val != null)
            m_keyHas.put(key, val);
    }

    private Hashtable m_methodReturnHas;
    private Hashtable m_keyHas;
}