
package org.databasesync.transdata;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: BaseTransData
 * @Description: 传输内对象用，根据不同的状态转变不同的职能
 * 
 * @author nxn on 2013-3-17
 */
public abstract class BaseTransData implements Cloneable {

	protected Date transDate;

	protected String tableName;

	protected List transData;

	protected TransDataStatus transDataStatus;
	
	protected int totalpage=0;
	

	public static enum TransDataStatus {

		NULLDATA(0),

		GETDATA(1),

		FILEDATA(2),

		ZIPDATA(3);

		private int value;

		private TransDataStatus(int i) {
			this.value = i;
		}

		/**
		 * @return the value
		 */
		public int getValue() {
			return value;
		}

	}

	protected abstract boolean dealWithNullDataStatus();

	protected abstract boolean dealWithGetDataStatus();

	protected abstract boolean dealWithFileDataStatus();

	protected abstract boolean dealWithZipDataStatus();

	public boolean getData() {
		boolean flag = false;
		switch (transDataStatus.getValue()) {
		case 0:
			flag = dealWithNullDataStatus();
			break;
		case 1:
			flag = dealWithGetDataStatus();
			break;
		case 2:
			flag = dealWithFileDataStatus();
			break;
		case 3:
			flag = dealWithZipDataStatus();
			break;
		default:
			break;
		}

		return flag;
	}

	/**
	 * @param transDate the transDate to set
	 */
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @param transData the transData to set
	 */
	public void setTransData(List transData) {
		this.transData = transData;
	}

	/**
	 * @param transDataStatus the transDataStatus to set
	 */
	public void setTransDataStatus(TransDataStatus transDataStatus) {
		this.transDataStatus = transDataStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public BaseTransData clone() throws CloneNotSupportedException {
		BaseTransData baseTransData=null;
		baseTransData=(BaseTransData)super.clone();
		return baseTransData;
	}

	
	
	
}
