package org.databasesync.autogen.sqlmap.impl;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.databasesync.autogen.BaseTable;
import org.databasesync.autogen.Metas;
import org.databasesync.main.Constants;
import org.dom4j.DocumentException;



public class GenExtractXml extends AbstractGenExtractXml {

	Logger logger = Logger.getLogger(this.getClass());
	protected String tableName = null;

	private String interfaceString;

	public GenExtractXml() throws IOException {
		interfaceString = Constants.XMLPACKAGE;
	}

	public String getSavePath() {
		return new File("").getAbsolutePath()+"\\"+interfaceString;
	}

	private String appendContent(Map paramMap, BaseTable table)
			throws IOException {
		String result = "";
		List<Metas> metasList = this.selectMetasByTable(paramMap);
		StringBuilder resultmapsql = new StringBuilder();
		StringBuilder idBuilder=new StringBuilder();
		StringBuilder tablecol = new StringBuilder();
		if (metasList != null && metasList.size() > 0) {
			for (int i = 0; i < metasList.size(); i++) {
				// 生成resultmap
				if (metasList.get(i).getColumnName()
						.equalsIgnoreCase(table.getPrimaryKey())) {
					idBuilder.append("\t\t<id property=\""
							+ metasList.get(i).getColumnName() + "\" column=\""
							+ metasList.get(i).getColumnName() + "\"").append("/>").append("\n");
				} else {
					resultmapsql.append("\t\t<result property=\""
							+ metasList.get(i).getColumnName() + "\" column=\""
							+ metasList.get(i).getColumnName() + "\"");
					resultmapsql.append("/>").append("\n");
				}
				
				// 生成col
				if (i == metasList.size() - 1) {
					tablecol.append("\t\t")
							.append(metasList.get(i).getColumnName())
							.append("\n");
				} else {
					tablecol.append("\t\t")
							.append(metasList.get(i).getColumnName())
							.append(",").append("\n");
				}

			}
			idBuilder.append(resultmapsql);
			StringBuilder insertsql = new StringBuilder();
			StringBuilder updatesql = new StringBuilder();
			for (int i = 0; i < metasList.size(); i++) {
				if (metasList.get(i).getDataType().equals("NUMBER")) {
					if (metasList.get(i).getDataScale() == 0) {
						insertsql.append("\t\t#{")
								.append(metasList.get(i).getColumnName())
								.append(",jdbcType=INTEGER}");
						updatesql.append("\t\t")
								.append(metasList.get(i).getColumnName())
								.append("=#{")
								.append(metasList.get(i).getColumnName())
								.append(",jdbcType=INTEGER}");
					} else {
						insertsql.append("\t\t#{")
								.append(metasList.get(i).getColumnName())
								.append(",jdbcType=NUMERIC}");
						updatesql.append("\t\t")
								.append(metasList.get(i).getColumnName())
								.append("=#{")
								.append(metasList.get(i).getColumnName())
								.append(",jdbcType=NUMERIC}");
					}
				} else if (metasList.get(i).getDataType()
						.equalsIgnoreCase("TIMESTAMP(6)")) {
					insertsql.append("\t\t#{")
							.append(metasList.get(i).getColumnName())
							.append(",jdbcType=TIMESTAMP}");
					updatesql.append("\t\t")
							.append(metasList.get(i).getColumnName())
							.append("=#{")
							.append(metasList.get(i).getColumnName())
							.append(",jdbcType=TIMESTAMP}");
				} else {
					insertsql.append("\t\t#{")
							.append(metasList.get(i).getColumnName())
							.append(",jdbcType=VARCHAR}");
					updatesql.append("\t\t")
							.append(metasList.get(i).getColumnName())
							.append("=#{")
							.append(metasList.get(i).getColumnName())
							.append(",jdbcType=VARCHAR}");
				}
				if (i != metasList.size() - 1) {
					insertsql.append(",").append("\n");
					updatesql.append(",").append("\n");
				}
			}

			if (table.getTableFunction() != null && table.getTableFunction() != null) {
				String content = "";
				if (table.getBillOrEntry().equalsIgnoreCase("bill")) {
					content = this
							.readFile(Constants.CONFIGEXAMPLEPATH);
				} else if (table.getBillOrEntry().equalsIgnoreCase("entry")) {
					content = this
							.readFile(Constants.HASFOREIGNKEYEXAMPLEPATH);
				}
				String sqlCondition="";
				if(table.getSqlCondition()!=null&&!("").equals(table.getSqlCondition()))
			 	{
					sqlCondition=" and "+table.getSqlCondition();
			 	}
				result = content
						.replace("insertvalue", insertsql)
						.replace("entryrelationfield",table.getRelationField())
						.replace("updatevalue", updatesql)
						.replace("interface",
								interfaceString + "." + tableName + "Mapper")
						.replace("tablename", table.getTableName())
						.replace("resultmapsql", idBuilder)
						.replace("sqlconditon", sqlCondition)
						.replace("tabletimekey", table.getTimeStampField())
						.replace("tablecol", tablecol)
						.replace("tablekey", table.getPrimaryKey());

			}
		}
		return result;

	}

	@Override
	public String genExtractXml(Map paramMap, BaseTable table)
			throws DocumentException, IOException {
		String filename = "";
		try {

			tableName = table.getTableName().toUpperCase();
			String content = appendContent(paramMap, table);
			if (content != null && content.length() > 0) {
				filename = saveAsOutputStreamWriter(tableName + ".xml",
						content.toString(), getSavePath());
			} else {
		
				System.exit(0);
			}
		} catch (IOException e) {
			logger.error(null, e);
			throw e;
		}
		return filename;
	}

}
