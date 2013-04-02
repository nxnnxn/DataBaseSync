package org.databasesync.autogen.java.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import org.databasesync.autogen.BaseTable;
import org.databasesync.autogen.Metas;
import org.databasesync.autogen.SaveToFile;
import org.databasesync.autogen.java.GenJavaMode;
import org.databasesync.main.Constants;
import org.dom4j.DocumentException;




/**
 * 
 * @ClassName:  GenJavaModeCode
 * @Description:TODO
 * 
 * @author nxn on 2013-3-12
 */
public class GenJavaModeCode extends SaveToFile implements GenJavaMode {

	Logger logger = Logger.getLogger(this.getClass());
	protected String tableName = null;
	protected String packageStr = null;
	
	public GenJavaModeCode() {
		this.packageStr = Constants.JAVAPACKAGE;
	}
	
	@Override
	public String getSavePath()  {
		return new File("").getAbsolutePath()+"\\"+packageStr;
	}

	@Override
	public String genJavaMode(Map paramMap, BaseTable table) throws SQLException, DocumentException, IOException {
		boolean hasDate = true;
		boolean hasBigDecimal = true;
		String filepath="";
		tableName = table.getTableName();
		StringBuffer importContent = new StringBuffer();
		if(packageStr != null && packageStr.length() > 0) {
			importContent = importContent.append("package " + packageStr).append(";").append("\n\r");
		}
		StringBuffer content = new StringBuffer();
		StringBuffer methodContent = new StringBuffer();
		content.append("public class ").append(tableName).append(" {").append("\n\r");
		List<Metas> metasList = null;
		try {
			metasList=this.selectMetasByTable(paramMap);
			if(metasList != null && metasList.size() > 0) {
				for (int i = 0; i < metasList.size(); i++) {
					content.append("\t").append("private ");
					if (metasList.get(i).getDataType().equals("NUMBER")) {
						if (metasList.get(i).getDataScale() == 0) {
							if (metasList.get(i).getDataDefault() != null
									&& !(metasList.get(i).getDataDefault().trim().equals(""))) {
								content.append("int "
										+ metasList.get(i).getColumnName() + " = "
										+ metasList.get(i).getDataDefault().trim() + ";").append("\n");
							} else {
								content.append("int "
										+ metasList.get(i).getColumnName() + ";").append("\n");
							}
							methodContent.append("\tpublic int get").append(metasList.get(i).getColumnName()).append("() {").append("\n");
							methodContent.append("\t\t").append("return ").append(metasList.get(i).getColumnName()).append(";").append("\n");
							methodContent.append("\t}").append("\n");
							
							methodContent.append("\tpublic void set").append(metasList.get(i).getColumnName()).append("(int ").append(toLowerCaseFirstOne(metasList.get(i).getColumnName())).append(") {").append("\n");
							methodContent.append("\t\t").append(metasList.get(i).getColumnName()).append(" = ").append(toLowerCaseFirstOne(metasList.get(i).getColumnName())).append(";").append("\n");
							methodContent.append("\t}").append("\n");
						} else if (metasList.get(i).getDataDefault() != null
								&& !(metasList.get(i).getDataDefault().trim().equals(""))) {
							content.append("BigDecimal "
									+ metasList.get(i).getColumnName() + " = new BigDecimal("
									+ metasList.get(i).getDataDefault().trim() + ");").append("\n");
							methodContent.append("\tpublic BigDecimal get").append(metasList.get(i).getColumnName()).append("() {").append("\n");
							methodContent.append("\t\t").append("return ").append(metasList.get(i).getColumnName()).append(";").append("\n");
							methodContent.append("\t}").append("\n");
							
							methodContent.append("\tpublic void set").append(metasList.get(i).getColumnName()).append("(BigDecimal ").append(toLowerCaseFirstOne(metasList.get(i).getColumnName())).append(") {").append("\n");
							methodContent.append("\t\t").append(metasList.get(i).getColumnName()).append(" = ").append(toLowerCaseFirstOne(metasList.get(i).getColumnName())).append(";").append("\n");
							methodContent.append("\t}").append("\n");
							if(hasBigDecimal) {
								importContent.append("import java.math.BigDecimal;").append("\n");
								hasBigDecimal = false;
							}
						} else {
							content.append("BigDecimal "
									+ metasList.get(i).getColumnName() + ";").append("\n");
							methodContent.append("\tpublic BigDecimal get").append(metasList.get(i).getColumnName()).append("() {").append("\n");
							methodContent.append("\t\t").append("return ").append(metasList.get(i).getColumnName()).append(";").append("\n");
							methodContent.append("\t}").append("\n");
							
							methodContent.append("\tpublic void set").append(metasList.get(i).getColumnName()).append("(BigDecimal ").append(toLowerCaseFirstOne(metasList.get(i).getColumnName())).append(") {").append("\n");
							methodContent.append("\t\t").append(metasList.get(i).getColumnName()).append(" = ").append(toLowerCaseFirstOne(metasList.get(i).getColumnName())).append(";").append("\n");
							methodContent.append("\t}").append("\n");
							if(hasBigDecimal) {
								importContent.append("import java.math.BigDecimal;").append("\n\r");
								hasBigDecimal = false;
							}
						}
					} else if (metasList.get(i).getDataType().equalsIgnoreCase("TIMESTAMP(6)")) {
						if (metasList.get(i).getDataDefault() != null
								&& !(metasList.get(i).getDataDefault().trim().equals(""))
								&& !(metasList.get(i).getDataDefault().trim().equalsIgnoreCase("SYSDATE"))) {
							content.append("Date "
									+ metasList.get(i).getColumnName() + " = "
									+ metasList.get(i).getDataDefault().trim() + ";").append("\n");
						} else if(metasList.get(i).getDataDefault() != null 
								&& metasList.get(i).getDataDefault().trim().equalsIgnoreCase("SYSDATE")){
							content.append("Date " + metasList.get(i).getColumnName() + " = null;").append("\n");
						} else {
							content.append("Date " + metasList.get(i).getColumnName() + ";").append("\n");
						}
						methodContent.append("\tpublic Date get").append(metasList.get(i).getColumnName()).append("() {").append("\n");
						methodContent.append("\t\t").append("return ").append(metasList.get(i).getColumnName()).append(";").append("\n");
						methodContent.append("\t}").append("\n");
						
						methodContent.append("\tpublic void set").append(metasList.get(i).getColumnName()).append("(Date ").append(toLowerCaseFirstOne(metasList.get(i).getColumnName())).append(") {").append("\n");
						methodContent.append("\t\t").append(metasList.get(i).getColumnName()).append(" = ").append(toLowerCaseFirstOne(metasList.get(i).getColumnName())).append(";").append("\n");
						methodContent.append("\t}").append("\n");
						if(hasDate) {
							importContent.append("import java.util.Date;").append("\n\r");
							hasDate = false;
						}
					} else {
						if (metasList.get(i).getDataDefault() != null
								&& !(metasList.get(i).getDataDefault().equals(""))) {
							String str = metasList.get(i).getDataDefault().trim();
							content.append("String "
									+ metasList.get(i).getColumnName() + " = \""
									+ str.substring(1, str.length()-1) + "\";").append("\n");
						} else {
							content.append("String "
									+ metasList.get(i).getColumnName() + ";").append("\n");
						}
						methodContent.append("\tpublic String get").append(metasList.get(i).getColumnName()).append("() {").append("\n");
						methodContent.append("\t\t").append("return ").append(metasList.get(i).getColumnName()).append(";").append("\n");
						methodContent.append("\t}").append("\n");
						
						methodContent.append("\tpublic void set").append(metasList.get(i).getColumnName()).append("(String ").append(toLowerCaseFirstOne(metasList.get(i).getColumnName())).append(") {").append("\n");
						methodContent.append("\t\t").append(metasList.get(i).getColumnName()).append(" = ").append(toLowerCaseFirstOne(metasList.get(i).getColumnName())).append(";").append("\n");
						methodContent.append("\t}").append("\n");
					}
				}
				
				methodContent.append("}");
			}
			if(content != null && methodContent != null && content.length() > 0 && methodContent.length() > 0) {
				filepath=saveAsOutputStreamWriter(tableName+".java",importContent.toString() + content.toString() + methodContent.toString(),getSavePath());
			}else {
				
			}
		} catch (IOException e) {
			logger.error(null, e);
			throw e;
		} catch (DocumentException e) {
			logger.error(null, e);
			throw e;
		}
		return filepath;
	}
	
	protected String toLowerCaseFirstOne(String s)   
    {   
        if(Character.isLowerCase(s.charAt(0)))   
            return s;   
        else  
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();   
    }   
  
	protected String toUpperCaseFirstOne(String s)   
    {   
        if(Character.isUpperCase(s.charAt(0)))   
            return s;   
        else  
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();   
    }
}
