import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\gautam.kunal\\eclipse-workspace\\DemoSelenium\\src\\test\\resources\\webdriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//        WebDriverWait wait = new WebDriverWait(driver,30);

//        String alertMessage = "";
//        driver.get("http://jsbin.com/usidix/1");
//        driver.findElement(By.cssSelector("input[value=\"Go!\"]")).click();
//        alertMessage = driver.switchTo().alert().getText();
//        driver.switchTo().alert().accept();
//        System.out.println(alertMessage);

//        driver.get("https://hiring.naukri.com/hiring/job-listing");
//        driver.manage().window().maximize();
//        driver.findElement(By.id("loginRegTab")).click();
//        driver.findElement(By.name("username")).sendKeys("ansh.makker@naukri.com");
//        driver.findElement(By.name("password")).sendKeys("P@ssw0rd");
//        driver.findElement(By.xpath("//button[@type='submit']")).click();

		driver.get("https://the-internet.herokuapp.com/drag_and_drop");
		Actions actions = new Actions(driver);
		WebElement from = driver.findElement(By.id("column-a"));
		WebElement to = driver.findElement(By.id("column-b"));
		actions.dragAndDrop(from, to).perform();
		
		if (from.getText().equals("B")) {
			System.out.println("PASS: File is dropped to target as expected");
		} else {
			System.out.println("FAIL: File couldn't be dropped to target as expected");
		}
		driver.quit();

	}
}
