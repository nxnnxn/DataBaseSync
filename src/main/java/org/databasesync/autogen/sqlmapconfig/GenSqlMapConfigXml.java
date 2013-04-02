package org.databasesync.autogen.sqlmapconfig;



import java.io.IOException;
import java.util.List;

import org.databasesync.autogen.BaseTable;
import org.dom4j.DocumentException;

public interface GenSqlMapConfigXml{
	public void genSqlMapConfigXml(List<BaseTable> toCenterTableList) throws  DocumentException, IOException;
}
