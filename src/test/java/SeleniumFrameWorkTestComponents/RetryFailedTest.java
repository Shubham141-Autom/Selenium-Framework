package SeleniumFrameWorkTestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTest implements IRetryAnalyzer
{
	/*
	 *  Sometimes kuch error ki wajah se non-intentionally hamare test fail h jate hai
	 *  ham un flaky test o ek baar firse run kr skte hai to see they really failed or not.--implements IRetryAnalyzer interface
	 *  test fail---->listeners fail method---->comes here to re-run
	 *  jis bhi test ko fail hne pr re-run krna hai uske args mai likh do retryAnalyzer={name of class that has re-run config.class}
	 *  ek class mai kisis test k liye 3 datasets hai to jo fail hua hai sirf wahi re-run hoga
	 */
	
	int count=0;
	int maxTry=1;    // hm 2 baar re-run krwa rahe
	


	@Override
	public boolean retry(ITestResult result) 
	{
		if(count<maxTry)     //0<2 means abhi tk re-run nahi hua
		{
			count++;        // re-run pr bhejne se pehle count increase so that we can udate ki ek baar re-run ho chuka --taki dubara aaye to to count updated mile
			return true;     // jb return kr diya to aage ka code nai chalega---yaha se true hoga mtlb wo failed test fir se chalao---fir fail hua to fir yaha aayga --cond check and repeat----when condition is false--out of if block and return false--means do not re-run the test
		}
		
		return false;
	}
	
}
