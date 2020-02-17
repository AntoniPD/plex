package com.scalefocus.java.service;

import com.scalefocus.java.dbconfig.DynamicDataSourceHolder;
import com.scalefocus.java.dbconfig.MultipleDataSource;
import com.scalefocus.java.dbconfig.RoutingConfiguration;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class DataSourceService {

  private final Environment env;
  private final RoutingConfiguration routingTestConfiguration;
  private final MultipleDataSource multipleDataSource;

  @Autowired
  public DataSourceService(Environment env, RoutingConfiguration routingTestConfiguration, MultipleDataSource multipleDataSource) {
    this.env = env;
    this.routingTestConfiguration = routingTestConfiguration;
    this.multipleDataSource = multipleDataSource;
  }

  public void createDataSource(String datasource) {
    DynamicDataSourceHolder.setRouteKey(datasource);
    DataSource dataSourceBuilder = DataSourceBuilder.create()
        .username(env.getProperty("spring.datasource.username"))
        .password(env.getProperty("spring.datasource.password"))
        .url(env.getProperty("remote.datasource.url") + datasource)
        .driverClassName(env.getProperty("remote.datasource.driver-class-name"))
        .build();
    routingTestConfiguration.getTargetDataSources().put(datasource, dataSourceBuilder);
    multipleDataSource.afterPropertiesSet();
  }
}
