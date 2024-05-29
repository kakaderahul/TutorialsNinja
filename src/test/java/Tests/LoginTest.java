package Tests;
import Utilities.ReadExcelUtility;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.Base;
import POM.LoginPage;



public class LoginTest extends Base {
	
	
	LoginPage loginpage;
	ReadExcelUtility read;
	
	///////////
	
	@Test(dataProvider="getData")
	public void login_With_Valid_Credentials(String email,String pwd) throws InterruptedException {
		
		// step 1: navigate to login page
		loginpage=navigateToLoginPage();
		
		// step 2: enter email and pwd and click on loginbutton
		
		loginpage.enterEmail(email);
		loginpage.enterPassword(pwd);
		loginpage.clickLogin();
		
		// step 3: validate login result
		String actualTitle=driver.getTitle();
		String expectedTitle="My Account";
		assertEquals(actualTitle, expectedTitle);
		
	}
	
	@Test(dataProvider="getData")
	public void login_With_Invalid_Creadentails(String email,String pwd) throws InterruptedException {
		
		// step 1: navigage to login page
		loginpage=navigateToLoginPage();
		
		// step 2: enter the login details 
		
		loginpage.enterEmail(email);
		loginpage.enterPassword(pwd);
		loginpage.clickLogin();
		
		// step 3:validate the result
		String actualErrorMessage=loginpage.getErrorMessageText();
		String expectedErrorMessage="Warning: No match for E-Mail Address and/or Password.";
		assertEquals(actualErrorMessage, expectedErrorMessage);
		
	}
	
	@Test
	public void checkBrokenLinks() throws IOException {
		
		List<WebElement> webLinks=driver.findElements(By.tagName("a"));
		
		for(WebElement e:webLinks) {
			
			String actual_url=e.getAttribute("href");
			
			URL url=new URL(actual_url);
			
			HttpURLConnection huc=(HttpURLConnection)url.openConnection();
			
			huc.connect();
			
			int responseStatusCode=huc.getResponseCode();
			
			if(responseStatusCode>=400) {
				
				System.out.println(actual_url+" : it is broken link");
			}
			else {
				
				System.out.println(actual_url+" : Not a broken link");
			}
				
		}
	}
	
	
   // Verify the logging , if user logging the application and click on back button.

	@Test(dataProvider="getData")
	public void verify_Login_With_Back_Button(String email,String pwd) throws InterruptedException {
		
		// step 1: navigate to login page
		loginpage=navigateToLoginPage();
		
		// step 2: enter the valid login credentials
		
		loginpage.enterEmail(email);
		loginpage.enterPassword(pwd);
		loginpage.clickLogin();
		
		// step 3: click on back button
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
		
		// step 4: click on forward button
		driver.navigate().forward();
		Thread.sleep(2000);
		
		// step 5: verify the result
		String actualPageTitle=driver.getTitle();
		String expectedPageTitle="My Account";
		
		assertEquals(actualPageTitle, expectedPageTitle);
		
		
	}
	
	
	@Test
	public void verifyPlaceHolders() throws InterruptedException {
		
		// Step 1: navigate to login page
		loginpage=navigateToLoginPage();
		
		// step 2: check the placeholder text and verify.
		
		String actualEmailPlaceholder=loginpage.getPlaceHolderText("email");
		String actualPasswordPlaceholder=loginpage.getPlaceHolderText("password");
		
		String expectedEmailPlaceholder="E-Mail Address";
		String expectedPasswordPlaceholder="Password";
		assertEquals(actualEmailPlaceholder, expectedEmailPlaceholder);
		assertEquals(actualPasswordPlaceholder, expectedPasswordPlaceholder);
	}
	
	// Verify the logging into the application using keyboard keys only(tab,enter and space)

	@Test
	public void loginToApplicationUsingKeyboardKeys() throws InterruptedException {
		
		// step 1: navigate to login page
		loginpage=navigateToLoginPage();
		
		// step 2: use tab key board key to navigate to email input field
		
		Actions action=new Actions(driver);
		
		boolean status=true;
		
		while(status){
			
			if(!loginpage.checkEmailTextBoxIsFocusOrNot()) {
				
				Thread.sleep(400);
				action.sendKeys(Keys.TAB).perform();
			}
			else {
				
				driver.switchTo().activeElement().sendKeys("kakaderahul788@gmail.com");
				status=false;
			}
			
		}
		
		status=true;
		
		while(status){
			
			if(!loginpage.checkPasswordTextBoxIsFocusedOrNot()) {
				
				Thread.sleep(400);
				action.sendKeys(Keys.TAB).perform();
			}
			else {
				
				driver.switchTo().activeElement().sendKeys("Rahul@123");
				status=false;
			}
			
		}
		
		// click enter button
		
		action.sendKeys(Keys.ENTER).perform();
			
	}
	
	@Test
	public void verify_UI_Of_LoginPage() throws InterruptedException {
		
		SoftAssert softAssert=new SoftAssert();
		
		// step 1: navigate to login page
		loginpage=navigateToLoginPage();
		
		
	
		// step 2: verify the page title.
		String expectedPageTitle="Account Login";
		String actualPageTitle=driver.getTitle();
		
		softAssert.assertEquals(actualPageTitle, expectedPageTitle,"Wrong page title....");
		
		
		softAssert.assertAll();
		
		// step 3: verify the page url
		String expectedPageUrl="https://tutorialsninja.com/demo/index.php?route=account/login";
		String actualPageUrl=driver.getCurrentUrl();
		softAssert.assertEquals(actualPageUrl, expectedPageUrl,"Wrong page url....");
		

		
		// step 4: verify the forgotten password link text
		String expectedLinkText="Forgotten Password";
		String actualLinkText=loginpage.getTextOfForgottenPasswordLink();
		
		softAssert.assertEquals(actualLinkText,expectedLinkText,"Forgotten password link text in wrong...");
		
		///
		
		softAssert.assertAll();
		
	}
	
	//Verify the logging session of the application

	
	
	
	
}
