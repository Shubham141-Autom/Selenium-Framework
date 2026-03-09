package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Login extends UtilsData
{

	
		/*
		 * 1. login page ke jitne bhi ele hai unke locator yaha le aao
		 * 2. is class ke pass driver ki knowldege nahi hai---driver hamne standalone wali class mai create kiya tha---
		 * 3. us class mai is class ka obj banayge and args mai driver daalega(us driver ko hamne chrome driver banaya tha).
		 * 4. yaha ek driver declare krege and cont banake usme value daalege
		 * 5. Page factory model use krege----driver.findElement isko replacekrta hai FindBy se
		 * 6. prob ye hai ki page factory ko driver ke bare mai kese pata chalega
		 * 7. cont k andar hi PageFactory mai jitne bhi ele hai unko initialize kr denge
		 */
		
		WebDriver driver;  //declare
		
		public Login(WebDriver driver)
		{
			super(driver);            // har child pehle parent ko feed krega
			this.driver=driver;
			PageFactory.initElements(driver, this);   //PF mai sabhi ele ko driver ki knowledge and this isliye use kiya taki bata sake ki isi ligon class ke obj ka driver jo yaha aaya and hamne cont se set kiya
			
		}
		
		//WebElement username=driver.findElement(By.id("userEmail"));
		
		@FindBy(id="userEmail")
		WebElement userEmail;        //syntax ko replace kara and element ko var mai store kiya
		
		@FindBy(id="userPassword")
		WebElement password;
		
		@FindBy(id="login")
		WebElement submit;
		
		//.ng-tns-c4-17.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error--this is css--toaterdisappear ho rha hai to uski class bhi nhi dikh rhi--islye jo hamne loc likha based on visi class wo galat hai
//		@FindBy(id="#toast-container")
//		WebElement errorMsgLoc;
		
		@FindBy(css="[class*='flyInOut']")  // in css give attribute class in reg exp
		WebElement errorMsgLoc;
		
		By errorMsgByLoc= By.cssSelector("[Class*='flyout']");    // ek hi ele ke pf and by locator--koi bhi use krlo jis hisab se parent mai hamne wait banaya hai
		
		//login krne k liye username and password chaiye and click submit button---ek method banake sab kr denge
		public ProductList loginPage(String email, String passcode)
		{
			// set values in locators above----page object class mai koi bhi value direct nahi daalte----value test se aati hai
			userEmail.sendKeys(email);
			password.sendKeys(passcode);
			submit.click();
			
			if(driver.findElements(By.cssSelector("div[class*='mb-3']")).size()>0)   //agar success login hai to list of prod ka obj milega and if list contains items it has some size.
			{
				return new ProductList(driver);
			}
			
			return null;    //suucess login ua to hi obj bhej rhe hai warna null-----test class mai assertion laga lo not null ka
			
		
		}
		
		public void goTo()
		{
			driver.get("https://rahulshettyacademy.com/client");
		}
		
		public String getErrorMsg()
		{
			waitTillElementAppear(errorMsgLoc);
			return errorMsgLoc.getText();   // msg appear hone se pehle text grab kiya to error....use wait
		}

	

}
