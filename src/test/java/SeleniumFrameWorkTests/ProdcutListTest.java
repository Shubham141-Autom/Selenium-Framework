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
import pages.ProductList;

public class ProdcutListTest 
{
	public static void main(String[] args) throws InterruptedException
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
		
		
		
		//add to cart this item ----ZARA COAT 3
		ProductList plist= new ProductList(driver);
		List<WebElement>products=plist.getItemList();
		
		WebElement element=plist.getProductByName(itemName);  //us page pr agar 1 se jyada item hue us naam ke to hamne pehle item do or koi bhi item na mile to null.
		//upar bss ek aisa ele hai jisko hamne scope mai use kiyahai apne le tk jane k liye
		
		plist.addproductToCart(itemName);
		
		// confirm if product is added--loader jab chala jaye and confirm message aa jaye and we grab the text---toster type msg hai--thodi der mai hat jata hai
		// <div id="toast-container" class="toast-bottom-right toast-container"></div>
	    
		// iske liye yaha explicit wait lagayge uske baad hi cart mai jayge
		// wait krna hai jab tk toaster visible na ho jaye and loader invisible na ho jaye
		
		
		//cart---iska header sabhi page k liye common hai to isko ham util class mai daal denge and can use multiple times.
		plist.goToCart();     // cart ka alg se obj nahi banaya---child ne parent ko use kr liya 
		
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
