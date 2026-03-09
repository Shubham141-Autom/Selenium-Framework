package Cucumberlearning;

import com.github.dockerjava.api.model.Info;

public class CucumberPractice 
{
	/*
	 *  1. Gherkin----lang to define a testCase
	 *  2. Business req are in plain english and if BA describes a req and it is  by QA--so BA has to follow req in gherkin syntax to avoid mismatch.
	 *  3. Keywords---Scenario,Feature,Scenario outline, feature outline,Step definition
	 */
	
	
	/*Sceanrio----TestCases are represented as scenarios. it contains steps whch include keywords as given,when,then,and,but...
	
	Given----preconiditons required to fill for the test Case--denotes the action user performs.
	When----user starts to create test or to perform functionality check---and---if user perfomrs multiple actions(fill form and click some button)
	then---output of the testCase.
	
	Scenario----make min due payment
	Given----user is on payment page-----when---usr fills the cars details-----and---clicks on pay button----then---confirm page is displayed---but---to test/validate negative scenarios
	for Automation---given represents landing on the page---when represents action performed---then represents results and validations on that results----but represents negaticve testing
	
	
	
	
	Feature----Defines the businesss req or we can say it is a functionality---so it includes multiple test Cases to test that functionality---by covering positive and neagtive sceanrios to validate the feature
	 Feature file---.feature is the extension that contains alll the sceanrios to that feature.
	 
	 Step Defintion-----It is a file .java is the extension and for each line in the scenario---develop code for the same---eg-> when user fill the payment info--so create code to fill the Info
	 
	Scenario outline----> contains data for the scenario like datasets in dataprovider in testng--like scenario is check string is palindrome
	datasets will be in table and sceanrio will run n times for n datasets in table. Jab multiple datasets ho
	
	import 2 dependancy----cucumber java and cucumber testng   and cucumber plugin in eclipse
	
	feature file mai scenarios= methods in testng
	
	just like before method Backgorung keyword does.  run that test which is common to all scenarios
	
	TO RUN FEATURE FILE USE TEST RUNNER AS WE ARE USING TESTNG FRAMEWORK SO USE IT.
	 */
	
}
	
	