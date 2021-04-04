package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.fail;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class HandlingMultipleWindows {
	WebDriver driver;

	@Test
	public void handlingMultipleWindows() throws InterruptedException {
		driver.get("http://selenium-examples.nichethyself.com");
		driver.findElement(By.xpath("//button[text()='Contact us!']")).click();
		String parentwindowHandle = driver.getWindowHandle();
		try {
			driver.switchTo().window("Contact");// window name given by
												// developer
			driver.findElement(By.xpath("//a[@onclick='prompty()']")).click();
			Alert myAlert = driver.switchTo().alert();
			myAlert.sendKeys("London");
			Thread.sleep(4000);
			myAlert.accept();
			driver.close();// It closes only current window
		} catch (NoSuchWindowException e) {
			fail("The expected Contact Us window did not work !.");
		}
		driver.switchTo().window(parentwindowHandle);
		driver.findElement(By.xpath("//a[@href='customised.html']")).click();
		Thread.sleep(2000);
		driver.get("http://selenium-examples.nichethyself.com");
		driver.findElement(By.xpath("//button[text()='Write to us!']")).click();
		Thread.sleep(4000);
		Set<String> allWindowHandles = driver.getWindowHandles();

		String writeToUsWindowHandle = null;

		for (String oneWindow : allWindowHandles) {
			if (!oneWindow.equals(parentwindowHandle))
				writeToUsWindowHandle = oneWindow;
		}

		driver.switchTo().window(writeToUsWindowHandle);
		driver.findElement(By.name("name")).sendKeys("Neha");
		Thread.sleep(2000);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "test//resources//chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterMethod() {
	}

}
