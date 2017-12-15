package cellos.bigdata.mapr.ra.pages;

import java.io.FileInputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class OneRaAD {
	WebDriver driver = null;
	public static WebElement element = null;
	public static List<WebElement> webelementlist = null;
	public static String stringelement = null;
	static Logger log = LogManager.getLogger(OneRaAD.class.getName());
	Screen s = new Screen();
	public static Pattern screenpattern = null;
	
	private XSSFWorkbook refExcelWBook, downloadedExcelWBook;
	private XSSFSheet refExcelWSheet, downloadedExcelWSheet;
/*	private XSSFCell Cell;
	private String refData;
	private String displayedData;
	
	private Double doubleval = null;
*/
	
	public OneRaAD(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
/*********************************************************************
 ********* Finding and returning WebElements of the Zoomdata UI ******* 
 *********************************************************************/
	public WebElement depVolMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Total Volume by DEP')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("<dep> volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.debug("<dep> volume maximize button element not found");
		}
		return element;
	}
	
	public WebElement depVolZoominButton() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Total Volume by DEP')]/../../../../..//*[contains(text(),'DAY')]"));
			log.debug("<dep> volume zoomin button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.debug("<dep> volume zoomin button element not found");
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
	
	public WebElement nwVolMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Total Volume by NW')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("<NW> volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<NW> volume maximize button element not found");
		}
		return element;
	}
	
	public WebElement nwVolZoominButton() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Total Volume by NW')]/../../../../..//*[contains(text(),'DAY')]"));
			log.debug("<dep> volume zoomin button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.debug("<dep> volume zoomin button element not found");
		}
		return element;
	}
	
	public WebElement chgVolMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Total Volume by CHG')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("<CHG> volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<CHG> volume maximize button element not found");
		}
		return element;
	}
	
	public WebElement chgVolZoominButton() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Total Volume by CHG')]/../../../../..//*[contains(text(),'DAY')]"));
			log.debug("<dep> volume zoomin button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.debug("<dep> volume zoomin button element not found");
		}
		return element;
	}
	
	public WebElement subsCountMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Total Subscribers')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("<Total subscriber count> volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Total subscriber count> volume maximize button element not found");
		}
		return element;
	}
	
	public WebElement subsCounZoominButton() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Total Subscribers')]/../../../../..//*[contains(text(),'DAY')]"));
			log.debug("<dep> volume zoomin button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.debug("<dep> volume zoomin button element not found");
		}
		return element;
	}
	
	public WebElement sessnCountMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Total Sessions')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("<Total session count> maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Total session count> maximize button element not found");
		}
		return element;
	}
	
	public WebElement sessnCountZoominButton() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Total Sessions')]/../../../../..//*[contains(text(),'DAY')]"));
			log.debug("<dep> volume zoomin button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.debug("<dep> volume zoomin button element not found");
		}
		return element;
	}
	
	public WebElement usgAssrnceMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Usage Assurance')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("<Usage assurance> maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Usage assurance> maximize button element not found");
		}
		return element;
	}
	
	public WebElement revLkgMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Revenue Leakage')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("<Revenue leakage> maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Revenue leakage> maximize button element not found");
		}
		return element;
	}
	
	public WebElement depVsChgMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'DEP Vs CHG')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("<DEP vs. CHG> maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<DEP vs. CHG> maximize button element not found");
		}
		return element;
	}
	
	public WebElement depVsNwMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'DEP V/S NW')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("<DEP vs. NW> maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<DEP vs. NW> maximize button element not found");
		}
		return element;
	}
	
	public WebElement nwVsChgMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'NW V/S CHG')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("<NW vs. CHG> maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<NW vs. CHG> maximize button element not found");
		}
		return element;
	}
	
	public WebElement ovrRprtMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Total Over-reported Volume')]/../../../..//span[contains(@class,'fullscreen')]/i"));
			log.debug("isDisplayed" + element.isDisplayed());
			log.debug("isEnabled" + element.isEnabled());
			log.debug("isEnabled" + element.isEnabled());
			log.debug("<Over report> maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Over report> maximize button element not found");
		}
		return element;
	}
	
	public WebElement detailsButn() {
		try {
			element = driver.findElement(By.xpath("//*[@data-name='details'][text()='Details']"));
			log.debug("<Details> web element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Details> web element not found");
		}
		return element;
	}
	
	public WebElement volContainer() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Time Window')]/following-sibling::dt/following-sibling::dd"));;
			log.debug("<volume information> web  element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<volume information> web element not found");
		}
		return element;
	}
	
	public WebElement subsContainer() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Time Window')]/following-sibling::dt[contains(text(),'Total Subscribers')]/following-sibling::dd"));;
			log.debug("<volume information> web  element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<volume information> web element not found");
		}
		return element;
	}
	
	public WebElement sessnContainer() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Time Window')]/following-sibling::dt[contains(text(),'Total Sessions')]/following-sibling::dd"));;
			log.debug("<volume information> web  element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<volume information> web element not found");
		}
		return element;
	}
	
	public WebElement closeDetailsButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Current')]/../../preceding-sibling::div//button [@title='Close']"));;
			log.debug("<Close details> web element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Close details> web element not found");
		}
		return element;
	}
	
	public WebElement totGapVol() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Total Gap Volume')]/../following-sibling::p/span"));;
			log.debug("<Close details> web element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Close details> web element not found");
		}
		return element;
	}

	public WebElement missingGapVol() {
			try {
				element = driver.findElement(By.xpath("//*[contains(text(),'Missing Volume')]/following-sibling::p/span"));;
				log.debug("<Close details> web element found");
			} catch (NoSuchElementException e) {
				log.trace(e);
				log.error("<Close details> web element not found");
			}
			return element;
	}

			public WebElement underGapVol() {
				try {
					element = driver.findElement(By.xpath("//*[contains(text(),'UnderReported Volume')]/following-sibling::p/span"));;
					log.debug("<Close details> web element found");
				} catch (NoSuchElementException e) {
					log.trace(e);
					log.error("<Close details> web element not found");
				}
				return element;
	}
	
				public WebElement overGapVol() {
					try {
						element = driver.findElement(By.xpath("//*[contains(text(),'Over Reported Volume')]/following-sibling::p/span"));;
						log.debug("<Close details> web element found");
					} catch (NoSuchElementException e) {
						log.trace(e);
						log.error("<Close details> web element not found");
					}
					return element;
	}
	
				public WebElement totLeakage() {
					try {
						element = driver.findElement(By.xpath("//p[contains(text(),'Revenue Leakage')]/following-sibling::p"));;
						log.debug("<Total Leakage> web element found");
					} catch (NoSuchElementException e) {
						log.trace(e);
						log.error("<Total Leakage> web element not found");
					}
					return element;
	}
	
				public WebElement postpaidLeakage() {
					try {
						element = driver.findElement(By.xpath("//p[contains(text(),'Postpaid')]/following-sibling::p"));;
						log.debug("<Postpaid Leakage> web element found");
					} catch (NoSuchElementException e) {
						log.trace(e);
						log.error("<Postpaid Leakage> web element not found");
					}
					return element;
	}
	
				public WebElement prepaidLeakage() {
					try {
						element = driver.findElement(By.xpath("//p[contains(text(),'Prepaid')]/following-sibling::p"));;
						log.debug("<Prepaid Leakage> web element found");
					} catch (NoSuchElementException e) {
						log.trace(e);
						log.error("<Prepaid Leakage> web element not found");
					}
					return element;
	}

				
	
	


				
				
				
				
				
				
				
				
	
	
	
	
	
	public Pattern depTotpatternOn628() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_003.png");
			log.debug("screenpattern of DEP Total volume on 28-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP Total volume on 28-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern depTotpatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_004.png");
			log.debug("screenpattern of DEP Total volume on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP Total volume on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern depTotpatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_005.png");
			log.debug("screenpattern of DEP Total volume on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP Total volume on 30-06-2016 not found");
		}
		return screenpattern;
	}

	public Pattern depTotpatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_006.png");
			log.debug("screenpattern of DEP Total volume on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP Total volume on 01-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern depTotpatternOn702() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_007.png");
			log.debug("screenpattern of DEP Total volume on 02-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP Total volume on 02-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern depTotpatternWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_008.png");
			log.debug("screenpattern of DEP Total volume for whole year found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP Total volume for whole year not found");
		}
		return screenpattern;
	}
	
	public Pattern chgTotpatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_009.png");
			log.debug("screenpattern of CCN Total volume on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CCN Total volume on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgTotpatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_010.png");
			log.debug("screenpattern of CCN Total volume on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CCN Total volume on 30-06-2016 not found");
		}
		return screenpattern;
	}

	public Pattern chgTotpatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_011.png");
			log.debug("screenpattern of CCN Total volume on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CCN Total volume on 01-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern chgTotpatternOn702() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_012.png");
			log.debug("screenpattern of CCN Total volume on 02-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CCN Total volume on 02-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern chgTotpatternWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_013.png");
			log.debug("screenpattern of CCN Total volume for whole year found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CCN Total volume for whole year not found");
		}
		return screenpattern;
	}
	
	public Pattern nwTotpatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_014.png");
			log.debug("screenpattern of GGSN Total volume on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of GGSN Total volume on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwTotpatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_015.png");
			log.debug("screenpattern of GGSN Total volume on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of GGSN Total volume on 30-06-2016 not found");
		}
		return screenpattern;
	}

	public Pattern nwTotpatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_016.png");
			log.debug("screenpattern of GGSN Total volume on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of GGSN Total volume on 01-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern nwTotpatternOn702() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_017.png");
			log.debug("screenpattern of GGSN Total volume on 02-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of GGSN Total volume on 02-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern nwTotpatternWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_018.png");
			log.debug("screenpattern of GGSN Total volume for whole year found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of GGSN Total volume for whole year not found");
		}
		return screenpattern;
	}
	
	public Pattern depSubspatternOn628() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_019.png");
			log.debug("screenpattern of DEP subscriber count on 28-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP subscriber count on 28-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgSubspatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_020.png");
			log.debug("screenpattern of CCN subscriber count on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CCN subscriber count on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern depSubspatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_021.png");
			log.debug("screenpattern of DEP subscriber count on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP subscriber count on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwSubspatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_022.png");
			log.debug("screenpattern of GGSN subscriber count on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of GGSN subscriber count on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgSubspatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_023.png");
			log.debug("screenpattern of CCN subscriber count on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CCN subscriber count on 30-06-2016 not found");
		}
		return screenpattern;
	}

	public Pattern depSubspatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_024.png");
			log.debug("screenpattern of DEP subscriber count on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP subscriber count on 30-06-2016 not found");
		}
		return screenpattern;
	}

	public Pattern nwSubspatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_025.png");
			log.debug("screenpattern of GGSN subscriber count on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of GGSN subscriber count on 30-06-2016 not found");
		}
		return screenpattern;
	}

	public Pattern chgSubspatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_026.png");
			log.debug("screenpattern of CCN subscriber count on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CCN subscriber count on 01-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern depSubspatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_027.png");
			log.debug("screenpattern of DEP subscriber count on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP subscriber count on 01-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern nwSubspatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_028.png");
			log.debug("screenpattern of GGSN subscriber count on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of GGSN subscriber count on 01-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern chgSubspatternOn702() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_029.png");
			log.debug("screenpattern of CCN subscriber count on 02-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CCN subscriber count on 02-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern depSubspatternOn702() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_030.png");
			log.debug("screenpattern of DEP subscriber count on 02-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP subscriber count on 02-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern nwSubspatternOn702() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_031.png");
			log.debug("screenpattern of GGSN subscriber count on 02-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of GGSN subscriber count on 02-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern chgSubspatternWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_032.png");
			log.debug("screenpattern of CCN subscriber count for whole year found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CCN subscriber count for whole year not found");
		}
		return screenpattern;
	}
	
	public Pattern depSubspatternWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_033.png");
			log.debug("screenpattern of DEP subscriber count for whole year found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP subscriber count for whole year not found");
		}
		return screenpattern;
	}
	
	public Pattern nwSubspatternWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_034.png");
			log.debug("screenpattern of GGSN subscriber count for whole year found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of GGSN subscriber count for whole year not found");
		}
		return screenpattern;
	}
	
	public Pattern depSessnpatternOn628() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_035.png");
			log.debug("screenpattern of DEP session count on 28-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP session count on 28-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgSessnpatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_036.png");
			log.debug("screenpattern of CCN session count on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CCN session count on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern depSessnpatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_037.png");
			log.debug("screenpattern of DEP session count on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP session count on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwSessnpatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_038.png");
			log.debug("screenpattern of GGSN session count on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of GGSN session count on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgSessnpatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_039.png");
			log.debug("screenpattern of CCN session count on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CCN session count on 30-06-2016 not found");
		}
		return screenpattern;
	}

	public Pattern depSessnpatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_040.png");
			log.debug("screenpattern of DEP session count on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP session count on 30-06-2016 not found");
		}
		return screenpattern;
	}

	public Pattern nwSessnpatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_041.png");
			log.debug("screenpattern of GGSN session count on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of GGSN session count on 30-06-2016 not found");
		}
		return screenpattern;
	}

	public Pattern chgSessnpatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_042.png");
			log.debug("screenpattern of CCN session count on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CCN session count on 01-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern depSessnpatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_043.png");
			log.debug("screenpattern of DEP session count on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP session count on 01-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern nwSessnpatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_044.png");
			log.debug("screenpattern of GGSN session count on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of GGSN session count on 01-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern chgSessnpatternOn702() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_045.png");
			log.debug("screenpattern of CCN session count on 02-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CCN session count on 02-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern depSessnpatternOn702() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_046.png");
			log.debug("screenpattern of DEP session count on 02-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP session count on 02-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern nwSessnpatternOn702() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_047.png");
			log.debug("screenpattern of GGSN session count on 02-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of GGSN session count on 02-07-2016 not found");
		}
		return screenpattern;
	}

	public Pattern chgSessnpatternWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_048.png");
			log.debug("screenpattern of CCN session count for whole year found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CCN session count for whole year not found");
		}
		return screenpattern;
	}
	
	public Pattern depSessnpatternWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_049.png");
			log.debug("screenpattern of DEP session count for whole year found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP session count for whole year not found");
		}
		return screenpattern;
	}
	
	public Pattern nwSessnpatternWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_050.png");
			log.debug("screenpattern of GGSN session count for whole year found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of GGSN session count for whole year not found");
		}
		return screenpattern;
	}
	
	public Pattern scrollDownPattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_054.png");
			log.debug("screenpattern of scrolling down is found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of scrolling down is not found");
		}
		return screenpattern;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	/********************************************
	 ********* Returning other elements ********* 
	 ********************************************/
	public String totVolume() throws Exception {
		WebElement elem1 = null;
		try {
			elem1 = driver.findElement(By.xpath("//*[@data-name='details'][text()='Details']"));
			log.debug("<Details> element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Details> element found not");
		}
		elem1.click();
		Thread.sleep(5000);
		WebElement elem2 = null;
		try {
			elem2 = driver.findElement(By.xpath("//*[contains(text(),'JUN 28 2016')]/../following-sibling::div//*[contains(text(),'Total DEP volume')]/following-sibling::dd"));
			log.debug("Value on <JUN 28 2016> is found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Value on <JUN 28 2016> is found");
		}
		elem2.click();
		stringelement = elem2.getText();
		return stringelement;
	}
	

