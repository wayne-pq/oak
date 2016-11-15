package cn.xxywithpq.web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.store.DataStoreWriter;
import org.springframework.data.hadoop.store.dataset.DatasetOperations;
import org.springframework.data.hadoop.store.strategy.naming.ChainedFileNamingStrategy;
import org.springframework.data.hadoop.store.strategy.naming.FileNamingStrategy;
import org.springframework.data.hadoop.store.strategy.naming.RollingFileNamingStrategy;
import org.springframework.data.hadoop.store.strategy.naming.StaticFileNamingStrategy;
import org.springframework.data.hadoop.store.strategy.naming.UuidFileNamingStrategy;
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
	
	@Autowired
	private org.apache.hadoop.conf.Configuration hadoopConfiguration;

	private DataStoreWriter<Icon> writer;
	private DatasetOperations datasetOperations;

	@Autowired
	public void setDatasetOperations(DatasetOperations datasetOperations) {
		this.datasetOperations = datasetOperations;
	}

	@Autowired
	public void setDataStoreWriter(DataStoreWriter<Icon> dataStoreWriter) {
		this.writer = dataStoreWriter;
	}

	@RequestMapping(method = RequestMethod.POST, value = "icon/upload")
	public void iconUpload(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		try {

			Path path = new Path("/icon3");
			MyOutputStreamWriter writer = new MyOutputStreamWriter(hadoopConfiguration, path, null);

			ChainedFileNamingStrategy strategy = new ChainedFileNamingStrategy();

			UuidFileNamingStrategy uuidFileNamingStrategy = new UuidFileNamingStrategy();
			StaticFileNamingStrategy staticFileNamingStrategy = new StaticFileNamingStrategy("icon");
			StaticFileNamingStrategy staticFileNamingStrategy1 = new StaticFileNamingStrategy("png", ".");
			RollingFileNamingStrategy rollingFileNamingStrategy = new RollingFileNamingStrategy();
			strategy.setStrategies(Arrays.asList(new FileNamingStrategy[] { staticFileNamingStrategy,
					uuidFileNamingStrategy, rollingFileNamingStrategy, staticFileNamingStrategy1 }));
			writer.setFileNamingStrategy(strategy);
			writer.write(file.getBytes());
			Path filepath = writer.getFilePath();
			log.info(filepath.toString());
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
