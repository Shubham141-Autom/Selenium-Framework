package SeleniumFrameWorkTests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameWorkTestComponents.BaseTest;
import SeleniumFrameWorkTestComponents.RetryFailedTest;
import pages.CartPage;
import pages.ProductList;

public class DataproviderByjson extends BaseTest
{
	/*
	 *  jo hamne Dataprovider wali class mai hashmap banaya and data send kiya ab ham alag krege
	 *  1. ek file mai jo data chaiye uska json bana lege---no of sets=no of objects in json ---array of objects--each obj has 1 data set
	 *  2. ek class banayge jisme esa krege ki json data k liye hashmap ban jaye.
	 *  3. Create class DataReader which reads data from json file and create hmap out of it
	 *  4. fileutils k pass method hai json ko string mai read krne ki---give obj of that json file in args
	 *  5. convert that string into hashmap by jackson api---named jackson databind
	 *  6. inherit that clas where we converted json to hmap and use that method here
	 *  7. aleady hamne basetest parent banaya hai to ab hm us convert method ko base Test mai bana dete hai.
	 */
	
	
	@Test(dataProvider="getData", groups={"purchase"},retryAnalyzer = RetryFailedTest.class)   // jb xml se run krege to srif yahi group ka test chalega.
	//public void orderConfirm(String email, String password, String itemName) throws InterruptedException
	public void orderConfirm(HashMap<String,String>input) throws InterruptedException
	{
		//String itemName="ZARA COAT 3";
       // ProductList plist=login.loginPage(email, password);
		ProductList plist=login.loginPage(input.get("email"), input.get("password"));
		
		Assert.assertNotNull(plist,"Invalid Credentials for login");  //invalid login hua to list nahi sirf null aayga. and yaha asserion laga diya taki test fail ho jaye and listern tk cha;a jaye and shot liya ja sake
		
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
	public Object[][] getData() throws IOException
	{
	
		//call method from baseTest for json data in hmaps
		List<HashMap<String,String>>data=getJsonDataToHmap(System.getProperty("user.dir")+"\\src\\test\\java\\DataForTests\\DataProviderByJson.json");
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};   // is array ke andar ka jo data hai---wo method args mai wahi accept krgegi
		// index pr hmaps hai jisme datadet hai
		
		
	}

}
