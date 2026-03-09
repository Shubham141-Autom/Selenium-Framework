package DataForTests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader 
{
	public List<HashMap<String, String>> getJsonDataToHmap() throws IOException
	{
		//1. get json file here--args take file obj---give path of json data file
		String jsonData=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\DataForTests"),StandardCharsets.UTF_8);  // tranform to this format
		
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

}
// data provider list deta hai hmaps ki---by index jese oth index pr pehle data set---accept method index wise dataset leti hai--usme se data nikal leti hai