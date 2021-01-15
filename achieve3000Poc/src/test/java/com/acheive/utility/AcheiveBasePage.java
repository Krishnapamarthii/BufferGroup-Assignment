package com.acheive.utility;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

public class AcheiveBasePage {
	WebDriver driver;
	String autUrl;
	FrameworkUtility util=new FrameworkUtility();
	public AcheiveBasePage() throws FileNotFoundException, IOException
	{
		autUrl=util.getProperty("url");
		driver=MyDriverFactory.getWebDriver();
		driver.get(autUrl);
		driver.manage().window().maximize();
	}
	

}
