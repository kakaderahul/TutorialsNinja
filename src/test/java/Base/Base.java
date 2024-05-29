package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import com.github.javafaker.Faker;
import com.google.common.io.Files;

import POM.Header;
import POM.LoginPage;
import POM.ResetPasswordPage;
import POM.SignUpPage;
import Utilities.ReadExcelUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	// ctrl+shift+f  // indentation
	
	public static WebDriver driver;
	public static Properties properties;
	public Header header;
	public Actions action;
	public ReadExcelUtility read;
	public static Faker faker;

	
	/////////////// setUp and tear down methods............
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws IOException {

		properties = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/files/config.properties");
		properties.load(fis);

		String BrowserName = properties.getProperty("BrowserName");

		switch (BrowserName) {

		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("invalid browser name");

		}

		String BaseUrl = properties.getProperty("BaseUrl");
		driver.get(BaseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {

		Thread.sleep(3000);
		driver.quit();

	}

	/////////////// Navigate Methods ................
	
	public LoginPage navigateToLoginPage() throws InterruptedException {

		header = new Header(driver);
		header.clickMyAccountDropDown();
		Thread.sleep(2000);
		header.clickLoginOption();
		return new LoginPage(driver);
	}

	public SignUpPage navigateToSignUpPage() throws InterruptedException {

		header = new Header(driver);
		header.clickMyAccountDropDown();
		Thread.sleep(2000);
		header.clickRegisterOption();

		return new SignUpPage(driver);
	}
	
	public ResetPasswordPage navigateToResetPasswordPage() throws InterruptedException {
		
		navigateToLoginPage().clickForgetPasswordLink();
		
		return new ResetPasswordPage(driver);
		
		
	}
	
	///////////////////// Screen shot method................

	public String takeScreenshot(String methodName) throws IOException {

		TakesScreenshot sc = (TakesScreenshot) driver;
		File src = sc.getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir") + "/target/ScreenShot/" + methodName + ".png";
		File dest = new File(destPath);
		Files.copy(src, dest);
		return destPath;
	}

	//////////////////////// action methods.

	public void moveToElement(WebDriver driver, WebElement e) {
		action = new Actions(driver);
		action.moveToElement(e).perform();
	}

	
	
	/////////////////////////////// explicit wait methods.
	
	
	

///////////////////...............  Data provider  .............//////////////

	@DataProvider
	public Object[][] getData(Method method) throws IOException {

		if (method.getName().equalsIgnoreCase("login_With_Valid_Credentials")) {

			read = new ReadExcelUtility();
			return read.readData("ValidLoginCredentials");
			
		} else if (method.getName().equalsIgnoreCase("verify_Login_With_Back_Button")) {

			read = new ReadExcelUtility();
			return read.readData("ValidLoginCredentials");

		}  else if (method.getName().equalsIgnoreCase("login_With_Invalid_Creadentails")) {

			read = new ReadExcelUtility();
			return read.readData("InvalidLoginCredential");

		}   else if (method.getName().equalsIgnoreCase("verifySignUpWithMandetoryFields")) {

			read = new ReadExcelUtility();
			return read.readData("ValidSignup");

		} else if (method.getName().equalsIgnoreCase("verify_SignUp_With_Invalid_EmailIds")) {

			read = new ReadExcelUtility();
			return read.readData("InvalidEmailIds");

		} else {
			System.out.println("invalid method details...");
			
			return null;
		}

	}
	
	
	

}
