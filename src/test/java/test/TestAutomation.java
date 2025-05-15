package test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAutomation {

	
//	1. Go to https://www.crossword.in/
//		2. In search input box - type  ‘manifest’.
//		3. Wait for Top Products to come up and click on View More.
//		4. Sort by - Low to high
//		5. Assert - all the books displayed are in ascending order of the prices till the end of scroll. (non discounted price)
//		6. Assert - set of book names which has discounted prices
//		7. Apply Price filter - 600-900
//		8. Assert - the filter is being applied in UI
//		9. Assert - all books displayed are within the price filter. (non discounted price)
		
		
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.out.println("Setup is running");
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\gautam.kunal\\eclipse-workspace\\DemoSelenium\\src\\test\\resources\\webdriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void close() {
		driver.quit();
	}

//	1. Go to https://www.crossword.in/
//		2. In search input box - type  ‘manifest’.
//		3. Wait for Top Products to come up and click on View More.
//		4. Sort by - Low to high
//		5. Assert - all the books displayed are in ascending order of the prices till the end of scroll. (non discounted price)
//		6. Assert - set of book names which has discounted prices
//		7. Apply Price filter - 600-900
//		8. Assert - the filter is being applied in UI
//		9. Assert - all books displayed are within the price filter. (non discounted price)
//		
//		
	@Test
	public void test1() throws InterruptedException {
		System.out.println("test1 is running");

		driver.get("https://www.crossword.in/");
		driver.findElement(By.name("q")).sendKeys("manifest");
		driver.findElement(By.xpath("//button[contains(text(),'View More')]")).click();
		System.out.println("Clicked on View more button");
		driver.findElement(By.className("wizzy-common-select-label")).click();
		driver.findElement(By.xpath("//div[contains(@title,'Price: Low to High')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='wizzy-common-select-selectedItem']")).getText()
				.contains("Price: Low to High"), "Sorting from low to high isn't applied");
		System.out.println("Sorted Low to High");
		Thread.sleep(2000);
//		System.out.println(driver.findElement(By.xpath("//div[@class='wizzy-product-item-price ']")).getText());
		List<WebElement> booksPrice = driver.findElements(By.xpath("//div[@class='wizzy-product-item-price ']"));

		System.out.println("Total books: " + booksPrice.size());

		for (int i = 0; i < booksPrice.size(); i++) {
			System.out.println(booksPrice.get(i).getText());
		}

		for (int i = 0; i < booksPrice.size() - 1; i++) {
			try {
				WebElement element1 = booksPrice.get(i);
				WebElement element2 = booksPrice.get(i + 1);
				String price1 = element1.getText();
				String price2 = element2.getText();
				if (!price1.contains("%") && !price2.contains("%") && price1.length() > 0 && price2.length() > 0) {
					price1 = price1.substring(1, price1.length());
					price2 = price2.substring(1, price2.length());
//					System.out.println("price1: " + price1);
//					System.out.println("price2: " + price2);
					Assert.assertTrue(Integer.parseInt(price1) <= Integer.parseInt(price2),
							"Books Price is not sorted in ascending order");
				}
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Books are sorted in ascending order.......");

		driver.findElement(By.xpath("//span[@class='facet-head-title' and contains(text(),'Price')]")).click();
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		WebElement leftSliderElement = driver.findElement(By.xpath("//div[@class='noUi-touch-area']"));
		WebElement rightSliderElement = driver.findElement(By.xpath("(//div[@class='noUi-touch-area'])[2]"));
		action.dragAndDropBy(leftSliderElement, 65, 0).perform();
		action.dragAndDropBy(rightSliderElement, -16, 0).perform();
		Thread.sleep(2000);
		System.out.println("Price filter applied.......");

		booksPrice = driver.findElements(By.xpath("//div[@class='wizzy-product-item-price ']"));

		for (int i = 0; i < booksPrice.size(); i++) {
			System.out.println(booksPrice.get(i).getText());
		}

		for (int i = 0; i < booksPrice.size(); i++) {
			try {
				WebElement element1 = booksPrice.get(i);
				String price1 = element1.getText();
				if (!price1.contains("%") && price1.length() > 0) {
					price1 = price1.substring(1, price1.length());
					System.out.println("actual price: " + price1);
					Assert.assertTrue(Integer.parseInt(price1) >= 600 && Integer.parseInt(price1) <= 900,
							"Price is out of range applied");
				}
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
			}
		}

	}
}
