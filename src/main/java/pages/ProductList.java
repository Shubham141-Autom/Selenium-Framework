package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductList extends UtilsData      //utils is parent to usme kuch var bhjene k liye use super
{

		WebDriver driver;  //declare
		
		public ProductList(WebDriver driver)
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);   //PF mai sabhi ele ko driver ki knowledge and this isliye use kiya taki bata sake ki isi ligon class ke obj ka driver jo yaha aaya and hamne cont se set kiya
			
		}
		
		//List<WebElement>products=driver.findElements(By.cssSelector("div[class*='mb-3']"));  --convert to page factory
		
	    @FindBy(css="div[class*='mb-3']")
	    List<WebElement>products;         // ye web element ki list hai jo hamne driver element se nikali hai
	    
	    // wait k liye hamne sirf locator use kiya hai usi list k liye--- jiska returntyoe by hai----or Page factory sirf elements k liye hai--to hm pf mai nahi usko normal store krege
	    By ListByLocator=By.cssSelector("div[class*='mb-3']");
	    
	    By prodLocator=By.cssSelector("div.card-body button:last-of-type");
	    
	    By eleAppearLoc=By.cssSelector("div#toast-container");
	    
	    // wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); --ye webEle hai isko page factory mai dalege
	    @FindBy(css=".ng-animating")
	    WebElement eleDisAppearLoc;
	    
	    
	    //action---- pehle kaam hamara rhega list mai se ek item pr click krna---uske pehle wait krege to list gets visible
	    public List<WebElement> getItemList()
	    {
	    	//prod return krne se pehle hame wait krna hai--
	    	waitTillElementAppear(ListByLocator);    // yaha wahi locator diya jisse list aa gyi ele ki--isliye hamne locator save kiya tha
	    	return products;
	    }
	    
	    //find product based on name---is item mai hamara desired prod nahi hai----isko hamne as a scope use kiya hai to get oue product
	    public WebElement getProductByName(String itemName)
	    {
	    	// yaha hamne getItem wali method call kro jo hame list to de rahi hai but wait k basd. direct product se stream lagate to ho skta tha wait kpehle hi list aa jati---error a skta tha agar loading late hoti
	    	WebElement item=getItemList().stream().filter(product->
			product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(itemName)).findFirst().orElseGet(null);
	    	return item;
	    }
	    
	    //add to cart
	    public void addproductToCart(String prodName) throws InterruptedException
	    {
	    	// element.findElement(By.cssSelector("div.card-body button:last-of-type")).click(); is loator ko firse save kr liya
	    	getProductByName(prodName).findElement(prodLocator).click();
	    	//hamne obj se addprod ko call kiya---namediya---andar aake usne getprod wali ko call kiya to get scope ele by that and fir us ele se hamne aone ele ko search kiya By Prod locator
	    	//have to wait to confirm the toast msg to confirm prod added to cart
	    	waitTillElementAppear(eleAppearLoc);
	    	waitTillElementDisAppear(eleDisAppearLoc);
	    }
		
		
		

	

}
