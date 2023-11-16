package sqa.framework.base;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import sqa.framework.config.ChromeBrowser;
import sqa.framework.config.EdgeBrowser;
import sqa.framework.utilities.DatabaseUtility;
import sqa.framework.utilities.ReadYamlFiles;

public class BaseSetup {

	// Encapsulated property.
	private static WebDriver driver;

	private final ReadYamlFiles environmentVariables;

	// ready property file (env_config yaml file).
	public BaseSetup() {
		String configFilePath = System.getProperty("user.dir") + "/src/main/resources/configuration/env_configs.yml";
		// handle exception might throw by the method.
		try {
			this.environmentVariables = ReadYamlFiles.getInstance(configFilePath);
		} catch (FileNotFoundException ex) {
			System.out.println("Exception while reading Yaml File. posible path problem check the directory.");
			throw new RuntimeException("Unable to Load environment variables." + ex.getMessage());
		}
	}

	public void setupBrowser() {
		HashMap<String, Object> uiVariables = environmentVariables.getProprty("ui");
		String url = uiVariables.get("url").toString();

		switch (uiVariables.get("browser").toString().toLowerCase()) {
		case "chrome":
			boolean headless = (boolean) uiVariables.get("headless");

			ChromeBrowser chrome = new ChromeBrowser(headless);
			driver = chrome.openBrowser(url);
			break;
		case "edge":
			EdgeBrowser edge = new EdgeBrowser();
			driver = edge.openBrowser(url);
			break;
		default:
			throw new RuntimeException("Env Config have wrong browser Type check Env Config");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	public void quitBrowser() {
		try {
			if (driver != null) {
				driver.quit();
				System.out.println("Browser quit successfully.");
			}
		} catch (Exception e) {
			System.err.println("Error while quitting the browser: " + e.getMessage());
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public DatabaseUtility getDbConnection() {
		HashMap<String, Object> dbVariable = environmentVariables.getProprty("db");
		String url = (String) dbVariable.get("url");
		String username = (String) dbVariable.get("username");
		String password = dbVariable.get("password").toString();
		return new DatabaseUtility(url, username, password);
	}

}
