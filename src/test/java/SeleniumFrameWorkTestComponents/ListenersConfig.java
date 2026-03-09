package SeleniumFrameWorkTestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import GlobalResources.ExtentReportsConfig;

/*
 *  jab bhi 1 test obj create hota hai By report.createTest--usko test var mai store kr diya
 *  parallel run mai multiple tests ek sath run hote hai----like test A and test B---A abhi process ho rha hai--pass/fail tk nahi pahucha
 *  usi time mai B create hua or wo bhi same test var mai aagya to usne A ko override kr diya---ab agar A fail hua---test var mai to B test hai
 *  reports mai B fail register ho jata hai jabki fail A hua
 *  isliye hm ThreadLocal class ka obj banate hai or har test create hone k baad wo var ko is class obj mai set lr dete hai
 *  har test obj k pass apna khud ka test var ban jata hai.----avoid concurrency issues
 */

public class ListenersConfig extends BaseTest implements ITestListener
{
	

	ExtentReports report=ExtentReportsConfig.getExtentReportsObj();   //isse hame ER ka obj mil jayga jo hamne config kiya tha  //global level pr declare--taki ab methods use kr 
    ExtentTest test;
    ThreadLocal <ExtentTest>threadTest= new ThreadLocal();   // is type ke var store krna hai unka type
   
	@Override
	public void onTestStart(ITestResult result) 
	{
		//test start pr Ereport ka obj se ham Extent test ka obj banate hai---1. ER obj(hamne ER class mai method ko static banay to class name se hi method access kr skte hai- )  2.ETest obj 3. ETest obj mai us test ka naam but dynamic
		
		test=report.createTest(result.getMethod().getMethodName());  //result obj ke pass us particular test ki info reht hai--us method se method ka naam nikal liya--or us naam se test create kr liay
		threadTest.set(test);  // ab ahr test k pass apn akhud ka thread hoga jisme uska test var hoga
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		//test.log(Status.FAIL,"Test Failed");  //isse sirf yahi message milega pr agar hame exact reason chahiye for fail
		// ham yaha explicit fail kr rhe hai kuki is block mai aaya mtlb test fail hua
		//test.fail(result.getThrowable());  // result obj ke pass details hai ku fail hua---throwable jb bhi test fail hota hai to uska exception throw krta hai.
		threadTest.get().fail(result.getThrowable());  //threadTest.get() ye usi test ki Id layga jo isme store hai and usi ko fail
		
		// now get sshot and attach it in reports---sshot ka logic ek  class mai tha Basetest--ineherit that class-- mai banaya hai
		String filepath=null;  //filePath declare kiya--outside try block to get its access globally.--init bhi kra with null--try mai jane se phel init krna peta hai
		try 
		{
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			/*
			 *  1.result.getTestClass---ye hame xml mai jakr us test ki class ka obj degga--obj se ham us test ki real class mai gye
			 *  2.us class mai se hamne ek field nikali driver naam k var ki---field hame sirf us var ki inofo deta hai jse datatype, name but value nhi deta
			 *  3.result--->objtestClass--->RealClass---Field nikali--->uske baad syntax ye bana Field.get(result.getInstance)--result.getInstance se hame running test class ka obj ilega
			 *  Field.get(obj)----ye hame us obj se is field ki value nikal kr dega.---complie time pr is value ka dataype nhi pata isliye ye Oject Type return krega
			 *  jisko hamne WebDriver type mai cast kiya and store in a var jske pass driver hai with life.
			 *  
			 *    Note- us test k pass driver with life kese aaya:- koi bhi test agar run kr rha hai to driver uske pass hoga hi
			 */
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			
		}
		;
		try {
			 filepath=getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) 
		{
			System.out.println(e.getMessage());
		} 
		// attach sshot in reports
		//test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		threadTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		
		/*
		 *   jb Basetest se sshot wali emthod call hui to usme driver var tha
		 *   2. Us driver var mai life tb aati jb login method call hoti or driver obj banta--invoke walimethod se
		 *   3. yaha hm sshot method ko call kr rhe--login nhi---driver jo hamne btest mai global declare kiya tha wo bss declaration tha--life nahi thi
		 *   yaha jo sshot mai driver aayga use bhi life nahi hogi.
		 *   4. hame sshot wali method mai driver accept krna hoga and Listener wali class se isme driver with life bhejna hoga
		 *   Test class ka ek object hota hai--Listener ka alag object hota hai--To BaseTest ka driver variable dono jagah same nahi hota--Isliye screenshot me driver null milta hai.
		 *   
		 */
		
		
		
	}
	
	@Override
	public void onFinish(ITestContext context) 
	{
		report.flush();
		
	}
	
	
	

}
