package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends UtilsData//inherit ki need tabhi hai jb parent se kuch data ya meth ki need ho---especially wait.--isliye for best pratice ham kr lete hai
{
	WebDriver driver;
	public OrderPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="tr td:nth-of-type(2)")
	List<WebElement>ordersBuy;
	
	public boolean verifyOrdersDisplay(String orderName)
	{
		boolean match=ordersBuy.stream().anyMatch(order->order.getText().equalsIgnoreCase(orderName));
		return match;
	}
	
	

	
	

}
