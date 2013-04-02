package org.databasesync.autogen.sqlmap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.databasesync.autogen.BaseTable;
import org.databasesync.autogen.GenMode;
import org.dom4j.DocumentException;




public interface GenExtractXml extends GenMode{

	public String genExtractXml(Map paramMap,BaseTable table) throws DocumentException, IOException;

	public String readFile(String exampleName) throws FileNotFoundException,IOException;
}
