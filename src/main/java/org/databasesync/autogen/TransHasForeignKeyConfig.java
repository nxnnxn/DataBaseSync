package org.databasesync.autogen;


public class TransHasForeignKeyConfig extends BaseTable {
	
    private String  parentId;
    

   
	 
	@Override
	public String getBillOrEntry() {
		// TODO Auto-generated method stub
		return "entry";
	}


	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}


	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}





	
    
}
