package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;

public class LoginPage extends Base {

	WebDriver localDriver;
	
	public LoginPage(WebDriver driver) {
		
		localDriver=driver;
		
		PageFactory.initElements(driver, this);
		
		
	}
	
	
	
	@FindBy(xpath="//input[@id='input-email']") private WebElement emailTextBox;
	
	@FindBy(xpath="//input[@id='input-password']") private WebElement PasswordTextBox;
	
	@FindBy(xpath="//input[@class='btn btn-primary']") private WebElement LoginBtn;
	
	@FindBy(xpath="//div[contains(text(),'Warning')]") private WebElement errorMessage;
	
	@FindBy(xpath="(//a[contains(text(),'Forgotten Password')])[1]") private WebElement forgotten_Password_Link;
	
	public void enterEmail(String email) {
		
		emailTextBox.sendKeys(email);
		
	}
	
	public void enterPassword(String pwd)
	{
		
		PasswordTextBox.sendKeys(pwd);
		
		
	}
	
	public void clickLogin() {
		
		LoginBtn.click();
	}
	
	public String getErrorMessageText() {
		
		return errorMessage.getText();
	}
	
	public String getPlaceHolderText(String fieldName) {
		
		if(fieldName.equals("email"))
			return emailTextBox.getAttribute("placeholder");
		else if(fieldName.equals("password"))
			return PasswordTextBox.getAttribute("placeholder");
		else
			return null;
	}
	
	public boolean checkEmailTextBoxIsFocusOrNot() {
		
		boolean focusedStatus=emailTextBox.equals(localDriver.switchTo().activeElement());
		
		return focusedStatus;
	}
	
	public boolean checkPasswordTextBoxIsFocusedOrNot() {
		
		boolean focusedStatus=PasswordTextBox.equals(localDriver.switchTo().activeElement());
		
		return focusedStatus;
	}
	
	public String getTextOfForgottenPasswordLink() {
		
		return forgotten_Password_Link.getText();
	}
	
	public void clickForgetPasswordLink() {
		forgotten_Password_Link.click();
		
	}
	
	public void loginApplication() {
		
		String email=properties.getProperty("LoginEmail");
		String pwd=properties.getProperty("LoginPassword");
		emailTextBox.sendKeys(email);
		PasswordTextBox.sendKeys(pwd);
		LoginBtn.click();
	}
	
}
