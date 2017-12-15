package cellos.bigdata.mapr.ra.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.relevantcodes.extentreports.LogStatus;

public class CommonClass {
	WebDriver driver = null;
	public static WebElement element = null;
	public static List<WebElement> webelementlist = null;
	static Logger log = LogManager.getLogger(CommonClass.class.getName());
	public Screen s = new Screen();
	public static Pattern screenpattern = null;
	public HashMap<String, Number> dictionary = null;
	public static boolean istrue = true;
	private FileInputStream ExcelFile, refExcelFile, dloadExcelFile;
	private XSSFWorkbook ExcelWBook, refExcelWBook, dloadExcelWBook;
	private XSSFSheet ExcelWSheet, refExcelWSheet, dloadExcelWSheet;

	
	public CommonClass(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	
/*********************************************************************
 ********* Finding and returning WebElements of the Zoomdata UI ******* 
 *********************************************************************/
	public WebElement depVolMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Total Volume by DEP')]/../../../following-sibling::div"));
			log.debug("dep volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.debug("dep volume maximize button element not found");
		}
		return element;
	}

	
	
	public WebElement userNameTextBox() {
		try {
			element = driver.findElement(By.id("username"));
			log.debug("<User Name> text box input element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("User Name text box element not found");
		}
		return element;
	}
	
	public WebElement passwordTextBox() {
		try {
			element = driver.findElement(By.id("password"));
			log.debug("<Password> text box input element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Password> text box input element not found");
		}
		return element;
	}
	
	public  WebElement loginSubmitButton() {
		try {
			element = driver.findElement(By.xpath("//*[@id='login-form']/input[2]"));
			log.debug("<Login> submit button input element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Login> submit button input element not found");
		}
		return element;
	}
	
	public WebElement userInfoButton() {
		try {
			element = driver.findElement(By.xpath("//a[@title='User Info']"));
			log.debug("<User Info> icon found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<User Info> icon not found");
		}
		return element;
	}
	
	public WebElement logoutButton() {
		try {
			element = driver.findElement(By.xpath("//span[text()='Logout']/.."));
			log.debug("<Logut> button found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Logut> button not found");
		}
		return element;
	}
	
	public WebElement displayAllViews() {
		try {
			element = driver.findElement(By.xpath("//*[text()='Favorites']/../../../*[contains(text(),'View All')]"));
			log.debug("<View All> link found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<View All> link not found");
		}
		return element;
	}
	
	public List<WebElement> allViewElements() {
		try {
			webelementlist = driver.findElements(By.className("carousel-area-snapshot-title"));
			log.debug("List of WebElements under the <Favourite> icon created");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("List of WebElements under the <Favourite> icon not created");
		}
		return webelementlist;
	}
	
	public List<WebElement> allDropDownViewElements() {
		try {
			webelementlist = driver.findElements(By.xpath("/html/body/div[7]//li/a[@href]"));
			log.debug("List of WebElements links in the dropdown icon created");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("List of WebElements links in the dropdown icon not created");
		}
		return webelementlist;
	}
	
	public List<WebElement> dateTimeBar() {
		try {
			webelementlist = driver.findElements(By.xpath("//div[@class='bottomPane']//div[@class='date-time-wrap']"));
			log.debug("List of WebElements in the time bar ruller found: " + webelementlist);
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("List of WebElements in the time bar ruller not found");
		}
		return webelementlist;
	}
	
	public WebElement OneRaAD() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'1.  RA Analyst- Dashboard')]/.."));
			log.debug("<1.RA Analyst Dashboard> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("1.RA Analyst Dashboard> webelement not found");
		}
		return element;
	}
	
	public WebElement Two1PaS() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'2a. Pre-analysis Summary (1 of 3)')]/.."));
			log.debug("<2a.Pre-analysis Summary (1 of 2)> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<2a.Pre-analysis Summary (1 of 2)> webelement not found");
		}
		return element;
	}
	
	public WebElement Two2PaS() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'2b.  Pre-analysis Summary (2 of 3)')]/.."));
			log.debug("<2b.Pre-analysis Summary (2 of 3)> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<2b.Pre-analysis Summary (2 of 3)> webelement not found");
		}
		return element;
	}
	
	public WebElement Two3PaS() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'2c.  Pre-analysis Summary (3 of 3)')]"));
			log.debug("<2c.Pre-analysis Summary (3 of 3)> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<2c.Pre-analysis Summary (3 of 3)> webelement not found");
		}
		return element;
	}
	
	public WebElement Three1RaAGS() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'3a.  RA Analyst�  GAP Summary (1/2)')]"));
			log.debug("<3a.RA Analyst�  GAP Summary (1/2)> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<3a.RA Analyst�  GAP Summary (1/2)> webelement not found");
		}
		return element;
	}
	
	public WebElement Three2RaAGS() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'3b.  RA Analyst�  GAP Summary (2/2)')]"));
			log.debug("<3b.RA Analyst�  GAP Summary (2/2)> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<3b.RA Analyst�  GAP Summary (2/2)> webelement not found");
		}
		return element;
	}
	
	public WebElement FourRaAApnR() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'4.  RA-Analyst  APN-wise Reports')]"));
			log.debug("<4.RA-Analyst  APN-wise Reports> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<4.RA-Analyst  APN-wise Reports> webelement not found");
		}
		return element;
	}
	
	public WebElement Five1RaAPSR() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'5a.  RA - App/Protocol/sub-protocol wise Reports')]"));
			log.debug("<5a.RA - App/Protocol/sub-protocol wise Reports> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<5a.RA - App/Protocol/sub-protocol wise Reports> webelement not found");
		}
		return element;
	}
	
	public WebElement Five2RaDUR() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'5b.  RA - Domain / URL wise Reports')]"));
			log.debug("<5b.RA - Domain / URL wise Reports> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<5b.RA - Domain / URL wise Reports> webelement not found");
		}
		return element;
	}
	
	public WebElement Five3RaPIpR() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'5c.  RA - Proxy-IP wise Reports')]"));
			log.debug("<5c.RA - Proxy-IP wise Reports> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<5c.RA - Proxy-IP wise Reports> webelement not found");
		}
		return element;
	}
	
	public WebElement SixEv() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'6. Evidences')]"));
			log.debug("<'6.Evidences> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("6.Evidences> webelement not found");
		}
		return element;
	}
	
	public WebElement Seven1GapPro() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'7a.  GAP Profiling (1 of 6)')]"));
			log.debug("<7a.GAP Profiling (1 of 6)> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<7a.GAP Profiling (1 of 6)> webelement not found");
		}
		return element;
	}
	
	public WebElement Seven2GapPro() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'7b.  GAP Profiling (2 of 6)')]"));
			log.debug("<7b.GAP Profiling (2 of 6)> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<7b.GAP Profiling (2 of 6)> webelement not found");
		}
		return element;
	}
	
	public WebElement Seven3GapPro() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'7c.  GAP Profiling (3 of 6)')]"));
			log.debug("<7c.GAP Profiling (3 of 6)> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<7c.GAP Profiling (3 of 6)> webelement not found");
		}
		return element;
	}
	
	public WebElement Seven4GapPro() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'7d.  GAP Profiling (4 of 6)')]"));
			log.debug("<7d.GAP Profiling (4 of 6)> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<7d.GAP Profiling (4 of 6)> webelement not found");
		}
		return element;
	}
	
	public WebElement Seven5GapPro() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'7e.  GAP Profiling (5 of 6)')]"));
			log.debug("<7e.GAP Profiling (5 of 6)> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<7e.GAP Profiling (5 of 6)> webelement not found");
		}
		return element;
	}
	
	public WebElement Seven6GapPro() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'7f.  GAP Profiling (6 of 6)')]"));
			log.debug("<7f.GAP Profiling (6 of 6)> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<7f.GAP Profiling (6 of 6)> webelement not found");
		}
		return element;
	}
	
	public WebElement Eight1RefFiles() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'8a.APN Details')]"));
			log.debug("<8a.APN Details> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<8a.APN Details> webelement not found");
		}
		return element;
	}
	
	public WebElement Eight2RefFiles() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'8b.  URL  Details')]"));
			log.debug("<8b.URL  Details> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<8b.URL  Details> webelement not found");
		}
		return element;
	}
	
	public WebElement Eight3RefFiles() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'8c.  PROTOCOL  Details')]"));
			log.debug("<8c.PROTOCOL  Details> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<8c.PROTOCOL  Details> webelement not found");
		}
		return element;
	}
	
	public WebElement Eight4RefFiles() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'8d.  SUB Protocol  Details')]"));
			log.debug("<8d.SUB Protocol  Details> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<8d.SUB Protocol  Details> webelement not found");
		}
		return element;
	}
	
	public WebElement Eight5RefFiles() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'8f.  MSISDN  Details')]"));
			log.debug("<8f.MSISDN  Details> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<8f.MSISDN  Details> webelement not found");
		}
		return element;
	}
	
	public WebElement Eight6RefFiles() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'8g.  Roaming Partner  Details')]"));
			log.debug("<8g.Roaming Partner  Details> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<8g.Roaming Partner  Details> webelement not found");
		}
		return element;
	}
	
	public WebElement Eight7RefFiles() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'8h.  Special IP  Details')]"));
			log.debug("<8h.Special IP  Details> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("8h.Special IP  Details> webelement not found");
		}
		return element;
	}
	
	public WebElement Eight8RefFiles() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'8i.  Handset Category Details')]"));
			log.debug("<8i.Handset Category Details> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("8i.Handset Category Details> webelement not found");
		}
		return element;
	}
	
	public WebElement Eight9RefFiles() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'8j.  NODE Details')]"));
			log.debug("<8j.NODE Details> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<8j.NODE Details> webelement not found");
		}
		
		return element;
	}
	
	public WebElement Eight10RefFiles() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Reference File Upload')]"));
			log.debug("<Reference File Upload> webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Reference File Upload> webelement not found");
		}
		return element;
	}
	
	public WebElement exportFiles() {
		try {
			element = driver.findElement(By.xpath("/html/body//*[text()='Export']/../../../../div[contains(@style,'block')]//div[@class='zd icon download']"));
			log.debug("<Export> button webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Export> button webelement not found");
		}
		
		return element;
	}
	
	public WebElement crosstabsXls() {
		try {
			element = driver.findElement(By.xpath("//button[contains(.,'Crosstabs (xls)')]"));
			log.debug("<Crosstabs> button webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Crosstabs> button webelement not found");
		}
		
		return element;
	}
	
	public WebElement closeExportFiles() {
		try {
			element = driver.findElement(By.xpath("//div[@data-name='Export']//button[@title='Close']"));
			log.debug("<Crosstabs> button webelement found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Crosstabs> button webelement not found");
		}
		
		return element;
	}
	
	public WebElement YearButton() {
		try {
			element = driver.findElement(By.xpath("//li[contains(text(),'YEAR')]"));
			log.debug("<YEAR> button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.debug("<YEAR> button element not found");
		}
		return element;
	}

	public WebElement DayButton() {
		try {
			element = driver.findElement(By.xpath("//li[contains(text(),'DAY')]"));
			log.debug("<YEAR> button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.debug("<YEAR> button element not found");
		}
		return element;
	}
	
	public WebElement readIncomplete() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Reading')]"));
			log.debug("<Reading> button element found");
		} catch (NoSuchElementException e) {
			log.debug("<Reading> button element not found");
			log.trace(e);
		}
		return element;
	}
	
	
	
	
	public Pattern displayAllDropDownCharts() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_001.PNG");
			log.debug("Dropdown screenshot pattern icon listing all available charts found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Dropdown screenshot pattern icon listing all available charts not found");
		}
		return screenpattern;
	}
	
	public Pattern homeButton() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_002.PNG");
			log.debug("Home screenshot pattern button found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Home screenshot pattern button not found");
		}
		return screenpattern;
	}
	
	public Pattern saveFileButton() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_051.PNG");
			log.debug("<Save file> screenshot pattern button found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Save file> screenshot pattern button not found");
		}
		return screenpattern;
	}
	
	public Pattern okButton() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_052.PNG");
			log.debug("<Ok> screenshot pattern button found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Ok> screenshot pattern button not found");
		}
		return screenpattern;
	}
	
	public Pattern leavePageConfrm() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_121.PNG");
			log.debug("<Leave Page> screenshot pattern button found");
		} catch (NoSuchElementException e) {
			log.error("<Leave Page> screenshot pattern button not found");
			log.trace(e);
		}
		return screenpattern;
	}
	
	public Pattern leavePageNoSave() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_122.PNG");
			log.debug("<Cancel> screenshot pattern button found");
		} catch (NoSuchElementException e) {
			log.error("<Cancel> screenshot pattern button not found");
			log.trace(e);
		}
		return screenpattern;
	}
	
	
	

	
	
