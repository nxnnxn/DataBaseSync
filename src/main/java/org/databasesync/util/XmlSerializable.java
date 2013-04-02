package org.databasesync.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.thoughtworks.xstream.XStream;


/*
 * 用于将任意对象持久化到XML文件及其逆过程的持久化类(dom4j,xStream实现)
 */
public class XmlSerializable<T> {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	// XML文件名
	private String xmlFile;
	// XML 文档对象
	private Document document;
	// 根节点
	private Element root;
	// 根节点名称
	private final String rootText = "root";
	
	/*
	 * 单参数构造函数，指定存储的文件名
	 */
	public XmlSerializable(String xmlFile) throws DocumentException {
		this.xmlFile = xmlFile;
		init();
	}
	
	/*
	 * 初始化文档对象及根节点
	 */
	private void init() throws DocumentException {
		File file = new File(xmlFile);
		try {
			if (file.exists()) {
				// 文件存在,直接从文件读取文档对象
				SAXReader reader = new SAXReader();
				
				InputStream in = new FileInputStream(file);
				InputStreamReader strInStream = new InputStreamReader(in, "utf-8");
				document = reader.read(strInStream);
				//document = reader.read(file);
				root = document.getRootElement();
			} else { 
		        //先创建文件的目录   
		        String path = xmlFile.substring(0, xmlFile.lastIndexOf(File.separator));   
		        File pFile = new File(path);   
		        pFile.mkdirs();
		        
				//创建文档对象
				document = DocumentHelper.createDocument();
				root = document.addElement(rootText);// 创建根节点
			}
		} catch (DocumentException e) {
			throw e;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description:TODO
	 *
	 * @param type
	 * @throws DocumentException
	 * @author xiangnan on 2013-3-30
	 */
	public void add(T type) throws DocumentException {
		
		XStream xStream = new XStream();
		String xml = xStream.toXML(type);
		try {
			Document docTmp = DocumentHelper.parseText(xml);
			Element typeElm = docTmp.getRootElement();
			root.add(typeElm);
		} catch (DocumentException e) {
			throw e;
		}
	}

	
	/**
	 * 
	 * @Description:TODO
	 *
	 * @param type
	 * @author xiangnan on 2013-3-30
	 */
	public void del(T type) {
		XStream xStream = new XStream();
		String xml = xStream.toXML(type);
		List nodes = root.elements();
		for (Iterator it = nodes.iterator(); it.hasNext();) {
			Element companyElm = (Element) it.next();
			if (companyElm.asXML().equals(xml)) {
				// 删除原有节点
				root.remove(companyElm);
				// 保存文件
				//saveDocumentToFile();
				//return;
			}
		}
	}

	/*
	 * 从XML中取得所有对象
	 */
	public List<T> loadAll() {
		List<T> retval = new ArrayList<T>();
		List nodes = root.elements();
		for (Iterator it = nodes.iterator(); it.hasNext();) {
			//取得每个节点
			Element companyElm = (Element) it.next();
			//将节点转化为对象
			XStream xStream = new XStream();
			T t = (T) xStream.fromXML(companyElm.asXML());
			retval.add(t);
		}
		return retval;
	}

	/*
	 * 将Document写回文件
	 */
	public void saveDocumentToFile() throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8"); // 指定XML编码
		format.setTrimText(false);
		XMLWriter writer = null;
		try {
	        OutputStream out = new FileOutputStream(xmlFile);
	        OutputStreamWriter outWriter = new OutputStreamWriter(out, "utf-8");
			writer = new XMLWriter(outWriter, format);
			writer.write(document);
		} catch (IOException e) {
			throw e;
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					throw e;
				} finally {
					writer = null;
				}
			}
		}
	}
}
