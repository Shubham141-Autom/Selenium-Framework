package SeleniumFrameWorkTests;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameWorkTestComponents.BaseTest;
import pages.CartPage;
import pages.ProductList;

public class DataProviderTest extends BaseTest
{
	@Test
	public void errorLogin()
	{
	   login.loginPage("shubh2304@gmail.com", "Shubham@20");  //give erong inputs but there is no validation for it---explicitly do fail in assertion
	   String errorMsg=login.getErrorMsg();
//	   System.out.println(errorMsg);
	   Assert.assertEquals("Incorrect email or password.", errorMsg);
	}
	
	@Test(dataProvider="getData", groups={"purchase"})   // jb xml se run krege to srif yahi group ka test chalega.
	//public void orderConfirm(String email, String password, String itemName) throws InterruptedException
	public void orderConfirm(HashMap<String,String>input) throws InterruptedException
	{
		//String itemName="ZARA COAT 3";
       // ProductList plist=login.loginPage(email, password);
		ProductList plist=login.loginPage(input.get("email"), input.get("password"));
		
		//add to cart this item ----ZARA COAT 3
	
		List<WebElement>products=plist.getItemList();
		
		//print to confirm items on page---p is a webElement which is used as scope to find our item
		products.forEach(p ->
	    System.out.println(p.findElement(By.cssSelector("b")).getText()));
		
		//WebElement element=plist.getProductByName(itemName);  //us page pr agar 1 se jyada item hue us naam ke to hamne pehle item do or koi bhi item na mile to null.
		WebElement element=plist.getProductByName(input.get("itemName"));
		//upar bss ek aisa ele hai jisko hamne scope mai use kiyahai apne le tk jane k liye
	
		
		//plist.addproductToCart(itemName);
		plist.addproductToCart(input.get("itemName"));
		CartPage cpage=plist.goToCart();     // cart ka alg se obj nahi banaya---child ne parent ko use kr liya ----is method ne cartpage ka obj diya
		
		//boolean b=cpage.verifyAddedProduct(itemName);   // validation can not go in page object files
		boolean b=cpage.verifyAddedProduct(input.get("itemName"));
		Assert.assertTrue(b);  
		if(b)
		{
			System.out.println("Item matches in the cart");
		}
		
	}
	
	//   we will create a data provider method which will feed data to above tests. for login username and password and for other test we will provide itemName also
	//jaha is data ko  bhjna hai us test mai (dataProvider="name of method which is sending data")
	@DataProvider
	public Object[][] getData()
	{
	// hamne 3 d ataset diye hai---test 3 baar chalega---3 diff prduct add krega
		//return new Object[][] {{"shubh2304@gmail.com", "Shubham@2304","ZARA COAT 3"}, {"shubh2304@gmail.com", "Shubham@2304","ADIDAS ORIGINAL"}, {"shubh2304@gmail.com", "Shubham@2304","iphone 13 pro"}};
		
		// abhi to ham 3 params accept kiye but kal ko 10-20 params ki need hui to ham kitne params likhege method mai----hashmap use krege
		HashMap<String,String>hmap=new HashMap<>();    //key and value both are object type as we can store any time on both for diff sets of data--but ham string hi use krege kuki hmari old methids disturbed ho jaygi
		hmap.put("email","shubh2304@gmail.com" );
		hmap.put("password","Shubham@2304" );
		hmap.put("itemName","ZARA COAT 3" );
		
		HashMap<String,String>hmap1=new HashMap<>();    //jitne data sets utne hmaps
		hmap1.put("email","shubh2304@gmail.com" );   
		hmap1.put("password","Shubham@2304" );
		hmap1.put("itemName","ADIDAS ORIGINAL" );
		
		HashMap<String,String>hmap2=new HashMap<>();    
		hmap2.put("email","shubh2304@gmail.com" );
		hmap2.put("password","Shubham@2304" );
		hmap2.put("itemName","iphone 13 pro" );
		
		return new Object[][] {{hmap},{hmap1},{hmap2}};   // is array ke andar ka jo data hai---wo method args mai wahi accept krgegi
		
		
		
	}
	
	
	
	

}
