package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;

public class SignUpPage extends Base {
	
	WebDriver localDriver;
	
	public SignUpPage(WebDriver driver) {
		
		localDriver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath="//input[@id='input-firstname']") private WebElement firstNameInputField;
	
	@FindBy(xpath="//input[@id='input-lastname']") private WebElement lastNameInputField;
	
	@FindBy(id="input-email") private WebElement emailInputField;
	
	@FindBy(xpath="//input[@id='input-telephone']") private WebElement telephoneInputField;
	
	@FindBy(xpath="//input[@id='input-password']") private WebElement passwordInputField;
	
	@FindBy(xpath="//input[@id='input-confirm']") private WebElement conformPassword;
	
	@FindBy(xpath="//input[@name='agree']") private WebElement checkBox;
	
	@FindBy(xpath="//input[@value='Continue']") private WebElement continueButton;
	
	
	
	
	// Error Messages
	
	@FindBy(xpath="//div[contains(text(),'First Name must be between 1 and 32 characters!')]") private WebElement FirstNameErrorMessage;
	
	@FindBy(xpath="//div[contains(text(),'Password must be between 4 and 20 characters!')]") private WebElement pwdErrorMessage;
	
	@FindBy(xpath="//div[contains(text(),'Warning')]") private WebElement errorMessage;
	
	@FindBy(xpath="//div[text()='Password confirmation does not match password!']")private WebElement conformPwdErrorMessage;
	
	
	public void enterFirstName(String fname) {
		
		firstNameInputField.sendKeys(fname);
		
	}
	
	
	public void enterLastName(String lname) {
		
		lastNameInputField.sendKeys(lname);
		
	}
	
	public void enterEmailId(String emailId) {
		
		emailInputField.sendKeys(emailId);
		
	}
	
	public void enterTelephoneNumber(String number) {
		
		telephoneInputField.sendKeys(number);
		
	}
	
	
	public void enterPassword(String pwd) throws InterruptedException {
		
		moveToElement(localDriver,passwordInputField);
		Thread.sleep(2000);
		passwordInputField.sendKeys(pwd);
		
	}
	
	public void enterConformPassword(String conformpwd) throws InterruptedException {
		
		moveToElement(localDriver, conformPassword);
		Thread.sleep(2000);
		conformPassword.sendKeys(conformpwd);
		
	}
	
	public void selectCheckBox() {
		
		checkBox.click();
	}
	
	public void clickContinueButton() throws InterruptedException{
		
		moveToElement(localDriver, continueButton);
		Thread.sleep(2000);
		continueButton.click();
	}
	
	public String checkFirstNameErrorMessage() {
		
		return FirstNameErrorMessage.getText();
	}
	
	public String getEmailFieldValidationMessage() {
		
		return emailInputField.getAttribute("validationMessage");
	}
	
	public String getPasswordErrorMessage() {
		
		try {
			
			String message=pwdErrorMessage.getText();
			return message;
			
		} catch (Exception e) {
		   return null;
		}
		
	}
	
	public boolean isAvailable(String fieldName) {
		
		switch(fieldName) {
		
		case "firstNameInputField": 
				return firstNameInputField.isDisplayed();
		case "lastNameInputField":
			    return lastNameInputField.isDisplayed();
		
		default: return false;
		
		}
		
	}
	
	
	public String getErrorMessage() {
		
		return errorMessage.getText();
	}

	public String getConformPasswordErrorMessage() {
		
		return conformPwdErrorMessage.getText();
	}
	
}
