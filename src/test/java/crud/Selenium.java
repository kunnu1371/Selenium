package crud;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Selenium {

	WebDriver driver;


	@BeforeMethod
	public void tearUp() {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\gautam.kunal\\eclipse-workspace\\DemoSelenium\\src\\test\\resources\\webdriver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			TakeScreenShot.screenShot(driver, result.getName());
		}
		driver.quit();
	}

	@Test
	public void automation() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

//        String alertMessage = "";
//        driver.get("http://jsbin.com/usidix/1");
//        driver.findElement(By.cssSelector("input[value=\"Go!\"]")).click();
//        alertMessage = driver.switchTo().alert().getText();
//        driver.switchTo().alert().accept();
//        System.out.println(alertMessage);

//		driver.get("https://hiring.naukri.com/hiring/job-listing");
//		driver.manage().window().maximize();
//		driver.findElement(By.xpath("//div[text()='Register/Log in']")).click();
//		driver.findElement(By.name("username")).sendKeys("ansh.makker@naukri.com");
//		driver.findElement(By.name("password")).sendKeys("rT7@fdL2d");
//		driver.findElement(By.xpath("//button[@type='submit']")).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dash_top")));
//		String title = driver.getTitle();
//		
//		System.out.println(title);
//		Assert.assertEquals(title, "My Naukri For Recruiters");
//
////		driver.get("https://the-internet.herokuapp.com/drag_and_drop");
////		Actions actions = new Actions(driver);
////		WebElement from = driver.findElement(By.id("column-a"));
////		WebElement to = driver.findElement(By.id("column-b"));
////		actions.dragAndDrop(from, to).perform();
////		
////		if (from.getText().equals("B")) {
////			System.out.println("PASS: File is dropped to target as expected");
////		} else {
////			System.out.println("FAIL: File couldn't be dropped to target as expected");
////		}
////		driver.quit();
        
//        driver.get("https://stablebonds.in/");
//        String text = driver.findElement(By.xpath("(//h4[@data-single-line-target='title' and contains(text(), 'Krazy')])[2]")).getText();
//        Assert.assertEquals(text, "Krazybee Dec'25");
//        driver.findElement(By.xpath("(//span[text()='Know more'])[8]")).click();
//        Thread.sleep(2000);
//        String title = driver.getTitle();
//        
//        Assert.assertEquals(title, "Invest in Krazybee Services Private Limited Bonds - High Returns | StableBonds");
         driver.get("https://stablemoney.in/");
         driver.manage().window().maximize();
         driver.findElement(By.xpath("//button[text()='Login/Register']")).click();
         driver.findElement(By.name("mobileNumber")).sendKeys("9650543482");
         
	}
}	
