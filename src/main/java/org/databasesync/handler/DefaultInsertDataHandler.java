
package org.databasesync.handler;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;



/**
 * @ClassName:  DefaultInsertDataHandler
 * @Description:TODO
 * 
 * @author nxn on 2013-3-8
 */
public class DefaultInsertDataHandler implements Runnable{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	private AtomicBoolean isRun=new AtomicBoolean(true);
	
    private Object obj=new Object();
   
	
	@Override
	public void run() {
		
		
	}

	
	
	


}
