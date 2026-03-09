package SeleniumFrameWorkTests;

public class POM {

	public static void main(String[] args) 
	{
		/*
		 *  1.abhi tak hamne ek hi test mai pura flow banaya hai
		 *  2.kisi ele ka id ka base pr locator liya and usko hamne bhot jagah use kiya--agar dev ne us ele ki Id badal di
		 *  3. to hame har jagah jakar us id ko change krna pdega---that will take more time and efforts.
		 *  4. sabhi locators ko ek jagah rakh do taki sare test wahi se unko access kr sake---dev change bhi kre to hame ek hi jagah sab rkhe hai to easily we can change also
		 *  5. sabhi locators ko ek file mai bhi nahi rkh skte---ek locator mai change kiya or usme koi error aaya and if it is linked with some other locator also----all tests will be getting eror
		 *  6.hamari app mai 4 page hai----login   2.product list   3.cart    4. checkout
		 *  7. create 4 java class acc to these pages and place the relative locators in this class only
		 *  8. If login tests requires login locator----just call the login class by creating obj and you can get it.
		 *  conclusion-----jis bhi page ka locator chahiye us page ka obj create krlo----Page Object Model 
		 *  
		 *  9. Now we will seggregate all the modules in pages. we will create pages in main java
		 */
		

	}

}
