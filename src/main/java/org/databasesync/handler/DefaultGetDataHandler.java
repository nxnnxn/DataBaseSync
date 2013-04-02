
package org.databasesync.handler;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.databasesync.transdata.BaseTransData;
import org.databasesync.transdata.TransQueue;



/**
 * @ClassName: DefaultGetDataHandler
 * @Description:TODO
 * 
 * @author nxn on 2013-3-8
 */
public class DefaultGetDataHandler implements Runnable {

	Logger logger = Logger.getLogger(this.getClass());

	private AtomicBoolean isRun = new AtomicBoolean(true);

	private Object obj = new Object();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		DefaultWriteFileHandler defaultWriteFileHandler = new DefaultWriteFileHandler();

		while (this.isRun.get()) {
			BaseTransData data = null;
			synchronized (TransQueue.COMMONTABLENAMEOBJ) {
				if (TransQueue.COMMONTABLENAMEQUEUE.isEmpty()) {
					try {
						TransQueue.COMMONTABLENAMEOBJ.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					data = TransQueue.COMMONTABLENAMEQUEUE.poll();
				}
			}
			if (data != null) {
				while (data.getData()) {
					try {
					BaseTransData baseTransData = data.clone();
						defaultWriteFileHandler.addQueue(baseTransData);
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} else {
				logger.error("error");
			}

		}

	}

}
