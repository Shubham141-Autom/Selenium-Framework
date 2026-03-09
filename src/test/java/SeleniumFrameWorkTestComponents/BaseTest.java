package SeleniumFrameWorkTestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Login;

public class BaseTest 
{
	public WebDriver driver;    //default set krte to scope sirf isi class mai hota
	
	public Login login;
	/*
	 *  here we will store all the driver based config.
	 *  1. driver invoke krna.
	 *  2. ye sirf chrome browser invoke kr rha hai---agar hame dusre browser invoke krne hai to.
	 *  3. base test se is nethod ko call krege and browser ka naam denge to wahi browser chala chaiye
	 *  4. PageObject wale section mai ek file banayge jo Global properties save kregi key and value pair mai. Extension---(.properties).
	 *  5. file se data fis obj mai aayga---create fis obj and pass path of our file.
	 *  5. is class mai Properties class ka objectc banayeg---un prop ko load krege
	 *  6. condition lagayi ki browser agar chrome hua to ye code---else if edge hua to ye code
	 */
	public WebDriver invokeBrowser() throws IOException
	{
		Properties prop= new Properties();
		//FileInputStream fis= new FileInputStream("C:\\Users\\Satyam\\eclipse-workspace\\SeleniumFrameWork1\\src\\main\\java\\GlobalResources\\GlobalData.properties");
		// ye file path bhot lamba hai or isme hamare syatem ka local path har coded hai---dure system pr chalayge to nahi chalega...
		//ham path denge is project tak aane ka by System.getProperty("user.dir")--jis proj mai waha tk ka path aa jayga
		
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\GlobalResources\\GlobalData.properties");
		prop.load(fis);       // jb load krege to hamari file se data yaha tk aata hai but wo data fileInputStream obj ki form mai aata hai. hame hamari file ko fis obj banana hai-
		
		String browser=prop.getProperty("browser");    // prop file se us prop ki value nikali
		
		if(browser.equalsIgnoreCase("chrome"))
		{
	
	    WebDriverManager.chromedriver().setup();    // chrome driver is downloaded
	
         driver = new ChromeDriver();
      	//niche ki dono line sabhi browser mai common hai---isko cond ka bahar likh lo---jo bhi browser choose kiya uske liye chal jaygi.
//    	driver.manage().window().maximize();
//    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		
		else if(browser.equalsIgnoreCase("edge"))
		{
			//edge setup
		}
		

		else if(browser.equalsIgnoreCase("firefox"))
		{
			//firefox setup
		}
		
		// ab yaha driver ko global set krna pdega--taki cond block k bahar use kr sake
		driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	
    	return driver;
    	
	}
	
	
	// Method to convert jsondata to hmaps for data provider if any tests needs data
	// now this method uses a hardcoded file path---have to make it dynamic---give path from tests and it will accept that paths for diff files also
	
	public List<HashMap<String, String>> getJsonDataToHmap(String filepath) throws IOException
	{
		//1. get json file here--args take file obj---give path of json data file
		String jsonData=FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);  // tranform to this format
		
		/*2. String to hmap by jackson databind--add dependancy in pom
		 *  1. Crate obj of ObjectMapper----which maps string to hmap
		 *  2. ye har data set joki ek obj hai json mai usko hmap mai badlega and hame utne hmaps dega jitne datasets hai---ya obj hai json mai
		 *  3. un sabhi hmaps ko list mai capture krege like list of hashmaps and store in list. as mapper will give obj of list od hashmaps
		 *  
		 */
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>>hmapOfJsonData=mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>() {
		});       // list esi dikhegi----{{hmap}, {hmap2},{hmap3}}
		
		return hmapOfJsonData;       // jese hamne waha return kiya tha array obj mai hmaps ko wese hi yaha kiya bss yaha arr/list obj ko var mai store krke  return kiya---accept wali method abhi bhi data k hisab se accept kregi joki hmap hai
		
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public Login launchApp() throws IOException
	{
		// kis browser mai launch karna hai----upar walimethod ko call
		driver=invokeBrowser();
	    login= new Login(driver);     // created login obj to pass driver to that class
		login.goTo();
		return login;       // login page obj return kiya taki test mai obj na banana bade---just accept the obj in var and do the test for login
	}
	
	
	/* Case 1: Test PASS → AfterMethod runs ✅
	Case 2: Test FAIL → AfterMethod runs ✅
	Case 3: Test SKIPPED → AfterMethod may NOT run ❌
	Is case me browser open reh sakta hai → memory leak, invalid session, parallel issues. isliye always run true to avoid the warnings in console
	*/

	
	
	@AfterMethod(alwaysRun = true)
	public void testEnd()
	{
	    if(driver != null)  //kuki pehle se hi close hua to error sessionId not found
	    {
	        driver.quit();
	    }
	}
	
	//screenshot on failure
		public String getScreenshot(String testName, WebDriver driver) throws IOException  // driver iliye daala ki sidha yahi method access kri kahi pr to bina login methos ke isme driver ki life aaygi nahi---isliye jb bhi sirf isko call krege isme life dalwayge
		{
		
		TakesScreenshot sshot=(TakesScreenshot)driver;
		File source=sshot.getScreenshotAs(OutputType.FILE);
		File destination= new File(System.getProperty("user.dir")+"//reports//"+testName+".png");  // project level pr reports foder mai us dynamic testname ka png format mai sshot
		FileUtils.copyFile(source,destination );
		//return destination;  // ya to file object return krao----ya niche file path ki is location pr file save hai---baad mai is file ko extent reports mai laga denge
		return System.getProperty("user.dir")+"//reports"+testName+".png";
		}
		

}
