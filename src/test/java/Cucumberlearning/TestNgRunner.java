package Cucumberlearning;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

   
// to run cucumber tests annotate with cucumberOptions and give path of feature file and its step definition file--dono ko glue se chipa do.glue akes package only
// 3rs args---monochrome=true--> readable output dega---4th args---cucumber plugin to give reports in html----plugin={"html:target/Cucumber.html"} ---key value pair whre key is html and value is folder and file name
@CucumberOptions(features="src/test/java/Cucumberlearning", glue="Cucumberlearning",monochrome = true,plugin = {"html:target/Cucumber.html"}) 
public class TestNgRunner extends AbstractTestNGCucumberTests
{
	/*
	 *  cucumber sidha testNg code ko scan nahi kr skta---have to inherit from AbstarctTestNgCucumberTests to get the helper methods
	 *  runner ab ek ek krke sari feature files ko scan krega and run krega--we have only one as of now.
	 */
	

}
