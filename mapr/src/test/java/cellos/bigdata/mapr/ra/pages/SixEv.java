package cellos.bigdata.mapr.ra.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class SixEv {
	WebDriver driver = null;
	public static WebElement element = null;
	public static List<WebElement> webelementlist = null;
	public static String stringelement = null;
	static Logger log = LogManager.getLogger(OneRaAD.class.getName());
	Screen s = new Screen();
	public static Pattern screenpattern = null;
	
	public SixEv(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

/*********************************************************************
 ********* Finding and returning WebElements of the Zoomdata UI ******* 
 *********************************************************************/
	public WebElement missRprtVolMax() {
		return element;
	}

}
