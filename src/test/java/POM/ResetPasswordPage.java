package POM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPasswordPage {
	
	WebDriver localDriver;
	
	public ResetPasswordPage(WebDriver driver) {
		localDriver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(id="input-email") private WebElement email;
	
	@FindBy(css=".btn-primary") private WebElement continueButton;
	
	@FindBy(xpath="//div[contains(text(),'Warning')]") private WebElement errorMessage;
	
	@FindBy(xpath="//ul[@class='breadcrumb']/li") private List<WebElement> breadCrumbListItems;
	
	
	public void enterEmailId(String emailId) {
		
		email.sendKeys(emailId);
		
	}
	
	public void clickContinueButton() {
		
		continueButton.click();
	}
	
	public String getErrorMessage() {
		
		return errorMessage.getText();
	}
	
	public List<WebElement> getBreadcrumbListItems() {
		
		return breadCrumbListItems;
	}
	

}
