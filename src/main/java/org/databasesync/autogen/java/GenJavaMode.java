package org.databasesync.autogen.java;



import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.databasesync.autogen.BaseTable;
import org.databasesync.autogen.GenMode;
import org.dom4j.DocumentException;

/**
 * 
 * @ClassName:  GenJavaMode
 * @Description:TODO
 * 
 * @author nxn on 2013-3-11
 */
public interface GenJavaMode extends GenMode{
	public String genJavaMode(Map paramMap,BaseTable table) throws SQLException, DocumentException, IOException;
}
