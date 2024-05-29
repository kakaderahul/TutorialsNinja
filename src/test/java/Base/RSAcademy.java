package Base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RSAcademy {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
	  Thread.sleep(3000);
	  driver.quit();
	}

	@Test
	public void radioButtonTest() throws InterruptedException {
		
	List<WebElement> radioButtons=	driver.findElements(By.xpath("//label"));
		System.out.println(radioButtons.size());
	for(WebElement e:radioButtons) {
		
		if(e.getText().equals("Radio2"))
		 e.findElement(By.xpath("//input")).click();
		
		 Thread.sleep(2000);
		
	}
	
	
	
	}
	
	
	
	
	
}
