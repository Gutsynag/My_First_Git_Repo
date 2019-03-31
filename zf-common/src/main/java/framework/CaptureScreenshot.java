package framework;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


/**
 * This class help to create method for taking screenshot
 */
public class CaptureScreenshot {


	/**
	 * This method will create a directory and return the
	 * directory path
	 * 
	 * @return directory path
	 */
	private static String loadScreenShotFolder() {

		String path=null;
		
		File reportsfolder = new File(EnvironmentManager.getScreenShotPath());
		if (reportsfolder.exists()) {
			path=reportsfolder.getAbsolutePath()+EnvironmentManager.getCommonSlash();
		} else {
			reportsfolder.mkdir();
			path=reportsfolder.getAbsolutePath()+EnvironmentManager.getCommonSlash();
		}
		return path;
	}

	/**
	 * This method will take the path and append the screen shots folder
	 * destination
	 * 
	 * @return the screenshot name with path
	 */
	public String captureScreenshot(String fileName) {


		String file=loadScreenShotFolder()+fileName+EnvironmentManager.getDotPng();
		File srcFile=((TakesScreenshot) DriverManager.getDriverInstance()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(file));
			TestLogger.fileInfo(file);
		} 
		catch (IOException e) {
			TestLogger.errorMessage(e.getMessage());
		}
		return file;
	}
}
