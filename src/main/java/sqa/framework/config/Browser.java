package sqa.framework.config;

import org.openqa.selenium.WebDriver;
/**
 * 
 * @author Ali Faizi
 *
 */
public interface Browser {
	
	/**
	 * Returning WebDriver one Implemented with any of browser types. 
	 * @param url URI should pass to open 
	 * @return
	 */
	WebDriver openBrowser(String url);

}
