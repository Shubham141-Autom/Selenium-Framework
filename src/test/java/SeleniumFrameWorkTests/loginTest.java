package SeleniumFrameWorkTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Login;

public class loginTest 
{
	public static void main(String[] args)
	{
		String itemName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();    // chrome driver is downloaded
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		
		//login
		Login login= new Login(driver);     // created login obj to pass driver to that class
		login.goTo();
		login.loginPage("shubh2304@gmail.com", "Shubham@2304");
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='mb-3']")));
		
		//add to cart this item ----ZARA COAT 3---jese hi visible hua grab the list--is wait ko ham central class mai dalege
		List<WebElement>products=driver.findElements(By.cssSelector("div[class*='mb-3']"));   // ye ek card mai aa gye----driver ka scope limited kr denge and search in that element only
		WebElement element=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(itemName)).findFirst().orElseGet(null);  //us page pr agar 1 se jyada item hue us naam ke to hamne pehle item do or koi bhi item na mile to null.
		
		// ab hamare ele aa gaya hai -- ham is element mai hi add to cart ka locator dekhege driver scope to element
		element.findElement(By.cssSelector("div.card-body button:last-of-type")).click();  // 2 button the as 2 child last button liya
		
		// confirm if product is added--loader jab chala jaye and confirm message aa jaye and we grab the text---toster type msg hai--thodi der mai hat jata hai
		// <div id="toast-container" class="toast-bottom-right toast-container"></div>
	    
		// iske liye yaha explicit wait lagayge uske baad hi cart mai jayge
		// wait krna hai jab tk toaster visible na ho jaye and loader invisible na ho jaye
		// sabhi wait mechanism ko ek central class mai rakh lo as they will be used again and again. It will be parent to all class whom any class inherits.
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));   // class hai loader ki
		
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click(); 
		
		//collect all items from cart and check for what we have added is in the cart or not
		
		List<WebElement>cartItems=driver.findElements(By.cssSelector("div.cartSection h3"));
		// hame bs true and false mai batana hai ki item hai ya nhi to filter nhi ham anymatch method use krege---kuki filter hame webelement dega and anymatch hame boolean result dega
		boolean match=cartItems.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(itemName));  // list mai se 1 bhi naam match ho gaya to true dega
		Assert.assertTrue(match);      
		// just print in console to see
		if(match)
		{
			System.out.println("Item matches in the cart");
		}
		
		//checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//fill the details there on page but this time with action class.
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build().perform();
		a.click(driver.findElement(By.cssSelector("button[class*='ta-item']:nth-of-type(2)"))).build().perform();  // jese ham list mai se kisi index pr jate the wese nth of type bhi list hai usme index de diya---parent of childs should be same and type of childs same
		a.click(driver.findElement(By.cssSelector("div[class='actions'] a"))).build().perform();
		
		//validate checkout order by confirming success message
		String successMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(successMsg.equalsIgnoreCase("Thankyou for the order."));
		
	}

}
