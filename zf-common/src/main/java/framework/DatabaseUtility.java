package framework;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import framework.EnvironmentManager;
import framework.TestLogger;



public class DatabaseUtility {

	
	private static Map<String, Connection> dbConnections = new HashMap<String, Connection>();
	
	public DatabaseUtility() {			
		
	}
	
	/*
	 * ****************************************************************************** 
	 * Name : getConnection 
	 * Parameters : dbname (Database Name) 
	 * Purpose : To establish the connection with the specified database
	 * ******************************************************************************
	*/
	
	public static Connection getConnection(String dbName) throws Exception {
		Connection conn = null;
		/*
		 * String mysql = EnvironmentManager.getMySqlServer(); String sqlserver =
		 * EnvironmentManager.getSqlServer();
		 */
				
        switch (dbName) {        
        
	        case "mysql" :
	        	try {
		        	if(dbConnections.get(dbName.trim()) == null) {
		        		TestLogger.appInfo("Registering the MYSQL Connection string : "+EnvironmentManager.getMySqlDriver());
		        		Class.forName(EnvironmentManager.getMySqlDriver());            	
		            	String tmpmysql = EnvironmentManager.getMySqlJdbc();
		            	String host = EnvironmentManager.getMySqlHost();
		            	String port = EnvironmentManager.getMySqlPort();
		            	String dbname = EnvironmentManager.getMySqlDatabase();
		            	String user = EnvironmentManager.getMySqlUser();
		            	String password = EnvironmentManager.getMySqlPassword();
		            	String mysqlstring = tmpmysql+host+":"+port+"/"+dbname+"?useSSL=false&allowPublicKeyRetrieval=true";
		            	TestLogger.appInfo("MYSQL connection string is : "+ mysqlstring);
		            	conn = DriverManager.getConnection(mysqlstring,user,password);
		        		dbConnections.put(dbName.trim(), conn); 
		        		TestLogger.appInfo("Connection with MYSql is established"+dbConnections.get(dbName.trim()));
	        		}
	        	}catch(Exception e) {
	        		TestLogger.errorMessage("An error has occured while establishing connection with mysql server" + e.getMessage());
	        		dbConnections.put(dbName.trim(), null);
	        	}
	        	break;
	        case "sqlserver" :
	        	try {
		        	if(dbConnections.get(dbName.trim()) == null) {
		        		TestLogger.appInfo("Registering the SQLServer Connection string : "+EnvironmentManager.getSqlServerDriver());
		        		Class.forName(EnvironmentManager.getSqlServerDriver());
		        		    
		            	String tmpsqlserver = EnvironmentManager.getSqlServerJdbc();
		            	String host = EnvironmentManager.getSqlServerHost();
		            	String dbname = EnvironmentManager.getSqlServerDatabase();
		            	String user = EnvironmentManager.getSqlServerUser();
		            	String password = EnvironmentManager.getSqlServerPassword();
		            	String sqlserverstring = tmpsqlserver+host+";instanceName=SQLEXPRESS;databaseName="+dbname;
		            	//String sqlserverstring = tmpsqlserver+host+":"+port+";DatabaseName="+dbname;
		            	conn = DriverManager.getConnection(sqlserverstring,user,password);
		        		dbConnections.put(dbName.trim(), conn); 
		        		TestLogger.appInfo("Connection with SQL Server is established");
	        		}
	        	}catch(Exception e) {
	        		TestLogger.errorMessage("An error has occured while establishing connection with sqlserver server"+e.getMessage());
	        		dbConnections.put(dbName.trim(), null);
	        	}
	        	break;
	        default:
	        	TestLogger.errorMessage("No Matching database server found !!!");
	        	dbConnections.put(dbName, null); 
        	}  
        	TestLogger.appInfo("Database Connection returned for database "+dbName+" is : "+dbConnections.get(dbName));
        	       	
        	return dbConnections.get(dbName);
		}
	
	/*
	 * ****************************************************************************** 
	 * Name : getSelectQueryResult 
	 * Parameters : con (Connection instance) ,query(select sql query string)
	 * Purpose : To return the list of selected rows filterd by selected select query
	 * ******************************************************************************
	*/
	public static List<String> getSelectQueryResult(Connection con,String query)  {
		
		
		Statement stmt = null;
		ResultSet resultset = null;	
		ResultSetMetaData rsmd = null;
		int colCount = 0;
		StringBuilder resultbuilder = null;
		List<String> list = null;
		
		
		try {
			list = new ArrayList<String>();
			try {
				stmt = con.createStatement();
				TestLogger.appInfo("Statement Instance Created successfull ");
			}catch(Exception e) {
				TestLogger.errorMessage("Failed to create Statement Instance "+e.getMessage());
			}
			try {
				resultset = stmt.executeQuery(query);	
				TestLogger.appInfo("Resultset Instance Created successfull ");
			}catch(Exception e) {
				TestLogger.errorMessage("Failed to create Resultset Instance "+e.getMessage());
			}
			rsmd=resultset.getMetaData();
			colCount = rsmd.getColumnCount();
			int loop = 1;
			while(resultset.next()) {
				resultbuilder = new StringBuilder();
				while(loop <=colCount) {
					resultbuilder.append(resultset .getString(loop));
					if((loop+1) > colCount) {
						list.add(resultbuilder.toString());
						resultbuilder = null;
						break;
					}				
					resultbuilder.append(" ");
					loop++;
				}
				loop = 1;
			}
			
		}catch(Exception e) 
		{	
			TestLogger.errorMessage("An exception has occured while executing select query : "+e.getMessage());
			list = null;
		}
		finally {
			if(resultset!=null) {
				try{
					resultset.close();		
					TestLogger.appInfo("Closing the ResultSet Instance");
				}catch(Exception e) {
					TestLogger.errorMessage("An exception has occured while closing the Resultset instance "+e.getMessage());
				}
			}
			if(stmt!=null) {
				try{
					stmt.close();	
					TestLogger.appInfo("Closing the Statement Instance");
				}catch(Exception e) {
					TestLogger.errorMessage("An exception has occured while closing the Statement instance "+e.getMessage());
				}
			}
		}
		return list;
	}
	
