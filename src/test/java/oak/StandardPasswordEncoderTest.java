package oak;

import org.junit.Test;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
/**
 * 加密文件测试
 * @author panqian
 * @date 2016年10月27日 下午10:34:29
 */
public class StandardPasswordEncoderTest {

	@Test
	public void encode() {
		//构造参数 传入额外的保护密文
		String rawPassword = "xxywithpq";
		StandardPasswordEncoder s = new StandardPasswordEncoder("oak");
		String encode = s.encode(rawPassword);
		System.out.println("加密后的密码:" + encode);
		System.out.println("是否匹配？ " + s.matches(rawPassword, encode));
	}
}
