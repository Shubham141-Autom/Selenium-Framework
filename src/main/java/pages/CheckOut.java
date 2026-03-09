package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckOut extends UtilsData
{
	WebDriver driver;

	public CheckOut(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryLoc;
	
	@FindBy(css="button[class*='ta-item']:nth-of-type(2)")
	WebElement CountrySelectLoc;
	
	@FindBy(css="div[class='actions'] a")
	WebElement clickLoc;
	
	By allCountryLoc=By.cssSelector("section[class*='ta-results']");
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(countryLoc,countryName).build().perform();
		//wait till all countries load
		waitTillElementAppear(allCountryLoc);
		a.click(CountrySelectLoc).build().perform();  // jese ham list mai se kisi index pr jate the wese nth of type bhi list hai usme index de diya---parent of childs should be same and type of childs same
		
		
	}
	
	public ConfirmPage placeOrder()
	{
		clickLoc.click();
		return new ConfirmPage(driver);
	}
	

}
