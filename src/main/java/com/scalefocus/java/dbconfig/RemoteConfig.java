package com.scalefocus.java.dbconfig;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "sqliteEntityManagerFactory",
    basePackages = {"com.scalefocus.java.repository.remote"},
    transactionManagerRef = "sqliteTransactionManager"
)
public class RemoteConfig {

  @Autowired
  private Environment env;

  @Autowired
  @Qualifier("routerDb")
  private DataSource dataSource;

  @Bean(name = "sqliteEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em
        = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource);
    em.setPackagesToScan(
        "com.scalefocus.java.domain.remote");

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    Map<String, Object> properties = new HashMap<>();
    properties.put("hibernate.dialect",
        env.getProperty("remote.hibernate.dialect"));
    em.setJpaPropertyMap(properties);

    return em;
  }

  @Bean(name = "sqliteTransactionManager")
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
          entityManagerFactory().getObject());
        return transactionManager;
  }
}
