package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage extends UtilsData
{

	    WebDriver driver;
		
		public CartPage(WebDriver driver)
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver,this);
			
		}
		
		@FindBy(css="div.cartSection h3")
		List<WebElement>cartItems;
		
		@FindBy(css=".totalRow button")
		WebElement checkOut;
		
		
		//Action
		public boolean verifyAddedProduct(String prodName)
		{
			boolean match=cartItems.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(prodName));  // list mai se 1 bhi naam match ho gaya to true dega
			
			return match;
		}
		
		public CheckOut goTocheckOut()   // next page pr ja rhe hai to us page ka obj yahi bana denge taki test class mai na banana pade--waha sirf store krege obj var mai
		{
			checkOut.click();
			return new CheckOut(driver);
		}
		

	

}
