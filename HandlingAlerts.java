package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class HandlingAlerts {
	
	WebDriver driver;
  @Test
  public void alerts() throws InterruptedException {
	  driver.get("http://selenium-examples.nichethyself.com");
		driver.manage().window().maximize();
		driver.findElement(By.id("loginname")).clear();
		driver.findElement(By.id("loginname")).sendKeys("stc123");
		driver.findElement(By.id("loginbutton")).click();
		Thread.sleep(3000);
		try
		{
			Alert myAlert=driver.switchTo().alert();
			String expectedMessage = "Please enter Password";
			String actualMessage = myAlert.getText();
			assertEquals(actualMessage, expectedMessage, "Alert message did not match the requirements");
			myAlert.accept();
			
		}catch(NoAlertPresentException e)
		{
			fail("The alert was expected but seems it is missing.");
		}
  }
  @BeforeMethod
  public void beforeMethod() {
	  
		System.setProperty("webdriver.chrome.driver", "test//resources//chromedriver.exe");
		driver=new ChromeDriver();
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
