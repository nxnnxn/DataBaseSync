package org.databasesync.autogen.sql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.databasesync.autogen.BaseTable;
import org.databasesync.autogen.GenMode;
import org.dom4j.DocumentException;



/**
 * 
 * @ClassName:  GenSqls
 * @Description:TODO
 * 
 * @author nxn on 2013-3-11
 */
public interface GenSqls extends GenMode{

	public String readFile(String exampleName) throws FileNotFoundException, IOException;
	

	public String[]  genSqls(List<BaseTable> fromCenterTableList) throws  DocumentException, IOException;
}
