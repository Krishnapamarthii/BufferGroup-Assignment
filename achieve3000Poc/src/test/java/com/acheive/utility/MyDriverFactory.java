package com.acheive.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyDriverFactory {
	private static WebDriver driver=null;
	private static MyDriverFactory driverFactoryInstance=null;
	private MyDriverFactory(String browserType)
	{
		String driverPath;
		if(browserType.toLowerCase().equals("chrome"))
		{
		driverPath=System.getProperty("user.dir")+File.separator+"Ext"+File.separator+"chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		}
	}
	public static WebDriver getWebDriver() throws FileNotFoundException, IOException {
		FrameworkUtility utility=new FrameworkUtility();
		String browserType=utility.getProperty("browserType");
		if(driverFactoryInstance==null)
			driverFactoryInstance=new MyDriverFactory(browserType);
		return driver;		
	}

}
