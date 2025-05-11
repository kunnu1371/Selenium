package crud;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println("Test Started: " + result.getName());
		System.out.println(result.getTestContext().getSkippedTests().getResults(result.getMethod()));
		System.out.println(result.getTestContext().getSkippedTests().getResults(result.getMethod()).size());

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test passed: " + result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		System.out.println("Test failed: " + result.getName());

	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped: " + result.getName());
	}

	public void onStart(ITestContext context) {
		System.out.println("Test Suite Started: " + context.getName());
	}

	public void onFinish(ITestContext context) {
		System.out.println("Test Suite Finished: " + context.getName());
	}
}
