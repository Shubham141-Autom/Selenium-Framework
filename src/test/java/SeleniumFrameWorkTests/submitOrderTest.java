package SeleniumFrameWorkTests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import SeleniumFrameWorkTestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CartPage;
import pages.CheckOut;
import pages.ConfirmPage;
import pages.Login;
import pages.ProductList;

public class submitOrderTest extends BaseTest{

	@Test
	public  void submitOrder() throws InterruptedException, IOException 
	{
		String itemName="ZARA COAT 3";
		String countryName="India";
		
		//login------launchApp bhi har test k liye common hai kuki sabhi test pehle landing page pr hi jayge--isko before method mai rkh do base test mai--jb is class ka test run hoga to pehle beforemethod ko search krega and will search in parent also
//		Login login=launchApp();
		/*
		 *   1.ye test chalne se pehle before method chalegi
		 *   2.before method mai gaya--uske andar login obj banaya pr uska scope sirf block k andar hi raha
		 *   3. base test mai global level pr obj declare krege and jb befre method chalegi tb wo obj init ho jayga
		 *   4. as we are using inrerface--ham directly us basetest class ke var and prop use kr skte hai
		 *   5. method call krke var accept ki need nahi as inheritance is there
		 */
		ProductList plist=login.loginPage("shubh2304@gmail.com", "Shubham@2304");
		
		
		
		//add to cart this item ----ZARA COAT 3
	
		List<WebElement>products=plist.getItemList();
		
		WebElement element=plist.getProductByName(itemName);  //us page pr agar 1 se jyada item hue us naam ke to hamne pehle item do or koi bhi item na mile to null.
		//upar bss ek aisa ele hai jisko hamne scope mai use kiyahai apne le tk jane k liye
		
		plist.addproductToCart(itemName);
		
		// confirm if product is added--loader jab chala jaye and confirm message aa jaye and we grab the text---toster type msg hai--thodi der mai hat jata hai
		// <div id="toast-container" class="toast-bottom-right toast-container"></div>
	    
		// iske liye yaha explicit wait lagayge uske baad hi cart mai jayge
		// wait krna hai jab tk toaster visible na ho jaye and loader invisible na ho jaye
		
		
		//cart---iska header sabhi page k liye common hai to isko ham util class mai daal denge and can use multiple times.
		CartPage cpage=plist.goToCart();     // cart ka alg se obj nahi banaya---child ne parent ko use kr liya ----is method ne cartpage ka obj diya
		
		//collect all items from cart and check for what we have added is in the cart or not
		
		
		
		boolean b=cpage.verifyAddedProduct(itemName);   // validation can not go in page object files
		Assert.assertTrue(b);  
		if(b)
		{
			System.out.println("Item matches in the cart");
		}
		
		//checkout
		CheckOut checkOutPage=cpage.goTocheckOut();   // ye method checkout button pr click krke checkout page pr le jaygi
		//pehle ham kisi bhi page ko use krne k liye uska obj banate the--driver pass krte the--
		//is method ko jaha banaya hai whi last mai next page ka obj bana dege and return kr denge or yaha ek var mai store kr lenge
		
		//fill the details there on page but this time with action class.
		checkOutPage.selectCountry(countryName);
		 ConfirmPage confirmPage=checkOutPage.placeOrder(); //ye confirm page pr gaya---ab niche firse placeOrder ko call krege to page change ho chuka tha--usi page pr wapas aayga to us ele k liye dom refresh ho gya h--stale error dega--isliye next page k obj ko pehle hi capture kr liya
		
		//validate checkout order by confirming success message
		// ConfirmPage confirmPage=checkOutPage.placeOrder();
		 String msg=confirmPage.verifyConfirmMsg();
		Assert.assertTrue(msg.equalsIgnoreCase("Thankyou for the order."));
		
	//testEnd(); after method mai close kr diya hai
		
		// is tarh se hamne ek bhi object creation test class mai nahi kiya----jis method k baad ham next page pr ja rhe usme obj of next page return kr diya
    
		
		
		
		
	}

}
