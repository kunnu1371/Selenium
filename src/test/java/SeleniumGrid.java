import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SeleniumGrid {
	WebDriver driver;
	String baseUrl, nodeURL;

	String hubUrl = "http://192.168.1.7:4444/";

	@Test
	public void setupGrid() {
		System.out.println("Setting up Grid.................");
		DesiredCapabilities ds = new DesiredCapabilities();
//		ds.setCapability("browserName", "chrome");
		ds.setBrowserName("firefox");
		ds.setPlatform(Platform.MAC);
		try {
			driver = new RemoteWebDriver(new URL(hubUrl), ds);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		driver.manage().window().maximize();
		driver.get("https://recruit.naukri.com");
		System.out.println(driver.getTitle());
		driver.findElement(By.id("loginRegTab")).click();
		driver.findElement(By.name("username")).sendKeys("ansh.makker@naukri.com");
		driver.findElement(By.name("password")).sendKeys("P@ssw0rd");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.get("https://hiring.naukri.com/hiring/job-listing");
		System.out.println(driver.getTitle());
		driver.quit();
	}

}