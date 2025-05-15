package crud;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class TakeScreenShot {
	public static void screenShot(WebDriver driver, String filename) {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(source, new File(System.getProperty("user.dir") + "\\src\\test\\resources\\ScreenShot\\" + filename + ".png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
}
