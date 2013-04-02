
package org.databasesync.transdata;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;


import org.apache.ibatis.session.SqlSession;
import org.databasesync.main.Constants;
import org.databasesync.util.SqlSessionFactoryUtil;
import org.databasesync.util.XmlSerializable;
import org.dom4j.DocumentException;



/**
 * @ClassName: DefaultTransData
 * @Description:TODO
 * 
 * @author xiangnan on 2013-3-30
 */
public class DefaultTransData extends BaseTransData {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	@Override
	protected boolean dealWithNullDataStatus() {

		SqlSession session = SqlSessionFactoryUtil.getInstance().openSession();
		HashMap<String, String> map = new HashMap<String, String>();
		int count = session.selectOne(Constants.XMLPACKAGE + "."
				+ this.tableName + "Mapper.countByConditions", map);
		// 分页取出数据
		this.totalpage = (count + Constants.PAGESIZE) / Constants.PAGESIZE;
		session.close();
		return true;
	}

	
	@Override
	protected boolean dealWithGetDataStatus() {
		// TODO Auto-generated method stub

		SqlSession session = SqlSessionFactoryUtil.getInstance().openSession();
		HashMap<String, String> map = new HashMap<String, String>();
		// map.put(arg0, arg1);
		if(this.totalpage>0)
		{
			this.transData = session.selectList(Constants.XMLPACKAGE + "."
					+ this.tableName + "Mapper.countByConditions", map);
			this.totalpage--;
			this.transDataStatus=TransDataStatus.GETDATA;
		}else {
			this.transDataStatus=TransDataStatus.GETDATA;
		}
		session.close();
	
		return true;
	}

	
	@Override
	protected boolean dealWithFileDataStatus() {
		String fileName=dateFormat.format(this.transData)+"_"+this.tableName;
		File file=new File(fileName);
		try {
			XmlSerializable xmlSerializable=new XmlSerializable(fileName);
			for(int i=0;i<this.transData.size();i++)
			{
				xmlSerializable.add(this.transData.get(i));
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	
	@Override
	protected boolean dealWithZipDataStatus() {
		// TODO Auto-generated method stub
		return true;
	}

}
