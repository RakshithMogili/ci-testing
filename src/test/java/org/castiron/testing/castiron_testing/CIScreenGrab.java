package org.castiron.testing.castiron_testing;

import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class CIScreenGrab {

	@Test
	public void TestMethod() throws InterruptedException, FindFailed, IOException {

		System.setProperty("webdriver.chrome.driver", "/Users/Rakshith/Downloads/chromedriver");
		/*ChromeOptions option = new ChromeOptions();
		option.setHeadless(true);*/
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://svt2ci01m.rtp.raleigh.ibm.com/login");

		WebElement uname = driver.findElement(By.xpath("//input[@name='username']"));
		uname.sendKeys("admin");
		WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys("Sep13pwd");
		WebElement login = driver.findElement(By.xpath("//input[@type='submit']"));
		login.click();

		WebElement link = driver.findElement(By.xpath("//a"));
		link.click();

		// Create a object of a screen class
		Screen sc = new Screen();

		// Crate a object of Pattern class to click on Allow button
		Pattern allow = new Pattern("Allow.png");
		sc.wait(allow, 2000);
		sc.click();
		
		//Sleeping the whole code for 2 minutes
		Thread.sleep(30000);
		
		//Finding an element in the screen
		Pattern IDM = new Pattern("IDM.png");
		sc.wait(IDM,3000);
		System.out.println("Found IDM Collpase Orchestration");
		
		// Takes screenshot of the Disk Utilization Screen
		System.out.println("Taking the screen shot of Disk Utilization");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("/Users/Rakshith/Desktop/castiron/castiron.png"));

		// Logout of Cast Iron after taking the screen shot
		Pattern lgout = new Pattern("LogOut.png");
		sc.wait(lgout, 2000);
		System.out.println("Logging out from Cast Iron");
		sc.click();

		// Close the driver after execution of the test script
		driver.close();
		System.out.println("Closing the driver connection");

	}

}
