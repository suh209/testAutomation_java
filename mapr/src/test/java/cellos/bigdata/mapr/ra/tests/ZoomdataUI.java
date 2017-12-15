package cellos.bigdata.mapr.ra.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

/*import page.ra.ActionClass;*/
import cellos.bigdata.mapr.ra.pages.CommonClass;
import cellos.bigdata.mapr.ra.pages.EightRefFiles;
import cellos.bigdata.mapr.ra.pages.Five1RaAPSR;
import cellos.bigdata.mapr.ra.pages.Five2RaDUR;
import cellos.bigdata.mapr.ra.pages.Five3RaPIpR;
import cellos.bigdata.mapr.ra.pages.FourRaAApnR;
import cellos.bigdata.mapr.ra.pages.OneRaAD;
import cellos.bigdata.mapr.ra.pages.SevenGapPro;
import cellos.bigdata.mapr.ra.pages.SixEv;
import cellos.bigdata.mapr.ra.pages.ThreeRaAGS;
import cellos.bigdata.mapr.ra.pages.TwoPaS;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ZoomdataUI {
	private String failedscreenshotdir, reportpath, refdatafile, refdir, downloaddir, baseUrl, username, password, displayedString, refString;
	private Number refData, refData1, displayedData, displayedData1;
	private WebDriver driver;
	private WebDriverWait wait;
	private List<String> refchartlist, reftimewindow, reftimewindow1;
	public HashMap<String, Number> refdictionary, dloadeddictionary;
	private boolean istrue, istrue1, istrue2, istrue3, istrue4, istrue5, istrue6, istrue7, istrue8, istrue9, istrue10, istrue11, istrue12, istrue13, istrue14, istrue15, istrue16;   
	static Logger log = LogManager.getLogger(ZoomdataUI.class.getName());
	private Assertion hardAssert = new Assertion();
	private SoftAssert softAssert = new SoftAssert();
	
	
	FirefoxProfile profile = new FirefoxProfile();
	
	private XSSFWorkbook ExcelWBook;
	private XSSFSheet ExcelWSheetoneraad;

	private Screen screen;
	
	CommonClass ccc;
	OneRaAD one;
/*	ActionClass aaa;
*/	TwoPaS two;
	ThreeRaAGS three;
	FourRaAApnR four;
	Five1RaAPSR fivea;
	Five2RaDUR fiveb;
	Five3RaPIpR fivec;
	SixEv six;
	SevenGapPro seven;
	EightRefFiles eight;

	

	ExtentReports report;
	ExtentTest test;
	
	
	
	@Parameters("browserType")
	@BeforeTest(groups = { "all_tests", "RaAnaApnWsRprt", "RaGapProf", "PreAnalysisSummary" })
	public void beforeTest(String browser) throws Exception {
		failedscreenshotdir = System.getProperty("user.dir") + "\\failedscreenshots\\";
		reportpath = System.getProperty("user.dir") + "\\reports\\"; 
		refdir = System.getProperty("user.dir") + "\\referencedata\\front\\";
		refdatafile = refdir + "refdata.xlsx";
		downloaddir = System.getProperty("user.dir") + "\\downloaddir\\front\\";
		refchartlist = new ArrayList<String>(Arrays.asList("1. RA Analyst- Dashboard", "2a. Pre-analysis Summary (1 of 3)", "2b. Pre-analysis Summary (2 of 3)", "2c. Pre-analysis Summary (3 of 3)", "3a. RA Analyst� GAP Summary (1/2)", "3b. RA Analyst� GAP Summary (2/2)", "4. RA-Analyst APN-wise Reports", "5a. RA - App/Protocol/sub-protocol wise Reports", "5b. RA - Domain / URL wise Reports", "5c. RA - Proxy-IP wise Reports", "6. Evidences", "7a. GAP Profiling (1 of 6)", "7b. GAP Profiling (2 of 6)", "7c. GAP Profiling (3 of 6)", "7d. GAP Profiling (4 of 6)", "7e. GAP Profiling (5 of 6)", "7f. GAP Profiling (6 of 6)", "8a.APN Details", "8b. URL Details", "8c. PROTOCOL Details", "8d. SUB Protocol Details", "8e. APPLICATION Details", "8f. MSISDN Details", "8g. Roaming Partner Details", "8h. Special IP Details", "8i. Handset Category Details", "8j. NODE Details", "Reference File Upload"));
		reftimewindow = new ArrayList<String>(Arrays.asList("JUN 25 2016 12:00:00 AM", "JUL 2 2016 12:00:00 AM"));
		username = "ra_manager";
		password = "cellos1";
		baseUrl = "http://melserlin000050:8080/zoomdata/login";
		screen = new Screen();

		profile.setPreference("browser.download.dir", downloaddir);
		profile.setPreference("browser.download.folderList", 2);
		
		if (browser.equalsIgnoreCase("firefox")) {
			report = new ExtentReports( reportpath + "zoomdatauifrefoxtest.html");
			test = report.startTest("Sanity test with Firefox browsers");
			driver = new FirefoxDriver(profile);
			test.log(LogStatus.INFO, "Testing started with " + browser + " browser");
		} else if (browser.equalsIgnoreCase("chrome")) {
			report = new ExtentReports(reportpath + "zoomdatauichrometest.html");
			test = report.startTest("Sanity test with Chrome browsers");
			driver = new ChromeDriver();
			test.log(LogStatus.INFO, "Testing started with " + browser + " browser");
		}
		
		try {
			FileInputStream ExcelFile = new FileInputStream(refdatafile);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			log.info("Referecne Excel data file found");
			ExcelWSheetoneraad = ExcelWBook.getSheet("oneraad");
			log.info("Excel worksheet found");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Something gone wrong while reading data from Excel file");
		}
		
		ccc = new CommonClass(driver);
/*		aaa = new ActionClass(driver);*/
		one = new OneRaAD(driver);
		two = new TwoPaS(driver);
		three = new ThreeRaAGS(driver);
		three = new ThreeRaAGS(driver);
		four = new FourRaAApnR(driver);
		fivea = new Five1RaAPSR(driver);
		fiveb = new Five2RaDUR(driver);
		fivec = new Five3RaPIpR(driver);
		six = new SixEv(driver);
		seven = new SevenGapPro(driver);
		eight = new EightRefFiles(driver);
		
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "maximize the browser window");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, "Defined implicit wait timeout as 5 seconds");
		wait = new WebDriverWait(driver, 180);
		test.log(LogStatus.INFO, "Defined explicit wait timeout as 180 seconds");
		driver.get(baseUrl);
		test.log(LogStatus.INFO, "load login page");
		Thread.sleep(3000);
		test.log(LogStatus.INFO, "waiting 3 seconds for the page to display");

		ccc.fillUserNameTextBox(username);
		test.log(LogStatus.INFO, "Fillied up username textbox");
		ccc.fillPasswordTextBox(password);
		test.log(LogStatus.INFO, "Fillied up password textbox");
		ccc.clickOnLoginButton();
		test.log(LogStatus.INFO, "clicked on login button");
		
	}
	
	@BeforeMethod(groups = { "all_tests", "RaAnaApnWsRprt", "RaGapProf", "PreAnalysisSummary" })
	public void beforeMethod() throws Exception {
		log.debug("Beforemethod starting ..");
		istrue = istrue1 = istrue2 = istrue3 = istrue4 = istrue5 = istrue6 = istrue7 = istrue8 = istrue9 = istrue10 = istrue11 = istrue12 = istrue13 = istrue14 = istrue15 = istrue16 = false;
		log.debug("Initial values of all <istrue> boolean variables are set to false");
		screen.click(ccc.homeButton());
		log.debug("Clicked on Home screenshot pattern button");
		Thread.sleep(3000);
/*		try {
			screen.click(ccc.leavePageConfrm());
			log.debug("Clicked on <Leave Page> screenshot button to confirm leave page");
		} catch (Exception e) {
			log.debug("<Leave Page> is not shown as this is the first test case, or nothing has been changed in the previous test case. So nothing to save.");
			log.trace(e);
		}
		Thread.sleep(3000);
*/		try {
			screen.click(ccc.leavePageNoSave());
			log.debug("Clicked on <Cancel> screenshot button to confirm quit without saving");
		} catch (Exception e) {
			log.debug("Confirmation is not shown as this is the first test case, or nothing has been changed in the previous test case. So nothing to save.");
			log.trace(e);
		}
		Thread.sleep(2000);
		driver.navigate().refresh();
		log.debug("Refreshing the web page");
		Thread.sleep(2000);
		ccc.clickOnAllViews();
		log.debug("clicked on View All button to display all favorite charts");
		log.debug("Beforemethod finished");
	}

	@AfterMethod(groups = { "all_tests", "RaAnaApnWsRprt", "RaGapProf", "PreAnalysisSummary" })
	public void afterMethod(ITestResult testResult) throws IOException, Exception {
		log.debug("Aftermethod starting ..");
		//if (testResult.getStatus() == ITestResult.SUCCESS) {
		if (istrue) {
			log.debug("Test Case passed");
			test.log(LogStatus.PASS, "Test Case passed - all the test steps are successful");
		//} else if (testResult.getStatus() == ITestResult.FAILURE) {
		} else {
			log.debug("Test Case failed, as one or more test steps failed");
			test.log(LogStatus.WARNING, "Test Case failed, as one or more test steps failed");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
			String imagepath = test.addScreenCapture(path);
			test.log(LogStatus.FAIL, "Test Case failed, as one or more test steps failed", imagepath);
		}
		test.log(LogStatus.SKIP, "---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---*---\r\n");
		test.log(LogStatus.SKIP, "\r\n");
		log.debug("Aftermethod finished");
	}
	 
	@AfterTest(groups = { "all_tests", "RaAnaApnWsRprt", "RaGapProf", "PreAnalysisSummary" })
	public void afterTest() {
		log.debug("AfterTest starting ..");
		ccc.clickOnUserInfoButton();
		log.debug("User info button clicked");
		ccc.clickOnLogoutButton();
		log.debug("Logout button clicked");
		driver.close();
		driver.quit();
		report.endTest(test);
		report.flush();
		log.debug("AfterTest finished");
	}
	
	
	@Test(groups = { "all_tests", "basic_sanity" }, priority = 0, enabled=true)
	public void t001AllChartNames() throws Exception {
		test.log(LogStatus.INFO, "Test Case - t001: Starting RA Zoomdata UI testing with FireFox browser");
		log.debug("Test Case - t001: Starting RA Zoomdata UI testing with FireFox browser");  
		test.log(LogStatus.INFO, "Test step-1: Starting to test the presence of all zoomdata charts in the Favourite");
		log.debug("Test step-1: Starting to test the presence of all zoomdata charts in the Favourite");
		istrue1 = refchartlist.equals(ccc.allViewNames());
		if (istrue1) {
			log.debug("favourite charts list matched with the expected chart list? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing the presence of all zoomdata charts in the Favourite is successful");
		} else {
			log.debug("favourite charts list matched with the expected chart list? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "favourite charts list matched with the expected chart list? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
			String imagepath = test.addScreenCapture(path);
			log.warn("Test step-1: Testing the presence of all zoomdata charts in the Favourite Failed");
			test.log(LogStatus.FAIL, "Test step-1: Testing the presence of all zoomdata charts in the Favourite Failed", imagepath);
		}
		
		test.log(LogStatus.INFO, "Test step-2: Starting to test the presence of all zoomdata charts in the dropdown");
		log.debug("Test step-2: Starting to test the presence of all zoomdata charts in the dropdown");
		screen.click(ccc.displayAllDropDownCharts());
		log.debug("clicked on dropdown button to display all charts");
		istrue2 = refchartlist.equals(ccc.allDropDownViewNames());
		if (istrue2) {
			log.debug("dropdown charts list matched with the expected chart list? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-2: Testing the presence of all zoomdata charts in the dropdown is successful");
		} else {
			log.debug("dropdown charts list matched with the expected chart list? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.FAIL, "dropdown charts list matched with the expected chart list? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
			String imagepath = test.addScreenCapture(path);
			log.warn("Test step-2: Testing the presence of all zoomdata charts in the dropdown Failed");
			test.log(LogStatus.FAIL, "Test step-2: Testing the presence of all zoomdata charts in the dropdown Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Dload", }, priority = 1, enabled=true)
	public void t002DepTotVolDload() throws Exception {
		log.info("Test Case - t002: Starting to test total DEP volumes in RA Analyst Dashboard by downloading the Excel report and comparing it withe the reference report");
		test.log(LogStatus.INFO, "Test Case - t002: Starting to test total DEP volumes in RA Analyst Dashboard by downloading the Excel report and comparing it withe the reference report");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.depVolMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.depVolMaxButn().click();
		log.info("Clicked on DEP vol maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of DEP Total Volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of DEP Total Volume");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result: - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of DEP Total Volume is successful");
		} else {
			log.debug("Is time window matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Is time window matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of DEP Total Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of DEP Total Volume is Failed", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"DepTotVol.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"DepTotVol.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"DepTotVol.xlsx", downloaddir+"DepTotVol.xlsx", 3, 0, 7, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference filetime window matching with the expected result: - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference filetime window matching with the expected result: - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Is downloaded Excel file matched with the reference filetime window matching with the expected result: - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}

	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 2, enabled=true)
	public void t003DepTotVolChart() throws Exception {
		log.info("Test Case - t003: Starting to test displayed total DEP volumes in RA Analyst Dashboard");
		test.log(LogStatus.INFO, "Test Case - t003: Starting to test displayed total DEP volumes in RA Analyst Dashboard");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.depVolMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.depVolMaxButn().click();
		log.info("Clicked on DEP vol maximize button");
		Thread.sleep(5000);
		test.log(LogStatus.INFO,"Test step-1: Starting to test displayed data on <28-Jun-2016>");
		log.debug("Test step-1: Starting to test displayed data on <28-Jun-2016>");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		Thread.sleep(2000);
		try {
			screen.click(one.depTotpatternOn628());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = Float.valueOf(ExcelWSheetoneraad.getRow(2).getCell(1).getStringCellValue()).floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed data matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of total DEP volume on <28-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed data matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Displayed data on <28-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed data on <28-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed data on <28-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		test.log(LogStatus.INFO,"Test step-2: Starting to test displayed data on <29-Jun-2016>");
		log.debug("Test step-2: Starting to test displayed data on <29-Jun-2016>");
		try {
			screen.click(one.depTotpatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = Float.valueOf(ExcelWSheetoneraad.getRow(3).getCell(1).getStringCellValue()).floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed data matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed data on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed data matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Displayed data on <29-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed data on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed data on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		test.log(LogStatus.INFO,"Test step-3: Starting to test displayed data on <30-Jun-2016>");
		log.debug("Test step-3: Starting to test displayed data on <30-Jun-2016>");
		try {
			screen.click(one.depTotpatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = Float.valueOf(ExcelWSheetoneraad.getRow(4).getCell(1).getStringCellValue()).floatValue();
		istrue3 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue3) {
			log.info("Displayed data matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed data on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed data matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Displayed data on <30-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed data on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of total DEP volume on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		test.log(LogStatus.INFO,"Test step-4: Starting to test displayed data on <01-Jul-2016>");
		log.debug("Test step-4: Starting to test displayed data on <01-Jul-2016>");
		try {
			screen.click(one.depTotpatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = Float.valueOf(ExcelWSheetoneraad.getRow(5).getCell(1).getStringCellValue()).floatValue();
		istrue4 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue4) {
			log.info("Displayed data on <01-Jul-2016> matched with the expected results? - " + istrue4);
			test.log(LogStatus.PASS, "Test step-4: Testing of displayed data on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed data on <01-Jul-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Displayed data on <01-Jul-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-4: Testing of displayed data on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-4: Testing of displayed data on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		test.log(LogStatus.INFO,"Test step-5: Starting to test displayed data on <02-Jul-2016>");
		log.debug("Test step-5: Starting to test displayed data on <02-Jul-2016>");
		try {
			screen.click(one.depTotpatternOn702());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = Float.valueOf(ExcelWSheetoneraad.getRow(6).getCell(1).getStringCellValue()).floatValue();
		istrue5 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue5) {
			log.info("Displayed data matched with the expected results? - " + istrue5);
			test.log(LogStatus.PASS, "Test step-5: Testing of displayed data on <02-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed data on <02-Jul-2016> matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Displayed data on <02-Jul-2016> matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-5: Testing of displayed data on <02-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-5: Testing of displayed data on <02-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		test.log(LogStatus.INFO,"Test step-6: Starting to test displayed data for whole year");
		log.debug("Test step-6: Starting to test displayed data for whole year");
		one.depVolZoominButton().click();
		Thread.sleep(1000);
		one.YearButton().click();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(one.depTotpatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(7).getCell(1).getNumericCellValue();
		refData = refData.floatValue();
		istrue6 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue6) {
			log.info("Displayed data on whole duration matched with the expected results? - " + istrue6);
			test.log(LogStatus.PASS, "Test step-6: Testing of displayed data on whole duration is successful");
	    } else {
	    	log.info("Displayed data on whole duration matched with the expected results? - " + istrue6 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Displayed data on whole duration matched with the expected results? - " + istrue6 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-6: Testing of displayed data on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-6: Testing of displayed data on whole duration is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		istrue = istrue1&&istrue2&&istrue3&&istrue4&&istrue5&&istrue6;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
		
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Dload", }, priority = 3, enabled=true)
	public void t004NwTotVolDload() throws Exception {
		log.info("Test Case - t004: Starting to test total NW volumes in RA Analyst Dashboard by downloading the Excel report and comparing it withe the reference report");
		test.log(LogStatus.INFO, "Test Case - t004: Starting to test total NW volumes in RA Analyst Dashboard by downloading the Excel report and comparing it withe the reference report");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.nwVolMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.nwVolMaxButn().click();
		log.info("Clicked on NW vol maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of NW Total Volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of NW Total Volume");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result: - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of NW Total Volume is successful");
		} else {
			log.debug("Is time window matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Is time window matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of NW Total Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of NW Total Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"NwTotVol.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"NwTotVol.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"NwTotVol.xlsx", downloaddir+"NwTotVol.xlsx", 3, 0, 7, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference filetime window matching with the expected result: - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference filetime window matching with the expected result: - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Is downloaded Excel file matched with the reference filetime window matching with the expected result: - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}

	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 4, enabled=true)
	public void t005NwTotVolChart() throws Exception {
		log.info("Test Case - t005: Starting to test displayed total NW volumes in RA Analyst Dashboard");
		test.log(LogStatus.INFO, "Test Case - t005: Starting to test displayed total NW volumes in RA Analyst Dashboard");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.nwVolMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.nwVolMaxButn().click();
		log.info("Clicked on NW vol maximize button");
		Thread.sleep(5000);
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test displayed data on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed data on <29-Jun-2016>");
		try {
			screen.click(one.nwTotpatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = Float.valueOf(ExcelWSheetoneraad.getRow(3).getCell(5).getStringCellValue()).floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed data on <29-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of total NW volume on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed data on <29-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Displayed data on <29-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of total displayed NW volume on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of total displayed NW volume on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-2: Starting to test displayed data on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed data on <30-Jun-2016>");
		try {
			screen.click(one.nwTotpatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = Float.valueOf(ExcelWSheetoneraad.getRow(4).getCell(5).getStringCellValue()).floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed data on <30-Jun-2016> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed NW volume on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed data on <30-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Displayed data on <30-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed NW volume on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed NW volume on <30-Jun-2016> is Failed", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-3: Starting to test displayed NW volume on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed NW volume on <01-Jul-2016>");
		try {
			screen.click(one.nwTotpatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = Float.valueOf(ExcelWSheetoneraad.getRow(5).getCell(5).getStringCellValue()).floatValue();
		istrue3 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue3) {
			log.info("Displayed NW volume on <01-Jul-2016> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed NW volume on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed NW volume on <01-Jul-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Displayed NW volume on <01-Jul-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed NW volume on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed NW volume on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-4: Starting to test displayed data on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-4: Starting to test displayed data on <02-Jul-2016>");
		try {
			screen.click(one.nwTotpatternOn702());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = Float.valueOf(ExcelWSheetoneraad.getRow(6).getCell(5).getStringCellValue()).floatValue();
		istrue4 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue4) {
			log.info("Displayed NW volume on <02-Jul-2016> matched with the expected results? - " + istrue4);
			test.log(LogStatus.PASS, "Test step-4: Testing of displayed NW volume on <02-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed NW volume on <02-Jul-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Displayed NW volume on <02-Jul-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-4: Testing of displayed NW volume on <02-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-4: Testing of displayed NW volume on <02-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-5: Starting to test displayed data on whole duration");
		test.log(LogStatus.INFO, "Test step-5: Starting to test displayed data on whole duration");
		one.nwVolZoominButton().click();
		Thread.sleep(1000);
		one.YearButton().click();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(one.nwTotpatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = Float.valueOf(ExcelWSheetoneraad.getRow(7).getCell(5).getStringCellValue()).floatValue();
		istrue5 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue5) {
			log.info("Displayed NW volume on whole duration matched with the expected results? - " + istrue5);
			test.log(LogStatus.PASS, "Test step-5: Testing of displayed NW volume on whole duration is successful");
	    } else {
	    	log.info("Displayed NW volume on whole duration matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Displayed NW volume on whole duration matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-5: Testing of displayed NW volume on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-5: Testing of displayed NW volume on whole duration is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2&&istrue3&&istrue4&&istrue5;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
		
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Dload", }, priority = 5, enabled=true)
	public void t006ChgTotVolDload() throws Exception {
		log.info("Test Case - t006: Starting to test total CHG volumes in RA Analyst Dashboard by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t006: Starting to test total CHG volumes in RA Analyst Dashboard by downloading the Excel report and comparing it with the reference report");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.chgVolMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.chgVolMaxButn().click();
		log.info("Clicked on CHG vol maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of CHG Total Volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of CHG Total Volume");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result: - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of CHG Total Volume is successful");
		} else {
			log.debug("Is time window of CHG Total Volume matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Is time window of CHG Total Volume matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of CHG Total Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of CHG Total Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
			Thread.sleep(3000);
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"ChgTotVol.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"ChgTotVol.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"ChgTotVol.xlsx", downloaddir+"ChgTotVol.xlsx", 3, 0, 7, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference filetime window matching with the expected result: - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference filetime window matching with the expected result: - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Is downloaded Excel file matched with the reference filetime window matching with the expected result: - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}

	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 6, enabled=true)
	public void t007ChgTotVolChart() throws Exception {
		log.info("Test Case - t007: Starting to test displayed total CHG volumes in RA Analyst Dashboard");
		test.log(LogStatus.INFO, "Test Case - t007: Starting to test displayed total CHG volumes in RA Analyst Dashboard");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.depVolMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.chgVolMaxButn().click();
		log.info("Clicked on NW vol maximize button");
		Thread.sleep(5000);
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed CHG volume on <29-Jun-2016>");
		log.debug("Test step-1: Starting to test displayed CHG volume on <29-Jun-2016>");
		try {
			screen.click(one.chgTotpatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = Float.valueOf(ExcelWSheetoneraad.getRow(3).getCell(9).getStringCellValue()).floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed data matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed CHG volume on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed CHG volume on <29-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Testing of displayed CHG volume on <29-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed CHG volume on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed CHG volume on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed CHG volume on <30-Jun-2016>");
		log.debug("Test step-2: Starting to test displayed CHG volume on <30-Jun-2016>");
		try {
			screen.click(one.chgTotpatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = Float.valueOf(ExcelWSheetoneraad.getRow(4).getCell(9).getStringCellValue()).floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed CHG volume on <30-Jun-2016> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed CHG volume on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed CHG volume on <30-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Testing of displayed CHG volume on <30-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.info("Test step-2: Testing of displayed CHG volume on 30-Jun is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed CHG volume on 30-Jun is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed CHG volume on <01-Jul-2016>");
		log.debug("Test step-3: Starting to test displayed CHG volume on <01-Jul-2016>");
		try {
			screen.click(one.chgTotpatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = Float.valueOf(ExcelWSheetoneraad.getRow(5).getCell(9).getStringCellValue()).floatValue();
		istrue3 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue3) {
			log.info("Displayed CHG volume on <01-Jul-2016> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed CHG volume on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed CHG volume matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Testing of displayed CHG volume on <01-Jul-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.info("Test step-3: Testing of displayed CHG volume on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed CHG volume on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		test.log(LogStatus.INFO, "Test step-4: Starting to test displayed CHG volume on <02-Jul-2016>");
		log.debug("Test step-4: Starting to test displayed CHG volume on <02-Jul-2016>");
		try {
			screen.click(one.chgTotpatternOn702());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = Float.valueOf(ExcelWSheetoneraad.getRow(6).getCell(9).getStringCellValue()).floatValue();
		istrue4 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue4) {
			log.info("Displayed CHG volume on <02-Jul-2016> matched with the expected results? - " + istrue4);
			test.log(LogStatus.PASS, "Test step-4: Testing of displayed CHG volume on <02-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed CHG volume on <02-Jul-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-4: Testing of displayed CHG volume on <02-Jul-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.info("Test step-4: Testing of displayed CHG volume on <02-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-4: Testing of displayed CHG volume on <02-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		test.log(LogStatus.INFO, "Test step-5: Starting to test displayed CHG volume on whole duration");
		log.debug("Test step-5: Starting to test displayed CHG volume on whole duration");
		one.chgVolZoominButton().click();
		Thread.sleep(1000);
		one.YearButton().click();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(one.chgTotpatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(7).getCell(9).getNumericCellValue();
		refData = refData.floatValue();
		istrue5 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue5) {
			log.info("Displayed CHG volume on whole duration matched with the expected results? - " + istrue5);
			test.log(LogStatus.PASS, "Test step-5: Testing of displayed CHG volume on whole duration is successful");
	    } else {
	    	log.info("Displayed data matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-5: Displayed CHG volume on whole duration matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-5: Testing of displayed CHG volume on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-5: Testing of displayed CHG volume on whole duration is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2&&istrue3&&istrue4&&istrue5;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
		
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Dload" }, priority = 7, enabled=true)
	public void t008SubsCountDload() throws Exception {
		log.info("Test Case - t008: Starting to test total subscriber count in RA Analyst Dashboard by downloading the Excel report and comparing it withe the reference report");
		test.log(LogStatus.INFO, "Test Case - t008: Starting to test total subscriber count in RA Analyst Dashboard by downloading the Excel report and comparing it withe the reference report");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.subsCountMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.subsCountMaxButn().click();
		log.info("Clicked on suscribe count maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of subscriber count");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of subscriber count");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result: - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of subscriber count is successful");
		} else {
			log.debug("Is time window matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of subscriber count is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of subscriber count is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"subsCount.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"subsCount.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"subsCount.xlsx", downloaddir+"subsCount.xlsx", 3, 0, 15, 3);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference filetime window matching with the expected result: - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference filetime window matching with the expected result: - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference filetime window matching with the expected result: - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 8, enabled=true)
	public void t009SubsCountChart() throws Exception {
		log.info("Test Case - t009: Starting to test displayed total subscriber count in RA Analyst Dashboard");
		test.log(LogStatus.INFO, "Test Case - t009: Starting to test displayed total subscriber count in RA Analyst Dashboard");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.subsCountMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.subsCountMaxButn().click();
		log.info("Clicked on subscriber count maximize button");
		Thread.sleep(5000);
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test displayed DEP subscriber count on <28-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed DEP subscriber count on <28-Jun-2016>");
		try {
			screen.click(one.depSubspatternOn628());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(13).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed DEP subscriber count on <28-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed DEP subscriber count on <28-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed DEP subscriber count on <28-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed DEP subscriber count on <28-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed DEP subscrier count on 28-Jun is Failed\r\n");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed DEP subscrier count on 28-Jun is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-2: Starting to test displayed CHG subscriber count on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed CHG subscriber count on <29-Jun-2016>");
		try {
			screen.click(one.chgSubspatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(14).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed CHG subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed CHG subscriber count on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed CHG subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed CHG subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed CHG subscriber count on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed CHG subscriber count on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-3: Starting to test displayed DEP subscriber count on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed DEP subscriber count on <29-Jun-2016>");
		try {
			screen.click(one.depSubspatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(15).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue3 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue3) {
			log.info("Displayed DEP subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed DEP subscriber count on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed data matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed DEP subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed DEP subscriber count on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-4: Starting to test displayed NW subscriber count on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-4: Starting to test displayed NW subscriber count on <29-Jun-2016>");
		try {
			screen.click(one.nwSubspatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(16).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue4 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue4) {
			log.info("Displayed NW subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue4);
			test.log(LogStatus.PASS, "Test step-4: Testing of displayed NW subscriber count on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed NW subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-4: Displayed NW subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-4: Testing of displayed NW subscriber count on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-4: Testing of displayed NW subscriber count on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-5: Starting to test displayed CHG subscriber count on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-5: Starting to test displayed CHG subscriber count on <30-Jun-2016>");
		try {
			screen.click(one.chgSubspatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(17).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue5 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue5) {
			log.info("Displayed CHG subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue5);
			test.log(LogStatus.PASS, "Test step-5: Testing of displayed CHG subscrier count on 29-Jun is successful");
	    } else {
	    	log.info("Displayed CHG subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-5: Displayed CHG subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-5: Test step-5: Testing of displayed CHG subscrier count on 29-Jun is Failed");
	    	test.log(LogStatus.FAIL, "Test step-5: Testing of displayed CHG subscrier count on 29-Jun is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-6: Starting to test displayed DEP subscriber count on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-6: Starting to test displayed DEP subscriber count on <30-Jun-2016>");
		try {
			screen.click(one.depSubspatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(18).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue6 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue6) {
			log.info("Displayed DEP subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue6);
			test.log(LogStatus.PASS, "Test step-6: Testing of displayed DEP subscriber count on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed DEP subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue6 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-6: Displayed DEP subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue6 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-6: Testing of displayed DEP subscriber count on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-6: Testing of displayed DEP subscriber count on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-7: Starting to test displayed NW subscriber count on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-7: Starting to test displayed NW subscriber count on <30-Jun-2016>");
		try {
			screen.click(one.nwSubspatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(19).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue7 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue7) {
			log.info("Displayed NW subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue7);
			test.log(LogStatus.PASS, "Test step-7: Testing of total NW subscriber count on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed NW subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue7 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-7: Displayed NW subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue7 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-7: Testing of total NW subscriber count on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-7: Testing of total NW subscriber count on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-8: Starting to test displayed CHG subscriber count on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-8: Starting to test displayed CHG subscriber count on <01-Jul-2016>");
		try {
			screen.click(one.chgSubspatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(20).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue8 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue8) {
			log.info("Displayed CHG subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue8);
			test.log(LogStatus.PASS, "Test step-8: Testing of displayed CHG subscriber count on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed CHG subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue8 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-8: Displayed CHG subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue8 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-8: Testing of displayed CHG subscriber count on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-8: Testing of displayed CHG subscriber count on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-9: Starting to test displayed DEP subscriber count on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-9: Starting to test displayed DEP subscriber count on <01-Jul-2016>");
		try {
			screen.click(one.depSubspatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(21).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue9 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue9) {
			log.info("Displayed DEP subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue9);
			test.log(LogStatus.PASS, "Test step-9: Testing of displayed DEP subscriber count on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed DEP subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue9 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-9: Displayed DEP subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue9 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-9: Testing of displayed DEP subscriber count on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-9: Testing of displayed DEP subscriber count on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-10: Starting to test displayed NW subscriber count on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-10: Starting to test displayed NW subscriber count on <01-Jul-2016>");
		try {
			screen.click(one.nwSubspatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(22).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue10 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue10) {
			log.info("Displayed NW subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue10);
			test.log(LogStatus.PASS, "Test step-10: Testing of displayed NW subscriber count on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed NW subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue10 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-10: Displayed NW subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue10 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-10: Testing of displayed NW subscriber count on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-10: Testing of displayed NW subscriber count on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-11: Starting to test displayed CHG subscriber count on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-11: Starting to test displayed CHG subscriber count on <02-Jul-2016>");
		try {
			screen.click(one.chgSubspatternOn702());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(23).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue11 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue11) {
			log.info("Displayed CHG subscriber count on <02-Jul-2016> matched with the expected results? - " + istrue11);
			test.log(LogStatus.PASS, "Test step-11: Testing of displayed CHG subscriber count on <02-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed CHG subscriber count on <02-Jul-2016> matched with the expected results? - " + istrue11 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-11: Displayed CHG subscriber count on <02-Jul-2016> matched with the expected results? - " + istrue11 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-11: Testing of displayed CHG subscriber count on <02-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-11: Testing of displayed CHG subscriber count on <02-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-12: Starting to test displayed DEP subscriber count on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-12: Starting to test displayed DEP subscriber count on <02-Jul-2016>");
		try {
			screen.click(one.depSubspatternOn702());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(24).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue12 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue12) {
			log.info("Displayed DEP subscriber count on <02-Jul-2016> matched with the expected results? - " + istrue12);
			test.log(LogStatus.PASS, "Test step-12: Testing of displayed DEP subscriber count on <02-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed DEP subscriber count on <02-Jul-2016> matched with the expected results? - " + istrue12 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-12: Displayed DEP subscriber count on <02-Jul-2016> matched with the expected results? - " + istrue12 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-12: Testing of displayed DEP subscriber count on <02-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-12: Testing of displayed DEP subscriber count on <02-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-13: Starting to test displayed NW subscriber count on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-13: Starting to test displayed NW subscriber count on <02-Jul-2016>");
		try {
			screen.click(one.nwSubspatternOn702());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(25).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue13 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue13) {
			log.info("Displayed NW subscriber count on <02-Jul-2016> matched with the expected results? - " + istrue13);
			test.log(LogStatus.PASS, "Test step-13: Testing of displayed NW subscriber count on <02-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed NW subscriber count on <02-Jul-2016> matched with the expected results? - " + istrue13 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-13: Displayed NW subscriber count on <02-Jul-2016> matched with the expected results? - " + istrue13 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-13: Testing of displayed NW subscriber count on <02-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-13: Testing of displayed NW subscriber count on <02-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-14: Starting to test displayed CHG subscriber count on whole data");
		test.log(LogStatus.INFO, "Test step-14: Starting to test displayed CHG subscriber count on whole data");
		one.subsCounZoominButton().click();
		Thread.sleep(1000);
		one.YearButton().click();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(one.chgSubspatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(26).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue14 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue14) {
			log.info("Displayed CHG subscriber count on whole data matched with the expected results? - " + istrue14);
			test.log(LogStatus.PASS, "Test step-14: Testing of displayed CHG subscriber count on whole data is successful");
	    } else {
	    	log.info("Displayed CHG subscriber count on whole data matched with the expected results? - " + istrue14 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-14: Displayed CHG subscriber count on whole data matched with the expected results? - " + istrue14 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-14: Testing of displayed CHG subscriber count on whole data is Failed");
	    	test.log(LogStatus.FAIL, "Test step-14: Testing of displayed CHG subscriber count on whole data is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-15: Starting to test displayed DEP subscriber count on whole data");
		test.log(LogStatus.INFO, "Test step-15: Starting to test displayed DEP subscriber count on whole data");
		try {
			screen.click(one.depSubspatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(27).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue15 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue15) {
			log.info("Displayed DEP subscriber count on whole data matched with the expected results? - " + istrue15);
			test.log(LogStatus.PASS, "Test step-15: Testing of displayed DEP subscriber count on whole data is successful");
	    } else {
	    	log.info("Displayed DEP subscriber count on whole data matched with the expected results? - " + istrue15 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-15: Displayed DEP subscriber count on whole data matched with the expected results? - " + istrue15 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-15: Testing of displayed DEP subscriber count on whole data is Failed");
	    	test.log(LogStatus.FAIL, "Test step-15: Testing of displayed DEP subscriber count on whole data is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-16: Starting to test displayed NW subscriber count on whole data");
		test.log(LogStatus.INFO, "Test step-16: Starting to test displayed NW subscriber count on whole data");
		try {
			screen.click(one.nwSubspatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(28).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue16 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue16) {
			log.info("Displayed NW subscriber count on whole data matched with the expected results? - " + istrue16);
			test.log(LogStatus.PASS, "Test step-16: Testing of displayed NW subscriber count on whole data is successful");
	    } else {
	    	log.info("Displayed NW subscriber count on whole data matched with the expected results? - " + istrue16 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-16: Displayed NW subscriber count on whole data matched with the expected results? - " + istrue16 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-16: Testing of displayed NW subscriber count on whole data is Failed");
	    	test.log(LogStatus.FAIL, "Test step-16: Testing of displayed NW subscriber count on whole data is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		istrue = istrue1&&istrue2&&istrue3&&istrue4&&istrue5&&istrue6&&istrue7&&istrue8&&istrue9&&istrue10&&istrue11&&istrue12&& istrue13&&istrue14&&istrue15&&istrue16;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Dload" }, priority = 9, enabled=true)
	public void t010SessnCountDload() throws Exception {
		log.info("Test Case - t010: Starting to test total session count in RA Analyst Dashboard by downloading the Excel report and comparing it withe the reference report");
		test.log(LogStatus.INFO, "Test Case - t010: Starting to test total session count in RA Analyst Dashboard by downloading the Excel report and comparing it withe the reference report");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.sessnCountMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.sessnCountMaxButn().click();
		log.info("Clicked on session count maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of session count");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of session count");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result: - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of session count is successful");
		} else {
			log.debug("Is time window matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of session count is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of session count is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"sessnCount.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(3000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(3000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"sessnCount.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"sessnCount.xlsx", downloaddir+"sessnCount.xlsx", 3, 0, 15, 3);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference filetime window matching with the expected result: - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference filetime window matching with the expected result: - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference filetime window matching with the expected result: - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 10, enabled=true)
	public void t011tSessnCountChart() throws Exception {
		log.info("Test Case - t011: Starting to test displayed session count in RA Analyst Dashboard");
		test.log(LogStatus.INFO, "Test Case - t011: Starting to test displayed session count in RA Analyst Dashboard");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.sessnCountMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.sessnCountMaxButn().click();
		log.info("Clicked on subscriber count maximize button");
		Thread.sleep(5000);
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test displayed DEP session count on <28-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed DEP session count on <28-Jun-2016>");
		try {
			screen.click(one.depSessnpatternOn628());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(13).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed DEP session count on <28-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed DEP session count on 28-Jun is success");
	    } else {
	    	log.info("Displayed DEP session count on <28-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Displayed DEP session count on <28-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed DEP session count on 28-Jun is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed DEP session count on 28-Jun is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-2: Starting to test displayed CHG session count on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed CHG session count on <29-Jun-2016>");
		try {
			screen.click(one.chgSessnpatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(14).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed CHG session count on <29-Jun-2016> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed CHG session count on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed CHG session count on <29-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed CHG session count on <29-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed CHG session count on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed CHG session count on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-3: Starting to test displayed DEP session count on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed DEP session count on <29-Jun-2016>");
		try {
			screen.click(one.depSessnpatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(15).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue3 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue3) {
			log.info("Displayed DEP session count on <29-Jun-2016> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed DEP session count on <29-Jun-2016> is successfull");
	    } else {
	    	log.info("Displayed DEP session count on <29-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed DEP session count on <29-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed DEP session count on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed DEP session count on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-4: Starting to test displayed NW session count on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-4: Starting to test displayed NW session count on <29-Jun-2016>");
		try {
			screen.click(one.nwSessnpatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(16).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue4 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue4) {
			log.info("Displayed NW session count on <29-Jun-2016> matched with the expected results? - " + istrue4);
			test.log(LogStatus.PASS, "Test step-4: Testing of total displayed NW session count on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed NW session count on <29-Jun-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-4: Displayed NW session count on <29-Jun-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-4: Testing of total displayed NW session count on <29-Jun-2016> is");
	    	test.log(LogStatus.FAIL, "Test step-4: Testing of total displayed NW session count on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-5: Starting to test displayed CHG session count on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-5: Starting to test displayed CHG session count on <30-Jun-2016>");
		try {
			screen.click(one.chgSessnpatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(17).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue5 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue5) {
			log.info("Displayed CHG session count on <30-Jun-2016> matched with the expected results? - " + istrue5);
			test.log(LogStatus.PASS, "Test step-5: Testing of displayed CHG session count on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed CHG session count on <30-Jun-2016> matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-5: Displayed CHG session count on <30-Jun-2016> matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-5: Testing of displayed CHG session count on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-5: Testing of displayed CHG session count on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-6: Starting to test displayed DEP session count on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-6: Starting to test displayed DEP session count on <30-Jun-2016>");
		try {
			screen.click(one.depSessnpatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(18).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue6 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue6) {
			log.info("Displayed DEP session count on <30-Jun-2016> matched with the expected results? - " + istrue6);
			test.log(LogStatus.PASS, "Test step-6: Testing of displayed DEP session count on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed DEP session count on <30-Jun-2016> matched with the expected results? - " + istrue6 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-6: Displayed DEP session count on <30-Jun-2016> matched with the expected results? - " + istrue6 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-6: Testing of displayed DEP session count on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-6: Testing of displayed DEP session count on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-7: Starting to test displayed NW session count on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-7: Starting to test displayed NW session count on <30-Jun-2016>");
		try {
			screen.click(one.nwSessnpatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(19).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue7 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue7) {
			log.info("Displayed NW session count on <30-Jun-2016> matched with the expected results? - " + istrue7);
			test.log(LogStatus.PASS, "Test step-7: Testing of displayed NW session count on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed NW session count on <30-Jun-2016> matched with the expected results? - " + istrue7 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-7: Displayed NW session count on <30-Jun-2016> matched with the expected results? - " + istrue7 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-7: Testing of displayed NW session count on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-7: Testing of displayed NW session count on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-8: Starting to test displayed CHG session count on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-8: Starting to test displayed CHG session count on <01-Jul-2016>");
		try {
			screen.click(one.chgSessnpatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(20).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue8 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue8) {
			log.info("Displayed CHG session count on <01-Jul-2016> matched with the expected results? - " + istrue8);
			test.log(LogStatus.PASS, "Test step-8: Testing of displayed CHG session count on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed CHG session count on <01-Jul-2016> matched with the expected results? - " + istrue8 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-8: Displayed CHG session count on <01-Jul-2016> matched with the expected results? - " + istrue8 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-8: Testing of displayed CHG session count on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-8: Testing of displayed CHG session count on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-9: Starting to test displayed DEP session count on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-9: Starting to test displayed DEP session count on <01-Jul-2016>");
		try {
			screen.click(one.depSessnpatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(21).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue9 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue9) {
			log.info("Displayed DEP session count on <01-Jul-2016> matched with the expected results? - " + istrue9);
			test.log(LogStatus.PASS, "Test step-9: Testing of displayed DEP session count on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed DEP session count on <01-Jul-2016> matched with the expected results? - " + istrue9 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-9: Displayed DEP session count on <01-Jul-2016> matched with the expected results? - " + istrue9 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-9: Testing of displayed DEP session count on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-9: Testing of displayed DEP session count on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-10: Starting to test displayed NW session count on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-10: Starting to test displayed NW session count on <01-Jul-2016>");
		try {
			screen.click(one.nwSessnpatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(22).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue10 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue10) {
			log.info("Displayed NW session count on <01-Jul-2016> matched with the expected results? - " + istrue10);
			test.log(LogStatus.PASS, "Test step-10: Testing of displayed NW session count on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed NW session count on <01-Jul-2016> matched with the expected results? - " + istrue10 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-10: Displayed NW session count on <01-Jul-2016> matched with the expected results? - " + istrue10 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-10: Testing of displayed NW session count on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-10: Testing of displayed NW session count on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-11: Starting to test displayed CHG session count on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-11: Starting to test displayed CHG session count on <02-Jul-2016>");
		try {
			screen.click(one.chgSessnpatternOn702());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(23).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue11 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue11) {
			log.info("Displayed CHG session count on <02-Jul-2016> matched with the expected results? - " + istrue11);
			test.log(LogStatus.PASS, "Test step-11: Testing of displayed CHG session count on <02-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed CHG session count on <02-Jul-2016> matched with the expected results? - " + istrue11 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-11: Displayed CHG session count on <02-Jul-2016> matched with the expected results? - " + istrue11 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-11: Testing of displayed CHG session count on <02-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-11: Testing of displayed CHG session count on <02-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-12: Starting to test displayed DEP session count on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-12: Starting to test displayed DEP session count on <02-Jul-2016>");
		try {
			screen.click(one.depSessnpatternOn702());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(24).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue12 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue12) {
			log.info("Displayed DEP session count on <02-Jul-2016> matched with the expected results? - " + istrue12);
			test.log(LogStatus.PASS, "Test step-12: Testing of displayed DEP session count on <02-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed DEP session count on <02-Jul-2016> matched with the expected results? - " + istrue12 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-12: Displayed DEP session count on <02-Jul-2016> matched with the expected results? - " + istrue12 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-12: Testing of displayed DEP session count on <02-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-12: Testing of displayed DEP session count on <02-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-13: Starting to test displayed NW session count on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-13: Starting to test displayed NW session count on <02-Jul-2016>");
		try {
			screen.click(one.nwSessnpatternOn702());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(25).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue13 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue13) {
			log.info("Displayed NW session count on <02-Jul-2016> matched with the expected results? - " + istrue13);
			test.log(LogStatus.PASS, "Test step-13: Testing of displayed NW session count on <02-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed NW session count on <02-Jul-2016> matched with the expected results? - " + istrue13 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-13: Displayed NW session count on <02-Jul-2016> matched with the expected results? - " + istrue13 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-13: Testing of displayed NW session count on <02-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-13: Testing of displayed NW session count on <02-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-14: Starting to test displayed CHG session count on whole duration");
		test.log(LogStatus.INFO, "Test step-14: Starting to test displayed CHG session count on whole duration");
		one.sessnCountZoominButton().click();
		Thread.sleep(1000);
		one.YearButton().click();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(one.chgSessnpatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(26).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue14 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue14) {
			log.info("Displayed CHG session count on whole duration matched with the expected results? - " + istrue14);
			test.log(LogStatus.PASS, "Test step-14: Testing of displayed CHG session count on whole duration is successful");
	    } else {
	    	log.info("Displayed CHG session count on whole duration matched with the expected results? - " + istrue14 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-14: Displayed CHG session count on whole duration matched with the expected results? - " + istrue14 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-14: Testing of displayed CHG session count on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-14: Testing of displayed CHG session count on whole duration is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-15: Starting to test displayed DEP session count on whole duration");
		test.log(LogStatus.INFO, "Test step-15: Starting to test displayed DEP session count on whole duration");
		try {
			screen.click(one.depSessnpatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(27).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue15 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue15) {
			log.info("Displayed DEP session count on whole duration matched with the expected results? - " + istrue15);
			test.log(LogStatus.PASS, "Test step-15: Testing of displayed DEP session count on whole duration is successful");
	    } else {
	    	log.info("Displayed DEP session count on whole duration matched with the expected results? - " + istrue15 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-15: Displayed DEP session count on whole duration matched with the expected results? - " + istrue15 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-15: Testing of displayed DEP session count on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-15: Testing of displayed DEP session count on whole duration is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-16: Starting to test displayed NW session count on whole data");
		test.log(LogStatus.INFO, "Test step-16: Starting to test displayed NW session count on whole data");
		try {
			screen.click(one.nwSessnpatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		displayedData = Float.valueOf(one.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(28).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue16 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue16) {
			log.info("Displayed data matched with the expected results? - " + istrue16);
			test.log(LogStatus.PASS, "Test step-16: Testing of displayed NW session count on whole data is successful");
	    } else {
	    	log.info("Displayed data matched with the expected results? - " + istrue16 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-16: Displayed data matched with the expected results? - " + istrue16 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-16: Testing of displayed NW session count on whole data is Failed");
	    	test.log(LogStatus.FAIL, "Test step-16: Testing of displayed NW session count on whole data is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2&&istrue3&&istrue4&&istrue5&&istrue6&&istrue7&&istrue8&&istrue9&&istrue10&&istrue11&&istrue12&& istrue13&&istrue14&&istrue15&&istrue16;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Dload" }, priority = 11, enabled=true)
	public void t012RevAssrnceDload() throws Exception {
		log.info("Test Case - t012: Starting to test Usage Assurance in RA Analyst Dashboard by downloading the Excel report and comparing it withe the reference report");
		test.log(LogStatus.INFO, "Test Case - t012: Starting to test Usage Assurance in RA Analyst Dashboard by downloading the Excel report and comparing it withe the reference report");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.usgAssrnceMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.usgAssrnceMaxButn().click();
		log.info("Clicked on Usage Assurance maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Usage Assurance");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Usage Assurance");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of Usage Assurance matching with the expected result: - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Usage Assurance is successful");
		} else {
			log.debug("Is time window of Usage Assurance matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Usage Assurance matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Usage Assurance is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Usage Assurance is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"usageAssurance.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(3000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(3000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"usageAssurance.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"usageAssurance.xlsx", downloaddir+"usageAssurance.xlsx", 2, 0, 10, 10);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file: - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Test step-2: Is downloaded Excel file matched with the reference file: - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Is downloaded Excel file matched with the reference file: - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed\r\n", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 12, enabled=true)
	public void t013RevAssrnceChart() throws Exception {
		log.info("Test Case - t013: Starting to test displayed Revenue Assurance in RA Analyst Dashboard on whole duration");
		test.log(LogStatus.INFO, "Test Case - t013: Starting to test displayed Usage Assurance in RA Analyst Dashboard on whole duration");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.usgAssrnceMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.usgAssrnceMaxButn().click();
		log.info("Clicked on Usage Assurance maximize button");
		Thread.sleep(5000);
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test displayed gap volume of Usage Assurance on whole duration");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed gap volume of Usage Assurance on whole duration");
		displayedData = Float.valueOf(one.totGapVol().getText()).floatValue();
		refData = ExcelWSheetoneraad.getRow(34).getCell(1).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed total Gap Volume of Usage Assurance on whole duration matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of total Gap Volume of Usage Assurance on whole duration is successful");
	    } else {
	    	log.info("Displayed total Gap Volume of Usage Assurance on whole duration matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed total Gap Volume of Usage Assurance on whole duration matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of total Gap Volume of Usage Assurance on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of total Gap Volume of Usage Assurance on whole duration is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-2: Starting to test displayed missing volume of Usage Assurance on whole duration");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed missing volume of Usage Assurance on whole duration");
		displayedData = Float.valueOf(one.missingGapVol().getText()).floatValue();
		refData = ExcelWSheetoneraad.getRow(34).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed missing volume of Usage Assurance on whole duration matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of missing volume of Usage Assurance on duration data is successful");
	    } else {
	    	log.info("Displayed missing volume of Usage Assurance on whole duration matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed missing volume of Usage Assurance on whole duration matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of missing volume of Usage Assurance on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of missing volume of Usage Assurance on whole duration is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-3: Starting to test displayed under-reported volume of Usage Assurance on whole duration");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed under-reported volume of Usage Assurance on whole duration");
		displayedData = Float.valueOf(one.underGapVol().getText()).floatValue();
		refData = ExcelWSheetoneraad.getRow(34).getCell(3).getNumericCellValue();
		refData = refData.floatValue();
		istrue3 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue3) {
			log.info("Displayed under-reported volume of Usage Assurance on whole duration matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of under-reported Volume of Usage Assurance on whole duration is successful");
	    } else {
	    	log.info("Displayed under-reported volume of Usage Assurance on whole duration matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed under-reported volume of Usage Assurance on whole duration matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of under-reported Volume of Usage Assurance on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of under-reported Volume of Usage Assurance on whole duration is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-4: Starting to test displayed over-reported volume of Usage Assurance on whole duration");
		test.log(LogStatus.INFO, "Test step-4: Starting to test displayed over-reported volume of Usage Assurance on whole duration");
		displayedData = Float.valueOf(one.overGapVol().getText()).floatValue();
		refData = ExcelWSheetoneraad.getRow(34).getCell(4).getNumericCellValue();
		refData = refData.floatValue();
		istrue4 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue4) {
			log.info("Displayed displayed over-reported volume of Usage Assurance on whole duration matched with the expected results? - " + istrue4);
			test.log(LogStatus.PASS, "Test step-4: Testing of over-reported Volume of Usage Assurance on whole duration is successful");
	    } else {
	    	log.info("Displayed displayed over-reported volume of Usage Assurance on whole duration matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-4: Displayed displayed over-reported volume of Usage Assurance on whole duration matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-4: Testing of over-reported Volume of Usage Assurance on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-4: Testing of over-reported Volume of Usage Assurance on whole duration is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2&&istrue3&&istrue4;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 13, enabled=true)
	public void t014RevAssrnceChart29() throws Exception {
		log.info("Test Case - t014: Starting to test displayed Revenue Assurance in RA Analyst Dashboard on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test Case - t014: Starting to test displayed Usage Assurance in RA Analyst Dashboard on <29-Jun-2016>");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.usgAssrnceMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.usgAssrnceMaxButn().click();
		log.info("Clicked on Usage Assurance maximize button");
		Thread.sleep(10000);
		ccc.setDateTimeBar29();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test displayed gap volume of Usage Assurance on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed gap volume of Usage Assurance on <29-Jun-2016>");
		displayedData = Float.valueOf(one.totGapVol().getText()).floatValue();
		refData = ExcelWSheetoneraad.getRow(36).getCell(1).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed total Gap Volume of Usage Assurance on <29-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of total Gap Volume of Usage Assurance on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed total Gap Volume of Usage Assurance on <29-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed total Gap Volume of Usage Assurance on <29-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of total Gap Volume of Usage Assurance on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of total Gap Volume of Usage Assurance on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-2: Starting to test displayed missing volume of Usage Assurance on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed missing volume of Usage Assurance on <29-Jun-2016>");
		displayedData = Float.valueOf(one.missingGapVol().getText()).floatValue();
		refData = ExcelWSheetoneraad.getRow(36).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed missing volume of Usage Assurance on <29-Jun-2016> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of missing Volume of Usage Assurance on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed missing volume of Usage Assurance on <29-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed missing volume of Usage Assurance on <29-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of missing Volume of Usage Assurance on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of missing Volume of Usage Assurance on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-3: Starting to test displayed under-reported volume of Usage Assurance on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed under-reported volume of Usage Assurance on <29-Jun-2016>");
		displayedData = Float.valueOf(one.underGapVol().getText()).floatValue();
		refData = ExcelWSheetoneraad.getRow(36).getCell(3).getNumericCellValue();
		refData = refData.floatValue();
		istrue3 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue3) {
			log.info("Displayed under-reported Volume of Usage Assurance on <29-Jun-2016> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed under-reported Volume of Usage Assurance on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed under-reported Volume of Usage Assurance on <29-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed under-reported Volume of Usage Assurance on <29-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed under-reported Volume of Usage Assurance on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed under-reported Volume of Usage Assurance on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-4: Starting to test displayed over-reported volume of Usage Assurance on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-4: Starting to test displayed over-reported volume of Usage Assurance on <29-Jun-2016>");
		displayedData = Float.valueOf(one.overGapVol().getText()).floatValue();
		refData = ExcelWSheetoneraad.getRow(36).getCell(4).getNumericCellValue();
		refData = refData.floatValue();
		istrue4 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue4) {
			log.info("Displayed over-reported volume of Usage Assurance on <29-Jun-2016> matched with the expected results? - " + istrue4);
			test.log(LogStatus.PASS, "Test step-4: Testing of displayed over-reported Volume of Usage Assurance on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed over-reported volume of Usage Assurance on <29-Jun-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-4: Displayed over-reported volume of Usage Assurance on <29-Jun-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-4: Testing of displayed over-reported Volume of Usage Assurance on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-4: Testing of displayed over-reported Volume of Usage Assurance on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2&&istrue3&&istrue4;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
		
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 14, enabled=true)
	public void t015RevAssrnceChart30() throws Exception {
		log.info("Test Case - t015: Starting to test displayed Revenue Assurance in RA Analyst Dashboard on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test Case - t015: Starting to test displayed Usage Assurance in RA Analyst Dashboard on <30-Jun-2016>");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.usgAssrnceMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.usgAssrnceMaxButn().click();
		log.info("Clicked on Usage Assurance maximize button");
		Thread.sleep(5000);
		ccc.setDateTimeBar30();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test displayed gap volume of Usage Assurance on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed gap volume of Usage Assurance on <30-Jun-2016>");
		displayedData = Float.valueOf(one.totGapVol().getText()).floatValue();
		refData = ExcelWSheetoneraad.getRow(37).getCell(1).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed total Gap Volume of Usage Assurance on <30-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed total Gap Volume of Usage Assurance on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed total Gap Volume of Usage Assurance on <30-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed total Gap Volume of Usage Assurance on <30-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed total Gap Volume of Usage Assurance on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed total Gap Volume of Usage Assurance on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-2: Starting to test displayed missing volume of Usage Assurance on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed missing volume of Usage Assurance on <30-Jun-2016>");
		displayedData = Float.valueOf(one.missingGapVol().getText()).floatValue();
		refData = ExcelWSheetoneraad.getRow(37).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed missing volume of Usage Assurance on <30-Jun-2016> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed missing Volume of Usage Assurance on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed missing volume of Usage Assurance on <30-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed missing volume of Usage Assurance on <30-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed missing Volume of Usage Assurance on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed missing Volume of Usage Assurance on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-3: Starting to test displayed under-reported volume of Usage Assurance on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed under-reported volume of Usage Assurance on <30-Jun-2016>");
		displayedData = Float.valueOf(one.underGapVol().getText()).floatValue();
		refData = ExcelWSheetoneraad.getRow(37).getCell(3).getNumericCellValue();
		refData = refData.floatValue();
		istrue3 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue3) {
			log.info("Displayed under-reported Volume of Usage Assurance on <30-Jun-2016> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed under-reported Volume of Usage Assurance on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed under-reported Volume of Usage Assurance on <30-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed under-reported Volume of Usage Assurance on <30-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed under-reported Volume of Usage Assurance on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed under-reported Volume of Usage Assurance on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2&&istrue3;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
		
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 15, enabled=true)
	public void t016RevAssrnceChart01() throws Exception {
		log.info("Test Case - t016: Starting to test displayed Revenue Assurance in RA Analyst Dashboard on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test Case - t016: Starting to test displayed Usage Assurance in RA Analyst Dashboard on <01-Jul-2016>");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.usgAssrnceMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.usgAssrnceMaxButn().click();
		log.info("Clicked on Usage Assurance maximize button");
		Thread.sleep(5000);
		ccc.setDateTimeBar01();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test displayed total gap volume of Usage Assurance on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed total gap volume of Usage Assurance on <01-Jul-2016>");
		displayedData = Float.valueOf(one.totGapVol().getText()).floatValue();
		refData = ExcelWSheetoneraad.getRow(38).getCell(1).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed total Gap Volume of Usage Assurance on <01-Jul-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed total Gap Volume of Usage Assurance on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed total Gap Volume of Usage Assurance on <01-Jul-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed total Gap Volume of Usage Assurance on <01-Jul-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed total Gap Volume of Usage Assurance on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed total Gap Volume of Usage Assurance on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-2: Starting to test displayed missing volume of Usage Assurance on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed missing volume of Usage Assurance on <01-Jul-2016>");
		displayedData = Float.valueOf(one.missingGapVol().getText()).floatValue();
		refData = ExcelWSheetoneraad.getRow(38).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed missing Volume of Usage Assurance on <01-Jul-2016> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed missing Volume of Usage Assurance on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed missing Volume of Usage Assurance on <01-Jul-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed missing Volume of Usage Assurance on <01-Jul-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed missing Volume of Usage Assurance on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed missing Volume of Usage Assurance on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-3: Starting to test displayed under-reported volume of Usage Assurance on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed under-reported volume of Usage Assurance on <01-Jul-2016>");
		displayedData = Float.valueOf(one.underGapVol().getText()).floatValue();
		refData = ExcelWSheetoneraad.getRow(38).getCell(3).getNumericCellValue();
		refData = refData.floatValue();
		istrue3 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue3) {
			log.info("Displayed under-reported Volume of Usage Assurance on <01-Jul-2016> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed under-reported Volume of Usage Assurance on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed under-reported Volume of Usage Assurance on <01-Jul-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed under-reported Volume of Usage Assurance on <01-Jul-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed under-reported Volume of Usage Assurance on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed under-reported Volume of Usage Assurance on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2&&istrue3;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
		
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 16, enabled=true)
	public void t017RevAssrnceChart02() throws Exception {
		log.info("Test Case - t017: Starting to test displayed Revenue Assurance in RA Analyst Dashboard on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test Case - t017: Starting to test displayed Usage Assurance in RA Analyst Dashboard on <01-Jul-2016>");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.usgAssrnceMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.usgAssrnceMaxButn().click();
		log.info("Clicked on Usage Assurance maximize button");
		Thread.sleep(5000);
		ccc.setDateTimeBar02();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test displayed over-reported volume of Usage Assurance on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed over-reported volume of Usage Assurance on <02-Jul-2016>");
		displayedData = Float.valueOf(one.overGapVol().getText()).floatValue();
		refData = ExcelWSheetoneraad.getRow(39).getCell(4).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed over-reported volume of Usage Assurance on <02-Jul-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed over-reported Volume of Usage Assurance on <02-Jul> is successful");
	    } else {
	    	log.info("Displayed over-reported volume of Usage Assurance on <02-Jul-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed over-reported volume of Usage Assurance on <02-Jul-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed over-reported Volume of Usage Assurance on <02-Jul> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed over-reported Volume of Usage Assurance on <02-Jul> is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Dload" }, priority = 17, enabled=true)
	public void t018RevLkgDload() throws Exception {
		log.info("Test Case - t018: Starting to test Revenue Leakage in RA Analyst Dashboard by downloading the Excel report and comparing it withe the reference report");
		test.log(LogStatus.INFO, "Test Case - t018: Starting to test Revenue Leakage in RA Analyst Dashboard by downloading the Excel report and comparing it withe the reference report");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.revLkgMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.revLkgMaxButn().click();
		log.info("Clicked on Revenue Leakage maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Revenue Leakage in RA Analyst Dashboard");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Revenue Leakage in RA Analyst Dashboard");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of of Revenue Leakage in RA Analyst Dashboard matching with the expected result: - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Revenue Leakage in RA Analyst Dashboard is successful");
		} else {
			log.debug("Is time window of Revenue Leakage in RA Analyst Dashboard matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Revenue Leakage in RA Analyst Dashboard matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Revenue Leakage in RA Analyst Dashboard is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Revenue Leakage in RA Analyst Dashboard is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"revLeakage.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(3000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(3000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"revLeakage.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"revLeakage.xlsx", downloaddir+"revLeakage.xlsx", 2, 0, 10, 10);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file : - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file: - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file: - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed\r\n", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 18, enabled=true)
	public void t019RevLkgChart() throws Exception {
		log.info("Test Case - t019: Starting to test displayed Revenue Leakage in RA Analyst Dashboard on whole duration");
		test.log(LogStatus.INFO, "Test Case - t019: Starting to test displayed Revenue Leakage in RA Analyst Dashboard on whole duration");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.revLkgMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.revLkgMaxButn().click();
		log.info("Clicked on Revenue Leakage maximize button");
		Thread.sleep(5000);
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test displayed total amount of Revenue Leakage on whole duration");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed total amount of Revenue Leakage on whole duration");
		displayedString = one.totLeakage().getText();
		refString = ExcelWSheetoneraad.getRow(34).getCell(7).getStringCellValue();
		istrue1 = ccc.equalityOfTwoStrings(refString,displayedString);
		if (istrue1) {
			log.info("Displayed total amount of Revenue Leakage on whole duration matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed Total amount of Revenue Leakage on whole duration is successful");
	    } else {
	    	log.info("Displayed total amount of Revenue Leakage on whole duration matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed total amount of Revenue Leakage on whole duration matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed Total amount of Revenue Leakage on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed Total amount of Revenue Leakage on whole duration is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-2: Starting to test displayed Postpaid amount of Revenue Leakage on whole duration");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed Postpaid amount of Revenue Leakage on whole duration");
		displayedString = one.postpaidLeakage().getText();
		refString = ExcelWSheetoneraad.getRow(34).getCell(8).getStringCellValue();
		istrue2 = ccc.equalityOfTwoStrings(displayedString,refString);
		if (istrue2) {
			log.info("Displayed Postpaid amount of Revenue Leakage on whole data matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed Postpaid amount of Revenue Leakage on whole duration is successful");
	    } else {
	    	log.info("Displayed Postpaid amount of Revenue Leakage on whole duration matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed Postpaid amount of Revenue Leakage on whole duration matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed Postpaid amount of Revenue Leakage on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed Postpaid amount of Revenue Leakage on whole duration is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-3: Starting to test displayed Prepaid amount of Revenue Leakage on whole duration");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed Prepaid amount of Revenue Leakage on whole duration");
		displayedString = one.prepaidLeakage().getText();
		refString = ExcelWSheetoneraad.getRow(34).getCell(9).getStringCellValue();
		istrue3 = ccc.equalityOfTwoStrings(displayedString,refString);
		if (istrue3) {
			log.info("Displayed displayed Prepaid amount of Revenue Leakage on whole duration matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed Prepaid amount of Revenue Leakage on whole duration is successful");
	    } else {
	    	log.info("Displayed displayed Prepaid amount of Revenue Leakage on whole duration matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed displayed Prepaid amount of Revenue Leakage on whole duration matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed Prepaid amount of Revenue Leakage on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed Prepaid amount of Revenue Leakage on whole duration is Failed\r\n", imagepath);
	    }
		istrue = istrue1 && istrue2 && istrue3;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 19, enabled=true)
	public void t020RevLkgChart28() throws Exception {
		log.info("Test Case - t020: Starting to test displayed Revenue Leakage in RA Analyst Dashboard on <28-Jun-2016>");
		test.log(LogStatus.INFO, "Test Case - t020: Starting to test displayed Revenue Leakage in RA Analyst Dashboard on <28-Jun-2016>");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.revLkgMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.revLkgMaxButn().click();
		log.info("Clicked on Revenue Leakage maximize button");
		Thread.sleep(5000);
		ccc.setDateTimeBar28();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test displayed total amount of Revenue Leakage on <28-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed total amount of Revenue Leakage on <28-Jun-2016>");
		displayedString = one.totLeakage().getText();
		refString = ExcelWSheetoneraad.getRow(35).getCell(7).getStringCellValue();
		istrue1 = ccc.equalityOfTwoStrings(refString,displayedString);
		if (istrue1) {
			log.info("Displayed total amount of Revenue Leakage on <28-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed Total amount of Revenue Leakage on <28-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed total amount of Revenue Leakage on <28-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed total amount of Revenue Leakage on <28-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed total amount of Revenue Leakage on <28-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed total amount of Revenue Leakage on <28-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-2: Starting to test displayed Prepaid amount of Revenue Leakage on <28-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed Prepaid amount of Revenue Leakage on <28-Jun-2016>");
		displayedString = one.prepaidLeakage().getText();
		refString = ExcelWSheetoneraad.getRow(35).getCell(9).getStringCellValue();
		istrue2 = ccc.equalityOfTwoStrings(displayedString,refString);
		if (istrue2) {
			log.info("Displayed Prepaid amount of Revenue Leakage on <28-Jun-2016> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed Prepaid amount of Revenue Leakage on <28-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed Prepaid amount of Revenue Leakage on <28-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed Prepaid amount of Revenue Leakage on <28-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed Prepaid amount of Revenue Leakage on <28-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed Prepaid amount of Revenue Leakage on <28-Jun-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 20, enabled=true)
	public void t021RevLkgChart29() throws Exception {
		log.info("Test Case - t021: Starting to test displayed Revenue Leakage in RA Analyst Dashboard on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test Case - t021: Starting to test displayed Revenue Leakage in RA Analyst Dashboard on <29-Jun-2016>");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.revLkgMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.revLkgMaxButn().click();
		log.info("Clicked on Revenue Leakage maximize button");
		Thread.sleep(5000);
		ccc.setDateTimeBar29();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test displayed total amount of Revenue Leakage on <28-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed total amount of Revenue Leakage on <28-Jun-2016>");
		displayedString = one.totLeakage().getText();
		refString = ExcelWSheetoneraad.getRow(36).getCell(7).getStringCellValue();
		istrue1 = ccc.equalityOfTwoStrings(refString,displayedString);
		if (istrue1) {
			log.info("Displayed total amount of Revenue Leakage on <29-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed total amount of Revenue Leakage on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed total amount of Revenue Leakage on <29-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed total amount of Revenue Leakage on <29-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed total amount of Revenue Leakage on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed total amount of Revenue Leakage on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-2: Starting to test displayed Postpaid amount of Revenue Leakage on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed Postpaid amount of Revenue Leakage on <29-Jun-2016>");
		displayedString = one.postpaidLeakage().getText();
		refString = ExcelWSheetoneraad.getRow(36).getCell(8).getStringCellValue();
		istrue2 = ccc.equalityOfTwoStrings(displayedString,refString);
		if (istrue2) {
			log.info("Displayed Postpaid amount of Revenue Leakage on <29-Jun-2016> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed Postpaid amount of Revenue Leakage on <29-Jun> is successful");
	    } else {
	    	log.info("Displayed Postpaid amount of Revenue Leakage on <29-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed Postpaid amount of Revenue Leakage on <29-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed Postpaid amount of Revenue Leakage on <29-Jun> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed Postpaid amount of Revenue Leakage on <29-Jun> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-3: Starting to test displayed Prepaid amount of Revenue Leakage on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed Prepaid amount of Revenue Leakage on <29-Jun-2016>");
		displayedString = one.prepaidLeakage().getText();
		log.debug("displayedString: " + displayedString);
		refString = ExcelWSheetoneraad.getRow(36).getCell(9).getStringCellValue();
		log.debug("refString: " + refString);
		istrue3 = ccc.equalityOfTwoStrings(displayedString,refString);
		if (istrue3) {
			log.info("Displayed Prepaid amount of Revenue Leakage on <29-Jun-2016> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed Prepaid amount of Revenue Leakage on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed Prepaid amount of Revenue Leakage on <29-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed Prepaid amount of Revenue Leakage on <29-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed Prepaid amount of Revenue Leakage on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed Prepaid amount of Revenue Leakage on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1 && istrue2 && istrue3;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 21, enabled=true)
	public void t022RevLkgChart30() throws Exception {
		log.info("Test Case - t022: Starting to test displayed Revenue Leakage in RA Analyst Dashboard on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test Case - t022: Starting to test displayed Revenue Leakage in RA Analyst Dashboard on <30-Jun-2016>");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.revLkgMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.revLkgMaxButn().click();
		log.info("Clicked on Revenue Leakage maximize button");
		Thread.sleep(5000);
		ccc.setDateTimeBar30();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test displayed Total amount of Revenue Leakage on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed Total amount of Revenue Leakage on <30-Jun-2016>");
		displayedString = one.totLeakage().getText();
		refString = ExcelWSheetoneraad.getRow(37).getCell(7).getStringCellValue();
		istrue1 = ccc.equalityOfTwoStrings(refString,displayedString);
		if (istrue1) {
			log.info("Displayed Total amount of Revenue Leakage on <30-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed Total amount of Revenue Leakage on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed Total amount of Revenue Leakage on <30-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed Total amount of Revenue Leakage on <30-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed Total amount of Revenue Leakage on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed Total amount of Revenue Leakage on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-2: Starting to test displayed Postpaid amount of Revenue Leakage on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed Postpaid amount of Revenue Leakage on <30-Jun-2016>");
		displayedString = one.postpaidLeakage().getText();
		refString = ExcelWSheetoneraad.getRow(37).getCell(8).getStringCellValue();
		istrue2 = ccc.equalityOfTwoStrings(displayedString,refString);
		if (istrue2) {
			log.info("Displayed Postpaid amount of Revenue Leakage on <30-Jun-2016> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed Postpaid amount of Revenue Leakage on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed Postpaid amount of Revenue Leakage on <30-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed Postpaid amount of Revenue Leakage on <30-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed Postpaid amount of Revenue Leakage on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed Postpaid amount of Revenue Leakage on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-3: Starting to test displayed Prepaid amount of Revenue Leakage on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed Prepaid amount of Revenue Leakage on <30-Jun-2016>");
		displayedString = one.prepaidLeakage().getText();
		log.debug("displayedString: " + displayedString);
		refString = ExcelWSheetoneraad.getRow(37).getCell(9).getStringCellValue();
		log.debug("refString: " + refString);
		istrue3 = ccc.equalityOfTwoStrings(displayedString,refString);
		if (istrue3) {
			log.info("Displayed Prepaid amount of Revenue Leakage on <30-Jun-2016> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed Prepaid amount of Revenue Leakage on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed Prepaid amount of Revenue Leakage on <30-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed Prepaid amount of Revenue Leakage on <30-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed Prepaid amount of Revenue Leakage on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed Prepaid amount of Revenue Leakage on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1 && istrue2 && istrue3;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 22, enabled=true)
	public void t023RevLkgChart01() throws Exception {
		log.info("Test Case - t023: Starting to test displayed Revenue Leakage in RA Analyst Dashboard on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test Case - t023: Starting to test displayed Revenue Leakage in RA Analyst Dashboard on <01-Jul-2016>");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.revLkgMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.revLkgMaxButn().click();
		log.info("Clicked on Revenue Leakage maximize button");
		Thread.sleep(5000);
		ccc.setDateTimeBar01();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test displayed Total amount of Revenue Leakage on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed Total amount of Revenue Leakage on <01-Jul-2016>");
		displayedString = one.totLeakage().getText();
		refString = ExcelWSheetoneraad.getRow(38).getCell(7).getStringCellValue();
		istrue1 = ccc.equalityOfTwoStrings(refString,displayedString);
		if (istrue1) {
			log.info("Displayed Total amount of Revenue Leakage on <01-Jul-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed Total amount of Revenue Leakage on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed Total amount of Revenue Leakage on <01-Jul-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed Total amount of Revenue Leakage on <01-Jul-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed Total amount of Revenue Leakage on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed Total amount of Revenue Leakage on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-2: Starting to test displayed Postpaid amount of Revenue Leakage on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed Postpaid amount of Revenue Leakage on <01-Jul-2016>");
		displayedString = one.postpaidLeakage().getText();
		refString = ExcelWSheetoneraad.getRow(38).getCell(8).getStringCellValue();
		istrue2 = ccc.equalityOfTwoStrings(displayedString,refString);
		if (istrue2) {
			log.info("Displayed Postpaid amount of Revenue Leakage on <01-Jul-2016> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed Postpaid amount of Revenue Leakage on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed Postpaid amount of Revenue Leakage on <01-Jul-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed Postpaid amount of Revenue Leakage on <01-Jul-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed Postpaid amount of Revenue Leakage on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed Postpaid amount of Revenue Leakage on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-3: Starting to test displayed Prepaid amount of Revenue Leakage on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed Prepaid amount of Revenue Leakage on <01-Jul-2016>");
		displayedString = one.prepaidLeakage().getText();
		log.debug("displayedString: " + displayedString);
		refString = ExcelWSheetoneraad.getRow(38).getCell(9).getStringCellValue();
		log.debug("refString: " + refString);
		istrue3 = ccc.equalityOfTwoStrings(displayedString,refString);
		if (istrue3) {
			log.info("Displayed Prepaid amount of Revenue Leakage on <01-Jul-2016> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed Prepaid amount of Revenue Leakage on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed Prepaid amount of Revenue Leakage on <01-Jul-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed Prepaid amount of Revenue Leakage on <01-Jul-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed Prepaid amount of Revenue Leakage on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed Prepaid amount of Revenue Leakage on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1 && istrue2 && istrue3;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Chart" }, priority = 23, enabled=false)
	public void t024RevLkgChart02() throws Exception {
		log.info("Test Case - t024: Starting to test displayed Revenue Leakage in RA Analyst Dashboard on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test Case - t024: Starting to test displayed Revenue Leakage in RA Analyst Dashboard on <02-Jul-2016>");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.revLkgMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.revLkgMaxButn().click();
		log.info("Clicked on Revenue Leakage maximize button");
		Thread.sleep(5000);
		ccc.setDateTimeBar02();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test displayed Total amount of Revenue Leakage on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed Total amount of Revenue Leakage on <02-Jul-2016>");
		displayedString = one.totLeakage().getText();
		refString = ExcelWSheetoneraad.getRow(39).getCell(7).getStringCellValue();
		istrue1 = ccc.equalityOfTwoStrings(refString,displayedString);
		if (istrue1) {
			log.info("Displayed Total amount of Revenue Leakage on <02-Jul-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed Total amount of Revenue Leakage on <02-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed Total amount of Revenue Leakage on <02-Jul-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed Total amount of Revenue Leakage on <02-Jul-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed Total amount of Revenue Leakage on <02-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed Total amount of Revenue Leakage on <02-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-2: Starting to test displayed Postpaid amount of Revenue Leakage on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed Postpaid amount of Revenue Leakage on <02-Jul-2016>");
		displayedString = one.postpaidLeakage().getText();
		refString = ExcelWSheetoneraad.getRow(39).getCell(8).getStringCellValue();
		istrue2 = ccc.equalityOfTwoStrings(displayedString,refString);
		if (istrue2) {
			log.info("Displayed Postpaid amount of Revenue Leakage on <02-Jul-2016> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed Postpaid amount of Revenue Leakage on <02-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed Postpaid amount of Revenue Leakage on <02-Jul-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed Postpaid amount of Revenue Leakage on <02-Jul-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed Postpaid amount of Revenue Leakage on <02-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed Postpaid amount of Revenue Leakage on <02-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-3: Starting to test displayed Prepaid amount of Revenue Leakage on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed Prepaid amount of Revenue Leakage on <02-Jul-2016>");
		displayedString = one.prepaidLeakage().getText();
		log.debug("displayedString: " + displayedString);
		refString = ExcelWSheetoneraad.getRow(39).getCell(9).getStringCellValue();
		log.debug("refString: " + refString);
		istrue3 = ccc.equalityOfTwoStrings(displayedString,refString);
		if (istrue3) {
			log.info("Displayed Prepaid amount of Revenue Leakage on <02-Jul-2016> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed Prepaid amount of Revenue Leakage on <02-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed Prepaid amount of Revenue Leakage on <02-Jul-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed Prepaid amount of Revenue Leakage on <02-Jul-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed Prepaid amount of Revenue Leakage on <02-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed Prepaid amount of Revenue Leakage on <02-Jul-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2&&istrue3;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Dload" }, priority = 24, enabled=true)
	public void t025DepVsChgDload() throws Exception {
		log.info("Test Case - t025: Starting to test DEP Vs CHG gap volume trend in RA Analyst Dashboard by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t025: Starting to test DEP Vs CHG gap volume trend in RA Analyst Dashboard by downloading the Excel report and comparing it with the reference report");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.depVsChgMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.depVsChgMaxButn().click();
		log.info("Clicked on DEP vs CHG maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of  DEP Vs CHG gap volume trend");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of  DEP Vs CHG gap volume trend");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result: - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of  DEP Vs CHG gap volume trend is successful");
		} else {
			log.debug("Is time window matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of  DEP Vs CHG gap volume trend is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of  DEP Vs CHG gap volume trend is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"depVsChgTrend.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(3000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(3000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"depVsChgTrend.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"depVsChgTrend.xlsx", downloaddir+"depVsChgTrend.xlsx", 4, 0, 11, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file : - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file: - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file: - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed\r\n", imagepath);
		}
		istrue = istrue1&&istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Dload" }, priority = 25, enabled=true)
	public void t026DepVsNwDload() throws Exception {
		log.info("Test Case - t026: Starting to test DEP Vs NW gap volume trend in RA Analyst Dashboard by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t026: Starting to test DEP Vs NW gap volume trend in RA Analyst Dashboard by downloading the Excel report and comparing it with the reference report");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.depVsNwMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.depVsNwMaxButn().click();
		log.info("Clicked on DEP vs NW maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of  DEP Vs NW gap volume trend");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of  DEP Vs NW gap volume trend");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result: - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of  DEP Vs NW gap volume trend is successful");
		} else {
			log.debug("Is time window matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window matching with the expected result: - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of  DEP Vs NW gap volume trend is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of  DEP Vs NW gap volume trend is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"depVsNwTrend.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(3000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(3000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"depVsNwTrend.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"depVsNwTrend.xlsx", downloaddir+"depVsNwTrend.xlsx", 4, 0, 11, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file : - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file: - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file: - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed\r\n", imagepath);
		}
		istrue = istrue1&&istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Dload" }, priority = 26, enabled=true)
	public void t027NwVsChgDload() throws Exception {
		log.info("Test Case - t027: Starting to test NW Vs CHG gap volume trend in RA Analyst Dashboard by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t027: Starting to test NW Vs CHG gap volume trend in RA Analyst Dashboard by downloading the Excel report and comparing it with the reference report");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.nwVsChgMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		one.nwVsChgMaxButn().click();
		log.info("Clicked on NW Vs CHG maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of  NW Vs CHG gap volume trend");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of  NW Vs CHG gap volume trend");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result: - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of  NW Vs CHG gap volume trend is successful");
		} else {
			log.debug("Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.FAIL, "Test step-1: Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of  NW Vs CHG gap volume trend is Failed");
	    	test.log(LogStatus.WARNING, "Test step-1: Testing of the time window of  NW Vs CHG gap volume trend is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"nwVsChgTrend.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(3000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(3000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"nwVsChgTrend.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"nwVsChgTrend.xlsx", downloaddir+"nwVsChgTrend.xlsx", 4, 0, 11, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file ? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed\r\n", imagepath);
		}
		istrue = istrue1&&istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnalystDashboard", "Dload" }, priority = 27, enabled=true)
	public void t028OverReportDload() throws Exception {
		log.info("Test Case - t028: Starting to test Over-reported volume trend in RA Analyst Dashboard by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t028: Starting to test Over-reported volume trend in RA Analyst Dashboard by downloading the Excel report and comparing it with the reference report");
		ccc.OneRaAD().click();
		log.info("Clicked on <RA Analyst Dashboard>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.ovrRprtMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		screen.click(one.scrollDownPattern());
		Thread.sleep(3000);
		one.ovrRprtMaxButn().click();
		log.info("Clicked on Over-reported maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of  Over-reported volume trend");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of  Over-reported volume trend");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of  Over-reported volume trend is successful");
		} else {
			log.debug("Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of  Over-reported volume trend is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of  Over-reported volume trend is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"overReportedTrend.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(3000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(3000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"overReportedTrend.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"overReportedTrend.xlsx", downloaddir+"overReportedTrend.xlsx", 4, 0, 6, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file ? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed\r\n", imagepath);
		}
		istrue = istrue1&&istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "PreAnalysisSummary", "Dload" }, priority = 28, enabled=true)
	public void t029PreAnaSumm1Dload() throws Exception {
		log.info("Test Case - t029: Starting to test data loading status in Pre-Analysis Summary (1 of 3) by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t029: Starting to test data loading status in Pre-Analysis Summary (1 of 3) by downloading the Excel report and comparing it with the reference report");
		ccc.Two1PaS().click();
		log.info("Clicked on <2a. Pre-analysis Summary (1 of 3)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(two.configureButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		two.configureButn().click();
		log.debug("Clicked on configure button");
		Thread.sleep(2000);
		two.removeHourButn().click();
		log.debug("Clicked on <Remove Hour> button");
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test the time window of  data loading status");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of  data loading status");
		reftimewindow1 = new ArrayList<String>(Arrays.asList("JUN 25 2016 12 AM", "JUL 2 2016 12 AM"));
		istrue1 = ccc.verifyTimeWindow(reftimewindow1);
		if (istrue1) {
			log.debug("Is time window matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of  data loading status is successful");
		} else {
			log.debug("Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of  data loading status is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of  data loading status is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"dataLoadingStatus.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(3000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(3000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"dataLoadingStatus.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"dataLoadingStatus.xlsx", downloaddir+"dataLoadingStatus.xlsx", 2, 0, 14, 9);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file ? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed\r\n", imagepath);
		}
		istrue = istrue1&&istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "PreAnalysisSummary", "Dload" }, priority = 29, enabled=true)
	public void t030PreAnaSumm2totDload() throws Exception {
		log.info("Test Case - t030: Starting to test Pre-Analysis Summary (2 of 3) by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t030: Starting to test Pre-Analysis Summary (2 of 3) by downloading the Excel report and comparing it with the reference report");
		ccc.Two2PaS().click();
		log.info("Clicked on <Pre-Analysis Summary (2 of 3)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(two.totVolMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		two.totVolMaxButn().click();
		log.info("Clicked on total vol maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Total Volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Total Volume");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Total Volume is successful");
		} else {
			log.debug("Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Total Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Total Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		if (two.totVolZoominButton().getText()!="DAY") {
			log.error("Zoomin button is not set to DAY, setting it to DAY");
			two.totVolZoominButton().click();
			Thread.sleep(1000);
			ccc.DayButton().click();
			Thread.sleep(3000);
			log.debug("Zoomin button set to DAY");
		}
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"PreTotVol.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(3000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(3000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"PreTotVol.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"PreTotVol.xlsx", downloaddir+"PreTotVol.xlsx", 2, 0, 14, 4);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference filetime window matching with the expected result? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference filetime window matching with the expected result? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference filetime window matching with the expected result? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1&&istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}

	@Test(groups = { "all_tests", "PreAnalysisSummary", "Chart" }, priority = 30, enabled=true)
	public void t031PreAnaSumm2totChart() throws Exception {
		log.info("Test Case - t031: Starting to test displayed total volumes in Pre-Analysis Summary (2 of 3)");
		test.log(LogStatus.INFO, "Test Case - t031: Starting to test displayed total volumes in Pre-Analysis Summary (2 of 3)");
		ccc.Two2PaS().click();
		log.info("Clicked on <Pre-Analysis Summary (2 of 3)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(two.totVolMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		two.totVolMaxButn().click();
		log.info("Clicked on total vol maximize button");
		Thread.sleep(5000);
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		log.debug("Time-window is set to correct dates now");
		Thread.sleep(3000);
		if (two.totVolZoominButton().getText().equals("DAY")) {
			log.debug("Zoomin button is set to DAY, no change in Zoom in");
		} else {
			log.error("Zoomin button is not set to DAY, setting it to DAY");
			two.totVolZoominButton().click();
			Thread.sleep(1000);
			ccc.DayButton().click();
			log.debug("Zoomin button set to DAY");
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		log.debug("Test step-1: Starting to test displayed DEP volume on <28-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed DEP volume on <28-Jun-2016>");
		try {
			screen.click(two.depTotVolpatternOn628());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(46).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed DEP volume on <28-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed DEP volume on <28-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed DEP volume on <28-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed DEP volume on <28-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed DEP volume on <28-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed DEP volume on <28-Jun-2016> is Failed\r\n", imagepath);
	    }

		Thread.sleep(3000);

		log.debug("Test step-2: Starting to test displayed NW volume on <28-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed NW volume on <28-Jun-2016>");
		try {
			screen.click(two.nwTotVolpatternOn628());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(47).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed NW volume on <28-Jun-2016> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed NW volume on <28-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed NW volume on <28-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed NW volume on <28-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed NW volume on <28-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed NW volume on <28-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-3: Starting to test displayed NW volume on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed NW volume on <29-Jun-2016>");
		try {
			screen.click(two.nwTotVolpatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(48).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue3 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue3) {
			log.info("Displayed NW volume on <29-Jun-2016> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed NW volume on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed NW volume on <29-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed NW volume on <29-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed NW volume on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed NW volume on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-4: Starting to test displayed CHG volume on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-4: Starting to test displayed CHG volume on <29-Jun-2016>");
		try {
			screen.click(two.chgTotVolpatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(49).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue4 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue4) {
			log.info("Displayed CHG volume on <29-Jun-2016> matched with the expected results? - " + istrue4);
			test.log(LogStatus.PASS, "Test step-4: Testing of displayed CHG volume on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed CHG volume on <29-Jun-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-4: Displayed CHG volume on <29-Jun-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-4: Testing of displayed CHG volume on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-4: Testing of displayed CHG volume on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-5: Starting to test displayed DEP volume on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-5: Starting to test displayed DEP volume on <29-Jun-2016>");
		try {
			screen.click(two.depTotVolpatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(50).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue5 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue5) {
			log.info("Displayed DEP volume on <29-Jun-2016> matched with the expected results? - " + istrue5);
			test.log(LogStatus.PASS, "Test step-5: Testing of displayed DEP volume on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed DEP volume on <29-Jun-2016> matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-5: Displayed DEP volume on <29-Jun-2016> matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-5: Testing of displayed DEP volume on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-5: Testing of displayed DEP volume on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-6: Starting to test displayed NW volume on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-6: Starting to test displayed NW volume on <30-Jun-2016>");
		try {
			screen.click(two.nwTotVolpatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(51).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue6 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue6) {
			log.info("Displayed NW volume on <30-Jun-2016> matched with the expected results? - " + istrue6);
			test.log(LogStatus.PASS, "Test step-6: Testing of displayed NW volume on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed NW volume on <30-Jun-2016> matched with the expected results? - " + istrue6 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-6: Displayed NW volume on <30-Jun-2016> matched with the expected results? - " + istrue6 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-6: Testing of displayed NW volume on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-6: Testing of displayed NW volume on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-7: Starting to test displayed CHG volume on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-7: Starting to test displayed CHG volume on <30-Jun-2016>");
		try {
			screen.click(two.chgTotVolpatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(52).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue7 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue7) {
			log.info("Displayed CHG volume on <30-Jun-2016> matched with the expected results? - " + istrue7);
			test.log(LogStatus.PASS, "Test step-7: Testing of displayed CHG volume on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed CHG volume on <30-Jun-2016> matched with the expected results? - " + istrue7 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-7: Displayed CHG volume on <30-Jun-2016> matched with the expected results? - " + istrue7 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-7: Testing of displayed CHG volume on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-7: Testing of displayed CHG volume on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-8: Starting to test displayed DEP volume on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-8: Starting to test displayed DEP volume on <30-Jun-2016>");
		try {
			screen.click(two.depTotVolpatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(53).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue8 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue8) {
			log.info("Displayed DEP volume on <30-Jun-2016> matched with the expected results? - " + istrue8);
			test.log(LogStatus.PASS, "Test step-8: Testing of displayed DEP volume on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed DEP volume on <30-Jun-2016> matched with the expected results? - " + istrue8 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-8: Displayed DEP volume on <30-Jun-2016> matched with the expected results? - " + istrue8 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-8: Testing of displayed DEP volume on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-8: Testing of displayed DEP volume on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-9: Starting to test displayed NW volume on <01-Ju1-2016>");
		test.log(LogStatus.INFO, "Test step-9: Starting to test displayed NW volume on <01-Ju1-2016>");
		try {
			screen.click(two.nwTotVolpatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(54).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue9 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue9) {
			log.info("Displayed NW volume on <01-Ju1-2016> matched with the expected results? - " + istrue9);
			test.log(LogStatus.PASS, "Test step-9: Testing of displayed NW volume on <01-Ju1-2016> is successful");
	    } else {
	    	log.info("Displayed NW volume on <01-Ju1-2016> matched with the expected results? - " + istrue9 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-9: Displayed NW volume on <01-Ju1-2016> matched with the expected results? - " + istrue9 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-9: Testing of displayed NW volume on <01-Ju1-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-9: Testing of displayed NW volume on <01-Ju1-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-10: Starting to test displayed CHG volume on <01-Ju1-2016>");
		test.log(LogStatus.INFO, "Test step-10: Starting to test displayed CHG volume on <01-Ju1-2016>");
		try {
			screen.click(two.chgTotVolpatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(55).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue10 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue10) {
			log.info("Displayed CHG volume on <01-Ju1-2016> matched with the expected results? - " + istrue10);
			test.log(LogStatus.PASS, "Test step-10: Testing of displayed CHG volume on <01-Ju1-2016> is successful");
	    } else {
	    	log.info("Displayed CHG volume on <01-Ju1-2016> matched with the expected results? - " + istrue10 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-10: Displayed CHG volume on <01-Ju1-2016> matched with the expected results? - " + istrue10 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-10: Testing of displayed CHG volume on <01-Ju1-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-10: Testing of displayed CHG volume on <01-Ju1-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-11: Starting to test displayed DEP volume on <01-Ju1-2016>");
		test.log(LogStatus.INFO, "Test step-11: Starting to test displayed DEP volume on <01-Ju1-2016>");
		try {
			screen.click(two.depTotVolpatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(56).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue11 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue11) {
			log.info("Displayed DEP volume on <01-Ju1-2016> matched with the expected results? - " + istrue11);
			test.log(LogStatus.PASS, "Test step-11: Testing of displayed DEP volume on <01-Ju1-2016> is successful");
	    } else {
	    	log.info("Displayed DEP volume on <01-Ju1-2016> matched with the expected results? - " + istrue11 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-11: Displayed DEP volume on <01-Ju1-2016> matched with the expected results? - " + istrue11 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-11: Testing of displayed DEP volume on <01-Ju1-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-11: Testing of displayed DEP volume on <01-Ju1-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-12: Starting to test displayed CHG volume on <02-Ju1-2016>");
		test.log(LogStatus.INFO, "Test step-12: Starting to test displayed CHG volume on <02-Ju1-2016>");
		try {
			screen.click(two.chgTotVolpatternOn702());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(57).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue12 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue12) {
			log.info("Displayed CHG volume on <02-Ju1-2016> matched with the expected results? - " + istrue12);
			test.log(LogStatus.PASS, "Test step-12: Testing of displayed CHG volume on <02-Ju1-2016> is successful");
	    } else {
	    	log.info("Displayed CHG volume on <02-Ju1-2016> matched with the expected results? - " + istrue12 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-12: Displayed CHG volume on <02-Ju1-2016> matched with the expected results? - " + istrue12 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-12: Testing of displayed CHG volume on <02-Ju1-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-12: Testing of displayed CHG volume on <02-Ju1-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-13: Starting to test displayed NW volume on whole duration");
		test.log(LogStatus.INFO, "Test step-13: Starting to test displayed NW volume on whole duration");
		two.totVolZoominButton().click();
		Thread.sleep(1000);
		ccc.YearButton().click();
		log.debug("Zoomin button set to YEAR");
		Thread.sleep(500);
		try {
			screen.click(two.nwTotVolpatternOnwhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(58).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue13 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue13) {
			log.info("Displayed NW volume on whole duration matched with the expected results? - " + istrue13);
			test.log(LogStatus.PASS, "Test step-13: Testing of displayed NW volume on duration data is successful");
	    } else {
	    	log.info("Displayed NW volume on whole duration matched with the expected results? - " + istrue13 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-13: Displayed NW volume on whole duration matched with the expected results? - " + istrue13 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-13: Testing of displayed NW volume on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-13: Testing of displayed NW volume on whole duration is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-14: Starting to test displayed CHG volume on whole duration");
		test.log(LogStatus.INFO, "Test step-14: Starting to test displayed CHG volume on whole duration");
		try {
			screen.click(two.chgTotVolpatternOnwhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(59).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue14 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue14) {
			log.info("Displayed CHG volume on whole duration matched with the expected results? - " + istrue14);
			test.log(LogStatus.PASS, "Test step-14: Testing of displayed CHG volume on whole duration is successful");
	    } else {
	    	log.info("Displayed CHG volume on whole duration matched with the expected results? - " + istrue14 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-14: Displayed CHG volume on whole duration matched with the expected results? - " + istrue14 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-14: Testing of displayed CHG volume on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-14: Testing of displayed CHG volume on whole duration is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-15: Starting to test displayed DEP volume on whole duration");
		test.log(LogStatus.INFO, "Test step-15: Starting to test displayed DEP volume on whole duration");
		try {
			screen.click(two.depTotVolpatternOnwhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.volContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(60).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue15 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue15) {
			log.info("Displayed DEP volume on whole duration matched with the expected results? - " + istrue15);
			test.log(LogStatus.PASS, "Test step-15: Testing of displayed DEP volume on whole duration is successful");
	    } else {
	    	log.info("Displayed DEP volume on whole duration matched with the expected results? - " + istrue15 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-15: Displayed DEP volume on whole duration matched with the expected results? - " + istrue15 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-15: Testing of displayed DEP volume on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-15: Testing of displayed DEP volume on whole duration is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2&&istrue3&&istrue4&&istrue5&&istrue6&&istrue7&&istrue8&&istrue9&&istrue10&&istrue11&&istrue12&&istrue13&&istrue14&&istrue15;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "PreAnalysisSummary", "Dload" }, priority = 31, enabled=true)
	public void t032PreAnaSumm2freeDload() throws Exception {
		log.info("Test Case - t032: Starting to test Free volume of Pre-Analysis Summary (2 of 3) by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t032: Starting to test Free volume of Pre-Analysis Summary (2 of 3) by downloading the Excel report and comparing it with the reference report");
		ccc.Two2PaS().click();
		log.info("Clicked on <Pre-Analysis Summary (2 of 3)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(two.freeVolMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		two.freeVolMaxButn().click();
		log.info("Clicked on free vol maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of free Volume in the Pre-Analysis Summary");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of free Volume in the Pre-Analysis Summary");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of free Volume is successful");
		} else {
			log.debug("Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of free Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of free Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		if (two.freeVolZoominButton().getText()!="DAY") {
			log.error("Zoomin button is not set to DAY, setting it to DAY");
			two.freeVolZoominButton().click();
			Thread.sleep(1000);
			ccc.DayButton().click();
			Thread.sleep(3000);
			log.debug("Zoomin button set to DAY");
		}
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"PreFreeVol.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(3000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(3000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"PreFreeVol.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"PreFreeVol.xlsx", downloaddir+"PreFreeVol.xlsx", 2, 0, 14, 3);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference filetime window matching with the expected result? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference filetime window matching with the expected result? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference filetime window matching with the expected result? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "PreAnalysisSummary", "Dload" }, priority = 32, enabled=true)
	public void t033PreAnaSumm3SubsDload() throws Exception {
		log.info("Test Case - t033: Starting to test subscriber count in Pre-Analysis Summary (3 of 3) by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t033: Starting to test subscriber count in Pre-Analysis Summary (3 of 3) by downloading the Excel report and comparing it with the reference report");
		ccc.Two3PaS().click();
		log.info("Clicked on <Pre-Analysis Summary (3 of 3)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(two.subsCountMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		two.subsCountMaxButn().click();
		log.info("Clicked on subscriber count maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of subscriber count");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of subscriber count");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of subscriber count is successful");
		} else {
			log.debug("Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of subscriber count is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of subscriber count is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		if (two.subsCountZoominButton().getText().equals("DAY")) {
			log.debug("Zoomin button is set to DAY, no change in Zoom in");
		} else {
			log.error("Zoomin button is not set to DAY, setting it to DAY");
			two.subsCountZoominButton().click();
			Thread.sleep(1000);
			ccc.DayButton().click();
			log.debug("Zoomin button set to DAY");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
		}
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"PreSubsCount.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"PreSubsCount.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"PreSubsCount.xlsx", downloaddir+"PreSubsCount.xlsx", 2, 0, 14, 3);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "PreAnalysisSummary", "Chart" }, priority = 33, enabled=true)
	public void t034PreAnaSumm3SubsChart() throws Exception {
		log.info("Test Case - t034: Starting to test displayed subscriber count in Pre-Analysis Summary (3 of 3)");
		test.log(LogStatus.INFO, "Test Case - t034: Starting to test displayed subscriber count in Pre-Analysis Summary (3 of 3)");
		ccc.Two3PaS().click();
		log.info("Clicked on <Pre-Analysis Summary (3 of 3)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(two.subsCountMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		two.subsCountMaxButn().click();
		log.info("Clicked on subscriber count maximize button");
		Thread.sleep(5000);
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		Thread.sleep(1000);
		if (two.subsCountZoominButton().getText().equals("DAY")) {
			log.debug("Zoomin button is set to DAY, no change in Zoom in");
		} else {
			log.error("Zoomin button is not set to DAY, setting it to DAY");
			two.subsCountZoominButton().click();
			Thread.sleep(1000);
			ccc.DayButton().click();
			log.debug("Zoomin button set to DAY");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		log.debug("Test step-1: Starting to test displayed DEP subscriber count on <28-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed DEP subscriber count on <28-Jun-2016>");
		try {
			screen.click(two.depSubsCountpatternOn628());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(46).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed DEP subscriber count on <28-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed DEP subscriber count on <28-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed DEP subscriber count on <28-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed DEP subscriber count on <28-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed DEP subscriber count on <28-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed DEP subscriber count on <28-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-2: Starting to test displayed NW subscriber count on <28-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed NW subscriber count on <28-Jun-2016>");
		try {
			screen.click(two.nwSubsCountpatternOn628());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(47).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed displayed NW subscriber count on <28-Jun-2016> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed NW subscriber count on <28-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed displayed NW subscriber count on <28-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed displayed NW subscriber count on <28-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed NW subscriber count on <28-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed NW subscriber count on <28-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-3: Starting to test displayed CHG subscriber count on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed CHG subscriber count on <29-Jun-2016>");
		try {
			screen.click(two.chgSubsCountpatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(48).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue3 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue3) {
			log.info("Displayed CHG subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed CHG subscriber count on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed CHG subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed CHG subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed CHG subscriber count on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed CHG subscriber count on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-4: Starting to test displayed DEP subscriber count on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-4: Starting to test displayed DEP subscriber count on <29-Jun-2016>");
		try {
			screen.click(two.depSubsCountpatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(49).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue4 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue4) {
			log.info("Displayed DEP subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue4);
			test.log(LogStatus.PASS, "Test step-4: Testing of displayed DEP subscriber count on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed DEP subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-4: Displayed DEP subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-4: Testing of displayed DEP subscriber count on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-4: Testing of displayed DEP subscriber count on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-5: Starting to test displayed NW subscriber count on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-5: Starting to test displayed NW subscriber count on <29-Jun-2016>");
		try {
			screen.click(two.nwSubsCountpatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(50).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue5 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue5) {
			log.info("Displayed NW subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue5);
			test.log(LogStatus.PASS, "Test step-5: Testing of displayed NW subscriber count on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed NW subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-5: Displayed NW subscriber count on <29-Jun-2016> matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-5: Testing of displayed NW subscriber count on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-5: Testing of displayed NW subscriber count on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-6: Starting to test displayed CHG subscriber count on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-6: Starting to test displayed CHG subscriber count on <30-Jun-2016>");
		try {
			screen.click(two.chgSubsCountpatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(51).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue6 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue6) {
			log.info("Displayed CHG subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue6);
			test.log(LogStatus.PASS, "Test step-6: Testing of displayed CHG subscriber count on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed CHG subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue6 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-6: Displayed CHG subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue6 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-6: Testing of displayed CHG subscriber count on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-6: Testing of displayed CHG subscriber count on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-7: Starting to test displayed DEP subscriber count on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-7: Starting to test displayed DEP subscriber count on <30-Jun-2016>");
		try {
			screen.click(two.depSubsCountpatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(52).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue7 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue7) {
			log.info("Displayed DEP subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue7);
			test.log(LogStatus.PASS, "Test step-7: Testing of displayed DEP subscriber count on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed DEP subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue7 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-7: Displayed DEP subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue7 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-7: Testing of displayed DEP subscriber count on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-7: Testing of displayed DEP subscriber count on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-8: Starting to test displayed NW subscriber count on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-8: Starting to test displayed NW subscriber count on <30-Jun-2016>");
		try {
			screen.click(two.nwSubsCountpatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(53).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue8 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue8) {
			log.info("Displayed NW subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue8);
			test.log(LogStatus.PASS, "Test step-8: Testing of displayed NW subscriber count on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed NW subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue8 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-8: Displayed NW subscriber count on <30-Jun-2016> matched with the expected results? - " + istrue8 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-8: Testing of displayed NW subscriber count on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-8: Testing of displayed NW subscriber count on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-9: Starting to test displayed CHG subscriber count on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-9: Starting to test displayed CHG subscriber count on <01-Jul-2016>");
		try {
			screen.click(two.chgSubsCountpatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(54).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue9 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue9) {
			log.info("Displayed CHG subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue9);
			test.log(LogStatus.PASS, "Test step-9: Testing of displayed CHG subscriber count on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed CHG subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue9 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-9: Displayed CHG subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue9 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-9: Testing of displayed CHG subscriber count on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-9: Testing of displayed CHG subscriber count on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-10: Starting to test displayed DEP subscriber count on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-10: Starting to test displayed DEP subscriber count on <01-Jul-2016>");
		try {
			screen.click(two.depSubsCountpatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(55).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue10 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue10) {
			log.info("Displayed DEP subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue10);
			test.log(LogStatus.PASS, "Test step-10: Testing of displayed DEP subscriber count on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed DEP subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue10 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-10: Displayed DEP subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue10 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-10: Testing of displayed DEP subscriber count on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-10: Testing of displayed DEP subscriber count on <01-Jul-2016> is Failed\r\n", imagepath);
	    }

		Thread.sleep(3000);

		log.debug("Test step-11: Starting to test displayed NW subscriber count on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-10: Starting to test displayed DEP subscriber count on <01-Jul-2016>");
		try {
			screen.click(two.nwSubsCountpatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(56).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue11 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue11) {
			log.info("Displayed NW subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue11);
			test.log(LogStatus.PASS, "Test step-11: Testing of displayed NW subscriber count on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed NW subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue11 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-11: Displayed NW subscriber count on <01-Jul-2016> matched with the expected results? - " + istrue11 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-11: Testing of displayed NW subscriber count on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-11: Testing of displayed NW subscriber count on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-12: Starting to test displayed CHG subscriber count on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-12: Starting to test displayed CHG subscriber count on <02-Jul-2016>");
		try {
			screen.click(two.chgSubsCountpatternOn702());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(57).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue12 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue12) {
			log.info("Displayed CHG subscriber count on <02-Jul-2016> matched with the expected results? - " + istrue12);
			test.log(LogStatus.PASS, "Test step-12: Testing of displayed CHG subscriber count on <02-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed CHG subscriber count on <02-Jul-2016> matched with the expected results? - " + istrue12 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-12: Displayed CHG subscriber count on <02-Jul-2016> matched with the expected results? - " + istrue12 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-12: Testing of displayed CHG subscriber count on <02-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-12: Testing of displayed CHG subscriber count on <02-Jul-2016> is Failed\r\n", imagepath);
	    }

		Thread.sleep(3000);
		

		log.debug("Test step-13: Starting to test displayed CHG subscriber count on whole duration");
		test.log(LogStatus.INFO, "Test step-13: Starting to test displayed CHG subscriber count on whole duration");
		two.subsCountZoominButton().click();
		Thread.sleep(1000);
		ccc.YearButton().click();
		log.debug("Zoomin button set to YEAR");
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(two.chgSubsCountpatternOnWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(58).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue13 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue13) {
			log.info("Displayed CHG subscriber count on whole duration matched with the expected results? - " + istrue13);
			test.log(LogStatus.PASS, "Test step-13: Testing of displayed CHG subscriber count on whole duration is successful");
	    } else {
	    	log.info("Displayed CHG subscriber count on whole duration matched with the expected results? - " + istrue13 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-13: Displayed CHG subscriber count on whole duration matched with the expected results? - " + istrue13 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-13: Testing of displayed CHG subscriber count on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-13: Testing of displayed CHG subscriber count on whole duration is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-14: Starting to test displayed DEP subscriber count on whole duration");
		test.log(LogStatus.INFO, "Test step-14: Starting to test displayed DEP subscriber count on whole duration");
		try {
			screen.click(two.depSubsCountpatternOnWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(59).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue14 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue14) {
			log.info("Displayed DEP subscriber count on whole duration matched with the expected results? - " + istrue14);
			test.log(LogStatus.PASS, "Test step-14: Testing of displayed DEP subscriber count on whole duration is successful");
	    } else {
	    	log.info("Displayed DEP subscriber count on whole duration matched with the expected results? - " + istrue14 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-14: Displayed DEP subscriber count on whole duration matched with the expected results? - " + istrue14 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-14: Testing of displayed DEP subscriber count on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-14: Testing of displayed DEP subscriber count on whole duration is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-15: Starting to test displayed NW subscriber count on whole duration");
		test.log(LogStatus.INFO, "Test step-14: Starting to test displayed DEP subscriber count on whole duration");
		try {
			screen.click(two.nwSubsCountpatternOnWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.subsContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(60).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue15 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue15) {
			log.info("Displayed NW subscriber count on whole duration matched with the expected results? - " + istrue15);
			test.log(LogStatus.PASS, "est step-15: Testing of displayed NW subscriber count on whole duration is successful");
	    } else {
	    	log.info("Displayed NW subscriber count on whole duration matched with the expected results? - " + istrue15 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-15: Displayed NW subscriber count on whole duration matched with the expected results? - " + istrue15 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("est step-15: Testing of displayed NW subscriber count on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-15: Testing of displayed NW subscriber count on whole duration is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2&&istrue3&&istrue4&&istrue5&&istrue6&&istrue7&&istrue8&&istrue9&&istrue10&&istrue11&&istrue12&&istrue13&&istrue14&&istrue15;
        softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "PreAnalysisSummary", "Dload" }, priority = 34, enabled=true)
	public void t035PreAnaSumm3SessnDload() throws Exception {
		log.info("Test Case - t035: Starting to test session count in Pre-Analysis Summary (3 of 3) by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t035: Starting to test session count in Pre-Analysis Summary (3 of 3) by downloading the Excel report and comparing it with the reference report");
		ccc.Two3PaS().click();
		log.info("Clicked on <Pre-Analysis Summary (3 of 3)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(two.sessnCountMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		two.sessnCountMaxButn().click();
		log.info("Clicked on session count maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of session count");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of session count");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of session count is successful");
		} else {
			log.debug("Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of session count is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of session count is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		if (two.sessnCountZoominButton().getText().equals("DAY")) {
			log.debug("Zoomin button is set to DAY, no change in Zoom in");
		} else {
			log.error("Zoomin button is not set to DAY, setting it to DAY");
			two.subsCountZoominButton().click();
			Thread.sleep(1000);
			ccc.DayButton().click();
			log.debug("Zoomin button set to DAY");
			Thread.sleep(5000);
		}
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(1000);
		ccc.deleteOldFile(downloaddir+"PreSessnCount.xlsx");
		Thread.sleep(1000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"PreSessnCount.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"PreSessnCount.xlsx", downloaddir+"PreSessnCount.xlsx", 2, 0, 14, 3);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "PreAnalysisSummary", "Chart" }, priority = 35, enabled=true)
	public void t036PreAnaSumm3SessnChart() throws Exception {
		log.info("Test Case - t036: Starting to test displayed session count in Pre-Analysis Summary (3 of 3)");
		test.log(LogStatus.INFO, "Test Case - t036: Starting to test displayed session count in Pre-Analysis Summary (3 of 3)");
		ccc.Two3PaS().click();
		log.info("Clicked on <Pre-Analysis Summary (3 of 3)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(two.sessnCountMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		two.sessnCountMaxButn().click();
		log.info("Clicked on displayed count maximize button");
		Thread.sleep(5000);
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		Thread.sleep(3000);
		if (two.sessnCountZoominButton().getText().equals("DAY")) {
			log.debug("Zoomin button is set to DAY, no change in Zoom in");
		} else {
			log.error("Zoomin button is not set to DAY, setting it to DAY");
			two.subsCountZoominButton().click();
			Thread.sleep(1000);
			ccc.DayButton().click();
			log.debug("Zoomin button set to DAY");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		log.debug("Test step-1: Starting to test displayed DEP session count on <28-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed DEP session count on <28-Jun-2016>");
		try {
			screen.click(two.depSessnCountpatternOn628());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(46).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed DEP session count on <28-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed DEP session count on <28-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed DEP session count on <28-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed DEP session count on <28-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed DEP session count on <28-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed DEP session count on <28-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-2: Starting to test displayed NW session count on <28-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed NW session count on <28-Jun-2016>");
		try {
			screen.click(two.nwSessnCountpatternOn628());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(47).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed NW session count on <28-Jun-2016> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed NW session count on <28-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed NW session count on <28-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed NW session count on <28-Jun-2016> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed NW session count on <28-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed NW session count on <28-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-3: Starting to test displayed CHG session count on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed CHG session count on <29-Jun-2016>");
		try {
			screen.click(two.chgSessnCountpatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(48).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		istrue3 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue3) {
			log.info("Displayed CHG session count on <29-Jun-2016> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed CHG session count on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed CHG session count on <29-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed CHG session count on <29-Jun-2016> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed CHG session count on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed CHG session count on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-4: Starting to test displayed DEP session count on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-4: Starting to test displayed DEP session count on <29-Jun-2016>");
		try {
			screen.click(two.depSessnCountpatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(49).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		istrue4 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue4) {
			log.info("Displayed DEP session count on <29-Jun-2016> matched with the expected results? - " + istrue4);
			test.log(LogStatus.PASS, "Test step-4: Testing of displayed DEP session count on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed DEP session count on <29-Jun-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-4: Displayed DEP session count on <29-Jun-2016> matched with the expected results? - " + istrue4 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-4: Testing of displayed DEP session count on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-4: Testing of displayed DEP session count on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-5: Starting to test displayed NW session count on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-5: Starting to test displayed NW session count on <29-Jun-2016>");
		try {
			screen.click(two.nwSessnCountpatternOn629());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(50).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		istrue5 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue5) {
			log.info("Displayed NW session count on <29-Jun-2016> matched with the expected results? - " + istrue5);
			test.log(LogStatus.PASS, "Test step-5: Testing of displayed NW session count on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed NW session count on <29-Jun-2016> matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-5: Displayed NW session count on <29-Jun-2016> matched with the expected results? - " + istrue5 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-5: Testing of displayed NW session count on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-5: Testing of displayed NW session count on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-6: Starting to test displayed CHG session count on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-6: Starting to test displayed CHG session count on <30-Jun-2016>");
		try {
			screen.click(two.chgSessnCountpatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(51).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		istrue6 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue6) {
			log.info("Displayed CHG session count on <30-Jun-2016> matched with the expected results? - " + istrue6);
			test.log(LogStatus.PASS, "Test step-6: Testing of displayed CHG session count on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed CHG session count on <30-Jun-2016> matched with the expected results? - " + istrue6 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-6: Displayed CHG session count on <30-Jun-2016> matched with the expected results? - " + istrue6 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-6: Testing of displayed CHG session count on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-6: Testing of displayed CHG session count on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-7: Starting to test displayed DEP session count on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-7: Starting to test displayed DEP session count on <30-Jun-2016>");
		try {
			screen.click(two.depSessnCountpatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(52).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		istrue7 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue7) {
			log.info("Displayed DEP session count on <30-Jun-2016> matched with the expected results? - " + istrue7);
			test.log(LogStatus.PASS, "Test step-7: Testing of displayed DEP session count on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed DEP session count on <30-Jun-2016> matched with the expected results? - " + istrue7 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-7: Displayed DEP session count on <30-Jun-2016> matched with the expected results? - " + istrue7 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-7: Testing of displayed DEP session count on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-7: Testing of displayed DEP session count on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-8: Starting to test displayed NW session count on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-8: Starting to test displayed NW session count on <30-Jun-2016>");
		try {
			screen.click(two.nwSessnCountpatternOn630());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(53).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		istrue8 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue8) {
			log.info("Displayed NW session count on <30-Jun-2016> matched with the expected results? - " + istrue8);
			test.log(LogStatus.PASS, "Test step-8: Testing of displayed NW session count on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed NW session count on <30-Jun-2016> matched with the expected results? - " + istrue8 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-8: Displayed NW session count on <30-Jun-2016> matched with the expected results? - " + istrue8 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-8: Testing of displayed NW session count on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-8: Testing of displayed NW session count on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-9: Starting to test displayed CHG session count on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-9: Starting to test displayed CHG session count on <01-Jul-2016>");
		try {
			screen.click(two.chgSessnCountpatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(54).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		istrue9 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue9) {
			log.info("Displayed CHG session count on <01-Jul-2016> matched with the expected results? - " + istrue9);
			test.log(LogStatus.PASS, "Test step-9: Testing of displayed CHG session count on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed CHG session count on <01-Jul-2016> matched with the expected results? - " + istrue9 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-9: Displayed CHG session count on <01-Jul-2016> matched with the expected results? - " + istrue9 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-9: Testing of displayed CHG session count on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-9: Testing of displayed CHG session count on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-10: Starting to test displayed DEP session count on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-10: Starting to test displayed DEP session count on <01-Jul-2016>");
		try {
			screen.click(two.depSessnCountpatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(55).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		istrue10 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue10) {
			log.info("Displayed DEP session count on <01-Jul-2016> matched with the expected results? - " + istrue10);
			test.log(LogStatus.PASS, "Test step-10: Testing of displayed DEP session count on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed DEP session count on <01-Jul-2016> matched with the expected results? - " + istrue10 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-10: Displayed DEP session count on <01-Jul-2016> matched with the expected results? - " + istrue10 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-10: Testing of displayed DEP session count on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-10: Testing of displayed DEP session count on <01-Jul-2016> is Failed\r\n", imagepath);
	    }

		Thread.sleep(3000);

		log.debug("Test step-11: Starting to test displayed NW session count on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-11: Starting to test displayed NW session count on <01-Jul-2016>");
		try {
			screen.click(two.nwSessnCountpatternOn701());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(56).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		istrue11 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue11) {
			log.info("Displayed NW session count on <01-Jul-2016> matched with the expected results? - " + istrue11);
			test.log(LogStatus.PASS, "Test step-11: Testing of displayed NW session count on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed NW session count on <01-Jul-2016> matched with the expected results? - " + istrue11 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-11: Displayed NW session count on <01-Jul-2016> matched with the expected results? - " + istrue11 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-11: Testing of displayed NW session count on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-11: Testing of displayed NW session count on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-12: Starting to test displayed CHG session count on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-12: Starting to test displayed CHG session count on <02-Jul-2016>");
		try {
			screen.click(two.chgSessnCountpatternOn702());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(57).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		istrue12 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue12) {
			log.info("Displayed CHG session count on <02-Jul-2016> matched with the expected results? - " + istrue12);
			test.log(LogStatus.PASS, "Test step-12: Testing of displayed CHG session count on <02-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed CHG session count on <02-Jul-2016> matched with the expected results? - " + istrue12 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-12: Displayed CHG session count on <02-Jul-2016> matched with the expected results? - " + istrue12 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-12: Testing of displayed CHG session count on <02-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-12: Testing of displayed CHG session count on <02-Jul-2016> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-13: Starting to test displayed CHG session count on whole duration");
		test.log(LogStatus.INFO, "Test step-13: Starting to test displayed CHG session count on whole duration");
		two.sessnCountZoominButton().click();
		Thread.sleep(1000);
		ccc.YearButton().click();
		log.debug("Zoomin button set to YEAR");
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(two.chgSessnCountpatternOnWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(two.sessnContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(58).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		log.debug(refData);
		istrue13 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue13) {
			log.info("Displayed CHG session count on whole duration matched with the expected results? - " + istrue13);
			test.log(LogStatus.PASS, "Test step-13: Testing of displayed CHG session count on whole duration is successful");
	    } else {
	    	log.info("Displayed CHG session count on whole duration matched with the expected results? - " + istrue13 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-13: Displayed CHG session count on whole duration matched with the expected results? - " + istrue13 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-13: Testing of displayed CHG session count on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-13: Testing of displayed CHG session count on whole duration is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		
		log.debug("Test step-14: Starting to test displayed DEP session count on whole duration");
		test.log(LogStatus.INFO, "Test step-14: Starting to test displayed DEP session count on whole duration");
		try {
			screen.click(two.depSessnCountpatternOnWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(3000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData = Float.valueOf(two.sessnContainer().getText()).floatValue();
		one.closeDetailsButn().click();
		log.debug("displayedData: " + displayedData);
		try {
			refData = ExcelWSheetoneraad.getRow(59).getCell(10).getNumericCellValue();
			log.debug("refData: " + refData);
			refData = refData.floatValue();
			log.debug("Float refData: " + refData);
		} catch (Exception e) {
			log.trace(e);
			log.debug("something went wrong while trying to read data from Excel file");
		}
		
		istrue14 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue14) {
			log.info("Displayed DEP session count on whole duration matched with the expected results? - " + istrue14);
			test.log(LogStatus.PASS, "Test step-14: Testing of displayed DEP session count on whole duration is successful");
	    } else {
	    	log.info("Displayed DEP session count on whole duration matched with the expected results? - " + istrue14 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "DTest step-14: isplayed DEP session count on whole duration matched with the expected results? - " + istrue14 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-14: Testing of displayed DEP session count on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-14: Testing of displayed DEP session count on whole duration is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-15: Starting to test displayed NW session count on whole duration");
		test.log(LogStatus.INFO, "Test step-15: Starting to test displayed NW session count on whole duration");
		try {
			screen.click(two.nwSessnCountpatternOnWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(two.sessnContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(60).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		istrue15 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue15) {
			log.info("Displayed NW session count on whole duration matched with the expected results? - " + istrue15);
			test.log(LogStatus.PASS, "Test step-15: Testing of displayed NW session count on whole duration is successful");
	    } else {
	    	log.info("Displayed NW session count on whole duration matched with the expected results? - " + istrue15 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-15: Displayed NW session count on whole duration matched with the expected results? - " + istrue15 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-15: Testing of displayed NW session count on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-15: Testing of displayed NW session count on whole duration is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);
		istrue = istrue1&&istrue2&&istrue3&&istrue4&&istrue5&&istrue6&&istrue7&&istrue8&&istrue9&&istrue10&&istrue11&&istrue12&&istrue13&&istrue14&&istrue15;
        softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Dload" }, priority = 36, enabled=true)
	public void t037RaAnaGapSumm1ApnDload() throws Exception {
		log.info("Test Case - t037: Starting to test APN-wise misisng and under-reported gap summary in <3a. RA Analyst� GAP Summary (1/2)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t037: Starting to test APN-wise misisng and under-reported gap summary in <3a. RA Analyst� GAP Summary (1/2)> by downloading the Excel report and comparing it with the reference report");
		ccc.Three1RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (1/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.apnMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.apnMaxButn().click();
		log.info("Clicked on APNs maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of APN-wise misisng and under-reported gap summary");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of APN-wise misisng and under-reported gap summary");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time of APN-wise misisng and under-reported gap summary window matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of APN-wise misisng and under-reported gap summary is successful");
		} else {
			log.debug("Is time of APN-wise misisng and under-reported gap summary window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of APN-wise misisng and under-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of APN-wise misisng and under-reported gap summary is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of APN-wise misisng and under-reported gap summary is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(3000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(1000);
		ccc.deleteOldFile(downloaddir+"GapSumMisUndApn.xlsx");
		Thread.sleep(1000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"GapSumMisUndApn.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"GapSumMisUndApn.xlsx", downloaddir+"GapSumMisUndApn.xlsx", 4, 0, 5, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Chart" }, priority = 37, enabled=true)
	public void t038RaAnaGapSumm1ApnChart() throws Exception {
		log.info("Test Case - t038: Starting to test displayed APN-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)>");
		test.log(LogStatus.INFO, "Test Case - t038: Starting to test displayed APN-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)>");
		ccc.Three1RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (1/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.apnMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.apnMaxButn().click();
		log.info("Clicked on APNs maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test displayed data for the APN-wise missing and under-reported volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed data for APN-wise missing and under-reported volume");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(three.misUndrApnpattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedString = three.dimContainer().getText();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refString = ExcelWSheetoneraad.getRow(67).getCell(0).getStringCellValue();
		refData = ExcelWSheetoneraad.getRow(67).getCell(1).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData) && ccc.equalityOfTwoStrings(refString, displayedString);
		if (istrue1) {
			log.info("Displayed data for APN-wise missing and under-reported volume matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed data for APN-wise missing and under-reported volume is successful");
	    } else {
	    	log.info("Displayed data for APN-wise missing and under-reported volume matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed data for APN-wise missing and under-reported volume matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed data for APN-wise missing and under-reported volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed data for APN-wise missing and under-reported volume is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Dload" }, priority = 38, enabled=true)
	public void t039RaAnaGapSumm1SiteDload() throws Exception {
		log.info("Test Case - t039: Starting to test Site-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t039: Starting to test Site-wise misisng and under-reported gap summary in <3a. RA Analyst� GAP Summary (1/2)> by downloading the Excel report and comparing it with the reference report");
		ccc.Three1RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (1/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.siteMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.siteMaxButn().click();
		log.info("Clicked on Sites maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Site-wise misisng and under-reported gap summary");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Site-wise misisng and under-reported gap summary");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Site-wise misisng and under-reported gap summary is successful");
		} else {
			log.debug("Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Site-wise misisng and under-reported gap summary is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Site-wise misisng and under-reported gap summary is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(1000);
		ccc.deleteOldFile(downloaddir+"GapSumMisUndSite.xlsx");
		Thread.sleep(1000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"GapSumMisUndSite.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"GapSumMisUndSite.xlsx", downloaddir+"GapSumMisUndSite.xlsx", 4, 0, 5, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Chart" }, priority = 39, enabled=true)
	public void t040RaAnaGapSumm1SiteChart() throws Exception {
		log.info("Test Case - t040: Starting to test displayed Site-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)>");
		test.log(LogStatus.INFO, "Test Case - t040: Starting to test displayed Site-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)>");
		ccc.Three1RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (1/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.siteMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.siteMaxButn().click();
		log.info("Clicked on Sites maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test displayed data for the Site-wise missing and under-reported volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed data for Site-wise missing and under-reported volume");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(three.misUndrSitepattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedString = three.dimContainer().getText();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refString = ExcelWSheetoneraad.getRow(67).getCell(3).getStringCellValue();
		refData = ExcelWSheetoneraad.getRow(67).getCell(4).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData) && ccc.equalityOfTwoStrings(refString, displayedString);
		if (istrue1) {
			log.info("Displayed data for Site-wise missing and under-reported volume matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed data for Site-wise missing and under-reported volume is successful");
	    } else {
	    	log.info("Displayed data for Site-wise missing and under-reported volume matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed data for Site-wise missing and under-reported volume matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed data for Site-wise missing and under-reported volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed data for Site-wise missing and under-reported volume is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Dload" }, priority = 40, enabled=true)
	public void t041RaAnaGapSumm1VolBucketDload() throws Exception {
		log.info("Test Case - t041: Starting to test Volume Bucket-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t041: Starting to test Volume Bucket-wise misisng and under-reported gap summary in <3a. RA Analyst� GAP Summary (1/2)> by downloading the Excel report and comparing it with the reference report");
		ccc.Three1RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (1/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.volBucketMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.volBucketMaxButn().click();
		log.info("Clicked on Volume Buckets maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Volume Bucket-wise misisng and under-reported gap summary");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Volume Bucket-wise misisng and under-reported gap summary");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of Volume Bucket-wise misisng and under-reported gap summary matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Volume Bucket-wise misisng and under-reported gap summary is successful");
		} else {
			log.debug("Is time window of Volume Bucket-wise misisng and under-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Volume Bucket-wise misisng and under-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Volume Bucket-wise misisng and under-reported gap summary is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Volume Bucket-wise misisng and under-reported gap summary is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}

		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(1000);
		ccc.deleteOldFile(downloaddir+"GapSumMisUndVB.xlsx");
		Thread.sleep(1000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"GapSumMisUndVB.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"GapSumMisUndVB.xlsx", downloaddir+"GapSumMisUndVB.xlsx", 4, 0, 7, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Chart" }, priority = 41, enabled=true)
	public void t042RaAnaGapSumm1VolBucketChart() throws Exception {
		log.info("Test Case - t042: Starting to test displayed Volume Bucket-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)>");
		test.log(LogStatus.INFO, "Test Case - t042: Starting to test displayed Volume Bucket-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)>");
		ccc.Three1RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (1/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.volBucketMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.volBucketMaxButn().click();
		log.info("Clicked on Volume Bucket maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test displayed data for the Volume Bucket-wise missing and under-reported volume for <bucket index 3>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 3>");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(three.misUndrVolBucket1pattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData1 = Float.valueOf(three.dimContainer().getText()).floatValue();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData1 = ExcelWSheetoneraad.getRow(67).getCell(6).getNumericCellValue();
		refData1 = refData1.floatValue();
		refData = ExcelWSheetoneraad.getRow(67).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData1,displayedData1) && ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed data matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 3> is successful");
	    } else {
	    	log.info("Displayed data matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed data matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 3> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 3> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-2: Starting to test displayed data for the Volume Bucket-wise missing and under-reported volume for <bucket index 2>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 2>");
		try {
			screen.click(three.misUndrVolBucket2pattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData1 = Float.valueOf(three.dimContainer().getText()).floatValue();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData1 = ExcelWSheetoneraad.getRow(68).getCell(6).getNumericCellValue();
		refData1 = refData1.floatValue();
		refData = ExcelWSheetoneraad.getRow(68).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData1,displayedData1) && ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 2> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 2> is successful");
	    } else {
	    	log.info("Displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 2> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 2> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 2> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 2> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-3: Starting to test displayed data for the Volume Bucket-wise missing and under-reported volume for <bucket index 1>");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 1>");
		try {
			screen.click(three.misUndrVolBucket3pattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData1 = Float.valueOf(three.dimContainer().getText()).floatValue();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData1 = ExcelWSheetoneraad.getRow(69).getCell(6).getNumericCellValue();
		refData1 = refData1.floatValue();
		refData = ExcelWSheetoneraad.getRow(69).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue3 = ccc.equalityOfTwoNumbers(refData1,displayedData1) && ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue3) {
			log.info("Displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 1> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 1> is successful");
	    } else {
	    	log.info("Displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 1> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 1> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 1> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed data for Volume Bucket-wise missing and under-reported volume for <bucket index 1> is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2&&istrue3;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Dload" }, priority = 42, enabled=true)
	public void t043RaAnaGapSumm1VplmnDload() throws Exception {
		log.info("Test Case - t043: Starting to test VPLMN-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t043: Starting to test VPLMN-wise misisng and under-reported gap summary in <3a. RA Analyst� GAP Summary (1/2)> by downloading the Excel report and comparing it with the reference report");
		ccc.Three1RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (1/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.vplmnMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.vplmnMaxButn().click();
		log.info("Clicked on VPLMN maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of VPLMN-wise misisng and under-reported gap summary");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of VPLMN-wise misisng and under-reported gap summary");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching of VPLMN-wise misisng and under-reported gap summary with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of VPLMN-wise misisng and under-reported gap summary is successful");
		} else {
			log.debug("Is time window of VPLMN-wise misisng and under-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of VPLMN-wise misisng and under-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of VPLMN-wise misisng and under-reported gap summary is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of VPLMN-wise misisng and under-reported gap summary is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(1000);
		ccc.deleteOldFile(downloaddir+"GapSumMisUndVplmn.xlsx");
		Thread.sleep(1000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"GapSumMisUndVplmn.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"GapSumMisUndVplmn.xlsx", downloaddir+"GapSumMisUndVplmn.xlsx", 4, 0, 5, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1&&istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Chart" }, priority = 43, enabled=true)
	public void t044RaAnaGapSumm1VplmnChart() throws Exception {
		log.info("Test Case - t044: Starting to test displayed VPLMN-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)>");
		test.log(LogStatus.INFO, "Test Case - t044: Starting to test displayed VPLMN-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)>");
		ccc.Three1RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (1/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.vplmnMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.vplmnMaxButn().click();
		log.info("Clicked on VPLMN maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test displayed data for the VPLMN-wise missing and under-reported volume for <VPLMN 62130>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed data for VPLMN-wise missing and under-reported volume for <VPLMN 62130>");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(three.misUndrVplmnpattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData1 = Float.valueOf(three.dimContainer().getText()).floatValue();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData1 = ExcelWSheetoneraad.getRow(67).getCell(9).getNumericCellValue();
		refData1 = refData1.floatValue();
		refData = ExcelWSheetoneraad.getRow(67).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData1,displayedData1) && ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed data for VPLMN-wise missing and under-reported volume for <VPLMN 62130> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed data for VPLMN-wise missing and under-reported volume for <VPLMN 62130> is successful");
	    } else {
	    	log.info("Displayed data for VPLMN-wise missing and under-reported volume for <VPLMN 62130> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed data for VPLMN-wise missing and under-reported volume for <VPLMN 62130> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed data for VPLMN-wise missing and under-reported volume for <VPLMN 62130> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed data for VPLMN-wise missing and under-reported volume for <VPLMN 62130> is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}

	@Test(groups = { "all_tests", "RaAnaGapSummary", "Dload" }, priority = 44, enabled=true)
	public void t045RaAnaGapSumm1RevStrmDload() throws Exception {
		log.info("Test Case - t045: Starting to test Rvevenue Stream-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t045: Starting to test Rvevenue Stream-wise misisng and under-reported gap summary in <3a. RA Analyst� GAP Summary (1/2)> by downloading the Excel report and comparing it with the reference report");
		ccc.Three1RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (1/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.revStrmMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.revStrmMaxButn().click();
		log.info("Clicked on Rvevenue Stream maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Rvevenue Stream-wise misisng and under-reported gap summary");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Rvevenue Stream-wise misisng and under-reported gap summary");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of Rvevenue Stream-wise misisng and under-reported gap summary matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Rvevenue Stream-wise misisng and under-reported gap summary is successful");
		} else {
			log.debug("Is time window of Rvevenue Stream-wise misisng and under-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Rvevenue Stream-wise misisng and under-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Rvevenue Stream-wise misisng and under-reported gap summary is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Rvevenue Stream-wise misisng and under-reported gap summary is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(1000);
		ccc.deleteOldFile(downloaddir+"GapSumMisUndRvSt.xlsx");
		Thread.sleep(1000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"GapSumMisUndRvSt.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"GapSumMisUndRvSt.xlsx", downloaddir+"GapSumMisUndRvSt.xlsx", 4, 0, 6, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Chart" }, priority = 45, enabled=true)
	public void t046RaAnaGapSumm1RevStrmChart() throws Exception {
		log.info("Test Case - t046: Starting to test displayed Revenue Stream-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)>");
		test.log(LogStatus.INFO, "Test Case - t046: Starting to test displayed Revenue Stream-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)>");
		ccc.Three1RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (1/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.revStrmMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.revStrmMaxButn().click();
		log.info("Clicked on Revenue Stream maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test displayed Revenue Stream-wise missing and under-reported volume for the <Postpaid> subscriber");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed Revenue Stream-wise missing and under-reported volume for the <Postpaid> subscriber");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(three.misUndrRevStrm1pattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedString = three.dimContainer().getText();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refString = ExcelWSheetoneraad.getRow(67).getCell(12).getStringCellValue();
		refData = ExcelWSheetoneraad.getRow(67).getCell(13).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData) && ccc.equalityOfTwoStrings(refString, displayedString);
		if (istrue1) {
			log.info("Displayed Revenue Stream-wise missing and under-reported volume for the <Postpaid> subscriber matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed Revenue Stream-wise missing and under-reported volume for the <Postpaid> subscriber is successful");
	    } else {
	    	log.info("Displayed Revenue Stream-wise missing and under-reported volume for the <Postpaid> subscriber matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed Revenue Stream-wise missing and under-reported volume for the <Postpaid> subscriber matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed Revenue Stream-wise missing and under-reported volume for the <Postpaid> subscriber is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed Revenue Stream-wise missing and under-reported volume for the <Postpaid> subscriber is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed Revenue Stream-wise missing and under-reported volume for the <Prepaid> subscriber");
		log.debug("Test step-2: Starting to test displayed Revenue Stream-wise missing and under-reported volume for the <Prepaid> subscriber");
		try {
			screen.click(three.misUndrRevStrm2pattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedString = three.dimContainer().getText();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refString = ExcelWSheetoneraad.getRow(68).getCell(12).getStringCellValue();
		refData = ExcelWSheetoneraad.getRow(68).getCell(13).getNumericCellValue();
		refData = refData.floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData,displayedData) && ccc.equalityOfTwoStrings(refString, displayedString);
		if (istrue2) {
			log.info("Displayed Revenue Stream-wise missing and under-reported volume for the <Prepaid> subscriber matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed Revenue Stream-wise missing and under-reported volume for the <Prepaid> subscriber is successful");
	    } else {
	    	log.info("Displayed Revenue Stream-wise missing and under-reported volume for the <Prepaid> subscriber matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed Revenue Stream-wise missing and under-reported volume for the <Prepaid> subscriber matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of Test step-2: Starting to test displayed Revenue Stream-wise missing and under-reported volume for the <Prepaid> subscriber is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of Test step-2: Starting to test displayed Revenue Stream-wise missing and under-reported volume for the <Prepaid> subscriber is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2;		
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}

	@Test(groups = { "all_tests", "RaAnaGapSummary", "Dload" }, priority = 46, enabled=true)
	public void t047RaAnaGapSumm1ChgCharDload() throws Exception {
		log.info("Test Case - t047: Starting to test Charging Characteristics-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t047: Starting to test Charging Characteristics-wise misisng and under-reported gap summary in <3a. RA Analyst� GAP Summary (1/2)> by downloading the Excel report and comparing it with the reference report");
		ccc.Three1RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (1/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.chgCharMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.chgCharMaxButn().click();
		log.info("Clicked on Charging Characteristics maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Volume Charging Characteristicst-wise misisng and under-reported gap summary");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Volume Charging Characteristics-wise misisng and under-reported gap summary");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of Charging Characteristics-wise misisng and under-reported gap summary matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Charging Characteristics-wise misisng and under-reported gap summary is successful");
		} else {
			log.debug("Is time window of Charging Characteristics-wise misisng and under-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Charging Characteristics-wise misisng and under-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Charging Characteristics-wise misisng and under-reported gap summary is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Charging Characteristics-wise misisng and under-reported gap summary is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(1000);
		ccc.deleteOldFile(downloaddir+"GapSumMisUndCC.xlsx");
		Thread.sleep(1000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"GapSumMisUndCC.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"GapSumMisUndCC.xlsx", downloaddir+"GapSumMisUndCC.xlsx", 2, 0, 4, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1&&istrue2;		
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Chart" }, priority = 47, enabled=true)
	public void t048RaAnaGapSumm1ChgCharChart() throws Exception {
		log.info("Test Case - t048: Starting to test displayed Charging Characteristicst-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)>");
		test.log(LogStatus.INFO, "Test Case - t048: Starting to test displayed Charging Characteristicst-wise misisng and under-reported gap summary in < 3a. RA Analyst� GAP Summary (1/2)>");
		ccc.Three1RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (1/2)>");

		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.chgCharMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.chgCharMaxButn().click();
		log.info("Clicked on Charging Characteristicst <1024> maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test displayed data for the Charging Characteristicst-wise missing and under-reported volume for <CC 1024>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed data for Charging Characteristicst-wise missing and under-reported volume for <CC 1024>");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(three.misUndrChgChar1pattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData1 = Float.valueOf(three.dimContainer().getText()).floatValue();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.valContainerCC().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData1 = ExcelWSheetoneraad.getRow(67).getCell(15).getNumericCellValue();
		refData1 = refData1.floatValue();
		refData = ExcelWSheetoneraad.getRow(67).getCell(16).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData1,displayedData1) && ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed data for Charging Characteristicst-wise missing and under-reported volume for <CC 1024> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed data for Charging Characteristicst-wise missing and under-reported volume for <CC 1024> is successful");
	    } else {
	    	log.info("Displayed data for Charging Characteristicst-wise missing and under-reported volume for <CC 1024> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed data for Charging Characteristicst-wise missing and under-reported volume for <CC 1024> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed data for Charging Characteristicst-wise missing and under-reported volume for <CC 1024> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed data for Charging Characteristicst-wise missing and under-reported volume for <CC 1024> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-2: Starting to test displayed data for the Charging Characteristicst-wise missing and under-reported volume for <CC 1280>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed data for Charging Characteristicst-wise missing and under-reported volume for <CC 1280>");
		try {
			screen.click(three.misUndrChgChar2pattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData1 = Float.valueOf(three.dimContainer().getText()).floatValue();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.valContainerCC().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData1 = ExcelWSheetoneraad.getRow(68).getCell(15).getNumericCellValue();
		refData1 = refData1.floatValue();
		refData = ExcelWSheetoneraad.getRow(68).getCell(16).getNumericCellValue();
		refData = refData.floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData1,displayedData1) && ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed data matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed data for Charging Characteristicst-wise missing and under-reported volume for <CC 1280> is successful");
	    } else {
	    	log.info("Displayed data matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed data matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed data for Charging Characteristicst-wise missing and under-reported volume for <CC 1280> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed data for Charging Characteristicst-wise missing and under-reported volume for <CC 1280> is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2;		
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Dload" }, priority = 48, enabled=true)
	public void t049RaAnaGapSumm2ApnDload() throws Exception {
		log.info("Test Case - t049: Starting to test APN-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t049: Starting to test APN-wise over-reported gap summary in <3a. RA Analyst� GAP Summary (2/2)> by downloading the Excel report and comparing it with the reference report");
		ccc.Three2RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (2/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.apnMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.apnMaxButn().click();
		log.info("Clicked on APNs maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of APN-wise over-reported gap summary");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of APN-wise over-reported gap summary");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of APN-wise over-reported gap summary matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of APN-wise over-reported gap summary is successful");
		} else {
			log.debug("Is time window of APN-wise over-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of APN-wise over-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of APN-wise over-reported gap summary is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of APN-wise over-reported gap summary is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(1000);
		ccc.deleteOldFile(downloaddir+"GapSumOvrApn.xlsx");
		Thread.sleep(1000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"GapSumOvrApn.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"GapSumOvrApn.xlsx", downloaddir+"GapSumOvrApn.xlsx", 4, 0, 5, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1&&istrue2;		
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Chart" }, priority = 49, enabled=true)
	public void t050RaAnaGapSumm2ApnChart() throws Exception {
		log.info("Test Case - t050: Starting to test displayed APN-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)>");
		test.log(LogStatus.INFO, "Test Case - t050: Starting to test displayed APN-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)>");
		ccc.Three2RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (2/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.apnMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.apnMaxButn().click();
		log.info("Clicked on APNs maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test displayed data for the APN-wise over-reported volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed data for APN-wise over-reported volume");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(three.overApnpattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedString = three.dimContainer().getText();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.overValContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refString = ExcelWSheetoneraad.getRow(88).getCell(0).getStringCellValue();
		refData = ExcelWSheetoneraad.getRow(88).getCell(1).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData) && ccc.equalityOfTwoStrings(refString, displayedString);
		if (istrue1) {
			log.info("Displayed data for APN-wise over-reported volume matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed data for APN-wise over-reported volume is successful");
	    } else {
	    	log.info("Displayed data for APN-wise over-reported volume matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed data for APN-wise over-reported volume matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed data for APN-wise over-reported volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed data for APN-wise over-reported volume is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Dload" }, priority = 50, enabled=true)
	public void t051RaAnaGapSumm2SiteDload() throws Exception {
		log.info("Test Case - t051: Starting to test Site-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t051: Starting to test Site-wise over-reported gap summary in <3a. RA Analyst� GAP Summary (2/2)> by downloading the Excel report and comparing it with the reference report");
		ccc.Three2RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (2/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.siteMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.siteMaxButn().click();
		log.info("Clicked on Sites maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Site-wise over-reported gap summary");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Site-wise over-reported gap summary");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of Site-wise over-reported gap summary matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Site-wise over-reported gap summary is successful");
		} else {
			log.debug("Is time window of Site-wise over-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Is time window of Site-wise over-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Site-wise over-reported gap summary is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Site-wise over-reported gap summary is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(1000);
		ccc.deleteOldFile(downloaddir+"GapSumOvrSite.xlsx");
		Thread.sleep(1000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"GapSumOvrSite.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"GapSumOvrSite.xlsx", downloaddir+"GapSumOvrSite.xlsx", 4, 0, 5, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Chart" }, priority = 51, enabled=true)
	public void t052RaAnaGapSumm2SiteChart() throws Exception {
		log.info("Test Case - t052: Starting to test displayed Site-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)>");
		test.log(LogStatus.INFO, "Test Case - t052: Starting to test displayed Site-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)>");
		ccc.Three2RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (2/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.siteMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.siteMaxButn().click();
		log.info("Clicked on Sites maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test displayed data for the Site-wise over-reported volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed data for Site-wise over-reported volume");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(three.overSitepattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedString = three.dimContainer().getText();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.overValContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refString = ExcelWSheetoneraad.getRow(88).getCell(3).getStringCellValue();
		refData = ExcelWSheetoneraad.getRow(88).getCell(4).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData) && ccc.equalityOfTwoStrings(refString, displayedString);
		if (istrue1) {
			log.info("Displayed data for Site-wise over-reported volume matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed data for Site-wise over-reported volume is successful");
	    } else {
	    	log.info("Displayed data for Site-wise over-reported volume matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed data for Site-wise over-reported volume matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed data for Site-wise over-reported volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed data for Site-wise over-reported volume is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Dload" }, priority = 52, enabled=true)
	public void t053RaAnaGapSumm2VolBucketDload() throws Exception {
		log.info("Test Case - t053: Starting to test Volume Bucket-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t053: Starting to test Volume Bucket-wiseover-reported gap summary in <3a. RA Analyst� GAP Summary (2/2)> by downloading the Excel report and comparing it with the reference report");
		ccc.Three2RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (2/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.volBucketMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.volBucketMaxButn().click();
		log.info("Clicked on Volume Buckets maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Volume Bucket-wise over-reported gap summary");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Volume Bucket-wise over-reported gap summary");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of Volume Bucket-wise over-reported gap summary matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Volume Bucket-wise over-reported gap summary is successful");
		} else {
			log.debug("Is time window of Volume Bucket-wise over-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Volume Bucket-wise over-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Volume Bucket-wise over-reported gap summary is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Volume Bucket-wise over-reported gap summary is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(1000);
		ccc.deleteOldFile(downloaddir+"GapSumOvrVB.xlsx");
		Thread.sleep(1000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"GapSumOvrVB.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"GapSumOvrVB.xlsx", downloaddir+"GapSumOvrVB.xlsx", 4, 0, 7, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Chart" }, priority = 53, enabled=true)
	public void t054RaAnaGapSumm2VolBucketChart() throws Exception {
		log.info("Test Case - t054: Starting to test displayed Volume Bucket-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)>");
		test.log(LogStatus.INFO, "Test Case - t054: Starting to test displayed Volume Bucket-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)>");
		ccc.Three2RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (2/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.volBucketMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.volBucketMaxButn().click();
		log.info("Clicked on Volume Bucket maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test displayed data for the Volume Bucket-wise over-reported volume for <bucket index 3>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed data for Volume Bucket-wise over-reported volume for <bucket index 3>");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(three.overVolBucket1pattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData1 = Float.valueOf(three.dimContainer().getText()).floatValue();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.overValContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData1 = ExcelWSheetoneraad.getRow(88).getCell(6).getNumericCellValue();
		refData1 = refData1.floatValue();
		refData = ExcelWSheetoneraad.getRow(88).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData1,displayedData1) && ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed data for Volume Bucket-wise over-reported volume for <bucket index 3> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed data for Volume Bucket-wise over-reported volume for <bucket index 3> is successful");
	    } else {
	    	log.info("Displayed data for Volume Bucket-wise over-reported volume for <bucket index 3> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed data for Volume Bucket-wise over-reported volume for <bucket index 3> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed data for Volume Bucket-wise over-reported volume for <bucket index 3> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed data for Volume Bucket-wise over-reported volume for <bucket index 3> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-2: Starting to test displayed data for the Volume Bucket-wise over-reported volume for <bucket index 2>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed data for Volume Bucket-wise over-reported volume for <bucket index 2>");
		try {
			screen.click(three.overVolBucket2pattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData1 = Float.valueOf(three.dimContainer().getText()).floatValue();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.overValContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData1 = ExcelWSheetoneraad.getRow(89).getCell(6).getNumericCellValue();
		refData1 = refData1.floatValue();
		refData = ExcelWSheetoneraad.getRow(89).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData1,displayedData1) && ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed data for Volume Bucket-wise over-reported volume for <bucket index 2> matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed data for Volume Bucket-wise over-reported volume for <bucket index 2> is successful");
	    } else {
	    	log.info("Displayed data for Volume Bucket-wise over-reported volume for <bucket index 2> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed data for Volume Bucket-wise over-reported volume for <bucket index 2> matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed data for Volume Bucket-wise over-reported volume for <bucket index 2> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed data for Volume Bucket-wise over-reported volume for <bucket index 2> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-3: Starting to test displayed data for the Volume Bucket-wise over-reported volume for <bucket index 1>");
		test.log(LogStatus.INFO, "Test step-3: Starting to test displayed data for Volume Bucket-wise over-reported volume for <bucket index 1>");
		try {
			screen.click(three.overVolBucket3pattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData1 = Float.valueOf(three.dimContainer().getText()).floatValue();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.overValContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData1 = ExcelWSheetoneraad.getRow(90).getCell(6).getNumericCellValue();
		refData1 = refData1.floatValue();
		refData = ExcelWSheetoneraad.getRow(90).getCell(7).getNumericCellValue();
		refData = refData.floatValue();
		istrue3 = ccc.equalityOfTwoNumbers(refData1,displayedData1) && ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue3) {
			log.info("Displayed data for Volume Bucket-wise over-reported volume for <bucket index 1> matched with the expected results? - " + istrue3);
			test.log(LogStatus.PASS, "Test step-3: Testing of displayed data for Volume Bucket-wise over-reported volume for <bucket index 1> is successful");
	    } else {
	    	log.info("Displayed data for Volume Bucket-wise over-reported volume for <bucket index 1> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-3: Displayed data for Volume Bucket-wise over-reported volume for <bucket index 1> matched with the expected results? - " + istrue3 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-3: Testing of displayed data for Volume Bucket-wise over-reported volume for <bucket index 1> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-3: Testing of displayed data for Volume Bucket-wise over-reported volume for <bucket index 1> is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2&&istrue3; 
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Dload" }, priority = 54, enabled=true)
	public void t055RaAnaGapSumm2VplmnDload() throws Exception {
		log.info("Test Case - t055: Starting to test VPLMN-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t055: Starting to test VPLMN-wise over-reported gap summary in <3a. RA Analyst� GAP Summary (2/2)> by downloading the Excel report and comparing it with the reference report");
		ccc.Three2RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (2/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.vplmnMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.vplmnMaxButn().click();
		log.info("Clicked on VPLMN maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of VPLMN-wise over-reported gap summary");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of VPLMN-wise over-reported gap summary");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of VPLMN-wise over-reported gap summary matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of VPLMN-wise over-reported gap summary is successful");
		} else {
			log.debug("Is time window of VPLMN-wise over-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of VPLMN-wise over-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of VPLMN-wise over-reported gap summary is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of VPLMN-wise over-reported gap summary is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(1000);
		ccc.deleteOldFile(downloaddir+"GapSumOvrVplmn.xlsx");
		Thread.sleep(1000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(3000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"GapSumOvrVplmn.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"GapSumOvrVplmn.xlsx", downloaddir+"GapSumOvrVplmn.xlsx", 4, 0, 5, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Chart" }, priority = 55, enabled=true)
	public void t056RaAnaGapSumm2VplmnChart() throws Exception {
		log.info("Test Case - t056: Starting to test displayed VPLMN-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)>");
		test.log(LogStatus.INFO, "Test Case - t056: Starting to test displayed VPLMN-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)>");
		ccc.Three2RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (2/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.vplmnMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.vplmnMaxButn().click();
		log.info("Clicked on VPLMN maximize button");
		Thread.sleep(2000);
		log.debug("Test step-1: Starting to test displayed data for the VPLMN-wise over-reported volume for <VPLMN 62130>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed data for VPLMN-wise over-reported volume for <VPLMN 62130>");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(three.overVplmnpattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData1 = Float.valueOf(three.dimContainer().getText()).floatValue();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.overValContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData1 = ExcelWSheetoneraad.getRow(88).getCell(9).getNumericCellValue();
		refData1 = refData1.floatValue();
		refData = ExcelWSheetoneraad.getRow(88).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData1,displayedData1) && ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed data for VPLMN-wise over-reported volume for <VPLMN 62130> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed data for VPLMN-wise over-reported volume for <VPLMN 62130> is successful");
	    } else {
	    	log.info("Displayed data for VPLMN-wise over-reported volume for <VPLMN 62130> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed data for VPLMN-wise over-reported volume for <VPLMN 62130> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed data for VPLMN-wise over-reported volume for <VPLMN 62130> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed data for VPLMN-wise over-reported volume for <VPLMN 62130> is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}

	@Test(groups = { "all_tests", "RaAnaGapSummary", "Dload" }, priority = 56, enabled=true)
	public void t057RaAnaGapSumm2RevStrmDload() throws Exception {
		log.info("Test Case - t057: Starting to test Rvevenue Stream-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t057: Starting to test Rvevenue Stream-wise over-reported gap summary in <3a. RA Analyst� GAP Summary (2/2)> by downloading the Excel report and comparing it with the reference report");
		ccc.Three2RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (2/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.revStrmMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.revStrmMaxButn().click();
		log.info("Clicked on Rvevenue Stream maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Rvevenue Stream-wise over-reported gap summary");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Rvevenue Stream-wise over-reported gap summary");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time of Rvevenue Stream-wise over-reported gap summary window matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Rvevenue Stream-wise over-reported gap summary is successful");
		} else {
			log.debug("Is time window of Rvevenue Stream-wise over-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Rvevenue Stream-wise over-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Rvevenue Stream-wise over-reported gap summary is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Rvevenue Stream-wise over-reported gap summary is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(1000);
		ccc.deleteOldFile(downloaddir+"GapSumOvrRvSt.xlsx");
		Thread.sleep(1000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"GapSumOvrRvSt.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"GapSumOvrRvSt.xlsx", downloaddir+"GapSumOvrRvSt.xlsx", 4, 0, 6, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Chart" }, priority = 57, enabled=true)
	public void t058RaAnaGapSumm2RevStrmChart() throws Exception {
		log.info("Test Case - t058: Starting to test displayed Revenue Stream-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)>");
		test.log(LogStatus.INFO, "Test Case - t058: Starting to test displayed Revenue Stream-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)>");
		ccc.Three2RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (2/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.revStrmMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.revStrmMaxButn().click();
		log.info("Clicked on Revenue Stream maximize button");
		Thread.sleep(2000);
		log.debug("Test step-1: Starting to test displayed Revenue Stream-wise over-reported volume for the <Prepaid> subscriber");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed Revenue Stream-wise over-reported volume for the <Prepaid> subscriber");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(three.overRevStrm1pattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedString = three.dimContainer().getText();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.overValContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refString = ExcelWSheetoneraad.getRow(89).getCell(12).getStringCellValue();
		refData = ExcelWSheetoneraad.getRow(89).getCell(13).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData) && ccc.equalityOfTwoStrings(refString, displayedString);
		if (istrue1) {
			log.info("Displayed Revenue Stream-wise over-reported volume for the <Prepaid> subscriber matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed Revenue Stream-wise over-reported volume for the <Prepaid> subscriber is successful");
	    } else {
	    	log.info("Displayed Revenue Stream-wise over-reported volume for the <Prepaid> subscriber matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed Revenue Stream-wise over-reported volume for the <Prepaid> subscriber matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed Revenue Stream-wise over-reported volume for the <Prepaid> subscriber is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed Revenue Stream-wise over-reported volume for the <Prepaid> subscriber is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed Revenue Stream-wise over-reported volume for the <Postpaid> subscriber");
		log.debug("Test step-2: Starting to test displayed Revenue Stream-wise over-reported volume for the <Postpaid> subscriber");
		try {
			screen.click(three.overRevStrm2pattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedString = three.dimContainer().getText();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.overValContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refString = ExcelWSheetoneraad.getRow(88).getCell(12).getStringCellValue();
		refData = ExcelWSheetoneraad.getRow(88).getCell(13).getNumericCellValue();
		refData = refData.floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData,displayedData) && ccc.equalityOfTwoStrings(refString, displayedString);
		if (istrue2) {
			log.info("Displayed Revenue Stream-wise over-reported volume for the <Postpaid> subscriber matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed Revenue Stream-wise over-reported volume for the <Postpaid> subscriber is successful");
	    } else {
	    	log.info("Displayed Revenue Stream-wise over-reported volume for the <Postpaid> subscriber matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed Revenue Stream-wise over-reported volume for the <Postpaid> subscriber matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of Test step-2: Starting to test displayed Revenue Stream-wise over-reported volume for the <Postpaid> subscriber is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of Test step-2: Starting to test displayed Revenue Stream-wise over-reported volume for the <Postpaid> subscriber is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}

	@Test(groups = { "all_tests", "RaAnaGapSummary", "Dload" }, priority = 58, enabled=true)
	public void t059RaAnaGapSumm2ChgCharDload() throws Exception {
		log.info("Test Case - t059: Starting to test Charging Characteristics-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t059: Starting to test Charging Characteristics-wise over-reported gap summary in <3a. RA Analyst� GAP Summary (2/2)> by downloading the Excel report and comparing it with the reference report");
		ccc.Three2RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (2/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.chgCharMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.chgCharMaxButn().click();
		log.info("Clicked on Charging Characteristics maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Volume Charging Characteristicst-wise over-reported gap summary");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Volume Charging Characteristics-wise over-reported gap summary");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of Charging Characteristics-wise over-reported gap summary matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Charging Characteristics-wise over-reported gap summary is successful");
		} else {
			log.debug("Is time window of Charging Characteristics-wise over-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Charging Characteristics-wise over-reported gap summary matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Charging Characteristics-wise over-reported gap summary is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Charging Characteristics-wise over-reported gap summary is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(1000);
		ccc.deleteOldFile(downloaddir+"GapSumOvrCC.xlsx");
		Thread.sleep(1000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"GapSumOvrCC.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"GapSumOvrCC.xlsx", downloaddir+"GapSumOvrCC.xlsx", 2, 0, 4, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1&&istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaGapSummary", "Chart" }, priority = 59, enabled=true)
	public void t060RaAnaGapSumm2ChgCharChart() throws Exception {
		log.info("Test Case - t060: Starting to test displayed Charging Characteristicst-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)>");
		test.log(LogStatus.INFO, "Test Case - t060: Starting to test displayed Charging Characteristicst-wise over-reported gap summary in < 3a. RA Analyst� GAP Summary (2/2)>");
		ccc.Three2RaAGS().click();
		log.info("Clicked on <3a. RA Analyst� GAP Summary (2/2)>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(three.chgCharMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		three.chgCharMaxButn().click();
		log.info("Clicked on Charging Characteristicst <1024> maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test displayed data for the Charging Characteristicst-wise over-reported volume for <CC 1024>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed data for Charging Characteristicst-wise over-reported volume for <CC 1024>");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(three.overChgChar1pattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData1 = Float.valueOf(three.dimContainer().getText()).floatValue();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.overValContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData1 = ExcelWSheetoneraad.getRow(88).getCell(15).getNumericCellValue();
		refData1 = refData1.floatValue();
		refData = ExcelWSheetoneraad.getRow(88).getCell(16).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData1,displayedData1) && ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed data for Charging Characteristicst-wise over-reported volume for <CC 1024> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed data for Charging Characteristicst-wise over-reported volume for <CC 1024> is successful");
	    } else {
	    	log.info("Displayed data for Charging Characteristicst-wise over-reported volume for <CC 1024> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed data for Charging Characteristicst-wise over-reported volume for <CC 1024> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed data for Charging Characteristicst-wise over-reported volume for <CC 1024> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed data for Charging Characteristicst-wise over-reported volume for <CC 1024> is Failed\r\n", imagepath);
	    }
		Thread.sleep(3000);

		log.debug("Test step-2: Starting to test displayed data for the Charging Characteristicst-wise over-reported volume for <CC 1280>");
		test.log(LogStatus.INFO, "Test step-2: Starting to test displayed data for Charging Characteristicst-wise over-reported volume for <CC 1280>");
		try {
			screen.click(three.overChgChar2pattern());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		displayedData1 = Float.valueOf(three.dimContainer().getText()).floatValue();
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(three.overValContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData1 = ExcelWSheetoneraad.getRow(89).getCell(15).getNumericCellValue();
		refData1 = refData1.floatValue();
		refData = ExcelWSheetoneraad.getRow(89).getCell(16).getNumericCellValue();
		refData = refData.floatValue();
		istrue2 = ccc.equalityOfTwoNumbers(refData1,displayedData1) && ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue2) {
			log.info("Displayed data matched with the expected results? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of displayed data for Charging Characteristicst-wise over-reported volume for <CC 1280> is successful");
	    } else {
	    	log.info("Displayed data matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-2: Displayed data matched with the expected results? - " + istrue2 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of displayed data for Charging Characteristicst-wise over-reported volume for <CC 1280> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of displayed data for Charging Characteristicst-wise over-reported volume for <CC 1280> is Failed\r\n", imagepath);
	    }
		istrue = istrue1&&istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Dload" }, priority = 60, enabled=true)
	public void t061RaAnaApnWsMissDload() throws Exception {
		log.info("Test Case - t061: Starting to test missing volumes in <4.  RA-Analyst  APN-wise Reports> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t061: Starting to test missing volumes in <4.  RA-Analyst  APN-wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst  APN-wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.missRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.missRprtVolMax().click();
		log.info("Clicked on Missing Volume maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Missing Volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Missing Volume");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of Missing Volume matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Missing Volume is successful");
		} else {
			log.debug("Is time window of Missing Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Missing Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Missing Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Missing Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			Thread.sleep(2000);
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"fourMisVol.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"fourMisVol.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"fourMisVol.xlsx", downloaddir+"fourMisVol.xlsx", 4, 0, 5, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1&&istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Chart" }, priority = 61, enabled=true)
	public void t062RaAnaApnWsMissChartWhole() throws Exception {
		log.info("Test Case - t062: Starting to test displayed missing volumes in <4. RA-Analyst APN-wise Reports> on whole duration");
		test.log(LogStatus.INFO, "Test Case - t062: Starting to test displayed missing volumes in <4. RA-Analyst APN-wise Reports> on whole duration");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst  APN-wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.missRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.missRprtVolMax().click();
		Thread.sleep(5000);
		log.info("Clicked on Missing Volume maximize button");
		log.debug("Test step-1: Starting to test displayed missing volumes on whole duration");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed missing volumes on whole duration");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(four.misUndrOvrpatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(four.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(109).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed missing volumes on whole duration matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Testing of displayed missing volumes on whole duration is successful");
	    } else {
	    	log.info("Displayed missing volumes on whole duration matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed missing volumes on whole duration matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed missing volumes on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed missing volumes on whole duration is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Chart" }, priority = 62, enabled=true)
	public void t063RaAnaApnWsMissChart28() throws Exception {
		log.info("Test Case - t063: Starting to test displayed missing volumes in <4. RA-Analyst APN-wise Reports> on <28-Jun-2016>");
		test.log(LogStatus.INFO, "Test Case - t063: Starting to test displayed missing volumes in <4. RA-Analyst APN-wise Reports> on <28-Jun-2016>");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst APN-wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.missRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.missRprtVolMax().click();
		Thread.sleep(5000);
		log.info("Clicked on Missing Volume maximize button");
		log.debug("Test step-1: Starting to test displayed missing volumes on <28-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed missing volumes on <28-Jun-2016>");
		ccc.setDateTimeBar28();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(four.misUndrOvrpatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(four.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(110).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed missing volumes on <28-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed missing volumes on <28-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed missing volumes on <28-Jun-2016>  matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed missing volumes on <28-Jun-2016>  matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed missing volumes on <28-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed missing volumes on <28-Jun-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Chart" }, priority = 63, enabled=true)
	public void t064RaAnaApnWsMissChart29() throws Exception {
		log.info("Test Case - t064: Starting to test displayed missing volumes in <4. RA-Analyst APN-wise Reports> on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test Case - t064: Starting to test displayed missing volumes in <4. RA-Analyst APN-wise Reports> on <29-Jun-2016>");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst APN-wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.missRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.missRprtVolMax().click();
		Thread.sleep(5000);
		log.info("Clicked on Missing Volume maximize button");
		log.debug("Test step-1: Starting to test displayed missing volumes on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed missing volumes on <29-Jun-2016>");
		ccc.setDateTimeBar29();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(four.misUndrOvrpatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(four.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(111).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed missing volumes on <29-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed missing volumes on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed missing volumes on <29-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed missing volumes on <29-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed missing volumes on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed missing volumes on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Chart" }, priority = 64, enabled=true)
	public void t065RaAnaApnWsMissChart30() throws Exception {
		log.info("Test Case - t065: Starting to test displayed missing volumes in <4. RA-Analyst APN-wise Reports> on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test Case - t065: Starting to test displayed missing volumes in <4. RA-Analyst APN-wise Reports> on <30-Jun-2016>");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst APN-wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.missRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.missRprtVolMax().click();
		Thread.sleep(5000);
		log.info("Clicked on Missing Volume maximize button");
		log.debug("Test step-1: Starting to test displayed missing volumes on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed missing volumes on <29-Jun-2016>");
		ccc.setDateTimeBar30();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(four.misUndrOvrpatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(four.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(112).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed missing volumes on <30-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed missing volumes on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed missing volumes on <30-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed missing volumes on <30-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed missing volumes on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed missing volumes on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Chart" }, priority = 65, enabled=true)
	public void t066RaAnaApnWsMissChart01() throws Exception {
		log.info("Test Case - t066: Starting to test displayed missing volumes in <4. RA-Analyst APN-wise Reports> on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test Case - t066: Starting to test displayed missing volumes in <4. RA-Analyst APN-wise Reports> on <01-Jul-2016>");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst APN-wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.missRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.missRprtVolMax().click();
		Thread.sleep(5000);
		log.info("Clicked on Missing Volume maximize button");
		log.debug("Test step-1: Starting to test displayed missing volumes on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed missing volumes on <01-Jul-2016>");
		ccc.setDateTimeBar01();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		try {
			screen.click(four.misUndrOvrpatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(four.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(113).getCell(2).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed missing volumes on <01-Jul-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed missing volumes on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed missing volumes on <01-Jul-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed missing volumes on <01-Jul-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed missing volumes on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed missing volumes on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Dload" }, priority = 66, enabled=true)
	public void t067RaAnaApnWsMissTrendDload() throws Exception {
		log.info("Test Case - t067: Starting to test missing volumes trend in <4. RA-Analyst APN-wise Reports> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t067: Starting to test missing volumes trend in <4. RA-Analyst APN-wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst APN-wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.missTrndVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.missTrndVolMax().click();
		log.info("Clicked on Missing Volume Trend maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Missing Volume Trend");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Missing Volume Trend");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Missing Volume is successful");
		} else {
			log.debug("Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed DEP subscrier count on 28-Jun is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Missing Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"fourMisVolTrnd.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"fourMisVolTrnd.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"fourMisVolTrnd.xlsx", downloaddir+"fourMisVolTrnd.xlsx", 2, 0, 6, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Dload" }, priority = 67, enabled=true)
	public void t068RaAnaApnWsUndrDload() throws Exception {
		log.info("Test Case - t068: Starting to test under-reported volumes in <4.  RA-Analyst  APN-wise Reports> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t068: Starting to test under-reported volumes in <4.  RA-Analyst  APN-wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst  APN-wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.underRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.underRprtVolMax().click();
		log.info("Clicked on under-reported Volume maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of under-reported Volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of under-reported Volume");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of under-reported Volume matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of under-reported Volume is successful");
		} else {
			log.debug("Is time window of under-reported Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of under-reported Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of under-reported Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of under-reported Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			Thread.sleep(2000);
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"fourUndrVol.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"fourUndrVol.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"fourUndrVol.xlsx", downloaddir+"fourUndrVol.xlsx", 4, 0, 5, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Chart" }, priority = 68, enabled=true)
	public void t069RaAnaApnWsUndrChartWhole() throws Exception {
		log.info("Test Case - t069: Starting to test displayed under-reported volumes in <4. RA-Analyst APN-wise Reports> on whole duration");
		test.log(LogStatus.INFO, "Test Case - t069: Starting to test under-reported missing volumes in <4. RA-Analyst APN-wise Reports> on whole duration");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst APN-wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.underRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.underRprtVolMax().click();
		Thread.sleep(5000);
		log.info("Clicked on under-reported Volume maximize button");
		log.debug("Test step-1: Starting to test displayed under-reported volumes on whole duration");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed under-reported volumes on whole duration");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.debug("Loading complete");
			Thread.sleep(1000);
		}
		try {
			screen.click(four.misUndrOvrpatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(four.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(109).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed data matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed under-reported volumes on whole duration is successful");
	    } else {
	    	log.info("Displayed data matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed data matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed under-reported volumes on whole duration is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed under-reported volumes on whole duration is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Chart" }, priority = 69, enabled=true)
	public void t070RaAnaApnWsUndrChart29() throws Exception {
		log.info("Test Case - t070: Starting to test displayed under-reported volumes in <4. RA-Analyst APN-wise Reports> on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test Case - t070: Starting to test under-reported missing volumes in <4. RA-Analyst APN-wise Reports> on <29-Jun-2016>");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst APN-wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.underRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.underRprtVolMax().click();
		Thread.sleep(5000);
		log.info("Clicked on under-reported Volume maximize button");
		log.debug("Test step-1: Starting to test displayed under-reported volumes on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed under-reported volumes on <29-Jun-2016>");
		ccc.setDateTimeBar29();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.debug("Loading complete");
			Thread.sleep(1000);
		}
		try {
			screen.click(four.misUndrOvrpatternWhole());
			log.info("Clicked on screenpattern");
		} catch (Exception e) {
			log.trace(e);
			log.debug("Something went wrong while tried to clicked on screenpattern");
		}
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(four.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(111).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed under-reported volumes on <29-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed under-reported volumes on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed under-reported volumes on <29-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed under-reported volumes on <29-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed under-reported volumes on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed under-reported volumes on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Chart" }, priority = 70, enabled=true)
	public void t071RaAnaApnWsUndrChart30() throws Exception {
		log.info("Test Case - t071: Starting to test displayed under-reported volumes in <4. RA-Analyst APN-wise Reports> on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test Case - t071: Starting to test under-reported missing volumes in <4. RA-Analyst APN-wise Reports> on <30-Jun-2016>");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst APN-wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.underRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.underRprtVolMax().click();
		Thread.sleep(5000);
		log.info("Clicked on under-reported Volume maximize button");
		log.debug("Test step-1: Starting to test displayed under-reported volumes on <30-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed under-reported volumes on <30-Jun-2016>");
		ccc.setDateTimeBar30();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.debug("Loading complete");
			Thread.sleep(1000);
		}
		try {
			screen.click(four.misUndrOvrpatternWhole());
			log.info("Clicked on screenpattern");
		} catch (Exception e) {
			log.trace(e);
			log.debug("Something went wrong while tried to clicked on screenpattern");
		}
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(four.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(112).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed under-reported volumes on <30-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed under-reported volumes on <30-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed under-reported volumes on <30-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed under-reported volumes on <30-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed under-reported volumes on <30-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed under-reported volumes on <30-Jun-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Chart" }, priority = 71, enabled=true)
	public void t072RaAnaApnWsUndrChart01() throws Exception {
		log.info("Test Case - t072: Starting to test displayed under-reported volumes in <4. RA-Analyst APN-wise Reports> on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test Case - t072: Starting to test under-reported missing volumes in <4. RA-Analyst APN-wise Reports> on <01-Jul-2016>");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst APN-wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.underRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.underRprtVolMax().click();
		Thread.sleep(5000);
		log.info("Clicked on under-reported Volume maximize button");
		log.debug("Test step-1: Starting to test displayed under-reported volumes on <01-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed under-reported volumes on <01-Jul-2016>");
		ccc.setDateTimeBar01();
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.debug("Loading complete");
			Thread.sleep(1000);
		}
		try {
			screen.click(four.misUndrOvrpatternWhole());
			log.info("Clicked on screenpattern");
		} catch (Exception e) {
			log.trace(e);
			log.debug("Something went wrong while tried to clicked on screenpattern");
		}
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(1000);
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(four.valContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(113).getCell(6).getNumericCellValue();
		refData = refData.floatValue();
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed under-reported volumes on <01-Jul-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed under-reported volumes on <01-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed under-reported volumes on <01-Jul-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed under-reported volumes on <01-Jul-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed under-reported volumes on <01-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed under-reported volumes on <01-Jul-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Dload" }, priority = 72, enabled=true)
	public void t073RaAnaApnWsUndrTrendDload() throws Exception {
		log.info("Test Case - t073: Starting to test under-reported volumes trend in <4. RA-Analyst APN-wise Reports> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t073: Starting to test under-reported volumes trend in <4. RA-Analyst APN-wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst  APN-wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.underTrndVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.underTrndVolMax().click();
		log.info("Clicked on under-reported Volume Trend maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of under-reported Volume Trend");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of under-reported Volume Trend");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of under-reported Volume is successful");
		} else {
			log.debug("Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of under-reported Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of under-reported Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"fourUndrVolTrnd.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"fourUndrVolTrnd.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"fourUndrVolTrnd.xlsx", downloaddir+"fourUndrVolTrnd.xlsx", 4, 0, 7, 3);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Dload" }, priority = 73, enabled=true)
	public void t074RaAnaApnWsOvrDload() throws Exception {
		log.info("Test Case - t074: Starting to test over-reported volumes in <4. RA-Analyst APN-wise Reports> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t074: Starting to test over-reported volumes in <4. RA-Analyst APN-wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst  APN-wise Reports>");
		Thread.sleep(10000);
		screen.click(four.misUndrOvrpatternScrlDn());
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.overRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.overRprtVolMax().click();
		log.info("Clicked on over-reported Volume maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of over-reported Volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of over-reported Volume");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of over-reported Volume matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of under-reported Volume is successful");
		} else {
			log.debug("Is time window of over-reported Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of over-reported Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of over-reported Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of over-reported Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			Thread.sleep(2000);
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"fourOvrVol.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"fourOvrVol.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"fourOvrVol.xlsx", downloaddir+"fourOvrVol.xlsx", 4, 0, 5, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Chart" }, priority = 74, enabled=true)
	public void t075RaAnaApnWsOvrChartWhole() throws Exception {
		log.info("Test Case - t075: Starting to test displayed over-reported volumes in <4. RA-Analyst APN-wise Reports> on whole duration");
		test.log(LogStatus.INFO, "Test Case - t075: Starting to test over-reported volumes in <4. RA-Analyst APN-wise Reports> on whole duration");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst  APN-wise Reports>");
		Thread.sleep(10000);
		screen.click(four.misUndrOvrpatternScrlDn());
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.overRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.overRprtVolMax().click();
		Thread.sleep(5000);
		log.info("Clicked on over-reported Volume maximize button");
		log.debug("Test step-1: Starting to test displayed over-reported volumes on whole duration");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed over-reported volumes on whole duration");
		if (ccc.verifyTimeWindow(reftimewindow) == false) {
			ccc.setDateTimeBar();
		}
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.debug("Loading complete");
			Thread.sleep(1000);
		}
		try {
			screen.click(four.misUndrOvrpatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(four.overValContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(109).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		log.debug("converted to Float Reference data: " + refData); //-------------------------------------------------->
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed over-reported volumes in <4.  RA-Analyst  APN-wise Reports> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Testing of displayed over-reported volumes in <4.  RA-Analyst  APN-wise Reports> is successful");
	    } else {
	    	log.info("Displayed over-reported volumes in <4.  RA-Analyst  APN-wise Reports> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed over-reported volumes in <4.  RA-Analyst  APN-wise Reports> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed over-reported volumes in <4.  RA-Analyst  APN-wise Reports> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed over-reported volumes in <4.  RA-Analyst  APN-wise Reports> is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Chart" }, priority = 75, enabled=true)
	public void t076RaAnaApnWsOvrChart29() throws Exception {
		log.info("Test Case - t076: Starting to test displayed over-reported volumes in <4. RA-Analyst APN-wise Reports> on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test Case - t076: Starting to test over-reported volumes in <4. RA-Analyst APN-wise Reports> on <29-Jun-2016>");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst APN-wise Reports>");
		Thread.sleep(10000);
		screen.click(four.misUndrOvrpatternScrlDn());
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.overRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.overRprtVolMax().click();
		Thread.sleep(5000);
		log.info("Clicked on over-reported Volume maximize button");
		ccc.setDateTimeBar29();
		log.debug("Date is set to 29-Jun-2016");
		log.debug("Test step-1: Starting to test displayed over-reported volumes on <29-Jun-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed over-reported volumes on <29-Jun-2016>");
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.debug("Loading complete");
			Thread.sleep(1000);
		}
		try {
			screen.click(four.misUndrOvrpatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(four.overValContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(111).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		log.debug("converted to Float Reference data: " + refData); //-------------------------------------------------->
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed over-reported volumes on <29-Jun-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed displayed over-reported volumes on <29-Jun-2016> is successful");
	    } else {
	    	log.info("Displayed over-reported volumes on <29-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed over-reported volumes on <29-Jun-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed displayed over-reported volumes on <29-Jun-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed displayed over-reported volumes on <29-Jun-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Chart" }, priority = 76, enabled=true)
	public void t077RaAnaApnWsOvrChart02() throws Exception {
		log.info("Test Case - t077: Starting to test displayed over-reported volumes in <4. RA-Analyst APN-wise Reports> on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test Case - t077: Starting to test displayed over-reported volumes in <4. RA-Analyst APN-wise Reports> on <02-Jul-2016>");
		ccc.FourRaAApnR().click();
		Thread.sleep(10000);
		screen.click(four.misUndrOvrpatternScrlDn());
		log.info("Clicked on <4. RA-Analyst APN-wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.overRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.overRprtVolMax().click();
		Thread.sleep(5000);
		log.info("Clicked on over-reported Volume maximize button");
		ccc.setDateTimeBar02();
		log.debug("Date is set to 02-Jul-2016");
		log.debug("Test step-1: Starting to test displayed over-reported volumes on <02-Jul-2016>");
		test.log(LogStatus.INFO, "Test step-1: Starting to test displayed over-reported volumes on <02-Jul-2016>");
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
		} catch (Exception e) {
			log.debug("Loading complete");
			Thread.sleep(1000);
		}
		try {
			screen.click(four.misUndrOvrpatternWhole());
			log.info("Clicked on screenpattern");
	    } catch (Exception e) {
	    	log.trace(e);
	    	log.debug("Something went wrong while tried to clicked on screenpattern");
	    }
		Thread.sleep(1000);
		one.detailsButn().click();
		log.info("Clicked on details button");
		Thread.sleep(2000);
		try {
			displayedData = NumberFormat.getNumberInstance(java.util.Locale.US).parse(four.overValContainer().getText()).floatValue();
			log.debug("converted to Float displayed data: " + displayedData);
		} catch (Exception e) {
			log.error(e);
			log.error("displayed data couldn't be converted to float");
		}
		one.closeDetailsButn().click();
		refData = ExcelWSheetoneraad.getRow(114).getCell(10).getNumericCellValue();
		refData = refData.floatValue();
		log.debug("converted to Float Reference data: " + refData); //-------------------------------------------------->
		istrue1 = ccc.equalityOfTwoNumbers(refData,displayedData);
		if (istrue1) {
			log.info("Displayed over-reported volumes on <02-Jul-2016> matched with the expected results? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of displayed over-reported volumes on <02-Jul-2016> is successful");
	    } else {
	    	log.info("Displayed over-reported volumes on <02-Jul-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	test.log(LogStatus.WARNING, "Test step-1: Displayed over-reported volumes on <02-Jul-2016> matched with the expected results? - " + istrue1 + ". taking screenshot ... ");
	    	String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of displayed over-reported volumes on <02-Jul-2016> is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of displayed over-reported volumes on <02-Jul-2016> is Failed\r\n", imagepath);
	    }
		istrue = istrue1;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaAnaApnWsRprt", "Dload" }, priority = 77, enabled=true)
	public void t078RaAnaApnWsOvrTrendDload() throws Exception {
		log.info("Test Case - t078: Starting to test over-reported volumes trend in <4. RA-Analyst APN-wise Reports> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t078: Starting to test over-reported volumes trend in <4. RA-Analyst APN-wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.FourRaAApnR().click();
		log.info("Clicked on <4. RA-Analyst  APN-wise Reports>");
		Thread.sleep(10000);
		screen.click(four.misUndrOvrpatternScrlDn());
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(four.overTrndVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		four.overTrndVolMax().click();
		log.info("Clicked on over-reported Volume Trend maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of over-reported Volume Trend");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of over-reported Volume Trend");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of over-reported Volume is successful");
		} else {
			log.debug("Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of over-reported Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of over-reported Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"fourOvrVolTrnd.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"fourOvrVolTrnd.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"fourOvrVolTrnd.xlsx", downloaddir+"fourOvrVolTrnd.xlsx", 4, 0, 6, 3);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		Thread.sleep(3000);
		softAssert.assertTrue(istrue1 && istrue2);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaApPrSubWsRprt", "Dload" }, priority = 78, enabled=true)
	public void t079RaAppWsDload() throws Exception {
		log.info("Test Case - t079: Starting to test application-wise missing volumes in <5a. RA - App/Protocol/sub-protocol wise Reports > by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t079: Starting to test application-wise missing volumes in <5a. RA - App/Protocol/sub-protocol wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.Five1RaAPSR().click();
		log.info("Clicked on <5a. RA - App/Protocol/sub-protocol wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(fivea.missRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		fivea.missRprtVolMax().click();
		log.info("Clicked on missing Volume maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of application-wise missing Volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of application-wise missingVolume");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of protocol-wise missing Volume matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of application-wise missing Volume is successful");
		} else {
			log.debug("Is time window of protocol-wise missing Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of protocol-wise missing Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of protocol-wise missing Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of protocol-wise missing Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"five1AppVol.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"five1AppVol.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"five1AppVol.xlsx", 2, 11, 0, 3, 12, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaApPrSubWsRprt", "Dload" }, priority = 79, enabled=true)
	public void t080RaAppWsTrndDload() throws Exception {
		log.info("Test Case - t080: Starting to test application-wise missing volumes trend in <5a. RA - App/Protocol/sub-protocol wise Reports > by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t080: Starting to test application-wise missing volumes trend in <5a. RA - App/Protocol/sub-protocol wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.Five1RaAPSR().click();
		log.info("Clicked on <5a. RA - App/Protocol/sub-protocol wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(fivea.missRprtTrndMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		fivea.missRprtTrndMax().click();
		log.info("Clicked on missing Volume Trend maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of application-wise missing Volume trend ");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of application-wise missing Volume trend");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of protocol-wise missing Volume trend matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of application-wise missing Volume trend is successful");
		} else {
			log.debug("Is time window of protocol-wise missing Volume trend matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time of protocol-wise missing Volume trend window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of protocol-wise missing Volume trend is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of protocol-wise missing Volume trend is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"five1AppTrnd.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"five1AppTrnd.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"five1AppTrnd.xlsx", downloaddir+"five1AppTrnd.xlsx", 2, 0, 31, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaApPrSubWsRprt", "Dload" }, priority = 80, enabled=true)
	public void t081RaPrtoWsDload() throws Exception {
		log.info("Test Case - t081: Starting to test protocol-wise missing volumes in <5a. RA - App/Protocol/sub-protocol wise Reports > by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t081: Starting to test protocol-wise missing volumes in <5a. RA - App/Protocol/sub-protocol wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.Five1RaAPSR().click();
		log.info("Clicked on <5a. RA - App/Protocol/sub-protocol wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(fivea.missRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		fivea.missRprtVolMax().click();
		log.info("Clicked on missing Volume Trend maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of protocol-wise missing Volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of protocol-wise missing Volume");
		fivea.missRprtGrpBtn().click();
		log.debug("Clicked on filter button");
		fivea.protoBtn().click();
		log.debug("Clicked on Protocol filter option to select it");
		Thread.sleep(3000);
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of protocol-wise missing Volume matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of protocol-wise missing Volume is successful");
		} else {
			log.debug("Is time window of protocol-wise missing Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of protocol-wise missing Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of protocol-wise missing Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of protocol-wise missing Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"five1PrtVol.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"five1PrtVol.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"five1PrtVol.xlsx", 16, 25, 0, 3, 12, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaApPrSubWsRprt", "Dload" }, priority = 81, enabled=true)
	public void t082RaPrtoWsTrndDload() throws Exception {
		log.info("Test Case - t082: Starting to test protocol-wise missing volumes trend in <5a. RA - App/Protocol/sub-protocol wise Reports > by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t082: Starting to test protocol-wise missing volumes trend in <5a. RA - App/Protocol/sub-protocol wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.Five1RaAPSR().click();
		log.info("Clicked on <5a. RA - App/Protocol/sub-protocol wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(fivea.missRprtTrndMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		fivea.missRprtTrndMax().click();
		log.info("Clicked on missing Volume Trend maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of protocol-wise missing Volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of protocol-wise missing Volume");
		fivea.missRprtGrpBtn().click();
		log.debug("Clicked on filter button");
		fivea.protoBtn().click();
		log.debug("Clicked on Protocol filter option to select it");
		Thread.sleep(3000);
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of protocol-wise missing Volume trend matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of protocol-wise missing Volume trend is successful");
		} else {
			log.debug("Is time window of protocol-wise missing Volume trend matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of protocol-wise missing Volume trend matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of protocol-wise missing Volume trend is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of protocol-wise missing Volume trend is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"five1PrtTrnd.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"five1PrtTrnd.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"five1PrtTrnd.xlsx", downloaddir+"five1PrtTrnd.xlsx", 2, 0, 34, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaApPrSubWsRprt", "Dload" }, priority = 82, enabled=true)
	public void t083RaSubPrtoWsDload() throws Exception {
		log.info("Test Case - t083: Starting to test sub_protocol-wise missing volumes in <5a. RA - App/Protocol/sub-protocol wise Reports > by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t083: Starting to test sub_protocol-wise missing volumes in <5a. RA - App/Protocol/sub-protocol wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.Five1RaAPSR().click();
		log.info("Clicked on <5a. RA - App/Protocol/sub-protocol wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(fivea.missRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		fivea.missRprtVolMax().click();
		log.info("Clicked on missing Volume Trend maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of sub_protocol-wise missing Volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of sub_protocol-wise missing Volume");
		fivea.missRprtGrpBtn().click();
		log.debug("Clicked on filter button");
		fivea.subProtoBtn().click();
		log.debug("Clicked on Protocol filter option to select it");
		Thread.sleep(3000);
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of sub_protocol-wise missing Volume matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time of sub_protocol-wise missing Volume window of sub_protocol-wise missing Volume is successful");
		} else {
			log.debug("Is time window of sub_protocol-wise missing Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of sub_protocol-wise missing Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of sub_protocol-wise missing Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of sub_protocol-wise missing Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"five1SbPrVol.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"five1SbPrVol.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"five1SbPrVol.xlsx", 29, 38, 0, 3, 12, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaApPrSubWsRprt", "Dload" }, priority = 83, enabled=true)
	public void t084RaSubPrtoWsTrndDload() throws Exception {
		log.info("Test Case - t084: Starting to test sub_protocol-wise missing volumes trend in <5a. RA - App/Protocol/sub-protocol wise Reports > by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t084: Starting to test sub_protocol-wise missing volumes trend in <5a. RA - App/Protocol/sub-protocol wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.Five1RaAPSR().click();
		log.info("Clicked on <5a. RA - App/Protocol/sub-protocol wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(fivea.missRprtTrndMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		fivea.missRprtTrndMax().click();
		log.info("Clicked on missing Volume Trend maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of sub_protocol-wise missing Volume trend");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of sub_protocol-wise missing Volume trend");
		fivea.missRprtGrpBtn().click();
		log.debug("Clicked on filter button");
		fivea.subProtoBtn().click();
		log.debug("Clicked on Protocol filter option to select it");
		Thread.sleep(3000);
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of sub_protocol-wise missing Volume trend matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of sub_protocol-wise missing Volume trend is successful");
		} else {
			log.debug("Is time window of sub_protocol-wise missing Volume trend matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of sub_protocol-wise missing Volume trend matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of sub_protocol-wise missing Volume trend is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of sub_protocol-wise missing Volume trend is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"five1SbPrTrnd.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"five1SbPrTrnd.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"five1SbPrTrnd.xlsx", downloaddir+"five1SbPrTrnd.xlsx", 2, 0, 32, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaUrlWsRprt", "Dload" }, priority = 84, enabled=true)
	public void t085RaUrlWsDload() throws Exception {
		log.info("Test Case - t085: Starting to test URL-wise missing volumes in <5b. RA - Domain / URL wise Reports> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t085: Starting to test URL-wise missing volumes in <5b. RA - Domain / URL wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.Five2RaDUR().click();
		log.info("Clicked on <5b. RA - Domain / URL wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(fiveb.missRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		fiveb.missRprtVolMax().click();
		log.info("Clicked on missing Volume maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of URL-wise missing Volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of URL-wise missingVolume");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of URL-wise missing Volume matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of URL-wise missing Volume is successful");
		} else {
			log.debug("Is time window of URL-wise missing Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of URL-wise missing Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of URL-wise missing Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of URL-wise missing Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"five2UrlVol.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"five2UrlVol.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"five2UrlVol.xlsx", 42, 51, 0, 3, 12, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaUrlWsRprt", "Dload" }, priority = 85, enabled=true)
	public void t086RaUrlWsTrndDload() throws Exception {
		log.info("Test Case - t086: Starting to test URL-wise missing volumes trend in <5b. RA - Domain / URL wise Reports> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t086: Starting to test URL-wise missing volumes trend in <5b. RA - Domain / URL wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.Five2RaDUR().click();
		log.info("Clicked on <5b. RA - Domain / URL wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(fiveb.missRprtTrndMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		fiveb.missRprtTrndMax().click();
		log.info("Clicked on missing Volume trend maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of URL-wise missing Volume trend");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of URL-wise missing Volume trend");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of URL-wise missing Volume trend matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of URL-wise missing Volume trend is successful");
		} else {
			log.debug("Is time window of URL-wise missing Volume trend matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of URL-wise missing Volume trend matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of URL-wise missing Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of URL-wise missing Volume trend is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"five2UrlTrnd.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"five2UrlTrnd.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelFiles(refdir+"five2UrlTrnd.xlsx", downloaddir+"five2UrlTrnd.xlsx", 2, 0, 24, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaPrxyWsRprt", "Dload" }, priority = 86, enabled=true)
	public void t087RaPrxyWsDload() throws Exception {
		log.info("Test Case - t087: Starting to test Proxy-wise missing volumes in <5c. RA - Proxy-IP wise Reports> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t087: Starting to test Proxy-wise missing volumes in <5c. RA - Proxy-IP wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.Five3RaPIpR().click();
		log.info("Clicked on <5c. RA - Proxy-IP wise Reports>");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(fivec.missRprtVolMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		fivec.missRprtVolMax().click();
		log.info("Clicked on missing Volume maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Proxy-wise missing Volume");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Proxy-wise missingVolume");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of Proxy-wise missing Volume matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Proxy-wise missing Volume is successful");
		} else {
			log.debug("Is time window of Proxy-wise missing Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Proxy-wise missing Volume matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Proxy-wise missing Volume is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Proxy-wise missing Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"five3PrxyVol.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"five3PrxyVol.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"five3PrxyVol.xlsx", 55, 59, 0, 3, 7, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaPrxyWsRprt", "Dload" }, priority = 87, enabled=true)
	public void t088RaPrxyWsTrndDload() throws Exception {
		log.info("Test Case - t088: Starting to test Proxy-wise missing volumes trend in <5c.  RA - Proxy-IP wise Reports> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t088: Starting to test Proxy-wise missing volumes trend in <5c.  RA - Proxy-IP wise Reports> by downloading the Excel report and comparing it with the reference report");
		ccc.Five3RaPIpR().click();
		log.info("Clicked on <5c.  RA - Proxy-IP wise Reports>");
		Thread.sleep(5000);
		fivec.missRprtTrndMax().click();
		log.info("Clicked on missing Volume trend maximize button");
		Thread.sleep(3000);
		log.debug("Test step-1: Starting to test the time window of Proxy-wise missing Volume trend");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Proxy-wise missing Volume trend");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Proxy-wise missing Volume trend is successful");
		} else {
			log.debug("Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Proxy-wise missing trend Volume is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"five3PrxyTrnd.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(5000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(5000);
		ccc.closeExportFiles().click();
		Thread.sleep(5000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"five3PrxyTrnd.xlsx");
		Thread.sleep(10000);
		istrue2 = ccc.compareExcelFiles(refdir+"five3PrxyTrnd.xlsx", downloaddir+"five3PrxyTrnd.xlsx", 2, 0, 12, 2);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.FAIL, "Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		Thread.sleep(3000);
		softAssert.assertTrue(istrue1 && istrue2);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaGapProf", "Dload" }, priority = 88, enabled=true)
	public void t089RaGapProf1aDload() throws Exception {
		log.info("Test Case - t089: Starting to test APN-wise gap profiling in <7a.  GAP Profiling (1 of 6)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t089: Starting to test APN-wise gap profiling in <7a.  GAP Profiling (1 of 6)> by downloading the Excel report and comparing it with the reference report");
		ccc.Seven1GapPro().click();
		log.info("Clicked on <7a.  GAP Profiling (1 of 6)");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(seven.apnGapProfMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		seven.apnGapProfMax().click();
		log.info("Clicked on APN-wise gap profiling maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of APN-wise gap profiling");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of APN-wise gap profiling");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of APN-wise gap profiling matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Proxy-wise missing Volume is successful");
		} else {
			log.debug("Is time window of APN-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of APN-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of APN-wise gap profiling is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of APN-wise gap profiling is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"sevn1Apn.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(1000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"sevn1Apn.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"sevn1Apn.xlsx", 64, 64, 0, 3, 3, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaGapProf", "Dload" }, priority = 89, enabled=true)
	public void t090RaGapProf1bDload() throws Exception {
		log.info("Test Case - t090: Starting to test Proxy IP-wise gap profiling in <7a.  GAP Profiling (1 of 6)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t090: Starting to test Proxy IP-wise gap profiling in <7a.  GAP Profiling (1 of 6)> by downloading the Excel report and comparing it with the reference report");
		ccc.Seven1GapPro().click();
		log.info("Clicked on <7a.  GAP Profiling (1 of 6)");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(seven.prxyIpGapProfMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		seven.prxyIpGapProfMax().click();
		log.info("Clicked on Proxy IP-wise gap profiling maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Proxy IP-wise gap profiling");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Proxy IP-wise gap profiling");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of Proxy IP-wise gap profiling matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Proxy IP-wise missing Volume is successful");
		} else {
			log.debug("Is time window of Proxy IP-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Proxy IP-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Proxy IP-wise gap profiling is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Proxy IP-wise gap profiling is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"sevn1Prxy.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"sevn1Prxy.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"sevn1Prxy.xlsx", 68, 77, 0, 3, 12, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaGapProf", "Dload" }, priority = 90, enabled=true)
	public void t091RaGapProf2aDload() throws Exception {
		log.info("Test Case - t091: Starting to test URL-wise gap profiling in <7b.  GAP Profiling (2 of 6)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t091: Starting to test URL-wise gap profiling in <7b.  GAP Profiling (2 of 6)> by downloading the Excel report and comparing it with the reference report");
		ccc.Seven2GapPro().click();
		log.info("Clicked on <7b.  GAP Profiling (2 of 6)");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(seven.urlGapProfMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		seven.urlGapProfMax().click();
		log.info("Clicked on URL-wise gap profiling maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of URL-wise gap profiling");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of URL-wise gap profiling");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of URL-wise gap profiling matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of URL-wise missing Volume is successful");
		} else {
			log.debug("Is time window of URL-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of URL-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of URL-wise gap profiling is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of URL-wise gap profiling is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"sevn2Url.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"sevn2Url.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"sevn2Url.xlsx", 82, 1531, 0, 3, 12, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaGapProf", "Dload" }, priority = 91, enabled=true)
	public void t092RaGapProf2bSubPrDload() throws Exception {
		log.info("Test Case - t092: Starting to test Sub_Protocol-wise gap profiling in <7b. GAP Profiling (2 of 6)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t092: Starting to test Sub_Protocol-wise gap profiling in <7b.  GAP Profiling (2 of 6)> by downloading the Excel report and comparing it with the reference report");
		ccc.Seven2GapPro().click();
		log.info("Clicked on <7b.  GAP Profiling (2 of 6)");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(seven.prtoGapProfMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		seven.prtoGapProfMax().click();
		log.info("Clicked on Sub_Protocol-wise gap profiling maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Sub_Protocol-wise gap profiling");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Sub_Protocol-wise gap profiling");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of Sub_Protocol-wise gap profiling matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Sub_Protocol-wise missing Volume is successful");
		} else {
			log.debug("Is time window of Sub_Protocol-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Sub_Protocol-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Sub_Protocol-wise gap profiling is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Sub_Protocol-wise gap profiling is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"sevn2Spro.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"sevn2Spro.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"sevn2Spro.xlsx", 1549, 1558, 0, 3, 12, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaGapProf", "Dload" }, priority = 92, enabled=true)
	public void t093RaGapProf2bProtDload() throws Exception {
		log.info("Test Case - t093: Starting to test Protocol-wise gap profiling in <7b. GAP Profiling (2 of 6)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t093: Starting to test Protocol-wise gap profiling in <7b.  GAP Profiling (2 of 6)> by downloading the Excel report and comparing it with the reference report");
		ccc.Seven2GapPro().click();
		log.info("Clicked on <7b. GAP Profiling (2 of 6)");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(seven.prtoGapProfMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		seven.prtoGapProfMax().click();
		log.info("Clicked on Protocol-wise gap profiling maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Protocol-wise gap profiling");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Protocol-wise gap profiling");
		try {
			log.debug("trying to click on filter options button");
			seven.gapProfGrpBtn().click();
			log.debug("Clicked on filter option button");
		} catch (Exception e) {
			log.debug("something went wrong while trying to click on the filter optios");
			log.debug(e);
		}
		Thread.sleep(3000);
		seven.protoBtn().click();
		log.debug("Clicked on Protocol filter option button to select it");
		Thread.sleep(5000);
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of Protocol-wise gap profiling matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Protocol-wise missing Volume is successful");
		} else {
			log.debug("Is time window of Protocol-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Protocol-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Protocol-wise gap profiling is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"sevn2Pro.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"sevn2Pro.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"sevn2Pro.xlsx", 1536, 1545, 0, 3, 12, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaGapProf", "Dload" }, priority = 93, enabled=true)
	public void t094RaGapProf2cAppDload() throws Exception {
		log.info("Test Case - t094: Starting to test Application-wise gap profiling in <7b. GAP Profiling (2 of 6)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t094: Starting to test Application-wise gap profiling in <7b. GAP Profiling (2 of 6)> by downloading the Excel report and comparing it with the reference report");
		ccc.Seven2GapPro().click();
		log.info("Clicked on <7b. GAP Profiling (2 of 6)");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(seven.prtoGapProfMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		seven.prtoGapProfMax().click();
		log.info("Clicked on Application-wise gap profiling maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Application-wise gap profiling");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Application-wise gap profiling");
		try {
			log.debug("trying to click on filter options button");
			seven.gapProfGrpBtn().click();
			log.debug("Clicked on filter option button");
		} catch (Exception e) {
			log.debug("something went wrong while trying to click on the filter optios");
			log.debug(e);
		}
		seven.appBtn().click();
		log.debug("Clicked on Application filter option button to select it");
		Thread.sleep(3000);
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of Application-wise gap profiling matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Application-wise missing Volume is successful");
		} else {
			log.debug("Is time window of Application-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Application-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Application-wise gap profiling is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Application-wise gap profiling is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"sevn2App.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"sevn2App.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"sevn2App.xlsx", 1563, 1582, 0, 3, 12, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaGapProf", "Dload" }, priority = 94, enabled=true)
	public void t095RaGapProf3aDload() throws Exception {
		log.info("Test Case - t095: Starting to test RAT type-wise gap profiling in <7c.  GAP Profiling (3 of 6)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t095: Starting to test RAT type-wise gap profiling in <7c.  GAP Profiling (3 of 6)> by downloading the Excel report and comparing it with the reference report");
		ccc.Seven3GapPro().click();
		log.info("Clicked on <7c.  GAP Profiling (3 of 6)");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(seven.ratGapProfMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		seven.ratGapProfMax().click();
		log.info("Clicked on RAT type-wise gap profiling maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of RAT type-wise gap profiling");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of RAT type-wise gap profiling");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of RAT type-wise gap profiling matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of RAT type-wise missing Volume is successful");
		} else {
			log.debug("Is time window of RAT type-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of RAT type-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of RAT type-wise gap profiling is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of RAT type-wise gap profiling is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"sevn3rat.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"sevn3rat.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"sevn3rat.xlsx", 1587, 1588, 0, 3, 4, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaGapProf", "Dload" }, priority = 95, enabled=true)
	public void t096RaGapProf3bDload() throws Exception {
		log.info("Test Case - t096: Starting to test Destination Server IP-wise gap profiling in <7c. GAP Profiling (3 of 6)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t096: Starting to test Destination Server IP-wise gap profiling in <7c. GAP Profiling (3 of 6)> by downloading the Excel report and comparing it with the reference report");
		ccc.Seven3GapPro().click();
		log.info("Clicked on <7c. GAP Profiling (3 of 6)");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(seven.destSrvrGapProfMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		seven.destSrvrGapProfMax().click();
		log.info("Clicked on  Destination Server IP-wise gap profiling maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Destination Server IP-wise gap profiling");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Destination Server IP-wise gap profiling");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of Destination Server IP-wise gap profiling matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Destination Server IP-wise missing Volume is successful");
		} else {
			log.debug("Is time window of Destination Server IP-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Destination Server IP-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Destination Server IP-wise gap profiling is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Destination Server IP-wise gap profiling is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"sevn3DSrv.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"sevn3DSrv.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"sevn3DSrv.xlsx", 1593, 3952, 0, 3, 12, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaGapProf", "Dload" }, priority = 96, enabled=true)
	public void t097RaGapProf4aDload() throws Exception {
		log.info("Test Case - t097: Starting to test TAC/UE Model-wise gap profiling in <7d.  GAP Profiling (4 of 6)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t097: Starting to test TAC/UE Model-wise gap profiling in <7d.  GAP Profiling (4 of 6)> by downloading the Excel report and comparing it with the reference report");
		ccc.Seven4GapPro().click();
		log.info("Clicked on <7d.  GAP Profiling (4 of 6)");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(seven.tacGapProfMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		seven.tacGapProfMax().click();
		log.info("Clicked on TAC/UE Model-wise gap profiling maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of TAC/UE Model-wise gap profiling");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of TAC/UE Model-wise gap profiling");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window TAC/UE Model-wise gap profiling matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Proxy-wise missing Volume is successful");
		} else {
			log.debug("Is time window TAC/UE Model-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window TAC/UE Model-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of TAC/UE Model-wise gap profiling is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of TAC/UE Model-wise gap profiling is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"seven4Tac.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"seven4Tac.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"seven4Tac.xlsx", 3957, 3977, 0, 3, 12, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaGapProf", "Dload" }, priority = 97, enabled=true)
	public void t098RaGapProf4bDload() throws Exception {
		log.info("Test Case - t098: Starting to test proxy domain name-wise gap profiling in <7d.  GAP Profiling (4 of 6)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t098: Starting to test proxy domain name-wise gap profiling in <7d.  GAP Profiling (4 of 6)> by downloading the Excel report and comparing it with the reference report");
		ccc.Seven4GapPro().click();
		log.info("Clicked on <7d.  GAP Profiling (4 of 6)");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(one.depVolMaxButn()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		seven.prxyDmnGapProfMax().click();
		log.info("Clicked on proxy domain name-wise gap profiling maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of proxy domain name-wise gap profiling");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of proxy domain name-wise gap profiling");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window proxy domain name-wise gap profiling matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of proxy domain name-wise missing Volume is successful");
		} else {
			log.debug("Is time window proxy domain name-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window proxy domain name-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of proxy domain name-wise gap profiling is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of proxy domain name-wise gap profiling is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"seven4Prxy.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"seven4Prxy.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"seven4Prxy.xlsx", 3984, 4867, 0, 3, 12, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaGapProf", "Dload" }, priority = 98, enabled=true)
	public void t099RaGapProf5aDload() throws Exception {
		log.info("Test Case - t099: Starting to test LAC/Cell ID-wise gap profiling in <7e.  GAP Profiling (5 of 6)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t099: Starting to test LAC/Cell ID-wise gap profiling in <7e.  GAP Profiling (5 of 6)> by downloading the Excel report and comparing it with the reference report");
		ccc.Seven5GapPro().click();
		log.info("Clicked on <7e.  GAP Profiling (5 of 6)");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(seven.lacGapProfMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		seven.lacGapProfMax().click();
		log.info("Clicked on APN-wise gap profiling maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of LAC/Cell ID-wise gap profiling");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of LAC/Cell ID-wise gap profiling");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window LAC/Cell ID-wise gap profiling matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of LAC/Cell ID-wise missing Volume is successful");
		} else {
			log.debug("Is time window LAC/Cell ID-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window LAC/Cell ID-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of LAC/Cell ID-wise gap profiling is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of LAC/Cell ID-wise gap profiling is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"seven5Lac.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"seven5Lac.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"seven5Lac.xlsx", 4872, 5288, 0, 3, 12, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaGapProf", "Dload" }, priority = 99, enabled=true)
	public void t100RaGapProf5bDload() throws Exception {
		log.info("Test Case - t100: Starting to test VPLMN-wise gap profiling in <7e.  GAP Profiling (5 of 6)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t190: Starting to test VPLMN-wise gap profiling in <7e.  GAP Profiling (5 of 6)> by downloading the Excel report and comparing it with the reference report");
		ccc.Seven5GapPro().click();
		log.info("Clicked on <7e.  GAP Profiling (5 of 6)");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(seven.vplmnGapProfMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		seven.vplmnGapProfMax().click();
		log.info("Clicked on APN-wise gap profiling maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of VPLMN-wise gap profiling");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of VPLMN-wise gap profiling");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window of VPLMN-wise gap profiling matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of VPLMN-wise missing Volume is successful");
		} else {
			log.debug("Is time window of VPLMN-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of VPLMN-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of VPLMN-wise gap profiling is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of VPLMN-wise gap profiling is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"seven5Vpm.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"seven5Vpm.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"seven5Vpm.xlsx", 5293, 5293, 0, 3, 3, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaGapProf", "Dload" }, priority = 100, enabled=true)
	public void t101RaGapProf6aDload() throws Exception {
		log.info("Test Case - t101: Starting to test Site-wise gap profiling in <7f.  GAP Profiling (6 of 6)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t101: Starting to test Site-wise gap profiling in <7f.  GAP Profiling (6 of 6)> by downloading the Excel report and comparing it with the reference report");
		ccc.Seven6GapPro().click();
		log.info("Clicked on <7f.  GAP Profiling (6 of 6)");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(seven.siteGapProfMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		seven.siteGapProfMax().click();
		log.info("Clicked on Site-wise gap profiling maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Site-wise gap profiling");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Site-wise gap profiling");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time window Site-wise gap profiling matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Site-wise missing Volume is successful");
		} else {
			log.debug("Is time window Site-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window Site-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Site-wise gap profiling is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Site-wise gap profiling is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"seven6Site.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"seven6Site.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"seven6Site.xlsx", 5298, 5298, 0, 3, 3, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	@Test(groups = { "all_tests", "RaGapProf", "Dload" }, priority = 101, enabled=true)
	public void t102RaGapProf6bDload() throws Exception {
		log.info("Test Case - t102: Starting to test Revenue Stream-wise gap profiling in <7f. GAP Profiling (6 of 6)> by downloading the Excel report and comparing it with the reference report");
		test.log(LogStatus.INFO, "Test Case - t102: Starting to test Revenue Stream-wise gap profiling in <7f. GAP Profiling (6 of 6)> by downloading the Excel report and comparing it with the reference report");
		ccc.Seven6GapPro().click();
		log.info("Clicked on <7f. GAP Profiling (6 of 6)");
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOf(seven.revStrmGapProfMax()));
		} catch (Exception e) {
			log.trace(e);
			Thread.sleep(1000);
		}
		seven.revStrmGapProfMax().click();
		log.info("Clicked on Revenue Stream-wise gap profiling maximize button");
		Thread.sleep(5000);
		log.debug("Test step-1: Starting to test the time window of Revenue Stream-wise gap profiling");
		test.log(LogStatus.INFO, "Test step-1: Starting to test the time window of Revenue Stream-wise gap profiling");
		istrue1 = ccc.verifyTimeWindow(reftimewindow);
		if (istrue1) {
			log.debug("Is time of Revenue Stream-wise gap profiling window matching with the expected result? - " + istrue1);
			test.log(LogStatus.PASS, "Test step-1: Testing of the time window of Revenue Stream-wise missing Volume is successful");
		} else {
			log.debug("Is time window of Revenue Stream-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-1: Is time window of Revenue Stream-wise gap profiling matching with the expected result? - " + istrue1 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-1: Testing of the time window of Revenue Stream-wise gap profiling is Failed");
	    	test.log(LogStatus.FAIL, "Test step-1: Testing of the time window of Revenue Stream-wise gap profiling is Failed\r\n", imagepath);
	    	Thread.sleep(2000);
	    	log.debug("Setting time window correctly now ....");
	    	test.log(LogStatus.INFO, "Setting time window correctly now ....");
	    	ccc.setDateTimeBar();
	    	log.debug("Time window is set to correctly");
			test.log(LogStatus.INFO, "Time window is set to correctly");
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.invisibilityOf(ccc.readIncomplete()));
			} catch (Exception e) {
				log.trace(e);
				Thread.sleep(1000);
			}
		}
		
		log.debug("Test step-2: Starting to test downloaded Excel file");
		test.log(LogStatus.INFO, "Test step-2: Starting to test downloaded Excel file");
		ccc.deleteOldFile(downloaddir+"grid-chart.xlsx");
		Thread.sleep(2000);
		ccc.deleteOldFile(downloaddir+"seven6RvSt.xlsx");
		Thread.sleep(2000);
		ccc.exportFiles().click();
		Thread.sleep(2000);
		ccc.crosstabsXls().click();
		Thread.sleep(5000);
		screen.wait(ccc.saveFileButton());
		screen.click(ccc.saveFileButton());
		screen.click(ccc.okButton());
		Thread.sleep(2000);
		ccc.closeExportFiles().click();
		Thread.sleep(2000);
		ccc.renameFile(downloaddir+"grid-chart.xlsx", downloaddir+"seven6RvSt.xlsx");
		Thread.sleep(2000);
		istrue2 = ccc.compareExcelReports(refdir+"refdata.xlsx", downloaddir+"seven6RvSt.xlsx", 5303, 5304, 0, 3, 4, 0, 1);
		if (istrue2) {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2);
			test.log(LogStatus.PASS, "Test step-2: Testing of downloaded Excel file is successful");
		} else {
			log.debug("Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			test.log(LogStatus.WARNING, "Test step-2: Is downloaded Excel file matched with the reference file? - " + istrue2 + ". taking screenshot ... ");
			String path = ccc.takeScreenShot(10, failedscreenshotdir);
	    	String imagepath = test.addScreenCapture(path);
	    	log.warn("Test step-2: Testing of downloaded Excel file is Failed");
	    	test.log(LogStatus.FAIL, "Test step-2: Testing of downloaded Excel file is Failed", imagepath);
		}
		istrue = istrue1 && istrue2;
		softAssert.assertTrue(istrue);
		softAssert.assertAll();
	}
	
	
	
	
	
	
	
}


