package com.acheive.pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.acheive.utility.AcheiveBasePage;
import com.acheive.utility.FrameworkUtility;
import com.acheive.utility.MyDriverFactory;

public class OurPartner extends AcheiveBasePage
{
	String acceptAllCookies="//button[text()='Accept All Cookies']";
	String btnHamburger="(//button[@class=\"navbar-toggler collapsed\"])[1]";
	String linkOurPartner="//a[text()='Our Partners']";
	String logoContentFull="//h3/../parent::div[@class='col-12 col-md-9 news-list-post article']/..";	
	
	WebDriver driver;
	SoftAssert asserts= new SoftAssert();
	FrameworkUtility utils=new FrameworkUtility();
	
	OurPartner() throws FileNotFoundException, IOException
	{
		super();		
	}
@Test
	void automateOurPartner() throws FileNotFoundException, IOException, InterruptedException
	{	
	
	List<String> listOfPartnerNames=new ArrayList<String>();
	driver=MyDriverFactory.getWebDriver();
	if(utils.elementExist(acceptAllCookies)) {		
		Thread.sleep(3000);
		utils.jsClick(utils.getWebElement(acceptAllCookies, "xpath"));   //Clicking in Accept all cookies
	}
	utils.jsClick(utils.getWebElement(btnHamburger, "xpath"));
	utils.getWebElement(linkOurPartner, "xpath").click();
	listOfPartnerNames=VerifyOurClientPage();	
	System.out.println("Execution Complete");
	
	}
	List<String> VerifyOurClientPage() throws FileNotFoundException, IOException
	{		
	List<String> logoHeaders=new ArrayList<String>();
	List<WebElement> listOfElementsOurPartners=driver.findElements(By.xpath(logoContentFull));
	int partnerCount=1;
	for(WebElement element:listOfElementsOurPartners)
	{
		utils.scrollIntoTheElement(element);
		
		//Verification of header
		String headerPartner=element.findElement(By.tagName("h3")).getText();
		if(headerPartner.equals(""))
		  asserts.assertTrue(false);
		System.out.println(partnerCount++ +" Partner Name="+headerPartner);	
		

		//Verification of header and text description
		String paragraph=element.findElement(By.tagName("p")).getText();
		if(paragraph.equals(""))
			asserts.assertTrue(false);
		
		//Verification of Logo
		element=element.findElement(By.tagName("img"));
		String logoHyperlink=element.getAttribute("src");		
		if(logoHyperlink.equals(""))			
			asserts.assertTrue(false);
		
		logoHeaders.add(headerPartner);		
	}
	return logoHeaders;
	}
	@AfterSuite
	public void tearDown()
	{
		asserts.assertAll();
		driver.quit();
	}
	
}


