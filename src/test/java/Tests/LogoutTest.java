package Tests;

import org.testng.annotations.Test;

import Base.Base;
import POM.Header;
import POM.LoginPage;

public class LogoutTest extends Base{
	
	LoginPage loginpage;
	
	@Test
	public void logoutFromMyAccount() throws InterruptedException {
		
		// step 1: login the application with valid credentials
		loginpage=navigateToLoginPage();
		loginpage.loginApplication();
		
		// step 2: logout from my account option
		Header header=new Header(driver);
		header.clickLogoutOption();
		
		
	}

}
