package com.qa.opencarts.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Kartik Badnore
 *
 */
public class DriverFactory {
	WebDriver driver;
	Properties prop;
	public static String highlight = null;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * 
	 * @param Will accept string as browserName
	 * @return This method will return the driver
	 */
	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser");
		OptionsManager optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
		System.out.println("Browser Name : " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		}
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		if (browserName.equalsIgnoreCase("safari")) {

			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * 
	 * @return This will return the object of properties
	 */
	public Properties init_prop() {
		FileInputStream ip = null;
		prop = new Properties();
		String env = System.getProperty("env");

		System.out.println("ENVIROMENT ---> " + env);
		try {

			if (env == null) {
				ip = new FileInputStream("./src/test/resources/config/prod-config.properties");

			} else {
				switch (env) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa-config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev-config.properties");
					break;

				default:
					break;
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("FILENOTFOUND");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * @author Kartik
	 * @param No param needs
	 * @return this will return the File Path of the Screenshot captured
	 */
	public static String getScreenShot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
