package com.scalefocus.java.dbconfig;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Data
@Configuration
public class RoutingConfiguration {

  private final Map<Object, Object> targetDataSources = new HashMap<>();

  @Autowired
  private Environment env;

  @Bean(name = "sqliteDataSource")
  public DataSource dataSource() {
    return DataSourceBuilder.create()
        .username(env.getProperty("spring.datasource.username"))
        .password(env.getProperty("spring.datasource.password"))
        .url(env.getProperty("remote.datasource.url") + "defaultDB.db")
        .driverClassName(env.getProperty("remote.datasource.driver-class-name"))
        .build();
  }


  @Bean(name = "routerDb")
  public DataSource addDataSourceToMap() {
    DataSource defaultDataSource = dataSource();
    DynamicDataSourceHolder.setRouteKey(env.getProperty("remote.defaultdb"));
    targetDataSources.put(env.getProperty("remote.defaultdb"), defaultDataSource);
    MultipleDataSource clientRoutingDatasource
        = new MultipleDataSource();
    clientRoutingDatasource.setTargetDataSources(targetDataSources);
    clientRoutingDatasource.setDefaultTargetDataSource(defaultDataSource);
    clientRoutingDatasource.afterPropertiesSet();
    return clientRoutingDatasource;
  }

}
