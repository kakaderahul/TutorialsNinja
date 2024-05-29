package Tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.github.javafaker.Faker;

import Base.Base;
import POM.SignUpPage;

public class SignUpTest extends Base {
	
	SignUpPage signup;
	SoftAssert softAssert;
	
	
	//////////////////////////////////
	
	
	// Test 1: Verify the UI of the registration page
	
	@Test
	public void verify_UI_Of_The_Signup_Page() throws InterruptedException {
		
		// step 1: navigate to signup page
		
		signup=navigateToSignUpPage();
		
		// step 2: verify the page title
		
		String expectedPageTitle="Register Account";
		String actualPageTitle=driver.getTitle();
		
		softAssert=new SoftAssert();
		softAssert.assertEquals(actualPageTitle, expectedPageTitle, "SignupPage: title is not correct.");
		
		// step 3: verify the page url
		String expectedPageUrl="https://tutorialsninja.com/demo/index.php?route=account/register";
		String actualPageUrl=driver.getCurrentUrl();
		softAssert.assertEquals(actualPageUrl,expectedPageUrl,"SignupPage: incorrect page url......");
		
		softAssert.assertAll();
		
	}
	
	
	// test 2: Verify that all the fields are present in the registration page as per the client requirement.

	@Test
	public void verify_Availiblilty_Of_Fields() throws InterruptedException {
		
		// step 1: navigate to signup page
		signup=navigateToSignUpPage();
		
		softAssert=new SoftAssert();
		// step 1: check the fistname field.
		boolean actualStatus=signup.isAvailable("firstNameInputField");
		boolean expectedStatus=true;
		softAssert.assertEquals(actualStatus, expectedStatus,"signuppage: firstName field is not available");
		
		// step 1: check the fistname field.
		actualStatus=signup.isAvailable("lastNameInputField");
		expectedStatus=true;
		softAssert.assertEquals(actualStatus, expectedStatus,"signuppage: firstName field is not available");
		
		softAssert.assertAll();
	}
	
	// test 3: Verify the registration functionality by providing all mandatory fields
	
	@Test(dataProvider="getData")
	public void verifySignUpWithMandetoryFields(String fname,String lname, String email,String telephone, String pwd, String conformpwd) throws InterruptedException {
		
		// step 1: navigate to signup page
		signup=navigateToSignUpPage();
		
		faker=new Faker();
		email=faker.internet().emailAddress();
	
		// step 2: enter the mandatory data.
		signup.enterFirstName(fname);
		signup.enterLastName(lname);
		signup.enterEmailId(email);
		signup.enterTelephoneNumber(telephone);
		signup.enterPassword(pwd);
		signup.enterConformPassword(conformpwd);
		signup.selectCheckBox();
		signup.clickContinueButton();
		
		// step 3: verify the result
		softAssert=new SoftAssert();
		String actualPageTitle=driver.getTitle();
		String expectedPageTitle="Your Account Has Been Created!";
		softAssert.assertEquals(actualPageTitle, expectedPageTitle,"SignUpSuccessPage: page title is wrong...");
		softAssert.assertAll();
	}

	@Test
	public void verify_SignUp_Without_Any_Data() throws InterruptedException {
		
		// Step 1: navigate to signup page
		signup=navigateToSignUpPage();
		
		// step 2: Keep all fields empty and click on continue button.
		signup.clickContinueButton();
		
		// step 3: verify result
		String actualErrorMessage=signup.getErrorMessage();
		String expectedErrorMessage="Warning: You must agree to the Privacy Policy!";
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
		
	}
	
	// test 5: Verify the registration functionality with invalid email ids.

	@Test(dataProvider="getData")
	public void verify_SignUp_With_Invalid_EmailIds(String email) throws InterruptedException {
		
		// step 1: navigate to signup page
		signup=navigateToSignUpPage();
		
		// step 2: enter the invalid email ids.
		signup.enterEmailId(email);
		signup.clickContinueButton();
		
		// step 3: verify the result
		System.out.println(signup.getErrorMessage());
		
	}
	
	// test 6: Verify the registration functionality using already registered email id.

	@Test
	public void verify_Signup_With_Registered_EmailId() throws InterruptedException {
		
		// step 1: navigate to sign up page
		signup=navigateToSignUpPage();
		
		// step 2: registered with already registered email id
		signup.enterFirstName("Rahul");
		signup.enterLastName("Kakade");
		signup.enterEmailId("kakaderahul70@gmail.com");
		signup.enterTelephoneNumber("4394739489");
		signup.enterPassword("12345");
		signup.enterConformPassword("12345");
		signup.selectCheckBox();
		signup.clickContinueButton();
		
		// step 3: verify result
		String actualErrorMessage=signup.getErrorMessage();
		String expectedErrorMessage="Warning: E-Mail Address is already registered!";
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
	}
	
	// test 7: Verify the registration functionality while password and conform password fields by providing non matching data

	@Test
	public void verify_pwd_ConformPwd_Fields_Validation_With_Non_Matching_Password() throws InterruptedException {
		
		// step 1: navigate to signup page
		signup=navigateToSignUpPage();
		
		// step 2: enter the pwd and conform password , click continue button
		
		signup.enterPassword("12345");
		signup.enterConformPassword("32321");
		signup.clickContinueButton();
		
		// step 3: verify the result
		System.out.println(signup.getConformPasswordErrorMessage());
	}

	// test 8: Verify the registration functionality while privacy policy check box unchecked.

	@Test
	public void verify_SignUp_Without_Selecting_privacyPolicy_Checkbox() throws InterruptedException {
		
		// step 1: navigate to sign up
		signup=navigateToSignUpPage();
		
		// step 2: fill the form without selecting the privacy policy check box
		
		signup.enterFirstName("Rahul");
		signup.enterLastName("Kakade");
		signup.enterEmailId("kafdfdslfhk23470@gmail.com");
		signup.enterTelephoneNumber("4394739489");
		signup.enterPassword("12345");
		signup.enterConformPassword("12345");
		signup.clickContinueButton();
		
		// step 3: verify the result
		System.out.println(signup.getErrorMessage());
		
		
	}
	
	// check the broken links in the sign up page
	
	@Test
	public void checkBrokenLinks() throws InterruptedException, IOException {
		
		// step 1: navigate to signup page
		signup=navigateToSignUpPage();
		
		// step 2: check broken links
		
		List<WebElement> links=driver.findElements(By.tagName("a"));
		
		
		for(WebElement link:links) {
			
		String actualLink=link.getAttribute("href");
		
		URL url=new URL(actualLink);
		
		HttpURLConnection huc=(HttpURLConnection)url.openConnection();
		huc.connect();
		
		int statusCode=huc.getResponseCode();
		
		if(statusCode<400) {
			
			System.out.println(actualLink+ "   "+statusCode);
			
		}
			
			
		}
		
		
	}
	
	
	
}