	/*
	 * ****************************************************************************** 
	 * Name : insertQuery 
	 * Parameters : con (Connection instance) ,query(Insert sql query string)
	 * Purpose : To insert the record specified in query string
	 * ******************************************************************************
	*/	
	
	public static boolean insertQuery(Connection con,String query) {
		Statement stmt = null;
		int rowsInserted = 0;
		boolean result = false;
		try {			
			try {
				stmt = con.createStatement();
				TestLogger.appInfo("Statement Instance Created successfull ");
			}catch(Exception e) {
				TestLogger.errorMessage("Failed to create Statement instance "+e.getMessage());
			}
			rowsInserted = stmt.executeUpdate(query);
			TestLogger.appInfo("Rows Inserted are : "+rowsInserted);			
			if(rowsInserted > 0) {
				result = true;
			}			
			
		}catch(Exception e) {
			TestLogger.errorMessage("An exception has occured while inserting the records "+e.getMessage());	
		}
		finally {			
			if(stmt!=null) 
				try{
					stmt.close();	
					TestLogger.appInfo("Closing the Statement Instance");
				}catch(Exception e) {
					TestLogger.errorMessage("An exception has occured while closing the Statement instance "+e.getMessage());
				}
		}
		return result;
	}
	
	/*
	 * ****************************************************************************** 
	 * Name : createTableQuery 
	 * Parameters : con (Connection instance) ,query(Create table sql query string)
	 * Purpose : To create the table specified in query string
	 * ******************************************************************************
	*/	
	
	public static boolean createTableQuery(Connection con,String query) {
		Statement stmt = null;
		boolean result = false;
		try {
			
			try {
				stmt = con.createStatement();
				TestLogger.appInfo("Statement Instance Created successfull ");
			}catch(Exception e) {
				TestLogger.errorMessage("Failed to create Statement instance "+e.getMessage());
			}
			stmt.executeUpdate(query);
			result = true;
			TestLogger.appInfo("Table created successfully");
			
		}catch(Exception e) {	
			
			TestLogger.errorMessage("Exception generated while creating table  "+e.getMessage());
			result = false;
		}
		finally {			
			if(stmt!=null) {			
				try{
					stmt.close();	
					TestLogger.appInfo("Closing the Statement Instance");
				}catch(Exception e) {
					TestLogger.errorMessage("An exception has occured while closing the Statement instance " +e.getMessage());
				}		
			}
		}
		return result;
	}
	
	/*
	 * ****************************************************************************** 
	 * Name : dropTableQuery 
	 * Parameters : con (Connection instance) ,query(Drop table sql query string)
	 * Purpose : To drop the table specified in query string
	 * ******************************************************************************
	*/	
	
	public static boolean dropTableQuery(Connection con,String query)  {
		Statement stmt = null;		
		boolean result = false;
		try {
			
			try {
				stmt = con.createStatement();
				TestLogger.appInfo("Statement Instance Created successfull ");
			}catch(Exception e) {
				TestLogger.errorMessage("Failed to create Statement instance "+e.getMessage());
			}
			stmt.executeUpdate(query);
			result = true;
			TestLogger.appInfo("Table dropped successfully");
			
		}catch(Exception e) {	
			TestLogger.errorMessage("Exception generated while dropping table "+e.getMessage());
			result = false;
		}
		finally {			
			if(stmt!=null) {			
				try{
					stmt.close();	
					TestLogger.appInfo("Closing the Statement Instance");
				}catch(Exception e) {
					TestLogger.errorMessage("An exception has occured while closing the Statement instance "+e.getMessage());
				}		
			}
		}
		return result;
	}
	
	/*
	 * ****************************************************************************** 
	 * Name : updateQuery 
	 * Parameters : con (Connection instance) ,query(update sql query string)
	 * Purpose : To update the record specified in query string
	 * ******************************************************************************
	*/		
	
	public static boolean updateQuery(Connection con,String query)  {
		Statement stmt = null;
		int rowsUpdated = 0;
		boolean result = false;
		try {
			
			try {
				stmt = con.createStatement();
				TestLogger.appInfo("Statement Instance Created successfull ");
			}catch(Exception e) {
				TestLogger.errorMessage("Failed to create Statement instance "+e.getMessage());
			}
			
			rowsUpdated = stmt.executeUpdate(query);
			TestLogger.appInfo("Rows updated are : "+rowsUpdated);
			
			if(rowsUpdated > 0) {
				result = true;
			}			
			
		}catch(Exception e) {	
			TestLogger.errorMessage("Exception generated while Updating records "+e.getMessage());
		}
		finally {			
			if(stmt!=null) {			
				try{
					stmt.close();	
					TestLogger.appInfo("Closing the Statement Instance");
				}catch(Exception e) {
					TestLogger.errorMessage("An exception has occured while closing the Statement instance "+e.getMessage());
				}			
			}
		}
		return result;
	}
	
	 
	
}