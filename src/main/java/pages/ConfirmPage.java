package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmPage extends UtilsData
{
	WebDriver driver;
	public ConfirmPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".hero-primary")
	WebElement SuccessMsgLoc;
	
	public String verifyConfirmMsg()
	{
		return SuccessMsgLoc.getText();
	}

}
