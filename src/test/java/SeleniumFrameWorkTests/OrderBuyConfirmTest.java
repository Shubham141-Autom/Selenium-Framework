package SeleniumFrameWorkTests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFrameWorkTestComponents.BaseTest;
import pages.CartPage;
import pages.OrderPage;
import pages.ProductList;

public class OrderBuyConfirmTest extends BaseTest
{
	@Test
	public void errorCartItem() throws InterruptedException
	{
		String itemName="ZARA COAT 3";
        ProductList plist=login.loginPage("shubh2304@gmail.com", "Shubham@2304");
		
		//add to cart this item ----ZARA COAT 3
	
		List<WebElement>products=plist.getItemList();
		
		WebElement element=plist.getProductByName(itemName);  //us page pr agar 1 se jyada item hue us naam ke to hamne pehle item do or koi bhi item na mile to null.
		//upar bss ek aisa ele hai jisko hamne scope mai use kiyahai apne le tk jane k liye
		
		plist.addproductToCart(itemName);
		CartPage cpage=plist.goToCart();     // cart ka alg se obj nahi banaya---child ne parent ko use kr liya ----is method ne cartpage ka obj diya
		
		boolean b=cpage.verifyAddedProduct(itemName);   // validation can not go in page object files
		Assert.assertTrue(b);  
		if(b)
		{
			System.out.println("Item matches in the cart");
		}
		
	}
	
	// NOW WE WILL VALIDATE KI JO PRODCUT HAMNE PURCHASE KIYA WO ORDERS PAGE MAI HAI YA NAHI---pr ye test depend hai upar wale mai ki product kharida tabhi check krege
	
	@Test(dependsOnMethods= {"errorCartItem"})   //pehle upar wala test chalega fir uske result ko verify krne k liye ye wala
	public void confirmOrderPage()
	{
		String itemName="ZARA COAT 3";
		ProductList plist=login.loginPage("shubh2304@gmail.com", "Shubham@2304");
		      // ye common hai sabhi page k liye to iska action ham util class mai banayge to click order in header
		OrderPage opage=plist.goToOrders();
		boolean match=opage.verifyOrdersDisplay(itemName);
		Assert.assertTrue(match);
		
	}
	

}
