package cn.xxywithpq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.config.annotation.EnableHadoop;
import org.springframework.data.hadoop.config.annotation.SpringHadoopConfigurerAdapter;
import org.springframework.data.hadoop.config.annotation.builders.HadoopConfigConfigurer;

@Configuration
@EnableHadoop 
public class HadoopConfig extends SpringHadoopConfigurerAdapter {

  @Override
  public void configure(HadoopConfigConfigurer config) throws Exception {
    config
      .fileSystemUri("hdfs://123.206.26.77:9000");
  }

}