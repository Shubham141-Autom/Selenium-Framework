package Cucumberlearning;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFrameWorkTestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.CheckOut;
import pages.ConfirmPage;
import pages.Login;
import pages.ProductList;

/*
 *  tidy gherkin plugin se stepDefinition autogenerte ho jata---add that plugin
 */

public class StepDefinition extends BaseTest
{
	/*
	 *   feature file ki har line step definition se map hogi and jese hi line execute hogi file mai---wo sidha aaygi sdefintion mai or will look where it found match
	 *   2. match milte hi us sdefin mai jo code hoga wo execute.
	 */
	public Login loginPage;
	public ProductList plist;
	public 	CartPage cpage;
	public CheckOut checkOutPage;
	public  ConfirmPage confirmPage;
	
	@Given("I landed on e-comm page")
	public void I_landed_on_e_comm_page() throws IOException
	{
		loginPage=launchApp();
	}
	
	
	@Given("^Logged in to e-comm site with username (.+) and password (.+)$")   // values comes in this dynamically from feature file for each datasets---syntax for regular expression
    public void Logged_in_to_ecomm_site_with_username_and_password(String username, String password)
    {
		 plist=login.loginPage(username,password);
    }
	
	@When("^I add the product with name (.+) from cart$")
		public void I_add_the_product_with_name_from_cart(String itemName) throws InterruptedException
		{
		    System.out.println("item is "+itemName);
			plist.addproductToCart(itemName);
		}
	
	@And("^checkout this product (.+)$")         // we can use When also as and is in conjunction with when
	public void checkout_this_product(String itemName)
	{
		 cpage=plist.goToCart();
		 boolean b=cpage.verifyAddedProduct(itemName);  
		 Assert.assertTrue(b);
		 checkOutPage=cpage.goTocheckOut();
		 checkOutPage.selectCountry("india");
		 confirmPage=checkOutPage.placeOrder();
	 
	}
	
	@Then("confirmation message is {string}")   //regexp will work only for data from columns/table---not for data directly coming from steps in feature file.will use{string} as string is coming at runtime ffrom ffile
	public void confirmation_message(String expectedMsg)
	{
		String actualMsg=confirmPage.verifyConfirmMsg();
		Assert.assertTrue(actualMsg.equalsIgnoreCase(expectedMsg));
		if(driver!=null)
		{
			driver.close();
		}
	}
	
	
		
	
}
