package cn.xxywithpq.hadoop.store.input;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.springframework.data.hadoop.store.codec.CodecInfo;
import org.springframework.data.hadoop.store.output.OutputStreamWriter;

public class MyOutputStreamWriter extends OutputStreamWriter {

	private final static Log log = LogFactory.getLog(MyOutputStreamWriter.class);

	
	public MyOutputStreamWriter(Configuration configuration, Path basePath, CodecInfo codec) {
		super(configuration, basePath, codec);
	}
	
	public Path getFilePath() throws IOException{
		return super.getOutput().getPath();
	}
	
	

}