/********************************************************
 ********* Performing actions on found elements ********* 
 ********************************************************/
	
	public boolean verifyDepTotVolDloadFile(String refdatafile, String downloadedfile) throws Exception {
		FileInputStream refExcelFile = new FileInputStream(refdatafile);
		FileInputStream downloadedExcelFile = new FileInputStream(downloadedfile);
		refExcelWBook = new XSSFWorkbook(refExcelFile);
		log.info("Referecne Excel data file found");
		downloadedExcelWBook = new XSSFWorkbook(downloadedExcelFile);
		log.info("Downloaded Excel data file found");
		refExcelWSheet = refExcelWBook.getSheet("oneraad");
		log.info("Referecne Excel work sheet found: " + refExcelWSheet);
		downloadedExcelWSheet = downloadedExcelWBook.getSheetAt(0);
		log.debug("Downloaded Excel work sheet found: " + downloadedExcelWSheet);
		boolean istrue1 = roundingValue(refExcelWSheet.getRow(2).getCell(2).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(3).getCell(1).getNumericCellValue()));
		boolean istrue2 = roundingValue(refExcelWSheet.getRow(3).getCell(2).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(4).getCell(1).getNumericCellValue()));
		boolean istrue3 = roundingValue(refExcelWSheet.getRow(4).getCell(2).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(5).getCell(1).getNumericCellValue()));
		boolean istrue4 = roundingValue(refExcelWSheet.getRow(5).getCell(2).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(6).getCell(1).getNumericCellValue()));
		boolean istrue5 = roundingValue(refExcelWSheet.getRow(6).getCell(2).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(7).getCell(1).getNumericCellValue()));
		boolean istrue = istrue1&&istrue2&&istrue3&&istrue4&&istrue5;
		return istrue;
	}

	public boolean verifyNwTotVolDloadFile(String refdatafile, String downloadedfile) throws Exception {
		FileInputStream refExcelFile = new FileInputStream(refdatafile);
		FileInputStream downloadedExcelFile = new FileInputStream(downloadedfile);
		refExcelWBook = new XSSFWorkbook(refExcelFile);
		log.info("Referecne Excel data file found");
		downloadedExcelWBook = new XSSFWorkbook(downloadedExcelFile);
		log.info("Downloaded Excel data file found");
		refExcelWSheet = refExcelWBook.getSheet("oneraad");
		log.info("Referecne Excel work sheet found: " + refExcelWSheet);
		downloadedExcelWSheet = downloadedExcelWBook.getSheetAt(0);
		log.debug("Downloaded Excel work sheet found: " + downloadedExcelWSheet);
		boolean istrue1 = roundingValue(refExcelWSheet.getRow(2).getCell(6).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(3).getCell(1).getNumericCellValue())); 
		boolean istrue2 = roundingValue(refExcelWSheet.getRow(3).getCell(6).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(4).getCell(1).getNumericCellValue()));
		boolean istrue3 = roundingValue(refExcelWSheet.getRow(4).getCell(6).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(5).getCell(1).getNumericCellValue()));
		boolean istrue4 = roundingValue(refExcelWSheet.getRow(5).getCell(6).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(6).getCell(1).getNumericCellValue()));
		boolean istrue5 = roundingValue(refExcelWSheet.getRow(6).getCell(6).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(7).getCell(1).getNumericCellValue()));
		boolean istrue = istrue1&&istrue2&&istrue3&&istrue4&&istrue5;
		return istrue;
	}

	public boolean verifyChgTotVolDloadFile(String refdatafile, String downloadedfile) throws Exception {
		FileInputStream refExcelFile = new FileInputStream(refdatafile);
		FileInputStream downloadedExcelFile = new FileInputStream(downloadedfile);
		refExcelWBook = new XSSFWorkbook(refExcelFile);
		log.info("Referecne Excel data file found");
		downloadedExcelWBook = new XSSFWorkbook(downloadedExcelFile);
		log.info("Downloaded Excel data file found");
		refExcelWSheet = refExcelWBook.getSheet("oneraad");
		log.info("Referecne Excel work sheet found: " + refExcelWSheet);
		downloadedExcelWSheet = downloadedExcelWBook.getSheetAt(0);
		log.debug("Downloaded Excel work sheet found: " + downloadedExcelWSheet);
		boolean istrue1 = roundingValue(refExcelWSheet.getRow(2).getCell(10).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(3).getCell(1).getNumericCellValue()));
		boolean istrue2 = roundingValue(refExcelWSheet.getRow(3).getCell(10).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(4).getCell(1).getNumericCellValue()));
		boolean istrue3 = roundingValue(refExcelWSheet.getRow(4).getCell(10).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(5).getCell(1).getNumericCellValue()));
		boolean istrue4 = roundingValue(refExcelWSheet.getRow(5).getCell(10).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(6).getCell(1).getNumericCellValue()));
		boolean istrue5 = roundingValue(refExcelWSheet.getRow(6).getCell(10).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(7).getCell(1).getNumericCellValue()));
		boolean istrue = istrue1&&istrue2&&istrue3&&istrue4&&istrue5;
		return istrue;
	}
	
	public boolean verifySubsCountDloadFile(String refdatafile, String downloadedfile) throws Exception {
		FileInputStream refExcelFile = new FileInputStream(refdatafile);
		FileInputStream downloadedExcelFile = new FileInputStream(downloadedfile);
		refExcelWBook = new XSSFWorkbook(refExcelFile);
		log.info("Referecne Excel data file found");
		downloadedExcelWBook = new XSSFWorkbook(downloadedExcelFile);
		log.info("Downloaded Excel data file found");
		refExcelWSheet = refExcelWBook.getSheet("oneraad");
		log.info("Referecne Excel work sheet found: " + refExcelWSheet);
		downloadedExcelWSheet = downloadedExcelWBook.getSheetAt(0);
		log.debug("Downloaded Excel work sheet found: " + downloadedExcelWSheet);
		boolean istrue1 = roundingValue(refExcelWSheet.getRow(2).getCell(10).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(3).getCell(1).getNumericCellValue()));
		boolean istrue2 = roundingValue(refExcelWSheet.getRow(3).getCell(10).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(4).getCell(1).getNumericCellValue()));
		boolean istrue3 = roundingValue(refExcelWSheet.getRow(4).getCell(10).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(5).getCell(1).getNumericCellValue()));
		boolean istrue4 = roundingValue(refExcelWSheet.getRow(5).getCell(10).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(6).getCell(1).getNumericCellValue()));
		boolean istrue5 = roundingValue(refExcelWSheet.getRow(6).getCell(10).getNumericCellValue()).equals(roundingValue(downloadedExcelWSheet.getRow(7).getCell(1).getNumericCellValue()));
		boolean istrue = istrue1&&istrue2&&istrue3&&istrue4&&istrue5;
		return istrue;
	}
	
	public String roundingValue(Number value) {
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.CEILING);
		String doubleval = df.format(value.doubleValue());
		return doubleval;
	}

	
	
}
