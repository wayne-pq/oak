package oak;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class CommonTest {

	@Test
	public void test1() throws UnsupportedEncodingException {
		byte[] bytes = "我爱中国".getBytes("GBK");

		String string = new String(bytes, "GBK");
		System.out.println(string);
	}
}
