package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class GoogleLinksTest {
	WebDriver driver;
	@Test
  public void googleLinks() {
		driver.get("http://www.google.com");
		// WebElement myElement=driver.findElement()--only one element
		List<WebElement>allGoogleLinks=driver.findElements(By.xpath("//a"));//gives all the element
		System.out.println("Total number of links on google home page :" +allGoogleLinks.size());

		
		
	/*	for(int i=0;i<allGoogleLinks.size();i++)
		{
			 String visibleTextOfElement=allGoogleLinks.get(i).getText();
			 String urlWhereItIsPointingTo=allGoogleLinks.get(i).getAttribute("href");
			 System.out.println(visibleTextOfElement + "-"+ urlWhereItIsPointingTo);
		}*/
		
		for(WebElement oneLinkAtTime:allGoogleLinks)
		{
			String visibleTextOfElement=oneLinkAtTime.getText();//This returns single webElement
			 String urlWhereItIsPointingTo=oneLinkAtTime.getAttribute("href");
			 System.out.println(visibleTextOfElement + "-"+ urlWhereItIsPointingTo);
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
