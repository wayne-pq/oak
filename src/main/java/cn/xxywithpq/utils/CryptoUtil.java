package cn.xxywithpq.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * 加密/解密 工具类
 * 
 * @author panqian
 * @date 2016年11月27日 下午6:01:01
 */
public class CryptoUtil {

	private final static Log log = LogFactory.getLog(CryptoUtil.class);

	private static StandardPasswordEncoder s = new StandardPasswordEncoder("oak");

	public static String encoder(String rawPassword) {
		String encode = "";
		if (null != rawPassword && !"".equals(rawPassword)) {
			encode = s.encode(rawPassword);
		}
		log.info("after Encoder : " + encode);
		return encode;
	}
	
	public static Boolean matches(String rawPassword, String encodedPassword) {
		return s.matches(rawPassword,encodedPassword);
	}
}
