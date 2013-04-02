
package org.databasesync.transdata;

import java.util.concurrent.ConcurrentLinkedQueue;



/**
 * @ClassName:  TransQueue
 * @Description:TODO
 * 
 * @author nxn on 2013-3-17
 */
public class TransQueue {
	
	public  final static ConcurrentLinkedQueue<BaseTransData> COMMONTABLENAMEQUEUE=new  ConcurrentLinkedQueue<BaseTransData>();
	
	public  final static  Object COMMONTABLENAMEOBJ=new  Object();
	
	public  final static ConcurrentLinkedQueue<BaseTransData> FILETRANSDATAQUEUE=new  ConcurrentLinkedQueue<BaseTransData>();
	
	public  final static  Object FILETRANSDATAOBJ=new  Object();


}
