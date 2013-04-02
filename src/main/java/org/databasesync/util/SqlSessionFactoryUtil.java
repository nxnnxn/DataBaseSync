package org.databasesync.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.databasesync.main.Constants;



/**
 * 
 * @ClassName:  SqlMapUtil
 * @Description:TODO
 * 
 * @author nxn on 2013-3-12
 */
public class SqlSessionFactoryUtil {

	Logger logger = Logger.getLogger(this.getClass());
	

	
	private static SqlSessionFactory sqlSessionFactory;
	
	/**
	 * 
	 * @Description:TODO
	 *
	 * @param database
	 * @return
	 * @throws IOException
	 * @author nxn on 2013-3-13
	 */
	public static synchronized SqlSessionFactory getDefalultInstance() {
		if(sqlSessionFactory==null)
		{
			InputStream inputStream = null;
			SqlSessionFactoryBuilder builder = null;
		    try {
				inputStream = Resources.getResourceAsStream(Constants.MAPCONFIGDEFAULTPATH);
				builder = new SqlSessionFactoryBuilder(); 
				sqlSessionFactory = builder.build(inputStream); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(-1);
			}finally
			{
				if(inputStream!=null)
				{
					try {
						inputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.exit(-1);
					}
				}
			}
			
		}
		return sqlSessionFactory;
	}
	
	public static synchronized SqlSessionFactory getInstance() {
		if(sqlSessionFactory==null)
		{
			InputStream inputStream = null;
			SqlSessionFactoryBuilder builder = null;
		    try {
				inputStream = Resources.getResourceAsStream(Constants.MAPCONFIGPATH);
				builder = new SqlSessionFactoryBuilder(); 
				sqlSessionFactory = builder.build(inputStream); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(-1);
			}finally
			{
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(-1);
				}
			}
			
		}
		return sqlSessionFactory;
	}
	
	public static synchronized void loseConnection(){
		sqlSessionFactory=null;
	}
	
	
}
