package cn.xxywithpq.config;

import java.util.Arrays;

import org.kitesdk.data.Formats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.hadoop.store.DataStoreWriter;
import org.springframework.data.hadoop.store.dataset.AvroPojoDatasetStoreWriter;
import org.springframework.data.hadoop.store.dataset.DatasetDefinition;
import org.springframework.data.hadoop.store.dataset.DatasetOperations;
import org.springframework.data.hadoop.store.dataset.DatasetRepositoryFactory;
import org.springframework.data.hadoop.store.dataset.DatasetTemplate;

import cn.xxywithpq.domain.Icon;

@Configuration
@ImportResource("hadoop-context.xml")
public class DatasetConfig {

	@Autowired
	private org.apache.hadoop.conf.Configuration hadoopConfiguration;

	@Bean
	public DatasetRepositoryFactory datasetRepositoryFactory() {
		DatasetRepositoryFactory datasetRepositoryFactory = new DatasetRepositoryFactory();
		datasetRepositoryFactory.setConf(hadoopConfiguration);
		datasetRepositoryFactory.setBasePath("/icon2");
		datasetRepositoryFactory.setNamespace("default");
		return datasetRepositoryFactory;
	}

	@Bean
	public DataStoreWriter<Icon> dataStoreWriter() {
		return new AvroPojoDatasetStoreWriter<Icon>(Icon.class, datasetRepositoryFactory(),
				fileInfoDatasetDefinition());
	}

	@Bean
	public DatasetOperations datasetOperations() {
		DatasetTemplate datasetOperations = new DatasetTemplate();
		datasetOperations.setDatasetDefinitions(Arrays.asList(fileInfoDatasetDefinition()));
		datasetOperations.setDatasetRepositoryFactory(datasetRepositoryFactory());
		return datasetOperations;
	}

	@Bean
	public DatasetDefinition fileInfoDatasetDefinition() {
		DatasetDefinition definition = new DatasetDefinition();
		definition.setFormat(Formats.AVRO.getName());
		definition.setTargetClass(Icon.class);
		definition.setAllowNullValues(false);
		return definition;
	}
}