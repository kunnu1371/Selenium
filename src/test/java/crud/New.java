package crud;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class,CustomListener.class})
public class New {

	@Test(retryAnalyzer = Retry.class)
	public void newTc1() {
		System.out.println("newTc1..............................");
		Assert.assertTrue(true);

	}
	
	@Test(retryAnalyzer = Retry.class)
	public void newTc2() {
		System.out.println("newTc2..............................");
		Assert.assertTrue(false);

	}
}
