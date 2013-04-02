package org.databasesync.autogen;

import java.util.List;




public class TransConfig extends BaseTable{
	
  
    
    private String  tableType;
    
	
    private List<TransHasForeignKeyConfig> entryList;
    

	/**
	 * @return the entryList
	 */
	public List<TransHasForeignKeyConfig> getEntryList() {
		return entryList;
	}

	/**
	 * @param entryList the entryList to set
	 */
	public void setEntryList(List<TransHasForeignKeyConfig> entryList) {
		this.entryList = entryList;
	}

	
	@Override
	public String getBillOrEntry() {
		// TODO Auto-generated method stub
		return "Bill";
	}


	/**
	 * @return the tableType
	 */
	public String getTableType() {
		return tableType;
	}

	/**
	 * @param tableType the tableType to set
	 */
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	
    
   
	
}
