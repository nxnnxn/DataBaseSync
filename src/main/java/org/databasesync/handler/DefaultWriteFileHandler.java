
package org.databasesync.handler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;
import org.databasesync.transdata.BaseTransData;



/**
 * @ClassName: DefaultWriteFileHandler
 * @Description:TODO
 * 
 * @author nxn on 2013-3-17
 */
public class DefaultWriteFileHandler implements Runnable {

	Logger logger = Logger.getLogger(this.getClass());

	private AtomicBoolean isRun = new AtomicBoolean(true);

	private Object obj = new Object();

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	private ConcurrentLinkedQueue<BaseTransData> queue = new ConcurrentLinkedQueue<BaseTransData>();

	public void addQueue(List<BaseTransData> list) {
		this.queue.addAll(list);
		this.obj.notifyAll();
	}

	public void addQueue(BaseTransData baseTransData) {
		this.queue.add(baseTransData);
		this.obj.notifyAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		while (isRun.get()) {
			BaseTransData data = null;
			synchronized (this.obj) {
				if (this.queue.isEmpty()) {
					try {
						this.obj.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						continue;
					}
				} else {
					data = this.queue.poll();
				}
				if (data != null) {
					data.getData();
				}
			}
		}
	}

}
