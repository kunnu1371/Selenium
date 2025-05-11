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

public class C2 {

	
	@AfterTest
	void logout() {
		System.out.println("This is afterTest......");
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
	void test() {
		System.out.println("Test from C2......");
	}
	
}
