package crud;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	public void onTestSuccess(ITestResult result) {

		if (result.getMethod().getRetryAnalyzer(result) != null) {
			int numberOfSkippedCases = result.getTestContext().getSkippedTests().getResults(result.getMethod()).size();

			for (int i = 0; i < numberOfSkippedCases; i++) {
				result.getTestContext().getSkippedTests().removeResult(result.getMethod());
			}
		}

	}

	public void onTestFailure(ITestResult result) {

		if (result.getMethod().getRetryAnalyzer(result) != null) {
			int numberOfSkippedCases = result.getTestContext().getSkippedTests().getResults(result.getMethod()).size();

			for (int i = 0; i < numberOfSkippedCases; i++) {
				result.getTestContext().getSkippedTests().removeResult(result.getMethod());
			}
		}

	}
}
