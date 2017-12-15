package cellos.bigdata.mapr.ra.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class RaBackEnd {
	private static String reportpath, refdir, refdatafile, driverName, downloaddir, url, username, password, colhead, createdfilnename, filename;
	private boolean istrue;   
	protected static Connection con;
	protected static Statement stmt;
	protected static String sql;
	protected static ResultSet res, res1;
	
	
	
	private List<String> headerlist;
	
	static Logger log = LogManager.getLogger(RaBackEnd.class.getName());
	protected Assertion hardAssert = new Assertion();
	protected SoftAssert softAssert = new SoftAssert();
	ExtentReports report;
	ExtentTest test;
	
	@Parameters("session_key")
	@BeforeTest(groups = { "all_test", "bi_presentation" , "gap_volume_daily", "gap_volume", "ai_dimension", "evidence", "ai_dep_presentation" })
	  public void beforeTest(String sessionkey) throws Exception {
		log.debug("Starting beforetest ..........");
		log.debug("Classpath is: - " + System.getProperty("user.dir"));
		log.debug("Setting directory paths .........");
		
		
		switch (sessionkey) {
		case "key1": 
			log.debug("Session Key is 1");
			reportpath = System.getProperty("user.dir") + "\\reports\\back\\key1\\"; 
			log.debug("Test report path is set to - " + reportpath);
			refdir = System.getProperty("user.dir") + "\\referencedata\\back\\key1\\";
			log.debug("Reference report path is set to - " + refdir);
			downloaddir = System.getProperty("user.dir") + "\\downloaddir\\back\\key1\\";
			log.debug("Downloaded report path is set to - " + downloaddir);
			report = new ExtentReports(reportpath + "Key1TestReport.html");
			test = report.startTest("Back end testing with session key 1");
			test.log(LogStatus.INFO, "Testing started with the session key 1");
			log.debug("Testing started with the session key 1");
			break;
			
		case "key2": 
			log.debug("Session Key is 2");
			reportpath = System.getProperty("user.dir") + "\\reports\\back\\key2\\"; 
			log.debug("Test report path is set to - " + reportpath);
			refdir = System.getProperty("user.dir") + "\\referencedata\\back\\key2\\";
			log.debug("Reference report path is set to - " + refdir);
			downloaddir = System.getProperty("user.dir") + "\\downloaddir\\back\\key2\\";
			log.debug("Downloaded report path is set to - " + downloaddir);
			report = new ExtentReports(reportpath + "Key2TestReport.html");
			test = report.startTest("Back end testing with session key 2");
			test.log(LogStatus.INFO, "Testing started with the session key 2");
			log.debug("Testing started with the session key 2");
			break;

		case "key3": 
			log.debug("Session Key is 3");
			reportpath = System.getProperty("user.dir") + "\\reports\\back\\key3\\"; 
			log.debug("Test report path is set to - " + reportpath);
			refdir = System.getProperty("user.dir") + "\\referencedata\\back\\key3\\";
			log.debug("Reference report path is set to - " + refdir);
			downloaddir = System.getProperty("user.dir") + "\\downloaddir\\back\\key3\\";
			log.debug("Downloaded report path is set to - " + downloaddir);
			report = new ExtentReports(reportpath + "Key3TestReport.html");
			test = report.startTest("Back end testing with session key 3");
			test.log(LogStatus.INFO, "Testing started with the session key 3");
			log.debug("Testing started with the session key 3");
			break;

		case "key4": 
			log.debug("Session Key is 4");
			reportpath = System.getProperty("user.dir") + "\\reports\\back\\key4\\"; 
			log.debug("Test report path is set to - " + reportpath);
			refdir = System.getProperty("user.dir") + "\\referencedata\\back\\key4\\";
			log.debug("Reference report path is set to - " + refdir);
			downloaddir = System.getProperty("user.dir") + "\\downloaddir\\back\\key4\\";
			log.debug("Downloaded report path is set to - " + downloaddir);
			report = new ExtentReports(reportpath + "Key4TestReport.html");
			test = report.startTest("Back end testing with session key 4");
			test.log(LogStatus.INFO, "Testing started with the session key 4");
			log.debug("Testing started with the session key 4");
			break;

		case "key5": 
			log.debug("Session Key is 5");
			reportpath = System.getProperty("user.dir") + "\\reports\\back\\key5\\"; 
			log.debug("Test report path is set to - " + reportpath);
			refdir = System.getProperty("user.dir") + "\\referencedata\\back\\key5\\";
			log.debug("Reference report path is set to - " + refdir);
			downloaddir = System.getProperty("user.dir") + "\\downloaddir\\back\\key5\\";
			log.debug("Downloaded report path is set to - " + downloaddir);
			report = new ExtentReports(reportpath + "Key5TestReport.html");
			test = report.startTest("Back end testing with session key 5");
			test.log(LogStatus.INFO, "Testing started with the session key 5");
			log.debug("Testing started with the session key 5");
			break;
		}
		
		driverName = "org.apache.hive.jdbc.HiveDriver";
		url = "jdbc:hive2://melserlin000049:10015/";
		username = "mapr";
		password = "mapr";
		
		try {
			log.debug("Checking hive jdbc driver");
			Class.forName(driverName);
		} catch (Exception e){
			log.debug("jdbc driver could not be found");
			e.printStackTrace();
			System.exit(1);
		}
		try {
			log.debug("Connecting to hive database");
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			stmt.execute("use cellos_ra");
			log.debug(".......... Finished beforetest");
		} catch (Exception e){
			log.debug(e);
		}
		
		
	  }
	
	@BeforeMethod(groups = { "all_test", "bi_presentation" , "gap_volume_daily", "gap_volume", "ai_dimension", "evidence", "ai_dep_presentation" })
	  public void beforeMethod() {
		log.debug("Starting beformethod ..........");
		istrue = false;
		log.debug("Initial value of istrue is set to: - " + istrue);
		log.debug(".......... Finished beformethod");
		
	  }
	
	@AfterMethod(groups = { "all_test", "bi_presentation" , "gap_volume_daily", "gap_volume", "ai_dimension", "evidence", "ai_dep_presentation" })
	  public void afterMethod(ITestResult testResult) throws SQLException {
		log.debug("Starting aftermethod ..........");
		try {
			if (res != null) {
				res.close();
			}
			if (res1 != null) {
				res1.close();
			}
			headerlist.clear();
		} catch (Exception e) {
			log.debug(e);
		}
		log.debug("Value of istrue is: " + istrue);
		if (istrue) {
			test.log(LogStatus.PASS, "SUCCESS");
		} else {
			test.log(LogStatus.FAIL, "FAIL");
		}

		
		test.log(LogStatus.SKIP, "---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---\r\n");
		test.log(LogStatus.SKIP, "\r\n");
		log.debug(".......... Finished aftermethod");
	  }
	
	@AfterTest(groups = { "all_test", "bi_presentation" , "gap_volume_daily", "gap_volume", "ai_dimension", "evidence", "ai_dep_presentation" })
	  public void afterTest() {
		log.debug("Starting aftertest ..........");
		try {
			log.debug(con);
			if (con != null) {
				con.close();
			}
			log.debug(con);
		} catch (Exception e) {
			log.debug(e);
		}
		report.endTest(test);
		report.flush();
		log.debug(".......... Finished aftertest");
		
	  }
	
	@Test(groups = { "all_test", "bi_presentation" }, priority = 0, enabled=true)
	  public void t001biPresentationTable() throws Exception {
		new startLog(1, "total volumes/subscriber count/session count from each sources in bi_presentation_table");
		
		filename = "bipt_1";
		//headerlist = new ArrayList<String>(Arrays.asList("source_name", "session_count", "subscriber_count", "uplink_vol", "downlink_vol", "total_vol", "free_vol"));
		sql = "SELECT source_name, COUNT(DISTINCT(key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(uplink_vol) AS uplink_vol, SUM(downlink_vol) AS downlink_vol, SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM bi_presentation_table GROUP BY source_name ORDER BY source_name";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		log.debug("Value of istrue is before validation: - " + istrue);
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		new resultValidation(istrue, 1, "total volumes/subscriber count/session count from each sources in bi_presentation_table", res1);
		log.debug("Value of istrue is after validation: - " + istrue);
	  }
	
	@Test(groups = { "all_test", "bi_presentation" }, priority = 1, enabled=true)
	  public void t002biPresentationTable() throws Exception {
		new startLog(2, "Site-wise DEP volumes/subscriber count/session count in bi_presentation_table");
		
		filename = "bipt_2a";
		//headerlist = new ArrayList<String>(Arrays.asList("site_name", "session_count", "subscriber_count", "uplink_vol", "downlink_vol", "total_vol", "free_vol"));
		sql = "SELECT site_name, COUNT(DISTINCT(key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(uplink_vol) AS uplink_vol, SUM(downlink_vol) AS downlink_vol, SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM bi_presentation_table WHERE source_name='DEP' GROUP BY site_name ORDER BY site_name";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istru is: " + istrue);
		new resultValidation(istrue, 2, "Site-wise DEP volumes/subscriber count/session count in bi_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "bi_presentation" }, priority = 2, enabled=true)
	  public void t003biPresentationTable() throws Exception {
		new startLog(3, "RAT-wise DEP volumes/subscriber count/session count in bi_presentation_table");
		
		filename = "bipt_2b";
		//headerlist = new ArrayList<String>(Arrays.asList("rat_type_name", "session_count", "subscriber_count", "uplink_vol", "downlink_vol", "total_vol", "free_vol"));
		sql = "SELECT rat_type_name, COUNT(DISTINCT(key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(uplink_vol) AS uplink_vol, SUM(downlink_vol) AS downlink_vol, SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM bi_presentation_table WHERE source_name='DEP' GROUP BY rat_type_name ORDER BY rat_type_name";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istru is: " + istrue);
		new resultValidation(istrue, 3, "RAT-wise DEP volumes/subscriber count/session count in bi_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "bi_presentation" }, priority = 3, enabled=true)
	  public void t004biPresentationTable() throws Exception {
		new startLog(4, "Subscriber type-wise DEP volumes/subscriber count/session count in bi_presentation_table");
		
		filename = "bipt_2c";
		//headerlist = new ArrayList<String>(Arrays.asList("subscriber_type", "session_count", "subscriber_count", "uplink_vol", "downlink_vol", "total_vol", "free_vol"));
		sql = "SELECT subscriber_type, COUNT(DISTINCT(key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(uplink_vol) AS uplink_vol, SUM(downlink_vol) AS downlink_vol, SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM bi_presentation_table WHERE source_name='DEP' GROUP BY subscriber_type ORDER BY subscriber_type";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istru is: " + istrue);
		new resultValidation(istrue, 4, "Subscriber type-wise DEP volumes/subscriber count/session count in bi_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "bi_presentation" }, priority = 4, enabled=true)
	  public void t005biPresentationTable() throws Exception {
		new startLog(5, "Date-wise DEP volumes/subscriber count/session count in bi_presentation_table");
		
		filename = "bipt_2d";
		//headerlist = new ArrayList<String>(Arrays.asList("Start_Time", "session_count", "subscriber_count", "uplink_vol", "downlink_vol", "total_vol", "free_vol"));
		sql = "SELECT SUBSTR(Start_Time,1,10) AS Start_Time, COUNT(DISTINCT(key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(uplink_vol) AS uplink_vol, SUM(downlink_vol) AS downlink_vol, SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM  bi_presentation_table WHERE source_name='DEP' GROUP BY SUBSTR(Start_Time,1,10) ORDER BY SUBSTR(Start_Time,1,10)";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istru is: " + istrue);
		new resultValidation(istrue, 5, "Date-wise DEP volumes/subscriber count/session count in bi_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "bi_presentation" }, priority = 5, enabled=true)
	  public void t006biPresentationTable() throws Exception {
		new startLog(6, "Site-wise CHG volumes/subscriber count/session count in bi_presentation_table");
		
		filename = "bipt_3a";
		//headerlist = new ArrayList<String>(Arrays.asList("site_name", "session_count", "subscriber_count", "uplink_vol", "downlink_vol", "total_vol", "free_vol"));
		sql = "SELECT site_name, COUNT(DISTINCT(key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(uplink_vol) AS uplink_vol, SUM(downlink_vol) AS downlink_vol, SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM bi_presentation_table WHERE source_name='CHG' GROUP BY site_name ORDER BY site_name";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istru is: " + istrue);
		new resultValidation(istrue, 6, "Site-wise CHG volumes/subscriber count/session count in bi_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "bi_presentation" }, priority = 6, enabled=true)
	  public void t007biPresentationTable() throws Exception {
		new startLog(7, "Subscriber type-wise CHG volumes/subscriber count/session count in bi_presentation_table");
		
		filename = "bipt_3b";
		//headerlist = new ArrayList<String>(Arrays.asList("subscriber_type", "session_count", "subscriber_count", "uplink_vol", "downlink_vol", "total_vol", "free_vol"));
		sql = "SELECT subscriber_type, COUNT(DISTINCT(key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(uplink_vol) AS uplink_vol, SUM(downlink_vol) AS downlink_vol, SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM bi_presentation_table WHERE source_name='CHG' GROUP BY subscriber_type ORDER BY subscriber_type";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istru is: " + istrue);
		new resultValidation(istrue, 7, "Subscriber type-wise CHG volumes/subscriber count/session count in bi_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "bi_presentation" }, priority = 7, enabled=true)
	  public void t008biPresentationTable() throws Exception {
		new startLog(8, "Date-wise CHG volumes/subscriber count/session count in bi_presentation_table");
		
		filename = "bipt_3c";
		//headerlist = new ArrayList<String>(Arrays.asList("Start_Time", "session_count", "subscriber_count", "uplink_vol", "downlink_vol", "total_vol", "free_vol"));
		sql = "SELECT SUBSTR(Start_Time,1,10) AS Start_Time, COUNT(DISTINCT(key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(uplink_vol) AS uplink_vol, SUM(downlink_vol) AS downlink_vol, SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM bi_presentation_table WHERE source_name='CHG' GROUP BY SUBSTR(Start_Time,1,10) ORDER BY SUBSTR(Start_Time,1,10)";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istru is: " + istrue);
		new resultValidation(istrue, 8, "Date-wise CHG volumes/subscriber count/session count in bi_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "bi_presentation" }, priority = 8, enabled=true)
	  public void t009biPresentationTable() throws Exception {
		new startLog(9, "Site-wise NW volumes/subscriber count/session count in bi_presentation_table");
		
		filename = "bipt_4a";
		//headerlist = new ArrayList<String>(Arrays.asList("site_name", "session_count", "subscriber_count", "uplink_vol", "downlink_vol", "total_vol", "free_vol"));
		sql = "SELECT site_name, COUNT(DISTINCT(key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(uplink_vol) AS uplink_vol, SUM(downlink_vol) AS downlink_vol, SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM bi_presentation_table WHERE source_name='NTW' GROUP BY site_name ORDER BY site_name";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istru is: " + istrue);
		new resultValidation(istrue, 9, "Site-wise NW volumes/subscriber count/session count in bi_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "bi_presentation" }, priority = 9, enabled=true)
	  public void t010biPresentationTable() throws Exception {
		new startLog(10, "RAT-wise NW volumes/subscriber count/session count in bi_presentation_table");
		
		filename = "bipt_4b";
		//headerlist = new ArrayList<String>(Arrays.asList("rat_type_name", "session_count", "subscriber_count", "uplink_vol", "downlink_vol", "total_vol", "free_vol"));
		sql = "SELECT rat_type_name, COUNT(DISTINCT(key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(uplink_vol) AS uplink_vol, SUM(downlink_vol) AS downlink_vol, SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM bi_presentation_table WHERE source_name='NTW' GROUP BY rat_type_name ORDER BY rat_type_name";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istru is: " + istrue);
		new resultValidation(istrue, 10, "RAT-wise NW volumes/subscriber count/session count in bi_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "bi_presentation" }, priority = 10, enabled=true)
	  public void t011biPresentationTable() throws Exception {
		new startLog(11, "Subscriber type-wise NW volumes/subscriber count/session count in bi_presentation_table");
		
		filename = "bipt_4c";
		//headerlist = new ArrayList<String>(Arrays.asList("subscriber_type", "session_count", "subscriber_count", "uplink_vol", "downlink_vol", "total_vol", "free_vol"));
		sql = "SELECT subscriber_type, COUNT(DISTINCT(key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(uplink_vol) AS uplink_vol, SUM(downlink_vol) AS downlink_vol, SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM bi_presentation_table WHERE source_name='NTW' GROUP BY subscriber_type ORDER BY subscriber_type";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istru is: " + istrue);
		new resultValidation(istrue, 11, "Subscriber type-wise NW volumes/subscriber count/session count in bi_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "bi_presentation" }, priority = 11, enabled=true)
	  public void t012biPresentationTable() throws Exception {
		new startLog(12, "Date-wise NW volumes/subscriber count/session count in bi_presentation_table");
		
		filename = "bipt_4d";
		//headerlist = new ArrayList<String>(Arrays.asList("Start_Time", "session_count", "subscriber_count", "uplink_vol", "downlink_vol", "total_vol", "free_vol"));
		sql = "SELECT SUBSTR(Start_Time,1,10) AS Start_Time, COUNT(DISTINCT(key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(uplink_vol) AS uplink_vol, SUM(downlink_vol) AS downlink_vol, SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM  bi_presentation_table WHERE source_name='NTW' GROUP BY SUBSTR(Start_Time,1,10) ORDER BY SUBSTR(Start_Time,1,10)";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istru is: " + istrue);
		new resultValidation(istrue, 12, "Date-wise NW volumes/subscriber count/session count in bi_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "gap_volume_daily" }, priority = 12, enabled=true)
	  public void t013gapVolumeDaily() throws Exception {
		new startLog(13, "Session Category wise volumes and counts in gap_volume_daily_table");
		
		filename = "gapvdt_1";
		//headerlist = new ArrayList<String>(Arrays.asList("session_category", "session_count", "subscriber_count", "probe_total", "probe_free", "ggsn_total", "ggsn_free", "ccn_total", "probe_ggsn_gap", "ggsn_ccn_gap", "probe_ccn_gap"));
		sql = "SELECT session_category,COUNT(DISTINCT(session_key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(session_key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(probe_total) AS probe_total, SUM(probe_free) AS probe_free, SUM(ggsn_total) AS ggsn_total, SUM(ggsn_free) AS ggsn_free, SUM(ccn_total) AS ccn_total, SUM(probe_ggsn_gap) AS probe_ggsn_gap, SUM(ggsn_ccn_gap) AS ggsn_ccn_gap, SUM(probe_ccn_gap) AS probe_ccn_gap FROM gap_volume_daily_table GROUP BY session_category ORDER BY session_category";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istru is: " + istrue);
		new resultValidation(istrue, 13, "Session Category wise volumes and counts in gap_volume_daily_table", res1);
	  }

	@Test(groups = { "all_test", "gap_volume_daily" }, priority = 13, enabled=true)
	  public void t014gapVolumeDaily() throws Exception {
		new startLog(14, "Daily volumes and counts in gap_volume_daily_table");
		
		filename = "gapvdt_2";
		//headerlist = new ArrayList<String>(Arrays.asList("session_category", "end_date", "session_count", "subscriber_count", "probe_total", "probe_free", "ggsn_total", "ggsn_free", "ccn_total", "probe_ggsn_gap", "ggsn_ccn_gap", "probe_ccn_gap"));
		sql = "SELECT session_category,end_date,COUNT(DISTINCT(session_key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(session_key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(probe_total) AS probe_total, SUM(probe_free) AS probe_free, SUM(ggsn_total) AS ggsn_total, SUM(ggsn_free) AS ggsn_free, SUM(ccn_total) AS ccn_total, SUM(probe_ggsn_gap) AS probe_ggsn_gap, SUM(ggsn_ccn_gap) AS ggsn_ccn_gap, SUM(probe_ccn_gap) AS probe_ccn_gap FROM gap_volume_daily_table GROUP BY session_category,end_date ORDER BY session_category,end_date";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istru is: " + istrue);
		new resultValidation(istrue, 14, "Session Category wise volumes and counts in gap_volume_daily_table", res1);
	  }

	@Test(groups = { "all_test", "gap_volume_daily" }, priority = 14, enabled=true)
	  public void t015gapVolumeDaily() throws Exception {
		new startLog(15, "Site wise volumes and counts in gap_volume_daily_table");
		
		filename = "gapvdt_3";
		//headerlist = new ArrayList<String>(Arrays.asList("session_category", "site_name", "session_count", "subscriber_count", "probe_total", "probe_free", "ggsn_total", "ggsn_free", "ccn_total", "probe_ggsn_gap", "ggsn_ccn_gap", "probe_ccn_gap"));
		sql = "SELECT session_category,site_name,COUNT(DISTINCT(session_key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(session_key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(probe_total) AS probe_total, SUM(probe_free) AS probe_free, SUM(ggsn_total) AS ggsn_total, SUM(ggsn_free) AS ggsn_free, SUM(ccn_total) AS ccn_total, SUM(probe_ggsn_gap) AS probe_ggsn_gap, SUM(ggsn_ccn_gap) AS ggsn_ccn_gap, SUM(probe_ccn_gap) AS probe_ccn_gap FROM gap_volume_daily_table GROUP BY session_category,site_name ORDER BY session_category,site_name";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istru is: " + istrue);
		new resultValidation(istrue, 15, "Session Category wise volumes and counts in gap_volume_daily_table", res1);
	  }

	@Test(groups = { "all_test", "gap_volume_daily" }, priority = 15, enabled=true)
	  public void t016gapVolumeDaily() throws Exception {
		new startLog(16, "UserCategory wise volumes and counts in gap_volume_daily_table");
		
		filename = "gapvdt_4";
		//headerlist = new ArrayList<String>(Arrays.asList("session_category", "subscriber_type", "session_count", "subscriber_count", "probe_total", "probe_free", "ggsn_total", "ggsn_free", "ccn_total", "probe_ggsn_gap", "ggsn_ccn_gap", "probe_ccn_gap"));
		sql = "SELECT session_category,subscriber_type,COUNT(DISTINCT(session_key)) AS session_count, COUNT(DISTINCT(REGEXP_REPLACE(session_key, '^([0-9]+)|.*$', '$1'))) AS subscriber_count, SUM(probe_total) AS probe_total, SUM(probe_free) AS probe_free, SUM(ggsn_total) AS ggsn_total, SUM(ggsn_free) AS ggsn_free, SUM(ccn_total) AS ccn_total, SUM(probe_ggsn_gap) AS probe_ggsn_gap, SUM(ggsn_ccn_gap) AS ggsn_ccn_gap, SUM(probe_ccn_gap) AS probe_ccn_gap FROM gap_volume_daily_table GROUP BY session_category,subscriber_type ORDER BY session_category,subscriber_type";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istru is: " + istrue);
		new resultValidation(istrue, 16, "Session Category wise volumes and counts in gap_volume_daily_table", res1);
	  }
	
	@Test(groups = { "all_test", "gap_volume" }, priority = 16, enabled=true)
	  public void t017gapVolume() throws Exception {
		new startLog(17, "Session Category wise volumes in gap_volume_table");
		filename = "gapvt_1";
		sql = "SELECT session_category,SUM(probe_ul) AS probe_ul,SUM(probe_dw) AS probe_dw,SUM(probe_total) AS probe_total,SUM(probe_free) AS probe_free, SUM(ggsn_ul) AS ggsn_ul,SUM(ggsn_dw) AS ggsn_dw,SUM(ggsn_total) AS ggsn_total,SUM(ggsn_free) AS ggsn_free, SUM(ccn_total) AS ccn_total,SUM(probe_ggsn_gap) AS probe_ggsn_gap,SUM(ggsn_ccn_gap) AS ggsn_ccn_gap,SUM(probe_ccn_gap) AS probe_ccn_gap FROM gap_volume_table GROUP BY session_category ORDER BY session_category";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 17, "Session Category wise volumes in gap_volume_table", res1);
	  }

	@Test(groups = { "all_test", "gap_volume" }, priority = 17, enabled=true)
	  public void t018gapVolume() throws Exception {
		new startLog(18, "Daily volumes in gap_volume_table");
		filename = "gapvt_2";
		sql = "SELECT session_category,SUBSTR(end_hour,1,10) AS end_hour,SUM(probe_ul) AS probe_ul,SUM(probe_dw) AS probe_dw,SUM(probe_total) AS probe_total,SUM(probe_free) AS probe_free, SUM(ggsn_ul) AS ggsn_ul,SUM(ggsn_dw) AS ggsn_dw,SUM(ggsn_total) AS ggsn_total,SUM(ggsn_free) AS ggsn_free, SUM(ccn_total) AS ccn_total,SUM(probe_ggsn_gap) AS probe_ggsn_gap,SUM(ggsn_ccn_gap) AS ggsn_ccn_gap,SUM(probe_ccn_gap) AS probe_ccn_gap FROM gap_volume_table GROUP BY session_category,SUBSTR(end_hour,1,10) ORDER BY session_category,SUBSTR(end_hour,1,10)";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 18, "Daily volumes in gap_volume_table", res1);
	  }

	@Test(groups = { "all_test", "gap_volume" }, priority = 18, enabled=true)
	  public void t019gapVolume() throws Exception {
		new startLog(19, "Bucket Index wise volumes in gap_volume_table");
		filename = "gapvt_3";
		sql = "SELECT session_category,volume_bucket_index,SUM(probe_ul) AS probe_ul,SUM(probe_dw) AS probe_dw,SUM(probe_total) AS probe_total,SUM(probe_free) AS probe_free, SUM(ggsn_ul) AS ggsn_ul,SUM(ggsn_dw) AS ggsn_dw,SUM(ggsn_total) AS ggsn_total,SUM(ggsn_free) AS ggsn_free, SUM(ccn_total) AS ccn_total,SUM(probe_ggsn_gap) AS probe_ggsn_gap,SUM(ggsn_ccn_gap) AS ggsn_ccn_gap,SUM(probe_ccn_gap) AS probe_ccn_gap FROM gap_volume_table WHERE session_category BETWEEN 2 AND 6 GROUP BY session_category,volume_bucket_index ORDER BY session_category,volume_bucket_index";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 19, "Bucket Index wise volumes in gap_volume_table", res1);
	  }

	@Test(groups = { "all_test", "gap_volume" }, priority = 19, enabled=true)
	  public void t020gapVolume() throws Exception {
		new startLog(20, "Site wise volumes in gap_volume_table");
		filename = "gapvt_4";
		sql = "SELECT session_category,site_name,SUM(probe_ul) AS probe_ul,SUM(probe_dw) AS probe_dw,SUM(probe_total) AS probe_total,SUM(probe_free) AS probe_free, SUM(ggsn_ul) AS ggsn_ul,SUM(ggsn_dw) AS ggsn_dw,SUM(ggsn_total) AS ggsn_total,SUM(ggsn_free) AS ggsn_free, SUM(ccn_total) AS ccn_total,SUM(probe_ggsn_gap) AS probe_ggsn_gap,SUM(ggsn_ccn_gap) AS ggsn_ccn_gap,SUM(probe_ccn_gap) AS probe_ccn_gap FROM gap_volume_table GROUP BY session_category,site_name ORDER BY session_category,site_name";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 20, "Site wise volumes in gap_volume_table", res1);
	  }

	@Test(groups = { "all_test", "gap_volume" }, priority = 20, enabled=true)
	  public void t021gapVolume() throws Exception {
		new startLog(21, "RAT wise volumes in gap_volume_table");
		filename = "gapvt_5";
		sql = "SELECT session_category,rat_type_name,SUM(probe_ul) AS probe_ul,SUM(probe_dw) AS probe_dw,SUM(probe_total) AS probe_total,SUM(probe_free) AS probe_free, SUM(ggsn_ul) AS ggsn_ul,SUM(ggsn_dw) AS ggsn_dw,SUM(ggsn_total) AS ggsn_total,SUM(ggsn_free) AS ggsn_free, SUM(ccn_total) AS ccn_total,SUM(probe_ggsn_gap) AS probe_ggsn_gap,SUM(ggsn_ccn_gap) AS ggsn_ccn_gap,SUM(probe_ccn_gap) AS probe_ccn_gap FROM gap_volume_table GROUP BY session_category,rat_type_name ORDER BY session_category,rat_type_name";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 21, "RAT wise volumes in gap_volume_table", res1);
	  }

	@Test(groups = { "all_test", "gap_volume" }, priority = 21, enabled=true)
	  public void t022gapVolume() throws Exception {
		new startLog(22, "User Category wise volumes in gap_volume_table");
		filename = "gapvt_6";
		sql = "SELECT session_category,subscriber_type,SUM(probe_ul) AS probe_ul,SUM(probe_dw) AS probe_dw,SUM(probe_total) AS probe_total,SUM(probe_free) AS probe_free, SUM(ggsn_ul) AS ggsn_ul,SUM(ggsn_dw) AS ggsn_dw,SUM(ggsn_total) AS ggsn_total,SUM(ggsn_free) AS ggsn_free, SUM(ccn_total) AS ccn_total,SUM(probe_ggsn_gap) AS probe_ggsn_gap,SUM(ggsn_ccn_gap) AS ggsn_ccn_gap,SUM(probe_ccn_gap) AS probe_ccn_gap FROM gap_volume_table GROUP BY session_category,subscriber_type ORDER BY session_category,subscriber_type";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 22, "User Category wise volumes in gap_volume_table", res1);
	  }

	@Test(groups = { "all_test", "gap_volume" }, priority = 22, enabled=true)
	  public void t023gapVolume() throws Exception {
		new startLog(23, "Charging Characteristics wise volumes in gap_volume_table");
		filename = "gapvt_7";
		sql = "SELECT session_category,charging_characteristics,SUM(probe_ul) AS probe_ul,SUM(probe_dw) AS probe_dw,SUM(probe_total) AS probe_total,SUM(probe_free) AS probe_free, SUM(ggsn_ul) AS ggsn_ul,SUM(ggsn_dw) AS ggsn_dw,SUM(ggsn_total) AS ggsn_total,SUM(ggsn_free) AS ggsn_free, SUM(ccn_total) AS ccn_total,SUM(probe_ggsn_gap) AS probe_ggsn_gap,SUM(ggsn_ccn_gap) AS ggsn_ccn_gap,SUM(probe_ccn_gap) AS probe_ccn_gap FROM gap_volume_table GROUP BY session_category,charging_characteristics ORDER BY session_category,charging_characteristics";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 23, "Charging Characteristics wise volumes in gap_volume_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dimension" }, priority = 23, enabled=true)
	  public void t024aiDimension() throws Exception {
		new startLog(24, "daily volumes and counts in ai_dimension_table");
		filename = "aidt_1";
		sql = "SELECT SUBSTR(end_hour,1,10) AS end_hour,COUNT(DISTINCT(imsi)) AS subscriber_count, COUNT(DISTINCT(CONCAT(imsi,charging_id))) AS session_count, SUM(chargable_vol) AS chargable_vol FROM ai_dimension_table GROUP BY SUBSTR(end_hour,1,10) ORDER BY SUBSTR(end_hour,1,10)";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 24, "daily volumes and counts in ai_dimension_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dimension" }, priority = 24, enabled=true)
	  public void t025aiDimension() throws Exception {
		new startLog(25, "protocol-wise and counts in ai_dimension_table");
		filename = "aidt_2";
		sql = "SELECT protocol,COUNT(DISTINCT(imsi)) AS subscriber_count, COUNT(DISTINCT(CONCAT(imsi,charging_id))) AS session_count, SUM(chargable_vol) AS chargable_vol FROM ai_dimension_table GROUP BY protocol ORDER BY protocol";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 25, "protocol-wise and counts in ai_dimension_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dimension" }, priority = 25, enabled=true)
	  public void t026aiDimension() throws Exception {
		new startLog(26, "sub_protocol-wise and counts in ai_dimension_table");
		filename = "aidt_3";
		sql = "SELECT sub_protocol,COUNT(DISTINCT(imsi)) AS subscriber_count, COUNT(DISTINCT(CONCAT(imsi,charging_id))) AS session_count, SUM(chargable_vol) AS chargable_vol FROM ai_dimension_table GROUP BY sub_protocol ORDER BY sub_protocol";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 26, "sub_protocol-wise and counts in ai_dimension_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dimension" }, priority = 26, enabled=true)
	  public void t027aiDimension() throws Exception {
		new startLog(27, "application-wise and counts in ai_dimension_table");
		filename = "aidt_4";
		sql = "SELECT application,COUNT(DISTINCT(imsi)) AS subscriber_count, COUNT(DISTINCT(CONCAT(imsi,charging_id))) AS session_count, SUM(chargable_vol) AS chargable_vol FROM ai_dimension_table GROUP BY application ORDER BY application";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 27, "application-wise and counts in ai_dimension_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dimension" }, priority = 27, enabled=true)
	  public void t028aiDimension() throws Exception {
		new startLog(28, "domain-wise and counts in ai_dimension_table");
		filename = "aidt_5";
		sql = "SELECT url,COUNT(DISTINCT(imsi)) AS subscriber_count, COUNT(DISTINCT(CONCAT(imsi,charging_id))) AS session_count, SUM(chargable_vol) AS chargable_vol FROM ai_dimension_table where url<>'null' AND url<>'' GROUP BY url ORDER BY url";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 28, "domain-wise and counts in ai_dimension_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dimension" }, priority = 28, enabled=true)
	  public void t029aiDimension() throws Exception {
		new startLog(29, "proxy-wise and counts in ai_dimension_table");
		filename = "aidt_6";
		sql = "SELECT proxy_ip,COUNT(DISTINCT(imsi)) AS subscriber_count, COUNT(DISTINCT(CONCAT(imsi,charging_id))) AS session_count, SUM(chargable_vol) AS chargable_vol FROM ai_dimension_table GROUP BY proxy_ip ORDER BY proxy_ip";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 29, "proxy-wise and counts in ai_dimension_table", res1);
	  }
	
	@Test(groups = { "all_test", "evidence" }, priority = 29, enabled=true)
	  public void t030evidence() throws Exception {
		new startLog(30, "Session Category wise volumes and counts in evidence_table");
		filename = "evt_1";
		sql = "SELECT session_category,COUNT(DISTINCT(imsi)) AS imsi,COUNT(DISTINCT(imsi)) AS imsi,COUNT(DISTINCT(CONCAT(imsi,charging_id))) AS Session,SUM(probe_ul) AS probe_ul,SUM(probe_dw) AS probe_dw,SUM(probe_total) AS probe_total,SUM(ggsn_ul) AS ggsn_ul,SUM(ggsn_dw) AS ggsn_dw,SUM(ggsn_total) AS ggsn_total,SUM(ccn_total) AS ccn_total,SUM(probe_ggsn_gap) AS probe_ggsn_gap,SUM(ggsn_ccn_gap) AS ggsn_ccn_gap,SUM(probe_ccn_gap) AS probe_ccn_gap FROM evidence_table GROUP BY session_category ORDER BY session_category";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 30, "Session Category wise volumes and counts in evidence_table", res1);
	  }
	
	@Test(groups = { "all_test", "evidence" }, priority = 30, enabled=true)
	  public void t031evidence() throws Exception {
		new startLog(31, "Start date wise volumes and counts in evidence_table");
		filename = "evt_2";
		sql = "SELECT session_category,SUBSTR(session_start_time,1,10) AS session_start_time,COUNT(DISTINCT(imsi)) AS imsi,COUNT(DISTINCT(imsi)) AS imsi,COUNT(DISTINCT(CONCAT(imsi,charging_id))) AS Session,SUM(probe_ul) AS probe_ul,SUM(probe_dw) AS probe_dw,SUM(probe_total) AS probe_total,SUM(ggsn_ul) AS ggsn_ul,SUM(ggsn_dw) AS ggsn_dw,SUM(ggsn_total) AS ggsn_total,SUM(ccn_total) AS ccn_total,SUM(probe_ggsn_gap) AS probe_ggsn_gap,SUM(ggsn_ccn_gap) AS ggsn_ccn_gap,SUM(probe_ccn_gap) AS probe_ccn_gap FROM evidence_table GROUP BY session_category,SUBSTR(session_start_time,1,10) ORDER BY session_category,SUBSTR(session_start_time,1,10)";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 31, "Start date wise volumes and counts in evidence_table", res1);
	  }
	
	@Test(groups = { "all_test", "evidence" }, priority = 31, enabled=true)
	  public void t032evidence() throws Exception {
		new startLog(32, "End date wise volumes and counts in evidence_table");
		filename = "evt_3";
		sql = "SELECT session_category,SUBSTR(session_end_time,1,10) AS session_end_time,COUNT(DISTINCT(imsi)) AS imsi,COUNT(DISTINCT(imsi)) AS imsi,COUNT(DISTINCT(CONCAT(imsi,charging_id))) AS Session,SUM(probe_ul) AS probe_ul,SUM(probe_dw) AS probe_dw,SUM(probe_total) AS probe_total,SUM(ggsn_ul) AS ggsn_ul,SUM(ggsn_dw) AS ggsn_dw,SUM(ggsn_total) AS ggsn_total,SUM(ccn_total) AS ccn_total,SUM(probe_ggsn_gap) AS probe_ggsn_gap,SUM(ggsn_ccn_gap) AS ggsn_ccn_gap,SUM(probe_ccn_gap) AS probe_ccn_gap FROM evidence_table GROUP BY session_category,SUBSTR(session_end_time,1,10) ORDER BY session_category,SUBSTR(session_end_time,1,10)";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 32, "End date wise volumes and counts in evidence_table", res1);
	  }
	
	@Test(groups = { "all_test", "evidence" }, priority = 32, enabled=true)
	  public void t033evidence() throws Exception {
		new startLog(33, "Site wise volumes and counts in evidence_table");
		filename = "evt_4";
		sql = "SELECT session_category,site_name,COUNT(DISTINCT(imsi)) AS imsi,COUNT(DISTINCT(imsi)) AS imsi,COUNT(DISTINCT(CONCAT(imsi,charging_id))) AS Session,SUM(probe_ul) AS probe_ul,SUM(probe_dw) AS probe_dw,SUM(probe_total) AS probe_total,SUM(ggsn_ul) AS ggsn_ul,SUM(ggsn_dw) AS ggsn_dw,SUM(ggsn_total) AS ggsn_total,SUM(ccn_total) AS ccn_total,SUM(probe_ggsn_gap) AS probe_ggsn_gap,SUM(ggsn_ccn_gap) AS ggsn_ccn_gap,SUM(probe_ccn_gap) AS probe_ccn_gap FROM evidence_table GROUP BY session_category,site_name ORDER BY session_category,site_name";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 33, "Site wise volumes and counts in evidence_table", res1);
	  }
	
	@Test(groups = { "all_test", "evidence" }, priority = 33, enabled=true)
	  public void t034evidence() throws Exception {
		new startLog(34, "RAT wise volumes and counts in evidence_table");
		filename = "evt_5";
		sql = "SELECT session_category,rat_type_name,COUNT(DISTINCT(imsi)) AS imsi,COUNT(DISTINCT(imsi)) AS imsi,COUNT(DISTINCT(CONCAT(imsi,charging_id))) AS Session,SUM(probe_ul) AS probe_ul,SUM(probe_dw) AS probe_dw,SUM(probe_total) AS probe_total,SUM(ggsn_ul) AS ggsn_ul,SUM(ggsn_dw) AS ggsn_dw,SUM(ggsn_total) AS ggsn_total,SUM(ccn_total) AS ccn_total,SUM(probe_ggsn_gap) AS probe_ggsn_gap,SUM(ggsn_ccn_gap) AS ggsn_ccn_gap,SUM(probe_ccn_gap) AS probe_ccn_gap FROM evidence_table GROUP BY session_category,rat_type_name ORDER BY session_category,rat_type_name";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 34, "RAT wise volumes and counts in evidence_table", res1);
	  }
	
	@Test(groups = { "all_test", "evidence" }, priority = 34, enabled=true)
	  public void t035evidence() throws Exception {
		new startLog(35, "User category wise volumes and counts in evidence_table");
		filename = "evt_6";
		sql = "SELECT session_category,subscriber_type,COUNT(DISTINCT(imsi)) AS imsi,COUNT(DISTINCT(imsi)) AS imsi,COUNT(DISTINCT(CONCAT(imsi,charging_id))) AS Session,SUM(probe_ul) AS probe_ul,SUM(probe_dw) AS probe_dw,SUM(probe_total) AS probe_total,SUM(ggsn_ul) AS ggsn_ul,SUM(ggsn_dw) AS ggsn_dw,SUM(ggsn_total) AS ggsn_total,SUM(ccn_total) AS ccn_total,SUM(probe_ggsn_gap) AS probe_ggsn_gap,SUM(ggsn_ccn_gap) AS ggsn_ccn_gap,SUM(probe_ccn_gap) AS probe_ccn_gap FROM evidence_table GROUP BY session_category,subscriber_type ORDER BY session_category,subscriber_type";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 35, "User category wise volumes and counts in evidence_table", res1);
	  }
	
	@Test(groups = { "all_test", "evidence" }, priority = 35, enabled=true)
	  public void t036evidence() throws Exception {
		new startLog(36, "Bucket index wise volumes and counts in evidence_table");
		filename = "evt_7";
		sql = "SELECT session_category,volume_bucket_index,COUNT(DISTINCT(imsi)) AS imsi,COUNT(DISTINCT(imsi)) AS imsi,COUNT(DISTINCT(CONCAT(imsi,charging_id))) AS Session,SUM(probe_ul) AS probe_ul,SUM(probe_dw) AS probe_dw,SUM(probe_total) AS probe_total,SUM(ggsn_ul) AS ggsn_ul,SUM(ggsn_dw) AS ggsn_dw,SUM(ggsn_total) AS ggsn_total,SUM(ccn_total) AS ccn_total,SUM(probe_ggsn_gap) AS probe_ggsn_gap,SUM(ggsn_ccn_gap) AS ggsn_ccn_gap,SUM(probe_ccn_gap) AS probe_ccn_gap FROM evidence_table GROUP BY session_category,volume_bucket_index ORDER BY session_category,volume_bucket_index";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 36, "Bucket index wise volumes and counts in evidence_table", res1);
	  }
	
	

	
	
	
	
	
	
	
	
	
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 36, enabled=true)
	  public void t037aiDepPresentation() throws Exception {
		new startLog(37, "volumes with Session_Category and Volume_Bucket_Index as dimension in ai_dep_presentation_table");
		filename = "aidpt_1";
		sql = "SELECT Session_Category,Volume_Bucket_Index,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table WHERE Session_Category BETWEEN 2 AND 6 GROUP BY Session_Category,Volume_Bucket_Index ORDER BY Session_Category,Volume_Bucket_Index";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 37, "volumes with Session_Category and Volume_Bucket_Index as dimension in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 37, enabled=true)
	  public void t038aiDepPresentation() throws Exception {
		new startLog(38, "date-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_2";
		sql = "SELECT SUBSTR(end_hour,1,10) AS end_hour,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table WHERE end_hour<>'' GROUP BY SUBSTR(end_hour,1,10) ORDER BY SUBSTR(end_hour,1,10)";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 38, "date-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 38, enabled=true)
	  public void t039aiDepPresentation() throws Exception {
		new startLog(39, "Site-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_3";
		sql = "SELECT site_name,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY site_name ORDER BY site_name";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 39, "Site-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 39, enabled=true)
	  public void t040aiDepPresentation() throws Exception {
		new startLog(40, "RAT_Type-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_4";
		sql = "SELECT rat_type_name,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY rat_type_name ORDER BY rat_type_name";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 40, "RAT_Type-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 40, enabled=true)
	  public void t041aiDepPresentation() throws Exception {
		new startLog(41, "Subscriber_Type-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_5";
		sql = "SELECT subscriber_type,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY subscriber_type ORDER BY subscriber_type";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 41, "Subscriber_Type-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 41, enabled=true)
	  public void t042aiDepPresentation() throws Exception {
		new startLog(42, "Usage_Type-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_6";
		sql = "SELECT usage_type,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY usage_type ORDER BY usage_type";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 42, "Usage_Type-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 42, enabled=true)
	  public void t043aiDepPresentation() throws Exception {
		new startLog(43, "Serving_Gateway_IP-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_7";
		sql = "SELECT serving_ip,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY serving_ip ORDER BY serving_ip";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 43, "Serving_Gateway_IP-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 43, enabled=true)
	  public void t044aiDepPresentation() throws Exception {
		new startLog(44, "PDN_Gateway_IP-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_8";
		sql = "SELECT gateway_ip,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY gateway_ip ORDER BY gateway_ip";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 44, "PDN_Gateway_IP-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 44, enabled=true)
	  public void t045aiDepPresentation() throws Exception {
		new startLog(45, "LAC-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_9";
		sql = "SELECT lac,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY lac ORDER BY lac";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 45, "LAC-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 45, enabled=true)
	  public void t046aiDepPresentation() throws Exception {
		new startLog(46, "TAC-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_10";
		sql = "SELECT tac,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY tac ORDER BY tac";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 46, "TAC-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 46, enabled=true)
	  public void t047aiDepPresentation() throws Exception {
		new startLog(47, "Cell-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_11";
		sql = "SELECT cell_id,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY cell_id ORDER BY cell_id";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 47, "Cell-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 47, enabled=true)
	  public void t048aiDepPresentation() throws Exception {
		new startLog(48, "eCell-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_12";
		sql = "SELECT ecell_id,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY ecell_id ORDER BY ecell_id";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 48, "eCell-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 48, enabled=true)
	  public void t049aiDepPresentation() throws Exception {
		new startLog(49, "URL-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_13";
		sql = "SELECT url,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table WHERE url<>'' AND url<>' ' AND url<>'null' GROUP BY url ORDER BY url";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 49, "URL-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 49, enabled=true)
	  public void t050aiDepPresentation() throws Exception {
		new startLog(50, "protocol-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_14";
		sql = "SELECT protocol,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY protocol ORDER BY protocol";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 50, "protocol-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 50, enabled=true)
	  public void t051aiDepPresentation() throws Exception {
		new startLog(51, "sub_protocol-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_15";
		sql = "SELECT sub_protocol,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY sub_protocol ORDER BY sub_protocol";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 51, "sub_protocol-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 51, enabled=true)
	  public void t052aiDepPresentation() throws Exception {
		new startLog(52, "Application-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_16";
		sql = "SELECT application,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY application ORDER BY application";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 52, "Application-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 52, enabled=true)
	  public void t053aiDepPresentation() throws Exception {
		new startLog(53, "Proxy IP-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_17";
		sql = "SELECT proxy_ip,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY proxy_ip ORDER BY proxy_ip";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 53, "Proxy IP-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 53, enabled=true)
	  public void t054aiDepPresentation() throws Exception {
		new startLog(54, "proxy Domain Name-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_18";
		sql = "SELECT proxy_domain_name,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table WHERE Proxy_Domain_name<>'' AND proxy_domain_name<>'null' GROUP BY proxy_domain_name ORDER BY proxy_domain_name";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 54, "proxy Domain Name-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 54, enabled=true)
	  public void t055aiDepPresentation() throws Exception {
		new startLog(55, "destination Server IP-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_19";
		sql = "SELECT destination_server_ip,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY destination_server_ip ORDER BY destination_server_ip";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 55, "destination Server IP-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	@Test(groups = { "all_test", "ai_dep_presentation" }, priority = 55, enabled=true)
	  public void t056aiDepPresentation() throws Exception {
		new startLog(56, "Handset_Category IP-wise volumes in ai_dep_presentation_table");
		filename = "aidpt_20";
		sql = "SELECT handset_category,SUM(uplink_vol) AS uplink_vol,SUM(downlink_vol) AS downlink_vol,SUM(total_vol) AS total_vol, SUM(free_vol) AS free_vol FROM ai_dep_presentation_table GROUP BY handset_category ORDER BY handset_category";
		res = stmt.executeQuery(sql);
		writeAndCompare wrCmp = new writeAndCompare();
		istrue = wrCmp.createExcelFile(downloaddir, refdir, filename, res);
		res1 = stmt.executeQuery(sql);
		log.debug("value of istrue is: " + istrue);
		new resultValidation(istrue, 56, "Handset_Category IP-wise volumes in ai_dep_presentation_table", res1);
	  }
	
	
	
	
	
	
	
	
	
	
	private class writeAndCompare {
		private String c1createdfilename, c1reffilename;
		private FileOutputStream c1createdExcelFile;
		private XSSFWorkbook c1createdWBook;
		private XSSFSheet c1createdWSheet;
		private XSSFRow c1row, headerrow;
		private XSSFCell c1cell, headercell;
		private File c1createdfile, c1reffile;
		//private File c1createdfile;
		private boolean createExcelFile (String c1createddir, String c1refdir, String c1filename, ResultSet c1res) throws Exception {
			log.debug("Starting writeAndCompare class");
			c1createdWBook = new XSSFWorkbook();
			c1createdWSheet = c1createdWBook.createSheet(c1filename);
			c1reffilename = c1refdir + c1filename + ".xlsx";
			log.debug("created file name: - " + c1reffilename);
			c1createdfilename = c1createddir + c1filename + ".xlsx";
			log.debug("created file name: - " + c1createdfilename);
			c1reffile = new File(c1reffilename);
			c1createdfile = new File(c1createdfilename);
			if(c1createdfile.exists()) {
				c1createdfile.delete();
				log.debug("An existing created file is present in the download directory, deleted it");
			}
			if (c1res != null) {
				ResultSetMetaData rsmd = c1res.getMetaData();
				int i = 1;
				while (c1res.next()) {
					if (i==1) {
						headerrow = c1createdWSheet.createRow(i-1);
						c1row = c1createdWSheet.createRow(i);
						for (int j = 1; j <= rsmd.getColumnCount(); j++) {
							headercell = headerrow.createCell(j-1);
							c1cell = c1row.createCell(j-1);
							headercell.setCellValue(rsmd.getColumnName(j));
							int cellType = rsmd.getColumnType(j);
							if (cellType == Types.VARCHAR || cellType == Types.CHAR) {
								c1cell.setCellValue(c1res.getString(j));
							} else {
								c1cell.setCellValue(c1res.getLong(j));
							}
						}
					} else {
						c1row = c1createdWSheet.createRow(i);
						for (int j = 1; j <= rsmd.getColumnCount(); j++) {
							c1cell = c1row.createCell(j-1);
							int cellType = rsmd.getColumnType(j);
							if (cellType == Types.VARCHAR || cellType == Types.CHAR) {
								c1cell.setCellValue(c1res.getString(j));
							} else {
								c1cell.setCellValue(c1res.getLong(j));
							}
						}
					}
					i++;
				}
			}
			c1createdExcelFile = new FileOutputStream(c1createdfile);
			c1createdWBook.write(c1createdExcelFile);
			c1createdExcelFile.close();
			log.debug(c1createdfile + " is created and output table of hive query is written in it successfully");
			
			compReports comp = new compReports();
			boolean istrue1 = comp.compareExcelFiles(c1reffilename, c1createdfilename);
			log.debug("step-4");
			log.debug("Value of istrue1 received from compReports class is: - " + istrue1);
			if (istrue1) {
				log.debug("This query in the backend produces expected results");
			} else {
				log.debug("This query in the backend produces incorrect results");
/*				log.info("Expected table is: ");
				test.log(LogStatus.INFO, "Expected table is: ");
				new displayExcelSheet(c1reffile);
				log.error("Received table is: ");
				test.log(LogStatus.WARNING, "Received table is: ");
				new displayExcelSheet(c1createdfile);
*/			}
			log.debug("Finished writeAndCompare class");
			return istrue1;
			
		}
	}
	
	private class compReports {
		public boolean compareExcelFiles(String c2reffilename, String c2createdfilename) throws Exception {
			log.debug("Starting compReports class");
			int totRows, totCols;
			FileInputStream c2refExcelFile, c2createdExcelFile;
			XSSFWorkbook c2refExcelWBook, c2createdExcelWBook;
			XSSFSheet c2refExcelWSheet, c2createdExcelWSheet;
			XSSFRow c2Row;
			boolean istrue2 = true;
			
			c2refExcelFile = new FileInputStream(c2reffilename);
			c2refExcelWBook = new XSSFWorkbook(c2refExcelFile);
			c2refExcelWSheet = c2refExcelWBook.getSheetAt(0);
			c2createdExcelFile = new FileInputStream(c2createdfilename);
			c2createdExcelWBook = new XSSFWorkbook(c2createdExcelFile);
			c2createdExcelWSheet = c2createdExcelWBook.getSheetAt(0);

			Iterator <Row> c2rowIterator = c2refExcelWSheet.iterator();
			totRows = totCols = 0;
			while (c2rowIterator.hasNext()) {
				c2Row = (XSSFRow) c2rowIterator.next();
				Iterator <Cell> c2cellIterator = c2Row.cellIterator();
				totCols = 0;
				while (c2cellIterator.hasNext()) {
					Cell c2Cell = c2cellIterator.next();
					CellType reftype = c2Cell.getCellTypeEnum(); 
					if (reftype == CellType.STRING) {
						totCols++;
					} else if (reftype == CellType.NUMERIC) {
						totCols++;
					} else {
						log.debug("Celltype is neither STRING nor NUMERIC");
					}
				}
				totRows++;
			}
			for (int i=0; i<totRows; i++) {
				for (int j=0; j<totCols; j++) {
					try {
						CellType reftype =  c2refExcelWSheet.getRow(i).getCell(j).getCellTypeEnum();
						//log.debug("Cell type of reference file is: " + reftype);
						if (reftype == CellType.STRING) {
							try {
								//log.debug("Cell type of created file is: " + c2createdExcelWSheet.getRow(i).getCell(j).getCellTypeEnum());
								log.debug("reference: " + c2refExcelWSheet.getRow(i).getCell(j).getStringCellValue());
								log.debug("downloaded: " + c2createdExcelWSheet.getRow(i).getCell(j).getStringCellValue());
								istrue2 = istrue2 && (c2refExcelWSheet.getRow(i).getCell(j).getStringCellValue().equals(c2createdExcelWSheet.getRow(i).getCell(j).getStringCellValue()));
								log.debug(c2refExcelWSheet.getRow(i).getCell(j).getStringCellValue().equals(c2createdExcelWSheet.getRow(i).getCell(j).getStringCellValue()));
							} catch (Exception e) {
								istrue2 = false;
								log.debug(istrue2);
								log.error("something went wrong during reading the Excel file");
								log.debug(e);
							}
						} else if (reftype == CellType.NUMERIC) {
							//log.debug("Cell type of created file is: " + c2createdExcelWSheet.getRow(i).getCell(j).getCellTypeEnum());
							log.debug("reference: " + c2refExcelWSheet.getRow(i).getCell(j).getNumericCellValue());
							log.debug("downloaded: " + c2createdExcelWSheet.getRow(i).getCell(j).getNumericCellValue());
							istrue2 = istrue2 && c2refExcelWSheet.getRow(i).getCell(j).getNumericCellValue() == c2createdExcelWSheet.getRow(i).getCell(j).getNumericCellValue();
							log.debug(istrue2);
						}
					} catch (Exception e) {
						log.error("something went wrong during reading the Excel file");
						log.debug(e);
					}
				}
			}
			log.debug("Finished compReports class, value of istrue2 is: - " + istrue2);
			return istrue2;
		}
	}
	
	private class resultValidation {
		public resultValidation(boolean istrue3, Integer testno, String testwhat, ResultSet c2res) {
			log.debug("Starting resultValidation method, value of istrue is: - " + istrue3);
			if (istrue3) {
				log.debug("Test Case - " + testno + ": Testing of " + testwhat + " is SUCCESSFUL");
				test.log(LogStatus.PASS, "Test Case - " + testno + ": Testing of " + testwhat + " is SUCCESSFUL");
			} else {
				log.debug("Test Case - " + testno + ": Testing of " + testwhat + " is FAILED");
				test.log(LogStatus.WARNING, "Test Case - " + testno + ": Testing of " + testwhat + " is FAILED");
			}
			log.debug("Finished resultValidation class");
		}
	}
	
	
	private class displayExcelSheet {
		FileInputStream c3ExcelFile;
		XSSFWorkbook c3ExcelWBook;
		XSSFSheet c3ExcelWSheet;
		XSSFRow c3Row;
		public displayExcelSheet(File c3File) throws Exception {
			log.debug("Starting displayExcelSheet class");
			c3ExcelFile = new FileInputStream(c3File);
			c3ExcelWBook = new XSSFWorkbook(c3ExcelFile);
			c3ExcelWSheet = c3ExcelWBook.getSheetAt(0);
			Iterator <Row> rowIterator = c3ExcelWSheet.iterator();
			while (rowIterator.hasNext()) {
				c3Row = (XSSFRow) rowIterator.next();
				Iterator <Cell> cellIterator = c3Row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell c3Cell = cellIterator.next();
					CellType reftype = c3Cell.getCellTypeEnum(); 
					if (reftype == CellType.STRING) {
						log.error(c3Cell.getStringCellValue() + " \t\t ");
					} else if (reftype == CellType.NUMERIC) {
						log.error(c3Cell.getNumericCellValue() + " \t\t ");
					} else {
						log.debug("Celltype is neither STRING nor NUMERIC");
					}
				}
				log.info("");
			}
			c3ExcelFile.close();	
			log.debug("Finished displayExcelSheet class");
		}
	}
	
	private class startLog {
		public startLog(Integer testno, String testwhat) {
			log.debug("Test Case - " + testno + ": Starting to test " + testwhat);
			test.log(LogStatus.INFO, "Test Case - " + testno + ": Starting to test " + testwhat);
		}
	}
}
