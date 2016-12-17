package cn.xxywithpq.hadoop.store.output;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.Syncable;
import org.springframework.data.hadoop.store.DataStoreWriter;
import org.springframework.data.hadoop.store.codec.CodecInfo;
import org.springframework.data.hadoop.store.event.FileWrittenEvent;
import org.springframework.data.hadoop.store.event.StoreEventPublisher;
import org.springframework.data.hadoop.store.output.AbstractDataStreamWriter;
import org.springframework.data.hadoop.store.support.OutputContext;
import org.springframework.data.hadoop.store.support.StreamsHolder;

/**
 * 
 * @author panqian
 * @date 2016年11月15日 下午8:30:12
 */
public class MyOutputStreamWriter extends AbstractDataStreamWriter implements DataStoreWriter<byte[]> {

	private final static Log log = LogFactory.getLog(MyOutputStreamWriter.class);

	private StreamsHolder<OutputStream> streamsHolder;

	public Path filePath;
	
	/**
	 * Instantiates a new output stream writer.
	 *
	 * @param configuration the configuration
	 * @param basePath the base path
	 * @param codec the codec
	 */
	public MyOutputStreamWriter(Configuration configuration, Path basePath, CodecInfo codec) {
		super(configuration, basePath, codec);
	}

	@Override
	public void flush() throws IOException {
		if (streamsHolder != null) {
			streamsHolder.getStream().flush();
		}
	}

    public synchronized  void hflush() throws IOException {
        if (streamsHolder != null) {
            ((Syncable)streamsHolder.getStream()).hflush();
        }
    }

	@Override
	public synchronized void close() throws IOException {
		if (streamsHolder != null) {
			streamsHolder.close();

			Path path = renameFile(streamsHolder.getPath());

			StoreEventPublisher storeEventPublisher = getStoreEventPublisher();
			if (storeEventPublisher != null) {
				storeEventPublisher.publishEvent(new FileWrittenEvent(this, path));
			}

			streamsHolder = null;
		}
	}

	@Override
	public synchronized void write(byte[] entity) throws IOException {
		if (streamsHolder == null) {
			streamsHolder = getOutput();
		}
		OutputStream out = streamsHolder.getStream();
		out.write(entity);

		setWritePosition(getPosition(streamsHolder));

		OutputContext context = getOutputContext();
		if (context.getRolloverState()) {
			log.info("After write, rollover state is true");
			close();
			context.rollStrategies();
		}
		
		filePath = streamsHolder.getPath();
	}

	@Override
	protected void handleTimeout() {
		try {
			if (isAppendable()) {
				log.info("Timeout detected for this writer, flushing stream");
				hflush();
			} else {
				log.info("Timeout detected for this writer, closing stream");
				close();
			}
		} catch (IOException e) {
			log.error("Error closing", e);
		}
		getOutputContext().rollStrategies();
	}

	public Path getFilePath() {
		return filePath;
	}

	public void setFilePath(Path filePath) {
		this.filePath = filePath;
	}
}
