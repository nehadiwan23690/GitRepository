package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;

public class HandlingDropdown {
	WebDriver driver;
  @Test
  public void dropDown() throws InterruptedException {
	driver.get("http://cookbook.seleniumacademy.com/Config.html");
	WebElement dropDown=driver.findElement(By.name("make"));
	Select mydropDown=new Select(dropDown);
	List<WebElement>allOptions=mydropDown.getOptions();
	  List<String> expectedOptions = new ArrayList();
	  expectedOptions.add("BMW");
	  expectedOptions.add("Mercedes");
	  expectedOptions.add("Audi");
	  expectedOptions.add("Honda");
	  
	  List<String> actualOptions=new ArrayList();
	  for(WebElement oneOption:allOptions)
	  {
		  actualOptions.add(oneOption.getText());
	  }
	  
	  assertEquals(expectedOptions,actualOptions);
	  mydropDown.selectByIndex(1);
	  Thread.sleep(2000);
	  mydropDown.selectByVisibleText("Audi");
	  Thread.sleep(2000);
	  mydropDown.selectByValue("honda");
	  Thread.sleep(2000);

	  System.out.println(mydropDown.getFirstSelectedOption().getText());
	  System.out.println("No. options are - " + allOptions.size());
	  
	
	
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
