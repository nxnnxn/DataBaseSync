package org.databasesync.autogen.sql.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.databasesync.autogen.BaseTable;
import org.databasesync.main.Constants;
import org.dom4j.DocumentException;



public class GenTotalSqls extends AbstractGenSqls {

	Logger logger = Logger.getLogger(this.getClass());
	private int triggerSequence = 1; 

	@Override
	public String getSavePath() {
		return new File("").getAbsolutePath();
	}

	@Override
	public String[] genSqls(List<BaseTable> fromCenterTableList)
			throws DocumentException, IOException {
		String[] filename = new String[Constants.SQLEXAMPLE.length];
		try {
			List<String> content = appendContent(fromCenterTableList);
			for (int i = 0; i < Constants.SQLEXAMPLE.length; i++) {
				filename[i] = saveAsOutputStreamWriter("rt_datasync_trans"
						+ Constants.SQLEXAMPLE[i], content.get(i),
						getSavePath());
			}

		} catch (DocumentException e) {
			logger.error(null, e);
			throw e;
		} catch (IOException e) {
			logger.error(null, e);
			throw e;
		}
		return filename;
	}

	private String getTriggerName(String tableName) {
		String res = null;
		if (tableName.length() > 24) {
			res = "T"
					+ triggerSequence++
					+ "_"
					+ tableName
							.substring((tableName.length() - 20 > 0) ? tableName
									.length() - 20 : 0) + "SYNCTR";
		} else {
			res = tableName + "SYNCTR";
		}
		return res;
	}

	public List<String> appendContent(List<BaseTable> fromCenterTableList)
			throws FileNotFoundException, IOException {

		List<String> content = new ArrayList<String>();
		for (int i = 0; i < Constants.SQLEXAMPLE.length; i++) {
			StringBuilder result = new StringBuilder();
			String exampletrg = this.readFile(Constants.SQLEXAMPLE[i]);
			for (int j = 0; j < fromCenterTableList.size(); j++) {
				String trgtemp = exampletrg;
				trgtemp = trgtemp
						.replace("tablename",
								fromCenterTableList.get(j).getTableName())
						.replace(
								"triggername",
								getTriggerName(fromCenterTableList.get(j)
										.getTableName()))
						.replace("tablekey",
								fromCenterTableList.get(j).getPrimaryKey());
				if (("bill").equalsIgnoreCase(fromCenterTableList.get(j)
						.getBillOrEntry())) {
					trgtemp.replace("tabletype", "0");
				} else if (("entry").equalsIgnoreCase(fromCenterTableList
						.get(j).getBillOrEntry())) {
					trgtemp.replace("tabletype", "1");
				} else {
					logger.error("error");
				}
				result.append(trgtemp);
			}
			content.add(result.toString());
		}

		return content;
	}
}
