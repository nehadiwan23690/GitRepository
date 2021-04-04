package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class HandlingFrames {
	WebDriver driver;
	
  @Test
  public void frames() {
	  driver.get("http://cookbook.seleniumacademy.com/Frames.html");
	  try
	  {
		  driver.switchTo().frame("left");//id or name of frame from HTML
		  
	  }catch(NoSuchFrameException e)
	  {
		  fail("Frame was expected but not found");
	  }
	  WebElement leftmsg=driver.findElement(By.xpath("//p[text()='This is Left Frame']"));
	  assertEquals("This is Left Frame",leftmsg.getText());
	  driver.switchTo().defaultContent();
	  
	  driver.switchTo().frame("right");//name of frame from HTML.
	  WebElement rightmsg=driver.findElement(By.xpath("//p[text()='This is Right Frame']"));
	  assertEquals("This is Right Frame",rightmsg.getText());
	  driver.switchTo().defaultContent();
	  
	  driver.switchTo().frame("right");
	  
	  WebElement twitterFrame=driver.findElement(By.tagName("iframe"));
	  //Activate iframe
	  driver.switchTo().frame(twitterFrame);
	  WebElement followerscount=driver.findElement(By.xpath("//a[@id='count']"));
	  String count=followerscount.getText();
	  System.out.println(count);
	  driver.switchTo().defaultContent();
	  
	  driver.switchTo().frame(driver.findElement(By.xpath("//frame[@src='frame_b.htm']")));
	//  WebElement middlemsg=driver.findElement(By.xpath("//p[text()='This Frame doesn't have id or name']"));
	
//	  assertEquals("This Frame doesn't have id or name",middlemsg.getText());
	  driver.switchTo().defaultContent();
	  
	  
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
