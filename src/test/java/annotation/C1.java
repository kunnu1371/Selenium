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

public class C1 {

	@BeforeTest
	void login() {
		System.out.println("This is beforeTest......");
	}
	
	@Test
	void test() {
		System.out.println("Test from C1......");
	}
		
}
