package org.databasesync.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.databasesync.autogen.BaseTable;
import org.databasesync.autogen.TransConfig;
import org.databasesync.autogen.TransHasForeignKeyConfig;
import org.databasesync.main.Constants;


/**
 * 
 * @ClassName: ConstantsUtil
 * @Description:TODO
 * 
 * @author nxn on 2013-3-15
 */
public class ConstantsUtil {

	private static Logger logger = Logger.getLogger(ConstantsUtil.class);

	public static void IfNullDeleteFile(String filepath) {
		if (filepath != null && !filepath.equals("")) {
			Constants.ALLFILEPATH.add(filepath);
		} else {
			logger.info("SavePath is error,so delete Files");
			for (String str : Constants.ALLFILEPATH) {
				File file = new File(str);
				file.delete();
			}
			System.exit(-1);
		}
	}
	
	public static void IfNullDeleteFile(String[] filepath) {
		if (filepath != null) {
			for(String str:filepath)
			{
				Constants.ALLFILEPATH.add(str);
			}
		} else {
			logger.info("SavePath is error,so delete Files");
			for (String str : Constants.ALLFILEPATH) {
				File file = new File(str);
				file.delete();
			}
			System.exit(-1);
		}
	}

	public static List<BaseTable> selectBaseTable(Map map) throws IOException {
		List<BaseTable> baseTable = new ArrayList<BaseTable>();
		List<TransConfig> transConfigList=selectTransConfigTable(map);
		baseTable.addAll(transConfigList);
		for(TransConfig transBillConfig:transConfigList)
		{
			List<TransHasForeignKeyConfig> transEntryConfigList=selectTransEntryConfigTable(transBillConfig.getId());
			for(TransHasForeignKeyConfig transHasForeignKeyConfig:transEntryConfigList)
			{
				//transEntryConfig.setTableFunction(ConfigureUtil.getInstance().getFunction());
			}
			baseTable.addAll(transEntryConfigList);
		}
		
		return baseTable;
	}

	public static List<TransConfig> selectTransConfigTable(Map map)
			throws IOException {

		List<TransConfig> transBillConfigList = new ArrayList<TransConfig>();
		SqlSession session = SqlSessionFactoryUtil.getDefalultInstance().openSession();
		transBillConfigList = session
				.selectList(
						"",
						map);
		session.close();
		return transBillConfigList;
	}

	public static List<TransHasForeignKeyConfig> selectTransEntryConfigTable(String parentId)
			throws IOException {

		List<TransHasForeignKeyConfig> transEntryConfigList = new ArrayList<TransHasForeignKeyConfig>();
		SqlSession session = SqlSessionFactoryUtil.getDefalultInstance().openSession();
		transEntryConfigList = session
				.selectList(
						"",
						parentId);
		session.close();
		return transEntryConfigList;
	}
}
