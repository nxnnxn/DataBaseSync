package org.databasesync.autogen.sqlmapconfig.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import org.apache.log4j.Logger;
import org.databasesync.autogen.BaseTable;
import org.databasesync.autogen.sqlmapconfig.GenSqlMapConfigXml;
import org.databasesync.main.Constants;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class GenClientSqlMapConfigXml implements GenSqlMapConfigXml {

	Logger logger = Logger.getLogger(this.getClass());

	
	@Override
	public void genSqlMapConfigXml(List<BaseTable> list) throws IOException, DocumentException {
		InputStream stream = ClassLoader.getSystemClassLoader()
				.getResourceAsStream(Constants.MAPCONFIGDEFAULTPATH);
		SAXReader reader = new SAXReader();
		Document doc;
		FileWriter writer = null;
		XMLWriter xmlWriter = null;
		try {
			doc = reader.read(stream);
			Element root = doc.getRootElement();
			Element properties = root.element("properties");
			Attribute resource=properties.attribute("resource");
			resource.setText(Constants.DBPROPERTIES);
			Element typeAliases = root.element("typeAliases");
			Element mappers = root.element("mappers");
			for (BaseTable po : list) {
				typeAliases
						.addElement("typeAlias")
						.addAttribute("alias", po.getTableName())
						.addAttribute("type",
								Constants.JAVAPACKAGE + "." + po.getTableName());
				mappers.addElement("mapper").addAttribute(
						"resource",
						Constants.XMLPACKAGE.replace(".", "/") + "/"
								+ po.getTableName() + ".xml");
			}
			OutputFormat format = OutputFormat.createPrettyPrint();
		
			writer = new FileWriter(Constants.MAPCONFIGPATH);
			 xmlWriter = new XMLWriter(writer, format);
			xmlWriter.write(doc);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			stream.close();
			writer.close();
			xmlWriter.close();
		}

	}
}
