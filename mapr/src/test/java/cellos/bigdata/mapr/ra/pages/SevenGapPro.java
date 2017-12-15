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

public class SevenGapPro {
	WebDriver driver = null;
	public static WebElement element = null;
	public static List<WebElement> webelementlist = null;
	public static String stringelement = null;
	static Logger log = LogManager.getLogger(OneRaAD.class.getName());
	Screen s = new Screen();
	public static Pattern screenpattern = null;
	
	public SevenGapPro(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

/*********************************************************************
 ********* Finding and returning WebElements of the Zoomdata UI ******* 
 *********************************************************************/
	public WebElement apnGapProfMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='APNs']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Missing volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Missing volume maximize button element not found");
		}
		return element;
	}

	public WebElement prxyIpGapProfMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='Proxy IP']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Missing volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Missing volume maximize button element not found");
		}
		return element;
	}

	public WebElement urlGapProfMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='URL']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Missing volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Missing volume maximize button element not found");
		}
		return element;
	}

	public WebElement prtoGapProfMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='Protocol / Sub-protocol/ Application']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Missing volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Missing volume maximize button element not found");
		}
		return element;
	}

	public WebElement ratGapProfMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='RAT Type']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Missing volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Missing volume maximize button element not found");
		}
		return element;
	}

	public WebElement destSrvrGapProfMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='Destination Server IP']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Missing volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Missing volume maximize button element not found");
		}
		return element;
	}

	public WebElement tacGapProfMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='TAC / UE Model']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Missing volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Missing volume maximize button element not found");
		}
		return element;
	}

	public WebElement prxyDmnGapProfMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='Proxy Domain Name']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Missing volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Missing volume maximize button element not found");
		}
		return element;
	}

	public WebElement lacGapProfMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='LAC / CELL ID']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Missing volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Missing volume maximize button element not found");
		}
		return element;
	}

	public WebElement vplmnGapProfMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='VPLMN']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Missing volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Missing volume maximize button element not found");
		}
		return element;
	}

	public WebElement siteGapProfMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='NW Sites']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Missing volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Missing volume maximize button element not found");
		}
		return element;
	}

	public WebElement revStrmGapProfMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='Revenue Streams']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Missing volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Missing volume maximize button element not found");
		}
		return element;
	}

	public WebElement gapProfGrpBtn() {
		try {
			element = driver.findElement(By.xpath("//div[contains(@class,'fullScreenView')]//*[contains(text(),'Sub Protocol')]/../../../.."));
			log.debug("Application/Protocol/Sub-protocol filter button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Application/Protocol/Sub-protocol filter element not found");
		}
		return element;
	}

	public WebElement protoBtn() {
		try {
			element = driver.findElement(By.xpath("//div[text()='Protocol']"));
			log.debug("Protocol select-button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Protocol select-button element not found");
		}
		return element;
	}
	
	public WebElement appBtn() {
		try {
			element = driver.findElement(By.xpath("//div[text()='Application']"));
			log.debug("Application select-button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Application select-button element not found");
		}
		return element;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
