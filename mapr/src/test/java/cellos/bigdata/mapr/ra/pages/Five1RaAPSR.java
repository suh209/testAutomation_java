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

public class Five1RaAPSR {
	WebDriver driver = null;
	public static WebElement element = null;
	public static List<WebElement> webelementlist = null;
	public static String stringelement = null;
	static Logger log = LogManager.getLogger(OneRaAD.class.getName());
	Screen s = new Screen();
	public static Pattern screenpattern = null;
	
	public Five1RaAPSR(WebDriver driver) {
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

	public WebElement missRprtTrndMax() {
		try {
			element = driver.findElement(By.xpath("//*[text()='Missing Volume Trend']/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Missing volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Missing volume maximize button element not found");
		}
		return element;
	}

	public WebElement missRprtGrpBtn() {
		try {
			element = driver.findElement(By.xpath("//div[contains(@class,'fullScreenView')]//span[contains(text(),'Group')]"));
			log.debug("Application/Protocol/Sub-protocol filter button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Application/Protocol/Sub-protocol filter element not found");
		}
		return element;
	}

	public WebElement protoBtn() {
		try {
			element = driver.findElement(By.xpath("//div[@title='Proto']"));
			log.debug("Protocol select-button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Protocol select-button element not found");
		}
		return element;
	}
	
	public WebElement subProtoBtn() {
		try {
			element = driver.findElement(By.xpath("//div[@title='Sub Proto']"));
			log.debug("Protocol select-button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Protocol select-button element not found");
		}
		return element;
	}
	
	
	
	
	
	
}
