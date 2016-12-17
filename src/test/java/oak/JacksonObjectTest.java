package oak;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.xxywithpq.domain.User;
/**
 * jackson基本操作
 * @author panqian
 * @date 2016年11月21日 下午1:32:19
 */
public class JacksonObjectTest {

	@Test
	public void encode() throws IOException {

		User user = new User(Long.MIN_VALUE, "PAN", "123", true, null);

		StringWriter sw = new StringWriter();
		JsonFactory jfactory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(jfactory);
		mapper.writeValue(sw, user);
		System.out.println(sw.toString());

		StringWriter sw1 = new StringWriter();
		JsonGenerator responseJson = jfactory.createGenerator(sw1);
		responseJson.writeStartObject();
		responseJson.writeStringField("231", "12312321");
		responseJson.writeEndObject();
		responseJson.close();
		System.out.println(sw1.toString());
	}
}
