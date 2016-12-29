package nc.bs.mw.sqltrans;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import nc.bs.logging.Logger;
import nc.jdbc.framework.DataSourceCenter;
import nc.jdbc.framework.util.DBConsts;

public class SqlTranslator
  implements DBConsts
{
  private boolean m_bTranslate;
  private int m_dbType;
  private int m_DbVersion;
  private long m_lTime;
  private ITranslator m_trans;

  public SqlTranslator()
  {
    this.m_bTranslate = true;
    this.m_dbType = -1;
    this.m_DbVersion = 0;
    this.m_lTime = 0L;
    this.m_trans = null;
    initTranslator(2);
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator Over");
  }

  public SqlTranslator(int dbType) {
    this.m_bTranslate = true;
    this.m_dbType = -1;
    this.m_DbVersion = 0;
    this.m_lTime = 0L;
    this.m_trans = null;
    this.m_dbType = dbType;
    initTranslator(dbType);
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator Over");
  }

  public String getSql(String srcSql) throws SQLException {
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.getSql");
    int destDbType = getDestDbType();
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.getSql Over");
    return getSql(srcSql, destDbType);
  }

  public String getSql(String srcSql, int destDbType) throws SQLException {
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.getSql");
    if ((this.m_trans == null) || (this.m_trans.getDestDbType() != destDbType))
      initTranslator(destDbType);
    ((TranslatorObject)this.m_trans).setDbVersion(this.m_DbVersion);
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.getSql Over");
    return getResultSql(srcSql);
  }

  public synchronized SQLException getSqlException(SQLException srcExp) {
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.getSqlException");

    if (!(this.m_bTranslate)) {
      return srcExp;
    }
    this.m_trans.setSqlException(srcExp);
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.getSqlException Over");

    return this.m_trans.getSqlException();
  }

  public SQLException getSqlException(SQLException srcExp, int destDbType)
  {
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.getSqlException");

    if (this.m_trans.getDestDbType() != destDbType)
      initTranslator(destDbType);
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.getSqlException Over");

    return getSqlException(srcExp);
  }

  public long getTransTime() {
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.getTransTime");
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.getTransTime Over");

    return this.m_lTime;
  }

  private void initTranslator(int dbType) {
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.initTranslator");
    switch (dbType)
    {
    case 0:
      this.m_trans = new TranslateToDB2();
      break;
    case 1:
      this.m_trans = new TranslateToOracle();
      break;
    case 3:
      this.m_trans = new TranslateToSybase();
      break;
    case 2:
      this.m_trans = new TranslateToSqlServer();
      break;
    case 4:
      this.m_trans = new TranslateToInformix();
      break;
    default:
      this.m_trans = new TranslateToSqlServer();
    }

    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.initTranslator Over");
  }

  public void setTransFlag(boolean b)
  {
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.setTransFlag");
    this.m_bTranslate = b;
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.setTransFlag Over");
  }

  public int getDestDbType()
  {
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.getDestDbType");
    if (this.m_dbType != -1) {
      return this.m_dbType;
    }
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.getDestDbType Over");

    return 2;
  }

  public synchronized String getResultSql(String srcSql) throws SQLException
  {
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.getResultSql");
    if (!(this.m_bTranslate))
      return srcSql;
    this.m_lTime = System.currentTimeMillis();
    if (srcSql == null)
      return "";
    String sResult;
    try {
      srcSql = srcSql.trim();
      srcSql = trimPreChars(srcSql);
      this.m_trans.setSql(srcSql);
      sResult = this.m_trans.getSql();
      System.out.println("\n" + ((sResult == null) ? "" : sResult.trim()));
    } catch (Exception e) {
      throw new SQLException(e.getMessage());
    }
    this.m_lTime = (System.currentTimeMillis() - this.m_lTime);
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.getResultSql Over");

    return sResult;
  }

  public String trimPreChars(String srcSql) throws SQLException {
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.trimPreChars");
    if ((srcSql == null) || (srcSql.length() < 1))
      return "";
    int pos = 0;
    String lineSep = System.getProperty("line.separator");

    while ((pos < srcSql.length()) && (((srcSql.charAt(pos) == ' ') || (srcSql.charAt(pos) == '\t') || (lineSep.indexOf(srcSql.charAt(pos)) >= 0)))) {
      ++pos;
    }
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.trimPreChars Over");

    return srcSql.substring(pos);
  }

  public void setConnection(Connection con) {
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.setConnection");
    this.m_DbVersion = DataSourceCenter.getInstance().getDatabaseVersion();
    Logger.setThreadState("nc.bs.mw.sqltrans.SqlTranslator.setConnection Over");
  }
}