/********************************************
 ********* Returning other elements ********* 
 ********************************************/

	public String getRandomString(int length) {
		StringBuilder sb = new StringBuilder();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		log.debug("A random filename of " + length + " characters long is generated");
		return sb.toString();
	}
	
	public String takeScreenShot(int length, String directory) throws Exception {
		String filename = getRandomString(length) + ".png";
		String destination = directory + filename;
		File sourcefile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourcefile, new File(destination));
		log.debug("Screenshot " + filename + " is stored in the directory location " + directory);
		return destination;
	}
	
	public List<String> allViewNames() {
		List<String> element = new ArrayList<String>();
		for (WebElement e : allViewElements()) {
			String linkText = e.getText();
			element.add(linkText); 
		}
		log.debug("List of names of all the charts under the <Favorites> icon created");
		log.debug(element);
		return element;
	}
	
	public List<String> allDropDownViewNames() {
		List<String> element = new ArrayList<String>();
		for (WebElement e : allDropDownViewElements()) {
			String linkText = e.getText();
			element.add(linkText); 
		}
		log.debug("List of names of all the charts in the dropdown icon created");
		return element;
	}
	
	public List<String> startAndEndDates() {
		List<String> element = new ArrayList<String>();
		for (WebElement e : dateTimeBar()) {
			String linkText = e.getText();
			element.add(linkText);
		}
		log.debug("Start and end dates are: " + element);
		return element;
	}
	
	public boolean verifyTimeWindow(List<String> reftimewindow) {
		boolean istrue = startAndEndDates().equals(reftimewindow);
/*		log.debug("Reference time window is " + reftimewindow);
		log.debug("Displayed time window is " + startAndEndDates());
*/		log.debug("Displayed time-window is matched with the expected: - " + istrue);
		return istrue;
	}
	
	public boolean equalityOfTwoStrings( String val1, String val2) {
		log.info("Value of Reference data is: " + val1);
		log.info("Value of displayed data is: " + val2);
		boolean istrue = val1.equals(val2);
		return istrue;
	}
	
	public boolean equalityOfTwoNumbers( Number val1, Number val2) {
		log.info("Value of Reference data is: " + val1);
		log.info("Value of displayed data is: " + val2);
		boolean istrue = val1.equals(val2);
		return istrue;
	}
	
	

	

