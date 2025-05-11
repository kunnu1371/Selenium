package annotation;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import crud.Retry;

public class T3 {

	
	
	@Test(priority = 1,retryAnalyzer = Retry.class)
	void test() {
		System.out.println("Test T3");
		Assert.assertTrue(false);
	}
	
}
