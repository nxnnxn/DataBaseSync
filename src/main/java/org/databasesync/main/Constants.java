package org.databasesync.main;

import java.util.ArrayList;




/**
 * 
 * @ClassName:  Constants
 * @Description:TODO
 * 
 * @author nxn on 2013-3-15
 */
public class Constants {

	public  final static String LOG4JCONFIGPATH="./conf/DataBaseSyncLog4jConfig.properties";
	
	public  final static String DATASYNCCONFIG="./conf/DataBaseSyncServiceConfig.ini";
	
	public  final static String CONFIGEXAMPLEPATH="configExample.xml";
	
	public  final static String HASFOREIGNKEYEXAMPLEPATH="hasForeignKeyExample.xml";
	
	public  final static String MAPCONFIGPATH="./mybatis-config.xml";
	
	public  final static String MAPCONFIGDEFAULTPATH="mybatis-defaultconfig.xml";
	
    public  final static String JAVAPACKAGE = "org.databasesync.autogen";
	
	public  final static String XMLPACKAGE = "org.databasesync.autogen";
	
	public  final static String DBPROPERTIES="./conf/db.properties";
	
    public  final static String[] SQLEXAMPLE={"oracletrigger.sql","sqlservertrigger.sql"};
	
    public  final static int PAGESIZE = 50;
    
	public  final static ArrayList<String> ALLFILEPATH=new  ArrayList<String>();


}
