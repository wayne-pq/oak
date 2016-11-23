package cn.xxywithpq.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.kitesdk.shaded.com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.store.strategy.naming.ChainedFileNamingStrategy;
import org.springframework.data.hadoop.store.strategy.naming.FileNamingStrategy;
import org.springframework.data.hadoop.store.strategy.naming.RollingFileNamingStrategy;
import org.springframework.data.hadoop.store.strategy.naming.StaticFileNamingStrategy;
import org.springframework.data.hadoop.store.strategy.naming.UuidFileNamingStrategy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.xxywithpq.domain.Icon;
import cn.xxywithpq.hadoop.store.output.MyOutputStreamWriter;

@RestController
@RequestMapping("/hadoop/")
public class HadoopController {

	private final static Log log = LogFactory.getLog(HadoopController.class);
	
	private final static String preTempIcon = "TempIcon-";
	
	private static Calendar now = Calendar.getInstance();

	@Autowired
	private org.apache.hadoop.conf.Configuration hadoopConfiguration;
	
    @Autowired
    private RedisTemplate<String, Icon> iconRedisTemplate;

	@RequestMapping(method = RequestMethod.POST, value = "icon/upload")
	public Object iconUpload(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		Map<String, Object> object = Maps.newLinkedHashMap();
		
		try {
			
			String ipAddr = cn.xxywithpq.utils.NetUtils.getIpAddr(request);
			
			log.info("someone attempt to upload icon , ip: " + ipAddr);
			
			Path path = new Path("/icon3");
			MyOutputStreamWriter writer = new MyOutputStreamWriter(hadoopConfiguration, path, null);

			ChainedFileNamingStrategy strategy = new ChainedFileNamingStrategy();

			UuidFileNamingStrategy uuidFileNamingStrategy = new UuidFileNamingStrategy();
			StaticFileNamingStrategy staticFileNamingStrategy = new StaticFileNamingStrategy("icon");
			StaticFileNamingStrategy staticFileNamingStrategyafter = new StaticFileNamingStrategy("png", ".");
			RollingFileNamingStrategy rollingFileNamingStrategy = new RollingFileNamingStrategy();

			strategy.setStrategies(Arrays.asList(new FileNamingStrategy[] { staticFileNamingStrategy,
					uuidFileNamingStrategy, rollingFileNamingStrategy, staticFileNamingStrategyafter }));

			writer.setFileNamingStrategy(strategy);
			writer.write(file.getBytes());
			Path filepath = writer.getFilePath();
			String iconpath = "http://www.xxywithpq.cn:50070/webhdfs/v1" + filepath + "?op=OPEN";
			
			Icon icon = new Icon(preTempIcon+UUID.randomUUID().toString(), filepath+"", ipAddr);
			
			//icon先存进redis，正式注册再存入数据库
			iconRedisTemplate.opsForValue().set(icon.getId(), icon);
			now.setTime(new Date());
			now.add(Calendar.MINUTE, 3);
			iconRedisTemplate.expireAt(icon.getId(), now.getTime());
			
			log.info("return filepath = " + iconpath);
			object.put("iconpath", iconpath);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}

}
