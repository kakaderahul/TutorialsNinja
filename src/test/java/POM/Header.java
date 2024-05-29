package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {

	WebDriver localDriver;
	
	public Header(WebDriver driver) {
		
		localDriver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//li[@class='dropdown'])[1]") private WebElement MyAccoutDropDown;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']/li/a[contains(text(),'Login')]") private WebElement LoginOption;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']/li/a[contains(text(),'Register')]") private WebElement RegisterOption;
	
	@FindBy(xpath="//ul[@class='list-inline']/li[2]/ul/li[5]") private WebElement MyAccountlogoutOption;
	
	public void clickMyAccountDropDown() {
		
		MyAccoutDropDown.click();
		
	}
	
	public void clickLoginOption() {
		
		LoginOption.click();
		
	}
	
	public void clickRegisterOption() {
		
		RegisterOption.click();
		
	}
	
	public void clickLogoutOption() throws InterruptedException {
		MyAccoutDropDown.click();
		Thread.sleep(1000);
		MyAccountlogoutOption.click();
		
	}
	
}
