
public class ExtentReportsInfo 
{
	/* 
	 *  1. Abhi hamne jo demo dekha Ereports ka usme hamne ek class jisme tests likhe hai unke liye config kiya and har test k liye ETest ka obj banaya to monitor that test on failure or success.
	 *  2. Hame kese pata chalge aki test fail hua ya nahi----Listeners mai EReport koattach krege taki jb test fail ho,ETest ka obj bane and monitor that test.
	 *  3. Global level pr config krege EReport ka.
	 *  
	 *  Steps----
	 *  1. Extent Reports ka config ek global class mai kr dege
	 *  2. Listenrs ka kaam hai har test ko monitor krna or puchega ki test k start pr kya krna hai---fail pr--pass---bhot si methods hai
	 *  3. Hame ye krna hai ki har test k liye report ka object chahiye taki extent test ka obj mil sake.
	 *  4. Extent rports config class---method banayge or EReport ka obj return krege.
	 *  5. Connection banayge ER and Listenrs class mai----INherit kr nhi skte kuki Listen already ITestList ko inherit krta hai
	 *  ---- Ya to ER ka obj banao Liste class mai Ya ER mai us mmehtod ko static krdo or Liste class mai ER class.method ko access kr do
	 *  6. ab hame ER ka obj mil gaya to ham ya set kr enge List class ki mthods mai ki hame ktya kya krna hai.
	 *  7. Er config kiya hai java classes mai jaha global prop set kri thi.
	 *  8. Listeners class ki knowldege bhi batani pdegi xml file mai kis ye lister class kaha hai taki wo test run krne se pehle is class mai aaye--st suite level declare in xml.
	 *  9. test flush bhu krna hai after tests to generate ereports.
	 */

}
