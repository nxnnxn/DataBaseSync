package org.databasesync.autogen;

/**
 * 
 * @ClassName:  BaseTable
 * @Description:TODO
 * 
 * @author nxn on 2013-3-15
 */
public class BaseTable {
	
	private String  id;
	private String  tableName;
	private Integer priority;
    private String  description;
    private String  sqlCondition="";
    private String  primaryKey;
    private String  timeStampField="";
    private String  tableFunction;
    private String  relationField="";
    private String  billOrEntry;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the sqlCondition
	 */
	public String getSqlCondition() {
		return sqlCondition;
	}
	/**
	 * @param sqlCondition the sqlCondition to set
	 */
	public void setSqlCondition(String sqlCondition) {
		this.sqlCondition = sqlCondition;
	}
	
	/**
	 * @return the primaryKey
	 */
	public String getPrimaryKey() {
		return primaryKey;
	}
	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	/**
	 * @return the billOrEntry
	 */
	public String getBillOrEntry() {
		return billOrEntry;
	}
	/**
	 * @param billOrEntry the billOrEntry to set
	 */
	public void setBillOrEntry(String billOrEntry) {
		this.billOrEntry = billOrEntry;
	}
	/**
	 * @return the tableFunction
	 */
	public String getTableFunction() {
		return tableFunction;
	}
	/**
	 * @param tableFunction the tableFunction to set
	 */
	public void setTableFunction(String tableFunction) {
		this.tableFunction = tableFunction;
	}
	/**
	 * @return the timeStampField
	 */
	public String getTimeStampField() {
		return timeStampField;
	}
	/**
	 * @param timeStampField the timeStampField to set
	 */
	public void setTimeStampField(String timeStampField) {
		this.timeStampField = timeStampField;
	}
	public String getRelationField() {
		return relationField;
	}
	public void setRelationField(String relationField) {
		this.relationField = relationField;
	}

	
    
	

}