/********************************************************
 ********* Performing actions on found elements ********* 
 ********************************************************/
	
	public void fillUserNameTextBox(String username) {
		userNameTextBox().click();
		userNameTextBox().clear();
		userNameTextBox().sendKeys(new String[] {username});
		log.debug("Entered username as " + username);
	}
	
	public void fillPasswordTextBox(String password) {
		passwordTextBox().click();
		passwordTextBox().clear();
		passwordTextBox().sendKeys(new String[] {password});
		log.debug("Entered Password as **********");
	}
	
	public void clickOnLoginButton() throws Exception {
		loginSubmitButton().click();
		log.debug("Clicked on <login> button");
		Thread.sleep(2000);
	}
	
	public void clickOnAllViews() throws Exception {
		displayAllViews().click();
		log.debug("Clicked on <View All> button");
		Thread.sleep(2000);
	}
	
	public void clickOnUserInfoButton() {
		userInfoButton().click();
		log.debug("Clicked on <User Info> button");
	}
	
	public void clickOnLogoutButton() {
		logoutButton().click();
		log.debug("Clicked on <Logout> button");
	}
	
	public void clickOnaDropdownChart(int chartNo) {
		allDropDownViewElements().get(chartNo).click();
		log.debug("Clicked on " + allDropDownViewElements().get(chartNo).getText() + " from dropdown list to display the chart");
	}
	
	
	public void clickOnRaAnalystDashboard() {
		OneRaAD().click();
		log.debug("Clicked on <1. RA Analyst- Dashboard> chart");
	}
	
	public void clickOnPreAnalysisSummary1() {
		Two1PaS().click();
		log.debug("Clicked on <2a. Pre-analysis Summary (1 of 3)> chart");
	}
	
	public void clickOnPreAnalysisSummary2() {
		Two2PaS().click();
		log.debug("Clicked on <2b. Pre-analysis Summary (2 of 3)> chart");
	}
	
	public void clickOnPreAnalysisSummary3() {
		Two3PaS().click();
		log.debug("Clicked on <2c. Pre-analysis Summary (3 of 3)> chart");
	}
	
	public void clickOnRaAnalystGapSummary1() {
		Three1RaAGS().click();
		log.debug("Clicked on <3a. RA Analyst�  GAP Summary (1/2)> chart");
	}
	
	public void clickOnRaAnalystGapSummary2() {
		Three2RaAGS().click();
		log.debug("Clicked on <3b. RA Analyst�  GAP Summary (2/2)> chart");
	}
	
	public void clickOnRaAnalystApnWise() {
		FourRaAApnR().click();
		log.debug("Clicked on <4. RA-Analyst  APN-wise Reports> chart");
	}
	
	public void clickOnRaAppProtoSubprotWise() {
		Five1RaAPSR().click();
		log.debug("Clicked on <5a. RA - App/Protocol/sub-protocol wise Reports> chart");
	}
	
	public void clickOnRaDomainUrlWise() {
		Five2RaDUR().click();
		log.debug("Clicked on <5b.  RA - Domain / URL wise Reports> chart");
	}
	
	public void clickOnRaProxyIpWise() {
		Five3RaPIpR().click();
		log.debug("Clicked on <5c.  RA - Proxy-IP wise Reports> chart");
	}
	
	public void clickOnEvidences() {
		SixEv().click();
		log.debug("Clicked on <6. Evidences> chart");
	}
	
	public void clickOnGapProfiling1() {
		Seven1GapPro().click();
		log.debug("Clicked on <7a. GAP Profiling (1 of 6)> chart");
	}
	
	public void clickOnGapProfiling2() {
		Seven2GapPro().click();
		log.debug("Clicked on <7b. GAP Profiling (2 of 6)> chart");
	}
	
	public void clickOnGapProfiling3() {
		Seven3GapPro().click();
		log.debug("Clicked on <7c. GAP Profiling (3 of 6)> chart");
	}
	
	public void clickOnGapProfiling4() {
		Seven4GapPro().click();
		log.debug("Clicked on <7d. GAP Profiling (4 of 6)> chart");
	}
	
	public void clickOnGapProfiling5() {
		Seven5GapPro().click();
		log.debug("Clicked on <7e. GAP Profiling (5 of 6)> chart");
	}
	
	public void clickOnGapProfiling6() {
		Seven6GapPro().click();
		log.debug("Clicked on <7f. GAP Profiling (6 of 6)> chart");
	}
	
	public void clickOnApnDtls() {
		Eight1RefFiles().click();
		log.debug("Clicked on <8a. APN Details> reference chart");
	}
	
	public void clickOnUrlDtls() {
		Eight2RefFiles().click();
		log.debug("Clicked on <8b. URL Details> reference chart");
	}
	
	public void clickOnProtoDtls() {
		Eight3RefFiles().click();
		log.debug("Clicked on <8c. PROTOCOL Details> reference chart");
	}
	
	public void clickOnSubProtoDtls() {
		Eight4RefFiles().click();
		log.debug("Clicked on <8d. SUB Protocol Details> reference chart");
	}
	
	public void clickOnMsisdnDtls() {
		Eight5RefFiles().click();
		log.debug("Clicked on <8f. MSISDN Details> reference chart");
	}
	
	public void clickOnRoamPartnerDtls() {
		Eight6RefFiles().click();
		log.debug("Clicked on <8g. Roaming Partner Details> reference chart");
	}
	
	public void clickOnSpecialIpDtls() {
		Eight7RefFiles().click();
		log.debug("Clicked on <8h. Special IP Details> reference chart");
	}
	
	public void clickOnHandsetCategoryDtls() {
		Eight8RefFiles().click();
		log.debug("Clicked on <8i. Handset Category Details> reference chart");
	}
	
	public void clickOnNodeDtls() {
		Eight9RefFiles().click();
		log.debug("Clicked on <8j. NODE Details> reference chart");
	}
	
	public void clickOnRefFileUpld() {
		Eight10RefFiles().click();
		log.debug("Clicked on <Reference File Upload> reference chart");
	}
	
	public void setDateTimeBar() throws Exception {
		dateTimeBar().get(0).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='item-title']/*[contains(text(),'Filter by Range')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-semantic']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[contains(text(),'Calendar')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Year'][contains(text(),'Year')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='2016'][contains(text(),'2016')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Month'][contains(text(),'Month')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='Jun'][contains(text(),'Jun')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Day'][contains(text(),'Day')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//td[contains(@class,'day')][contains(text(),'25')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-semantic']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[contains(text(),'Calendar')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Year'][contains(text(),'Year')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='2016'][contains(text(),'2016')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Month'][contains(text(),'Month')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='Jul'][contains(text(),'Jul')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Day'][contains(text(),'Day')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//td[contains(@class,'day')][text()='2']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@value='applyFilter'][text()='Apply']")).click();
	}
	
	public void setDateTimeBar28() throws Exception {
		dateTimeBar().get(0).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='item-title']/*[contains(text(),'Filter by Range')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-semantic']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[contains(text(),'Calendar')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Year'][contains(text(),'Year')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='2016'][contains(text(),'2016')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Month'][contains(text(),'Month')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='Jun'][contains(text(),'Jun')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Day'][contains(text(),'Day')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//td[contains(@class,'day')][contains(text(),'28')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-semantic']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[contains(text(),'Calendar')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Year'][contains(text(),'Year')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='2016'][contains(text(),'2016')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Month'][contains(text(),'Month')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='Jul'][contains(text(),'Jul')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Day'][contains(text(),'Day')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//td[contains(@class,'day')][text()='28']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@value='applyFilter'][text()='Apply']")).click();
	}
	
	public void setDateTimeBar29() throws Exception {
		dateTimeBar().get(0).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='item-title']/*[contains(text(),'Filter by Range')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-semantic']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[contains(text(),'Calendar')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Year'][contains(text(),'Year')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='2016'][contains(text(),'2016')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Month'][contains(text(),'Month')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='Jul'][contains(text(),'Jul')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Day'][contains(text(),'Day')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//td[contains(@class,'day')][contains(text(),'29')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-semantic']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[contains(text(),'Calendar')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Year'][contains(text(),'Year')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='2016'][contains(text(),'2016')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Month'][contains(text(),'Month')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='Jul'][contains(text(),'Jul')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Day'][contains(text(),'Day')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//td[contains(@class,'day')][text()='29']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@value='applyFilter'][text()='Apply']")).click();
	}

	public void setDateTimeBar30() throws Exception {
		dateTimeBar().get(0).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='item-title']/*[contains(text(),'Filter by Range')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-semantic']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[contains(text(),'Calendar')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Year'][contains(text(),'Year')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='2016'][contains(text(),'2016')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Month'][contains(text(),'Month')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='Jul'][contains(text(),'Jul')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Day'][contains(text(),'Day')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//td[contains(@class,'day')][contains(text(),'30')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-semantic']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[contains(text(),'Calendar')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Year'][contains(text(),'Year')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='2016'][contains(text(),'2016')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Month'][contains(text(),'Month')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='Jul'][contains(text(),'Jul')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Day'][contains(text(),'Day')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//td[contains(@class,'day')][text()='30']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@value='applyFilter'][text()='Apply']")).click();
	}
	
	public void setDateTimeBar01() throws Exception {
		dateTimeBar().get(0).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='item-title']/*[contains(text(),'Filter by Range')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-semantic']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[contains(text(),'Calendar')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Year'][contains(text(),'Year')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='2016'][contains(text(),'2016')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Month'][contains(text(),'Month')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='Jul'][contains(text(),'Jul')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Day'][contains(text(),'Day')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//td[contains(@class,'day')][contains(text(),'1')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-semantic']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[contains(text(),'Calendar')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Year'][contains(text(),'Year')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='2016'][contains(text(),'2016')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Month'][contains(text(),'Month')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='Jul'][contains(text(),'Jul')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Day'][contains(text(),'Day')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//td[contains(@class,'day')][text()='1']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@value='applyFilter'][text()='Apply']")).click();
	}

	public void setDateTimeBar02() throws Exception {
		dateTimeBar().get(0).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='item-title']/*[contains(text(),'Filter by Range')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-semantic']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[contains(text(),'Calendar')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Year'][contains(text(),'Year')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='2016'][contains(text(),'2016')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Month'][contains(text(),'Month')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='Jul'][contains(text(),'Jul')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input from']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Day'][contains(text(),'Day')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//td[contains(@class,'day')][text()='2']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-semantic']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[contains(text(),'Calendar')]/..")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Year'][contains(text(),'Year')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='2016'][contains(text(),'2016')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Month'][contains(text(),'Month')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@data-item='Jul'][contains(text(),'Jul')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@class='time-input to']//*[@class='input-group-date']/span[@class='icon calendar-icon']")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//li[@title='Day'][contains(text(),'Day')]")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//td[contains(@class,'day')][text()='2']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@value='applyFilter'][text()='Apply']")).click();
	}
	
	
	public void deleteOldFile(String sourcefilename) {
		File source = new File(sourcefilename);
		if(source.exists()) {
			source.delete();
			log.debug("An existing downloaded file is present in the download directory, deleted it");
		}
	}

	public void renameFile(String sourcefilename, String destfilename) {
		File source = new File(sourcefilename);
		File dest = new File(destfilename);
		try {
			FileUtils.moveFile(source, dest);
			log.debug("Successfully renamed downloaded file");
		} catch (IOException e) {
			log.trace(e);
			log.debug("Something went wrong during renaming downloaded file");
		}
	}
	
	public HashMap<String, Number> createHashMap(int startKeyRowNo, int startKeyColNo, int startValColNo, int lenghtOfMap, String dataFileName) throws Exception {
		dictionary = new HashMap<String, Number>();
		ExcelFile = new FileInputStream(dataFileName);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheetAt(0);
		for (int i=0; i<=lenghtOfMap; i++, startKeyRowNo++) {
			dictionary.put(ExcelWSheet.getRow(startKeyRowNo).getCell(startKeyColNo).getStringCellValue(),ExcelWSheet.getRow(startKeyRowNo).getCell(startValColNo).getNumericCellValue());
		}
		return dictionary;
	}

	public boolean compareExcelFiles(String refFileName, String dloadFileName, int startRow, int startCol, int endRow, int endCol) throws Exception {
		refExcelFile = new FileInputStream(refFileName);
		refExcelWBook = new XSSFWorkbook(refExcelFile);
		refExcelWSheet = refExcelWBook.getSheetAt(0);
		dloadExcelFile = new FileInputStream(dloadFileName);
		dloadExcelWBook = new XSSFWorkbook(dloadExcelFile);
		dloadExcelWSheet = dloadExcelWBook.getSheetAt(0);
		for (int i=startRow; i<=endRow; i++) {
			for (int j=startCol; j<=endCol; j++) {
				try {
					CellType reftype =  refExcelWSheet.getRow(i).getCell(j).getCellTypeEnum();
					CellType dloadtype =  dloadExcelWSheet.getRow(i).getCell(j).getCellTypeEnum();
					if (reftype == CellType.STRING && dloadtype == CellType.STRING) {
						log.debug("reference: " + refExcelWSheet.getRow(i).getCell(j).getStringCellValue());
						log.debug("downloaded: " + dloadExcelWSheet.getRow(i).getCell(j).getStringCellValue());
						istrue = istrue && (refExcelWSheet.getRow(i).getCell(j).getStringCellValue().equals(dloadExcelWSheet.getRow(i).getCell(j).getStringCellValue()));
						log.debug(istrue);
					} else if (reftype == CellType.NUMERIC && dloadtype == CellType.NUMERIC) {
						log.debug("reference: " + refExcelWSheet.getRow(i).getCell(j).getNumericCellValue());
						log.debug("downloaded: " + dloadExcelWSheet.getRow(i).getCell(j).getNumericCellValue());
						istrue = refExcelWSheet.getRow(i).getCell(j).getNumericCellValue() == dloadExcelWSheet.getRow(i).getCell(j).getNumericCellValue();
						log.debug(istrue);
					}
				} catch (Exception e) {
					log.error("something went wrong during reading the Excel file");
					log.trace(e);
				}
			}
		}
		return istrue;
	}


	public boolean compareExcelReports(String refFileName, String dloadFileName, int refKeyStartRow, int refKeyEndRow, int refKeyCol, int dldKeyStartRow, int dldKeyEndRow, int dldKeyCol, int dldValueCol) throws Exception {
		dloadExcelFile = new FileInputStream(dloadFileName);
		dloadExcelWBook = new XSSFWorkbook(dloadExcelFile);
		dloadExcelWSheet = dloadExcelWBook.getSheetAt(0);
		refExcelFile = new FileInputStream(refFileName);
		refExcelWBook = new XSSFWorkbook(refExcelFile);
		refExcelWSheet = refExcelWBook.getSheetAt(1);
		String dldKey = null;
		Number dldVal = null;
		ArrayList<String> refKeyList = new ArrayList<String>(); 
		ArrayList<String> dldKeyList = new ArrayList<String>();
		ArrayList<String> extraKeyList = new ArrayList<String>();
		ArrayList<String> nonPresentKeyList = new ArrayList<String>();
		istrue = true;
		log.debug("Intial value of istrue: - " + istrue);
		for (int i=refKeyStartRow; i<=refKeyEndRow; i++) {
			refKeyList.add(refExcelWSheet.getRow(i).getCell(refKeyCol).getStringCellValue());
		}
		Collections.sort(refKeyList);
		log.debug("List of reference keys is: " + refKeyList);
		for (int i=dldKeyStartRow; i<=dldKeyEndRow; i++) {
			dldKeyList.add(dloadExcelWSheet.getRow(i).getCell(dldKeyCol).getStringCellValue());
		}
		Collections.sort(dldKeyList);
		log.debug("List of downloaded keys is: " + dldKeyList);
		
		//if (refKeyList.equals(dldKeyList)) {
		if (refKeyList.containsAll(dldKeyList)) {
			log.debug("Name of keys in the downloaded Excel file is maching with the expected keys, starting to test the values");
			for (int i=dldKeyStartRow; i<=dldKeyEndRow; i++) {
				log.debug("Starting to test " + i + "-th row");
				try {
					dldKey = dloadExcelWSheet.getRow(i).getCell(dldKeyCol).getStringCellValue();
					log.debug("the dload key in the download file, which need to be tested is: " + dldKey);
					
				} catch (Exception e) {
					log.trace("Download key could not be read at the row number: " +i + ". The exception is: " +e);
				}
				try {
					dldVal = dloadExcelWSheet.getRow(i).getCell(dldValueCol).getNumericCellValue();
					log.debug("the dload value in the download file, which need to be tested is: " + dldVal);
				} catch (Exception e) {
					log.trace("Download value could not be read at the row number: " +i + ". The exception is: " +e);
				}
				Number refVal = findandreturnvalue(refFileName, dldKey, refKeyStartRow, refKeyEndRow, refKeyCol);
				log.debug("Downloaded value in the " + i + "-th row is matched with the expected results: - " + dldVal.equals(refVal));
				istrue = istrue && dldVal.equals(refVal);
			}
		} else {
			log.error("Name of keys in the downloaded Excel file is not completely maching with the expected keys");
			for (String s: refKeyList) {
				if (dldKeyList.contains(s)) {
					log.trace("Reference key " + s + " is present in the download key list");
				} else {
					nonPresentKeyList.add(s);
				}
			}
			for (String s: dldKeyList) {
				if (refKeyList.contains(s)) {
					log.trace("Downloaded key " + s + " is present in the reference key list");
				} else {
					extraKeyList.add(s);
				}
			}
			log.error("List of extra keys found in the downloaded file is: - " + extraKeyList);
			log.error("List of expected keys not found in the downloaded file is: - " + nonPresentKeyList);
			istrue =false;
		}
		return istrue;
	}
	


	private Number findandreturnvalue(String refFileName, String dldKey, int refKeyStartRow, int refKeyEndRow, int refKeyCol) throws Exception {
		refExcelFile = new FileInputStream(refFileName);
		refExcelWBook = new XSSFWorkbook(refExcelFile);
		refExcelWSheet = refExcelWBook.getSheetAt(1);
		String refKey = null;
		Number refVal = null;
		
		//log.debug("At the begining of this search, the refkey is: " + refKey + " and the refval is: " + refVal);
		for (int i=refKeyStartRow; i<=refKeyEndRow; i++) {
			try {
				refKey = refExcelWSheet.getRow(i).getCell(refKeyCol).getStringCellValue();
			} catch (Exception e) {
				log.trace("Reference key could not be read at the row number: " +i + ". The exception is: " +e);
			}
			if (dldKey.equals(refKey)) {
				log.debug("A match of searched dload key found in the reference file: " + refKey);
				try {
					refVal = refExcelWSheet.getRow(i).getCell(refKeyCol+2).getNumericCellValue();
					log.debug("And the corresponding reference value is: " + refVal);
				} catch (Exception e) {
					log.trace("Reference value could not be read at the row number: " +i + ". The exception is: " +e);
				}
				//log.debug("At the end of this search, the refkey is: " + refKey + " and the refval is: " + refVal);
				return refVal;
			}
		}
//		log.debug("At the end of this search, the refkey is: " + refKey + " and the reval is: " + refVal);
		return refVal;
	}
	
	
	
}

