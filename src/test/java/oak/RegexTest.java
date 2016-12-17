package oak;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {

	@Test
	public void regex(){
		String str = "https://www.xxywithpq.cn:50470/webhdfs/v1/icon3/icon-71637ef6-351c-43ba-998e-ab60b33567c5-0.png?op=OPEN";
		
		String pattern = "icon-.+(?=.png)";

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(str);
		if(m.find()){
			System.out.println(m.group(0));
		}
		
	}
}
