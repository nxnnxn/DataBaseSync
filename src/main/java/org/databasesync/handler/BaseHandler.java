package org.databasesync.handler;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;
import org.databasesync.transdata.BaseTransData;
import org.databasesync.transdata.TransQueue;




/**
 * 
 * @ClassName:  BaseHandler
 * @Description:添加公共表上传任务,传输公共表
 * 
 * @author nxn on 2013-3-12
 */
public abstract class BaseHandler implements Runnable {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	private AtomicBoolean isRun=new AtomicBoolean(true);
	
    private Object obj=new Object();
    
    private ArrayList<BaseTransData> list=new ArrayList<BaseTransData>();
   
	
	@Override
	public void run() {
		
		
		while(this.isRun.get())
		{
			 synchronized (TransQueue.COMMONTABLENAMEOBJ) {
				if(TransQueue.COMMONTABLENAMEQUEUE.isEmpty())
				{
					TransQueue.COMMONTABLENAMEQUEUE.addAll(list);
					TransQueue.COMMONTABLENAMEOBJ.notifyAll();
				}
			}
			 try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

	
	
	

}
