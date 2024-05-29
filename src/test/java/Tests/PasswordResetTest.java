package Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.Base;
import POM.ResetPasswordPage;

public class PasswordResetTest extends Base{
	
	ResetPasswordPage resetPage;
	
	
	
	// Verify the password reset functionality with registered email address.
	
	@Test
	public void verifyPasswordResetFunctionalityWithRegisteredEmailId() throws InterruptedException {
		
		// step1: navigate to reset password page
		
		resetPage=navigateToResetPasswordPage();
		
		// step 2: enter the registered email id
		
		resetPage.enterEmailId("kakaderahul70@gmail.com");
		resetPage.clickContinueButton();
		
		// step 3: verify the result
	
		
	}
	
	@Test
	public void verifyPasswordResetFunctionalityWithNonRegisteredEmailId() throws InterruptedException {
		
		// step1: navigate to reset password page
		
		resetPage=navigateToResetPasswordPage();
		
		// step 2: enter the registered email id
		
		resetPage.enterEmailId("fkfhkdhfhkj@gmail.com");
		resetPage.clickContinueButton();
		
		Thread.sleep(3000);
		
		// step 3: verify the result
		String actualErrorMessage=resetPage.getErrorMessage();
		String expectedErrorMessage="Warning: The E-Mail Address was not found in our records, please try again!";
	
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
		
	}
	
	//Verify the breadcrumb of the 'reset your password'.
	
	@Test
	public void verify_BreadCrumb_Of_ResetPasswordPage() throws InterruptedException {
		
		// step 1: navigate to reset password page
		resetPage=navigateToResetPasswordPage();
		
		// step 2: validate the bread crumb
		
		String[] expectedBreadCrumb={"Home","Account","Forgotten Password"};
		
		List<WebElement> breadCrumbItems=resetPage.getBreadcrumbListItems();
		
		
		for(int i=1;i<breadCrumbItems.size();i++) {
			
			Assert.assertEquals(breadCrumbItems.get(i).getText(), expectedBreadCrumb[i]);
			
		}
		
		
	}

	

}
