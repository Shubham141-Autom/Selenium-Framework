package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilsData
{
	WebDriver driver;
	public UtilsData(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='cart']")        //cart ki alag se class nahi bnayi usko yahi pf banakar use kr liya
	WebElement cartHeader;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orderHeader;
	
	
	//wait method by element locator with By Locator
	public void waitTillElementAppear(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));   // ye locator dynamic hona chahiye---method mai accept krege---pr wo locator hai element nahi to uska type By hai
		
	}
	//wait method by element locator with with driver
	public void waitTillElementAppear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));   // ye locator dynamic hona chahiye---method mai accept krege---pr wo locator hai element nahi to uska type By hai
		
	}
	
	public void waitTillElementDisAppear(WebElement eleLocator) throws InterruptedException
	{
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//		wait.until(ExpectedConditions.invisibilityOf(eleLocator));   // ye locator dynamic hona chahiye---method mai accept krege---pr wo locator hai element nahi to uska type By hai
		//backend mai or bhi loader chal rahe hai jiske wajah se sel jyada time ruk raha hai to wait for all loaders not ust for ui loader
		// isliye yaha ham sirf thread.sleep krege kuki ui mai sirf 1 sec mai loader load ho gaya
		Thread.sleep(1000);
	}
	
	public CartPage goToCart()
	{
		 cartHeader.click();
		 return new CartPage(driver);
	}
	
	public OrderPage goToOrders()
	{
		orderHeader.click();
		return new OrderPage(driver);
	}
	
	
	

}
