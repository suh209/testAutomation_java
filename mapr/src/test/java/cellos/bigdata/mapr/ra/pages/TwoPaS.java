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

public class TwoPaS {
	WebDriver driver = null;
	public static WebElement element = null;
	public static List<WebElement> webelementlist = null;
	public static String stringelement = null;
	static Logger log = LogManager.getLogger(OneRaAD.class.getName());
	Screen s = new Screen();
	public static Pattern screenpattern = null;
	
	public TwoPaS(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

/*********************************************************************
 ********* Finding and returning WebElements of the Zoomdata UI ******* 
 *********************************************************************/
	public WebElement configureButn() {
		try {
			log.debug("Trying to find <Configure> button"); 
			element = driver.findElement(By.xpath("//div[@class='leftPane']//*[contains(text(),'Configure')]/preceding-sibling::div"));
			log.debug("<Configure> web element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Configure> web element not found");
		}
		return element;
	}

	public WebElement removeHourButn() {
		try {
			element = driver.findElement(By.xpath("//legend[contains(text(),'Rows')]/following-sibling::ul//*[contains(text(),'File Hour')]/following-sibling::div[contains(@class,'remove')]"));;
			log.debug("<Remove Hour> web element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<Remove Hour> web element not found");
		}
		return element;
	}

	public WebElement totVolMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Total Volumes')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("total volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("total volume maximize button element not found");
		}
		return element;
	}

	public WebElement totVolZoominButton() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Total Volumes')]/../../../../..//*[contains(@class,'zoomOut')]/following-sibling::b"));
			log.debug("total volume zoomin button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.debug("total volume zoomin button element not found");
		}
		return element;
	}
	
	public WebElement freeVolMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Discounted Volumes')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("Free volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("Free volume maximize button element not found");
		}
		return element;
	}

	public WebElement freeVolZoominButton() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Discounted Volumes')]/../../../../..//*[contains(@class,'zoomOut')]/following-sibling::b"));
			log.debug("Free volume zoomin button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.debug("Free volume zoomin button element not found");
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

	public WebElement volContainer() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Time Window')]/following-sibling::dt[contains(text(),'Total Volume(GB)')]/following-sibling::dd"));;
			log.debug("<volume information> web  element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<volume information> web element not found");
		}
		return element;
	}
	
	public WebElement subsCountMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'NUMBER OF DISTINCT SUBSCRIBERS')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("total volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("total volume maximize button element not found");
		}
		return element;
	}

	public WebElement subsCountZoominButton() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'NUMBER OF DISTINCT SUBSCRIBERS')]/../../../../..//*[contains(@class,'zoomOut')]/following-sibling::b"));
			log.debug("<Subscriber Count> zoomin button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.debug("<dep> volume zoomin button element not found");
		}
		return element;
	}
	
	public WebElement subsContainer() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'Time Window')]/following-sibling::dt[contains(text(),'Total Subscriber')]/following-sibling::dd"));;
			log.debug("<volume information> web  element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("<volume information> web element not found");
		}
		return element;
	}
	
	public WebElement sessnCountMaxButn() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'NUMBER OF DISTINCT SESSIONS')]/../../../..//span[contains(@class,'fullscreen')]"));
			log.debug("total volume maximize button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("total volume maximize button element not found");
		}
		return element;
	}

	public WebElement sessnCountZoominButton() {
		try {
			element = driver.findElement(By.xpath("//*[contains(text(),'NUMBER OF DISTINCT SESSIONS')]/../../../../..//*[contains(@class,'zoomOut')]/following-sibling::b"));
			log.debug("session count zoomin button element found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.debug("session count zoomin button element not found");
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

	public Pattern depTotVolpatternOn628() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_056.png");
			log.debug("screenpattern of DEP Total volume on 28-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP Total volume on 28-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwTotVolpatternOn628() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_057.png");
			log.debug("screenpattern of NW Total volume on 28-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of NW Total volume on 28-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwTotVolpatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_058.png");
			log.debug("screenpattern of NW Total volume on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of NW Total volume on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgTotVolpatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_059.png");
			log.debug("screenpattern of CHG Total volume on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CHG Total volume on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern depTotVolpatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_060.png");
			log.debug("screenpattern of DEP Total volume on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP Total volume on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwTotVolpatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_061.png");
			log.debug("screenpattern of NW Total volume on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of NW Total volume on 30-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgTotVolpatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_062.png");
			log.debug("screenpattern of CHG Total volume on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CHG Total volume on 30-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern depTotVolpatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_063.png");
			log.debug("screenpattern of DEP Total volume on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP Total volume on 30-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwTotVolpatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_064.png");
			log.debug("screenpattern of NW Total volume on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of NW Total volume on 01-07-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgTotVolpatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_065.png");
			log.debug("screenpattern of CHG Total volume on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CHG Total volume on 01-07-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern depTotVolpatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_066.png");
			log.debug("screenpattern of DEP Total volume on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP Total volume on 01-07-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgTotVolpatternOn702() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_067.png");
			log.debug("screenpattern of CHG Total volume on 02-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CHG Total volume on 02-07-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwTotVolpatternOnwhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_068.png");
			log.debug("screenpattern of NW Total volume on whole data found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of NW Total volume on whole data not found");
		}
		return screenpattern;
	}
	
	public Pattern chgTotVolpatternOnwhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_069.png");
			log.debug("screenpattern of CHG Total volume on whole data found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CHG Total volume on whole data not found");
		}
		return screenpattern;
	}

	public Pattern depTotVolpatternOnwhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_070.png");
			log.debug("screenpattern of DEP Total volume on whole data found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP Total volume on whole data not found");
		}
		return screenpattern;
	}
	
	public Pattern depSubsCountpatternOn628() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_071.png");
			log.debug("screenpattern of DEP subscriber count on 28-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP subscriber count on 28-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwSubsCountpatternOn628() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_072.png");
			log.debug("screenpattern of NW subscriber count on 28-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of NW subscriber count on 28-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgSubsCountpatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_073.png");
			log.debug("screenpattern of CHG subscriber count on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CHG subscriber count on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern depSubsCountpatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_074.png");
			log.debug("screenpattern of DEP subscriber count on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP subscriber count on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwSubsCountpatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_075.png");
			log.debug("screenpattern of NW subscriber count on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of NW subscriber count on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgSubsCountpatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_076.png");
			log.debug("screenpattern of CHG subscriber count on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CHG subscriber count on 30-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern depSubsCountpatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_077.png");
			log.debug("screenpattern of DEP subscriber count on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP subscriber count on 30-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwSubsCountpatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_078.png");
			log.debug("screenpattern of NW subscriber count on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of NW subscriber count on 30-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgSubsCountpatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_079.png");
			log.debug("screenpattern of CHG subscriber count on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CHG subscriber count on 01-07-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern depSubsCountpatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_080.png");
			log.debug("screenpattern of DEP subscriber count on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP subscriber count on 01-07-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwSubsCountpatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_081.png");
			log.debug("screenpattern of NW subscriber count on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of NW subscriber count on 01-07-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgSubsCountpatternOn702() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_082.png");
			log.debug("screenpattern of DEP subscriber count on 02-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP subscriber count on 02-07-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgSubsCountpatternOnWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_095.png");
			log.debug("screenpattern of CHG subscriber count on whole duration found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CHG subscriber count on whole duration not found");
		}
		return screenpattern;
	}
	
	public Pattern depSubsCountpatternOnWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_096.png");
			log.debug("screenpattern of DEP subscriber count on whole duration found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP subscriber count on whole duration not found");
		}
		return screenpattern;
	}
	
	public Pattern nwSubsCountpatternOnWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_097.png");
			log.debug("screenpattern of NW subscriber count on whole duration found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of NW subscriber count on whole duration not found");
		}
		return screenpattern;
	}

	public Pattern depSessnCountpatternOn628() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_083.png");
			log.debug("screenpattern of DEP session count on 28-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP session count on 28-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwSessnCountpatternOn628() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_084.png");
			log.debug("screenpattern of NW session count on 28-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of NW session count on 28-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgSessnCountpatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_085.png");
			log.debug("screenpattern of CHG session count on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CHG session count on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern depSessnCountpatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_086.png");
			log.debug("screenpattern of DEP session count on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP session count on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwSessnCountpatternOn629() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_087.png");
			log.debug("screenpattern of NW session count on 29-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of NW session count on 29-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgSessnCountpatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_088.png");
			log.debug("screenpattern of CHG session count on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CHG session count on 30-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern depSessnCountpatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_089.png");
			log.debug("screenpattern of DEP session count on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP session count on 30-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwSessnCountpatternOn630() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_090.png");
			log.debug("screenpattern of NW session count on 30-06-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of NW session count on 30-06-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgSessnCountpatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_091.png");
			log.debug("screenpattern of CHG session count on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CHG session count on 01-07-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern depSessnCountpatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_092.png");
			log.debug("screenpattern of DEP session count on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP session count on 01-07-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern nwSessnCountpatternOn701() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_093.png");
			log.debug("screenpattern of NW session count on 01-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of NW session count on 01-07-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgSessnCountpatternOn702() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_094.png");
			log.debug("screenpattern of DEP session count on 02-07-2016 found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP session count on 02-07-2016 not found");
		}
		return screenpattern;
	}
	
	public Pattern chgSessnCountpatternOnWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_098.png");
			log.debug("screenpattern of CHG session count on whole duration found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of CHG session count on whole duration not found");
		}
		return screenpattern;
	}
	
	public Pattern depSessnCountpatternOnWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_099.png");
			log.debug("screenpattern of DEP session count on whole duration found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of DEP session count on whole duration not found");
		}
		return screenpattern;
	}
	
	public Pattern nwSessnCountpatternOnWhole() {
		try {
			screenpattern = new Pattern("c:\\Ujjwal\\Learning\\selenium_java\\workspace\\raTestAutomation\\sikuliscreenshots\\common_100.png");
			log.debug("screenpattern of NW session count on whole duration found");
		} catch (NoSuchElementException e) {
			log.trace(e);
			log.error("screenpattern of NW session count on whole duration not found");
		}
		return screenpattern;
	}
}
