package cellos.bigdata.mapr.ra.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ThreeRaAGS {
	WebDriver driver = null;
	public static WebElement element = null;
	public static List<WebElement> webelementlist = null;
	public static String stringelement = null;
	static Logger log = LogManager.getLogger(OneRaAD.class.getName());
	Screen s = new Screen();
	public static Pattern screenpattern = null;
	
	public ThreeRaAGS(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

/*********************************************************************
 ********* Finding and returning WebElements of the Zoomdata UI ******* 
 *********************************************************************/
	public WebElement apnMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'APNs')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("total volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("total volume maximize button element not found");
		}
		return element;
	}

	public WebElement siteMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'NW Sites')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("total volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("total volume maximize button element not found");
		}
		return element;
	}

	public WebElement volBucketMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Volume Buckets')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("total volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("total volume maximize button element not found");
		}
		return element;
	}

	public WebElement vplmnMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'VPLMNs')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("total volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("total volume maximize button element not found");
		}
		return element;
	}

	public WebElement revStrmMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Revenue Streams')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("total volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("total volume maximize button element not found");
		}
		return element;
	}

	public WebElement chgCharMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Charging Characteristics')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("total volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("total volume maximize button element not found");
		}
		return element;
	}

	public WebElement dimContainer() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Current')]/../../preceding-sibling::div/h3"));;
			log.debug("<volume information> web  element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<volume information> web element not found");
		}
		return element;
	}

	public WebElement valContainer() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Time Window')]/following-sibling::dt[contains(text(),'GAP Volume')]/following-sibling::dd"));;
			log.debug("<volume information> web  element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<volume information> web element not found");
		}
		return element;
	}

	public WebElement valContainerCC() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Time Window')]/following-sibling::dt[contains(text(),'GAP Volume')]/following-sibling::dd"));;
			log.debug("<volume information> web  element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<volume information> web element not found");
		}
		return element;
	}
	
	public WebElement overValContainer() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Time Window')]/following-sibling::dt[contains(text(),'Over reported volume')]/following-sibling::dd"));;
			log.debug("<volume information> web  element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<volume information> web element not found");
		}
		return element;
	}



	
	
	
	
	
	
	
	
	
	
	public Pattern misUndrApnpattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_101.png");
			log.debug("screenpattern of APN <web.gprs.mtnnigeria.net> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of APN <web.gprs.mtnnigeria.net> not found");
		}
		return screenpattern;
	}

	public Pattern misUndrSitepattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_102.png");
			log.debug("screenpattern of site <Abuja> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of site <Abuja> found");
		}
		return screenpattern;
	}

	public Pattern misUndrVolBucket1pattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_103.png");
			log.debug("screenpattern of volume bucket <3> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of volume bucket <3> not found");
		}
		return screenpattern;
	}

	public Pattern misUndrVolBucket2pattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_104.png");
			log.debug("screenpattern of volume bucket <2> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of volume bucket <2> not found");
		}
		return screenpattern;
	}

	public Pattern misUndrVolBucket3pattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_105.png");
			log.debug("screenpattern of volume bucket <1> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of volume bucket <1> not found");
		}
		return screenpattern;
	}

	public Pattern misUndrVplmnpattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_106.png");
			log.debug("screenpattern of VPLMN <62130> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of VPLMN <62130> not found");
		}
		return screenpattern;
	}

	public Pattern misUndrRevStrm1pattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_107.png");
			log.debug("screenpattern of revenue stream <Postpaid> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of revenue stream <Postpaid> not found");
		}
		return screenpattern;
	}

	public Pattern misUndrRevStrm2pattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_108.png");
			log.debug("screenpattern of revenue stream <Prepaid> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of revenue stream <Prepaid> not found");
		}
		return screenpattern;
	}

	public Pattern misUndrChgChar1pattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_109.png");
			log.debug("screenpattern of charging characteristics <1024> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of charging characteristics <1024> not found");
		}
		return screenpattern;
	}

	public Pattern misUndrChgChar2pattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_110.png");
			log.debug("screenpattern of charging characteristics <1280> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of charging characteristics <1280> not found");
		}
		return screenpattern;
	}
	
	public Pattern overApnpattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_111.png");
			log.debug("screenpattern of APN <web.gprs.mtnnigeria.net> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of APN <web.gprs.mtnnigeria.net> not found");
		}
		return screenpattern;
	}

	public Pattern overSitepattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_112.png");
			log.debug("screenpattern of site <Abuja> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of site <Abuja> found");
		}
		return screenpattern;
	}

	public Pattern overVolBucket1pattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_113.png");
			log.debug("screenpattern of volume bucket <3> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of volume bucket <3> not found");
		}
		return screenpattern;
	}

	public Pattern overVolBucket2pattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_114.png");
			log.debug("screenpattern of volume bucket <2> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of volume bucket <2> not found");
		}
		return screenpattern;
	}

	public Pattern overVolBucket3pattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_115.png");
			log.debug("screenpattern of volume bucket <1> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of volume bucket <1> not found");
		}
		return screenpattern;
	}

	public Pattern overVplmnpattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_116.png");
			log.debug("screenpattern of VPLMN <62130> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of VPLMN <62130> not found");
		}
		return screenpattern;
	}

	public Pattern overRevStrm1pattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_117.png");
			log.debug("screenpattern of revenue stream <Prepaid> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of revenue stream <Prepaid> not found");
		}
		return screenpattern;
	}

	public Pattern overRevStrm2pattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_118.png");
			log.debug("screenpattern of revenue stream <Postpaid> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of revenue stream <Postpaid> not found");
		}
		return screenpattern;
	}

	public Pattern overChgChar1pattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_119.png");
			log.debug("screenpattern of charging characteristics <1024> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of charging characteristics <1024> not found");
		}
		return screenpattern;
	}

	public Pattern overChgChar2pattern() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_120.png");
			log.debug("screenpattern of charging characteristics <1280> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of charging characteristics <1280> not found");
		}
		return screenpattern;
	}
	
	
	
	
}
