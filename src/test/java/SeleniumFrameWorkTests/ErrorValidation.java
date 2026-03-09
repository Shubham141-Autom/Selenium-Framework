package SeleniumFrameWorkTests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFrameWorkTestComponents.BaseTest;
import pages.CartPage;
import pages.OrderPage;
import pages.ProductList;



public class ErrorValidation extends BaseTest
{
	/*
	 *  1.aagar mene wrong email password ddala to error aayga usko handle krna hai---action banayge pageClass mai--grab text
	 *  2. validation krege test class mai
	 *  3. har testCase k liye alag se testclass se nahi banani hai---club them acc to modules.
	 *  4. if login have 5 tcase and instead of creating 5 java testclass just create 1 and name it login error validation
	 *  5. Ab hame chahiye ki positive and negative tests sab sath chale parallel---create testng xml file and thread at test level.
	 *  6. try to keep a separate folder for each class.
	 *  7.submit order wale test ko ek class mai and error valida wale tests ko alag class mai and alag folder mai
	 */
	@Test
	public void errorLogin()
	{
	   login.loginPage("shubh2304@gmail.com", "Shubham@20");  //give erong inputs but there is no validation for it---explicitly do fail in assertion
	   String errorMsg=login.getErrorMsg();
//	   System.out.println(errorMsg);
	   Assert.assertEquals("Incorrect email or password.", errorMsg);
	}
	
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
	
// agar mujhe tests ko parallel run krna hai--ek sath sabhi tests chale and multiple windows khule---suite level pr  parallel="tests"--us suite mai jinee bi test honge sab ek sath run
	
	
	
	
	
	
}
