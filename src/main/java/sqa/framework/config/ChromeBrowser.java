package sqa.framework.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeBrowser implements Browser {
	private boolean headless = false;

	public ChromeBrowser(boolean headless) {
		this.headless = headless;
	}

	@Override
	public WebDriver openBrowser(String url) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		if (this.headless) {
			options.addArguments("--headless");
		}
		WebDriver driver = new ChromeDriver(options);
		driver.get(url);
		return driver;
	}

}
