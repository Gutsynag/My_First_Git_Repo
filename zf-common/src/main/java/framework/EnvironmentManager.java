package framework;

import java.io.IOException;
import java.util.Properties;


/**
 * This class having the config properties methods
 * 
 */

public class EnvironmentManager {

	/*
	 * properties declaration
	 *
	 */ 
	private static Properties properties;  

	/*
	 * This method returns the URL value
	 */
	public static String getUrl() {
		return properties.getProperty("URL");
	}

	/*
	 * This method returns the Browser value
	 */

	public static String getBrowserName() {
		return properties.getProperty("BROWSER");
	}

	/*
	 * This method returns the TIMEOUT value
	 */

	public static long getDelayTime() {
		return  Long.parseLong(properties.getProperty("TIMEOUT"));
	}

	/*
	 * This method returns the Username value
	 */

	public static String getUserName() {
		return properties.getProperty("USERNAME");
	}

	/*
	 * This method returns the Password value
	 */

	public static String getPassword() {
		return properties.getProperty("PASSWORD");
	}

	/*
	 * This method returns the Sender email id value
	 */
	public static String getEmailId() {
		return properties.getProperty("SENDEREMAILID");
	}

	/*
	 * This method returns the excel data path
	 */
	public static String getDataFromExcelPath() {
		return properties.getProperty("MASTERDATAEXCELFILE");
	}

	/*
	 * This method returns the JSON data path
	 */
	public static String getDataFromJsonPath() {
		return properties.getProperty("MASTERDATAJSONFILE");
	}
	/*
	 * This method returns the Report path
	 */
	public static String getReportPath() {
		return properties.getProperty("REPORTPATH");
	}
	
	/*
	 * This method returns the certificate path
	 */
	public static String getCertificatePath() {
		return properties.getProperty("CERTIFICATEPATH");
	}

	/*
	 * This method returns the Screenshot path
	 */
	public static String getScreenShotPath() {
		return properties.getProperty("SCREENSHOTPATH");
	}

	/*
	 * This method returns the Common Slash
	 */
	public static String getCommonSlash() {
		return properties.getProperty("COMMONSLASH");
	}

	/*
	 * This method returns the .HTML
	 */
	public static String getDotHtml() {
		return properties.getProperty("DOTHTML");
	}
	/*
	 * This method returns the .PNG
	 */
	public static String getDotPng() {
		return properties.getProperty("DOTPNG");
	}

	/*
	 * This method returns the ReportName
	 */
	public static String getReportName() {
		return properties.getProperty("REPORTNAME");
	}

	/*
	 * This method returns the ReportName
	 */
	public static String getReportTitle() {
		return properties.getProperty("REPORTTITLE");
	}

	/*
	 * This method returns the DateFormat
	 */
	public static String getDateForamt() {
		return properties.getProperty("DATEFORMAT");
	}

	/*
	 * This method returns the Test Data SheetName
	 */
	public static String getTestDataSheetName() {
		return properties.getProperty("TESTDATASHEET");
	}
	
			
	/*
	 * This method returns the MySql Server KEY
	 */
	public static String getMySqlServer() {
		return properties.getProperty("MYSQLSERVER");
	}
	
	/*
	 * This method returns the MySql Driver
	 */
	public static String getMySqlDriver() {
		return properties.getProperty("MYSQLDRIVER");
	}
	
	/*
	 * This method returns the MySql Jdbc driver string
	 */
	public static String getMySqlJdbc() {
		return properties.getProperty("MYSQLJDBC");
	}
	
	/*
	 * This method returns the MySql Host
	 */
	public static String getMySqlHost() {
		return properties.getProperty("MYSQLHOST");
	}
	
	/*
	 * This method returns the MySql Port
	 */
	public static String getMySqlPort() {
		return properties.getProperty("MYSQLPORT");
	}
	
	/*
	 * This method returns the MySql Database
	 */
	public static String getMySqlDatabase() {
		return properties.getProperty("MYSQLDATABASE");
	}
	
	/*
	 * This method returns the MySql User
	 */
	public static String getMySqlUser() {
		return properties.getProperty("MYSQLUSER");
	}
	
	/*
	 * This method returns the MySql Password
	 */
	public static String getMySqlPassword() {
		return properties.getProperty("MYSQLPASSWORD");
	}	
	

	/*
	 * This method returns the Sql Server key
	 */
	public static String getSqlServer() {
		return properties.getProperty("SQLSERVER");
	}
	
	/*
	 * This method returns the Sql Server Driver
	 */
	public static String getSqlServerDriver() {
		return properties.getProperty("SQLSERVERDRIVER");
	}
	
	/*
	 * This method returns the Sql Server Jdbc driver string
	 */
	public static String getSqlServerJdbc() {
		return properties.getProperty("SQLSERVERJDBC");
	}
	
	/*
	 * This method returns the Sql Server Host
	 */
	public static String getSqlServerHost() {
		return properties.getProperty("SQLSERVERHOST");
	}
	
	/*
	 * This method returns the Sql Server Port
	 */
	public static String getSqlServerPort() {
		return properties.getProperty("SQLSERVERPORT");
	}
	
	/*
	 * This method returns the Sql Server Database
	 */
	public static String getSqlServerDatabase() {
		return properties.getProperty("SQLSERVERDATABASE");
	}
	
	/*
	 * This method returns the Sql Server User
	 */
	public static String getSqlServerUser() {
		return properties.getProperty("SQLSERVERUSER");
	}

	/*
	 * This method returns the Sql Server User
	 */
	public static String getSqlServerPassword() {
		return properties.getProperty("SQLSERVERPASSWORD");
	}



	/*
	 * Static block to initializing the properties method
	 */
	static {
		properties = new Properties();
		try {
			properties.load(EnvironmentManager.class.getClassLoader().getResourceAsStream("config/config.properties"));
		} catch (IOException e) {
			TestLogger.appInfo("Exception was thrown at EnvironmentManager class - static block" + e.getMessage());
			e.printStackTrace();
		}
	}
}