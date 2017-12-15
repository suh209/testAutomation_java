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

public class FourRaAApnR {
	WebDriver driver = null;
	public static WebElement element = null;
	public static List<WebElement> webelementlist = null;
	public static String stringelement = null;
	static Logger log = LogManager.getLogger(OneRaAD.class.getName());
	Screen s = new Screen();
	public static Pattern screenpattern = null;
	
	public FourRaAApnR(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

/*********************************************************************
 ********* Finding and returning WebElements of the Zoomdata UI ******* 
 *********************************************************************/
	public WebElement missRprtVolMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='Missing Volume']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Missing volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Missing volume maximize button element not found");
		}
		return element;
	}

	public WebElement missTrndVolMax() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Missing Volume Trend')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Missing volume trend maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Missing volume trend maximize button element not found");
		}
		return element;
	}

	public WebElement underRprtVolMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='Under reported Volume']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Under-reported volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Under-reported volume maximize button element not found");
		}
		return element;
	}

	public WebElement underTrndVolMax() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Under reported Volume Trend')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Under-reported volume trend maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Under-reported volume trend maximize button element not found");
		}
		return element;
	}

	public WebElement overRprtVolMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='Over reported Volume']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Over-reported volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Over-reported volume maximize button element not found");
		}
		return element;
	}

	public WebElement overTrndVolMax() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Over reported Volume Trend')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Over-reported volume trend maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Over-reported volume trend maximize button element not found");
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
	

	
	
	
	
	
	
	
	public Pattern misUndrOvrpatternWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_123.png");
			log.debug("screenpattern of APN <web.gprs.mtnnigeria.net> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of APN <web.gprs.mtnnigeria.net> not found");
		}
		return screenpattern;
	}
	
	public Pattern misUndrOvrpatternScrlDn() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_124.png");
			log.debug("screenpattern of APN <web.gprs.mtnnigeria.net> found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of APN <web.gprs.mtnnigeria.net> not found");
		}
		return screenpattern;
	}
	
	

}
