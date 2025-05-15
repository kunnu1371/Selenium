package annotation;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Annotations {

	@BeforeMethod
	void login() {
		System.out.println("This is Login......");
	}
	
	@AfterMethod
	void logout() {
		System.out.println("This is Logout......");
	}
	
	@BeforeTest
	void beforeTest() {
		System.out.println("This is beforeTest......");
	}
	
	@AfterTest
	void afterTest() {
		System.out.println("This is afterTest......");
	}
	
	@BeforeSuite
	void beforeSuite() {
		System.out.println("This is beforeSuite......");
	}
	
	@AfterSuite
	void afterSuite() {
		System.out.println("This is afterSuite......");
	}
	
	@BeforeClass
	void beforeClass() {
		System.out.println("This is beforeClass......");
	}
	
	@AfterClass
	void afterClass() {
		System.out.println("This is afterClass......");
	}
	
	@Test(priority = 1)
	void search() {
		System.out.println("This is Search......");
	}
	
	@Test(priority = 2)
	void advSearch() {
		System.out.println("This is Advance Search......");
	}
	
}
